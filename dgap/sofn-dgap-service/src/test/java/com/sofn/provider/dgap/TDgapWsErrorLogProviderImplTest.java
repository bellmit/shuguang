//package com.sofn.provider.dgap;
//
//import static org.junit.Assert.*;
//
//import com.github.pagehelper.PageInfo;
//import com.sofn.model.dgap.Constants;
//import java.util.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///** Created by helong on 17-1-13. */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:Spring-testconfig.xml")
//public class TDgapWsErrorLogProviderImplTest {
//  @Autowired private TDgapWsErrorLogProvider tDgapWsErrorLogProvider;
//
//  @Test
//  public void list() throws Exception {
//    Map<String, Object> params = new HashMap<>();
//    // page
//    params.put(Constants.PAGE_NUM, 1);
//    params.put(Constants.PAGE_SIZE, 10);
//    // query
//    params.put("resourceDirectoryId", null);
//    params.put("webServiceName", null);
//    params.put("methodName", null);
//    // params.put("callerUser", "1,2,3,4");
//    params.put("errorType", null);
//    PageInfo<List<Map<String, Object>>> pageInfo = tDgapWsErrorLogProvider.list(params);
//    assertTrue(pageInfo.getTotal() >= 0);
//  }
//}
