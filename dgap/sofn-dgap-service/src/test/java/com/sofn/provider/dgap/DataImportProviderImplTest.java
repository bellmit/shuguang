//package com.sofn.provider.dgap;
//
//import com.sofn.model.dgap.Constants;
//import com.sofn.model.dgap.FieldData;
//import com.sofn.model.dgap.RowData;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import javax.annotation.Resource;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///** Created by weiq on 2016/12/22. */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:Spring-config.xml")
//public class DataImportProviderImplTest {
//  @Resource(name = "preJdbcTemplate")
//  private JdbcTemplate jdbcTemplate;
//
//  @Autowired private DataImportProviderImpl dataImportProviderImpl;
//
//  @Test
//  public void insertData() throws Exception {
//    RowData rowData = new RowData();
//    rowData.setId(UUID.randomUUID().toString());
//    List<FieldData> fieldsData = new ArrayList<>();
//    FieldData fieldData = new FieldData();
//    FieldData fieldData1 = new FieldData();
//    fieldData.setColumnName("USERNAME");
//    fieldData.setValue("测试2");
//    fieldData1.setColumnName("AGE");
//    fieldData1.setValue("22");
//    fieldsData.add(fieldData);
//    fieldsData.add(fieldData1);
//    rowData.setFieldsData(fieldsData);
//     Map<String,String > insertData =
//        dataImportProviderImpl.insertData(
//            "da01deff3609472ba135d28b759f0a14e8a9c46756e84591a0ba829686545279",
//                "",
//            rowData,
//            Constants.DataImportOp.添加);
//    String s=insertData.get("sql");
//    System.out.println(rowData.toString() + "***********************");
//    System.out.println("执行的SQL是：" + s.toString() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//  }
//
//  @Test
//  public void addData() throws Exception {
//    RowData rowData = new RowData();
//    rowData.setId(UUID.randomUUID().toString());
//    List<FieldData> fieldsData = new ArrayList<>();
//    FieldData fieldData = new FieldData();
//    FieldData fieldData1 = new FieldData();
//    fieldData.setColumnName("USERNAME");
//    fieldData.setValue("测试添加q");
//    fieldData1.setColumnName("AGE");
//    fieldData1.setValue("22");
//    fieldsData.add(fieldData);
//    fieldsData.add(fieldData1);
//    rowData.setFieldsData(fieldsData);
//    Map<String,String > insertData=
//        dataImportProviderImpl.insertData("da01deff3609472ba135d28b759f0a14e8a9c46756e84591a0ba829686545279",
//                "",
//            rowData,
//            Constants.DataImportOp.添加);
//    String s=insertData.get("sql");
//    System.out.println(rowData.toString() + "***********************");
//    System.out.println("执行的SQL是：" + s.toString() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//    jdbcTemplate.execute(s);
//  }
//
//  @Test
//  public void deleteData() throws Exception {
//    RowData rowData = new RowData();
//    rowData.setId(UUID.randomUUID().toString());
//    List<FieldData> fieldsData = new ArrayList<>();
//    FieldData fieldData = new FieldData();
//    FieldData fieldData1 = new FieldData();
//    fieldData.setColumnName("USERNAME");
//    fieldData.setValue("测试删除");
//    fieldData1.setColumnName("AGE");
//    fieldData1.setValue("22");
//    fieldsData.add(fieldData);
//    fieldsData.add(fieldData1);
//    rowData.setFieldsData(fieldsData);
//    Map<String,String >insertData =
//        dataImportProviderImpl.insertData(
//            "da01deff3609472ba135d28b759f0a14e8a9c46756e84591a0ba829686545279",
//                "",
//                rowData,
//            Constants.DataImportOp.删除);
//    String s=insertData.get("sql");
//    System.out.println(rowData.toString() + "***********************");
//    System.out.println("执行的SQL是：" + s.toString() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//    jdbcTemplate.execute(s);
//  }
//
//  @Test
//  public void updateData() throws Exception {
//    RowData rowData = new RowData();
//    rowData.setId(UUID.randomUUID().toString());
//    List<FieldData> fieldsData = new ArrayList<>();
//    FieldData fieldData = new FieldData();
//    FieldData fieldData1 = new FieldData();
//    fieldData.setColumnName("USERNAME");
//    fieldData.setValue("测试修改");
//    fieldData1.setColumnName("AGE");
//    fieldData1.setValue("22");
//    fieldsData.add(fieldData);
//    fieldsData.add(fieldData1);
//    rowData.setFieldsData(fieldsData);
//    Map<String,String >insertData =
//        dataImportProviderImpl.insertData("da01deff3609472ba135d28b759f0a14e8a9c46756e84591a0ba829686545279",
//                "",
//            rowData,
//            Constants.DataImportOp.修改);
//    String s=insertData.get("sql");
//    System.out.println(rowData.toString() + "***********************");
//    System.out.println("执行的SQL是：" + s.toString() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//    jdbcTemplate.execute(s);
//  }
//}
