package com.sofn.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * Created by huazi on 16-8-19.
 * 因为序列化问题
 * todo 不再使用
 */
public class SpringDataRedisUtils {

    //private static RedisTemplate<Serializable, Serializable> redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(SpringDataRedisUtils.class);

    private static RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");


    /**
     * @param keys
     * @return
     */
    public  static long del(final String... keys) {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del( ObjectUtils.serialize(keys[i]) );
                }
                return result;
            }
        });
    }


    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public static void set(final String key, final Object value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set( ObjectUtils.serialize(key), ObjectUtils.serialize(value) );
                if (liveTime > 0) {
                    connection.expire( ObjectUtils.serialize(key), liveTime );
                }
                return 1L;
            }
        });
    }

    /**
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        set(key,value,0L);
    }


    /**
     *
     * @param key
     * @return
     */
    public  static Object get(final String key) {
        return (Object) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return  ObjectUtils.unserialize( connection.get( ObjectUtils.serialize(key)) );
            }
        });
    }

    /**
     *
     * @return
     */
    public  static String flushDB() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     *
     * @return
     */
    public  static long dbSize() {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     *
     * @return
     */
    public  static String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }
}
