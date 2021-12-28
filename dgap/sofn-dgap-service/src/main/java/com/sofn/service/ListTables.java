package com.sofn.service;

import com.sofn.model.dgap.Column;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.Tables;
import java.sql.*;
import java.util.*;

/** Created by Thai on 2016/9/29. */
public class ListTables {
  private static PreparedStatement ps;

  public ListTables() throws SQLException {}



  // 数据库连接信息
  static class Ora {
    static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sofn";
    static final String DATABASE_USER = "root";
    static final String DATABASE_PASSWORD = "2016";
  }

  public static String selecetALL(String tableName) {
    String sql = "select * from " + tableName;
    return sql;
  }

  // 获取数据库连接
  public static Connection getOracleConnection() {
    Connection conn = null;
    Properties props = new Properties();
    props.put("user", ListTables.Ora.DATABASE_USER);
    props.put("password", ListTables.Ora.DATABASE_PASSWORD);
    props.put("remarksReporting", "true");
    try {
      Class.forName(ListTables.Ora.DRIVER_CLASS);
      conn = DriverManager.getConnection(ListTables.Ora.DATABASE_URL, props);
      return conn;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return conn;
  }

  // 获取用户下的所有信息
  public static Map<String, Object> getAllTables(
      Connection conn, String schemaName, String[] tableType) throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    ResultSet rs = metaData.getTables(null, schemaName, "%", tableType);
    Map<String, Object> tableInfo = new HashMap<>();

    while (rs.next()) {
      String tabName = rs.getString("TABLE_NAME");
      Tables tables = new Tables();
      tables.setTableName(rs.getString("TABLE_NAME"));
      tables.setTableType(rs.getString("TABLE_TYPE"));
      //            tables.setTypeName(rs.getString("TYPE_NAME"));
      //            tables.setTypeSchem(rs.getString("TYPE_SCHEM"));
      //            tables.setRefGenaration(rs.getString("REF_GENERATION"));
      tables.setRemarks(rs.getString("REMARKS"));
      tableInfo.put(tabName, tables);
    }

    return tableInfo;
  }

