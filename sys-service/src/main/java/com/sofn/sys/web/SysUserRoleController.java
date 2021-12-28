package com.sofn.sys.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.web.BaseController;
import com.sofn.sys.service.SysUserRoleService;
import com.sofn.sys.vo.UpdateUserAndRoleRelationshipVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户角色关系接口
 * Created by Administrator on 2019\7\16 0016.
 */
@RestController
@RequestMapping("/userRole")
@Api(tags = "用户角色相关接口")
public class SysUserRoleController extends BaseController {


    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * 根据用户管理角色
     */
    @ApiOperation(value = "根据用户管理角色", notes = "权限: sys:userRole:update")
    @PostMapping("/updateByUserId")
    @SofnLog(value = "根据用户管理角色",type = "修改")
    public Result updateUserRoleByUserId(
                                @RequestParam @ApiParam(value = "用户Id", required = true)String userId,
                                @RequestParam(required = false) @ApiParam(value = "关联角色Ids")String roleIds
                                ){

        //将角色字符串以','隔开转换为list集合
        List<String> idsByRoleId = IdUtil.getIdsByStr(roleIds, ",");
        sysUserRoleService.updateRelationshipByUserId(idsByRoleId,userId);
        return Result.ok("更新成功!");
    }


    /**
     * 根据角色管理用户
     */
    @ApiOperation(value = "根据角色管理用户", notes = "权限: sys:userRole:update")
    @PostMapping("/updateByRoleId")
    @SofnLog(value = "根据角色管理用户", type = "修改")
    public Result updateUserRoleByRoleId(@Validated @RequestBody UpdateUserAndRoleRelationshipVo updateUserAndRoleRelationshipVo, BindingResult result){
        String errMsg = this.getErrMsg(result);
        if (!StringUtils.isEmpty(errMsg)) {
            return Result.error(errMsg);
        }
        //将用户字符串以','隔开转换为list集合
        List<String> idsByUserId = IdUtil.getIdsByStr(updateUserAndRoleRelationshipVo.getUserIds());
        sysUserRoleService.updateRelationshipByRoleId(idsByUserId,updateUserAndRoleRelationshipVo.getRoleId());
        return Result.ok("更新成功!");

    }
}
