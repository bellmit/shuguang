package com.sofn.model.dgap;

/**
 * Created by helong on 17-1-17.
 */
public interface ErrorSupport {
    boolean isSuccess();

    void setSuccess(boolean success);

    String getErrorCode();

    void setErrorCode(String errorCode);

    String getErrorDesc();

    void setErrorDesc(String errorDesc);
}
