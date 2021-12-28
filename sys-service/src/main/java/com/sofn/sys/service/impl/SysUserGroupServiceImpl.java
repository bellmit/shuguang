package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysRoleGroupMapper;
import com.sofn.sys.mapper.SysUserGroupMapper;
import com.sofn.sys.model.SysRoleGroup;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.model.SysUserGroup;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysUserGroupService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroup>
        implements SysUserGroupService {

    @Autowired
    private SysUserGroupMapper userGroupMapper;

    @Autowired
    private SysRoleGroupMapper sysRoleGroupMapper;

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysUserService sysUserService;



    @Override
    public List<SysUserGroup> getUserGroupsByUserId(String userId) {
        // 根据用户ID查询出用户有的角色ID集合
        return userGroupMapper.getUserGroupsByUserId(userId);
    }

    @Override
    public List<String> getRoleGroupHaveUserIds(String roleGroupId) {
        List<SysUserGroup> sysUserGroups;
        if(permissionSuperAdminService.isSuperman()){
            sysUserGroups = userGroupMapper.selectList(new QueryWrapper<SysUserGroup>().eq("GROUP_ID", roleGroupId));
        }else{
            String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
            if(StringUtils.isBlank(loginUserOrganizationId)){
                throw new SofnException("当前用户无所属机构");
            }
            List<String> loginUserHaveSystemAppIds = UserUtil.getLoginUserHaveSystemAppIds();
            sysUserGroups = userGroupMapper.getRoleGroupHaveUserId(roleGroupId, loginUserHaveSystemAppIds, BoolUtils.Y, BoolUtils.Y,
                    loginUserOrganizationId,UserUtil.getLoginUserId());
        }
        if(!CollectionUtils.isEmpty(sysUserGroups)){
            return Lists.newArrayList(sysUserGroups.stream().map(SysUserGroup::getUserId).collect(Collectors.toSet()));

        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndRoleGroupRelationship(List<String> userIds, String groupId) {
        SysRoleGroup sysRoleGroup = sysRoleGroupMapper.selectById(groupId);
        if(sysRoleGroup == null || SysManageEnum.DEL_FLAG_Y.getCode().equals(sysRoleGroup.getDelFlag())){
            throw new SofnException("角色组不存在！");
        }
        List<String> newUserIds = Lists.newArrayList(userIds);
        if(!permissionSuperAdminService.isSuperman()){
            // 如果不是超级管理员需要加上该角色组当前用户无法查看的用户
            List<SysUserGroup> sysUserGroups =  userGroupMapper.selectList(new QueryWrapper<SysUserGroup>().eq("GROUP_ID", groupId));
            if(!CollectionUtils.isEmpty(sysUserGroups)){
                List<String> oldUserIds = sysUserGroups.stream().map(SysUserGroup::getUserId).collect(Collectors.toList());
                // 找出用户能够访问的机构
                List<String> userHaveOrgIds = sysOrgService.getUserHaveOrgIds(UserUtil.getLoginUserHaveSystemAppIds(), true, true);
                if(!CollectionUtils.isEmpty(userHaveOrgIds)){
                    // 筛选出用户无法查看的用户信息，然后重新加入到新的关系中
                    List<SysUser> sysUserList = sysUserService.list(new QueryWrapper<SysUser>().in("ID", oldUserIds));
                    if(!CollectionUtils.isEmpty(sysUserList)){
                        // 自己创建的用户也是有权限的，所以还需要排除自己创建的，剩下的才是没有权限的
                        String loginUserId = UserUtil.getLoginUserId();
                        List<String> userDontShowUserId = sysUserList.stream()
                                .filter(item -> !oldUserIds.contains(item.getOrganizationId()) && !loginUserId.equals(item.getCreateUserId()))
                                .map(SysUser::getId)
                                .collect(Collectors.toList());
                        if(!CollectionUtils.isEmpty(userDontShowUserId)){
                            newUserIds.addAll(userDontShowUserId);
                        }

                    }
                }
            }
        }

        // 1. 删除关系
        userGroupMapper.delete(new UpdateWrapper<SysUserGroup>().eq("GROUP_ID",groupId));
        // 2. 保存关系
        if(!CollectionUtils.isEmpty(newUserIds)){
            List<SysUserGroup> sysUserGroups2 = Lists.newArrayList();
            newUserIds.forEach(item->{
                if(!StringUtils.isBlank(item)){
                    SysUserGroup sysUserGroup = new SysUserGroup();
                    sysUserGroup.setId(IdUtil.getUUId());
                    sysUserGroup.setGroupId(groupId);
                    sysUserGroup.setUserId(item);
                    sysUserGroups2.add(sysUserGroup);
                }
            });
            if(!CollectionUtils.isEmpty(sysUserGroups2)){
                userGroupMapper.batchSaveSysUserGroup(sysUserGroups2);
            }
        }
    }
}
