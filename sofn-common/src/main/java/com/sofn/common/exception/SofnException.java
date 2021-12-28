package com.sofn.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SofnException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public SofnException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public SofnException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public SofnException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public SofnException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public SofnException(ExceptionType exceptionType) {
		this(exceptionType.getMsg(), exceptionType.getCode());
	}

}
