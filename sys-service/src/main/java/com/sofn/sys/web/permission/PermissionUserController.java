package com.sofn.sys.web.permission;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.service.permission.PermissionUserService;
import com.sofn.sys.vo.SysUserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户权限相关
 *
 * @author heyongjie
 * @date 2019/12/25 10:44
 */
@RestController
@RequestMapping("/permission/user")
@Api(tags = "用户权限相关接口")
public class PermissionUserController {

    @Autowired
    private PermissionUserService permissionUserService;

    @GetMapping("/userList")
    @ApiOperation(value = "分页查询用户信息", notes = "权限:(sys:permission:user:queryList)")
    @SofnLog("按条件分页获取用户信息")
    public Result<PageUtils<SysUserForm>> queryList(@RequestParam(required = false,value = "status") @ApiParam("状态") String status,
                                                    @RequestParam(required = false,value = "keyword") @ApiParam("关键字(用户名/昵称/邮箱/手机号)") String keyword,
                                                    @RequestParam(required = false,value = "organizationId") @ApiParam("机构ID")  String organizationId,
                                                    @RequestParam(value = "pageNo") @ApiParam(value = "从哪条记录开始", required = true) Integer pageNo,
                                                    @RequestParam(value = "pageSize") @ApiParam(value = "每页显示多少条", required = true) Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("status", status);
        params.put("keyword", keyword);
        params.put("organizationId",organizationId);
        PageUtils<SysUserForm> pageUtils = permissionUserService.getLoginUserCanShowUser(pageNo, pageSize, params);
        return Result.ok(pageUtils);
    }
}
