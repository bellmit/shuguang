package com.sofn.sys.service.permission.impl;

import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 超级管理员权限相关
 *
 * @author heyongjie
 * @date 2019/12/20 14:32
 */
@Service
public class PermissionSuperAdminServiceImpl implements PermissionSuperAdminService {

    @Override
    public boolean isSuperman() {
        // 获取当前登录用户有的角色
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            if (loginUserRoleCodeList.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())) {
                return true;
            }
        }
        return false;
    }
}
