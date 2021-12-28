package com.sofn.gatewayservice.api;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: 系统支撑平台api
 * @Author: quzhijie
 * @Date: 2020/4/28 14:20
 **/
@FeignClient(
       value = "sys-service",
       configuration = FeignConfiguration.class
)
public interface SysApi {

   @GetMapping(value ="/user/replaceToken", consumes = MediaType.APPLICATION_JSON_VALUE)
   Result<String> replaceToken();

}
