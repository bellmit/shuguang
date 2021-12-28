package com.sofn.sys.service.permission.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysResourceMapper;
import com.sofn.sys.mapper.SysRoleMapper;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.vo.RoleHaveResourceVo;
import com.sofn.sys.vo.SysResourceForm;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SystemRoleVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2019/12/18 16:17
 */
@Service
public class PermissionRoleServiceImpl implements PermissionRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysRoleVo> getLoginUserHaveRole() {
        return this.getLoginUserHaveRole(UserUtil.getLoginUserId(), false);
    }

    @Override
    public List<SysRoleVo> getLoginUserHaveRole(String userId, boolean superAdminSearchAll) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("status", SysManageEnum.STATUS_1.getCode());
        // 这里需要加上用户组的角色
        params.put("unionGroup", "Y");
        params.put("userId", userId);
        List<SysRoleVo> sysRoleVos = sysRoleMapper.selectUserHasRole(params);
        if (!CollectionUtils.isEmpty(sysRoleVos)) {
            // 如果是超级管理员就需要查询所有
            if (superAdminSearchAll) {
                return superAdminSearchAll(sysRoleVos,params);
            }
            return sysRoleVos;
        }
        return Lists.newArrayList();
    }

    /**
     * 超级管理员查询所有
     * @param sysRoleVos  当前用户有的角色
     */
    private List<SysRoleVo> superAdminSearchAll(List<SysRoleVo> sysRoleVos,Map<String, Object> params) {
        if (!CollectionUtils.isEmpty(sysRoleVos)) {
            // 如果是超级管理员就需要查询所有
            List<String> collect = sysRoleVos.stream().map(SysRoleVo::getRoleCode).collect(Collectors.toList());
            if (collect.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())) {
                params.remove("userId");
                params.put("ids", sysRoleVos.stream().map(SysRoleVo::getId).collect(Collectors.toList()));
                // 这里将用户有的放在最前面， 然后才是所有的
                List<SysRoleVo> sysRoleVoList = Lists.newArrayList();
                sysRoleVoList.addAll(sysRoleVos);
                List<SysRoleVo> sysRoleVos1 = sysRoleMapper.selectUserHasRole(params);
                if (!CollectionUtils.isEmpty(sysRoleVos1)) {
                    sysRoleVoList.addAll(sysRoleVos1);
                }
                return sysRoleVoList;
            }
        }
        return sysRoleVos;
    }

    @Override
    public List<SysRoleVo> getLoginUserHaveRoleNotIncludeUserCreate(String userId, boolean superAdminSearchAll) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("status", SysManageEnum.STATUS_1.getCode());
        // 这里需要加上用户组的角色
        params.put("unionGroup", "Y");
        params.put("userId", userId);
        List<SysRoleVo> sysRoleVos = sysRoleMapper.selectUserHasRoleAndNotIncludeUserCreate(params);
        if (superAdminSearchAll) {
           return superAdminSearchAll(sysRoleVos,params);
        }
        return sysRoleVos;
    }

    @Override
    public PageUtils<SysRoleVo> getLoginUserByPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        if (CollectionUtils.isEmpty(params)) {
            params = Maps.newHashMap();
        }
        boolean isSuperman = permissionSuperAdminService.isSuperman();
        if (!isSuperman) {
            // 如果不是超级管理员就加上用户限制，加上角色组中的角色
            params.put("userId", UserUtil.getLoginUserId());
            params.put("unionGroup", "Y");
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<SysRoleVo> sysRoleVos = sysRoleMapper.selectUserHasRole(params);
        PageInfo<SysRoleVo> pageInfo = new PageInfo<>(sysRoleVos);
        // 根据角色查询菜单信息
        List<SysRoleVo> sysRoleVoList = pageInfo.getList();
        List<String> roleIds = sysRoleVoList.stream().map(SysRoleVo::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(roleIds)) {
            // 这个不能包含父节点ID，如果包含了会导致父节点被选中，然后父节点下的所有就都会被选中
            List<RoleHaveResourceVo> roleHaveResourceVos = sysResourceMapper.getResourceByRoleIdsOrRoleCodesOrAppId(roleIds, null);
            if (!CollectionUtils.isEmpty(roleHaveResourceVos)) {
                Map<String, List<SysResourceForm>> roleHaveResource = Maps.newHashMap();
                roleHaveResourceVos.forEach(item -> roleHaveResource.put(item.getRoleId(), item.getSysResources()));
                // 设置菜单信息
                sysRoleVoList.forEach(item -> {
                    List<SysResourceForm> sysResourceList = roleHaveResource.get(item.getId());
                    if (!CollectionUtils.isEmpty(sysResourceList)) {
                        // 设置resourceIds
                        List<String> collect1 = sysResourceList.stream().map(SysResourceForm::getId).collect(Collectors.toList());
                        item.setResourceIds(IdUtil.getStrIdsByList(collect1));
                    }
                });
            }
        }
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public boolean isEdit(String roleId) {
        // 查看当前角色有的权限
        if(!permissionSuperAdminService.isSuperman()){
            List<RoleHaveResourceVo> roleHaveResourceVos = sysResourceMapper.getResourceByRoleIdsOrRoleCodesOrAppId(Lists.newArrayList(roleId), null);
            if(!CollectionUtils.isEmpty(roleHaveResourceVos)){
                List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
                if(!CollectionUtils.isEmpty(loginUserRoleCodeList)){
                    List<RoleHaveResourceVo> roleHaveResourceVos2 = sysResourceMapper.getResourceByRoleIdsOrRoleCodesOrAppId(Lists.newArrayList(loginUserRoleCodeList), null);
                    if(CollectionUtils.isEmpty(roleHaveResourceVos2)){
                        return false;
                    }
                    List<String> userHaveResource = getResourcePermission(roleHaveResourceVos2);
                    List<String> roleHaveResource = getResourcePermission(roleHaveResourceVos);
                    if(!CollectionUtils.isEmpty(userHaveResource) && !CollectionUtils.isEmpty(roleHaveResource)){
                        roleHaveResource.removeAll(userHaveResource);
                        if(!CollectionUtils.isEmpty(roleHaveResource)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public PageUtils<SystemRoleVo> getRoleAndSystemInfoByPage(String keyword,String roleIds, Integer pageNo, Integer pageSize) {
        boolean isSuperman = permissionSuperAdminService.isSuperman();
        List<String> roleIdList;
        if(StringUtils.isBlank(roleIds)){
            roleIdList = null;
        }else{
            roleIdList = IdUtil.getIdsByStr(roleIds);
        }

        if(pageSize > 0){
            PageHelper.offsetPage(pageNo,pageSize);
        }
        List<SystemRoleVo> systemRoleVos;
        if(isSuperman){
            systemRoleVos = sysRoleMapper.getSystemAndRoleInfo(keyword,null,roleIdList);
        }else{
            // 这里可能要添加上用户创建的
            systemRoleVos = sysRoleMapper.getSystemAndRoleInfo(keyword,UserUtil.getLoginUserId(),roleIdList);
        }
        PageInfo<SystemRoleVo> pageInfo = new PageInfo<>(systemRoleVos);
        return PageUtils.getPageUtils(pageInfo);
    }



    private List<String> getResourcePermission(List<RoleHaveResourceVo> roleHaveResourceVos){
        List<String> userHaveResource = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(roleHaveResourceVos)){
            roleHaveResourceVos.forEach(item->{
                List<SysResourceForm> sysResourceForms = item.getSysResources();
                if(!CollectionUtils.isEmpty(sysResourceForms)){
                    userHaveResource.addAll(sysResourceForms.stream().map(SysResourceForm::getPermission).collect(Collectors.toList()));
                }
            });
        }
        return userHaveResource;

    }
}
