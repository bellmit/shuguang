package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * 用户角色相关服务
 * Created by heyongjie on 2019/6/11 9:59
 */
public interface SysUserRoleService  extends IService<SysUserRole> {

    /**
     * 根据角色ID查询角色的名字
     * @param roleIds    List<String>
     * @return   roleName,roleName,roleName,...
     */
    String getRoleName(List<String> roleIds);


    /**
     * 根据用户ID查询当前用户有的角色信息
     * @param userId
     * @return
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 根据角色ID查询当前角色有哪些用户
     * @param roleId
     * @return
     */
    Set<String> getUserIdByRoleId(String roleId);

    /**
     * 更改用户对应的角色关系
     * @param roleIds List<String>  更新后的角色信息
     * @param  userId  要更改的用户
     */
    void updateRelationshipByUserId(List<String> roleIds,String userId);

    /**
     * 更改角色对应的用户关系
     * @param userIds List<String> 更新后的用户信息
     * @param roleId   要更改的角色
     */
    void updateRelationshipByRoleId(List<String> userIds,String roleId);



}
