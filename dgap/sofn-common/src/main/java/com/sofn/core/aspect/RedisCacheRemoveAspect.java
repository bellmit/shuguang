/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.aspect;

import com.sofn.core.annotation.RedisCacheRemove;
import com.sofn.core.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lijiang
 * @desc 移除redis缓存的切面
 * @create 2018-11-09 10:47
 **/
@Aspect
@Component
public class RedisCacheRemoveAspect {
    private final Logger logger = LoggerFactory.getLogger(RedisCacheRemoveAspect.class);

    /**
     * 切入点
     */
    @Pointcut(value = "@annotation(com.sofn.core.annotation.RedisCacheRemove)")
    public void pointcut() {
    }

    /**
     * 后置返回通知
     *
     * @param joinPoint   切入点对象
     * @param returnValue 被切方法返回值
     */
    @AfterReturning(pointcut = "pointcut()", returning = "returnValue")
    public void afterReturn(JoinPoint joinPoint, Object returnValue) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedisCacheRemove cacheRemove = method.getAnnotation(RedisCacheRemove.class);

        if (cacheRemove != null) {
            String prefix = cacheRemove.cachePrefix();
            String[] keys = cacheRemove.key();

            for (String key : keys) {
                String keyPattern = prefix + key;
                RedisUtil.delAll(keyPattern);
                logger.debug("缓存已删除：key={}", keyPattern);
            }
        }
    }

}
