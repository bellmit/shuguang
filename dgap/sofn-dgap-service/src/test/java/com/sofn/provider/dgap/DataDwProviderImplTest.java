//package com.sofn.provider.dgap;
//
//import java.util.Arrays;
//import java.util.List;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///** Created by weiq on 2016/12/22. */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:Spring-testconfig.xml")
//public class DataDwProviderImplTest {
//
//  @Autowired private TDgapDwProviderImpl dataDwProviderImpl;
//
//  @Test
//  public void getData() throws Exception {
//
//    String tableName = "s";
//    Integer start = 1;
//    Integer end = 10;
//    String[] fields = {"s"};
//    String condition = null;
//    List<String> fl = Arrays.asList(fields);
//    dataDwProviderImpl.getDatas(tableName, start, end, fl, condition);
//  }
//}
