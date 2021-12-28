package com.sofn.core.support.cache.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzg on 2016/10/26.
 */
@Repository
public class CountRedisCache extends AbstractRedisCache {


    public Object get(final Object key) {
        return execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.toString().getBytes());
            }
        });
    }

    public void set(final Object key, final Object res) {
        execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key.toString().getBytes(),res.toString().getBytes());
                return null;
            }
        });
    }

    public void incrby(final String key, final long i) {
        execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.incrBy(key.getBytes(),i);
                return null;
            }
        });
    }

    public void decrby(final String key, final int i) {
        execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.decrBy(key.getBytes(),i);
                return null;
            }
        });
    }
}
