package com.sofn.common.excel.util;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.exception.ExcelException;
import com.sofn.common.exception.SofnException;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Excel 检查工具类
 * Created by heyongjie on 2019/6/14 11:13
 */
public class ExcelCheckUtil {

    /**
     * 生成模板时，各种规则生成的行数
     */
    private static final int RULE_NUMBER = 500;


    /**
     * 设置Excel 数据有效性校验   导出模板的时候生成校验规则 当Excel中输入非法的时候会弹框提示
     * @param excelImportCheck  ExcelImportCheck 配置的校验规则
     * @param field  字段
     * @param sheet  需要设置到哪个Sheet
     * @param rownum 从哪行开始
     * @param column 规则放在哪列
     */
    public static void setCellValidation(ExcelImportCheck excelImportCheck, Field field, Sheet sheet, AtomicInteger rownum,int column){
        if(excelImportCheck != null){
            long max = excelImportCheck.max();
            long min = excelImportCheck.min();
            boolean maxFlag = (max == -1);
            boolean minFlag = (min  == -1);
            // 只要有一个设置了那么就需要设置区间限制
            boolean flag = (!maxFlag || !minFlag);
            String errMsg = excelImportCheck.errMsg();
            Class fieldType = field.getType();
            if (field.getType() == String.class && flag ) {
                // 如果是字符串类型，判断是否需要设置最大长度和最小长度
                ExcelCheckUtil.setDataValidationCheck(sheet, DataValidationConstraint.ValidationType.TEXT_LENGTH,
                        DataValidationConstraint.OperatorType.BETWEEN, rownum.get(), column, min + "", max + "", null, errMsg);
            } else if (field.getType() == Date.class && flag) {
                // 如果是时间格式
                ExcelCheckUtil.setDataValidationCheck(sheet, DataValidationConstraint.ValidationType.DATE,
                        DataValidationConstraint.OperatorType.BETWEEN, rownum.get(), column, min + "", max + "", null, errMsg);
            }else if ((Integer.class.equals(fieldType) || int.class.equals(fieldType)  ||
                    long.class.equals(fieldType) || Long.class.equals(fieldType)
                    || BigInteger.class.equals(fieldType)) && flag) {
                // 如果是Integer 类型  或者Long类型  BigInteger
                ExcelCheckUtil.setDataValidationCheck(sheet, DataValidationConstraint.ValidationType.INTEGER,
                        DataValidationConstraint.OperatorType.BETWEEN, rownum.get(), column, min + "", max + "", null, errMsg);
            } else if ((Double.class.equals(fieldType) || double.class.equals(fieldType) ||
                    Float.class.equals(fieldType) || float.class.equals(fieldType)
                    || BigDecimal.class.equals(fieldType)) && flag) {
                // 如果是Double 或者 Flout类型  或者BigDecimal 类型
                ExcelCheckUtil.setDataValidationCheck(sheet, DataValidationConstraint.ValidationType.DECIMAL,
                        DataValidationConstraint.OperatorType.BETWEEN, rownum.get(), column, min + "", max + "", null, errMsg);
            }
        }
    }

