package com.sofn.ducss.web;

import com.sofn.ducss.exception.ExceptionType;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.util.shiro.filter.ExceptionFilter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@Api(hidden = true)
public class FilterExceptionController {
    @Resource
    private HttpServletRequest request;

    /**
     * 重新抛出异常
     */
    @RequestMapping("/error/rethrow")
    public Result rethrow(HttpServletResponse response) {
        Object obj =  request.getAttribute(ExceptionFilter.FILTER_EXCEPTION_NAME) ;
        if(obj instanceof Exception ){
            Exception exception = (Exception)obj ;
            exception.printStackTrace();
            Throwable throwable = exception.getCause();
            // 这里主要做得是过滤器出错后抛出异常判断，如果是后台接口会走全局异常捕获
            if (throwable instanceof UnauthorizedException || exception instanceof UnauthorizedException) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return Result.error(ExceptionType.TOKEN_EXPIRED);
            } if(throwable instanceof UnauthenticatedException || exception instanceof  UnauthenticatedException){
                log.error("Authorization not exists");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return Result.error(ExceptionType.NOT_LOGIN);
            } else {
                log.error(exception.getMessage());
                return Result.error(ExceptionType.UNKNOWN_ERROR);
            }
        }else{
            if(obj != null){
                if(obj instanceof  Error){
                    Error error = (Error) obj;
                    log.error(error.getMessage());
                    log.error(error.getStackTrace().toString());
                }
                log.error(obj.toString());
            }
        }
        return Result.error("未知错误");
    }


}