package com.sofn.ducss.enums;

import java.util.Map;
import java.util.TreeMap;

/**
 *@Description 常量
 *@Author quzhijie
 *@Date 2020/3/20 11:29
 *@Version 1.0
 **/
public class SysConstant {

    /**
    * 机构级别
    */
    public final static Map<String,String> ORG_LEVEL = new TreeMap<>();
    public final static String MINISTRY = "ministry";
    public final static String PROVINCE = "province";
    public final static String CITY = "city";
    public final static String COUNTY = "county";
    public final static String COUNTRY = "country";

    static {
        ORG_LEVEL.put(MINISTRY, "部级");
        ORG_LEVEL.put(PROVINCE, "省级");
        ORG_LEVEL.put(CITY, "市级");
        ORG_LEVEL.put(COUNTY, "区县级");
        ORG_LEVEL.put(COUNTRY, "乡镇级");
    }

    /**
    * 根据机构级别代码获取下级级别代码
    * @param levelCode 机构级别代码
    * @return 下级机构级别代码
    */
    public static String getNextOrgLevelCode(String levelCode){
        switch (levelCode) {
          case MINISTRY:
            return PROVINCE;
          case PROVINCE:
            return CITY;
          case CITY:
            return COUNTY;
          case COUNTY:
            return COUNTRY;
          default:
            return null;
        }
    }

    /**
     * 月秒数
     */
    public static long MONTH_SECOND = 30*24*3600;

    /**
     * 周秒数
     */
    public static long WEEK_SECOND = 7*24*3600;

    /**
     * 天秒数
     */
    public static long DAY_SECOND = 24*3600;

    /**
     * 5分钟秒数
     */
    public static long FIVE_SECOND = 5*3600;

    /**
     * 默认初始密码
     */
    public final static String DEFAULT_PWD = "Abc123456";


    /**
     * 未修改初始密码状态
     */
    public final static Integer UPDATED_0 = 0;

    /**
     * 已修改初始密码状态
     */
    public final static Integer UPDATED_1 = 1;
}
