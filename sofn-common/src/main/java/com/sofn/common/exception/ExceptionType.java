package com.sofn.common.exception;

/**
 * 异常类型
 */
public enum ExceptionType {

    UNKNOWN_ERROR(1000,"未知错误"),
    USER_NOT_EXIST(1001,"用户不存在"),
    USER_EXIST(1004,"用户名重复"),
    PASSWORD_ERROR(1002,"用户密码错误"),
    NO_PERMISSION(1003,"暂无权限"),
    TOKEN_EXPIRED(401,"token过期"),
    // 前端方便统一处理，未登录也返回401
    NOT_LOGIN(401,"未登录"),
    CAPTCHA_CORRECT(1006,"验证码不正确"),
    CAPTCHA_NOT_INPUT(1007,"请输入验证码"),
    PARAMS_ERROR(1008,"参数错误"),
    CACHE_ERROR(1009,"缓存错误");

    private String msg;
    private int code;

    ExceptionType(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static String getMsg(int code) {
        for (ExceptionType c : ExceptionType.values()) {
            if (c.getCode() == code) {
                return c.msg;
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
