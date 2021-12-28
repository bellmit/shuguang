package com.sofn.fdzem.feign;

import com.sofn.common.model.Result;
import com.sofn.fdzem.vo.SysUserForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:高升
 * @description：2020.05.27
 * @program:生态环境监测数据子系统
 */
@FeignClient(name = "sys-service", contextId = "Region")
public interface RegionFeign {

    @GetMapping(value = "/sysRegion/getSysRegionName/{id}")
    @ApiOperation(value = "获取省市县", notes = "根据区域代码获取省市县")
    Result<String> getSysRegionName(@ApiParam(value = "区划代码") @PathVariable(value = "id") String id);
}
