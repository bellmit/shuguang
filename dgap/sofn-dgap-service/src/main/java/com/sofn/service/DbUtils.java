package com.sofn.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.sofn.model.dgap.Column;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.Tables;
import java.sql.*;
import java.util.*;

/** Created by Thai on 2016/9/29. */
public class DbUtils {

  private static PreparedStatement ps;

  private static final String[] tableType = new String[] {"TABLE"};

  public DbUtils() throws SQLException {}

  // 数据库连接信息
  static class Ora {
    static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
  }

  // 获取数据库连接
  public static Connection getOracleConnection(String url, String username, String password) {
    Connection conn = null;
    Properties props = new Properties();
    props.put("user", username);
    props.put("password", password);
    props.put("remarksReporting", "true");
    try {
      Class.forName(DbUtils.Ora.DRIVER_CLASS);
      conn = DriverManager.getConnection(url, props);
      return conn;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return conn;
  }

  // 获取数据库连接
  public static Connection getDruidOracleConnection(String url, String username, String password) {
    Connection conn = null;
    DruidDataSource ds = new DruidDataSource();
    ds.setConnectionProperties("remarksReporting=true");
    try {
      ds.setDriverClassName(DbUtils.Ora.DRIVER_CLASS);
      ds.setUrl(url);
      ds.setUsername(username);
      ds.setPassword(password);
      conn = ds.getConnection();
      // conn = ((DruidPooledConnection)conn).getConnection();
      return conn;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  // 获取用户下的所有信息
  public static Map<String, Tables> getAllTables(Connection conn, String schemaName)
      throws SQLException {

    return getTables(conn, schemaName, "%");
  }

  public static Map<String, Tables> getTables(Connection conn, String schemaName, String pattern)
      throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    ResultSet rs = metaData.getTables(null, schemaName, pattern, tableType);
    Map<String, Tables> tableInfo = new HashMap<>();

    while (rs.next()) {
      String tabName = rs.getString("TABLE_NAME");
      Tables tables = new Tables();
      tables.setTableName(rs.getString("TABLE_NAME"));
      tables.setTableType(rs.getString("TABLE_TYPE"));
      tables.setRemarks(rs.getString("REMARKS"));
      tableInfo.put(tabName, tables);
    }

    return tableInfo;
  }

  // 获取某个表的字段信息
  public static Map<String, Column> getAllColumns(
      Connection conn, String schemaName, String tableName) throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    Map<String, Column> columnInfo = new HashMap<>();
    ResultSet rs = metaData.getColumns(null, schemaName, tableName, null);

    while (rs.next()) {
      String colName = rs.getString("COLUMN_NAME");
      Column column = new Column();
      column.setcolumnName(rs.getString("COLUMN_NAME"));
      column.setColumnType(rs.getString("DATA_TYPE"));
      column.setColumnTypeName(rs.getString("TYPE_NAME"));
      column.setColumnSize(rs.getInt("COLUMN_SIZE"));
      column.setColumnDecimalDigits(rs.getString("DECIMAL_DIGITS"));
      column.setColumnNullable(rs.getString("NULLABLE"));
      column.setColumnRemarks(rs.getString("REMARKS"));
      columnInfo.put(colName, column);
    }
    return columnInfo;
  }

  public static int fillSampleData(
          Connection conn, String schemaName, String tableName, int rowCount) throws SQLException {
      try {
        Map<String, Column> allColumns = getAllColumns(conn, schemaName, tableName);
        String[] fields = allColumns.keySet().toArray(new String[0]);
        String sql = "insert into " + tableName + "(";
        int length = fields.length;
        for (int i = 0; i < length; i++) {
          sql += fields[i];
          // 最后一个之前加,
          if (i < length - 1) {
            sql += ",";
          }
        }
        sql += ") values(";
        for (int i = 0; i < length; i++) {
          sql += "?";
          if (i < length - 1) {
            sql += ",";
          }
        }
        sql += ");";
        System.out.println("添加数据的sql:" + sql);

        for(int j = 0; j < rowCount; j++) {
          // 预处理SQL 防止注入
          ps = conn.prepareStatement(sql);
          // 注入参数
          for (int i = 0; i < length; i++) {
            Column c = allColumns.get(fields[i]);
            switch(c.columnType){
              case "12":
                ps.setString(i + 1, DataGenerator.sampleString(c.columnSize));
                break;
            }
          }
          // 执行
          ps.executeUpdate();
        }
        // 关闭流
        ps.close();
        conn.close(); // 关闭数据库连接
      } catch (SQLException e) {
        System.out.println("添加数据失败" + e.getMessage());
      }
      return 0;
    }

}
