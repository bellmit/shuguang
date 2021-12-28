package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    /**
     * 批量添加角色的资源信息
     * @param sysRoleResources   角色拥有的资源信息
     */
    void batchSaveSysRoleResource(@Param("sysRoleResources") List<SysRoleResource> sysRoleResources);

    /**
     * 解除角色和资源的关系
     */
    void dropByResourceId(String resourceId);
    void dropRoleResourcesByRoleId(String roleId);
    List<SysRoleResource> findPermissionForRole(String roleId);

    /**
     * 根据资源ID查询角色ID列表
     * @param resourceId 资源ID
     * @return 角色ID列表
     */
    List<String> findRoleIdByResourceId(@Param("resourceId") String resourceId);

    /**
     * 批量删除角色和菜单关系
     * @param resourceIds  菜单IDS
     */
    void batchDeleteByResourceIds(@Param("resourceIds") List<String> resourceIds);
}
