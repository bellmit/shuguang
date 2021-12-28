package com.sofn.sys.service.permission.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.vo.SysRoleVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author heyongjie
 * @date 2019/12/19 15:33
 */
public class PermissionRoleServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private PermissionRoleService permissionRoleService;

    @Test
    public void getLoginUserHaveRole() {
        List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRole();
        System.out.println(loginUserHaveRole);
    }
}