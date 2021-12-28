package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.service.AppPackageManageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2019/11/19 9:22
 */
public class AppPackageManageServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private AppPackageManageService appPackageManageService;

    @Test
    public void insert() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getList() {

        PageUtils list = appPackageManageService.getList(0, 10, "", "", "");
        System.out.println(list);
    }

    @Test
    public void getOne() {
    }

    @Test
    public void getTheLatestVersion() {
    }

    @Test
    public void update() {
    }
}