package com.sofn.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 删除Redis KEY
 * @author heyongjie
 * @date 2019/12/30 13:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisDel {

    /**
     * 需要删除的KEY
     * @return   String []
     */
    String [] key();


    /**
     * 是否是Map   如果是Map那么关键字将被加在item里面
     * @return  String []
     */
    boolean [] isMap() default {};

    /**
     * KEY是否携带登录用户ID
     * 如果携带那么删除KEY的格式为   key*loginUserId*
     * @return boolean
     */
    boolean [] isCarryLoginUserId() default {};


    /**
     * KEY是否携带参数，只能是参数中的某个值
     * 如果携带了参数那么将自动在key或者item中加上参数，删除的时候就删除匹配的键
     * @return  boolean
     */
    boolean [] isCarryParams() default {};

    /**
     * ITEM携带参数表达式 每个key单独指定，删除的时候可以直接从方法请求参数中获取值
     * @return   String
     */
    String [] keywords() default {};

    /**
     * ITEM 关键字
     * @return  items
     */
    String [] items() default {};


}
