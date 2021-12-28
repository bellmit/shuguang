package com.sofn.core.support.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 静态注入中间类
 * Created by wangzg on 2016/10/21.
 */
public class RedisCacheTransfer {
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        AbstractRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}
