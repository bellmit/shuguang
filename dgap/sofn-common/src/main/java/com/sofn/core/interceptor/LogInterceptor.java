package com.sofn.core.interceptor;

import com.sofn.core.config.Global;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.JwtHelper;
import com.sofn.core.util.StringUtils;
import io.jsonwebtoken.Claims;
import org.apache.log4j.NDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Created by: dong4j.
 * Date: 2016-11-28.
 * Time: 15:51.
 * Description: 日志拦截
 */
public class LogInterceptor extends BaseInterceptor {
    private              Logger logger      = LoggerFactory.getLogger(getClass());
    private final static String DEFAULT_CID = "1234567890";

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
            "ThreadLocal StartTime");

    @SuppressWarnings("rawtypes")
    private String getRequestMap(HttpServletRequest request) {
        Iterator     iterator = request.getParameterMap().entrySet().iterator();
        StringBuffer param    = new StringBuffer();
        int          i        = 0;
        while (iterator.hasNext()) {
            i++;
            Entry entry = (Entry) iterator.next();
            if (i == 1)
                param.append("?").append(entry.getKey()).append("=");
            else
                param.append("&").append(entry.getKey()).append("=");
            if (entry.getValue() instanceof String[]) {
                param.append(((String[]) entry.getValue())[0]);
            } else {
                param.append(entry.getValue());
            }
        }
        return param.toString();
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uniqueToken = request.getHeader("token");
        Claims claims      = JwtHelper.parseJWT(uniqueToken, Global.getConfig("sofn.api.base64Secret"));
        String userId      = "";
        String account     = "";
        if (claims != null) {
            account = (String) claims.get("account");
            userId = (String) claims.get("userId");
        }
        String ip   = request.getHeader("X-Real-IP");
        String host = request.getHeader("Host");
        NDC.push((StringUtils.isEmpty(userId) ? DEFAULT_CID : userId) + "@" + StringUtils.getRandomNumber(10));
        logger.info("请求开始: host-ip:{}-{} token:{}  userId-account: {}-{}  URI: {}", host, ip, uniqueToken,
                userId, account, request.getRequestURI());
        logger.info("请求参数:" + getRequestMap(request));
        if (logger.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();// 1、开始时间
            startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat(
                    "hh:mm:ss.SSS").format(beginTime), request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
        //return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        String uniqueToken = request.getHeader("token");
        Claims claims      = JwtHelper.parseJWT(uniqueToken, Global.getConfig("sofn.api.base64Secret"));
        String userId      = "";
        String account     = "";
        if (claims != null) {
            account = (String) claims.get("account");
            userId = (String) claims.get("userId");
        }
        logger.info("请求结束:  token:{}  userId-account: {}-{}  URI: {}", uniqueToken,
                userId, account, request.getRequestURI());
        // 打印JVM信息。
        if (logger.isDebugEnabled()) {
            long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
            long endTime   = System.currentTimeMillis(); // 2、结束时间
            logger.info(
                    "计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
                    DateUtil.formatDateTime(endTime - beginTime), request
                            .getRequestURI(),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime
                            .getRuntime().totalMemory() / 1024 / 1024, Runtime
                            .getRuntime().freeMemory() / 1024 / 1024, (Runtime
                            .getRuntime().maxMemory()
                            - Runtime.getRuntime().totalMemory() + Runtime
                            .getRuntime().freeMemory()) / 1024 / 1024);
        }
        NDC.pop();
        super.afterCompletion(request, response, handler, ex);
    }
}
