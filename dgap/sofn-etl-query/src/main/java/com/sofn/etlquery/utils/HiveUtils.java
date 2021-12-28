package com.sofn.etlquery.utils;

import java.sql.*;

public class HiveUtils {

    private static final String hiveDriver = "org.apache.hive.jdbc.HiveDriver";
    private static final String hiveUrl = "jdbc:hive2://192.168.21.100:10000/test";
    private static final String hiveUser = "root";
    private static final String hivePassword = "1";

    private static Connection hiveConn = null;

    /**
     * 获取hive连接
     * @return
     */
    public static Connection getHiveConn(){
        try {
            if (null == hiveConn) {
                Class.forName(hiveDriver);
                hiveConn = DriverManager.getConnection(hiveUrl, hiveUser, hivePassword);
            }
        }catch (Exception e){}
        return hiveConn;
    }


    /**
     * 关闭连接
     * @param conn
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
