package com.sofn.sys.service.permission;

/**
 *  超级管理员权限
 *
 * @author heyongjie
 * @date 2019/12/20 14:19
 */
public interface PermissionSuperAdminService {

    /**
     * 是否是超级管理员
     * @return   true  是
     *           false 否
     */
    boolean isSuperman();

}
