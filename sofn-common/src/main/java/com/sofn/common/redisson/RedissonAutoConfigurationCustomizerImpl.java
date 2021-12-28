/*
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 *
 *
 */
package com.sofn.common.redisson;

import jodd.bean.BeanUtil;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Redisson自定义自动配置处理器
 * <p>
 * 解决单独的redisson配置文件中加密配置项不能解密读取的问题
 *
 * @author John
 * @version 1.0
 */
@Component
public class RedissonAutoConfigurationCustomizerImpl implements RedissonAutoConfigurationCustomizer {
    @Value(value = "${jasypt.encryptor.password}")
    private String password;

    enum ServerConfigTypeEnum {single, sentinel, cluster}

    @SneakyThrows
    @Override
    public void customize(Config config) {
        @SuppressWarnings("rawtypes") Map configMap;
        ServerConfigTypeEnum serverConfigType;

        if (config.isClusterConfig()) {
            configMap = BeanUtils.describe(config.useClusterServers());
            serverConfigType = ServerConfigTypeEnum.cluster;
        } else if (config.isSentinelConfig()) {
            configMap = BeanUtils.describe(config.useSentinelServers());
            serverConfigType = ServerConfigTypeEnum.sentinel;
        } else {
            configMap = BeanUtils.describe(config.useSingleServer());
            serverConfigType = ServerConfigTypeEnum.single;
        }

        final StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        final EnvironmentStringPBEConfig stringPBEConfig = new EnvironmentStringPBEConfig();
        stringPBEConfig.setAlgorithm("PBEWithMD5AndDES");
        stringPBEConfig.setPassword(password);
        stringEncryptor.setConfig(stringPBEConfig);

        //noinspection unchecked
        for (Map.Entry<String, String> entry : (Set<Map.Entry<String, String>>) configMap.entrySet()) {
            if (PropertyValueEncryptionUtils.isEncryptedValue(entry.getValue())) {
                String decodeValue = PropertyValueEncryptionUtils.decrypt(entry.getValue(), stringEncryptor);

                if (serverConfigType == ServerConfigTypeEnum.cluster) {
                    BeanUtil.pojo.setProperty(config.useClusterServers(), entry.getKey(), decodeValue);
                } else if (serverConfigType == ServerConfigTypeEnum.single) {
                    BeanUtil.pojo.setProperty(config.useSingleServer(), entry.getKey(), decodeValue);
                } else {
                    BeanUtil.pojo.setProperty(config.useSentinelServers(), entry.getKey(), decodeValue);
                }
            }
        }
    }
}