    /**
     * 检查值是否符合要求   导入的时候校验值  如果不符合规范 需要手动捕获ExcelException异常
     *
     * @param excelImportCheck ExcelImportCheck检查注解
     * @param val              待检查值
     * @param rowNum           哪行  用于提示
     * @param excelField       excelField 用于获取标题
     * @return true 符合要求，不符合直接抛出异常
     */
    public static boolean checkVal(ExcelImportCheck excelImportCheck, Object val, int rowNum, ExcelField excelField, Field field) throws ExcelException {
        try{
            if (excelImportCheck == null) {
                return true;
            }

            // 1. 检查是否可为空
            if (!excelImportCheck.isNull()) {
                if (val == null || "".equals(val.toString())){
                    throw new ExcelException("第" +(rowNum + 1) + "行属性" + excelField.title() + "不可为空");
                }
            }
            // 2.检查长度是否符合要求    针对字符串
            long max = excelImportCheck.max();
            long min = excelImportCheck.min();
            boolean maxFlag = (max == -1);
            boolean minFlag = (min  == -1);
            // 只要有一个设置了那么就需要设置区间限制
            boolean flag = (!maxFlag || !minFlag);
            Class fieldType = field.getType();
            if (String.class.equals(fieldType)) {
                // 字符串类型直接判断长度
                if(flag){
                    String temp = (val == null ? "" : val.toString());
                    if(temp.length() > max || temp.length() < min){
                        throw new ExcelException("第" +(rowNum + 1) + "行属性出现错误" + excelImportCheck.errMsg());
                    }
                }
            }

            // 3.检查数值是否符合要求
            if (Long.class.equals(fieldType) || long.class.equals(fieldType) ||         // Long 类型
                    Integer.class.equals(fieldType) || int.class.equals(fieldType) ||   // Integer 类型
                    Double.class.equals(fieldType) || double.class.equals(fieldType) || // Double 类型
                    Float.class.equals(fieldType) || float.class.equals(fieldType) ||   // Float 类型
                    BigDecimal.class.equals(fieldType) || BigInteger.class.equals(fieldType)  // Big类型
            ) {
                if(flag){
                    // 这里min可能是0 ，所以值有可能为空
                    String tempStr = "";
                    try{
                        if(val != null){
                            tempStr = val.toString();
                        }else{
                            tempStr = "";
                        }

                    }catch (ExcelException e){
                        e.printStackTrace();
                        tempStr = "";
                    }
                    BigDecimal bigDecimal  = new BigDecimal(StringUtils.isBlank(tempStr)  ? "0" : val.toString());
                    if(bigDecimal.compareTo(new BigDecimal(min)) < 0 || bigDecimal.compareTo(new BigDecimal(max)) > 0){
                        throw new ExcelException("第" +(rowNum + 1) + "行属性出现错误" + excelImportCheck.errMsg());
                    }
                }
            }
            return true;
        }catch (Exception e){
            if(e instanceof  ExcelException){
                throw new ExcelException(e.getMessage());
            }
            e.printStackTrace();
            throw new SofnException("获取Excel数据异常，请检查！");
        }
    }


    /**
     * 设置模板校验规则
     *
     * @param sheet          sheet
     * @param validationType 什么类型
     * @param operatorType   操作类型
     * @param rowIndex       开始列
     * @param colIndex       结束列
     * @param vMin           最小值 ValidationType.TEXT_LENGTH   DECIMAL  INTEGER  TIME 类型时必填
     * @param vMax           最大值 ValidationType.TEXT_LENGTH   DECIMAL  INTEGER  TIME 类型时必填
     * @param dictType       字典类型
     * @param errMsg         错误提示消息
     * @param excelField     ExcelField注解
     * @return  Sheet
     */
    public static Sheet setDataValidationCheck(Sheet sheet, int validationType, int operatorType, int rowIndex, int colIndex,
                                               String vMin, String vMax, String dictType, String errMsg,ExcelField excelField) {
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();

        DataValidationConstraint dvConstraint = null;
        if (StringUtils.isNotBlank(dictType) ||excelField != null ) {
            // 字典不为空   设置字典值
            String[] list = new String[]{};
            if(StringUtils.isNotBlank(dictType)){
                list = ExcelDictUtil.getDescriptions(dictType).toArray(list);
            }
            // 判断是否要执行Sql
            if(list.length == 0){
                if(excelField != null){
                    if(!StringUtils.isBlank(excelField.dictSql())){
                        List<String> desBySql = ExcelDictUtil.getDesBySql(excelField.dictSql(), excelField.dictSqlParam());
                        if(!CollectionUtils.isEmpty(desBySql)){
                            list = desBySql.toArray(new String[0]);
                        }
                    } else{
                        String[] strings = excelField.customDictValue();
                        if(strings.length > 0 ){
                            list = strings;
                        }
                    }
                }

            }
            if(list.length > 0){
                dvConstraint = dvHelper.createExplicitListConstraint(list);
            }
            // 是否是自定义值
        } else if (validationType == DataValidationConstraint.ValidationType.TEXT_LENGTH) {
            dvConstraint = dvHelper.createTextLengthConstraint(operatorType, vMin, vMax);
        } else if (validationType == DataValidationConstraint.ValidationType.DECIMAL) {
            dvConstraint = dvHelper.createDecimalConstraint(operatorType, vMin, vMax);
        } else if (validationType == DataValidationConstraint.ValidationType.INTEGER) {
            dvConstraint = dvHelper.createIntegerConstraint(operatorType, vMin, vMax);
        } else if (validationType == DataValidationConstraint.ValidationType.DATE) {
            dvConstraint = dvHelper.createDateConstraint(DataValidationConstraint.OperatorType.BETWEEN,
                    "date("+getDate(vMin,"1900,01,01",false)+")", "date("+
                            getDate(vMax,"9999,12,31",true)+")", "yyyy/MM/dd");
        } else if (validationType == DataValidationConstraint.ValidationType.TIME) {
            dvConstraint = dvHelper.createTimeConstraint(operatorType, vMin, vMax);
        }
        if (null == dvConstraint) {
            return null;
        }
        CellRangeAddressList addressList = new CellRangeAddressList(rowIndex, RULE_NUMBER, colIndex, colIndex);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);

