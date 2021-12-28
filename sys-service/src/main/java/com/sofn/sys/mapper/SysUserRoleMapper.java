package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by heyongjie on 2019/6/11 9:59
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 批量添加用户角色信息
     * @param sysUserRoles   用户角色信息
     */
    void batchSaveSysUserRole(@Param("sysUserRoles")List<SysUserRole> sysUserRoles);

    /**
     * 解除用户跟角色的关系
     * @param userId  用户ID
     */
    void dropUserRolesByUserId(String userId);

    /**
     * 拥有当前角色的用户数
     * @param roleId  角色Id
     * @return  用户数据量
     */
    Integer getUserCountOfRole(String roleId);
    List<SysUserRole> getUserRoleByUserId(String userId);

    /**
     * 根据角色ID解除用户和角色关心
     * @param roleId  角色ID
     */
    void dropUserRolesByRoleId(String roleId);
}
