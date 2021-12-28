package com.sofn.core.support.spring.data.redis.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangzg on 2016/9/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Cacheable {

    public enum KeyMode{
        DEFAULT,    //只有加了@CacheKey的参数,才加入key后缀中
        BASIC,      //只有基本类型参数,才加入key后缀中,如:String,Integer,Long,Short,Boolean
        ALL;        //所有参数都加入key后缀
    }

    public String key() default "";     //缓存key  系统ID+包名+类名+key @cacheable时如果没传key，这里需要传对应的方法名。
    public KeyMode keyMode() default KeyMode.DEFAULT;       //key的后缀模式
    public int expire() default 0;      //缓存多少秒,默认无限期
}
