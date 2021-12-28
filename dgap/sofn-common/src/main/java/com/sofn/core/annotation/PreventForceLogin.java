/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.annotation;

import com.sofn.core.constant.LoginFailedNoticeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lijiang
 * @desc 防止暴力登录注解
 * @create 2018-11-27 18:06
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PreventForceLogin {
    /**
     * 账号锁定时长，默认5分钟，单位秒
     * @return
     */
    long lockDuration() default 300;

    /**
     * 登录失败次数超过上限时的通知类型，默认使用抛出异常的方式通知调用方
     * @return
     */
    LoginFailedNoticeType noticeType() default LoginFailedNoticeType.exception;
}
