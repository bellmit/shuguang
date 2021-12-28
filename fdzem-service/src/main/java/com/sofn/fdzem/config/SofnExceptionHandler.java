package com.sofn.fdzem.config;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenbo
 * @description SofnException统一处理
 * @date 2021.3.30
 */
@RestControllerAdvice
@Slf4j
public class SofnExceptionHandler {

    /**
     * 拦截捕捉自定义异常 SofnException.class
     */
    @ExceptionHandler(value = SofnException.class)
    public Result<?> errorHandler(SofnException ex) {
        if (ex.getCause() != null) {
            log.info("异常被拦截", ex);
        }
        return Result.error(ex.getMessage());
    }

}
