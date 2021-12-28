package com.sofn.core.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangzg on 2016/12/26.
 */
public class ExpostPropertyConfigurer extends PropertyPlaceholderConfigurer {
    //使用方式
    //System.out.println(ExpostPropertyConfigurer.getProperties("redis.ip"));
    private static Map ctxMap = null;
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props){
        super.processProperties(beanFactory,props);
        ctxMap =new HashMap();
        for (Object obj:props.keySet()){
            ctxMap.put(obj,props.get(obj));
        }
    }
    public static String getProperties(String key){
        return (String) ctxMap.get(key);
    }
}
