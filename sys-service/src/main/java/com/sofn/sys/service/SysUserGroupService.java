package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysUserGroup;
import java.util.List;
public interface SysUserGroupService extends IService<SysUserGroup> {
  List<SysUserGroup> getUserGroupsByUserId(String userId);


  /**
   * 获取用户有的用户的ID
   * @param roleGroupId   角色组ID
   * @return  List<String>
   */
  List<String> getRoleGroupHaveUserIds(String roleGroupId);

  /**
   * 保存用户和角色组的关系
   * @param userIds    用户ID
   * @param groupId   角色组ID
   */
  void saveUserAndRoleGroupRelationship(List<String> userIds,String groupId);

}
