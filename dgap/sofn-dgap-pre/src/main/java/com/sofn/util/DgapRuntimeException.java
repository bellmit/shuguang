package com.sofn.util;

/**
 * author tianjie
 *
 * @date 2016/10/21
 */
public class DgapRuntimeException extends RuntimeException {

    private String errorCode;

    private String innerDesc;

    private String outDesc;

    public DgapRuntimeException(String errorCode, String innerDesc, String outDesc){
        this.errorCode = errorCode;
        this.innerDesc = innerDesc;
        this.outDesc = outDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getInnerDesc() {
        return innerDesc;
    }

    public String getOutDesc() {
        return outDesc;
    }
}
