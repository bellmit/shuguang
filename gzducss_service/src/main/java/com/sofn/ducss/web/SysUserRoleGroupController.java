package com.sofn.ducss.web;

import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysUserGroupService;
import com.sofn.ducss.service.SysUserService;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.vo.BasicUserInfo;
import com.sofn.ducss.vo.SaveUserAndRoleGroupRelationshipVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author heyongjie
 * @date 2020/3/2 15:15
 */
@RestController
@RequestMapping("/userRoleGroup")
@Api(tags = "用户角色组相关接口")
public class SysUserRoleGroupController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserGroupService sysUserGroupService;

    @GetMapping("/getAllUserBasicInfo")
    @ApiOperation(value = "获取所有用户",notes = "sys:userRoleGroup:getAllUserBasicInfo")
    public Result<List<BasicUserInfo>> getAllUserBasicInfo(){
        List<BasicUserInfo> allUser = sysUserService.getAllUser();
        return Result.ok(allUser);
    }


    @GetMapping("/getRoleGroupHaveUserIds")
    @ApiOperation(value = "根据角色组查询有的用户ID",notes = "sys:userRoleGroup:getRoleGroupHaveUserId")
    public Result<List<String>> getRoleGroupHaveUserId( @RequestParam @ApiParam(value = "角色组ID", required = true)String roleGroupId){
        List<String> roleGroupHaveUserIds = sysUserGroupService.getRoleGroupHaveUserIds(roleGroupId);
        return Result.ok(roleGroupHaveUserIds);
    }

    @PostMapping("/saveUserAndRoleGroupRelationship")
    @ApiOperation(value = "保存角色组和用户的关系",notes = "sys:userRoleGroup:saveUserAndRoleGroupRelationship")
    @SofnLog(value = "保存角色组和用户的关系", type = "修改")
    public Result<String> saveUserAndRoleGroupRelationship(@Validated @RequestBody SaveUserAndRoleGroupRelationshipVo saveUserAndRoleGroupRelationshipVo
            , BindingResult result){
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sysUserGroupService.saveUserAndRoleGroupRelationship(IdUtil.getIdsByStr(saveUserAndRoleGroupRelationshipVo.getUserIds()),
                saveUserAndRoleGroupRelationshipVo.getRoleGroupId());
        return Result.ok((Object) "保存成功！");
    }

}
