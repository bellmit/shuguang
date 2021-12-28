package com.sofn.fdzem.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.JwtUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {
    public static String getUserName(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request != null){
            String token = request.getHeader("Authorization");
            return JwtUtils.getUsername(token);
        }else {
            throw new SofnException("未获取到请求对象");
        }
    }
}
