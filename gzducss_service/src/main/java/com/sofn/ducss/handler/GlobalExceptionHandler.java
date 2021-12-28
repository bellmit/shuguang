package com.sofn.ducss.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sofn.ducss.excel.exception.ExcelException;
import com.sofn.ducss.exception.ExceptionType;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.model.basemodel.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseBody
    public Result unauthorizedHandler(UnauthorizedException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(ExceptionType.NO_PERMISSION);
    }

    @ExceptionHandler()
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Result unauthenticatedHandler(UnauthenticatedException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(ExceptionType.NOT_LOGIN);
    }

    @ExceptionHandler()
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Result tokenExpiredHandler(TokenExpiredException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(ExceptionType.TOKEN_EXPIRED);
    }

    @ExceptionHandler()
    @ResponseBody
    public Result sofnExceptionHandler(SofnException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler()
    @ResponseBody
    public Result paramsExceptionHandler(MissingServletRequestParameterException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(e.getMessage());
    }

    /**
     * 单个参数校验
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result singleViolationExceptionHandler(ConstraintViolationException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        StringBuffer sb = new StringBuffer();
        //遍历获取消息
        for(ConstraintViolation violation : e.getConstraintViolations()){
            //获取异常消息，暂定不抛全部
            sb.append(violation.getMessage());
            return Result.error(sb.toString());
        }
        return Result.error(ExceptionType.PARAMS_ERROR);
    }

    /**
     * vo数据校验
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result voViolationExceptionHandler(MethodArgumentNotValidException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler()
    public Result runtimeExceptionHandler(RuntimeException e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        if (StringUtils.isEmpty(e.getMessage())) {
            return Result.error(ExceptionType.UNKNOWN_ERROR);
        } else {
            return Result.error(e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ExcelException.class)
    public Result<String> excelException(ExcelException e){
        log.error(ExceptionUtils.getFullStackTrace(e));
        return Result.error(e.getMessage());
    }
}
