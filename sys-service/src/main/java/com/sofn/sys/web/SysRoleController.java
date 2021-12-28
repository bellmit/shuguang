package com.sofn.sys.web;


import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysRoleService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.vo.SysRoleForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author cjbi
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色相关接口")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysSubsystemService subsystemService;

    @ApiOperation(value = "按条件分页获取角色信息", notes = "权限(sys:role:index)")
    @GetMapping("/roleList")
    @SofnLog("按条件分页获取角色信息")
    @Deprecated
    public Result<PageUtils<SysRoleForm>> getSysRoleByPage(@RequestParam(required = false) @ApiParam("关键字(角色名/角色描述信息)") String keyword,
                                                      @RequestParam(value = "pageNo", required = false) @ApiParam(name = "pageNo", value = "分页序号") Integer pageNo,
                                                      @RequestParam(value = "pageSize", required = false) @ApiParam(name = "pageSize", value = "每页数量") Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("keyword", keyword);
        if (pageSize == null || pageNo == null || pageSize == 0 || pageNo == 0) {
            List<SysRoleForm> roleList = roleService.getSysRoleByCondition(params);
            return Result.ok(roleList);
        } else {
            PageUtils<SysRoleForm> sysRolePageUtils = roleService.getSysRoleByCondition(params, pageNo, pageSize);
            return Result.ok(sysRolePageUtils);
        }

    }

    @ApiOperation(value = "根据ID查询角色信息", notes = "权限:(sys:role:detail)")
    @PostMapping("/getOne")
    @SofnLog("根据ID获取角色信息")
    public Result<SysRoleForm> getOne(@ApiParam(name = "id", value = "角色ID", required = true) @RequestParam("id") String id) {
        SysRole sysRole = roleService.getById(id);
        SysSubsystem subsystem = subsystemService.getById(sysRole.getSubsystemId());
        SysRoleForm sysRoleForm = new SysRoleForm();
        BeanUtils.copyProperties(sysRole, sysRoleForm);
        sysRoleForm.setRoleCode(StringUtils.isBlank(sysRole.getRoleCode()) ? "" : sysRole.getRoleCode());
        sysRoleForm.setSubsystemName(subsystem.getSubsystemName());
        return Result.ok(sysRoleForm);
    }

    @ApiOperation(value = "创建角色", notes = "权限:(sys:role:create)")
    @PostMapping("/createSysRole")
    @SofnLog(value = "创建角色",type = "新增")
    public Result<String> createSysRole(@Validated @RequestBody @ApiParam(name = "角色对象", value = "传入json格式", required = true) SysRoleForm sysRoleForm, BindingResult result) {
        sysRoleForm.setId(IdUtil.getUUId());
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        roleService.createRole(sysRoleForm);
        return Result.ok();
    }

    @ApiOperation(value = "更新角色", notes = "权限(sys:role:update)")
    @PostMapping("/updateRole")
    @SofnLog(value = "修改角色信息",type = "修改")
    public Result<String> updateSysRole(@Validated @RequestBody @ApiParam(name = "角色对象", value = "传入json格式", required = true)
                                        SysRoleForm sysRoleForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        roleService.updateRole(sysRoleForm);
        return Result.ok("更新成功");
    }

    @ApiOperation(value = "批量删除角色，id使用英文逗号隔开", notes = "权限:(sys:role:delete)")
    @PostMapping("/deleteRole")
    @SofnLog(value = "删除角色",type = "删除")
    public Result<String> delete(@ApiParam(name = "id", value = "角色ID", required = true) @RequestParam(value = "id") String id) {
        // 这里前台使用的是批量删除，所以这里改为批量
        if (StringUtils.isBlank(id)) {
            return Result.error("ID不能为空");
        }
        List<String> idsByStr = IdUtil.getIdsByStr(id);
        roleService.deleteRole(idsByStr);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "通过子系统Id获取角色列表", notes = "权限:(sys:role:getUserListBySubsystemId)")
    @PostMapping("/getUserListBySubsystemId")
    @SofnLog("通过子系统Id获取角色列表")
    public Result<List<SysRole>> getUserListBySubsystemId(@ApiParam(name = "subsystemId", value = "子系统Id", required = true) @RequestParam(value = "subsystemId") String subsystemId) {
        List<SysRole> roleList = roleService.getSysRoleListBySubsystemId(subsystemId);
        return Result.ok(roleList);
    }

    @ApiOperation(value = "通过系统APPId获取角色列表", notes = "权限:(sys:role:getRoleListByAppId)")
    @PostMapping("/getRoleListByAppId")
    @SofnLog("通过系统APPId获取角色列表")
    public Result<List<SysRole>> getRoleListByAppId(@ApiParam(name = "appId", value = "系统APPId", required = true) @RequestParam(value = "appId") String appId) {
        List<SysRole> roleList = roleService.getRoleListByAppId(appId);
        return Result.ok(roleList);
    }
    


}
