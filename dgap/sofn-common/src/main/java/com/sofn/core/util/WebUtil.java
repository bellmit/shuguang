package com.sofn.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofn.core.Constants;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Web层辅助类
 *
 * @author dong4j
 * @version 2016年11月02日  上午 12:21:19 dong4j
 */
public final class WebUtil {

    protected static Logger logger = LoggerFactory.getLogger(WebUtil.class);
    /**
     * Instantiates a new Web util.
     */
    private WebUtil() {
    }

    /**
     * 保存当前用户
     *
     * @param user the user
     */
    public static void saveCurrentUser(CurrentUser user) {
        String key = user.getId() + Constants.CURRENT_USER;
        RedisUtil.set(key, user);
    }

    /**
     * 获取当前用户名Id todo 后期删除
     *
     * @return the current user id
     */
    public static String getCurrentUserId() {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if ("".equals(token))
            return ApiConstants.DEFAULT_USER;
        String userId = WebUtil.getCurrentUserId(token);
        if ("".equals(userId))
            return ApiConstants.DEFAULT_USER;
        else
            return userId;
    }

    /**
     * Gets current user id.
     * 根据 token 获取当前用户 id
     *
     * @param token the token
     * @return the current user id
     */
    public static String getCurrentUserId(String token) {
        CurrentUser currentUser = getCurrentUser(token);
        if (currentUser != null) {
            return currentUser.getId();
        }
        return ApiConstants.DEFAULT_USER;
    }

    /**
     * 获取当前用户名称
     *
     * @param token
     * @return
     */
    public static String getCurrentUserName(String token) {
        CurrentUser currentUser = getCurrentUser(token);
        if (currentUser != null) {
            return currentUser.getAccount();
        }
        return ApiConstants.DEFAULT_USER;
    }

    /**
     * Gets current user.
     * 根据 token 获取当前用户
     *
     * @param token the token
     * @return the current user
     */
    public static CurrentUser getCurrentUser(String token) {
//        // 解析 token
//        Claims claims = JwtHelper.parseJWT(token, ApiConstants.SOFN_API_BASE64SECRET);
//        System.out.println("claims:" + claims);
//        for (int i = 0; i < 1; i++) {
//            //判断令牌是否为空，如果空等待100毫秒后再解析一次
//            if (claims == null || claims.equals("")) {
//                try {
//                    Thread.sleep(100);
//                    claims = JwtHelper.parseJWT(token, ApiConstants.SOFN_API_BASE64SECRET);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    logger.error(e.toString());
//                }
//
//            }
//        }
//        if (claims != null) {
//            String userId = (String) claims.get("userId");
//            String currentUserKey = generateRedisCurrentUserKey(userId);
//            // 从 redis 获取 TODO 此处需要指定失效时间
//            CurrentUser currentUser = (CurrentUser) RedisUtil.get(currentUserKey);
//            System.out.println("currentUser:" + currentUser);
//            return currentUser;
//        }
//        return null;
        if(null == token){
            return null;
        }
        String loginKey = String.format("LOGIN:login_key#token=%s", token);
        JSONObject obj =  JSONObject.parseObject((String)RedisUtil.get(loginKey,30*60));

        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccount(obj.getString("account"));
        currentUser.setEmail(obj.getString("email"));
        currentUser.setOrganizationId(obj.getString("organizationId"));
        currentUser.setOrgType(obj.getString("orgType"));
        currentUser.setOrgName(obj.getString("orgName"));
        currentUser.setId(obj.getString("id"));
        currentUser.setDelFlag(obj.getString("delFlag"));
        currentUser.setUserName(obj.getString("userName"));

        Organization organization = new Organization();
        organization.setOrgLevel(obj.getString("orgLevel"));
        organization.setRegionId(obj.getString("regionId"));
        organization.setOrgType(obj.getString("orgType"));
        organization.setOrgName(obj.getString("orgName"));
        organization.setId(obj.getString("organizationId"));

        currentUser.setOrganization(organization);
        return currentUser;
    }

    public static String getTheCurrentUserId(String token) {
        // 解析当前token
        Claims claims = JwtHelper.parseJWT(token, ApiConstants.SOFN_API_BASE64SECRET);
        String userId = (String) claims.get("userId");
        System.out.println("当登录用户id为==：" + userId);
        if (userId == null) {//没有再取一次
            String usrId = (String) JwtHelper.parseJWT(token, ApiConstants.SOFN_API_BASE64SECRET).get("userId");
            System.out.println("登录用户id为==：" + userId);
            return usrId;
        }
        return userId;
    }

    /**
     * 移除当前用户
     *
     * @param token the token
     */
    public static void removeCurrentUser(String token) {
        RedisUtil.del(getCurrentUserId(token) + Constants.CURRENT_USER);
    }

    /**
     * 获得国际化信息
     *
     * @param key     键
     * @param request the request
     * @return application resource
     */
    public static String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    /**
     * 获得参数Map
     *
     * @param request the request
     * @return parameter map
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, null);
    }

    /**
     * 获取客户端IP
     *
     * @param request the request
     * @return the host
     */
    public static String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip)) {
            InetAddress inet = null;
            try { // 根据网卡取本机配置的IP
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ip = inet != null ? inet.getHostAddress() : "";
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    private static final String HEADER_X_FORWARDED_FOR =
            "X-FORWARDED-FOR";

    public static String remoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String x;
        if ((x = request.getHeader(HEADER_X_FORWARDED_FOR)) != null) {
            remoteAddr = x;
            int idx = remoteAddr.indexOf(',');
            if (idx > -1) {
                remoteAddr = remoteAddr.substring(0, idx);
            }
        }
        return remoteAddr;
    }

    /**
     * 生成在redis中存放token的key键
     *
     * @param account 账号
     * @param userId  用户ID
     * @param suffix  token后缀
     * @return 存放token的key
     */
    public static String generateRedisTokenKey(String account, String userId, String suffix) {
        return String.format("%s:%s_%s", suffix, account, userId);
    }

    /**
     * 生成在redis中存放当前登录用户信息的key键
     *
     * @param userId 当前登录用户ID
     * @return 当前登录用户对应的key
     */
    public static String generateRedisCurrentUserKey(String userId) {
        return String.format("%s:%s", Constants.CURRENT_USER, userId);
    }

    /**
     * 判断是否是微软他家的浏览器
     *
     * @param request http请求
     * @return 判断结果
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        final String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成登录缓存使用的key
     * @param account 账号
     * @param password 密码
     * @return key
     */
    public static String generateLoginCacheKey(String account, String password) {
        return String.format("LOGIN:login_key#account=%s&password=%s", account, password);
    }

    /**
     * 生成操作日志的缓存KEY
     * @param logId 日志ID
     * @return 操作日志的缓存KEY
     */
    public static String generateLogKey(String logId) {
        return String.format("operateLog:id=%s", logId);
    }

    /**
     * 设置下载文件时浏览器保存对话框显示的文件名
     *
     * @param request http请求
     * @param response http响应
     * @param fileName 浏览器保存对话框显示的文件名
     * @throws UnsupportedEncodingException
     */
    public static void setFileNameForDownload(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        // 解决文件下载时，文件名中文乱码问题
        if (WebUtil.isMSBrowser(request)) {
            String encodeFilename = URLEncoder.encode(fileName, "UTF-8");
            encodeFilename = encodeFilename.replace("+", "%20"); // 解决文件名称中有空格转码后有“+”号的问题
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", encodeFilename));
        } else {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "iso-8859-1")));
        }
    }
}
