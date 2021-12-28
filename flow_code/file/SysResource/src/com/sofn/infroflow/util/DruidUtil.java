package com.sofn.infroflow.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DruidUtil {
  private static DataSource dataSource;
  
  static {
    try {
      String url = "jdbc:uxdb://192.168.21.62:5432/sys_dev?stringtype=unspecified";
      String driver = "com.uxsino.uxdb.Driver";
      String username = "uxdb";
      String password = "sofn@123";
      dataSource = new DataSource() {
          public Connection getConnection() throws SQLException {
            try {
              Class.forName(driver);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            return DriverManager.getConnection(url, username,password);
          }
          
          public Connection getConnection(String username, String password) throws SQLException {
            try {
              Class.forName(driver);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            return DriverManager.getConnection(url, username, password);
          }
          
          public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
          }
          
          public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
          }
          
          public PrintWriter getLogWriter() throws SQLException {
            return null;
          }
          
          public void setLogWriter(PrintWriter out) throws SQLException {}
          
          public void setLoginTimeout(int seconds) throws SQLException {}
          
          public int getLoginTimeout() throws SQLException {
            return 0;
          }
          
          public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
          }
        };
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("");
    } 
  }
  
  public static void releaseSqlConnection(ResultSet rSet, PreparedStatement pStatement, Connection connection) {
    try {
      if (rSet != null)
        rSet.close(); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pStatement != null)
          pStatement.close(); 
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          if (connection != null)
            connection.close(); 
        } catch (SQLException e) {
          e.printStackTrace();
        } 
      } 
    } 
  }
}
