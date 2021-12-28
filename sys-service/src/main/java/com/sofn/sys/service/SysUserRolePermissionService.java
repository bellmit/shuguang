package com.sofn.sys.service;

import java.util.Map;
import java.util.Set;

/**
 * 用户角色权限相关接口
 * @author heyongjie
 * @date 2019/7/24 9:15
 */
public interface SysUserRolePermissionService {

    /**
     * 根据用户ID和子系统名称返回当前角色有的权限
     * @param userId  用户Id
     * @param subSystemAppId  子系统AppId
     * @return  用户拥有的权限信息  Map的key分为 menu 和 button  分别代表菜单和按钮
     */
    Map<String, Set<String>> getUserHasPermission(String userId, String subSystemAppId);


}
