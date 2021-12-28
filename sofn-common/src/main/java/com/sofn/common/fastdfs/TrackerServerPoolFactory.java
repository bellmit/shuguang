/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.common.fastdfs;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TrackServer连接池工厂
 *
 * @author lijiang
 * @create 2019-03-29 17:53
 **/
public class TrackerServerPoolFactory implements PooledObjectFactory<TrackerServer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerServerPoolFactory.class);

    /**
     * Creates an instance that can be served by the pool and wrap it in a
     * {@link PooledObject} to be managed by the pool.
     *
     * @return a {@code PooledObject} wrapping an instance that can be served by the pool
     * @throws Exception if there is a problem creating a new instance,
     * this will be propagated to the code requesting an object.
     */
    @Override
    public PooledObject<TrackerServer> makeObject() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        if (!ProtoCommon.activeTest(trackerServer.getSocket())) {
            throw new IOException("网络连接错误或者文件服务器不可用！");
        }

        DefaultPooledObject<TrackerServer> pooledObject = new DefaultPooledObject<>(trackerServer);
        return pooledObject;
    }

    /**
     * Destroys an instance no longer needed by the pool.
     * <p>
     * It is important for implementations of this method to be aware that there
     * is no guarantee about what state <code>obj</code> will be in and the
     * implementation should be prepared to handle unexpected errors.
     * </p>
     * <p>
     * Also, an implementation must take in to consideration that instances lost
     * to the garbage collector may never be destroyed.
     * </p>
     *
     * @param p a {@code PooledObject} wrapping the instance to be destroyed
     * @throws Exception should be avoided as it may be swallowed by
     * the pool implementation.
     * @see #validateObject
     * @see ObjectPool#invalidateObject
     */
    @Override
    public void destroyObject(PooledObject<TrackerServer> p) throws Exception {
        TrackerServer trackerServer = p.getObject();
        if (trackerServer != null) {
            trackerServer.close();
        }
    }

    /**
     * Ensures that the instance is safe to be returned by the pool.
     *
     * @param p a {@code PooledObject} wrapping the instance to be validated
     * @return <code>false</code> if <code>obj</code> is not valid and should
     * be dropped from the pool, <code>true</code> otherwise.
     */
    @Override
    public boolean validateObject(PooledObject<TrackerServer> p) {
        boolean validateResult = false;
        try {
            validateResult = ProtoCommon.activeTest(p.getObject().getSocket());
        } catch (IOException e) {
            LOGGER.error("当前TrackerServer对象不可用，已从连接池剔除", e);
            e.printStackTrace();
        } finally {
            return validateResult;
        }
    }

    /**
     * Reinitializes an instance to be returned by the pool.
     *
     * @param p a {@code PooledObject} wrapping the instance to be activated
     * @throws Exception if there is a problem activating <code>obj</code>,
     * this exception may be swallowed by the pool.
     * @see #destroyObject
     */
    @Override
    public void activateObject(PooledObject<TrackerServer> p) throws Exception {
//        LOGGER.debug("从连接池取出一个TrackerServer -> {}", p);
    }

    /**
     * Uninitializes an instance to be returned to the idle object pool.
     *
     * @param p a {@code PooledObject} wrapping the instance to be passivated
     * @throws Exception if there is a problem passivating <code>obj</code>,
     * this exception may be swallowed by the pool.
     * @see #destroyObject
     */
    @Override
    public void passivateObject(PooledObject<TrackerServer> p) throws Exception {
//        LOGGER.debug("返还TrackerServer到连接池 -> {}", p);
    }
}
