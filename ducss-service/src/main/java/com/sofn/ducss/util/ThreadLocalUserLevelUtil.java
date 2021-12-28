package com.sofn.ducss.util;

/**
 * 当前用户级别
 */
public class ThreadLocalUserLevelUtil {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置姿势
     * @param level   当前用户级别
     */
    public static void setValue(String level){
        threadLocal.set(level);
    }

    /**
     * 清除值
     */
    public static void clear(){
        threadLocal.remove();
    }

    /**
     * 获取值
     * @return  获取当前用户的级别
     */
    public static String getValue(){
        return threadLocal.get();
    }

}
