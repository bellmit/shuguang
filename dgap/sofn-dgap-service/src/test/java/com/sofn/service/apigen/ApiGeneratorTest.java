//package com.sofn.service.apigen;
//
//import org.junit.Test;
//
//import java.io.IOException;
//
//import static org.junit.Assert.*;
//
///**
// * Created by helong on 18-5-7.
// */
//public class ApiGeneratorTest {
//    @Test
//    public void genMybatisConfig() throws Exception {
//        ApiGenerator.genMybatisConfig("jdbc:oracle:thin:@192.168.21.249:1521:gjzs","gjzspt","12345678","com.sofn.wsClient.beans","/tmp/src","DGAP_PRE",new String[]{"TA","TB"});
//    }
//
//    @Test
//    public void genApiZip() throws IOException {
//        ApiGenerator.genApiJar("jdbc:oracle:thin:@192.168.21.249:1521:gjzs","dgap_pre","12345678",
//                "com.sofn.wsClient.beans","/tmp/dgap/src","DGAP_PRE",new String[]{"ZTXX","T_DGAP_RESOURCE"},true);
//    }
//
//    @Test
//    public void genApiZipError() throws IOException {
//        ApiGenerator.genApiJar("jdbc:oracle:thin:@192.168.21.249:1521:gjzs","dgap_pre","12345678",
//                "com.sofn.wsClient.beans","/tmp/dgap/src","DGAP_PRE",new String[]{"vvvvvvvvvvvvvvvvv","T_DGAP_RESOURCE"},false);
//    }}