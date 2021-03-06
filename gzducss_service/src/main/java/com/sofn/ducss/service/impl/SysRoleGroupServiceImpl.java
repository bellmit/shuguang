package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.ducss.enums.SysManageEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.SysRole;
import com.sofn.ducss.model.SysRoleGroup;
import com.sofn.ducss.model.SysRoleRoleGroup;
import com.sofn.ducss.service.SysRoleGroupService;
import com.sofn.ducss.service.permission.PermissionRoleService;
import com.sofn.ducss.service.permission.PermissionSubSystemService;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.RedisUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.vo.SysRoleGroupForm;
import com.sofn.ducss.vo.SysRoleVo;
import com.sofn.ducss.vo.SysSubsystemTreeVo;
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
        // 1. ????????????????????????
        checkSysRoleIsExists(groupForm.getGroupName(), null);
        // 2. ???????????????
        SysRoleGroup group = SysRoleGroupForm.getSysGroup(groupForm);
        group.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        group.setCreateTime(new Date());
        group.setCreateUserId(UserUtil.getLoginUserId());
        // 3. ??????
        groupMapper.insert(group);

        //?????????????????????????????????
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
            msg = "????????????????????????";
        }
        // 2. ????????????????????????
        checkSysRoleIsExists(group.getGroupName(), group.getId());
        // 3. ????????????
        BeanUtils.copyProperties(group, selectSysGroup);
        selectSysGroup.setUpdateTime(new Date());
        selectSysGroup.setUpdateUserId(UserUtil.getLoginUserId());
        groupMapper.updateById(selectSysGroup);

        sysRoleRoleGroupMapper.dropRoleGroupsByGroupId(group.getId());
        //?????????????????????????????????
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
          // ???????????????
          selectSysGroup.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
          selectSysGroup.preUpdate();
          groupMapper.updateById(selectSysGroup);
          // ??????????????????
          userGroupMapper.deleteGroupRoleTable(selectSysGroup.getId());
          // ??????????????????
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
        // ?????????????????????????????????
        Set<SysSubsystemTreeVo> sysSubsystemTreeVos = Sets.newHashSet();
        getAllSysSubsystemTreeVo(userHasSubsystem, sysSubsystemTreeVos);

        if (!CollectionUtils.isEmpty(sysSubsystemTreeVos)) {
            // ???????????????
            Optional<SysSubsystemTreeVo> first = sysSubsystemTreeVos.stream().filter(e -> e.getId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())).findFirst();
            if (first.isPresent()) {
                sysSubsystemTreeVos.remove(first.get());
            }
            Map<String, Object> params = Maps.newHashMap();
            List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRole(UserUtil.getLoginUserId(), true);
            sysSubsystemTreeVos.forEach(sysSubsystemTreeVo -> {
                params.put("subsystemId", sysSubsystemTreeVo.getId());
                // ???SysRoleVo ??????  SysSubsystemTreeVo
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
     * ??????????????????????????????????????????
     *
     * @param userHasSubsystem    ??????????????????
     * @param sysSubsystemTreeVos ???????????????Set???
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
        //??????????????????
        if (id != null) {
            // ??????
            // ????????????????????????
            Integer number = groupMapper.getSysGroupByName(name, id);
            if (number > 0) {
                throw new SofnException("??????????????????");
            }
            ;
        } else {
            // ??????
            // ????????????????????????
            Integer number = groupMapper.getSysGroupByName(name, null);
            if (number > 0) {
                throw new SofnException("??????????????????");
            }
            ;
        }
        return false;
    }


}
