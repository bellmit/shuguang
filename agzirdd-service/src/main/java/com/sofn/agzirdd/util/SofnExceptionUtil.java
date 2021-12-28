package com.sofn.agzirdd.util;

import com.sofn.common.exception.SofnException;

/**
 * 抛出异常工具类
 */
public class SofnExceptionUtil {

    public static void throwError(String errMsg){
        throw new SofnException(errMsg);
    }
}
