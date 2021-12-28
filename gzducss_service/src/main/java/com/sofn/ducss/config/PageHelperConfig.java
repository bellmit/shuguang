package com.sofn.ducss.config;

import com.sofn.ducss.config.BasePageHelperConfig;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybaits 分页插件 PageHelper配置类
 * <p>
 * Created by heyongjie on 2019/5/29 17:15
 */
@Configuration
public class PageHelperConfig extends BasePageHelperConfig {

    protected void setProperty(Properties properties) {
        super.setProperty(properties);
        // 配置oracle数据库的方言
        properties.setProperty("dialect", "postgresQL");
    }
}
