package com.sofn.common.utils.shiro.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

/**
 * 过滤器中的异常捕获过滤器
 * ##因为shiro等过滤器中的异常不会被全局异常处理器捕获##
 * @author heyongjie
 * @date 2019/7/22 13:19
 */
@Slf4j
public class ExceptionFilter implements Filter {

    public static final String FILTER_EXCEPTION_NAME = "filter_exception";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ex filter init .......");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 是否已经放有异常栈, 避免循环
        boolean isRethrow = !Objects.isNull(request.getAttribute(FILTER_EXCEPTION_NAME));
        if (isRethrow) {
            chain.doFilter(request, response);
            return;
        }
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if(e instanceof ServletException){
                ServletException serverException = (ServletException)e;
                Throwable throwable =  serverException.getRootCause();
                request.setAttribute(FILTER_EXCEPTION_NAME, throwable);
            }else{
                // 发生异常，保存异常栈
                request.setAttribute(FILTER_EXCEPTION_NAME, e);
            }
            // 请求转发到过滤器异常处理中  FilterExceptionController
            request.getRequestDispatcher("/error/rethrow").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
