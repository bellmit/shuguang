package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysRoleRoleGroup;
import com.sofn.sys.vo.SysRoleGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysRoleRoleGroupMapper extends BaseMapper<SysRoleRoleGroup> {
    void batchSaveSysRoleGroup(@Param("sysRoleGroups") List<SysRoleRoleGroup> sysRoleGroups);
    void dropRoleGroupsByGroupId(String groupId);
    int getCountByRoleId(String roleId);
    List<SysRoleRoleGroup> getListByGroupId(String groupId);

    /**
     * 获取用户有的角色组和自己创建的角色组
     * @param userId   登录用户ID
     * @param groupName   角色组名称
     * @return    List<SysRoleGroupVo>
     */
    List<SysRoleGroupVo> selectUserHasRoleAndCreateGroup(@Param("userId") String userId,@Param("groupName")String groupName);

    /**
     * 根据角色ID解除角色 角色组关系
     * @param roleId  角色ID
     */
    void dropRoleGroupByRoleId(String roleId);

    /**
     * 获取当前用户有的角色组
     * @param userId   用户ID
     * @return List<SysRoleGroupVo>
     */
    List<SysRoleGroupVo> selectUserHasRoleGroup( @Param("userId") String userId);

}
