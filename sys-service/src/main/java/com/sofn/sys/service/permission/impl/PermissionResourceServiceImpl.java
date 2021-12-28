package com.sofn.sys.service.permission.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.treeutil.basic.TreeGenerate;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysResourceMapper;
import com.sofn.sys.service.SysResourceService;
import com.sofn.sys.service.permission.PermissionResourceService;
import com.sofn.sys.vo.SysResourceForm;
import com.sofn.sys.vo.SysResourceTreeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2019/12/19 18:10
 */
@Service
public class PermissionResourceServiceImpl implements PermissionResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysResourceService sysResourceService;

    private TreeGenerate<SysResourceTreeVo> treeGenerate = new TreeGenerate<>(SysManageEnum.TREE_NODE_SPLIT_STR.getCode(),
            SysManageEnum.RESOURCE_ROOT.getCode());


    @Override
    public List<SysResourceTreeVo> getInfoByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("ids", ids);
            return sysResourceMapper.getSysResourceTreeVo(params);
        }
        return null;
    }

    @Override
    public List<SysResourceTreeVo> getInfoByCondition(Map<String, Object> params) {
        return sysResourceMapper.getSysResourceTreeVo(params);
    }

    @Override
    public void deleteByIds(List<String> ids) {

    }

    @Override
    public List<SysResourceTreeVo> getInfoByParentId(List<String> parentIds) {
        if (!CollectionUtils.isEmpty(parentIds)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("parentIdList", parentIds);
            return sysResourceMapper.getSysResourceTreeVo(params);
        }
        return null;
    }

    @Override
    public List<SysResourceTreeVo> getInfoByLikeParentIds(String parentIds) {
        if (!StringUtils.isBlank(parentIds)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("parentIds", parentIds);
            return sysResourceMapper.getSysResourceTreeVo(params);
        }
        return null;
    }

    @Override
    public void updateParentId(String parentIds, List<String> ids) {

    }

    @Override
    public SysResourceTreeVo getUserHasResourceTree(String appId,String resourceName) {
        List<String> loginUserRoleIdList = UserUtil.getLoginUserRoleIdList();
        if(CollectionUtils.isEmpty(loginUserRoleIdList)){
           throw new SofnException("当前用户没有角色");
        }
        List<SysResourceForm> resourceByRoleIdsOrRoleCodesOrAppId = sysResourceService.getResourceByRoleIdsOrRoleCodesOrAppId(loginUserRoleIdList, Lists.newArrayList(appId), true,true);
        // 加上自己创建的
        if(StringUtils.isNotBlank(resourceName)){
            Set<String> parentIds = Sets.newHashSet();

            Set<String> parentIdStrings = Sets.newHashSet();
            // 模糊查询
            List<SysResourceForm> collect = resourceByRoleIdsOrRoleCodesOrAppId.stream().filter(item -> {
                boolean flag = false;
                if (item.getResourceName().contains(resourceName)) {
                    flag = true;
                    String parentIds1 = item.getParentIds();
                    if (StringUtils.isNotBlank(parentIds1)) {
                        parentIds.addAll(Lists.newArrayList(parentIds1.split(SysManageEnum.TREE_NODE_SPLIT_STR.getCode())));
                    }
                    // 找出符合条件的子节点信息
                    parentIdStrings.add(item.getParentIds() + item.getId() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
                }
                return flag;
            }).collect(Collectors.toList());
            Set<SysResourceForm> allInfo = Sets.newHashSet();
            allInfo.addAll(collect);

            if(!CollectionUtils.isEmpty(collect)){
                // 找出符合条件的父节点
                List<SysResourceForm> collect1 = resourceByRoleIdsOrRoleCodesOrAppId.stream().filter(item -> parentIds.contains(item.getId())).collect(Collectors.toList());
                // 找出符合条件的子节点
                List<SysResourceForm> collect2 = resourceByRoleIdsOrRoleCodesOrAppId.stream().filter(item -> {
                    if(StringUtils.isNotBlank(item.getParentIds())){
                        Optional<String> first = parentIdStrings.stream().filter(i -> item.getParentIds().contains(i)).findFirst();
                        if (first.isPresent()) {
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(collect1)){
                    allInfo.addAll(collect1);
                }

                if(!CollectionUtils.isEmpty(collect2)){
                    allInfo.addAll(collect2);
                }
            }


            resourceByRoleIdsOrRoleCodesOrAppId = Lists.newArrayList(allInfo);
        }
        List<SysResourceTreeVo> collect2 = resourceByRoleIdsOrRoleCodesOrAppId.stream().map(item -> {
            SysResourceTreeVo sysResourceTreeVo = new SysResourceTreeVo();
            BeanUtils.copyProperties(item, sysResourceTreeVo);
            return sysResourceTreeVo;
        }).collect(Collectors.toList());
        // 如果不含有根节点，直接加上根节点
        if(!CollectionUtils.isEmpty(collect2)){
            Optional<SysResourceTreeVo> first = collect2.stream().filter(item -> SysManageEnum.RESOURCE_ROOT.getCode().equals(item.getId())).findFirst();
            if(!first.isPresent()){
                SysResourceTreeVo sysResourceTreeVo = new SysResourceTreeVo();
                sysResourceTreeVo.setId(SysManageEnum.RESOURCE_ROOT.getCode());
                sysResourceTreeVo.setResourceName("菜单根节点");
                collect2.add(sysResourceTreeVo);
            }
            return treeGenerate.toTree(collect2,SysManageEnum.RESOURCE_ROOT.getCode());
        }

        return null;

    }
}
