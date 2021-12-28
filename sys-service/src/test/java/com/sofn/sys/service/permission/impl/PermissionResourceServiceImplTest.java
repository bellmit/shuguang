package com.sofn.sys.service.permission.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.service.permission.PermissionResourceService;
import com.sofn.sys.vo.SysResourceTreeVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2019/12/19 18:37
 */
public class PermissionResourceServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private PermissionResourceService permissionResourceService;

    @Test
    public void getUserHasResourceTree() {
        SysResourceTreeVo userHasResourceTree = permissionResourceService.getUserHasResourceTree("","");
        System.out.println(userHasResourceTree);
    }
}