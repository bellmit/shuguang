/*
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 *
 *
 */
package com.sofn.common.jasypt;

import com.sofn.common.config.YmlConfig;
import com.sofn.common.utils.SpringContextHolder;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * StringEncryptor工厂类，用于获取StringEncryptor实例
 *
 * @author John
 * @version 1.0
 */
public final class StringEncryptorFactory {
    /**
     * 获取StringEncryptor实例
     *
     * @return StringEncryptor实例
     */
    public static StringEncryptor getInstance() {
        YmlConfig config = SpringContextHolder.getBean(YmlConfig.class);

        final StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        final EnvironmentStringPBEConfig stringPBEConfig = new EnvironmentStringPBEConfig();
        stringPBEConfig.setAlgorithm("PBEWithMD5AndDES");
        stringPBEConfig.setPassword(config.jasyptEncryptorPassword);
        stringEncryptor.setConfig(stringPBEConfig);

        return stringEncryptor;
    }
}
