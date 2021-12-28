package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.util.CookieUtil;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.core.util.JacksonUtil;
import com.xxl.job.admin.dao.XxlJobUserDao;
import com.xxl.job.admin.service.impl.SysUserToXXLJobUser;
import com.xxl.job.admin.sysapi.SysUserApi;
import com.xxl.job.admin.sysapi.bean.SysUserForm;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author xuxueli 2019-05-04 22:13:264
 */
@Configuration
public class LoginService {

    public static final String LOGIN_IDENTITY_KEY = "XXL_JOB_LOGIN_IDENTITY";

    @Resource
    private XxlJobUserDao xxlJobUserDao;

    @Autowired
    private SysUserApi sysUserApi;

    @Autowired
    private BaseUrlService baseUrlService;

    @Autowired
    private SysUserToXXLJobUser sysUserToXXLJobUser;

    private String makeToken(XxlJobUser xxlJobUser) {
        String tokenJson = JacksonUtil.writeValueAsString(xxlJobUser);
        String tokenHex = new BigInteger(tokenJson.getBytes()).toString(16);
        return tokenHex;
    }

    private XxlJobUser parseToken(String tokenHex) {
        // 校验token
        XxlJobUser xxlJobUser = null;
        if (tokenHex != null) {
            String tokenJson = new String(new BigInteger(tokenHex, 16).toByteArray());      // username_password(md5)
            xxlJobUser = JacksonUtil.readValue(tokenJson, XxlJobUser.class);
        }
        return xxlJobUser;
    }


    public ReturnT<String> login(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean ifRemember) {
        // param
        if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
            return new ReturnT<String>(500, I18nUtil.getString("login_param_empty"));
        }

        // valid passowrd
        XxlJobUser xxlJobUser = xxlJobUserDao.loadByUserName(username);
        if (xxlJobUser == null) {
            return new ReturnT<String>(500, I18nUtil.getString("login_param_unvalid"));
        }
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!passwordMd5.equals(xxlJobUser.getPassword())) {
            return new ReturnT<String>(500, I18nUtil.getString("login_param_unvalid"));
        }

        String loginToken = makeToken(xxlJobUser);

        // do login
        CookieUtil.set(response, LOGIN_IDENTITY_KEY, loginToken, ifRemember);
        return ReturnT.SUCCESS;
    }


    public ReturnT<String> tokenLogin(HttpServletRequest request, HttpServletResponse response,
                                      String token, String userId) {

        SysUserForm userInfo = sysUserApi.getUserInfo(token, userId);
        try {
            if (userInfo == null) {
                XxlJobLogger.log("调用支撑平台接口失败：参数：TOKEN=["+token+"]，useId=["+userId+"]");
                // 结束登录
                logout(request, response);
                // 访问首页跳转到登录界面
                response.sendRedirect(baseUrlService.getXxlRootPage());
                return new ReturnT<String>(500, "未获取到支撑平台用户信息！请检查");
            } else {
                // do login
                XxlJobUser xxlJobUser = xxlJobUserDao.loadByUserName(userInfo.getUsername());
                // 保存用户信息
                if (xxlJobUser == null) {
                    // 保存用户信息
                    xxlJobUser = sysUserToXXLJobUser.addXXlJobUserBySysUser(userInfo);
                }
                // 成功后使用当前用户进行登录
                login(request, response, xxlJobUser.getUsername(), SysUserToXXLJobUser.DEFAULT_PASSWORD, true);
                // 这里直接尝试访问首页，如果没有登录成功会自动跳转到配置的或者默认的登录界面
                response.sendRedirect(baseUrlService.getXxlRootPage());
                return ReturnT.SUCCESS;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ReturnT.FAIL;
        }
    }


    /**
     * logout
     *
     * @param request
     * @param response
     */
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, LOGIN_IDENTITY_KEY);
        return ReturnT.SUCCESS;
    }

    /**
     * 每次登陆都会在这里判断是否登陆
     *
     * @param request
     * @return
     */
    public XxlJobUser ifLogin(HttpServletRequest request, HttpServletResponse response){
        String cookieToken = CookieUtil.getValue(request, LOGIN_IDENTITY_KEY);
        if (cookieToken != null) {
            XxlJobUser cookieUser = null;
            try {
                cookieUser = parseToken(cookieToken);
            } catch (Exception e) {
                logout(request, response);
            }
            if (cookieUser != null) {
                XxlJobUser dbUser = xxlJobUserDao.loadByUserName(cookieUser.getUsername());
                if (dbUser != null) {
                    if (cookieUser.getPassword().equals(dbUser.getPassword())) {
                        return dbUser;
                    }
                }
            }
        }
        return null;
    }

}
