package com.sofn.common.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: quzhijie
 * @Date: 2019/11/27 11:22
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImageTran {

}
