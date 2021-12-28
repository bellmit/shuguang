package com.sofn.core.support.spring.data.redis.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangzg on 2016/9/9.
 */
@Aspect
@Component
public class CacheableAop {

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, Cacheable cache) throws Throwable {
        String key=getCacheKey(pjp, cache);
        ValueOperations<String, Object> valueOper=redisTemplate.opsForValue();
        Object value=valueOper.get(key);    //从缓存获取数据
        if(value!=null) return value;       //如果有数据,则直接返回

        value = pjp.proceed();      //跳过缓存,到后端查询数据
        if(cache.expire()<=0) {      //如果没有设置过期时间,则无限期缓存
            valueOper.set(key, value);
        } else {                    //否则设置缓存时间
            valueOper.set(key, value,cache.expire(), TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 获取缓存的key值
     * @param pjp
     * @param cache
     * @return
     */
    private String getCacheKey(ProceedingJoinPoint pjp,Cacheable cache) {

        StringBuilder buf=new StringBuilder();
        buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
        if(cache.key().length()>0) {
            buf.append(".").append(cache.key());
        }

        Object[] args=pjp.getArgs();
        if(cache.keyMode()== Cacheable.KeyMode.DEFAULT) {
            Annotation[][] pas=((MethodSignature)pjp.getSignature()).getMethod().getParameterAnnotations();
            for(int i=0;i<pas.length;i++) {
                for(Annotation an:pas[i]) {
                    if(an instanceof CacheKey) {
                        buf.append(".").append(args[i].toString());
                        break;
                    }
                }
            }
        } else if(cache.keyMode()== Cacheable.KeyMode.BASIC) {
            for(Object arg:args) {
                if(arg instanceof String) {
                    buf.append(".").append(arg);
                } else if(arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
                    buf.append(".").append(arg.toString());
                } else if(arg instanceof Boolean) {
                    buf.append(".").append(arg.toString());
                }
            }
        } else if(cache.keyMode()== Cacheable.KeyMode.ALL) {
            for(Object arg:args) {
                buf.append(".").append(arg.toString());
            }
        }

        return buf.toString();
    }
}
    /*
    对get()方法配置使用缓存,缓存有效期为3600秒,缓存的key格式为:{package_name}.DemoServiceImpl.get
    同时为参数配置了@CacheKey后,表示此参数的值将做为key的后缀,此例的key,最终是:{package_name}.DemoServiceImpl.get.{id}
    可以为多个参数配置@CacheKey,拦截器会调用参数的toString()做为key的后缀
    若配置多个@CacheKey参数,那么最终的key格式为:{package_name}.{class_name}.{method}.{arg1}.{arg2}.{...}
    */
    /*
    @Cacheable(expire=3600)
    public Demo get(@CacheKey String id) {
        return demoDao.get(id);
    }
    */

    /*
    若为名称相同的方法配置缓存,可以在@Cacheable中加入key属性,追加额外的key后缀
    @Cacheable还有一个KeyMode属性,用于配置哪些参数可以追加到key后缀中,
    默认取值 DEFAULT:表示只有加了@CacheKey的参数才能追加到key后缀
    BASIC:自动将基本类型追加到key后缀,而无需再配置@CacheKey
    ALL:自动将所有参数追加到lkey后缀,而无需再配置@CacheKey
    */
