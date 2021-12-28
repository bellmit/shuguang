package com.sofn.common.config;

import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.shiro.JWTFilter;
import com.sofn.common.utils.shiro.UserRealm;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 基础Shiro配置类，子系统需继承添加注解
 */
@Configuration
public abstract class BaseShiroConfig {

    @Autowired
    YmlConfig ymlConfig;

    @Autowired
    UriConfig uriConfig;

    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setGlobalSessionTimeout(-1000L);

        return sessionManager;
    }


    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        if (getSysAuthEnable()){
          userRealm.enableSysUserAuth();
        }
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(null);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, Filter> filters = shiroFilter.getFilters();
        filters.put("jwt", new JWTFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        // swagger 不需要进行权限拦截
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/doc.html**", "anon");
        filterMap.put("/service-worker.js", "anon");
        filterMap.putAll(getSettingConfig());
        filter(filterMap);
        //filterMap.put("/**", "authc");
        filterMap.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        //proxyCreator.setUsePrefix(true);
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    private Map<String, String> getSettingConfig(){
        Map<String, String> filterSetting = new HashMap<>();
        String appId;
        if (StringUtils.isNotBlank(ymlConfig.appId)) {
          appId = ymlConfig.appId;
        }else if (StringUtils.isNotBlank(ymlConfig.appName)){
          appId = ymlConfig.appName.substring(0,ymlConfig.appName.indexOf("-"));
        }else {
          appId = "";
        }

        if (StringUtils.isNotBlank(appId) && uriConfig != null && !CollectionUtils.isEmpty(uriConfig.getUris())){
          List<String> urlList = uriConfig.getUris().get(appId);
          if (!CollectionUtils.isEmpty(urlList)){
            urlList.forEach(url -> {
              if (StringUtils.isBlank(url)){
                return;
              }
              filterSetting.put(url,"anon");
            });
          }
        }

        return filterSetting;
    }

    protected boolean getSysAuthEnable(){
      return true;
    }

    /**
     * shiro过滤方法
     */
    protected abstract void filter(Map<String, String> filterMap);

}
