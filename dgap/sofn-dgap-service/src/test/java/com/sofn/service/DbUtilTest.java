//package com.sofn.service;
//
//import com.sofn.model.dgap.Column;
//import com.sofn.model.dgap.Tables;
//import java.sql.Connection;
//import java.util.Map;
//import org.junit.Test;
//
///** Created by helong on 18-1-21. */
//public class DbUtilTest {
//  @Test
//  public void getOracleAllTables() throws Exception {
//    Connection conn =
//        DbUtils.getOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "gjzspt", "12345678");
//    Map<String, Tables> tables = DbUtils.getAllTables(conn, "GJZSPT");
//    for (Map.Entry entry : tables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getOracleTables() throws Exception {
//    Connection conn =
//        DbUtils.getOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "gjzspt", "12345678");
//    Map<String, Tables> tables = DbUtils.getTables(conn, "GJZSPT", "%T_DGAP%");
//    for (Map.Entry entry : tables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getDwTables() throws Exception {
//    Connection conn =
//        DbUtils.getOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "dgap_dw", "12345678");
//    Map<String, Tables> tables = DbUtils.getTables(conn, "DGAP_DW", "%");
//    for (Map.Entry entry : tables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getDwDruidAllTables() throws Exception {
//    Connection conn =
//        DbUtils.getDruidOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "dgap_dw", "12345678");
//    Map<String, Tables> tables = DbUtils.getTables(conn, "DGAP_DW", "%");
//    for (Map.Entry entry : tables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getDwDruidTables() throws Exception {
//    Connection conn =
//        DbUtils.getDruidOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "dgap_dw", "12345678");
//    Map<String, Tables> tables = DbUtils.getTables(conn, "DGAP_DW", "%COUNTRY%");
//    for (Map.Entry entry : tables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getOracleAllTables2() throws Exception {
//    Connection conn =
//        DbUtils.getOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "gjzspt", "12345678");
//    Map<String, Tables> tables = DbUtils.getTables(conn, "GJZSPT", "%%");
//    for (Map.Entry entry : tables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getOracleTableColumns() throws Exception {
//    Connection conn =
//        DbUtils.getOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "gjzspt", "12345678");
//    Map<String, Column> columns = DbUtils.getAllColumns(conn, "GJZSPT", "T_DGAP_RESOURCE");
//    for (Map.Entry entry : columns.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  @Test
//  public void getDruidOracleTableColumns() throws Exception {
//    Connection conn =
//        DbUtils.getDruidOracleConnection(
//            "jdbc:oracle:thin:@192.168.21.249:1521:gjzs", "gjzspt", "12345678");
//    Map<String, Column> columns = DbUtils.getAllColumns(conn, "GJZSPT", "T_DGAP_RESOURCE");
//    for (Map.Entry entry : columns.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//}
