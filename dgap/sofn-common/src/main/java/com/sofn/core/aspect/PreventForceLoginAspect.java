/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.aspect;

import com.sofn.core.annotation.PreventForceLogin;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.constant.LoginFailedNoticeType;
import com.sofn.core.exception.LoginException;
import com.sofn.core.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijiang
 * @desc 防止暴力登录切面
 * @create 2018-11-27 18:13
 **/
@Aspect
@Component
public class PreventForceLoginAspect {
    private static final String LOGIN_FAILURE_TIMES_KEY = "LOGIN_FAILURE_TIMES:account=%s";
    private static final String ERROR_MESSAGE_TEMPLATE = "多次登录失败，账户已被锁定，%d分钟后再试！";
    private static final int FAILURE_TIMES_UPPER_LIMIT = 3; // 登录失败次数上限

    /**
     * 切入点
     */
    @Pointcut(value = "@annotation(com.sofn.core.annotation.PreventForceLogin) && args(p1, p2)", argNames = "p1,p2")
    public void pointcut(String p1, String p2) {
    }

    /**
     * 环绕通知
     *
     * @param joinPoint 切入点对象
     * @param account   账号
     * @param password  密码
     * @return 被切方法的返回值
     * @throws Throwable
     */
    @Around("pointcut(account,password)")
    public Object around(ProceedingJoinPoint joinPoint, String account, String password) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        PreventForceLogin preventForceLogin = method.getAnnotation(PreventForceLogin.class);
        long lockDuration = preventForceLogin.lockDuration();
        LoginFailedNoticeType noticeType = preventForceLogin.noticeType();
        final String loginFailureTimesKey = String.format(LOGIN_FAILURE_TIMES_KEY, account);

        // 获取登录失败次数
        Integer loginFailureTimes = (Integer) RedisUtil.get(loginFailureTimesKey, -1);
        // 登录失败次数超过上限值就略过登录操作，并提示错误信息
        if (loginFailureTimes != null && loginFailureTimes >= FAILURE_TIMES_UPPER_LIMIT) {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE, (lockDuration / 60));
            if (noticeType == LoginFailedNoticeType.exception) {
                throw new LoginException(errorMessage);
            } else {
                Map<String, Object> loginFailedMap = new HashMap<>();
                loginFailedMap.put("errorCode", HttpCode.LOGIN_LOCKED);
                loginFailedMap.put("errorMessage", errorMessage);
                return loginFailedMap;
            }

        }

        // 执行被拦截的登录方法
        Object object = joinPoint.proceed();

        if (object instanceof Map) {
            Map<String, Object> loginResultMap = (Map<String, Object>) object;

            // 登录失败，账户或者密码错误
            if (loginResultMap == null || loginResultMap.isEmpty()) {
                // 登录失败计数
                if (loginFailureTimes != null) {
                    loginFailureTimes++;
                } else {
                    loginFailureTimes = new Integer(1);
                }

                RedisUtil.set(loginFailureTimesKey, loginFailureTimes, lockDuration);
            } else {
                RedisUtil.del(loginFailureTimesKey);
            }
        }

        return object;
    }
}
