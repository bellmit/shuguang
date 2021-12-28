package com.sofn.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @description: 请求参数校验配置
 * @author: tc
 * @date: 2019/11/28 10:42
 */
@Configuration
public class WebValidatorConfig {
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(true) //只要有参数校验出错，直接返回，不必校验后面的
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
