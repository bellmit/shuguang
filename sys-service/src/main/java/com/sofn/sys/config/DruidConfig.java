package com.sofn.sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/7/10 15:46
 */
@ConditionalOnProperty(prefix = "spring.profiles",name = "active",havingValue = "local")
@Configuration
@Slf4j
public class DruidConfig {


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }


    /**
     * 配置Druid的监控
     * 配置一个管理后台的Servlet
     * @return  ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        log.warn("druid监控配置生效，该配置只在local环境生效");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String,String> initParam = Maps.newHashMap();
        initParam.put("loginUsername","admin");
        initParam.put("loginPassword","123456");
        //默认就是允许所有访问
        initParam.put("allow","");
        //默认访问
        initParam.put("deny","");

        servletRegistrationBean.setInitParameters(initParam);
        return  servletRegistrationBean;
    }

    /**
     * 注册一个filters
     * @return  FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
