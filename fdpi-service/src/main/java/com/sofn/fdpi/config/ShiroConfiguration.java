package com.sofn.fdpi.config;

import com.sofn.common.config.BaseShiroConfig;
import com.sofn.common.utils.shiro.JWTFilter;
import com.sofn.common.utils.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sofn
 */
@Configuration
public class ShiroConfiguration extends BaseShiroConfig {

    @Override
    protected void filter(Map<String, String> filterMap) {
        filterMap.put("/enterprise/register", "anon");
        filterMap.put("/enterprise/listPapersForSelect", "anon");
        filterMap.put("/enterprise/getPapersById", "anon");
        filterMap.put("/enterprise/getCompByCompName","anon");
        filterMap.put("/papers/paperInfo","anon");
        filterMap.put("/signboardManage/getByCode", "anon");
        filterMap.put("/question/save", "anon");
        filterMap.put("/TbArticles/get", "anon");
        filterMap.put("/TbArticles/list", "anon");
        filterMap.put("/TbArticles/count", "anon");
        filterMap.put("/TbArticles/getAllList", "anon");
        filterMap.put("/TbArticles/listNews", "anon");
        filterMap.put("/PictureArticle/list", "anon");
        filterMap.put("/PictureArticle/getInfo", "anon");
        filterMap.put("/PictureArticle/updateCount", "anon");
        filterMap.put("/PictureArticle/rotationSeeding", "anon");
        filterMap.put("/app/Feedback/savePublic", "anon");

    }
}
