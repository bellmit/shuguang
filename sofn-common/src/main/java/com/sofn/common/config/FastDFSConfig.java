/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.common.config;

import com.sofn.common.fastdfs.FastDFSProperties;
import com.sofn.common.fastdfs.TrackerServerPool;
import com.sofn.common.fastdfs.TrackerServerPoolFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerServer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.Properties;

/**
 * FastDFS文件服务器配置
 *
 * @author lijiang
 * @create 2019-04-01 11:25
 **/
@Configuration
public class FastDFSConfig {

    @Validated
    @Bean(name = "fastdfsProperties")
    @ConfigurationProperties(prefix = "fastdfs")
    public FastDFSProperties getFastdfsProperties() {
        return new FastDFSProperties();
    }

    @Bean(name = "trackerServerPool")
    public TrackerServerPool getTrackerServerPool(FastDFSProperties fastdfsProperties) throws Exception {
        Properties properties = convert(fastdfsProperties);
        try {
            ClientGlobal.initByProperties(properties);
        } catch (IOException | MyException e) {
            throw new Exception("初始化fastdfs客户端配置时发生异常", e);
        }
        TrackerServerPoolFactory trackerServerPoolFactory = new TrackerServerPoolFactory();
        GenericObjectPoolConfig<TrackerServer> config = new GenericObjectPoolConfig<>();
        config.setJmxEnabled(false);
        config.setTestWhileIdle(true);
        config.setMinIdle(1);
        config.setMaxIdle(8);
        config.setMaxTotal(50);
        config.setMaxWaitMillis(10000);
        config.setMinEvictableIdleTimeMillis(60000);
        config.setTimeBetweenEvictionRunsMillis(30000);
        config.setNumTestsPerEvictionRun(-1);
        TrackerServerPool trackerServerPool = new TrackerServerPool(trackerServerPoolFactory, config);

        return trackerServerPool;
    }

    private Properties convert(FastDFSProperties fastdfsProperties) {
        Properties properties = new Properties();
        properties.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, fastdfsProperties.getHttpSecretKey());
        properties.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, fastdfsProperties.getTrackerServers());
        properties.put(ClientGlobal.PROP_KEY_CHARSET, fastdfsProperties.getCharset());
        properties.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, fastdfsProperties.getConnectTimeoutInSeconds());
        properties.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, fastdfsProperties.getHttpAntiStealToken());
        properties.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, fastdfsProperties.getHttpTrackerHttpPort());
        properties.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, fastdfsProperties.getHttpTrackerHttpPort());

        return properties;
    }
}
