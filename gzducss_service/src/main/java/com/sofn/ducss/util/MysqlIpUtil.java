package com.sofn.ducss.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/12/3 17:49
 * @description mysql存储过程调用工具类
 */
public class MysqlIpUtil {
    /**
     * 获取数据库连接
     *
     * @return Connection对象
     */
    public Connection getConnection() {
        Connection conn = null;   //数据库连接
        try {
            Class.forName("com.uxsino.uxdb.Driver"); //加载数据库驱动，注册到驱动管理器
            /*数据库链接地址*/
            String url = "jdbc:uxdb://192.168.21.62:5432/ducss_test?stringtype=unspecified";
            String username = "uxdb";
            String password = "sofn@123";
            /*创建Connection链接*/
            conn = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;  //返回数据库连接

    }

    /**
     * 调用存储过程
     */
    public String transfer(String strawType, String year, String area_id) {
        Connection conn = getConnection();  //创建数据库连接
        try {
            //调用存储过程(先调用基础合并的9个存储过程--->再调用三张合并表的存储过程进行综合字段的赋值)
            CallableStatement callableStatement1 = conn.prepareCall("{call ducss_pro_area_canshu(?,?,?)}");
            callableStatement1.setString(1, strawType);
            callableStatement1.setString(2, year);
            callableStatement1.setString(3, area_id);
//            CallableStatement callableStatement2 = conn.prepareCall("{call ducss_pro_city_canshu(?,?,?)}");
//            callableStatement2.setString(1, strawType);
//            callableStatement2.setString(2, year);
//            callableStatement2.setString(3, area_id);
//            CallableStatement callableStatement3 = conn.prepareCall("{call ducss_pro_province_canshu(?,?,?)}");
//            callableStatement3.setString(1, strawType);
//            callableStatement3.setString(2, year);
//            callableStatement3.setString(3, area_id);
            CallableStatement callableStatement2 = conn.prepareCall("{call ducss_dis_area_canshu(?,?,?)}");
            callableStatement2.setString(1, strawType);
            callableStatement2.setString(2, year);
            callableStatement2.setString(3, area_id);
//            CallableStatement callableStatement5 = conn.prepareCall("{call ducss_dis_city_canshu(?,?,?)}");
//            callableStatement5.setString(1, strawType);
//            callableStatement5.setString(2, year);
//            callableStatement5.setString(3, area_id);
//            CallableStatement callableStatement6 = conn.prepareCall("{call ducss_dis_province_canshu(?,?,?)}");
//            callableStatement6.setString(1, strawType);
//            callableStatement6.setString(2, year);
//            callableStatement6.setString(3, area_id);
            CallableStatement callableStatement3 = conn.prepareCall("{call ducss_straw_area_canshu(?,?,?)}");
            callableStatement3.setString(1, strawType);
            callableStatement3.setString(2, year);
            callableStatement3.setString(3, area_id);

            CallableStatement callableStatementArea = conn.prepareCall("{call ducss_area_zonghe2(?,?,?)}");
            callableStatementArea.setString(1, strawType);
            callableStatementArea.setString(2, year);
            callableStatementArea.setString(3, area_id);
//            CallableStatement callableStatement8 = conn.prepareCall("{call ducss_straw_city_canshu(?,?,?)}");
//            callableStatement8.setString(1, strawType);
//            callableStatement8.setString(2, year);
//            callableStatement8.setString(3, area_id);
//            CallableStatement callableStatement9 = conn.prepareCall("{call ducss_straw_provice_canshu(?,?,?)}");
//            callableStatement9.setString(1, strawType);
//            callableStatement9.setString(2, year);
//            callableStatement9.setString(3, area_id);
//            //综合
            CallableStatement callableStatement4 = conn.prepareCall("{call ducss_city_zonghe_canshu_new(?,?,?)}");
            callableStatement4.setString(1, strawType);
            callableStatement4.setString(2, year);
            callableStatement4.setString(3, area_id);
            CallableStatement callableStatement5 = conn.prepareCall("{call ducss_province_zonghe_canshu_new(?,?,?)}");
            callableStatement5.setString(1, strawType);
            callableStatement5.setString(2, year);
            callableStatement5.setString(3, area_id);
//            CallableStatement callableStatement12 = conn.prepareCall("{call ducss_province_zonghe2(?,?,?)}");
//            callableStatement12.setString(1, strawType);
//            callableStatement12.setString(2, year);
//            callableStatement12.setString(3, area_id);
            callableStatement1.execute();
            callableStatement2.execute();
            callableStatement3.execute();
            callableStatementArea.execute();
            callableStatement4.execute();
            callableStatement5.execute();
//            callableStatement7.execute();
//            callableStatement8.execute();
//            callableStatement9.execute();
//            callableStatement10.execute();
//            callableStatement11.execute();
//            callableStatement12.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return "调用存储过程失败";
        }
        return "调用存储过程成功";
    }
}
