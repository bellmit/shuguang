package com.sofn.sys.util;

import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.RedisUtils;
import com.sofn.common.utils.SpringContextHolder;

/**
 * 系统缓存核心
 *
 * 根据角色缓存子系统
 * 根据角色缓存权限点
 * 角色组缓存角色。
 * 每个用户有的角色，登录一次就缓存一次
 *
 * 当用户登录的时候遍历
 *
 *
 * 缓存
 *
 * @author heyongjie
 * @date 2020/7/22 9:51
 */
public class SysCoreCacheUtil {


    private final static String ROLE_SYSTEM_KEY ="ROLE_SYSTEM_KEY";


    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    private static RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);








}
