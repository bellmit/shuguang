package com.sofn.sys.web;

import com.sofn.common.constants.Constants;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.web.BaseController;
import com.sofn.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 重新设置缓存
 *
 * @author heyongjie
 * @date 2020/4/23 15:27
 */
@RestController
@Api(tags = "重新设置缓存")
@RequestMapping("/initCache")
@Slf4j
public class SetCacheController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RedisHelper redisHelper;

    @GetMapping("/setRoleHavePermissionCache")
    @ApiOperation(value = "重新设置角色权限缓存，这个缓存对于权限相当重要", notes = "sys:initcache:setRoleHavePermissionCache")
    @SofnLog(value = "重新设置角色权限缓存，这个缓存对于权限相当重要", type = "修改")
    public Result<String> setRoleHavePermissionCache() {
        log.info("初始化角色权限缓存");
        Map<String, String> roleHasPermission = sysRoleService.getRoleHasPermission();
        if (!CollectionUtils.isEmpty(roleHasPermission)) {
            roleHasPermission.forEach((k, v) -> redisHelper.hset(Constants.ROLE_HAVA_PERMISSION_CACHE, k, v));
        }
        return Result.ok("重新设置" + Constants.ROLE_HAVA_PERMISSION_CACHE + "成功");
    }

}
