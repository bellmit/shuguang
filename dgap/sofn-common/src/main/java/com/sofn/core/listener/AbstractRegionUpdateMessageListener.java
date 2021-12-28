/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.listener;

import com.sofn.core.constant.RegionUpdateMessage;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.web.context.ContextLoader;
import redis.clients.util.SafeEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @author lijiang
 * @desc 行政区划更新消息抽象监听器
 * @create 2019-02-26 15:26
 **/
public abstract class AbstractRegionUpdateMessageListener implements MessageListener {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractRegionUpdateMessageListener.class);
    private final GenericJackson2JsonRedisSerializer serializer;
    @Autowired
    private RedissonClient redisson;

    protected AbstractRegionUpdateMessageListener() {
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
        String channel = SafeEncoder.encode(message.getChannel());
        RegionUpdateMessage regionUpdateMessage = (RegionUpdateMessage) serializer.deserialize(message.getBody());
        logger.debug("onMessage：已接收到来自频道#{}#的行政区划更新消息：{}", channel, regionUpdateMessage);

        boolean gotLock = false;
        final String webAppRootKey = ContextLoader.getCurrentWebApplicationContext().getServletContext().getInitParameter("webAppRootKey");
        final String lockName = String.format("fairLock:%s|%s", webAppRootKey, regionUpdateMessage.getMessageId());
        RLock fairLock = redisson.getFairLock(lockName);

        try {
            // 尝试加锁
            gotLock = fairLock.tryLock(50, 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("尝试加锁时发生异常，任务未执行！", e);
        }
        // 加锁成功执行业务逻辑
        if (gotLock) {
            logger.debug("start >> 加锁成功开始执行业务逻辑......");
            executeRegionUpdate(regionUpdateMessage);
            logger.debug("业务逻辑执行完成！ << end");
        }
    }

    /**
     * 执行行政区划更新相关业务操作的抽象方法
     *
     * 其他订阅消息的系统需要实现该方法
     * @param regionUpdateMessage 接收到的行政区划更新消息
     */
    public abstract void executeRegionUpdate(RegionUpdateMessage regionUpdateMessage);
}
