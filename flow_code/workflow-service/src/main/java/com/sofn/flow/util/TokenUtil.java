package com.sofn.flow.util;

/**
 * @author heyongjie
 * @date 2020/5/13 16:23
 */
public class TokenUtil {

    /**
     * HEAD请求中TOKEN的名称
     */
    public final static String TOKEN_HEADNAME = "Authorization";

    /**
     * 存储用户TOKEN
     */
    private static final ThreadLocal<String> TOKEN_THREAD_LOCAL = new ThreadLocal<String>();

    /**
     * 设置token
     * @param token  TOKEN值
     */
    public static void setToken(String token){
        TOKEN_THREAD_LOCAL.set(token);
    }

    /**
     * 获取TOKEN的值
     * @return
     */
    public static String getToken(){
        return TOKEN_THREAD_LOCAL.get();
    }

    /**
     * 从内存中移除信息
     */
    public static void remove(){
        TOKEN_THREAD_LOCAL.remove();
    }

}
