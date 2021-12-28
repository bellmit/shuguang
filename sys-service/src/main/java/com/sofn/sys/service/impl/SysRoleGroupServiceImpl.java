package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.RedisUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.*;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.model.SysRoleGroup;
import com.sofn.sys.model.SysRoleRoleGroup;
import com.sofn.sys.service.SysRoleGroupService;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.vo.SysRoleGroupForm;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SysSubsystemTreeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by sofn
 */
@SuppressWarnings("ALL")
@Service(value = "sysRoleGroupService")
public class SysRoleGroupServiceImpl extends ServiceImpl<SysRoleGroupMapper, SysRoleGroup> implements SysRoleGroupService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysRoleGroupMapper groupMapper;
    @Autowired
    private SysRoleRoleGroupMapper sysRoleRoleGroupMapper;
    @Autowired
    private SysSubsystemMapper systemMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserGroupMapper userGroupMapper;

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    @Autowired
    private PermissionRoleService permissionRoleService;

    @Override
    public PageUtils<SysRoleGroupForm> findAllGroupList(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SysRoleGroup> sysGroups = groupMapper.getSysGroupByCondition(params);
        PageInfo<SysRoleGroup> sysGroupPageInfo = new PageInfo<SysRoleGroup>(sysGroups);
        List<SysRoleGroupForm> sysGroupFormList = new ArrayList<>();
        PageInfo<SysRoleGroupForm> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(sysGroupPageInfo, pageInfo);
        for (SysRoleGroup sysGroup : sysGroups) {
            SysRoleGroupForm sysGroupForm = new SysRoleGroupForm();
            BeanUtils.copyProperties(sysGroup, sysGroupForm);
            List<SysRoleRoleGroup> rrpList = sysRoleRoleGroupMapper.getListByGroupId(sysGroup.getId());
            if (rrpList != null && rrpList.size() > 0) {
                List<String> roleNameList = new ArrayList<>();
                List<String> roleIdList = new ArrayList<>();
                for (SysRoleRoleGroup userRole : rrpList) {
                    if (userRole != null && StringUtils.isNotBlank(userRole.getRoleId())) {
                        SysRole role = sysRoleMapper.selectById(userRole.getRoleId());
                        roleIdList.add(role.getId());
                        roleNameList.add(role.getRoleName());
                    }
                }
                sysGroupForm.setRoleIds(StringUtils.join(roleIdList, ","));
                sysGroupForm.setRoleNames(StringUtils.join(roleNameList, ","));
            }
            sysGroupFormList.add(sysGroupForm);
        }
        pageInfo.setList(sysGroupFormList);

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_ROLE_GROUP_KEY})
    public void createRoleGroup(SysRoleGroupForm groupForm) {
        // 1. 校验内容是否重复
        checkSysRoleIsExists(groupForm.getGroupName(), null);
        // 2. 设置默认值
        SysRoleGroup group = SysRoleGroupForm.getSysGroup(groupForm);
        group.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        group.setCreateTime(new Date());
        group.setCreateUserId(UserUtil.getLoginUserId());
        // 3. 保存
        groupMapper.insert(group);

        //插入角色和角色组关联表
        List<SysRoleRoleGroup> sysRoleGroups = getRoleGroupRelations(groupForm);
        if (null != sysRoleGroups && sysRoleGroups.size() > 0) {
            sysRoleRoleGroupMapper.batchSaveSysRoleGroup(sysRoleGroups);
        }

    }

    private List<SysRoleRoleGroup> getRoleGroupRelations(SysRoleGroupForm groupForm) {
        List<SysRoleRoleGroup> sysRoleGroups = new ArrayList<>();
        if (StringUtils.isNotEmpty(groupForm.getRoleIds())) {
            String[] roleIds = groupForm.getRoleIds().split(",");
            for (int i = 0; i < roleIds.length; i++) {
                SysRoleRoleGroup sysRoleGroup = new SysRoleRoleGroup();
                sysRoleGroup.setId(IdUtil.getUUId());
                sysRoleGroup.setRoleId(roleIds[i]);
                sysRoleGroup.setGroupId(groupForm.getId());
                sysRoleGroups.add(sysRoleGroup);
            }
        }
        return sysRoleGroups;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_ROLE_GROUP_KEY})
    public String updateRoleGroup(SysRoleGroupForm group) {
        String msg = "";
        SysRoleGroup selectSysGroup = groupMapper.selectById(group.getId());
        if (selectSysGroup == null || SysManageEnum.DEL_FLAG_Y.getCode().equals(selectSysGroup.getDelFlag())) {
            msg = "待修改内容不存在";
        }
        // 2. 校验内容是否重复
        checkSysRoleIsExists(group.getGroupName(), group.getId());
        // 3. 更改内容
        BeanUtils.copyProperties(group, selectSysGroup);
        selectSysGroup.setUpdateTime(new Date());
        selectSysGroup.setUpdateUserId(UserUtil.getLoginUserId());
        groupMapper.updateById(selectSysGroup);

        sysRoleRoleGroupMapper.dropRoleGroupsByGroupId(group.getId());
        //插入角色和角色组关联表
        List<SysRoleRoleGroup> sysRoleGroups = getRoleGroupRelations(group);
        if (null != sysRoleGroups && sysRoleGroups.size() > 0) {
            sysRoleRoleGroupMapper.batchSaveSysRoleGroup(sysRoleGroups);
        }
        return msg;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_ROLE_GROUP_KEY})
    public void deleteRoleGroup(String[] ids) {
        for (String id : ids) {
          SysRoleGroup selectSysGroup = new SysRoleGroup();
          selectSysGroup.setId(id);
          // 使用软删除
          selectSysGroup.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
          selectSysGroup.preUpdate();
          groupMapper.updateById(selectSysGroup);
          // 解除用户关系
          userGroupMapper.deleteGroupRoleTable(selectSysGroup.getId());
          // 解除角色关系
          sysRoleRoleGroupMapper.dropRoleGroupsByGroupId(selectSysGroup.getId());
        }
    }

    @Override
    public List<SysRoleGroupForm> getSysRoleGroupByContion(Map<String, Object> params) {
        List<SysRoleGroup> sysGroups = groupMapper.getSysGroupByCondition(params);
        List<SysRoleGroupForm> sysGroupFormList = new ArrayList<>();
        for (SysRoleGroup sysGroup : sysGroups) {
            SysRoleGroupForm sysGroupForm = new SysRoleGroupForm();
            BeanUtils.copyProperties(sysGroup, sysGroupForm);
            List<SysRoleRoleGroup> rrpList = sysRoleRoleGroupMapper.getListByGroupId(sysGroup.getId());
            if (rrpList != null && rrpList.size() > 0) {
                List<String> roleNameList = new ArrayList<>();
                List<String> roleIdList = new ArrayList<>();
                for (SysRoleRoleGroup userRole : rrpList) {
                    if (userRole != null && StringUtils.isNotBlank(userRole.getRoleId())) {
                        SysRole role = sysRoleMapper.selectById(userRole.getRoleId());
                        roleIdList.add(role.getId());
                        roleNameList.add(role.getRoleName());
                    }
                }
                sysGroupForm.setRoleIds(StringUtils.join(roleIdList, ","));
                sysGroupForm.setRoleNames(StringUtils.join(roleNameList, ","));
            }
            sysGroupFormList.add(sysGroupForm);
        }

        return sysGroupFormList;
    }

    @Override
    public List<SysSubsystemTreeVo> getSubsystemHasRoleToTree() {
        SysSubsystemTreeVo userHasSubsystem = permissionSubSystemService.getUserHasSubsystem("");
        // 递归将所有的数据找出来
        Set<SysSubsystemTreeVo> sysSubsystemTreeVos = Sets.newHashSet();
        getAllSysSubsystemTreeVo(userHasSubsystem, sysSubsystemTreeVos);

        if (!CollectionUtils.isEmpty(sysSubsystemTreeVos)) {
            // 排除根节点
            Optional<SysSubsystemTreeVo> first = sysSubsystemTreeVos.stream().filter(e -> e.getId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())).findFirst();
            if (first.isPresent()) {
                sysSubsystemTreeVos.remove(first.get());
            }
            Map<String, Object> params = Maps.newHashMap();
            List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRole(UserUtil.getLoginUserId(), true);
            sysSubsystemTreeVos.forEach(sysSubsystemTreeVo -> {
                params.put("subsystemId", sysSubsystemTreeVo.getId());
                // 将SysRoleVo 转为  SysSubsystemTreeVo
                Set<SysSubsystemTreeVo> systemHaveRole = Sets.newHashSet();
                if (!CollectionUtils.isEmpty(loginUserHaveRole)) {
                    loginUserHaveRole.forEach(item -> {
                        if(item.getSubsystemId().equals( sysSubsystemTreeVo.getId())){
                            SysSubsystemTreeVo sysSubsystemTreeVo1 = new SysSubsystemTreeVo();
                            sysSubsystemTreeVo1.setId(item.getId());
                            sysSubsystemTreeVo1.setSubsystemName(item.getRoleName());
                            systemHaveRole.add(sysSubsystemTreeVo1);
                        }


                    });
                }
                sysSubsystemTreeVo.setChildren(Lists.newArrayList(systemHaveRole));
            });
            return Lists.newArrayList(sysSubsystemTreeVos);
        }
        return null;
    }

    /**
     * 将树结构里面的所有元素取出来
     *
     * @param userHasSubsystem    树结构根节点
     * @param sysSubsystemTreeVos 将元素放在Set中
     */
    private void getAllSysSubsystemTreeVo(SysSubsystemTreeVo userHasSubsystem, Set<SysSubsystemTreeVo> sysSubsystemTreeVos) {
        if (userHasSubsystem != null) {
            List<SysSubsystemTreeVo> children = userHasSubsystem.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                children.forEach((item) -> {
                    getAllSysSubsystemTreeVo(item, sysSubsystemTreeVos);
                });
                userHasSubsystem.setChildren(null);
                sysSubsystemTreeVos.add(userHasSubsystem);

            } else {
                sysSubsystemTreeVos.add(userHasSubsystem);
            }
        }
    }

    public boolean checkSysRoleIsExists(String name, String id) {
        //检查是否重复
        if (id != null) {
            // 修改
            // 检查名称是否重复
            Integer number = groupMapper.getSysGroupByName(name, id);
            if (number > 0) {
                throw new SofnException("角色名称重复");
            }
            ;
        } else {
            // 添加
            // 检查名称是否重复
            Integer number = groupMapper.getSysGroupByName(name, null);
            if (number > 0) {
                throw new SofnException("角色名称重复");
            }
            ;
        }
        return false;
    }


}
