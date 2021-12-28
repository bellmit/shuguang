package com.sofn.agzirdd.config;



import com.sofn.common.filter.RemoveSpaceFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.DispatcherType;


/**
 * Created by sofn
 */
@Slf4j
@Aspect
@Configuration
public class WebConfiguration  {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/sys/**", config);
        source.registerCorsConfiguration("/druid/**", config);
        source.registerCorsConfiguration("/management/**", config);
        return new CorsFilter(source);
    }

    /**
     * 去除参数头尾空格过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean parmsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new RemoveSpaceFilter());
        registration.addUrlPatterns("/*");
        registration.setName("removeSpaceFilter");
        registration.setOrder(Integer.MAX_VALUE-1);
        return registration;
    }
}
