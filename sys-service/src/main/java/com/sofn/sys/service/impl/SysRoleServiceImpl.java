package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.*;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysDictService;
import com.sofn.sys.service.SysRoleService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.util.RoleCacheUtils;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 角色相关管理
 *
 * @author sofn
 */
@SuppressWarnings("ALL")
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysRoleRoleGroupMapper sysRoleRoleGroupMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysSubsystemMapper subsystemMapper;

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Autowired
    private PermissionRoleService permissionRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDictService sysDictService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(SysRoleForm sysRoleForm) {
        // 1. 校验内容是否重复
        checkSysRoleIsExists(true, sysRoleForm.getRoleName(), sysRoleForm.getSubsystemId(), null);
        checkSysCodeIsExists(sysRoleForm.getRoleCode(), sysRoleForm.getSubsystemId(), null);
        // 2. 设置默认值
        sysRoleForm.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        sysRoleForm.setStatus(SysManageEnum.STATUS_1.getCode());
        sysRoleForm.setCreateTime(new Date());
        sysRoleForm.setCreateUserId(UserUtil.getLoginUserId());
        SysRole role = SysRoleForm.getSysRole(sysRoleForm);
        // 如果没有填写角色代码 ， 则随机生成一个
        if(StringUtils.isBlank(sysRoleForm.getRoleCode())){
            role.setRoleCode(IdUtil.getUUId());
        }

        // 3. 保存
        sysRoleMapper.insert(role);
        //插入角色资源关联表
        List<SysRoleResource> sysRoleResources = getSysRoleResourceRelations(sysRoleForm);
        this.saveSysRoleResource(role.getId(), sysRoleResources);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRoleForm sysRoleForm) {
        SysRole selectSysRole = sysRoleMapper.selectById(sysRoleForm.getId());
        if (selectSysRole == null || SysManageEnum.DEL_FLAG_Y.getCode().equals(selectSysRole.getDelFlag())) {
            throw new SofnException("待修改内容不存在");
        }
        // 2. 校验内容是否重复
        checkSysRoleIsExists(false, sysRoleForm.getRoleName(), sysRoleForm.getSubsystemId(), sysRoleForm.getId());
        checkSysCodeIsExists(sysRoleForm.getRoleCode(), sysRoleForm.getSubsystemId(), sysRoleForm.getId());
        // 3. 更改内容
        BeanUtils.copyProperties(sysRoleForm, selectSysRole);
        selectSysRole.setUpdateTime(new Date());
        selectSysRole.setUpdateUserId(UserUtil.getLoginUserId());
        sysRoleMapper.updateById(selectSysRole);
        //先解除角色和资源的关系,
        sysRoleResourceMapper.dropRoleResourcesByRoleId(sysRoleForm.getId());
        //再重新绑定角色和资源关系
        List<SysRoleResource> sysRoleResources = getSysRoleResourceRelations(sysRoleForm);
        this.saveSysRoleResource(selectSysRole.getId(), sysRoleResources);
        // 删除用户角色缓存
        delRoleCacheByUser(selectSysRole.getId());
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            // 将数据字典中的role_code_list取出来 然后去操作
            List<SysDict> roleUserList = sysDictService.getDictListByType("role_code_list");
            List<String> roleCodes = Lists.newArrayList();
            if(!CollectionUtils.isEmpty(roleUserList)){
                roleCodes = roleUserList.stream().map(SysDict::getDictcode).collect(Collectors.toList());
            }
//            List<String> finalRoleCodes = roleCodes;
            ids.forEach(id -> {
                SysRole selectSysRole = sysRoleMapper.selectById(id);
                if (selectSysRole == null) {
                    throw new SofnException(id + "不存在，删除失败");
                }
                if (SysManageEnum.DEL_FLAG_Y.getCode().equals(selectSysRole.getId())) {
                    throw new SofnException(id + "不存在，删除失败");
                }
//                if(finalRoleCodes.contains(selectSysRole.getRoleCode())){
//                    throw new SofnException("角色：" + selectSysRole.getRoleName() + "为内置角色，删除后会影响系统流程，无法删除！");
//                }

                Map<String, Object> params = Maps.newHashMap();
                params.put("roleId", id);
                PageUtils<BasicUserInfo> roleHaveUserInfo = sysUserService.getRoleHaveUserInfo(params, 0, 10);
                if (roleHaveUserInfo != null) {
                    List list = roleHaveUserInfo.getList();
                    if (!CollectionUtils.isEmpty(list)) {
                        throw new SofnException("当前角色被使用，请先解除关系后再删除！");
                    }
                }
                // 使用软删除
                selectSysRole.preUpdate();
                selectSysRole.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
                sysRoleMapper.updateById(selectSysRole);

                // 删除角色资源缓存
                RoleCacheUtils.delRole(id);
                // 删除用户角色缓存
                delRoleCacheByUser(id);

                // 解除角色组关系
                sysRoleRoleGroupMapper.dropRoleGroupByRoleId(selectSysRole.getId());
                // 解除用户角色关系
                sysUserRoleMapper.dropUserRolesByRoleId(selectSysRole.getId());
                // 解除角色菜单关系
                sysRoleResourceMapper.dropRoleResourcesByRoleId(selectSysRole.getId());
            });
        }
    }

    /**
     * 删除角色下用户缓存
     * @param roleId
     */
    private void delRoleCacheByUser(String roleId) {
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>()
                .eq("ROLE_ID", roleId));
        if (!CollectionUtils.isEmpty(userRoleList)){
            userRoleList.forEach(new Consumer<SysUserRole>() {
                @Override
                public void accept(SysUserRole sysUserRole) {
                    SysCacheUtils.increaseUserCacheVersion(sysUserRole.getUserId());
                }
            });
        }
    }

    @Override
    public PageUtils<SysRoleForm> getSysRoleByCondition(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        // 这方法应该暂时没用
        PageHelper.offsetPage(pageNo, pageSize);
        List<SysRole> sysRoleList = sysRoleMapper.getSysRoleByCondition(params);

        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(sysRoleList);
        PageInfo<SysRoleForm> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(sysRolePageInfo, pageInfo);
        List<SysRoleForm> sysRoleFormList = new ArrayList<>();
        pageInfo.setList(sysRoleFormList);
        for (SysRole sysRole : sysRoleList) {
            SysRoleForm sysRoleForm = new SysRoleForm();
            BeanUtils.copyProperties(sysRole, sysRoleForm);
            SysSubsystem subsystem = subsystemMapper.selectById(sysRole.getSubsystemId());
            if (subsystem != null) {
                sysRoleForm.setSubsystemName(subsystem.getSubsystemName());
            }
            List<SysResourceForm> resourceList = sysResourceMapper.getAllResourceByRoleId(sysRole.getId());
            List<String> resourceIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(resourceList)) {
                for (SysResourceForm sr : resourceList) {
                    resourceIds.add(sr.getId());
                }
            }
            sysRoleForm.setResourceIds(IdUtil.getStrIdsByList(resourceIds));
            sysRoleForm.setRoleCode(StringUtils.isBlank(sysRole.getRoleCode()) ? "" : sysRole.getRoleCode());
            sysRoleFormList.add(sysRoleForm);
        }

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SysRole> getRoleListByAppId(String appId) {
        if (StringUtils.isBlank(appId)) {
            throw new SofnException("参数appId为空");
        }
        return sysRoleMapper.getRoleListByAppId(appId);
    }

    @Override
    public Map<String, String> getRoleHasPermission() {
        List<Map<String, String>> roleHasPermission = sysRoleMapper.getRoleHasPermission();
        Map<String, Set<String>> roleHasPermissionMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(roleHasPermission)) {
            for (Map<String, String> stringStringMap : roleHasPermission) {
                if (CollectionUtils.isEmpty(stringStringMap)) {
                    System.out.println(123);
                }
                String roleId = stringStringMap.get("roleid");
                Set<String> values = roleHasPermissionMap.get(roleId);
                if (CollectionUtils.isEmpty(values)) {
                    values = Sets.newHashSet();
                }
                String permission = stringStringMap.get("permission");
                if (!StringUtils.isBlank(permission)) {
                    values.add(permission);
                }
                roleHasPermissionMap.put(roleId, values);
            }
        }

        // 超级管理员查询所有
        List<SysResource> allResource = sysResourceMapper.selectList(new QueryWrapper<SysResource>().eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
        if (!CollectionUtils.isEmpty(allResource)) {
            Set<String> collect = allResource.stream().map(SysResource::getPermission).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
            roleHasPermissionMap.put(SysManageEnum.DEVELOPER_ROLE_ID.getCode(), collect);
        }

        // 将value用,号分隔
        Map<String, String> permissions = Maps.newHashMap();
        roleHasPermissionMap.forEach((k, v) -> {
            if (!CollectionUtils.isEmpty(v)) {
                permissions.put(k, String.join(",", v));
            }
        });
        return permissions;
    }

    @Override
    public List<UserHaveRoleVo> getUserHaveRole(List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Lists.newArrayList();
        }
        return sysRoleMapper.getUserHaveRole(userIds);
    }

    @Override
    public List<SysRoleForm> getSysRoleByCondition(Map<String, Object> paramas) {
        List<SysRole> sysRoleList = sysRoleMapper.getSysRoleByCondition(paramas);
        List<SysRoleForm> sysRoleFormList = new ArrayList<>();
        for (SysRole sysRole : sysRoleList) {
            SysSubsystem subsystem = subsystemMapper.selectById(sysRole.getSubsystemId());
            SysRoleForm sysRoleForm = new SysRoleForm();
            BeanUtils.copyProperties(sysRole, sysRoleForm);
            sysRoleForm.setSubsystemName(subsystem.getSubsystemName());
            sysRoleForm.setRoleCode(StringUtils.isBlank(sysRole.getRoleCode()) ? "" : sysRole.getRoleCode());
            sysRoleFormList.add(sysRoleForm);
        }
        return sysRoleFormList;
    }

    @Override
    public List<SysRole> getSysRoleListBySubsystemId(String subsystemId) {
        // 角色组选择角色的时候会调用这个方法
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("subsystemId", StringUtils.isBlank(subsystemId) ? IdUtil.getUUId() : subsystemId);
        List<SysRole> sysRoleList = sysRoleMapper.getSysRoleByCondition(params);
        if (!CollectionUtils.isEmpty(sysRoleList)) {
            boolean superman = permissionSuperAdminService.isSuperman();
            if (!superman) {
                // 限制角色信息
                List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRole();
                Set<String> collect = loginUserHaveRole.stream().map(SysRoleVo::getId).collect(Collectors.toSet());
                return sysRoleList.stream().filter((sysRole -> collect.contains(sysRole.getId()))).collect(Collectors.toList());
            }
        }
        return sysRoleList;
    }

    /**
     * 通过系统APPID和角色码获取角色
     *
     * @param appId    APPID
     * @param roleCode 角色码
     * @return 角色
     */
    @Override
    public SysRole getSysRoleByAppIdAndRoleCode(String appId, String roleCode) {
        List<SysSubsystem> sysSubsystems = subsystemMapper.selectList(new QueryWrapper<SysSubsystem>().eq("APP_ID", appId));
        if (CollectionUtils.isEmpty(sysSubsystems)) {
            throw new SofnException("未找到该系统");
        }

        List<SysSubsystem> collect = sysSubsystems.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            throw new SofnException("未找到该系统");
        }
        SysSubsystem sysSubsystem = collect.get(0);
        if (sysSubsystem == null) {
            throw new SofnException("未找到该系统");
        }

        // 角色组选择角色的时候会调用这个方法
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("subsystemId", StringUtils.isBlank(sysSubsystem.getId()) ? IdUtil.getUUId() : sysSubsystem.getId());
        params.put("roleCode", roleCode);
        List<SysRole> sysRoleList = sysRoleMapper.getSysRoleByCondition(params);
        if (!CollectionUtils.isEmpty(sysRoleList)) {
            if (sysRoleList.size() > 1) {
                throw new SofnException("该系统下角色码重复");
            }

            return sysRoleList.get(0);
        } else {
            throw new SofnException("未找到对应角色");
        }
    }

    /**
     * 判断角色码是否重复
     *
     * @param roleCode    角色码
     * @param subSystemId 子系统
     * @param id          传入ID就不会包含当前数据
     */
    private void checkSysCodeIsExists(String roleCode, String subSystemId, String id) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>()
                .eq("SUBSYSTEM_ID", subSystemId)
                .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode());

        if (StringUtils.isNotBlank(roleCode)) {
          queryWrapper.eq("ROLE_CODE", roleCode);
        }

        if (!StringUtils.isBlank(id)) {
            queryWrapper.ne("ID", id);
        }
        if (!StringUtils.isBlank(roleCode)){
          int count = sysRoleMapper.selectCount(queryWrapper);
          if (count > 0) {
            throw new SofnException("角色代码重复");
          }
        }

    }

    /**
     * 检查当前系统下角色是否重复
     *
     * @param isCreate    是否正在创建角色
     * @param roleName    角色名称
     * @param subsystemId 系统ID
     * @param id          角色ID
     */
    private void checkSysRoleIsExists(boolean isCreate, String roleName, String subsystemId, String id) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>()
                .eq("SUBSYSTEM_ID", subsystemId)
                .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode())
                .eq("ROLE_NAME", roleName);
        if (!isCreate) {
            queryWrapper.ne("ID", id);
        }
        int count = sysRoleMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new SofnException("角色名称重复");
        }

    }


    /**
     * 根据SysRoleForm获取SysRoleResource集合用于保存数据
     *
     * @param sysRoleForm SysRoleForm
     * @return List<SysRoleResource>
     */
    private List<SysRoleResource> getSysRoleResourceRelations(SysRoleForm sysRoleForm) {
        if (sysRoleForm.getResourceIds() != null) {
            List<SysRoleResource> sysRoleResources = new ArrayList<>();
            String[] ids = sysRoleForm.getResourceIds().split(",");
            for (String id : ids) {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setId(IdUtil.getUUId());
                sysRoleResource.setRoleId(sysRoleForm.getId());
                sysRoleResource.setResourceId(id);
                sysRoleResources.add(sysRoleResource);
            }
            return sysRoleResources;
        } else {
            throw new SofnException("权限不能为空");
        }
    }

    /**
     * 保存角色和菜单的关系
     *
     * @param roleId           角色ID，用于更新缓存
     * @param sysRoleResources 角色和菜单关联表
     */
    private void saveSysRoleResource(String roleId, List<SysRoleResource> sysRoleResources) {
        if (!CollectionUtils.isEmpty(sysRoleResources)) {
            sysRoleResourceMapper.batchSaveSysRoleResource(sysRoleResources);
            List<SysResource> sysResourcesList = sysResourceMapper.selectBatchIds(sysRoleResources.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList()));
            if (!CollectionUtils.isEmpty(sysResourcesList)) {
                RoleCacheUtils.addOrUpdateRole(roleId, sysResourcesList.stream().map(SysResource::getPermission).collect(Collectors.toSet()));
            } else {
                throw new SofnException("角色不能没有资源！");
            }
        }
    }

}
