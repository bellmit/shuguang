package com.sofn.fyem.config;

import com.sofn.common.config.BasePageHelperConfig;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybaits 分页插件 PageHelper配置类
 *
 * Created by heyongjie on 2019/5/29 17:15
 */
@Configuration
public class PageHelperConfig extends BasePageHelperConfig {

    @Override
    protected void setProperty(Properties properties){
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        // 配置oracle数据库的方言
//        properties.setProperty("dialect","mysql");
        properties.setProperty("dialect","postgresQL");
    }
}
