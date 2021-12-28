package com.sofn.common.excel.util;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.google.common.collect.Lists;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelFields;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.common.excel.exception.ExcelException;
import com.sofn.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Excel反射相关工具
 * Created by heyongjie on 2019/6/13 13:53
 */
@Slf4j
public class ExcelReflectionUtil {

    /**
     * 获取从哪行开始读取
     *
     * @param cls Excel注解类
     * @return int  HeadNum
     */
    public static int getHeadNum(Class cls) {
        int headNum = 1;
        String title = getTableTitle(cls);
        if (StringUtils.isBlank(title)) {
            // 如果为空就从1开始
            return headNum;
        }
        // 如果标题不为空 就从2开始
        return 2;
    }

    /**
     * 返回一个类的结构信息
     *
     * @param cls 要获取的类
     * @return Object[] -->  第一个为Field，第二个为ExcelField注解，第三个为ExcelImportCheck注解，第4个为检查属性@ExcelFields
     */
    public static List<Object[]> getClassInfo(Class cls, ExcelField.Type type) {
        if (type == null) {
            type = ExcelField.Type.ALL;
        }

        Field[] fields = cls.getDeclaredFields();
        fields = Arrays.stream(fields).filter(field -> !"serialVersionUID".equals(field.getName())).toArray(Field[]::new);

        List<Object[]> classInfo = Lists.newArrayList();
        for (Field field : fields) {

            //start获取类中属性注解ExcelField上isShow为true的注解属性，不是则跳过循环 update by wuXY 2019-11-26 10:45:07
            // 注解在属性上
            ExcelField excelFieldAttr = field.getAnnotation(ExcelField.class);
            if (excelFieldAttr != null && !excelFieldAttr.isShow()) {
                continue;
            }
            //end获取类中属性注解ExcelField上isShow为true的注解属性，不是则跳过循环 update by wuXY 2019-11-26 10:45:07

            Object[] obj = new Object[4];
            // 当前类的字段
            obj[0] = field;
            // 如果是集合直接continue
            if (field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Map.class) ||
                    field.getType().isAssignableFrom(Set.class)) {
                // 当前属性是集合 不做处理
                continue;
            }
            // 注解在属性上
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField != null) {
                // 区别导入和导出
                ExcelField.Type type1 = excelField.type();
                // 当前字段的Type 既不等于传入的Type 又不等于All那就跳过
                if (type.value() != excelField.type().value() && excelField.type().value() != ExcelField.Type.ALL.value()) {
                    continue;
                }
                // 当前字段的ExcelField注解
                obj[1] = excelField;
            }
            ExcelImportCheck excelImportCheck = field.getAnnotation(ExcelImportCheck.class);
            if (excelImportCheck != null) {
                //当前字段的 ExcelImportCheck 注解
                obj[2] = excelImportCheck;
            }

