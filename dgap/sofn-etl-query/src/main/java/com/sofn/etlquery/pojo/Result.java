package com.sofn.etlquery.pojo;

public class Result<T> {

    //消息
    private String msg;
    //状态码
    private Integer code;
    //结果数量
    private Integer resultCount;
    //结果集
    private T res;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", res=" + res +
                ", resultCount=" + resultCount +
                '}';
    }

    public Result setError(Result result,String log,int count,T t){
        result.setMsg(log);
        result.setCode(500);
        result.setResultCount(count);
        result.setRes(t);
        return result;
    }
    public Result setSuccessec(Result result,String log,int count,T t){
        result.setMsg(log);
        result.setCode(200);
        result.setResultCount(count);
        result.setRes(t);
        return result;
    }
}
