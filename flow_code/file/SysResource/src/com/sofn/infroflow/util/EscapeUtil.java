package com.sofn.infroflow.util;


import org.apache.commons.lang.StringUtils;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/3/11 13:44
 *@Version 1.0
 **/
public class EscapeUtil {
    //模糊查询时特殊字符转义
    public static String escapeChar(String before){
        if(StringUtils.isNotEmpty(before)){
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before ;
    }
}
