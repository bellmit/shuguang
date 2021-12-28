/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.sofn.core.constant.RedisMessage;
import com.sofn.core.listener.LocalCacheEvictMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.util.SafeEncoder;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.Assert.hasText;

/**
 * @author lijiang
 * @desc 自定义缓存实现，一级缓存使用Caffeine做进程内缓存，二级缓存使用Redis做进程外缓存
 * @create 2018-11-14 9:30
 **/
public final class CustomTwoLevelCache extends AbstractValueAdaptingCache {
    private final Logger logger = LoggerFactory.getLogger(CustomTwoLevelCache.class);
    private final RedisOperations redisOperations;
    private final CacheMetaData cacheMetaData;
    private final Cache<String, Object> localCache;
    private final JedisConnectionFactory connectionFactory;
    private final GenericJackson2JsonRedisSerializer serializer;
    public static final String CLEAR_FLAG = "cache#delete^_^all"; // 清空缓存的标识
    private static final long CACHE_EXPIRE_TIME = 1800; // 默认缓存过期时间（秒）
    private static final long CACHE_MAXIMUM_SIZE = 1000; // 缓存项最大条数
    public static final String NODE_ID = UUID.randomUUID().toString(); // 节点ID

    /**
     * 自定义缓存构造函数
     *
     * @param name                    缓存名称
     * @param prefix                  缓存key前缀
     * @param redisOperations         redis操作命令实现
     * @param expiration              缓存过期时间
     * @param refreshExpiration       是否刷新缓存失效时间
     * @param oneLevelCacheExpiration 一级缓存失效时间（秒）
     */
    public CustomTwoLevelCache(String name, String prefix, RedisOperations<? extends Object, ? extends Object> redisOperations,
                               long expiration, boolean refreshExpiration, long oneLevelCacheExpiration) {
        super(true);
        this.cacheMetaData = new CacheMetaData(name, prefix);
        this.redisOperations = redisOperations;
        this.cacheMetaData.setExpiration(expiration);
        this.connectionFactory = (JedisConnectionFactory) ((RedisTemplate) redisOperations).getConnectionFactory();
        this.cacheMetaData.setRefreshExpiration(refreshExpiration);
        this.serializer = new GenericJackson2JsonRedisSerializer();

        if (refreshExpiration) {
            localCache = Caffeine.newBuilder().expireAfterAccess(oneLevelCacheExpiration, TimeUnit.SECONDS).maximumSize(CACHE_MAXIMUM_SIZE).build();
        } else {
            localCache = Caffeine.newBuilder().expireAfterWrite(oneLevelCacheExpiration, TimeUnit.SECONDS).maximumSize(CACHE_MAXIMUM_SIZE).build();
        }

        subscribeLocalCacheEvictTopic();
    }

    /**
     * 自定义缓存构造函数
     *
     * @param name            缓存名称
     * @param prefix          缓存key前缀
     * @param redisOperations redis操作命令实现
     */
    public CustomTwoLevelCache(String name, String prefix, RedisOperations<? extends Object, ? extends Object> redisOperations) {
        super(true);
        this.cacheMetaData = new CacheMetaData(name, prefix);
        this.redisOperations = redisOperations;
        this.connectionFactory = (JedisConnectionFactory) ((RedisTemplate) redisOperations).getConnectionFactory();
        this.serializer = new GenericJackson2JsonRedisSerializer();

        if (this.cacheMetaData.isRefreshExpiration()) {
            localCache = Caffeine.newBuilder().expireAfterAccess(CACHE_EXPIRE_TIME, TimeUnit.SECONDS).maximumSize(CACHE_MAXIMUM_SIZE).build();
        } else {
            localCache = Caffeine.newBuilder().expireAfterWrite(CACHE_EXPIRE_TIME, TimeUnit.SECONDS).maximumSize(CACHE_MAXIMUM_SIZE).build();
        }

        subscribeLocalCacheEvictTopic();
    }

