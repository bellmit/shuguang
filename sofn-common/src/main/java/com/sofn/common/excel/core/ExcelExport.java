/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.sofn.common.excel.core;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelFields;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.exception.ExcelException;
import com.sofn.common.excel.util.ExcelCheckUtil;
import com.sofn.common.excel.util.ExcelDictUtil;
import com.sofn.common.excel.util.ExcelReflectionUtil;
import com.sofn.common.utils.DateUtils;
import lombok.Setter;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 导出Excel文件（导出“XLSX”格式）
 * <p>
 * Created by heyongjie on 2019/6/13 13:53
 */
public class ExcelExport {

    private static Logger log = LoggerFactory.getLogger(ExcelExport.class);

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 当前行号
     */

    private AtomicInteger rownum;

    /**
     * 注解列表
     * Object[] -->  第一个为Field，第二个为ExcelField注解，第三个为ExcelImportCheck注解，第4个为检查属性@ExcelFields
     */
    @Setter
    private List<Object[]> annotationList;

    /**
     * 构造函数
     *
     * @param cls 读取后转为的具体类
     */
    public ExcelExport(Class<?> cls) {
        this(cls, null, null);
    }

    /**
     * 构造函数
     *
     * @param cls  读取后转为的具体类
     * @param type ExcelField.Type  导入还是导出
     */
    public ExcelExport(Class<?> cls, ExcelField.Type type) {
        this(cls, type, null);
    }


    /**
     * 构造函数
     *
     * @param cls              读取后转为的具体类
     * @param type             ExcelField.Type  导入还是导出
     * @param sheetNameOrIndex 指定Sheet名称
     */
    public ExcelExport(Class<?> cls, ExcelField.Type type, String sheetNameOrIndex) {
        this.wb = createWorkbook();
        // 获取列信息并排序
        this.annotationList = ExcelReflectionUtil.getClassInfo(cls, type);
        // 生成一个Sheet
        this.createSheet(sheetNameOrIndex, cls, type);
    }

    /**
     * 构造函数
     *
     * @param cls 导出数据的类型参数
     * @param empty 占位参数，传空串就好
     */
    public ExcelExport(Class<?> cls, String empty) {
        this.wb = createWorkbook();
        // 获取列信息并排序
        this.annotationList = ExcelReflectionUtil.getClassInfo(cls, ExcelField.Type.EXPORT);
    }

    /**
     * 无参构造函数
     */
    public ExcelExport() {
        this.wb = createWorkbook();
    }

    /**
     * 创建一个工作簿
     */
    public Workbook createWorkbook() {
        return new SXSSFWorkbook(500);
    }


