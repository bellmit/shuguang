package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.service.SysUserRolePermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2019/7/24 10:27
 */
public class SysUserRolePermissionServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysUserRolePermissionService sysUserRolePermissionService;

    @Test
    public void getUserHasPermission() {
        // 0 11
        sysUserRolePermissionService.getUserHasPermission("05","sys");
    }


}