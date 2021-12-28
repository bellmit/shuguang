package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysRoleResource;

import java.util.List;

/**
 * 角色资源类关联表相关接口
 * @author sofn
 */
public interface RoleResourceService extends IService<SysRoleResource> {


    /**
     * 根据角色查询 角色资源关联信息
     * @param roleId  角色ID
     * @return  List<SysRoleResource>
     */
    List<SysRoleResource> findPermissionForRole(String roleId);

    /**
     * 保存角色和资源的关联信息
     * @param roleId  角色ID
     * @param ids  资源IDS 用英文逗号隔开
     */
    void addPermissionForRole(String roleId, String ids);

    /**
     * 取消角色和资源的关联信息
     * @param roleId  角色ID
     * @param ids   资源IDS 用英文逗号隔开
     */
    void cancelPermissionForRole(String roleId, String ids);
    /**
     * 根据资源ID查询角色ID列表
     * @param resourceId 资源ID
     * @return 角色ID列表
     */
    List<String> findRoleIdByResourceId(String resourceId);
}

