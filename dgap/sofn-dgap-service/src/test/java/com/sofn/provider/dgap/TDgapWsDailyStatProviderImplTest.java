//package com.sofn.provider.dgap;
//
//import static org.junit.Assert.*;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///** Created by helong on 17-1-13. */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:Spring-testconfig.xml")
//public class TDgapWsDailyStatProviderImplTest {
//  @Autowired private TDgapWsDailyStatProvider tDgapWsDailyStatProvider;
//
//  @Test
//  public void list() throws Exception {
//    Map<String, Object> params = new HashMap<>();
//    params.put("start", 0);
//    params.put("length", 10);
//    params.put("resourceDir", null);
//    params.put("webServiceName", null);
//    params.put("bussnessName", null);
//    params.put("callerToken", "1,2,3");
//    params.put("startTime", "2017-01-05");
//    params.put("endTime", "2017-01-05");
//    tDgapWsDailyStatProvider.list(params);
//  }
//
//  @Test
//  public void stateDailyLog() throws Exception {
//    System.out.println("******************************");
//    Date time = DateUtils.parseDate("2017/02/27 05:00:00", "yyyy/MM/dd HH:mm:ss");
//    System.out.println("time:" + time);
//    tDgapWsDailyStatProvider.stateDailyLog(time);
//    System.out.println("============================");
//  }
//
//  @Test
//  public void stateDailyLog2() throws Exception {
//    System.out.println("******************************");
//    Date time = DateUtils.parseDate("2017/04/17", "yyyy/MM/dd");
//    System.out.println("time:" + time);
//    tDgapWsDailyStatProvider.stateDailyLog(time);
//    System.out.println("============================");
//  }
//}
