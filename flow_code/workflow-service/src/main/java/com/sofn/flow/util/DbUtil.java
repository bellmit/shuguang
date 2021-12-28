package com.sofn.flow.util;

import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 连接数据库
 * 因为程序只时少部分会使用直接连接数据库查询  所以这里使用简单的jdbc
 * @author heyongjie
 * @date 2020/5/18 16:18
 */
public class DbUtil {

    private final static String DRIVER = "driverClassName";

    private final static String USERNAME = "username";

    private final static String PASSWORD="password";

    private final static String URL="url";

    private final static Map<String,String> DB_INFO = new HashMap<>(4);

    private final static String SQL = "select defXPDL from wr_model where defId=? and state='0'";

    static {
        // 从XML文件中获取数据库连接
        /*Map<String, String> dbInfo1 = XmlUtil.getDbInfo();
        if(!CollectionUtils.isEmpty(dbInfo1)){
            for(String key : dbInfo1.keySet()){
                DB_INFO.put(key,dbInfo1.get(key));
            }
        }else{
            System.out.println("数据库信息未正确加载，系统推出~~~");
            System.exit(500);
        }*/
    }

    /**
     * 获取数据库连接
     * @return  Connection
     */
    public static Connection getConnection(){
        try{
            // 1.加载驱动程序
            Class.forName(DB_INFO.get(DRIVER));
            // 2.获得数据库链接
            return DriverManager.getConnection(DB_INFO.get(URL), DB_INFO.get(USERNAME), DB_INFO.get(PASSWORD));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 回滚
     * @param connection  Connection
     */
    public static void roleback(Connection connection){
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接
     * @param connection   数据库连接
     */
    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取流程定义的XML文件
     * @param defId  流程定义ID
     * @return  流程文件byte数组
     */
    public static byte[] getProcessDefinitionXml(String defId) {
        byte[] bytes = null;
        try {
            // 1.加载驱动程序
            Class.forName(DB_INFO.get(DRIVER));
            // 2.获得数据库链接
            Connection conn = DriverManager.getConnection(DB_INFO.get(URL), DB_INFO.get(USERNAME), DB_INFO.get(PASSWORD));
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            //预编译
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setString(1, defId);
            ResultSet rs = statement.executeQuery();
            // 4.处理数据库的返回结果(使用ResultSet类)
            while (rs.next()) {
                bytes = rs.getBytes("defXPDL");
            }
            // 关闭资源
            conn.close();
            rs.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
