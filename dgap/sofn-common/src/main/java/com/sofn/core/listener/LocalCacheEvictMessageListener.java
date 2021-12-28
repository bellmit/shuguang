/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.listener;

import com.github.benmanes.caffeine.cache.Cache;
import com.sofn.core.config.CustomTwoLevelCache;
import com.sofn.core.constant.RedisMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.util.SafeEncoder;

import java.util.concurrent.ConcurrentMap;

/**
 * @author lijiang
 * @desc 本地缓存移除消息监听器
 * @create 2018-11-22 15:42
 **/
public final class LocalCacheEvictMessageListener implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(LocalCacheEvictMessageListener.class);
    private final Cache<String, Object> localCache;
    private final GenericJackson2JsonRedisSerializer serializer;

    public LocalCacheEvictMessageListener(Cache<String, Object> localCache) {
        this.localCache = localCache;
        this.serializer = new GenericJackson2JsonRedisSerializer();
    }

    /**
     * Callback for processing received objects through Redis.
     *
     * @param message message
     * @param pattern pattern matching the channel (if specified) - can be null
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String messageChannel = SafeEncoder.encode(message.getChannel());
        RedisMessage redisMessage = (RedisMessage) serializer.deserialize(message.getBody());

        logger.debug("onMessage：已接收到来自频道#{}#的一级缓存删除通知消息：{}", messageChannel, redisMessage.getMessage());

        if (CustomTwoLevelCache.NODE_ID.equals(redisMessage.getNodeId())) {
            logger.debug("onMessage：因该条删除通知消息由本节点发布，所以忽略后续操作");
            return;
        }

        // 清空一级缓存
        if (CustomTwoLevelCache.CLEAR_FLAG.equals(redisMessage.getMessage())) {
            localCache.invalidateAll();
            logger.debug("onMessage - clear：已清空一级缓存");

        } else {
            // 删除指定缓存
            String storageKey = redisMessage.getMessage();
            if (storageKey.indexOf('*') > 0) {
                String keyword = storageKey.split("\\*")[0];

                ConcurrentMap<String, Object> localCacheView = localCache.asMap();

                for (String localCacheKey : localCacheView.keySet()) {
                    if (localCacheKey.indexOf(keyword) == 0) {
                        localCache.invalidate(localCacheKey);
                        logger.debug("onMessage：找到需要删除的一级缓存并删除 --> key={}", localCacheKey);
                    }
                }

            } else {
                localCache.invalidate(storageKey);
                logger.debug("onMessage - delete：已删除本节点一级缓存 --> key={}", storageKey);
            }
        }
    }
}
