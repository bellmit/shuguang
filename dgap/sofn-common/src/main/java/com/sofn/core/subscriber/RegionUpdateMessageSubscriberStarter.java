/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.subscriber;

import com.sofn.core.config.Global;
import com.sofn.core.listener.AbstractRegionUpdateMessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import redis.clients.util.SafeEncoder;

/**
 * @author lijiang
 * @desc 行政区划更新消息订阅者启动器
 * @create 2019-02-26 15:56
 **/
public class RegionUpdateMessageSubscriberStarter implements Runnable {
    private final AbstractRegionUpdateMessageListener regionUpdateMessageListener;
    private final RedisConnection redisConnection;

    public RegionUpdateMessageSubscriberStarter(AbstractRegionUpdateMessageListener regionUpdateMessageListener, RedisConnection redisConnection) {
        this.regionUpdateMessageListener = regionUpdateMessageListener;
        this.redisConnection = redisConnection;
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
        redisConnection.subscribe(regionUpdateMessageListener, SafeEncoder.encode(Global.REGION_UPDATE_CHANNEL));
    }
}
