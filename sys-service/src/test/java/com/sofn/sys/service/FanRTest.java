package com.sofn.sys.service;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.util.FineReportUtil;
import com.sofn.sys.vo.SysReportVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;

/**
 * @author heyongjie
 * @date 2020/5/26 11:36
 */
public class FanRTest extends SysServiceApplicationTests {

    @Autowired
    private SysReportService sysReportService;

    public static void main(String[] args) {
        //数字签名有效时长
        long validTime = 30 * 60 * 1000;
        //数字签名内容，以访问资源的相对路径作为内容
        String path = "机构用户量报表.cpt";
//        String path = "decision#directory";
        String key = "123456"; //数字签名密钥，可以自定义
        //生成fine_digital_signature
        String fine_digital_signature = FineReportUtil.createJwt("", "", path, validTime, key);
        System.out.println(fine_digital_signature);
        String str = "%E6%9C%BA%E6%9E%84%E7%94%A8%E6%88%B7%E9%87%8F%E6%8A%A5%E8%A1%A8.cpt";

        String str1 = "机构用户量报表.cpt";

        String encode = URLEncoder.encode(str1);
        System.out.println(encode);


    }

    @Test
    public void testGetList() {
        PageUtils<SysReportVo> sysReportByCondition = sysReportService.getSysReportByCondition(null, 0, 10);
        System.out.println(sysReportByCondition);
    }

    @Test
    public void testAdd() {
        SysReportVo sysReportVo = new SysReportVo();
        sysReportVo.setAppId("sys");
        sysReportVo.setRemark("test");
        sysReportVo.setReportType(SysManageEnum.SYS_REPORT_TYPE_ORDINARA.getCode());
        sysReportVo.setReportName("机构用户量报表2");
        sysReportService.insertSysReport(sysReportVo);
    }

    @Test
    public void testUpdate() {
        SysReportVo sysReportVo = new SysReportVo();
        sysReportVo.setId("6d7bd3ec2d844a70b9450f9c478123d1");
        sysReportVo.setAppId("sys");
        sysReportVo.setRemark("test2");
        sysReportVo.setReportType(SysManageEnum.SYS_REPORT_TYPE_ORDINARA.getCode());
        sysReportVo.setReportName("机构用户量报表2");
        sysReportService.updateSysReport(sysReportVo);
    }

    @Test
    public void testDel() {
        sysReportService.delSysReport("6d7bd3ec2d844a70b9450f9c478123d1");

    }

    @Test
    public void testCheck() {
        FineReportUtil.checkReportIsExists("机构用户量报表", SysManageEnum.SYS_REPORT_TYPE_ORDINARA.getCode());
    }

}
