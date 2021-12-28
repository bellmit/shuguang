package com.sofn.common.config;

import com.sofn.common.utils.shiro.filter.ExceptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter 中的全局异常捕获
 * @author heyongjie
 * @date 2019/7/22 13:25
 */
@Configuration
public class WebMvcConfig {

    @Bean
    public ExceptionFilter exceptionFilter() {
        return new ExceptionFilter();
    }

    @Bean
    public FilterRegistrationBean registerExceptionFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(exceptionFilter());
        bean.addUrlPatterns("/*");
        // 将ExceptionFilter 注册为第一个
        bean.setOrder(1);
        return bean;
    }
}
