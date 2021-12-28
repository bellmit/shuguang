package com.sofn.core.support.cache.redis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;

/**
 * JedisConnectionFactory 是线程安全的
 * spring中已经有这样的封装，在RedisTemplate中
 * Created by wangzg on 2016/10/26.
 */
public abstract class AbstractRedisCache {
    protected static JedisConnectionFactory jedisConnectionFactory;

    //封装一个执行操作的回调函数，保证每次执行完之后能够关闭jedis
    protected Object execute(RedisCallback callback) {
        RedisConnection jedis = jedisConnectionFactory.getConnection();
        try {
            return callback.doInRedis(jedis);
        } finally {
            jedis.close();
        }
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        AbstractRedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}
