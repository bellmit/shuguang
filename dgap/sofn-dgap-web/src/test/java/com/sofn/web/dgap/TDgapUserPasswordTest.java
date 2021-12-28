//package com.sofn.web.dgap;
//
//import com.sofn.core.util.SecurityUtil;
//import com.sofn.core.util.encrypt.RSAUtils;
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertNotNull;
//
///** Created by helong on 17-5-16. */
//public class TDgapUserPasswordTest {
//
//  @Test
//  public void testPassword() throws Exception {
//    // superadmin
//    printPassword("superadmin","9999");
//    printPassword("dgap_dev","dgap_dev");
//    printPassword("zhangqiping","zhangqiping");
//    printPassword("zhifatest","zhifatest");
//    // dgapdev02 @52.1
//    printPassword("dgapdev02","12345678");
//    // 部级执法机构_admin
//    printPassword("部级执法机构_admin","00000000");
//    printPassword("部级监管机构_admin","00000000");
//    // JG-460000-001
//    printPassword("JG-460000-001","JG-460000-001");
//    printPassword("JG-460100-001","JG-460100-001");
//    printPassword("JG-460108-001","JG-460108-001");
//    //// ZF-460000-001
//    printPassword("ZF-110000-007","ZF-110000-007");
//    // lizhanshan
//    printPassword("ZF-460100-001","ZF-460100-001");
//    // sumeilan
//    printPassword("ZF-460108-001","ZF-460108-001");
//
//    printPassword("sczzydj","sczzydj");
//    printPassword("scyydj","scyydj");
//    printPassword("dgap_dev1","dgap_dev1");
//    printPassword("dgap_admin9517","dgap_admin9517");
//
//
//    printPassword("gongxiangxitongjianguan","gongxiangxitongjianguan");
//    printPassword("spyblvsezhuijie","spyblvsezhuijie");
//
//    // http://www.bg.qsst.moa.gov.cn/
//    printPassword("shujuluru","shujuluru");
//    printPassword("zhuisuxitong","zhuisuxitong");
//    printPassword("lvsexitong","lvsexitong");
//    printPassword("youjixitong","youjixitong");
//    printPassword("gongxiangziyuanshenpi","gongxiangziyuanshenpi");
//    printPassword("sichuanzhuisu","sichuanzhuisu");
//
//    System.out.println("\n");
//    printPassword("","0000");
//  }
//
//  private void printPassword(String username,String password) {
//    System.out.println(
//        "password:"
//            + password
//            + ",\tencrypted:"
//            + SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password)));
//  }
//
//  @Test
//  public void testDesKey(){
//    //String desKey = RSAUtils.decryptString(RSAUtils.Module.SYS, "34e025c098e4afdc9daa74f4664d12f0308943987f75b5057f34f635494ceed1db1c77c8fcdb57657ab6eb52e7665fb18c3eb6080500f02e53fd01c5ad4ae859a0dd924c24661a22d790a557d992ff0834f0d14243685642867bac7ab855e6563d4edb1572d356e0b8821eafa27866de50f6f27f1036c5ee519847bd4a140de1");
//    //assertNotNull(desKey);
//  }
//}
