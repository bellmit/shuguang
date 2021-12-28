package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.service.SysOrganizationService;
import com.sofn.sys.vo.SysRegionInfoVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2019/12/17 18:35
 */
public class SysOrganizationServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Test
    public void getSysRegionInfoByOrgId() {
        SysRegionInfoVo sysRegionInfoByOrgId = sysOrganizationService.getSysRegionInfoByOrgId("d0f6889112714154995d1d4901e8413f");
        System.out.println(sysRegionInfoByOrgId);
    }
}