//package com.sofn.etl;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateExceptionHandler;
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import org.junit.Before;
//import org.junit.Test;
//
///** Created by helong on 17-5-19. */
//public class FtlTest {
//
//  Configuration cfg;
//
//  @Before
//  public void setUp() throws Exception {
//    cfg = new Configuration(Configuration.VERSION_2_3_23);
//    cfg.setDirectoryForTemplateLoading(
//        new File(
//            "/home/helong/he/lky/share/sjgxpt/udev/codes/dgap/sofn-dgap-etl/src/main/resources"));
//    cfg.setDefaultEncoding("UTF-8");
//    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//    cfg.setLogTemplateExceptions(false);
//  }
//
//  @Test
//  public void testTableEtl() throws IOException, TemplateException {
//    TableEtlConfig etlConfig = new TableEtlConfig();
//
//    etlConfig.setName("pre2dw");
//
//    etlConfig.getConnection().getSource().setName("dgap_pre");
//    etlConfig.getConnection().getSource().setServer("192.168.21.249");
//    etlConfig.getConnection().getSource().setPort(1521);
//    etlConfig.getConnection().getSource().setType("ORACLE");
//    etlConfig.getConnection().getSource().setUsername("dgap_pre");
//    etlConfig.getConnection().getSource().setPassword("12345678");
//
//    etlConfig.getConnection().getTarget().setName("dgap_dw");
//    etlConfig.getConnection().getTarget().setServer("192.168.21.249");
//    etlConfig.getConnection().getTarget().setPort(1521);
//    etlConfig.getConnection().getTarget().setType("ORACLE");
//    etlConfig.getConnection().getTarget().setUsername("dgap_dw");
//    etlConfig.getConnection().getTarget().setPassword("12345678");
//
//    etlConfig.getTableinput().setTablename("TableA");
//    etlConfig.getTableoutput().setTablename("TableB");
//
//    Template temp = cfg.getTemplate("etl-table.ktr.ftl.xml");
//
//    Writer out = new OutputStreamWriter(System.out);
//    temp.process(etlConfig, out);
//  }
//}
