package com.sofn.gatewayservice.auth;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sofn.common.config.UriConfig;
import com.sofn.common.config.YmlConfig;
import com.sofn.common.constants.Constants;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.gatewayservice.utils.ExceptionUtil;
import com.sofn.gatewayservice.utils.FilterUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 鉴权过滤器
 */
public class AuthFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger("AuthFilter");
    private static List<String> filterRegexList = new ArrayList<>();

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private UriConfig uriConfig;

    @Autowired
    private YmlConfig ymlConfig;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        logger.info(request.getRequestURI());
        String uri = request.getRequestURI();

        if(request.getRequestURI().contains("swagger") || request.getRequestURI().contains("api-docs")){
            return false;
        }

        if (CollectionUtils.isEmpty(filterRegexList)){
          FilterUtil.uriToRegex(uriConfig, filterRegexList);
        }

        if (FilterUtil.isMatch(uri, filterRegexList)){
          return false;
        }

        return true;
    }

    
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getHeader(JWTToken.TOKEN);

        if (StringUtils.isBlank((token))) {
            token = request.getParameter("token");
        }

        if (StringUtils.isBlank(token) || !JwtUtils.verify(token) || !redisHelper.hasKey(token)) {
            ExceptionUtil.error_401(ctx);
        }else {
            Object rememberMe = redisHelper.hget(token, Constants.UserSession.rememberMe);
            Object mobilePlatform = redisHelper.hget(token, Constants.UserSession.mobilePlatform);
            // 是否记住密码
            boolean isRememberMe = rememberMe == null || BoolUtils.isFalse(rememberMe.toString());
            String pf = mobilePlatform == null?null:mobilePlatform.toString();
            boolean isMobilePlatform =  BoolUtils.isTrue(pf);

            // 记住密码或移动平台不需要每次刷新
            if (isRememberMe || !isMobilePlatform){
                // 刷新过期时间
                redisHelper.expire(token, ymlConfig.tokenExpireTime/1000);
            }
        }

        return null;
    }
}
