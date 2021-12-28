package com.sofn.ducss.db;

import com.sofn.ducss.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Slf4j
public class DruidUtil {

    /**
     * 工具类，私有化无参构造函数
     */
    private DruidUtil() {
    }

    /**
     * 静态数据源变量，供全局操作且用于静态代码块加载资源。
     */
    private static DataSource dataSource;

    private static Connection connection;

    // 静态代码块，加载配置文件。
    static {
//        String env ;
//        try{
//            env = SpringContextHolder.getActiveProfile();
//        }catch (Exception e){
//            e.printStackTrace();
//            env = "dev";
//        }
//        String dbConfigFile = String.format("config/db-%s.properties",env);
//        InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(dbConfigFile);
//        Properties properties = new Properties();
//        try {
//            properties.load(inStream);
//            dataSource = DruidDataSourceFactory.createDataSource(properties);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 创建数据库连接实例
     * @return 数据库连接实例 connection
     */
    public static Connection getConnection() {
        try {
            String env ;
            try{
                env = SpringContextHolder.getActiveProfile();
            }catch (Exception e){
                e.printStackTrace();
                env = "dev";
            }
            String dbConfigFile = String.format("config/db-%s.properties",env);
            InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(dbConfigFile);
            Properties properties = new Properties();
            try {
                properties.load(inStream);
                String driver = properties.getProperty("driver-class-name");
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url,username,password);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取数据库连接异常");
    }

    /**
     * 释放数据库连接 connection 到数据库缓存池，并关闭 rSet 和 pStatement 资源
     * @param rSet 数据库处理结果集
     * @param pStatement 数据库操作语句
     * @param connection 数据库连接对象
     */
    public static void releaseSqlConnection(ResultSet rSet, PreparedStatement pStatement, Connection connection) {
        try {
            if (rSet != null) {
                rSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
