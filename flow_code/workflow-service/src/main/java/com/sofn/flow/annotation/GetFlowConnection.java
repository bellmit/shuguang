package com.sofn.flow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 获取连接，使用此注解的方法，第一个参数必须是WfClient，用于将连接注入到方法中
 * @author heyongjie
 * @date 2020/5/9 13:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetFlowConnection {

    /**
     * 是否启用事务
     * @return  false  不启用  true启用
     */
    boolean isTransactional() default false;

}