    /**
     * 订阅本地缓存移除主题
     */
    private void subscribeLocalCacheEvictTopic() {
        SubscriberStartupRunner subscriberStartupRunner = new SubscriberStartupRunner(localCache, this.getChannelName(), this.connectionFactory.getConnection());
        new Thread(subscriberStartupRunner).start();
    }

    /**
     * Perform an actual lookup in the underlying store.
     *
     * @param key the key whose associated value is to be returned
     * @return the raw store value for the key
     */
    @Override
    protected Object lookup(Object key) {
        String storageKey = this.cacheMetaData.getKeyPrefix() + key.toString();
        Object value = fromStoreValue(localCache.getIfPresent(storageKey));
        if (value == null) {

            if (this.cacheMetaData.isRefreshExpiration()) {
                redisOperations.expire(storageKey, this.cacheMetaData.getExpiration(), TimeUnit.SECONDS);
            }
            value = fromStoreValue(redisOperations.opsForValue().get(storageKey));

            if (value != null) {
                localCache.put(storageKey, toStoreValue(value));
                logger.debug("lookup：查询二级缓存--> key={}, 缓存值已存入一级缓存", storageKey);
            }
        } else {
            logger.debug("lookup：查询一级缓存--> key={}", storageKey);
        }

        return value;
    }

    /**
     * Return the cache name.
     */
    @Override
    public String getName() {
        return cacheMetaData.getCacheName();
    }

    /**
     * Return the underlying native cache provider.
     */
    @Override
    public Object getNativeCache() {
        return redisOperations;
    }

    /**
     * Return the value to which this cache maps the specified key, obtaining
     * that value from {@code valueLoader} if necessary. This method provides
     * a simple substitute for the conventional "if cached, return; otherwise
     * create, cache and return" pattern.
     * <p>If possible, implementations should ensure that the loading operation
     * is synchronized so that the specified {@code valueLoader} is only called
     * once in case of concurrent access on the same key.
     * <p>If the {@code valueLoader} throws an exception, it is wrapped in
     * a {@link ValueRetrievalException}
     *
     * @param key         the key whose associated value is to be returned
     * @param valueLoader
     * @return the value to which this cache maps the specified key
     * @throws ValueRetrievalException if the {@code valueLoader} throws an exception
     * @since 4.3
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    /**
     * Associate the specified value with the specified key in this cache.
     * <p>If the cache previously contained a mapping for this key, the old
     * value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(Object key, Object value) {
        String storageKey = this.cacheMetaData.getKeyPrefix() + key.toString();

        localCache.put(storageKey, toStoreValue(value));
        logger.debug("put：数据已存入一级缓存--> key={}", storageKey);

        redisOperations.boundValueOps(storageKey).set(toStoreValue(value), this.cacheMetaData.getExpiration(), TimeUnit.SECONDS);
        logger.debug("put：数据已存入二级缓存--> key={}", storageKey);
    }

    /**
     * Atomically associate the specified value with the specified key in this cache
     * if it is not set already.
     * <p>This is equivalent to:
     * <pre><code>
     * Object existingValue = cache.get(key);
     * if (existingValue == null) {
     *     cache.put(key, value);
     *     return null;
     * } else {
     *     return existingValue;
     * }
     * </code></pre>
     * except that the action is performed atomically. While all out-of-the-box
     * CacheManager implementations are able to perform the put atomically,
     * the operation may also be implemented in two steps, e.g. with a check for
     * presence and a subsequent put, in a non-atomic way. Check the documentation
     * of the native cache implementation that you are using for more details.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the value to which this cache maps the specified key (which may be
     * {@code null} itself), or also {@code null} if the cache did not contain any
     * mapping for that key prior to this call. Returning {@code null} is therefore
     * an indicator that the given {@code value} has been associated with the key.
     * @since 4.1
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        Object existingValue = lookup(key);
        if (existingValue == null) {
            put(key, value);
            return null;
        } else {
            return toValueWrapper(existingValue);
        }

    }

    /**
     * Evict the mapping for this key from this cache if it is present.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    @Override
    public void evict(Object key) {
        String storageKey = this.cacheMetaData.getKeyPrefix() + key.toString();

        if (storageKey.indexOf('*') > 0) {
            String keyword = storageKey.split("\\*")[0];
            redisOperations.delete(redisOperations.keys(storageKey));
            logger.debug("evict：已批量删除二级缓存--> key={}", storageKey);

            ConcurrentMap<String, Object> localCacheView = localCache.asMap();

            for (String localCacheKey : localCacheView.keySet()) {
                if (localCacheKey.indexOf(keyword) == 0) {
                    localCache.invalidate(localCacheKey);
                    logger.debug("evict：找到需要删除的一级缓存并删除 --> key={}", localCacheKey);
                }
            }
        } else {
            redisOperations.delete(storageKey);
            logger.debug("evict：已删除二级缓存--> key={}", storageKey);

            localCache.invalidate(storageKey);
            logger.debug("evict：已删除一级缓存--> key={}", storageKey);
        }
        // 通知其他节点删除指定的一级缓存
        publish(storageKey);
    }

    /**
     * Remove all mappings from the cache.
     */
    @Override
    public void clear() {
        String pattern = String.format("%s*", this.cacheMetaData.getKeyPrefix());

        redisOperations.delete(redisOperations.keys(pattern));
        logger.debug("clear：已清空二级缓存--> pattern={}", pattern);

        localCache.invalidateAll();
        logger.debug("clear：已清空一级缓存");
        // 通知其他节点清空一级缓存
        publish(CLEAR_FLAG);
    }

