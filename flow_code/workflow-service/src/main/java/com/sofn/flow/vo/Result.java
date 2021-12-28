package com.sofn.flow.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * api接口返回结果封装对象
 * @author sofn
 */
@SuppressWarnings("ALL")
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer CODE = 200;

    private Integer code;

    private String msg = "";

    private T data;

    public Result() {

    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, T result) {
        this.code = code;
        this.data = result;
    }

    public Result(Integer code, String msg, T result) {
      this.code = code;
      this.msg = msg;
      this.data = result;
    }

    public Result(T result) {
        this.data = result;
    }

    public static Result error() {
        return error(500, "未知异常");
    }

    public static <T> Result<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> Result<T> error(Object result) {
        return new Result(500, result);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static <T> Result<T> ok(String msg) {
        return new Result(CODE, msg);
    }

    public static <T> Result<T> ok(Object data, String msg) {
      return new Result(CODE, msg, data);
    }

    public static <T> Result<T> ok(Object result) {
        return new Result(CODE, result);
    }

    public static <T> Result<T> ok() {
        return new Result(CODE);
    }

    /**
     * 未登录异常
     * @param <T>
     * @return
     */
    public static <T> Result<T> noLogin(){return new Result(401,"token过期"); }

}
