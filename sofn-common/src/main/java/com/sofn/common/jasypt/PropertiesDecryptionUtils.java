/*
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 *
 *
 */
package com.sofn.common.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import java.util.Properties;
import java.util.Set;

/**
 * 属性解密工具类
 * 用于解密属性中的加密配置项
 *
 * @author John
 * @version 1.0
 */
public final class PropertiesDecryptionUtils {
    /**
     * 解密属性中的加密配置项
     *
     * @param properties 属性
     */
    public static void decrypt(Properties properties) {
        StringEncryptor encryptor = StringEncryptorFactory.getInstance();
        Set<String> propNames = properties.stringPropertyNames();

        for (String name : propNames) {
            String value = properties.getProperty(name);
            if (PropertyValueEncryptionUtils.isEncryptedValue(value)) {
                properties.setProperty(name, PropertyValueEncryptionUtils.decrypt(value, encryptor));
            }
        }
    }
}
