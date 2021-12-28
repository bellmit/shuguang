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
//@ContextConfiguration("classpath:Spring-testconfig.xml")
//public class DataImportProviderImplTest2 {
//  @Resource(name = "preJdbcTemplate")
//  private JdbcTemplate jdbcTemplate;
//
//  @Autowired private DataImportProviderImpl dataImportProviderImpl;
//
//  private RowData getRowData() {
//    RowData rowData = new RowData();
//    rowData.setId(UUID.randomUUID().toString());
//
//    List<FieldData> fieldsData = new ArrayList<>();
//    FieldData fieldData = new FieldData();
//    fieldData.setColumnName("NAME");
//    fieldData.setValue("测试");
//    fieldsData.add(fieldData);
//    fieldData = new FieldData();
//    fieldData.setColumnName("DATE");
//    fieldData.setValue("11.2222");
//    fieldsData.add(fieldData);
//    fieldData = new FieldData();
//    fieldData.setColumnName("TIMES");
//    fieldData.setValue("12");
//    fieldsData.add(fieldData);
//    rowData.setFieldsData(fieldsData);
//    System.out.println(rowData.toString() + "***********************");
//    return rowData;
//  }
//
//  @Test
//  public void insertData() throws Exception {
//
//    Map<String,String > insertData =
//            dataImportProviderImpl.insertData("da01deff3609472ba135d28b759f0a14e8a9c46756e84591a0ba829686545279",
//                    "",
//                    getRowData(),
//                    Constants.DataImportOp.添加);
//    String s=insertData.get("sql");
//    System.out.println("执行的SQL是：" + s.toString() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//  }
//
//  @Test
//  public void addData() throws Exception {
//    dataImportProviderImpl.addData("",
//        "f205748642e84e0cb2e6b68626b34d0da145f656f8e84cffa01492507adb4658", getRowData());
//  }
//
//  @Test
//  public void addDataTableNotExists() throws Exception {
//    dataImportProviderImpl.addData("","xxxxxxxx", getRowData());
//  }
//}
