package com.sofn.core.authorization.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 使用Redis存储Token
 *
 * @author sofn.core
 * @date 2015/10/26.
 */
public class RedisTokenManager extends AbstractTokenManager {

    //redis采用的模式（单机：SINGLE 分片：SHARDED 集群：CLUSTER）
    private final static String REDIS_MODE = "CLUSTER";

    /**
     * Redis中Key的前缀
     */
    private static final String REDIS_KEY_PREFIX = "AUTHORIZATION_KEY_";

    /**
     * Redis中Token的前缀
     */
    private static final String REDIS_TOKEN_PREFIX = "AUTHORIZATION_TOKEN_";

    /**
     * jedisCluster客户端，集群模式
     */
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * Jedis连接池
     * 单机模式 本项目不用该方式
     */
    protected JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    protected void delSingleRelationshipByKey(String key) {
        String token = getToken(key);
        if (token != null) {
            delete(formatKey(key), formatToken(token));
        }
    }

    @Override
    public void delRelationshipByToken(String token) {
        if (singleTokenWithUser) {
            String key = getKey(token);
            delete(formatKey(key), formatToken(token));
        } else {
            delete(formatToken(token));
        }
    }

    @Override
    protected void createSingleRelationship(String key, String token) {
        String oldToken = get(formatKey(key));
        if (oldToken != null) {
            delete(formatToken(oldToken));
        }
        set(formatToken(token), key, tokenExpireSeconds);
        set(formatKey(key), token, tokenExpireSeconds);
    }

    @Override
    protected void createMultipleRelationship(String key, String token) {
        set(formatToken(token), key, tokenExpireSeconds);
    }

    @Override
    protected String getKeyByToken(String token) {
        return get(formatToken(token));
    }

    @Override
    protected void flushExpireAfterOperation(String key, String token) {
        if (singleTokenWithUser) {
            expire(formatKey(key), tokenExpireSeconds);
        }
        expire(formatToken(token), tokenExpireSeconds);
    }

    private String get(String key) {
        //单机模式
        if (REDIS_MODE.equals("SINGLE")) {
            try (Jedis jedis = jedisPool.getResource()) {
                return jedis.get(key);
            }
        }
        //集群模式
        else if (REDIS_MODE.equals("CLUSTER")) {
            return jedisCluster.get(key);
        }
        return null;
    }

    private String set(String key, String value, int expireSeconds) {
        //单机模式
        if (REDIS_MODE.equals("SINGLE")) {
            try (Jedis jedis = jedisPool.getResource()) {
                return jedis.setex(key, expireSeconds, value);
            }
        }
        //集群模式
        else if (REDIS_MODE.equals("CLUSTER")) {
            return jedisCluster.setex(key, expireSeconds, value);
        }
        return null;
    }

    private void expire(String key, int seconds) {
        //单机模式
        if (REDIS_MODE.equals("SINGLE")) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.expire(key, seconds);
            }
        }
        //集群模式
        else if (REDIS_MODE.equals("CLUSTER")) {
            jedisCluster.expire(key, seconds);
        }
    }

    private void delete(String... keys) {
        //单机模式
        if (REDIS_MODE.equals("SINGLE")) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.del(keys);
            }
        }
        //集群模式
        else if (REDIS_MODE.equals("CLUSTER")) {
            jedisCluster.del(keys);
        }
    }

    private String getToken(String key) {
        return get(formatKey(key));
    }

    private String formatKey(String key) {
        return REDIS_KEY_PREFIX.concat(key);
    }

    private String formatToken(String token) {
        return REDIS_TOKEN_PREFIX.concat(token);
    }
}
