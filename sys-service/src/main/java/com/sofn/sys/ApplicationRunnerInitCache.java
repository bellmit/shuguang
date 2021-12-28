package com.sofn.sys;

import com.sofn.common.constants.Constants;
import com.sofn.common.utils.RedisHelper;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;


/**
 * 初始化缓存
 *
 * @author heyongjie
 * @date 2020/1/10 18:04:52
 */
@Slf4j
@Component
public class ApplicationRunnerInitCache implements ApplicationRunner {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SysRegionService sysRegionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String devPermissions = (String) redisHelper.hget(Constants.ROLE_HAVA_PERMISSION_CACHE, SysManageEnum.DEVELOPER_ROLE_ID.getCode());
        if (StringUtils.isBlank(devPermissions)) {
            log.info("初始化角色权限缓存");
            Map<String, String> roleHasPermission = sysRoleService.getRoleHasPermission();
            if (!CollectionUtils.isEmpty(roleHasPermission)) {
                roleHasPermission.forEach((k, v) -> redisHelper.hset(Constants.ROLE_HAVA_PERMISSION_CACHE, k, v));
            }
        }
        log.info("初始化区划缓存");
        getRegionCache();

    }


    private void getRegionCache() {
        sysRegionService.updateRegionRedisCache();
    }
}
