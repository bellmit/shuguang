package com.sofn.gatewayservice.utils;

import com.netflix.zuul.context.RequestContext;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import org.springframework.http.HttpStatus;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/4/28 14:06
 *@Version 1.0
 **/
public class ExceptionUtil {

  public static void error_401(RequestContext ctx){
    // 过滤该请求，不对其进行路由
    ctx.setSendZuulResponse(false);
    //返回错误代码
    ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

    ctx.getResponse().setContentType("application/json; charset=utf-8");
    Result result = Result.error(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());
    ctx.setResponseBody(JsonUtils.obj2json(result));
  }
}