            ExcelFields excelFields = field.getAnnotation(ExcelFields.class);
            if (excelFields != null) {
                obj[3] = excelFields;
            }
            classInfo.add(obj);
        }
        classInfo.sort(Comparator.comparingInt(o -> ((o[1]) == null) ? 0 : ((ExcelField) o[1]).sort()));
        return classInfo;
    }

    /**
     * 递归获取所有列信息
     *
     * @param cls 类
     * @return Object[]  1->String标题名称,2->Integer 标题宽度,3->ExcelField,4->ExcelImportCheck,5->Field
     */
    public static List<Object[]> getAllFieldInfo(Class cls, ExcelField.Type type) {
        List<Object[]> objects = Lists.newArrayList();
        // 获取类信息
        List<Object[]> classInfo = getClassInfo(cls, type);
        // Object[] -->  第一个为Field，第二个为ExcelField注解，第三个为ExcelImportCheck注解，第4个为检查属性@ExcelFields
        if (classInfo != null) {
            for (int i = 0; i < classInfo.size(); i++) {
                Object[] objs = classInfo.get(i);
                ExcelFields excelFields = (ExcelFields) objs[3];
                if (excelFields != null) {
                    // 递归查找
                    List<Object[]> sonTitleInfos = getAllFieldInfo(excelFields.clazz(), type);
                    objects.addAll(sonTitleInfos);
                } else {
                    ExcelField excelField = (ExcelField) objs[1];
                    if (excelField != null) {
                        // Object[]  1->String标题名称,2->Integer 标题宽度,3->ExcelField,4->ExcelImportCheck
                        Object[] obj = new Object[6];
                        if (excelField != null) {
                            // 列标题
                            obj[0] = excelField.title();
                            // 列宽度
                            obj[1] = excelField.width();
                            // 属性注解
                            obj[2] = excelField;
                            //合并的标题
                            obj[5] = excelField.merge();
                        }
                        //   检查注解
                        obj[3] = objs[2];
                        //   列属性
                        obj[4] = objs[0];
                        objects.add(obj);
                    }

                }
            }
        }

        // 根据Sort排序
        objects.sort(Comparator.comparingInt(o -> ((o[2]) == null) ? 0 : ((ExcelField) o[2]).sort()));
        return objects;
    }

    /**
     * 获取SheetTitle
     *
     * @param cls 类信息
     * @return SheetTitle
     */
    public static String getSheetTitle(Class cls) {
        ExcelSheetInfo excelSheetInfo = (ExcelSheetInfo) cls.getAnnotation(ExcelSheetInfo.class);
        if (excelSheetInfo != null) {
            return excelSheetInfo.sheetName();
        }
        return "Sheet1";
    }

    /**
     * 获取表格标题信息
     *
     * @param cls Excel注解类
     * @return 表格标题信息
     */
    public static String getTableTitle(Class cls) {
        ExcelSheetInfo excelSheetInfo = (ExcelSheetInfo) cls.getAnnotation(ExcelSheetInfo.class);
        if (excelSheetInfo != null) {
            return excelSheetInfo.title();
        }
        return "";
    }


    /**
     * 根据Excel一行数据设置对象值  ExcelFields.sort失效  ExcelFields.column生效
     *
     * @param row        Excel一行数据
     * @param clazz      带有Excel注解的Bean
     * @param redCellNum 从那一列开始读取 默认从第0开始读取
     * @return 根据Excel的一行数据得到的Object对象
     * @throws Exception
     */
    public static Object setValueByRowAndClass(Row row, Class clazz, int redCellNum, ExcelField.Type type) throws Exception {
        if (row == null) {
            log.info("无操作，当前行为空");
            return null;
        }
        // 获取类信息
        List<Object[]> classInfo = getClassInfo(clazz, type);
        if (classInfo != null) {
            // 实例化类
            Object obj = clazz.newInstance();
            // 列信息
            int num = 0;
            // 遍历列     从哪列开始到哪列结束
            for (int i = redCellNum; i < (redCellNum + classInfo.size()); i++) {
                // 判断是否含有ExcelFields注解，如果有就需要递归设置值并返回Object值
                // 遍历对象列  这里从0开始
                Object[] objs = classInfo.get(num);
                num++;

                Field field = (Field) objs[0];
                ExcelFields excelFields = (ExcelFields) objs[3];
                if (excelFields != null) {
                    // 子对象
                    Object tempObj = setValueByRowAndClass(row, excelFields.clazz(), i, type);
                    setValue(obj, tempObj, field);
                } else {
                    // 不含有ExcelFields就判断是否有ExcelField 注解 ，如果有就需要从中获取值设置
                    ExcelField excelField = (ExcelField) objs[1];
                    if (excelField != null) {
                        // 获取值
                        int index = (-1 != excelField.column()) ? excelField.column() : i;
                        Cell cell = row.getCell(index);
                        //解决导入excel中读取单元格，对象中定义的是String,excel单元格中是数字，会导致数字带有小数点的问题。
                        if (cell != null && String.class.equals(field.getType()) && cell.getCellTypeEnum().compareTo(CellType.NUMERIC) == 0) {
//                            cell.setCellType(CellType.STRING);
                            DataFormatter df = new DataFormatter();
                            cell.setCellValue(df.formatCellValue(cell));
                        }
                        Object val = cell;
                        // 是否含有ExcelImportCheck  有的话就需要检查值是否符合规范
                        ExcelImportCheck excelImportCheck = (ExcelImportCheck) objs[2];
                        if (excelImportCheck != null) {
                            // 检查值是否符合要求
                            try {
                                ExcelCheckUtil.checkVal(excelImportCheck, val, row.getRowNum(), excelField, field);
                            } catch (ExcelException e) {
                                throw new ExcelException(e.getMessage());
                            }
                        }
                        // 类型强制转换
                        val = getValue(val, excelField, field, row.getCell(i));
                        // 将值设置到对象中
                        setValue(obj, val, field);
                    }
                }
            }
            return obj;
        }
        log.info("无操作，类信息为空");
        return null;
    }


    /**
     * 设置对象的字段的值  通过更改字段的访问权限设置值
     *
     * @param obj   要设置值的对象
     * @param value 值
     * @param field 字段
     * @throws Exception
     */
    public static void setValue(Object obj, Object value, Field field) throws Exception {
        field.setAccessible(true);
        field.set(obj, value);
        field.setAccessible(false);
    }

    /**
     * 值强制转换    用于导入
     *
     * @param val   需要转换的值
     * @param ef    ExcelField
     * @param field 字段属性
     * @param <T>   转换后的类型
     * @return 转换后的值
     */
    public static <T> T getValue(Object val, ExcelField ef, Field field, Cell cell) {

        // 获取参数类型和类型强制转换
        Class<?> valType = Class.class;
        valType = field.getType();
        try {
            if (val != null) {
                if (valType == String.class) {
                    // 如果是String 返回原始值
                    String s = String.valueOf(val.toString());
                    // 如果字典值 翻译字典值返回
                    if (!StringUtils.isBlank(ef.dictType())) {
                        s = ExcelDictUtil.getValue(ef.dictType(), s);
                    }
                    val = s;
                } else if (Integer.class.equals(valType) || int.class.equals(valType)) {
                    val = Double.valueOf(val.toString()).intValue();
                } else if (valType == Long.class || long.class.equals(valType)) {
                    val = Double.valueOf(val.toString()).longValue();
                } else if (valType == Double.class || double.class.equals(valType)) {
                    val = Double.valueOf(val.toString());
                } else if (valType == Float.class || float.class.equals(valType)) {
                    val = Float.valueOf(val.toString());
                } else if (valType == Date.class) {
                    // 如果是时间类型
                    String dataFormat = ef.dataFormat();
                    if ("@".equals(dataFormat)) {
                        dataFormat = DateUtils.DATE_PATTERN;
                    }
                    if (val instanceof Cell) {
                        try {
                            val = ((Cell) val).getDateCellValue();
                        } catch (Exception e1) {
                            try {
                                val = DateUtils.stringToDate(((Cell) val).getStringCellValue(), dataFormat);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (val instanceof String) {
                        // 如果值是字符串类型那么需要转换一次
                        // 如果时间转换异常的话 直接抛出
                        try {
                            val = DateUtils.stringToDate((String) val, dataFormat);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new ExcelException("导入时时间转换异常，请查看时间是否正确");
                        }
                    }
                } else if (valType == BigDecimal.class) {
                    if (StringUtils.isNotBlank(val.toString())) {
                        val = new BigDecimal(val.toString());
                    } else {
                        val = new BigDecimal(0);
                    }


                } else if (valType == BigInteger.class) {
                    val = new BigInteger(val.toString());
                } else if (valType == Timestamp.class) {
                    if (val instanceof Cell) {
                        try {
                            val = new Timestamp(((Cell) val).getDateCellValue().getTime());
                        } catch (Exception e1) {
                            try {
                                val = new Timestamp(DateUtils.stringToDate(((Cell) val).getStringCellValue(), DateUtils.DATE_TIME_PATTERN).getTime());
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (val instanceof String) {
                        val = new Timestamp(DateUtils.stringToDate(val.toString(), DateUtils.DATE_TIME_PATTERN).getTime());
                    }
                } else if (valType.isEnum()) {
                    Object[] enumConstants = valType.getEnumConstants();
                    for (Object enumConstant : enumConstants) {
                        Enum<?> enumClazz = (Enum<?>) enumConstant;
                        int ordinal = enumClazz.ordinal();
                        String name = enumClazz.name();

                        if (enumConstant instanceof IEnum) {
                            IEnum<? extends Serializable> iEnum = (IEnum<? extends Serializable>) enumConstant;
                            String code = iEnum.getValue().toString();
                            String desc = iEnum.toString();

                            if (code.equals(val.toString()) || desc.equals(val.toString()) || name.equals(val.toString()) ||
                                    (NumberUtils.isCreatable(val.toString()) && ordinal == Double.valueOf(val.toString()).intValue())) {
                                val = iEnum;
                                break;
                            }
                        } else {
                            if (name.equals(val.toString()) || (NumberUtils.isCreatable(val.toString()) && ordinal == Double.valueOf(val.toString()).intValue())) {
                                val = enumClazz;
                                break;
                            }
                        }
                    }

                    // 如果excel表格中有未定义的枚举值，舍弃该值
                    if (val instanceof XSSFCell) {
                        val = null;
                    }
                } else {
                    // TODO 其他类型
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (T) val;
    }

    /**
     * 导出模板时，动态控制抬头是否显示在模板中(@ExcelField(isShow=true))
     * add by wuXY 2019-11-27 14:55:31
     *
     * @param cls               动态显示导出抬头类
     * @param strShowHeaders    类中需要显示控制的属性字符串
     * @param allDynamicHeaders 类中需要动态控制的所有属性字符串
     */
    public static void doChangeExcelFieldIsShowVaule(Class cls, String strShowHeaders, String allDynamicHeaders) throws NoSuchFieldException, IllegalAccessException {
        if (!StringUtils.isBlank(strShowHeaders)) {
            if (!strShowHeaders.startsWith(",")) {
                strShowHeaders = "," + strShowHeaders;
            }
            if (!strShowHeaders.endsWith(",")) {
                strShowHeaders = strShowHeaders + ",";
            }
            if (!allDynamicHeaders.startsWith(",")) {
                allDynamicHeaders = "," + allDynamicHeaders;
            }
            if (!allDynamicHeaders.endsWith(",")) {
                allDynamicHeaders = allDynamicHeaders + ",";
            }

            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                // 注解在属性上
                String fieldName = field.getName();
                if (allDynamicHeaders.contains("," + fieldName + ",")) {
                    ExcelField excelField = field.getAnnotation(ExcelField.class);
                    //获取 excelField 这个代理实例所持有的 InvocationHandler
                    InvocationHandler h = Proxy.getInvocationHandler(excelField);
                    // 获取 AnnotationInvocationHandler 的 memberValues 字段
                    Field hField = h.getClass().getDeclaredField("memberValues");
                    // 因为这个字段事 private final 修饰，所以要打开权限
                    hField.setAccessible(true);
                    // 获取 memberValues
                    Map memberValues = (Map) hField.get(h);
                    // 修改 value 属性值
                    memberValues.put("isShow", false);
                    if (strShowHeaders.contains("," + fieldName + ",")) {
                        memberValues.put("isShow", true);
                    }
                    System.out.println("isShow的值：" + excelField.isShow());
                }

            }
        }
    }

    private static boolean isNumber(String str) {

        String reg = "^[0-9]+(.[0-9]+)?$";

        return str.matches(reg);

    }
}
