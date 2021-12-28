package com.sofn.core.util;

/**
 * Created by Liw on 2017/4/5.
 * 消息结果类
 */
public class Result {
    private boolean isSuccess = true;
    private String msg ;
    private Object data;
    public Result(String msg, boolean isSuccess) {
        this.isSuccess = isSuccess ;
        this.msg = msg ;
    }
    public Result(String msg, boolean isSuccess, Object data) {
        this.isSuccess = isSuccess ;
        this.msg = msg ;
        this.data = data ;
    }

    public Result() {
    }

    public static Result setFailMsg(String msg){
        Result result = new Result();
        result.isSuccess = false ;
        result.msg = msg ;
        return result ;
    }
    public static Result setSuccessData(Object data){
        Result result = new Result();
        result.isSuccess = true ;
        result.data = data ;
        return result ;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
