package com.sofn.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器基类
 * 
 * @author sofn
 * @version 2016年5月20日 下午3:16:31
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private BaseInterceptor[] nextInterceptor;

	public void setNextInterceptor(BaseInterceptor... nextInterceptor) {
		this.nextInterceptor = nextInterceptor;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (nextInterceptor == null) {
			return true;
		}
		for (BaseInterceptor aNextInterceptor : nextInterceptor) {
			if (!aNextInterceptor.preHandle(request, response, handler)) {
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (nextInterceptor != null) {
			for (BaseInterceptor aNextInterceptor : nextInterceptor) {
				aNextInterceptor.postHandle(request, response, handler, modelAndView);
			}
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (nextInterceptor != null) {
			for (BaseInterceptor aNextInterceptor : nextInterceptor) {
				aNextInterceptor.afterCompletion(request, response, handler, ex);
			}
		}
	}

	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (nextInterceptor != null) {
			for (BaseInterceptor aNextInterceptor : nextInterceptor) {
				aNextInterceptor.afterConcurrentHandlingStarted(request, response, handler);
			}
		}
	}
}
