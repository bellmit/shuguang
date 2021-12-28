package com.sofn.core.authorization.annotation;

import java.lang.annotation.*;

/**
 * describe:增加服务访问的时间间隔限制
 *
 * @author xuquan
 * @date 2018/11/09
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitServiceLimit {


    long value() default 5000;  //访问服务间隔时间，单位秒（ms）,默认为5s
}
