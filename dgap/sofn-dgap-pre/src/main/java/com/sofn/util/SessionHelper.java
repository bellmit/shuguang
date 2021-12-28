package com.sofn.util;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.invoker.MethodDispatcher;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * author tianjie
 *
 * @date 2016/10/20
 */
public class SessionHelper {

    /**
     * 获取全局的session
     * @return
     */
    public static HttpSession getSession(){
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        return  session;
    }

    public static String getUrl(){
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
        StringBuffer sb = request.getRequestURL();
        return String.valueOf(sb);
    }

    public static String getMethod(){
        Message message = PhaseInterceptorChain.getCurrentMessage();
        Exchange exchange = message.getExchange();
        BindingOperationInfo bop = exchange.get(BindingOperationInfo.class);
        MethodDispatcher md = (MethodDispatcher) exchange.get(Service.class)
                .get(MethodDispatcher.class.getName());
        Method method = md.getMethod(bop);
        return method.getName();
    }
}
