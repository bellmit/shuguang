package com.sofn.fdzem.feign;

import com.sofn.common.model.Result;
import com.sofn.fdzem.vo.SysUserForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:袁彬峰
 * @description：2020.04.03
 * @program:生态环境监测数据子系统
 */
@FeignClient(name="sys-service",contextId="user")
public interface UserFeign {

    @GetMapping(value = "/user/getUserInfoByUsername", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取用户信息",notes = "通过用户名获取用户信息")
    Result<SysUserForm> getUserInfoByUsername(@RequestParam(value = "username", required = true) String username);
}
