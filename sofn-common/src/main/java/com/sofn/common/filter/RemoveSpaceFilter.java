package com.sofn.common.filter;

import com.sofn.common.utils.ParameterRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author heyongjie
 * @date 2020/3/9 17:23
 */
@ConditionalOnProperty(prefix = "sofn.enableRemoveSpace", name = "enable", havingValue = "true")
@WebFilter(urlPatterns="/*" ,filterName="removeSpaceFilter")
@Configuration
@Slf4j
public class RemoveSpaceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("============================================");
        log.info("||                                        ||");
        log.info("||          去除前后空格过滤器启动成功        ||");
        log.info("||         将过滤所有请求参数的前后空格       ||");
        log.info("============================================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //只是我们自己写的param参数去除空格并写回的类
        log.info("请求参数前后空格将被移除！");
        ParameterRequestWrapper parmsRequest = new ParameterRequestWrapper(
                (HttpServletRequest) servletRequest);

        filterChain.doFilter(parmsRequest, servletResponse);
    }
}
