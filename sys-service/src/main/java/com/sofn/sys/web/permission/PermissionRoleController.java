package com.sofn.sys.web.permission;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SystemRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色权限Controller
 * @author heyongjie
 * @date 2019/12/20 15:18
 */
@RestController
@RequestMapping("/permission/role")
@Api(tags = "角色权限")
public class PermissionRoleController {

    @Autowired
    private PermissionRoleService permissionRoleService;

    @GetMapping("/getUserHasRole")
    @ApiOperation(value = "获取当前用户有的角色，包含角色组中角色",notes="权限码(sys:permission:role:getUserHasRole)")
    public Result<List<SysRoleVo>> getUserHasRole(){
        String loginUserId = UserUtil.getLoginUserId();
        List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRole(loginUserId,true);
        return Result.ok(loginUserHaveRole);
    }

    @GetMapping("/getUserHasRoleByPage")
    @ApiOperation(value = "分页获取当前用户有的角色，不包含角色组中的角色",notes="权限码(sys:permission:role:getUserHasRoleByPage)")
    public Result<PageUtils<SysRoleVo>> getUserHasRoleByPage(@RequestParam(required = false) @ApiParam("关键字(角色名/角色描述信息)") String keyword,
                                                             @RequestParam(required = false) @ApiParam("子系统ID")String subsystemId,
                                                             @RequestParam(value = "pageNo", required = false) @ApiParam(name = "pageNo", value = "分页序号") Integer pageNo,
                                                             @RequestParam(value = "pageSize", required = false) @ApiParam(name = "pageSize", value = "每页数量") Integer pageSize){
        Map<String,Object> params = Maps.newHashMap();
        if(!StringUtils.isBlank(keyword)){
            params.put("keyword",keyword);
        }
        if(!StringUtils.isBlank(subsystemId)){
            params.put("subsystemId",subsystemId);
        }
        PageUtils<SysRoleVo> loginUserByPage = permissionRoleService.getLoginUserByPage(params, pageNo, pageSize);
        return Result.ok(loginUserByPage);
    }

    @GetMapping("/isEdit/{roleId}")
    @ApiOperation(value = "是否可以编辑角色，返回Y表示可以，N表示不可以")
    public Result<String> isEdit(@PathVariable(value = "roleId") String roleId){
        boolean edit = permissionRoleService.isEdit(roleId);
        Result<String> result = Result.ok();
        if(edit){
            result.setData(BoolUtils.Y);
        }else{
            result.setData(BoolUtils.N);
            result.setMsg("当前用户主角色权限已经被缩小，已无权再更改当前角色（该角色为当前用户创建）！");
        }

        return result;
    }

    @GetMapping("/getRoleAndSystemInfoByPage")
    @ApiOperation(value = "查询角色和系统信息，可按照系统名称和角色名称搜索")
    public Result<PageUtils<SystemRoleVo>> getRoleAndSystemInfoByPage(@RequestParam(required = false) @ApiParam("关键字(系统名称或者角色名称或者角色代码或者系统代码)")String keyword,
                                                                      @RequestParam(required = false) @ApiParam("角色ID，多个用逗号隔开")String roleIds,
                                                                      @RequestParam(value = "pageNo", required = false)Integer pageNo,
                                                                      @RequestParam(value = "pageSize", required = false)Integer pageSize){
        PageUtils<SystemRoleVo> roleAndSystemInfoByPage = permissionRoleService.getRoleAndSystemInfoByPage(keyword,roleIds, pageNo, pageSize);
        return Result.ok(roleAndSystemInfoByPage);

    }

    

}
