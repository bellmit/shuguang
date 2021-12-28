package com.xxl.job.admin.core.util;

import com.xxl.job.core.enums.RegistryConfig;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 * @author heyongjie
 * @date 2019/12/6 17:30
 */
public class DateUtil {

    /**
     * 获取当前时间减去单位时间后的时间
     * @param company  单位  如 Calendar.SECOND
     * @param distance   差距
     * @return  当前时间-distance后的时间
     */
    public static Date getDateByDistance(int company,int distance){
        //获取当前时间
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //当前时间减去 RegistryConfig.DEAD_TIMEOUT秒 
        calendar.add(company, -distance);
        return calendar.getTime();
    }
}
