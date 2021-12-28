package com.sofn.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 /**
  * @Description: 图片id转换成URL注解
  * @Author: quzhijie
  * @Date: 2019/11/27 11:22
  **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataImage {

  boolean isList() default false;

}
