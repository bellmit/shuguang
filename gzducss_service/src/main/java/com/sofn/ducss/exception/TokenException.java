package com.sofn.ducss.exception;

public class TokenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 401;

    public TokenException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TokenException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public TokenException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public TokenException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
