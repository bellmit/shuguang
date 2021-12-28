package com.sofn.ducss.enums;


import com.sofn.ducss.model.excelmodel.StrawUtilizeExcel;

import java.lang.reflect.Field;

/***
 * excel 导出枚举类
 * @author xl
 * @date 2021/6/21 11:44
 */
public enum MarketExcelEnum {
    CODE_1("主体编码","主体编码"),
    CODE_2("","市场主体名称"),
    CODE_3("","法人名称"),
    CODE_4("","法人电话"),
    CODE_5("","地址"),
    CODE_6("","农作物类型"),
    CODE_7("","肥料化(吨)"),
    CODE_8("","饲料化"),
    CODE_9("","燃料化"),
    CODE_10("","基料化"),
    CODE_11("","原料化"),
    CODE_12("","外县来源"),
    CODE_13("","本县来源"),
    ;
    private String code;
    private String value;

    MarketExcelEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static void main(String[] args) {
        StrawUtilizeExcel strawUtilizeExcel = new StrawUtilizeExcel();
        Class<?> aClass = StrawUtilizeExcel.class;
        Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length ; i++) {
            System.out.println(fields[i].getName());
        }
    }
}