    /**
     * 获取订阅频道名称
     *
     * @return 频道名称
     */
    private String getChannelName() {
        return String.format("%s-%s", this.getClass().getCanonicalName(), this.getName());
    }

    /**
     * 发布消息通知 清空其他节点的一级缓存
     *
     * @param message 消息内容
     */
    private void publish(String message) {
        final RedisMessage redisMessage = new RedisMessage();
        redisMessage.setNodeId(NODE_ID);
        redisMessage.setMessage(message);
        connectionFactory.getConnection().publish(SafeEncoder.encode(this.getChannelName()), serializer.serialize(redisMessage));
    }

    /**
     * 缓存元数据
     */
    static class CacheMetaData {
        private final String cacheName; // 缓存名
        private final String keyPrefix; // 缓存key前缀
        private long expiration = 1800; // 缓存失效时间，默认1800秒
        private boolean isRefreshExpiration = false; // 是否刷新缓存失效时间，默认不刷新

        public CacheMetaData(String cacheName, String keyPrefix) {
            hasText(cacheName, "CacheName must not be null or empty!");
            this.cacheName = cacheName;
            this.keyPrefix = keyPrefix;
        }

        public long getExpiration() {
            return expiration;
        }

        public void setExpiration(long expiration) {
            this.expiration = expiration;
        }

        public String getCacheName() {
            return cacheName;
        }

        public String getKeyPrefix() {
            return keyPrefix;
        }

        public boolean isRefreshExpiration() {
            return isRefreshExpiration;
        }

        public void setRefreshExpiration(boolean refreshExpiration) {
            isRefreshExpiration = refreshExpiration;
        }
    }

    /**
     * 订阅者启动器
     */
    private class SubscriberStartupRunner implements Runnable {
        private final LocalCacheEvictMessageListener subscribeMessageListener;
        private final RedisConnection redisConnection;
        private String channel;

        public SubscriberStartupRunner(Cache<String, Object> localCache, String channel, RedisConnection subscriberConnection) {
            this.subscribeMessageListener = new LocalCacheEvictMessageListener(localCache);
            this.channel = channel;
            this.redisConnection = subscriberConnection;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            redisConnection.subscribe(subscribeMessageListener, SafeEncoder.encode(channel));
        }
    }
}
