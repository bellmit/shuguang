package com.sofn.common.excel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.core.ExcelExport;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导出Excel工具
 *
 * @author heyongjie
 * @date 2019/11/15 15:06
 */
@Slf4j
public class ExcelExportUtil {

    /**
     * 创建Excel
     *
     * @param filePath 创建后的文件路径
     * @param cls      添加了Excel注解信息的类
     * @param list     数据，如果数据为空就会生成模板信息
     * @param <E>      E
     */
    public static <E> void createExcel(String filePath, Class cls, List<E> list) {
        createExcel(filePath, cls, list, ExcelField.Type.EXPORT);
    }

    /**
     * 创建Excel
     *
     * @param filePath 创建后的文件路径
     * @param cls      添加了Excel注解信息的类
     * @param list     数据，如果数据为空就会生成模板信息
     * @param <E>      E
     */
    public static <E> void createExcel(String filePath, Class cls, List<E> list, ExcelField.Type type) {
        try {
            ExcelExport ee =  createExcel(cls,list,type);
            ee.writeFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建Excel
     * @param cls      添加了Excel注解信息的类
     * @param list     数据，如果数据为空就会生成模板信息
     * @param <E>      E
     * @return   ExcelExport
     */
    public static  <E> ExcelExport createExcel( Class cls, List<E> list, ExcelField.Type type){
        ExcelExport ee;
        if (type != null) {
            ee = new ExcelExport(cls, type);
        } else {
            ee = new ExcelExport(cls);
        }
        ee.setDataList(list, type,cls);
        return ee;
    }


    /**
     * 创建Excel
     *
     * @param response   HttpServletResponse
     * @param cls  添加了Excel注解信息的类
     * @param list 数据，如果数据为空就会生成模板信息
     * @param <E>  E
     */
    public static <E> void createExcel(HttpServletResponse response, Class cls, List<E> list) {
        createExcel(response,cls,list,"excel.xlsx");
    }

    /**
     * 创建Excel
     *
     * @param response   HttpServletResponse
     * @param cls  添加了Excel注解信息的类
     * @param list 数据，如果数据为空就会生成模板信息
     * @param <E>  E
     */
    public static <E> void createExcel(HttpServletResponse response, Class cls, List<E> list,String fileName) {
        try{
            /*response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);*/
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename="+fileName+";filename*=utf-8''"
              + URLEncoder.encode(fileName, "utf-8"));
            ExcelExport ee =  createExcel(cls,list, ExcelField.Type.EXPORT);
            ee.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建Excel
     *
     * @param response   HttpServletResponse
     * @param cls  添加了Excel注解信息的类
     * @param list 数据，如果数据为空就会生成模板信息
     * @param <E>  E
     */
    public static <E> void createExcel(HttpServletResponse response, Class cls, List<E> list, ExcelField.Type type,String fileName) {
        try{
            ExcelExport ee =  createExcel(cls,list,type);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName+";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            ee.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建Excel
     *
     * @param response   HttpServletResponse
     * @param cls  添加了Excel注解信息的类
     * @param list 数据，如果数据为空就会生成模板信息
     * @param <E>  E
     */
    public static <E> void createExcel(HttpServletResponse response, Class cls, List<E> list, ExcelField.Type type) {
        createExcel(response, cls, list, type,"excel.xlsx");
    }


    /**
     * 创建模板  创建模板的时候可以导出Type.All 和Type.IMPORT
     *
     * @param filePath 文件生成路径
     * @param cls      Excel注解类
     */
    public static void createTemplate(String filePath, Class cls) {
        createExcel(filePath, cls, null, ExcelField.Type.IMPORT);
    }

/*
    *//**
     * 创建多个Sheet
     *
     * @param filePath 文件路径
     * @param cls      多个类信息
     * @param datas    类所对应的数据   必须和cls的顺序相同
     * @param <E>      E
     *//*
    public static <E> void createExcelMoreSheet(String filePath, List<Class> cls, List<List<E>> datas, ExcelField.Type type) {
        if (cls == null || cls.size() == 0) {
            return;
        }
        if (datas != null && datas.size() != cls.size()) {
            log.info("【创建具有多个Sheet的Excel】当数据不为空时，数据数量应该与类数量相同，为空请用null占位");
            return;
        }
        ExcelExport ee = new ExcelExport(cls.get(0));
        ee.setDataList(datas == null ? null : datas.get(0), type);
        // 创建多个Sheet
        for (int i = 1; i < cls.size(); i++) {
            Class clazz = cls.get(i);
            ee.createSheet(null, clazz, type);
            ee.setAnnotationList(ExcelReflectionUtil.getClassInfo(clazz, type));
            ee.setDataList(datas == null ? null : datas.get(i), type);
        }
        try {
            ee.writeFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