    /**
     * 创建Sheet
     *
     * @param sheetName       sheet名称
     * @param title           标题
     * @param headerList      列标题
     * @param headerWidthList 列宽度
     */
    public void createSheet(String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        if (StringUtils.isBlank(sheetName)) {
            sheetName = "Sheet1";
        }
        this.sheet = wb.createSheet(StringUtils.defaultString(sheetName, StringUtils.defaultString(title, "Sheet1")));
        this.styles = createStyles(wb);
        this.rownum = new AtomicInteger(0);
        // 创建标题
        if (StringUtils.isNotBlank(title)) {
            Row titleRow = sheet.createRow(rownum.get());
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
                    titleRow.getRowNum(), titleRow.getRowNum(), headerList.size() - 1));
        }
        // 创建列标题
        if (headerList == null) {
            throw new ExcelException("headerList not null!");
        }
        // 可以不要标题
        Row headerRow ;
        if(StringUtils.isNotBlank(title)){
            headerRow = sheet.createRow(rownum.incrementAndGet());
        }else{
            headerRow = sheet.createRow(rownum.get());
        }
        headerRow.setHeightInPoints(16);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get("header"));
            cell.setCellValue(headerList.get(i));
        }
        // 设置每列的宽度
        boolean isDefWidth = (headerWidthList != null && headerWidthList.size() == headerList.size());

        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = -1;
            if (isDefWidth) {
                colWidth = headerWidthList.get(i);
            }
            if (colWidth == -1) {
                colWidth = sheet.getColumnWidth(i) * 2;
                colWidth = colWidth < 3000 ? 3000 : colWidth;
            }
            if (colWidth == 0) {
                sheet.setColumnHidden(i, true);
            } else {
                sheet.setColumnWidth(i, colWidth);
            }
        }
        log.debug("Create sheet {} success.", sheetName);

    }

    /**
     * 创建工作表
     *
     * @param sheetName 如果sheetName不传的话会从Class中注解上获取，
     *                  因为Sheet名称不能重复，作用于同一个类需要导入多个Sheet的情况
     * @param cls       Excel 注解类
     */
    public void createSheet(String sheetName, Class cls, ExcelField.Type type) {
        if (StringUtils.isBlank(sheetName)) {
            sheetName = ExcelReflectionUtil.getSheetTitle(cls);
        }
        String title = ExcelReflectionUtil.getTableTitle(cls);
        List<Object[]> titleInfos = ExcelReflectionUtil.getAllFieldInfo(cls, type);
        List<String> headerList = new ArrayList<>();
        List<Integer> headerWidthList = Lists.newArrayList();
        for (Object[] titleInfo : titleInfos) {
            headerList.add((String) titleInfo[0]);
            headerWidthList.add((Integer) titleInfo[1]);
        }
        this.createSheet(sheetName, title, headerList, headerWidthList);

    }

    /**
     * 将表格样式放在Map中，方便取出来使用
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = Maps.newHashMap();
        // 标题样式
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        style.setFont(titleFont);
        styles.put("title", style);

        // 列样式
        style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("data3", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);

        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());   // 设置列信息颜色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }

    /**
     * 添加一行
     *
     * @return 行对象
     */
    public Row addRow() {
        Row row = sheet.createRow(rownum.incrementAndGet());
        if (row == null) {
            System.out.println(123);
        }
        return row;
    }


    /**
     * 添加一个单元格
     *
     * @param row        添加的行
     * @param column     添加列号
     * @param val        添加值
     * @param align      对齐方式（1：靠左；2：居中；3：靠右）
     * @param dataFormat 数值格式（例如：0.00，yyyy-MM-dd）
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val, ExcelField.Align align, String dataFormat) {
        Cell cell = row.createCell(column);
        try {
            if (val == null) {
                cell.setCellValue("");
            } else {
                if (val instanceof String) {
                    cell.setCellValue((String) val);
                } else if (val instanceof Integer) {
                    cell.setCellValue((Integer) val);
                } else if (val instanceof Long) {
                    cell.setCellValue((Long) val);
                } else if (val instanceof Double) {
                    cell.setCellValue((Double) val);
                } else if (val instanceof Float) {
                    cell.setCellValue((Float) val);
                } else if (val instanceof Date) {
                    // 按照特定格式导出
                    if ("@".equals(dataFormat)) {
                        dataFormat = DateUtils.DATE_PATTERN;
                    }
                    if (val instanceof Timestamp){
                        dataFormat = DateUtils.DATE_TIME_PATTERN;
                    }
                    String date = DateUtils.format((Date) val, dataFormat);
                    cell.setCellValue(date);
                    ExcelCheckUtil.setCellStyle(this.wb, cell, dataFormat);
                } else if (val instanceof BigDecimal) {
                    cell.setCellValue(new BigDecimal(val.toString()).doubleValue());
                } else if (val instanceof BigInteger) {
                    cell.setCellValue(new BigInteger(val.toString()).intValue());
                } else if (val instanceof IEnum) {
                    cell.setCellValue(val.toString());
                }
            }
        } catch (Exception ex) {
            cell.setCellValue(ObjectUtils.toString(val));
        }
        return cell;
    }

    /**
     * 添加数据（通过annotation.ExportField添加数据）
     *
     * @return list 数据列表
     */
    public <E> ExcelExport setDataList(List<E> list, ExcelField.Type type, Class<?> tClass) {
        createTemplate(tClass, this.sheet, type);
        log.info("模板创建成功");
        if (list != null) {

            // 创建数据
            for (E e : list) {
                int colunm = 0;
                Row row = this.addRow();
                try {
                    createData(e, type, row, colunm);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            log.info("数据写入成功");
        }
        return this;
    }

    /**
     * 设置值
     *
     * @param e      从哪个对象中获取值
     * @param type   对象类型
     * @param row
     * @param colunm
     * @param <E>
     * @throws Exception
     */
    public <E> void createData(E e, ExcelField.Type type, Row row, int colunm) throws Exception {
        // 获取类信息
        List<Object[]> objs = ExcelReflectionUtil.getClassInfo(e.getClass(), type);
        //Object[] -->  第一个为Field，第二个为ExcelField注解，第三个为ExcelImportCheck注解，第4个为@ExcelFields
        if (objs != null) {
            int num = 0;
            for (int i = colunm; i < colunm + objs.size(); i++) {
                // 如果是对象
                Object[] obj = objs.get(num);
                num++;
                // 1 获取对象中的值
                Object val = null;
                Field field = null;
                if (obj[0] instanceof Field) {
                    field = (Field) obj[0];
                    field.setAccessible(true);
                    val = field.get(e);
                    field.setAccessible(false);
                }
                ExcelField ef = (ExcelField) obj[1];
                // 如果是普通的值
                if (ef != null) {
                    if (StringUtils.isNotBlank(ef.dictType())) {
                        // 翻译成中文
                        String temp = ExcelDictUtil.getDescription(ef.dictType(), (String) val);
                        if (!StringUtils.isBlank(temp)) {
                            val = temp;
                        } else {
                            log.info("未找到相应字典{}，值{}", ef.dictType(), val);
                        }
                    }
                    String dataFormat = ef.dataFormat();
                    this.addCell(row, i, val, ef.align(), dataFormat);
                }

                // 如果是对象 递归添加
                ExcelFields excelFields = (ExcelFields) obj[3];
                // 需要根据val 继续获取值
                if (excelFields != null) {
                    createData(val, type, row, i);
                }
            }
        }
    }

    /**
     * 创建模板, 主要如果属性为字典的时候导出模板时需要设置下拉菜单
     */
    public void createTemplate(Class cls, Sheet sheet, ExcelField.Type type) {
        List<Object[]> objects = ExcelReflectionUtil.getAllFieldInfo(cls, type);
        if (objects != null) {
            // Object[]  1->String标题名称,2->Integer 标题宽度,3->ExcelField,4->ExcelImportCheck,5->Field
            for (int i = 0; i < objects.size(); i++) {
                // 如果有字典类型 需要创建下拉列表
                Object[] obj = objects.get(i);
                ExcelField excelField = (ExcelField) obj[2];
                if (!StringUtils.isBlank(excelField.dictType()) || StringUtils.isNotBlank(excelField.dictSql()) || (excelField.customDictValue().length > 0)) {
                    // 设置下拉列表
                    ExcelCheckUtil.setDataValidationCheck(sheet, 0, 0, rownum.get(), i,
                            null, null, excelField.dictType(), "",excelField);
                    continue;
                }
                Field field = (Field) obj[4];
                // 设置Excel 数据有效性校验
                ExcelImportCheck excelImportCheck = (ExcelImportCheck) obj[3];
                if (excelImportCheck != null) {
                    ExcelCheckUtil.setCellValidation(excelImportCheck, field, sheet, rownum, i);
                }
                // 设置单元格格式
                String dataFormat = excelField.dataFormat();
                if (!StringUtils.isBlank(dataFormat)) {
                    ExcelCheckUtil.getCellStyle(this.wb, this.sheet, dataFormat, i);
                }
            }
        }

    }

    /**
     * 获取当前行数
     *
     * @return
     */
    public AtomicInteger getRownum() {
        return rownum;
    }


    /**
     * 输出数据流
     *
     * @param os 输出数据流
     */
    public void write(OutputStream os) {
        try {
            wb.write(os);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * 输出到文件
     */
    public ExcelExport writeFile(String name) throws IOException {
        FileOutputStream os = new FileOutputStream(name);
        this.write(os);
        return this;
    }

    /**
     * 获取工作薄对象
     * @return 工作薄对象
     */
    public Workbook getWorkBook() {
        return wb;
    }

}
