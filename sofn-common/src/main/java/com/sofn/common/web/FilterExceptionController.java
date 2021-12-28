package com.sofn.common.web;

import com.sofn.common.exception.ExceptionType;
import com.sofn.common.model.Result;
import com.sofn.common.utils.shiro.filter.ExceptionFilter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 过滤器异常处理
 * 因为还在过滤器中 所以并不会走全局异常处理  这里需要手动设置值
 *
 * @author heyongjie
 * @date 2019/7/22 13:23
 */
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
                log.error(ExceptionUtils.getFullStackTrace(exception));
                if (StringUtils.isEmpty(exception.getMessage())) {
                    return Result.error(ExceptionUtils.getFullStackTrace(exception));
                }
                return Result.error(exception.getMessage());
            }
        }else{
            if(obj != null){
                if(obj instanceof  Error){
                    Error error = (Error) obj;
                    log.error(error.getMessage());
                    log.error(Arrays.toString(error.getStackTrace()));
                }
                log.error(obj.toString());
            }
        }
        return Result.error("未知错误");
    }


}
