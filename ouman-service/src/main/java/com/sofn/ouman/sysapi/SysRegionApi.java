package com.sofn.ouman.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 使用Feign 调用sys服务
 */
@FeignClient(value = "sys-service", configuration = FeignConfiguration.class)
public interface SysRegionApi {


    /**
     * 根据区划代码获取代码对应的区划名称
     */
    @GetMapping("/sysRegion/getRegionNamesByCodes/{codes}")
    Result<String> getRegionNamesByCodes(@ApiParam(value = "区划代码，多个用逗号分隔") @PathVariable("codes") String codes);

    @GetMapping("/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@PathVariable(value = "id") String id);

}
