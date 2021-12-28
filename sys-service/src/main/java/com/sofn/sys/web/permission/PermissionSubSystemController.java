package com.sofn.sys.web.permission;

import com.sofn.common.model.Result;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.vo.SysSubsystemForm;
import com.sofn.sys.vo.SysSubsystemTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author heyongjie
 * @date 2019/12/20 15:27
 */
@RestController
@RequestMapping("/permission/subSystem")
@Api(tags = "子系统权限相关")
public class PermissionSubSystemController {

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    @GetMapping("/getUserHasSubsystem")
    @ApiOperation(value = "获取当前用户有的子系统", notes = "权限码(sys:permission:subSystem:getUserHasSubsystem)")
    public Result<SysSubsystemTreeVo> getUserHasSubsystem(@ApiParam(name = "subsystemName",value = "子系统名称") @RequestParam(value = "subsystemName",required = false) String subsystemName) {
        SysSubsystemTreeVo userHasSubsystem = permissionSubSystemService.getUserHasSubsystem(subsystemName);
        return Result.ok(userHasSubsystem);
    }


    @GetMapping("/getUserHaveSystemList")
    @ApiOperation(value = "获取当前用户有的子系统列表，不返回Tree", notes = "权限码(sys:permission:subSystem:getUserHaveSystemList)")
    public Result<List<SysSubsystemForm>> getUserHaveSystemList(){
        List<SysSubsystemForm> userHaveSystemList = permissionSubSystemService.getUserHaveSystemList();
        return Result.ok(userHaveSystemList);
    }

}
