package com.sofn.common.excel.core;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.exception.ExcelException;
import com.sofn.common.excel.util.ExcelReflectionUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Excel导入工具类
 * Created by heyongjie on 2019/6/13 13:53
 */
public class ExcelImport implements Closeable {

    private static Logger log = LoggerFactory.getLogger(ExcelImport.class);

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 标题行数
     */
    private int headerNum;

    /**
     * 用于清理缓存
     */
    private Set<Class<?>> fieldTypes = Sets.newHashSet();

    /**
     * 构造函数
     *
     * @param file      需要读取的文件
     * @param headerNum 从哪行开始读取   从0开始计算 和Excel中的不一样
     * @throws Exception
     */
    public ExcelImport(File file, int headerNum)
            throws Exception {
        this(file, headerNum, 0);
    }

    /**
     * 构造函数
     *
     * @param file             需要读取的文件
     * @param headerNum        从哪行开始读取
     * @param sheetIndexOrName Sheet位置
     * @throws Exception
     */
    public ExcelImport(File file, int headerNum, Object sheetIndexOrName)
            throws Exception {
        this(file.getName(), new FileInputStream(file), headerNum, sheetIndexOrName);
    }

    /**
     * 构造行数
     *
     * @param fileName         文件名称
     * @param is               文件流
     * @param headerNum        从哪行开始读取
     * @param sheetIndexOrName Sheet位置
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ExcelImport(String fileName, InputStream is, int headerNum, Object sheetIndexOrName)
            throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        if (StringUtils.isBlank(fileName)) {
            throw new ExcelException("导入文档为空!");
        }else if (fileName.toLowerCase().endsWith("xls")||fileName.toLowerCase().endsWith("xlsx")) {
            //update by wuXY 2019-12-18 18:17:04 excel的版本不能根据后缀来判断，使用WorkbookFactory；
//            this.wb = new HSSFWorkbook(is);
            this.wb= WorkbookFactory.create(is);
        }
//        else if (fileName.toLowerCase().endsWith("xlsx")) {
//            this.wb = new XSSFWorkbook(is);
//        }
        else {
            throw new ExcelException("文档格式不正确!");
        }
        this.setSheet(sheetIndexOrName, headerNum);
        log.debug("Initialize success.");
    }


    /**
     * 设置当前工作表和标题行数
     *
     * @author ThinkGem
     */
    public void setSheet(Object sheetIndexOrName, int headerNum) {
        if (sheetIndexOrName instanceof Integer || sheetIndexOrName instanceof Long) {
            this.sheet = this.wb.getSheetAt(Integer.parseInt(sheetIndexOrName + ""));
        } else {
            this.sheet = this.wb.getSheet(ObjectUtils.toString(sheetIndexOrName));
        }
        if (this.sheet == null) {
            throw new ExcelException("没有找到‘" + sheetIndexOrName + "’工作表!");
        }
        this.headerNum = headerNum;
    }

    /**
     * 获取行对象
     *
     * @param rownum
     * @return 返回Row对象，如果空行返回null
     */
    public Row getRow(int rownum) {
        Row row = this.sheet.getRow(rownum);
        if (row == null) {
            return null;
        }
        // 验证是否是空行，如果空行返回null
        short cellNum = 0;
        short emptyNum = 0;
        Iterator<Cell> it = row.cellIterator();
        while (it.hasNext()) {
            cellNum++;
            Cell cell = it.next();
            if (StringUtils.isBlank(cell.toString())) {
                emptyNum++;
            }
        }
        if (cellNum == emptyNum) {
            return null;
        }
        return row;
    }

    /**
     * 获取开始读取位置
     *
     * @return
     */
    public int getDataRowNum() {
        return headerNum;
    }

    /**
     * 获取最后一个数据行号
     *
     * @return
     */
    public int getLastDataRowNum() {
        return this.sheet.getLastRowNum() + 1;
    }

    /**
     * 获取导入数据列表
     *
     * @param cls 导入对象类型
     */
    public <E> List<E> getDataList(Class<E> cls, ExcelField.Type type) throws ExcelException {
        return getDataList(cls,type,this.getDataRowNum(),this.getLastDataRowNum());
    }


    /**
     * 获取导入数据列表
     *
     * @param cls 导入对象类型
     */
    public <E> List<E> getDataList(Class<E> cls, ExcelField.Type type,int start,int end) throws ExcelException {
        // 读取Excel获取数据
        List<E> dataList = Lists.newArrayList();
        for (int i = start; i < end; i++) {
            // 行
            Row row = this.getRow(i);
            if (row == null) {
                continue;
            }
            try {
                Object obj = ExcelReflectionUtil.setValueByRowAndClass(row, cls, 0,type);
                dataList.add((E) obj);
            } catch (Exception ex) {
                ex.printStackTrace();
                if(ex instanceof ExcelException){
                    throw new ExcelException(ex.getMessage());
                }
                throw new ExcelException("Excel解析有误，请检查数据！");
            }
        }
        return dataList;
    }


    @Override
    public void close() {
        Iterator<Class<?>> it = fieldTypes.iterator();
        while (it.hasNext()) {
            Class<?> clazz = it.next();
            try {
                clazz.getMethod("clearCache").invoke(null);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}
