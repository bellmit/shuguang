package com.sofn.core.config;

import com.sofn.core.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by: dong4j.
 * Date: 2016-09-21.
 * Time: 11:11.
 * Description:
 */
public class RedisCache implements Cache {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * The Redis template.
     */
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * The Name.
     */
    private String                        name;

    /**
     * redis 存储时间
     */
    private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

    /**
     * Gets redis template.
     *
     * @return the redis template
     */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * Sets redis template.
     *
     * @param redisTemplate the redis template
     */
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        // TODO Auto-generated method stub
        return this.redisTemplate;
    }

    /**
     * cacheable 回调
     * @param key
     * @return
     */

    //public ValueWrapper get(Object key) {
    //    // TODO Auto-generated method stub
    //    System.out.println("get key");
    //    final String keyf   = key.toString();
    //    Object       object = null;
    //    object = redisTemplate.execute(new RedisCallback<Object>() {
    //        public Object doInRedis(RedisConnection connection)
    //                throws DataAccessException {
    //            byte[] key   = keyf.getBytes();
    //            byte[] value = connection.get(key);
    //            if (value == null) {
    //                return null;
    //            }
    //            return toObject(value);
    //        }
    //    });
    //    return (object != null ? new SimpleValueWrapper(object) : null);
    //}

    /**
     * 重写 cacheable 所调用的 get 方法,直接使用 redisTemplate
     * @param key
     * @return
     */
    @Override
    public ValueWrapper get(Object key) {
        logger.info("cacheable 被调用, key = {}", key);
        final String keyf   = key.toString();
        Object       object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                //byte[] key   = keyf.getBytes();
                //byte[] value = connection.get(key);
                Object object = redisTemplate.opsForValue().get(key);
                if (object == null) {
                    return null;
                }
                return object;
            }
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    /**
     * 重写 cacheput 所调用的 put 方法,直接使用 redisTemplate
     * 直接使用 redisTemplate 来存储
     * @param key   the key
     * @param value the value
     */
    @Override
    public void put(Object key, Object value) {
        logger.info("cacheput 被调用, key = {}, value = {}", key, value);
        final String keyf   = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                // 使用 redisTemplate直接操作 redis, 避免使用 byte
                redisTemplate.boundValueOps(keyf).set(value);
                // 设置存储时间
                expire(keyf, EXPIRE);
                return 1L;
            }
        });
    }

    public void putNotTimeOut(Object key,Object value){
        logger.info("cacheput 被调用, key = {}, value = {}", key, value);
        final String keyf   = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                // 使用 redisTemplate直接操作 redis, 避免使用 byte
                redisTemplate.boundValueOps(keyf).set(value);
                // 设置存储时间
                //expire(keyf, EXPIRE);
                return 1L;
            }
        });
    }

    /**
     * Expire boolean.
     * 在某段时间后失效
     * @param key     the key
     * @param seconds the seconds
     * @return the boolean
     */
    public Boolean expire(String key, int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * To byte array byte [ ].
     *
     * @param obj the obj
     * @return the byte [ ]
     */
    private byte[] toByteArray(Object obj) {
        byte[]                bytes = null;
        ByteArrayOutputStream bos   = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * To object object.
     * 反序列化
     * @param bytes the bytes
     * @return the object
     */
    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream    ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * CacheEvict 注解
     * @param key
     */
    @Override
    public void evict(Object key) {
        logger.info("CacheEvict 被调用, key = {}", key);
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        System.out.println("clear key");
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

}
