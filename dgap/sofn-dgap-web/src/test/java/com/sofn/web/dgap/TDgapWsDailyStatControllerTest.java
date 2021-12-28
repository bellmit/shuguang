//package com.sofn.web.dgap;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextHierarchy;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
///** Created by helong on 17-5-16. */
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@ContextHierarchy({
//  // @ContextConfiguration("/Spring-config.xml"),
//  @ContextConfiguration("/test-spring-servlet.xml")
//})
//public class TDgapWsDailyStatControllerTest {
//
//  @Autowired private WebApplicationContext wac;
//
//  private MockMvc mockMvc;
//
//  @Before
//  public void setup() {
//    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//  }
//
//  @Test
//  public void list() throws Exception {
//    this.mockMvc
//        .perform(
//            get("/wsDailyStat/list")
//                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//        .andExpect(status().is(401));
//  }
//
//  @Test
//  public void list2() throws Exception {
//    this.mockMvc
//        .perform(
//            get("/wsDailyStat/list")
//                .param("token", "xxxx")
//                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//        .andExpect(status().is(401));
//  }
//}
