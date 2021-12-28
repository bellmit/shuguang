package com.sofn.core.exception;

import com.sofn.core.constant.HttpCode;

/**
 * @author sofn
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	private HttpCode httpCode;
	public BusinessException() {
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}

	public BusinessException(String message, HttpCode httpCode){
		super(message);
		this.httpCode = httpCode;
	}

	public BusinessException(HttpCode httpCode){
		this.httpCode = httpCode;
	}
	protected HttpCode getHttpCode() {
		return httpCode;
	}
}