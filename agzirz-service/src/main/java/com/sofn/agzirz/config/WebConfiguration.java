package com.sofn.agzirz.config;



import com.sofn.common.filter.RemoveSpaceFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.DispatcherType;
import javax.validation.Validation;
import javax.validation.Validator;


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
     *注入bean
     */
    @Bean
    public Validator localValidatorFactoryBean() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")   //快速validator,只要有错就校验反馈
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
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
