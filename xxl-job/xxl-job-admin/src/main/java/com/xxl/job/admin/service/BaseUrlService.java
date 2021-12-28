package com.xxl.job.admin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支撑平台URL
 * @author heyongjie
 * @date 2019/12/12 15:01
 */
@Component
public class BaseUrlService {


    @Value("${xxl.sys.index}")
    private String sysIndexPage = "";

    @Value("${xxl.job.loginurl}")
    private String loginUrl ;

    @Value("${server.context-path}")
    private String contextPath;


    public String getSysIndexPage() {
        return sysIndexPage;
    }

    public void setSysIndexPage(String sysIndexPage) {
        this.sysIndexPage = sysIndexPage;
    }

    public String getLoginUrl() {
        // 获取登录路径
       return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * 获取项目根目录
     * @return  项目跟目录
     */
    public String getXxlRootPage() {
        if(contextPath == null || "".equals(contextPath)){
            return "/";
        }
        return contextPath + "/";
    }



}
