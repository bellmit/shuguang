package com.sofn.ouman.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.ouman.vo.apivo.SysRole;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 使用Feign 调用sys服务
 */
@FeignClient(value = "sys-service", configuration = FeignConfiguration.class)
public interface SysRoleApi {


    /**
     * 通过子系统Id获取角色列表
     *
     * @param subsystemId
     * @return
     */
    @PostMapping("/role/getUserListBySubsystemId")
    @SofnLog("通过子系统Id获取角色列表")
    Result<List<SysRole>> getUserListBySubsystemId(@ApiParam(name = "subsystemId", value = "子系统Id", required = true) @RequestParam(value = "subsystemId") String subsystemId);


}
