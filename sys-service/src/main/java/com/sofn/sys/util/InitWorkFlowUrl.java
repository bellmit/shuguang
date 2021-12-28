//package com.sofn.sys.util;
//
//
//import com.sofn.sys.mapper.WorkFlowUrlRegulatorMapper;
//import com.sofn.sys.model.WorkFlowUrlRegulator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class InitWorkFlowUrl {
//  public static ArrayList<WorkFlowUrlRegulator> workFlowUrls = new ArrayList<>();
//
//  public static String getWorkFlowUrl(String urlKey) {
//    //数据库无该key，则返回0
//    String urlValue = "0";
//    if (!workFlowUrls.isEmpty()) {
//      for (WorkFlowUrlRegulator workFlowUrl : workFlowUrls) {
//        if (workFlowUrl.getUrlKey().equals(urlKey)) {
//          urlValue = workFlowUrl.getUrlValue();
//        }
//      }
//    }
//    return urlValue;
//  }
//
//  @Autowired
//  private WorkFlowUrlRegulatorMapper workFlowUrlRegulatorMapper;
//
//  @PostConstruct
//  public void init() {
////项目启动加载url的所有数据
//    List<WorkFlowUrlRegulator> list = workFlowUrlRegulatorMapper.selectList(null);
//    list.forEach(v -> {
//      WorkFlowUrlRegulator workFlowUrlRegulator = new WorkFlowUrlRegulator();
//      workFlowUrlRegulator.setUrlKey(v.getUrlKey());
//      workFlowUrlRegulator.setUrlValue(v.getUrlValue());
//      workFlowUrls.add(workFlowUrlRegulator);
//    });
////    System.out.println("======================================================================" + workFlowUrls);
//  }
//
//}
