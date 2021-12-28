package com.sofn.sys.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户有的子系统
 * @author heyongjie
 * @date 2020/6/9 13:50
 */
public class UserHaveAppIdUtil {

    public static ThreadLocal<List<String>> userHaveAppId = new ThreadLocal<>();


    /**
     * 设置值
     * @param appIds   当前用户有的权限
     */
    public static void setValue(List<String> appIds){
        if(!CollectionUtils.isEmpty(appIds)){
            userHaveAppId.set(appIds);
        }
    }

    /**
     * 获取值
     * @return ThreadLocal  存储的值
     */
    public static List<String> getValue(){
        return userHaveAppId.get();
    }

    /**
     * 移除值
     */
    public static void remove(){
        userHaveAppId.remove();
    }

}
