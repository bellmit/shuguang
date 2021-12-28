package com.sofn.core.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by: dong4j.
 * Date: 2016-09-28.
 * Time: 18:53.
 * Description: 从数据连接池获取连接
 */

public class DBUtil {
    private DBUtil() {
    }

    private static DBUtil single = null;
    private static DruidDataSource dataSource;

    public synchronized static DBUtil getInstance() throws SQLException {
        if (single == null) {
            single = new DBUtil();
            initPool();
        }
        return single;
    }

    private static void initPool() throws SQLException {
       // PropertiesLoader proLoader=new PropertiesLoader("dataSource.xml");
        DruidDataSource druidDataSource = SpringContextHolder.getBean("readDataSource");
        // 得到数据库连接
        Connection connection = druidDataSource.getConnection().getConnection();




        Properties p = new Properties();
        //Oracle获取注释
        p.put("remarksReporting","true");
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("dataSource.xml");
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = (DruidDataSource) BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //要考虑多线程的情况

    public static Connection getConnection() throws Exception {
        Connection connection = null;
        try {
            synchronized (dataSource) {
                connection = dataSource.getConnection();
 }
        } catch (SQLException e) {
         throw new Exception(e);
        }
        return connection;
    }
}