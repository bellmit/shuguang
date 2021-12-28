package com.sofn.core.authorization.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sofn.core.authorization.annotation.VisitServiceLimit;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.JwtHelper;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.VisitService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import static jxl.biff.FormatRecord.logger;

/**
 * describe:
 *
 * @author xuquan
 * @date 2018/11/09
 */
public class VisitServiceLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ResponseHolder responseHolder;

    //鉴权信息的无用前缀，默认为空
    private String httpHeaderPrefix = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //查看方法上是否有注解
        if (method.getAnnotation(VisitServiceLimit.class) == null) {
            return true;
        }

        //从header中得到token
        String token = null;
        token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }

        String userId = "";

        if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0 && !StringUtils.isNullBlank(token)) {
            token = token.substring(httpHeaderPrefix.length());

            Claims claims = JwtHelper.parseJWT(token, ApiConstants.SOFN_API_BASE64SECRET);
            if (claims != null) {
                userId = (String) claims.get("userId");
            }
        }
        //验证存在 VisitServiceLimit 注解，并且解析token成功（即userId不为空），取消token是否有效检验
        if (method.getAnnotation(VisitServiceLimit.class) != null && userId != null && !userId.equals("")) {

            String contextPath = request.getContextPath();
            String requestUrl = request.getRequestURI();
            String context = contextPath.replace("/", "").replace("-","_").toUpperCase();
            String url = requestUrl.replace(contextPath,"").replace("/",".");
            long interval = method.getAnnotation(VisitServiceLimit.class).value();

            //增加用户访问服务时间限制
            VisitService visitService = new VisitService(context, url, interval, userId);
            if (visitService.isLimited()) {
                response.setStatus(HttpCode.FREQUENT_OPERATION.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));

                ModelMap map = new ModelMap();
                map.put("httpCode", HttpCode.FREQUENT_OPERATION.value());
                map.put("msg", visitService.getMessage());
                map.put("timestamp", System.currentTimeMillis());

                writer.write(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
                writer.close();
                return false;
            }


        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (responseHolder == null) {
            logger.error("system error", ex);
            responseHolder.setErrorMsg(HttpCode.FORBIDDEN);
        }

        responseHolder.clean();
    }
}
