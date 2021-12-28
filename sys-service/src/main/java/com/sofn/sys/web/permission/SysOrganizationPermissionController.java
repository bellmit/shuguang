package com.sofn.sys.web.permission;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.service.permission.PermissionOrganizationService;
import com.sofn.sys.vo.SysOrgVo;
import com.sofn.sys.vo.SysOrganizationTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 机构权限相关接口
 *
 * @author heyongjie
 * @date 2019/12/18 11:34
 */
@RestController
@RequestMapping("/permission/org")
@Api(tags = "机构权限相关接口")
public class SysOrganizationPermissionController {

    @Autowired
    private PermissionOrganizationService permissionOrganizationService;

    @GetMapping("/getTreeByLoginUser")
    @ApiOperation(value = "查询当前登录用户的机构和下级机构", notes = "权限码(sys:permission:org:getTreeByLoginUser)")
    @Deprecated
    public Result<SysOrganizationTreeVo> getTreeByLoginUser(@RequestParam(required = false) @ApiParam(value = "区划ID") String regionid,
                                                            @RequestParam(required = false) @ApiParam(value = "机构名称") String orgName) {
        String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
        SysOrganizationTreeVo permissionOrganizationTree = permissionOrganizationService.getPermissionOrganizationTree(loginUserOrganizationId, regionid,orgName);
        return Result.ok(permissionOrganizationTree);
    }

    @GetMapping("/getOrgIdsByLoginUser")
    @ApiOperation(value = "查询当前登录用户的机构和下级机构的ID集合，数据权限时很有用", notes = "权限码(sys:permission:org:getOrgIdsByLoginUser)")
    public Result<List<String>> getOrgIdsByLoginUser() {
        List<String> orgIdsByLoginUser = permissionOrganizationService.getOrgIdsByLoginUser();
        return Result.ok(orgIdsByLoginUser);
    }

    @GetMapping("/getUserHaveOrgByParentIdAndOtherParam")
    @ApiOperation(value = "获取当前用户有的机构，只返回一层，如果带了除系统标识和父节点ID以外的参数不分页（返回的分页信息无效），并且不止返回一层", notes = "权限码(sys:permission:org:getUserHaveOrgByParentIdAndOtherParam)")
    public Result<PageUtils<SysOrgVo>> getUserHaveOrgByParentIdAndOtherParam(@RequestParam @ApiParam(value = "系统标识",required = true) String appId,
                                                                             @RequestParam @ApiParam(value = "父节点ID",required = true) String parentId,
                                                                             @RequestParam(required = false) @ApiParam(value = "机构名称，模糊查询") String orgName,
                                                                             @RequestParam(required = false) @ApiParam(value = "所属区划") String regionCode,
                                                                             @RequestParam(required = false) @ApiParam(value = "机构类型") String thirdOrg,
                                                                             @RequestParam(required = false) @ApiParam(value = "从哪条记录开始，分页参数任何一个没传入都不分页",required = true) Integer pageNo,
                                                                             @RequestParam(required = false) @ApiParam(value = "每页显示多少条",required = true)Integer pageSize){
        Map<String,Object> params = Maps.newHashMap();
        params.put("appId",appId);
        params.put("parentId",parentId);
        if(StringUtils.isNotBlank(orgName)){
            params.put("orgName",orgName);
        }
        if(StringUtils.isNotBlank(regionCode)){
            params.put("regionCode",regionCode);
        }
        if(StringUtils.isNotBlank(thirdOrg)){
            params.put("thirdOrg",thirdOrg);
        }
        PageUtils<SysOrgVo> userHaveOrgByParentIdAndOtherParam = permissionOrganizationService.getUserHaveOrgByParentIdAndOtherParam(params, pageNo, pageSize);
        return Result.ok(userHaveOrgByParentIdAndOtherParam);
    }


    @GetMapping("/getUserHaveOrgBaseInfoToTree")
    @ApiOperation(value = "获取当前用户有的机构的树结构，不做值的翻译操作", notes = "权限码(sys:permission:org:getUserHaveOrgBaseInfoToTree)")
    public Result<SysOrganizationTreeVo> getUserHaveOrgBaseInfoToTree(){
        SysOrganizationTreeVo userHaveOrgBaseInfoToTree = permissionOrganizationService.getUserHaveOrgBaseInfoToTree();
        return Result.ok(userHaveOrgBaseInfoToTree);
    }



}
