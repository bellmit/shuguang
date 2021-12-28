package com.sofn.core.interceptor;

import com.sofn.core.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 恶意请求拦截器
 *
 * @author sofn
 * @version 2016年5月20日 下午3:16:57
 */
public class MaliciousRequestInterceptor extends BaseInterceptor {
	/**
	 * The All request.
	 */
	private Boolean allRequest = false; // 拦截所有请求,否则拦截相同请求
	/**
	 * The Min request interval time.
	 */
	private Long minRequestIntervalTime; // 允许的最小请求间隔
	/**
	 * The Max malicious times.
	 */
	private Integer maxMaliciousTimes; // 允许的最大恶意请求次数

	// 关闭 session
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String preRequest = (String) session.getAttribute(Constants.PREREQUEST);
		Long preRequestTime = (Long) session.getAttribute(Constants.PREREQUEST_TIME);
		String url = request.getServletPath();
		//if (preRequestTime != null && preRequest != null) { // 过滤频繁操作
		//	if ((url.equals(preRequest) || allRequest)
		//			&& System.currentTimeMillis() - preRequestTime < minRequestIntervalTime) {
		//		Integer maliciousRequestTimes = (Integer) session.getAttribute(Constants.MALICIOUS_REQUEST_TIMES);
		//		if (maliciousRequestTimes == null) {
		//			maliciousRequestTimes = 1;
		//		} else {
		//			maliciousRequestTimes++;
		//		}
		//		session.setAttribute(Constants.MALICIOUS_REQUEST_TIMES, maliciousRequestTimes);
		//		if (maliciousRequestTimes > maxMaliciousTimes) {
		//			response.setStatus(HttpCode.MULTI_STATUS.value());
		//			logger.warn("To intercept a malicious request : {}", url);
		//			return false;
		//		}
		//	} else {
		//		session.setAttribute(Constants.MALICIOUS_REQUEST_TIMES, 0);
		//	}
		//}
		session.setAttribute(Constants.PREREQUEST, url);
		session.setAttribute(Constants.PREREQUEST_TIME, System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	/**
	 * Sets all request.
	 *
	 * @param allRequest the all request
	 */
	public void setAllRequest(Boolean allRequest) {
		this.allRequest = allRequest;
	}

	/**
	 * Sets min request interval time.
	 *
	 * @param minRequestIntervalTime the min request interval time
	 */
	public void setMinRequestIntervalTime(Long minRequestIntervalTime) {
		this.minRequestIntervalTime = minRequestIntervalTime;
	}

	/**
	 * Sets max malicious times.
	 *
	 * @param maxMaliciousTimes the max malicious times
	 */
	public void setMaxMaliciousTimes(Integer maxMaliciousTimes) {
		this.maxMaliciousTimes = maxMaliciousTimes;
	}
}
