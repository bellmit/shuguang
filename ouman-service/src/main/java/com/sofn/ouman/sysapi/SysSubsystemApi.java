package com.sofn.ouman.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.ouman.vo.apivo.SysSubsystemForm;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 使用Feign 调用sys服务
 */
@FeignClient(value = "sys-service", configuration = FeignConfiguration.class)
public interface SysSubsystemApi {

    /**
     * 根据APPID获取子系统信息
     */
    @GetMapping(value = "/sysSubsystem/getSysSubsystemOneByAppId")
    Result<SysSubsystemForm> getSysSubsystemOneByAppId(@ApiParam(name = "appId",value = "子系统appId",required = true) @RequestParam(value = "appId") String appId);
}
