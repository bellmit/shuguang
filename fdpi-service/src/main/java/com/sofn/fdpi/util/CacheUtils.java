package com.sofn.fdpi.util;

import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.RedisUtils;
import com.sofn.common.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 缓存工具类
 */
@Slf4j
public class CacheUtils {

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);
    private static RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);

    private static boolean checkRedisHelper(){
        return redisHelper != null;
    }
}
