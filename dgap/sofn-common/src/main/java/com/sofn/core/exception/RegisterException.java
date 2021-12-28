package com.sofn.core.exception;

import com.sofn.core.constant.HttpCode;

/**
 * Created by: dong4j.
 * Date: 2016-10-23.
 * Time: 20:17.
 * Description:
 */

public class RegisterException extends BaseException{
    public RegisterException() {
    }

    public RegisterException(String message) {
        super(message);
    }

    public RegisterException(String message, Exception e) {
        super(message, e);
    }
    @Override
    protected HttpCode getHttpCode() {
        return HttpCode.LOGIN_FAIL;
    }
}
