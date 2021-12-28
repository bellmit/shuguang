package com.sofn.sys.service.impl;

import com.google.common.collect.Lists;
import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.vo.BasicUserInfo;
import com.sofn.sys.vo.SelectUserConditionVo;
import com.sofn.sys.vo.SysUserForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author heyongjie
 * @date 2019/12/3 16:09
 */
public class SysUserServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void getUserInfoByIds() {
        List<SysUserForm> userInfoByIds = sysUserService.getUserInfoByIds(Lists.newArrayList("e8c70d1c4b5c477c8e371f67bc14118a","05"));
        System.out.println(userInfoByIds);
    }

    @Test
    public void getBasicUserInfo(){
        SelectUserConditionVo selectUserConditionVo = new SelectUserConditionVo();
       /* selectUserConditionVo.setStrIds("05,02");
        selectUserConditionVo.setRoleIdStrs("developer-role-has-all-perms,4,1");
        selectUserConditionVo.setNickName("勇");*/
        PageUtils<BasicUserInfo> basicUserInfo = sysUserService.getBasicUserInfo(selectUserConditionVo, null, null);
        System.out.println(basicUserInfo);
    }
}