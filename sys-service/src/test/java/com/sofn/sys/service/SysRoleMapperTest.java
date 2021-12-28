package com.sofn.sys.service;

import com.google.common.collect.Lists;
import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.mapper.SysRoleMapper;
import com.sofn.sys.vo.UserHaveRoleVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author heyongjie
 * @date 2020/7/16 10:59
 */
public class SysRoleMapperTest extends SysServiceApplicationTests {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Test
    public void test(){
        List<UserHaveRoleVo> userHaveRole = sysRoleMapper.getUserHaveRole(Lists.newArrayList("05", "06"));
        System.out.println(userHaveRole);
    }

}
