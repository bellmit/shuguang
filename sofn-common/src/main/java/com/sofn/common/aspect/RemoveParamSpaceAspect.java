package com.sofn.common.aspect;

import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 移除参数中的空格
 *
 * @author heyongjie
 * @date 2020/3/6 16:11
 */
/*@Aspect
@Component*/
@Slf4j
public class RemoveParamSpaceAspect {

    @Pointcut("execution(* com.sofn.*.web..*.*(..))")
    public void includeService() {
    }

    @Around("includeService()")
    public Object removeParamSpace(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            MethodSignature sign = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = sign.getMethod();
            // 请求的参数值
            Object[] args = proceedingJoinPoint.getArgs();
            // 获取参数类型
            Class<?>[] parameterNames = method.getParameterTypes();
            if (args.length == 0 || parameterNames.length == 0) {
                return proceedingJoinPoint.proceed();
            } else {
                if (args.length != parameterNames.length) {
                    return proceedingJoinPoint.proceed();
                } else {
                    List<Object> realParam = Lists.newArrayList();
                    boolean flag = false;
                    for (int i = 0; i < args.length; i++) {
                        // 如果是校验类就不去除空格
                        if ("org.springframework.validation.BindingResult".equalsIgnoreCase(parameterNames[i].getName())) {
                            realParam.add(args[i]);
                        } else {
                            String json;
                            try {
                                // 移除空格
                                json = JsonUtils.obj2json(args[i]);
                                if (!StringUtils.isBlank(json) && !"null".equalsIgnoreCase(json) && !"''".equalsIgnoreCase(json)) {
                                    // 去除空格
                                    String removeSpace = json.replaceAll(" ", "");
                                    // 去除\t
                                    String removeT = removeSpace.replaceAll("\\\\t", "");
                                    // 先转换一次! 如果转换失败直接抛出异常
                                    Object o = JsonUtils.json2obj(removeT, parameterNames[i]);
                                    if (o == null) {
                                        flag = true;
                                        break;
                                    }
                                    realParam.add(o);
                                } else {
                                    realParam.add(args[i]);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        // 一旦中间出现任何一个错误那么就不去除空格
                        return proceedingJoinPoint.proceed();
                    } else {
                        log.info("将移除请求参数中的空格");
                        return proceedingJoinPoint.proceed(realParam.toArray());
                    }
                }
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (throwable instanceof SofnException) {
                throw (SofnException) throwable;
            }
            throw new RuntimeException(throwable.getMessage());
        }
//        log.warn("去除空格切面返回默认的NULL");
//        return null;
    }


}
