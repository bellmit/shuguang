/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lijiang
 * @desc 移除redis缓存的注解, 支持批量删除
 * @create 2018-11-09 10:35
 **/
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheRemove {
    String cachePrefix() default "";

    String[] key() default {};
}
