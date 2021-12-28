package com.sofn.ducss.service.permission.impl;

import com.sofn.ducss.enums.SysManageEnum;
import com.sofn.ducss.service.permission.PermissionSuperAdminService;
import com.sofn.ducss.util.UserUtil;
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
            if (loginUserRoleCodeList.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())){

            }
        }
        return false;
    }
}
