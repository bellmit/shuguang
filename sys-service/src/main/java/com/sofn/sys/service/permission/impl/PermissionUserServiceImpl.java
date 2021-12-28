package com.sofn.sys.service.permission.impl;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.service.permission.PermissionUserService;
import com.sofn.sys.vo.SysUserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 用户权限相关
 *
 * @author heyongjie
 * @date 2019/12/25 10:26
 */
@Service
@Slf4j
public class PermissionUserServiceImpl implements PermissionUserService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PermissionSuperAdminService superAdminService;


    @Autowired
    private PermissionSubSystemService permissionSubSystemService;


    @Override
    public PageUtils<SysUserForm> getLoginUserCanShowUser(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        log.info("开始查询用户");
        if (CollectionUtils.isEmpty(params)) {
            params = Maps.newHashMap();
        }

        boolean superman = superAdminService.isSuperman();
        if (!superman) {
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            if (sysOrganization != null) {
                // 获取用户系统
                List<String> userAppIds = permissionSubSystemService.getUserAppIds();
                if (CollectionUtils.isEmpty(userAppIds)) {
                    log.error("当前登录用户{}", UserUtil.getLoginUserId());
                    throw new SofnException("当前用户不属于任何系统");
                }
                params.put("appIds", userAppIds);
                params.put("limitOrg", BoolUtils.Y);
                params.put("carryChildren", BoolUtils.Y);
                params.put("carryProxyOrg", BoolUtils.Y);
                params.put("orgId",sysOrganization.getId());
            }
            params.put("userId",UserUtil.getLoginUserId());
        } else {
            log.info("超级管理员显示所有数据");
        }
        log.info("真实查询用户");
        return sysUserService.findAllUserList(params, pageNo, pageSize);
    }

}