        //处理Excel兼容性问题
        if (validation instanceof XSSFDataValidation) {
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            if (StringUtils.isBlank(errMsg)) {
                errMsg = "输入不合法";
            }
            validation.createErrorBox("错误提示", errMsg);
        } else {
            validation.setSuppressDropDownArrow(false);
        }
        validation.setShowPromptBox(true);
        sheet.addValidationData(validation);
        return sheet;
    }

    /**
     * 设置模板校验规则
     *
     * @param sheet          sheet
     * @param validationType 什么类型
     * @param operatorType   操作类型
     * @param rowIndex       开始列
     * @param colIndex       结束列
     * @param vMin           最小值 ValidationType.TEXT_LENGTH   DECIMAL  INTEGER  TIME 类型时必填
     * @param vMax           最大值 ValidationType.TEXT_LENGTH   DECIMAL  INTEGER  TIME 类型时必填
     * @param dictType       字典类型
     * @param errMsg         错误提示消息
     * @return  Sheet
     */
    public static Sheet setDataValidationCheck(Sheet sheet, int validationType, int operatorType, int rowIndex, int colIndex,
                                               String vMin, String vMax, String dictType, String errMsg) {
       return setDataValidationCheck(sheet, validationType, operatorType, rowIndex, colIndex, vMin, vMax, dictType, errMsg,null);
    }

    /**
     * 将传入的Max 和Min 转为符合要求的日期
     * @param date  最大时间 最小时间
     * @param defaultValue  默认时间
     * @param flag  true 大 false 小
     * @return
     */
    public static String getDate(String date,String defaultValue,boolean flag){
        // 为日期时满足格式：年月日，如：20190225;年月,如201902；年2019,其余都不满足
        String strDate = defaultValue;
        if(date != null){
            if(date.length() == 8 ){
                strDate =  date.substring(0,4) + "," + date.substring(4,6) + "," +  date.substring(6,8);
            }else if(date.length() == 6 ){
                strDate =  date.substring(0,4) + "," + date.substring(4,6) + "," + (flag ? "28" : "01");
            }else if(date.length() == 5 ){
                strDate =  date.substring(0,4) + "," + (flag ? "12" : "01" )+ "," + (flag ? "31" : "01");
        }
        }
        return strDate;
    }

    /**
     * 获得单元格格式   整列都是这个格式
     * @param workbook  Workbook
     * @param dataFormatStr  格式
     */
    public static void getCellStyle(Workbook workbook,Sheet sheet, String dataFormatStr,int colNum) {
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat(dataFormatStr));
        sheet.setDefaultColumnStyle(colNum, cellStyle);
    }

    /**
     * 给指定单元格设置格式
     * @param workbook   Workbook
     * @param cell  指定单元格
     * @param dataFormatStr    格式
     */
    public static void setCellStyle(Workbook workbook,Cell cell,String dataFormatStr){
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat(dataFormatStr));
        cell.setCellStyle(cellStyle);
    }


}