  // 获取某个表的字段信息
  public static Map<String, Object> getAllColumns(
      Connection conn, String schemaName, String tableName) throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    Map<String, Object> columnInfo = new HashMap<>();
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
      //                column.setGeneratedColumn(rs.getString("IS_GENERATEDCOLUMN"));
      //        column.setAutoIncrement(rs.getString("IS_AUTOINCREMENT"));
      column.setColumnRemarks(rs.getString("REMARKS"));
      columnInfo.put(colName, column);
    }
    return columnInfo;
  }

  // 获取所有表的字段信息
  public static Map<String, Object> getTables(
      Connection conn, String schemaName, String[] tableType) throws SQLException {
    LinkedHashMap<String, Object> columnInfo = new LinkedHashMap<>();
    for (Map.Entry<String, Object> entry : getAllTables(conn, schemaName, tableType).entrySet()) {
      columnInfo.put("[" + entry.getKey() + "]", "[" + entry.getValue() + "]");
      Set<String> set = getAllColumns(conn, schemaName, entry.getKey()).keySet();
      for (String key : set) {
        columnInfo.put(
            entry.getKey() + "." + key, getAllColumns(conn, schemaName, entry.getKey()).get(key));
      }
    }
    return columnInfo;
  }

  // 得到所有字段的所有数据
  public static Map getData(Connection conn, String schemaName, String tableName)
      throws SQLException {
    List tab_fields = new ArrayList();
    List len = new ArrayList();
    List type = new ArrayList();
    List fields_comment = new ArrayList();

    //        //获取数据库的链接
    //        Connection connection = JDBCUtils.getConnection();
    // 新建一个List用来存放查出来的数据
    Map<String, List<Object>> map = new HashMap<>();
    Statement statement = null;
    ResultSet rs = null;
    // 得到所有的列
    Map<String, Object> allColumns = ListTables.getAllColumns(conn, schemaName, tableName);
    // 遍历所有的列
    for (String key : allColumns.keySet()) {
      // System.out.println(key+"="+allColumns.get(key));
      try {
        statement = conn.createStatement();
        // 查出一个字段的所有数据
        rs = statement.executeQuery("select " + key + " from " + tableName);
        while (rs.next()) {
          // 获取字段的所有信息
          ResultSetMetaData md = rs.getMetaData();
          for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (md.getColumnName(i).equals("CHINESE_NAME")) {
              fields_comment.add(rs.getObject(i));
              map.put(md.getColumnName(i), fields_comment);
            }
            if (md.getColumnName(i).equals("ENGLISH_NAME")) {
              tab_fields.add(rs.getObject(i));
              map.put(md.getColumnName(i), tab_fields);
            }
            if (md.getColumnName(i).equals("TYPE")) {
              type.add(rs.getObject(i));
              map.put(md.getColumnName(i), type);
            }
            if (md.getColumnName(i).equals("LEN")) {
              len.add(rs.getObject(i));
              map.put(md.getColumnName(i), len);
            }
          }
        }
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    return map;
  }

  /**
   * oracle建表语句
   *
   * @param tabName
   * @param tab_fields
   * @param len
   * @param type
   * @param fields_comment
   * @return
   * @throws SQLException
   */
  public static String createOracleTable(
      String tabName,
      List<String> tab_fields,
      List<String> len,
      List<String> type,
      List<String> fields_comment)
      throws SQLException {
    String sql = "create table " + tabName + "(id VARCHAR(64) not null";
    if (tab_fields != null && tab_fields.size() > 0) {
      sql += ",";
      for (int i = 0; i < fields_comment.size(); i++) {
        for (int x = 0; x < len.size(); x++) {
          if (len.get(i) == null || len.get(i).equals("")) {
            sql += tab_fields.get(i).trim() + " " + type.get(i);
          } else {
            sql +=
                "\""
                    + tab_fields.get(i).trim()
                    + "\""
                    + " "
                    + type.get(i)
                    + "("
                    + len.get(i)
                    + ") ";
          }
          if (i < tab_fields.size() - 1) {
            sql += ",";
          }
          break;
        }
      }
    }
    sql += ")";
    return sql;
  }

  public static String createDefaultTable(String tabName) throws SQLException {
    String sql =
        "CREATE TABLE "
            + tabName
            + " (ID VARCHAR2(64 CHAR),STATUS VARCHAR2(10 CHAR),OPREATE VARCHAR2(10 CHAR)," +
                "DATAID VARCHAR2(64 CHAR),TOKEN VARCHAR2(10 CHAR),DATA_IMPORT_TIME TIMESTAMP,MARK VARCHAR2(64 CHAR))";
    return sql;
  }

  public String createseqMark(String tableEnglishName) {
    String sql="CREATE SEQUENCE seq_"+tableEnglishName+ "  INCREMENT BY 1  START WITH 1  NOMAXvalue  NOCYCLE NOCACHE";
    return  sql;
  }

  public static String createDefaultWsTable(String tabName) throws SQLException {
    String sql =
            "CREATE TABLE "
                    + tabName
                    + " (ID VARCHAR2(80 CHAR))";
    return sql;
  }

  /**
   * 表注释语句
   *
   * @param tabName
   * @param table_comment
   * @return
   * @throws SQLException
   */
  public static String tableComment(String tabName, String table_comment) throws SQLException {
    String tableComment = "";
    tableComment += "comment on table " + tabName + " is " + "'" + table_comment + "'";
    return tableComment;
  }

  /**
   * 字段注释语句
   *
   * @param tabName
   * @param tab_fields
   * @param fields_comment
   * @return
   * @throws SQLException
   */
  public static List fieldComment(
      String tabName, List<String> tab_fields, List<String> fields_comment) throws SQLException {
    List<String> field = new ArrayList<String>();
    for (int x = 0; x < tab_fields.size(); x++) {
      String fieldComment =
          " comment on column "
              + tabName
              + "."
              + "\""
              + tab_fields.get(x)
              + "\""
              + " is "
              + "'"
              + fields_comment.get(x)
              + "'";
      /*  String fieldComment = " comment on column " + tabName + "." + tab_fields.get(x)  + " is " + "'" + fields_comment.get(x) + "'";*/
      field.add(fieldComment);
    }
    return field;
  }

  /**
   * 在已经生成表的基础上新增字段
   *
   * @param tabName
   * @param len
   * @param type
   * @return
   * @throws SQLException
   */
  public static List<String> addField(
      String tabName, List<String> englishName, List<String> len, List<String> type)
      throws SQLException {
    List<String> addField = new ArrayList<String>();
    for (int x = 0; x < englishName.size(); x++) {
      if (type.get(x).equals(Constants.FieldType.小数.type())) {
        String addSql =
            " alter table "
                + tabName
                + " add("
                + "\""
                + englishName.get(x)
                + "\""
                + " "
                + "number("
                + len.get(x)
                + ",4))";
        addField.add(addSql);
      } else if (type.get(x).equals(Constants.FieldType.文本.type())) {
        String addSql =
            " alter table "
                + tabName
                + " add("
                + "\""
                + englishName.get(x)
                + "\""
                + " "
                + "varchar2("
                + len.get(x)
                + " CHAR"
                + "))";
        addField.add(addSql);
      } else if (type.get(x).equals(Constants.FieldType.数值.type())) {
        String addSql =
            " alter table "
                + tabName
                + " add("
                + "\""
                + englishName.get(x)
                + "\""
                + " "
                + "number("
                + len.get(x)
                + "))";
        addField.add(addSql);
      } else if (type.get(x).equals(Constants.FieldType.时间戳.type())) {
        String addSql =
            " alter table "
                + tabName
                + " add("
                + "\""
                + englishName.get(x)
                + "\""
                + " "
                + "timestamp)";
        addField.add(addSql);
      } else if (type.get(x).equals(Constants.FieldType.时间.type())) {
        String addSql =
            " alter table " + tabName + " add(" + "\"" + englishName.get(x) + "\"" + " " + "date)";
        addField.add(addSql);
      }else if (type.get(x).equals(Constants.FieldType.二进制数据.type())) {
        String addSql =
                " alter table " + tabName + " add(" + "\"" + englishName.get(x) + "\"" + " " + "BLOB)";
        addField.add(addSql);
      }

    }
    return addField;
  }

  /**
   * 创建表（mysql）
   *
   * @param tabName
   * @param table_comment
   * @param tab_fields
   * @param len
   * @param type
   * @param fields_comment
   * @throws SQLException
   */
  public static String createMySqlTable(
      String tabName,
      String table_comment,
      List<String> tab_fields,
      List<String> len,
      List<String> type,
      List<String> fields_comment)
      throws SQLException {
    String sql = "create table " + tabName + "(id VARCHAR(64)  primary key not null";
    if (tab_fields != null && tab_fields.size() > 0) {
      sql += ",";
      for (int i = 0; i < fields_comment.size(); i++) {
        for (int x = 0; x < len.size(); x++) {
          // 添加字段
          if (len.get(i) == "") {
            sql +=
                tab_fields.get(i).trim()
                    + " "
                    + type.get(i)
                    + " COMMENT "
                    + "'"
                    + fields_comment.get(i)
                    + "'";
          } else {
            sql +=
                tab_fields.get(i).trim()
                    + " "
                    + type.get(i)
                    + "("
                    + len.get(i)
                    + ") COMMENT "
                    + "'"
                    + fields_comment.get(i)
                    + "'";
          }
          // 防止最后一个,
          if (i < tab_fields.size() - 1) {
            sql += ",";
          }
          break;
        }
      }
    }
    // 设置默认字符集,以及表的注释
    sql += ")DEFAULT CHARSET=utf8 comment " + "'" + table_comment + "'";
    System.out.println("建表语句是：" + sql);
    return sql;
  }

  public static String queryForPage(String tabName, int pageNo, int pageSize) { // 首先要获取连接，即连接到数据库
    String sql = "select * from  " + tabName + " LIMIT " + pageNo + "," + pageSize;
    System.out.println("查询sql:" + sql);
    return sql;
  }

  public static String checkStatus(String enName, String dataId) {
    String sql = "SELECT STATUS FROM " + enName + " WHERE ID='" + dataId + "'";
    return sql;
  }

  public static String checkOperate(String enName, String dataId) {
    String sql = "SELECT OPREATE FROM " + enName + " WHERE ID='" + dataId + "'";
    return sql;
  }

    /**
   * 查询表中是否有数据
   *
   * @param tabName
   * @return
   */
  public static String getCount(String tabName) {
    String countSql = "select count(*) from  " + tabName;
    return countSql;
  }

  /**
   * 删除表
   *
   * @param tabName
   */
  public static String deleteTable(String tabName) {
    String sql = "drop table  " + tabName;
    return sql;
  }

  /**
   * 删除sequence
   * @param tabName
   * @return
   */
  public static String deleteSeqMark(String tabName) {
    String sql = "drop SEQUENCE seq_"+tabName;
    return sql;
  }

  // 更新某条数据的状态字段
  public static String updateStatus(String tableName, String status, String dataId) {

    String sql =
        "update "
            + tableName
            + " set STATUS = "
            + "'"
            + status
            + "'"
            + " where id= "
            + "'"
            + dataId
            + "'";
    System.out.println(sql);
    return sql;
  }

  /**
   * 向表中添加数据
   *
   * @param tabName
   * @param fields 需要插入的字段名
   * @param data 插入的数据
   */
  public static void insert(Connection connection, String tabName, String[] fields, String[] data) {
    try {
      String sql = "insert into " + tabName + "(";
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
      // 预处理SQL 防止注入
      infusePara(connection, sql, length, data);
      // 执行
      ps.executeUpdate();
      // 关闭流
      ps.close();
      connection.close(); // 关闭数据库连接
    } catch (SQLException e) {
      System.out.println("添加数据失败" + e.getMessage());
    }
  }

  /**
   * 条件查询
   *
   * @param fields 作为条件的字段
   * @param data 条件参数
   * @param tableName 表名
   * @param tab_fields 表里面的字段
   * @return
   */
  public static String[] query(
      Connection connection,
      String[] fields,
      String[] data,
      String tableName,
      String[] tab_fields) {
    String[] result = null;
    int length = fields.length;
    String sql = null;
    try {
      if (data.length == 0) {
        sql = "select * from " + tableName;
      } else {
        sql = "select * from " + tableName + " where ";
        for (int i = 0; i < length; i++) {
          sql += fields[i] + " = ? ";
          if (i < length - 1) {
            sql += " and ";
          }
        }
      }

      sql += ";";
      System.out.println("查询sql:" + sql);
      // 预处理SQL 防止注入
      infusePara(connection, sql, length, data);
      // 查询结果集
      ResultSet rs = ps.executeQuery();
      // 存放结果集
      result = new String[tab_fields.length];
      while (rs.next()) {
        for (int i = 0; i < tab_fields.length; i++) {
          result[i] = rs.getString(tab_fields[i]);
        }
      }
      // 关闭流
      rs.close();
      ps.close();
      connection.close(); // 关闭数据库连接
    } catch (SQLException e) {
      System.out.println("查询失败" + e.getMessage());
    }
    return result;
  }

  /**
   * 注入参数
   *
   * @param sql
   * @param length
   * @param data
   * @throws SQLException
   */
  private static void infusePara(Connection connection, String sql, int length, String[] data)
      throws SQLException {
    // 预处理SQL 防止注入
    ps = connection.prepareStatement(sql);
    // 注入参数
    for (int i = 0; i < length; i++) {
      ps.setString(i + 1, data[i]);
    }
  }
}
