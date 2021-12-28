package com.sofn.ahhdp.sysapi;

import com.sofn.ahhdp.vo.SysDict;
import com.sofn.ahhdp.vo.SysRegionForm;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 使用Feign 调用sys服务
 *
 * @author chenlf
 * @date 2019/9/16 14:26
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysRegionApi {

    @ApiOperation(value = "根据行政区划名称获取区划", notes = "权限码(sys:region:regionbyname)")
    @GetMapping(value = "/sysRegion/getSysRegionByName")
    Result<SysRegionForm> getSysRegionByName(@ApiParam(value = "行政区划名称", required = true) @RequestParam(value = "regionName") String regionName);

    @GetMapping("/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@PathVariable(value = "id") String id);

    @GetMapping(value = "/dict/getDictListByType")
    Result<List<SysDict>> getDictListByType(@ApiParam(required = true, value = "字典类型值") @RequestParam(value = "typevalue") String typevalue);

}
