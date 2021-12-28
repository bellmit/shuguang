package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.constants.Constants;
import com.sofn.common.utils.RedisHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/1/13 10:19
 */
public class SysRoleServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private RedisHelper redisHelper;

    @Test
    public void getRoleHasPermission() {
        Map<Object, Object> hmget = redisHelper.hmget(Constants.ROLE_HAVA_PERMISSION_CACHE);
        System.out.println(hmget);
    }
}