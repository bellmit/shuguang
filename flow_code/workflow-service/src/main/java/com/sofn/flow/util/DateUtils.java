package com.sofn.flow.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * @author heyongjie
 * @date 2020年5月13日11:32:27
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd HH:mm:ss
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
	    if(date == null){
	        return "";
        }
        return format(date, DATE_TIME_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            synchronized (DateUtils.class){
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                return df.format(date);
            }

        }
        return null;
    }


}
