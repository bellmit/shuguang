package com.sofn.common.log;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SofnLog {

    /**
     * 日志描述<br/>
     * 例如：新增用户
     */
    String value() default "";

    /**
     * 日志类型<br/>
     * 例如：新增
     */
    String type() default "";

    /**
     * 日志标签<br/>
     * 例如：aaa
     */
    String tag() default "";

    /**
     * 是否需要获取结果数据<br/>
     * 默认不获取
     */
    boolean returnData() default false;

}

