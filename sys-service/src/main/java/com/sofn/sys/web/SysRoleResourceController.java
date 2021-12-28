package com.sofn.sys.web;


import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysRoleResource;
import com.sofn.sys.service.RoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luoyh
 */
@RestController
@RequestMapping("/roleResource")
@Api(tags = "角色和权限相关接口")
public class SysRoleResourceController extends BaseController {

    @Autowired
    private RoleResourceService roleResourceService;


    //给角色取消授权
    //param:roleId,resourceIds
    @ApiOperation(value="给角色取消授权" ,notes = "权限:(sys:roleResource:cancelPermission)")
    @PostMapping("/cancelPermissionForRole")
    @SofnLog(value = "角色取消授权",type = "删除")
    public Result<String> cancelPermissionForRole(@ApiParam(name ="roleId",value="角色ID",required = true)
                                       @RequestParam(value = "roleId") String roleId,@ApiParam(required = true ,value = "待删除IDS，ID用英文,号分隔")
                                       @RequestParam(value = "ids") String ids){

        if(roleId == null  || "".equals(roleId)){
            return Result.error("角色ID不能为空");
        }
        roleResourceService.cancelPermissionForRole(roleId,ids);
        return Result.ok();

    }
    //给角色添加权限
    //param:roleId,resourceIds
    @ApiOperation(value="给角色添加权限" ,notes = "权限:(sys:roleResource:addPermission)")
    @PostMapping("/addPermissionForRole")
    @SofnLog(value = "角色添加权限",type = "新增")
    public Result<String> addPermissionForRole(@ApiParam(name ="roleId",value="角色ID",required = true)
    @RequestParam(value = "roleId") String roleId,@ApiParam(value = "待删除IDS，ID用英文,号分隔",required = true)
    @RequestParam(value = "ids") String ids){

        if(roleId == null  || "".equals(roleId)){
            return Result.error("角色ID不能为空");
        }
        roleResourceService.addPermissionForRole(roleId,ids);
        return Result.ok();

    }

    //获取角色所有权限
    @ApiOperation(value="获取角色所有权限" ,notes = "权限:(sys:roleResource:findPermission)")
    @GetMapping("/findPermissionForRole")
    @SofnLog("获取角色所有权限")
    public Result<List<SysRoleResource>> findPermissionForRole(@ApiParam(name ="roleId",value="角色ID",required = true)
                                       @RequestParam(value = "roleId") String roleId){

        if(roleId == null  || "".equals(roleId)){
            return Result.error("角色ID不能为空");
        }
        List<SysRoleResource> ssrList= roleResourceService.findPermissionForRole(roleId);
        return Result.ok(ssrList);

    }

}
