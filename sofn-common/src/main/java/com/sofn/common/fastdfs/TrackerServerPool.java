/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.common.fastdfs;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.fastdfs.TrackerServer;

/**
 * TrackerServer连接池
 *
 * @author lijiang
 * @create 2019-04-01 15:17
 **/
public class TrackerServerPool extends GenericObjectPool<TrackerServer> {
    /**
     * Creates a new <code>GenericObjectPool</code> using defaults from
     * {@link GenericObjectPoolConfig}.
     *
     * @param factory The object factory to be used to create object instances
     * used by this pool
     */
    public TrackerServerPool(PooledObjectFactory<TrackerServer> factory) {
        super(factory);
    }

    /**
     * Creates a new <code>GenericObjectPool</code> using a specific
     * configuration.
     *
     * @param factory The object factory to be used to create object instances
     * used by this pool
     * @param config The configuration to use for this pool instance. The
     * configuration is used by value. Subsequent changes to
     * the configuration object will not be reflected in the
     */
    public TrackerServerPool(PooledObjectFactory<TrackerServer> factory, GenericObjectPoolConfig<TrackerServer> config) {
        super(factory, config);
    }

    /**
     * Creates a new <code>GenericObjectPool</code> that tracks and destroys
     * objects that are checked out, but never returned to the pool.
     *
     * @param factory The object factory to be used to create object instances
     * used by this pool
     * @param config The base pool configuration to use for this pool instance.
     * The configuration is used by value. Subsequent changes to
     * the configuration object will not be reflected in the
     * pool.
     * @param abandonedConfig Configuration for abandoned object identification
     */
    public TrackerServerPool(PooledObjectFactory<TrackerServer> factory, GenericObjectPoolConfig<TrackerServer> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
