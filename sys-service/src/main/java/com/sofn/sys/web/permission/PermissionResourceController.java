package com.sofn.sys.web.permission;

import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.sys.service.permission.PermissionResourceService;
import com.sofn.sys.vo.SysResourceTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author heyongjie
 * @date 2019/12/20 15:13
 */
@RestController
@RequestMapping("/permission/resource")
@Api(tags = "菜单权限相关")
public class PermissionResourceController {

    @Autowired
    private PermissionResourceService permissionResourceService;

    @PostMapping("/getSysResourceTreeVo")
    @ApiOperation(value = "获取当前登录用户拥有的菜单树", notes = "权限码(sys:permission:resource:getSysResourceTreeVo)")
    public Result<List<SysResourceTreeVo>> getSysResourceTreeVo(@ApiParam(name = "appId",value = "AppID",required = true) @RequestParam(value = "appId") String appId,
                                                               @RequestParam(required = false) @ApiParam(value = "菜单名称") String resourceName) {
        // 为了满足之前已有的前台界面，所以这里返回List，这个List只有一个元素
        SysResourceTreeVo userHasResourceTree = permissionResourceService.getUserHasResourceTree(appId, resourceName);
        return Result.ok(Lists.newArrayList(userHasResourceTree));
    }

}
