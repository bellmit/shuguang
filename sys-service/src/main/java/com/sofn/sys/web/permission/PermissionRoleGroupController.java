package com.sofn.sys.web.permission;

import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.service.permission.PermissionRoleGroupService;
import com.sofn.sys.vo.SysRoleGroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色组管理
 * @author heyongjie
 * @date 2019/12/20 15:23
 */
@RestController
@RequestMapping("/permission/roleGroup")
@Api(tags = "角色组权限相关")
@Slf4j
public class PermissionRoleGroupController {

    @Autowired
    private PermissionRoleGroupService permissionRoleGroupService;

    @GetMapping("/getUserHasRoleGroup")
    @ApiOperation(value = "获取当前登录用户拥有的角色组", notes = "权限码(sys:permission:roleGroup:getUserHasRoleGroup)")
    public Result<List<SysRoleGroupVo>> getUserHasRoleGroup(){
        String loginUserId = UserUtil.getLoginUserId();
        List<SysRoleGroupVo> sysRoleGroupVos = permissionRoleGroupService.selectUserHasRoleGroup(loginUserId);
        return Result.ok(sysRoleGroupVos);
    }

    @GetMapping("/getUserHasRoleGroupByPage")
    @ApiOperation(value = "获取当前登录用户拥有的角色组并分页展示", notes = "权限码(sys:permission:roleGroup:getUserHasRoleGroupByPage)")
    public Result<PageUtils<SysRoleGroupVo>> getUserHasRoleGroupByPage(@RequestParam(required = false) @ApiParam("角色组名称")
                                                                    String groupName,
                                                            @RequestParam(value = "pageNo")  @ApiParam(value = "从哪条记录开始",required = true) Integer pageNo,
                                                            @RequestParam(value = "pageSize")  @ApiParam(value = "每页显示多少条",required = true) Integer pageSize){
        String loginUserId = UserUtil.getLoginUserId();
        PageUtils<SysRoleGroupVo> sysRoleGroupVoPageUtils = permissionRoleGroupService.selectUserHasRoleGroupByPage(loginUserId, groupName, pageNo, pageSize);
        return Result.ok(sysRoleGroupVoPageUtils);

    }

    @GetMapping("/isEdit/{roleGroupId}")
    @ApiOperation(value = "是否可以编辑角色，返回Y表示可以，N表示不可以")
    public Result<String> isEdit(@PathVariable(value = "roleGroupId") String roleGroupId){
        boolean edit = permissionRoleGroupService.isEdit(roleGroupId);
        Result<String> result = Result.ok();
        if(edit){
            result.setData(BoolUtils.Y);
        }else{
            result.setData(BoolUtils.N);
            result.setMsg("当前用户主角色组权限已经被缩小，已无权再更改当前角色组（该角色组为该用户创建）！");
        }
        return result;
    }



}
