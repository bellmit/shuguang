package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysUserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserGroupMapper extends BaseMapper<SysUserGroup> {
    void batchSaveSysUserGroup(@Param("sysUserGroups")List<SysUserGroup> sysUserGroups);
    void dropUserGroupsByUserId(String userId);
    int getCountOfGroupId(String groupId);
    List<SysUserGroup> getUserGroupsByUserId(String userId);

    /**
     * 删除角色组角色关联关系
     * @param groupId  角色组ID
     */
    void deleteGroupRoleTable(String groupId);

    /**
     * 获取角色组有的用户
     * @param groupId    角色组
     * @param orgId    当前用户的机构ID
     * @param appIds   当前用户可访问的系统ID
     * @param carryProxyOrg  是否携带代理用户
     * @param carryChildren   是否携带子节点
     * @param userId    用户ID
     * @return   List<SysUserGroup>
     */
    List<SysUserGroup> getRoleGroupHaveUserId(@Param("groupId") String groupId,
                                              @Param("appIds") List<String> appIds,
                                              @Param("carryProxyOrg") String carryProxyOrg,
                                              @Param("carryChildren") String carryChildren,
                                              @Param("orgId") String orgId,
                                              @Param("userId") String userId);


}
