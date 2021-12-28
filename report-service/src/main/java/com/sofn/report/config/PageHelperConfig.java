package com.sofn.report.config;

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
    protected void setProperty(Properties properties) {
        super.setProperty(properties);
        properties.setProperty("dialect","mysql");
    }
}
