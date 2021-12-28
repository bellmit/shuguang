package com.sofn.flow.util;

import java.sql.Connection;

/**
 * 将数据库连接缓存到ThreadLocal中
 * @author heyongjie
 * @date 2020/5/21 14:59
 */
public class DbConnectionUtil {

    /**
     * 存储一个请求中的数据库连接
     */
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取线程中的数据库连接
     * @return  Connection
     */
    public static Connection getConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if(connection == null){
            Connection connection1 = DbUtil.getConnection();
            CONNECTION_THREAD_LOCAL.set(connection1);
        }

        return CONNECTION_THREAD_LOCAL.get();
    }

    /**
     * 移除线程缓存中的数据库连接
     */
    public static void removeConnection() {
        CONNECTION_THREAD_LOCAL.remove();
    }


}
