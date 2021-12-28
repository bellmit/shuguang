package com.sofn.etlquery.utils;

import java.util.Date;

public class CharUtils {
    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //空值处理
    public static String toNullCase(String s) {
        if ("".equals(s) || s == null)
            s = "";
        return s;
    }

    //控制处理
    public static Date toDateNullCase(Date s) {
        if (s.equals("") || s == null)
            s = new Date("xxxx");
        return s;
    }

}
