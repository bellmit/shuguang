package com.sofn.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis 缓存
 * 使用在有返回值得方法上，添加该注解后该方法的返回值将会被缓存
 * 方法没有返回值  该注解无效
 *
 * 如果既需要携带参数也需要携带登录用户，那么最终生成的key：RedisCache中key_将参数转为json后的字符串_当前登录用户ID
 * @author heyongjie
 * @date 2019/12/30 13:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * REDIS键
     * @return   String
     */
    String key() ;

    /**
     * 超时时间   默认4个小时
     * 如果item有值  超时时间将失效
     * @return long
     */
    long timeout() default 1000* 60 * 60 * 4;

    /**
     * Map自定义item
     * @return   String
     */
    String item() default "";

    /**
     * 是否是Map
     * @return   boolean
     */
    boolean isMap() default false;

    /**
     * KEY是否携带登录用户ID
     * 如果携带参数，那么最终生成的KEY:RedisCache中key_将参数转为json后的字符串
     * @return boolean
     */
    boolean isCarryLoginUserId() default false;

    /**
     * KEY是否携带参数
     * 如果携带当前登录用户，那么最终生成的KEY：RedisCache中key_当前登录用户ID
     * @return  boolean
     */
    boolean isCarryParams() default false;

}
