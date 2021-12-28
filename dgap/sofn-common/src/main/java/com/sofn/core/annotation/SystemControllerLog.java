package com.sofn.core.annotation;

import java.lang.annotation.*;

/**
 * Created by: dong4j.
 * Date: 2016-10-27.
 * Time: 13:54.
 * Description: 自定义注解 拦截Controller
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface SystemControllerLog {
    String description()  default "";
    String operationType() default "";
}
