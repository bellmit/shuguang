///** Created by Thai on 2016/9/27. */
//
//import com.sofn.model.dgap.Column;
//import com.sofn.service.ListTables;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.*;
//import javax.annotation.Resource;
//import org.junit.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//public class TestListTables {
//  private static Connection conn = ListTables.getOracleConnection();
//  public final String schemaName = "";
//  public final String tableName = "t_table";
//  public final String tableName1 = "t_field";
//  public final String tableName2 = "t_resource";
//  public String[] tableTypes = new String[] {"TABLE", "VIEW"};
//
//  // 获取表的字段信息
//  @Test
//  public void getAllColumn() throws SQLException {
//    Column column = new Column();
//    final Map<String, Object> allTables = ListTables.getAllColumns(conn, schemaName, tableName2);
//    Set<String> set = allTables.keySet();
//    for (String key : set) {
//      column = (Column) allTables.get(key);
//      System.out.println(column.getcolumnName());
//      System.out.println(column.getColumnRemarks());
//      System.out.println(column.getColumnSize());
//      System.out.println(column.getColumnTypeName());
//    }
//  }
//
//  // 获取表的注释信息
//  @Test
//  public void getAllTable() throws SQLException {
//    Map<String, Object> allTables = ListTables.getAllTables(conn, schemaName, tableTypes);
//    for (Map.Entry<String, Object> entry : allTables.entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  // 获取所有表的信息
//  @Test
//  public void getAllInfo() throws SQLException {
//    for (Map.Entry<String, Object> entry :
//        ListTables.getTables(conn, schemaName, tableTypes).entrySet()) {
//      System.out.println(entry.getKey() + ":" + entry.getValue());
//    }
//  }
//
//  //    @Test
//  //    public void getTableInfos() throws SQLException {
//  //        com.sofn.service.ListTables.getTableInfos(conn,schemaName,new String[]{"TABLE"});
//  //        for(Map.Entry<String, LinkedHashMap<String,Object>> entry
//  // :listTables.getTableInfos(conn, schemaName,new String[]{"TABLE"}).entrySet()){
//  //            System.out.println(entry.getKey()+":"+entry.getValue());
//  //       }
//  //    }
//  // 得到所有字段的所有数据
//  @Test
//  public void getData() throws SQLException {
//    /* List list = ListTables.getData(conn, schemaName, tableName1);
//     for(int i=0;i<list.size();i++){
//        System.out.println(list.get(i));
//    }*/
//    List tab_fields = new ArrayList();
//    List len = new ArrayList();
//    List type = new ArrayList();
//    List fields_comment = new ArrayList();
//    Map map = ListTables.getData(conn, schemaName, tableName1);
//    for (Object in : map.keySet()) {
//      List list = (List) map.get(in);
//      for (int i = 0; i < list.size(); i++) {
//        if (in.equals("CHINESE_NAME")) {
//          fields_comment.add(list.get(i));
//        }
//        if (in.equals("ENGLISH_NAME")) {
//          tab_fields.add(list.get(i));
//        }
//        if (in.equals("LEN")) {
//          len.add(list.get(i));
//        }
//        if (in.equals("TYPE")) {
//          type.add(list.get(i));
//        }
//      }
//      for (int j = 0; j < tab_fields.size(); j++) {
//        System.out.println(tab_fields.get(j));
//      }
//    }
//  }
//
//  @Test
//  public void creatTable() throws SQLException {
//    List<String> tab_fields = new ArrayList<>();
//    tab_fields.add("NAME");
//    tab_fields.add("AGE");
//    List<String> arr = new ArrayList<>();
//    arr.add("名字");
//    arr.add("年龄");
//    List<String> len = new ArrayList<>();
//    len.add("5");
//    len.add("");
//    List<String> type = new ArrayList<>();
//    type.add("int");
//    type.add("date");
//    for (int i = 0; i < arr.size(); i++) {
//      System.out.println(arr.get(i));
//    }
//    /*String[] arr ={"userId","userName"};
//    String[] tab_fields ={"用户ID","用户名字"};
//    String[] len ={"5",""};
//    String[] type={"int","date"};*/
//    ListTables.createMySqlTable("t_s33333", "用户表", tab_fields, len, type, arr);
//  }
//
//  @Test
//  public void insertData() throws SQLException {
//    String[] fields = {"NAME", "AGE"};
//    String[] data = {"火影", "20"};
//    ListTables.insert(conn, "t_employee", fields, data);
//  }
//
//  @Test
//  public void query() throws SQLException {
//    String[] tab_fields = {"AGE"};
//    String[] fields = {"NAME"};
//    String[] data = {""};
//    String[] query = ListTables.query(conn, fields, data, "t_employee", tab_fields);
//    for (int i = 0; i < query.length; i++) {
//      System.out.println(query[i] + "    ");
//    }
//  }
//
//  @Resource(name = "preJdbcTemplate")
//  private JdbcTemplate jdbcTemplate;
//
//  public Connection getConnection() throws Exception {
//    Connection connection = jdbcTemplate.getDataSource().getConnection();
//    return connection;
//  }
//
//  public static void main(String[] args) {
//    System.out.println(System.currentTimeMillis());
//  }
//}
