package com.sofn.sys.service;

import java.util.List;

/**
 * 角色用户Provider接口
 */
public interface SysUserRoleProvider {

    /**
     * 根据用户ID 获取用户拥有的角色id集合
     *
     * @param userId 用户id
     * @return
     */
    List<String> getRoleIdsByUserId(String userId);
}
