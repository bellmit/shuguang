package com.sofn.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parsing The Configuration File
 * @author sofn
 * @version 2016年7月30日 下午11:41:53
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Map<String, String> ctxPropertiesMap;
    //配置文件中要解密的键值对的key
    private final String[] encryptPropNames = {"redis.password"};
    private static final byte[] KEY = {82, -1, 13, 5, 3, 7, 24, 19};
    // 是否解密属性值
    private boolean decryptPropertyValue = false;

    /**
     * Convert the given property from the properties source to the value
     * which should be applied.
     * <p>The default implementation calls {@link #convertPropertyValue(String)}.
     *
     * @param propertyName  the name of the property that the value is defined for
     * @param propertyValue the original value from the properties source
     * @return the converted value, to be used for processing
     * @see #convertPropertyValue(String)
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (decryptPropertyValue && isEncryptProperty(propertyName)) {
            String decryptValue = null;
            try {
                decryptValue = SecurityUtil.decryptDes(propertyValue, KEY);
            } catch (Exception e) {
                decryptValue = propertyValue;
            } finally {
                logger.debug("已解密的属性: {}={}", propertyName, decryptValue);
                return decryptValue;
            }
        } else {
            return propertyValue;
        }
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new ConcurrentHashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value == null ? "" : value);
        }

    }

    /**
     * Get a value based on KEY , if KEY does not exist , null is returned
     * 
     * @param key
     * @return
     */
    public static String getString(String key) {
        if (key == null) {
            return "";
        }
        try {
            return ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 根据key获取值
     * 
     * @param key
     * @return
     */
    public static int getInt(String key) {
        if (org.springframework.util.StringUtils.isEmpty(key)) {
            return -1;
        }
        return Integer.parseInt(ctxPropertiesMap.get(key));
    }

    public static Map<String, String> getGlobalMap(){
        return ctxPropertiesMap;
    }

    /**
     * 判断是否是加密的属性
     * @param propertyName
     * @return
     */
    private boolean isEncryptProperty(String propertyName) {
        for (String encryptPropertyName : encryptPropNames) {
            if (encryptPropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 加密属性值
     * @param args 属性值运行参数
     */
    public static void main(String[] args) {
        for (String propertyValue : args) {
            String encryptedPropertyValue = SecurityUtil.encryptDes(propertyValue, KEY);
            logger.info("加密前的属性值：{} --> 加密后的属性值：{}", propertyValue, encryptedPropertyValue);
        }
    }

    public boolean isDecryptPropertyValue() {
        return decryptPropertyValue;
    }

    public void setDecryptPropertyValue(boolean decryptPropertyValue) {
        this.decryptPropertyValue = decryptPropertyValue;
    }
}
