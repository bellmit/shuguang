package com.sofn.sys.web;


import com.sofn.common.exception.ExceptionType;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.service.SysRoleGroupService;
import com.sofn.sys.vo.SysRoleGroupForm;
import com.sofn.sys.vo.SysSubsystemTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjbi
 */
@RestController
@RequestMapping("/roleGroup")
@Api(tags = "角色组相关接口")
public class SysRoleGroupController extends BaseController {

    @Autowired
    private SysRoleGroupService roleGroupService;
    @GetMapping("/roleGroupList")
    @ApiOperation(value = "分页查询角色组信息", notes = "权限:(sys:roleGroup:index)")
    @SofnLog("按条件分页获取角色组信息")
    public Result<PageUtils<SysRoleGroupForm>> queryList(@RequestParam(required = false) @ApiParam("角色组名称")
                                                     String groupName,
                                         @RequestParam(value = "pageNo")Integer pageNo,
                                         @RequestParam(value = "pageSize")Integer pageSize) {
        Map params = new HashMap<String, Object>();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("groupName",groupName);
        PageUtils<SysRoleGroupForm> pageUtils = roleGroupService.findAllGroupList(params, pageNo, pageSize);
        return Result.ok(pageUtils);
    }
//
    @GetMapping("/getGroupList")
    @ApiOperation(value = "查询所有角色组信息", notes = "权限:(sys:roleGroup:getGroupList)")
    @SofnLog("按条件分页获取角色组信息")
    public Result<List<SysRoleGroupForm>> getGroupList() {
        Map params = new HashMap<String, Object>();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        List<SysRoleGroupForm> groupList = roleGroupService.getSysRoleGroupByContion(params);
        return Result.ok(groupList);
    }

    //    @RequiresPermissions("sys:roleGroup:create")
    @ApiOperation(value = "创建角色组", notes = "权限:(sys:roleGroup:create)")
    @PostMapping("/createRoleGroup")
    @SofnLog(value = "创建角色组",type = "新增")
    public Result<String> createSysRoleGroup(@Validated @RequestBody @ApiParam(name = "角色组对象", value = "传入json格式", required = true) SysRoleGroupForm sysGroupForm, BindingResult result) {
        sysGroupForm.setId(IdUtil.getUUId());
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        // 转化为MODEL对象
        roleGroupService.createRoleGroup(sysGroupForm);
        return Result.ok();
    }

    @ApiOperation(value = "更新角色组", notes = "权限(sys:roleGroup:update)")
    @PostMapping("/updateRoleGroup")
    @SofnLog(value = "修改角色组信息",type = "修改")
    public Result<String> updateSysRoleGroup(@Validated @RequestBody @ApiParam(name = "角色组对象", value = "传入json格式", required = true)
                                             SysRoleGroupForm sysGroupForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String msg= roleGroupService.updateRoleGroup(sysGroupForm);
        return Result.ok(msg);
    }

//    @RequiresPermissions("sys:role:delete")
    @ApiOperation(value = "删除角色组", notes = "权限:(sys:roleGroup:delete)")
    @PostMapping("/deleteRoleGroup")
    @SofnLog(value = "删除角色组",type = "删除")
    public Result<String> delete(@RequestBody Map<String,String> params) {
        if (params == null || StringUtils.isBlank(params.get("id"))) {
            throw new SofnException(ExceptionType.PARAMS_ERROR);
        }

        String[] ids = StringUtils.split(params.get("id"),",");
        if(ids == null  || ids.length==0){
            return Result.error("ID不能为空");
        }

        roleGroupService.deleteRoleGroup(ids);
        return Result.ok();
    }

    @ApiOperation(value = "获取当前用户有的子系统和角色，返回树结构，第一层为子系统，第二层为角色",
            notes = "权限:(sys:roleGroup:getSubsystemHasRoleToTree)")
    @GetMapping("/getSubsystemHasRoleToTree")
    public Result<List<SysSubsystemTreeVo>> getSubsystemHasRoleToTree(){
        List<SysSubsystemTreeVo> subsystemHasRoleToTree = roleGroupService.getSubsystemHasRoleToTree();
        return Result.ok(subsystemHasRoleToTree);
    }


}
