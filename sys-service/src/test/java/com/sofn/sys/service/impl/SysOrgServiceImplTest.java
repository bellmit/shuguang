package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.vo.SysOrgVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author heyongjie
 * @date 2020/4/1 18:49
 */
public class SysOrgServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysOrgService sysOrgService;


    @Test
    public void  testgetList(){
        List<SysOrgVo> aeem = sysOrgService.getAllByAppId("aeem", "", "");
        System.out.println(aeem);
    }


    @Test
    public void testGetAppId(){
        List<String> dd9e219641b14a4899e9774cb213a6b8 = sysOrgService.getAppIdsByOrgId("dd9e219641b14a4899e9774cb213a6b8");
        System.out.println(dd9e219641b14a4899e9774cb213a6b8);
    }

}