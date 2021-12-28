package com.sofn.flow.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 *@Description mybaits 分页插件 PageHelper配置类
 *@Author quzhijie
 *@Date 2019/11/15 13:56
 *@Version 1.0
 **/
public class BasePageHelperConfig {

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        setProperty(properties);
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    protected void setProperty(Properties properties){
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        // 配置oracle数据库的方言
        properties.setProperty("dialect","mysql");
    }
}
