package com.sofn.sys.service.permission.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.mapper.SysRoleRoleGroupMapper;
import com.sofn.sys.model.SysRoleRoleGroup;
import com.sofn.sys.service.permission.PermissionRoleGroupService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.vo.SysRoleGroupVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2019/12/19 16:32
 */
@Service
public class PermissionRoleGroupServiceImpl implements PermissionRoleGroupService {

    @Autowired
    private SysRoleRoleGroupMapper sysRoleRoleGroupMapper;

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Override
//    @RedisCache(key= PermissionConstant.PERMISSION_ROLE_GROUP_KEY + "permissionRoleGroup",isCarryLoginUserId = true,isCarryParams = true)
    public List<SysRoleGroupVo> selectUserHasRoleGroup(String userId) {
        boolean isSuperman = permissionSuperAdminService.isSuperman();
        List<SysRoleGroupVo> sysRoleGroupVoList;
        if(isSuperman){
            // 超级管理员
            sysRoleGroupVoList =  sysRoleRoleGroupMapper.selectUserHasRoleAndCreateGroup(null,"");
        }else {
            // 普通用户
            sysRoleGroupVoList =  sysRoleRoleGroupMapper.selectUserHasRoleAndCreateGroup(userId,"");
        }
        return getSysRoleGroupVos(sysRoleGroupVoList);
    }

    @Override
    public PageUtils<SysRoleGroupVo> selectUserHasRoleGroupByPage(String userId, String groupName, Integer pageNo, Integer pageSize) {
        boolean isSuperman = permissionSuperAdminService.isSuperman();
        PageHelper.offsetPage(pageNo,pageSize);
        List<SysRoleGroupVo> sysRoleGroupVoList;
        if(isSuperman){
            // 超级管理员
            sysRoleGroupVoList =  sysRoleRoleGroupMapper.selectUserHasRoleAndCreateGroup(null,groupName);
        }else {
            // 普通用户
            sysRoleGroupVoList =  sysRoleRoleGroupMapper.selectUserHasRoleAndCreateGroup(userId,groupName);
        }
        PageInfo<SysRoleGroupVo> pageInfo = new PageInfo<>(sysRoleGroupVoList);
        // 去除最后的,
        List<SysRoleGroupVo> list = pageInfo.getList();
        List<SysRoleGroupVo> sysRoleGroupVos = getSysRoleGroupVos(list);
        pageInfo.setList(sysRoleGroupVos);

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public boolean isEdit(String roleGroupId) {
        // 超级管理员直接可以编辑
        if(permissionSuperAdminService.isSuperman()){
            return true;
        }
        List<SysRoleRoleGroup> listByGroupId = sysRoleRoleGroupMapper.getListByGroupId(roleGroupId);
        if(!CollectionUtils.isEmpty(listByGroupId)){
            List<String> loginUserRoleIdList = UserUtil.getLoginUserRoleIdList();
            if(!CollectionUtils.isEmpty(loginUserRoleIdList)){
                List<String> groupHaveRole = listByGroupId.stream().map(SysRoleRoleGroup::getRoleId).collect(Collectors.toList());
                groupHaveRole.removeAll(loginUserRoleIdList);
                if(!CollectionUtils.isEmpty(groupHaveRole)){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 将角色组中的roleIds   roleNames  去除最后一个,
     * @param oldSysRoleGroupVo    List<SysRoleGroupVo>
     * @return  List<SysRoleGroupVo>
     */
    private List<SysRoleGroupVo> getSysRoleGroupVos(List<SysRoleGroupVo> oldSysRoleGroupVo){
        List<SysRoleGroupVo> sysRoleGroupVoList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(oldSysRoleGroupVo)){
            oldSysRoleGroupVo.forEach((item) -> {
               String roleIds =  item.getRoleIds();
               if(!StringUtils.isBlank(roleIds)){
//                   item.setRoleIds(roleIds.substring(0,roleIds.length() -1));
               }
                String roleNames = item.getRoleNames();
               if(!StringUtils.isBlank(roleNames)){
//                   item.setRoleNames(roleNames.substring(0,roleNames.length() - 1));
               }
                sysRoleGroupVoList.add(item);

            });
        }
        return sysRoleGroupVoList;
    }

}
