package com.sofn.common.excel;

import com.google.common.collect.Lists;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.core.ExcelImport;
import com.sofn.common.excel.exception.ExcelException;
import com.sofn.common.excel.service.ExcelDataBatchInsertService;
import com.sofn.common.excel.test.SiteImport;
import com.sofn.common.excel.util.ExcelReflectionUtil;
import com.sofn.common.excel.util.ReadExcelForkJoin;
import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 导入工具
 * @author heyongjie
 * @date 2019/11/15 15:09
 */
@Slf4j
public class ExcelImportUtil {

    /**
     * 根据上传的文件获取数据
     * @param multipartFile  上传的文件
     * @param cls  Excel注解类
     * @param <T>  泛型
     * @return  根据上传的文件获取的数据
     * @throws ExcelException  读取文件异常
     */
    public static <T> List<T> getDataByExcel(MultipartFile multipartFile, Class<T> cls) throws ExcelException{
        return getDataByExcel(multipartFile,-1,cls,null);
    }

    /**
     * 导入文件获取数据
     * @param filePath    文件路径  C:\Users\heyongjie\Desktop\testReadExcel.xlsx
     * @param headerNum   如果使用的是导出模板然后在导入请传入-1
     * @param cls         Excel注解标志类
     * @param <T>         泛型
     * @return            读取Excel后产生的数据
     */
    public static <T> List<T>  getDataByExcel(String filePath, int headerNum, Class<T> cls) throws ExcelException {
        // 默认读第一个Sheet
        List<T> data;
        try{
            data =  getDataByExcelAndSheet(filePath,headerNum,cls, ExcelField.Type.IMPORT,0);
        }catch (ExcelException e){
            throw  new ExcelException(e.getMessage());
        }
        return data;
    }

    /**
     * 根据上传的文件获取数据
     * @param multipartFile  上传的文件
     * @param headerNum    从哪行开始读取
     * @param cls     Excel注解类
     * @param sheetIndexOrName   读取第几个sheet 默认第0个
     * @param <T>  泛型
     * @return  根据上传的文件获取的数据
     * @throws ExcelException  读取文件异常
     */
    public static <T> List<T> getDataByExcel(MultipartFile multipartFile, int headerNum, Class<T> cls, Object sheetIndexOrName) throws ExcelException{
        List<T> data;
        // 默认值
        if(headerNum == -1 ){
            headerNum = ExcelReflectionUtil.getHeadNum(cls);
        }
        if(sheetIndexOrName == null){
            sheetIndexOrName = 0;
        }
        try (
          InputStream is = multipartFile.getInputStream();
          ExcelImport excelImport = new ExcelImport(multipartFile.getOriginalFilename(),is,headerNum,sheetIndexOrName);
          ){
            data = excelImport.getDataList(cls, ExcelField.Type.IMPORT);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            throw  new ExcelException("读取文件异常");
        }
        return data;
    }




    /**
     * 指定Sheet获取数据
     * @param filePath    文件路径  C:\Users\heyongjie\Desktop\testReadExcel.xlsx
     * @param headerNum   如果使用的是导出模板然后在导入请传入-1
     * @param cls         Excel注解标志类
     * @param sheet       指定的sheet
     * @param <T>         泛型
     * @return            读取Excel后产生的数据
     */
    public static <T> List<T>  getDataByExcelAndSheet(String filePath, int headerNum, Class<T> cls, ExcelField.Type type,Object sheet){
        List<T> data = Lists.newArrayList();
        try {
            if(headerNum == -1 ){
                headerNum = ExcelReflectionUtil.getHeadNum(cls);
            }
            if(sheet == null){
                sheet = 0;
            }
            ExcelImport  excelImport = new ExcelImport(new File(filePath),headerNum,sheet);
            data = excelImport.getDataList(cls,type);
        } catch (Exception e) {
            e.printStackTrace();
            // 文件未找到异常
        }
        return data;
    }

    /**
     * 根据Excel批量插入数据
     * @param multipartFile   文件
     * @param cls   类信息
     * @param headerNum   从哪行开始读取
     * @param  excelDataBatchInsertService 实现ExcelDataBatchInsertService的类
     */
    public static void batchInsertByExcel(MultipartFile multipartFile, Class cls, int headerNum, ExcelDataBatchInsertService excelDataBatchInsertService){
        InputStream is = null;
        try {
            is = multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();

            ExcelImport excelImport = new ExcelImport(fileName, is, headerNum, 0);
            // 1. 先获取Excel总行数
            int lastDataRowNum = excelImport.getLastDataRowNum();
            // 2. 根据总行数分别启动多个线程来读取数据
            ReadExcelForkJoin readExcelForkJoin = new ReadExcelForkJoin(headerNum, lastDataRowNum, excelImport, cls, excelDataBatchInsertService);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            ForkJoinTask<Integer> submit = forkJoinPool.submit(readExcelForkJoin);
            submit.get();
            if (submit.isCompletedAbnormally()) {
                Throwable exception = submit.getException();
                exception.printStackTrace();
                throw new SofnException(exception.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        log.info("开始");
        batchInsertByExcel(null, SiteImport.class,2,null);
        log.info("结束");
    }


}
