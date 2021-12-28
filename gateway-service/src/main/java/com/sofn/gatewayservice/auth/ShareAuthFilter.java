//package com.sofn.gatewayservice.auth;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.sofn.common.config.UriConfig;
//import com.sofn.common.constants.Constants;
//import com.sofn.common.model.Result;
//import com.sofn.common.utils.*;
//import com.sofn.common.utils.shiro.JWTToken;
//import com.sofn.gatewayservice.api.ShareApi;
//import com.sofn.gatewayservice.api.SysApi;
//import com.sofn.gatewayservice.utils.ExceptionUtil;
//import com.sofn.gatewayservice.utils.GatewayCacheUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.util.CollectionUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.util.*;
//
///**
// * 信息资源目录鉴权过滤器
// */
//@SuppressWarnings("ALL")
//public class ShareAuthFilter extends ZuulFilter {
//    private static Logger logger = LoggerFactory.getLogger("ShareAuthFilter");
//
//    @Autowired
//    private RedisHelper redisHelper;
//
//    @Autowired
//    private ShareApi shareApi;
//
//    @Autowired
//    private SysApi sysApi;
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        String token = request.getHeader(JWTToken.TOKEN);
//        if (StringUtils.isNotBlank(token)) {
//          String signType = JwtUtils.getSignType(token);
//          if (StringUtils.isNotBlank(signType) && StringUtils.equals(signType, JWTToken.SIGN_TYPE_SHARE)){
//            if (JwtUtils.verify(token)){
//              return true;
//            }
//          }
//        }
//
//        return false;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        String token = request.getHeader(JWTToken.TOKEN);
//        if (StringUtils.isBlank((token))) {
//            token = request.getParameter("token");
//            if (StringUtils.isBlank(token)) {
//              return null;
//            }
//        }
//
//        if (shareApi != null && sysApi != null) {
//            // 调用share-service接口校验token是否有效
//            Result<String> resultVerify = shareApi.tokenVerify(token, request.getRequestURL().toString());
//            boolean isValid = JwtUtils.verify(token) && resultVerify != null && StringUtils.equalsIgnoreCase(resultVerify.getData(),BoolUtils.Y);
//            if (isValid) {
//              String cacheToken = GatewayCacheUtils.getReplaceToken();
//              if (StringUtils.isBlank(cacheToken)) {
//                Result<String> tokenResult = sysApi.replaceToken();
//                if (tokenResult != null && tokenResult.getCode()==200 && StringUtils.isNotBlank(tokenResult.getData())){
//                  cacheToken = tokenResult.getData();
//                  GatewayCacheUtils.putReplaceToken(cacheToken);
//                }
//              }
//
//              final String replaceToken = cacheToken;
//              if (StringUtils.isNotBlank(replaceToken)){
//                // 让转发后的服务可以获取到替换后的token，不加入此行代码转发后的其他服务还是获取的原始token
//                ctx.addZuulRequestHeader(JWTToken.TOKEN, replaceToken);
//                // 让后续拦截器可以获取替换后的token
//                ctx.setRequest(new HttpServletRequestWrapper(request){
//                  @Override
//                  public String getHeader(String name) {
//                    if (StringUtils.equals(name,JWTToken.TOKEN)){
//                      return replaceToken;
//                    }
//
//                    return super.getHeader(name);
//                  }
//                });
//              }
//            }
//        }
//
//        return null;
//    }
//}
