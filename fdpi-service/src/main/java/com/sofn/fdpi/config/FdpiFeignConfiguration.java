package com.sofn.fdpi.config;

import com.sofn.common.model.Result;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.Auth;
import com.sofn.fdpi.sysapi.bean.LoginVo;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Configuration
public class FdpiFeignConfiguration implements RequestInterceptor {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private final String mark = "mark";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            // 对消息头进行配置
            String token = request.getHeader(JWTToken.TOKEN);
            StringBuffer requestURL = request.getRequestURL();
            if (StringUtils.isNotBlank(token) || threadLocal.get() != null) {
                template.header(JWTToken.TOKEN, token);
            } else if (requestURL.toString().contains("enterprise/register") && threadLocal.get() == null) {
                SysRegionApi bean = SpringContextHolder.getBean(SysRegionApi.class);
                LoginVo defaultUser = LoginVo.getDefaultUser();
                threadLocal.set(mark);
                Result<Auth> login = bean.login(defaultUser);
                Auth data = login.getData();
                threadLocal.remove();
                template.header(JWTToken.TOKEN, data.getToken());
            }
        }
    }

}