package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.GetAllInfoByPageUtil;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysResourceMapper;
import com.sofn.sys.mapper.SysRoleResourceMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.model.SysResource;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysResourceService;
import com.sofn.sys.util.RoleCacheUtils;
import com.sofn.sys.vo.RoleHaveResourceVo;
import com.sofn.sys.vo.SysResourceBatchSaveVo;
import com.sofn.sys.vo.SysResourceForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sofn
 */
@Service(value = "sysResourceService")
@Slf4j
public class SysResourceServiceImpl extends
        ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createResource(SysResource resource) {
        // 1. 校验内容是否重复
        checkSysResourceIsExists(resource.getPermission(), null);
        // 2. 设置默认值
        String resourceId = IdUtil.getUUId();
        resource.setId(resourceId);
        resource.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        getParentIdStrings(resource);
        if (SysManageEnum.RESOURCE_MENU.getCode().equals(resource.getType())) {
            if (StringUtils.isEmpty(resource.getResourceUrl())) {
                resource.setResourceUrl("#");
            }
        }
        if (!(SysManageEnum.RESOURCE_MENU.getCode().equals(resource.getType()) || SysManageEnum.RESOURCE_BUTTON.getCode().equals(resource.getType()))) {
            throw new SofnException("菜单类型只能是BUTTON或者MENU");
        }
        if (!StringUtils.isBlank(resource.getAppId())) {
            SysSubsystem sysSubsystemAppId = sysSubsystemMapper.getSysSubsystemAppId(resource.getAppId());
            if (sysSubsystemAppId == null) {
                throw new SofnException("子系统不存在");
            }
        }
        resource.preInsert();
        // 3. 保存resource
        sysResourceMapper.insert(resource);

        RoleCacheUtils.addPermission(resource.getPermission());

    }

    /**
     * 根据资源获取ParentIds
     *
     * @param resource 资源信息
     */
    private void getParentIdStrings(SysResource resource) {
        if (resource.getParentId().equals(SysManageEnum.RESOURCE_ROOT.getCode())) {
            resource.setParentIds(SysManageEnum.RESOURCE_ROOT.getCode() + "/");
        } else {
            SysResource parent = sysResourceMapper.selectById(resource.getParentId());
            if (parent == null) {
                throw new SofnException("父菜单不存在");
            }
            resource.setParentIds(parent.makeSelfAsParentIds());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResource(SysResource resource) {
        if (SysManageEnum.RESOURCE_ROOT.getCode().equals(resource.getId())) {
            throw new SofnException("根节点不能被更改");
        }

        SysResource selectSysResource = sysResourceMapper.selectById(resource.getId());
        if (selectSysResource == null) {
            throw new SofnException("待修改内容不存在");
        }
        checkSysResourceIsExists(resource.getPermission(), resource.getId());

        getParentIdStrings(resource);

        if (!selectSysResource.getParentId().equals(resource.getParentId())) {
            log.info("根节点发生了变化需要更改！");
            // 根节点发生变化需要更新子节点的parentIds字段
            String parentIds = selectSysResource.getParentIds() + selectSysResource.getId() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode();
            List<SysResource> sysResourceList = sysResourceMapper.selectList(new QueryWrapper<SysResource>().like("PARENT_IDS", parentIds + "%")
                    .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
            if (!CollectionUtils.isEmpty(sysResourceList)) {
                List<String> sysResourceIds = sysResourceList.stream().map(SysResource::getId).collect(Collectors.toList());
                String newParentIdsPrefix = resource.getParentIds() + resource.getId() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode();
                this.updateParentIdsByIds(sysResourceIds, newParentIdsPrefix, parentIds);
            }
        }

        // 如果系统发生变化需要将子节点的子系统给更新了
        if (!selectSysResource.getAppId().equals(resource.getAppId())) {
            log.info("菜单的系统发生了变化");
            String newParentIdsPrefix = resource.getParentIds() + resource.getId() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode();
            List<SysResource> sysResourceList = sysResourceMapper.selectList(new QueryWrapper<SysResource>().like("PARENT_IDS", newParentIdsPrefix + "%")
                    .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
            if (!CollectionUtils.isEmpty(sysResourceList)) {
                sysResourceList.forEach(item -> {
                    item.setAppId(resource.getAppId());
                });
                this.updateBatchById(sysResourceList, 1000);
            }
        }

        BeanUtils.copyProperties(resource, selectSysResource);
        selectSysResource.setUpdateTime(new Date());
        selectSysResource.setUpdateUserId(UserUtil.getLoginUserId());
        sysResourceMapper.updateById(selectSysResource);
        // 删除缓存
        RoleCacheUtils.addPermission(resource.getPermission());
    }

    /**
     * 检查菜单是否重复
     *
     * @param permission 权限码
     * @param id         菜单ID
     */
    private void checkSysResourceIsExists(String permission, String id) {
        //检查是否重复
        if (id != null) {
            // 修改
            // 检查名称是否重复
            Integer number = sysResourceMapper.getSysResourceByPermissionOrUrl(permission, null, id);
            if (number > 0) {
                throw new SofnException("权限标识重复");
            }
        } else {
            // 添加
            // 检查名称是否重复
            Integer number = sysResourceMapper.getSysResourceByPermissionOrUrl(permission, null, null);
            if (number > 0) {
                throw new SofnException("权限标识重复");
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteResource(String id) {
        if (SysManageEnum.RESOURCE_ROOT.getCode().equals(id)) {
            throw new SofnException("根节点不能被删除！");
        }

        SysResource selectSysResource = sysResourceMapper.selectById(id);
        // 2. 如果下面有子节点的不能删除
        Map<String, Object> params = Maps.newHashMap();
        params.put("parentId", id);
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        List<SysResourceForm> sysResourceList = sysResourceMapper.getSysResourceByCondition(params);
        if (sysResourceList != null && sysResourceList.size() > 0) {
            throw new SofnException("当前菜单下有子菜单，删除失败");
        }
        // 使用软删除
        selectSysResource.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
        selectSysResource.preUpdate();
        sysResourceMapper.updateById(selectSysResource);
        //解除角色菜单关系
        roleResourceMapper.dropByResourceId(id);

        RoleCacheUtils.delPermission(selectSysResource.getPermission());
    }

    @Override
    public List<SysResourceForm> getAllResourceByRoleId(String roleId) {
        List<SysResourceForm> sysResourceList = this.getResourceByRoleIdsOrRoleCodesOrAppId(Lists.newArrayList(roleId), null, false, false);

        if (!CollectionUtils.isEmpty(sysResourceList)) {
            sysResourceList.forEach(item -> {
                //如果为一级菜单,父菜单名称为子系统名称
                if (SysManageEnum.RESOURCE_ROOT.getCode().equals(item.getParentId())) {
                    item.setParentName(item.getSysSubsystemName());
                }
            });
        }
        return sysResourceList;
    }

    @Override
    public List<SysResourceForm> getAllResourceBySubsystemId(String subsystemId) {
        //根据subsystemId获取所有资源权限   用于前台Form表单根据子系统切换选项，需要携带父节点
        SysSubsystem sss = sysSubsystemMapper.selectById(subsystemId);
        if (sss == null) {
            sss = sysSubsystemMapper.getSysSubsystemAppId(subsystemId);
            if (sss == null) {
                log.warn("未找到子系统：{}", subsystemId);
                return Lists.newArrayList();
            }
        }
        // 获取当前用户有的角色
        List<String> roleIdList = UserUtil.getLoginUserRoleIdList();
        if (CollectionUtils.isEmpty(roleIdList)) {
            log.warn("当前用户【{}】没有角色", UserUtil.getLoginUserName());
            return Lists.newArrayList();
        }
        List<SysResourceForm> sysResourceForms = this.getResourceByRoleIdsOrRoleCodesOrAppId(roleIdList, Lists.newArrayList(sss.getAppId()), true, true);

        // 构造树节点  无法使用树工具，所以直接复制树工具的代码
        SysResourceForm sysResourceForm = toTree(sysResourceForms, SysManageEnum.RESOURCE_ROOT.getCode());
        if (sysResourceForm != null) {
            return sysResourceForm.getChildList();
        }
        return Lists.newArrayList();
    }

    /**
     * 将数据转为树结构
     *
     * @param basicTreeBeans 继承BasicTreeBean的数据
     * @param rootLevelId    需要的根节点ID
     * @return 根节点
     */
    private SysResourceForm toTree(List<SysResourceForm> basicTreeBeans, String rootLevelId) {
        if (!CollectionUtils.isEmpty(basicTreeBeans)) {
            // 1. 准备基础数据
            // 根节点
            SysResourceForm basicTreeBean = null;
            // 一级节点
            List<SysResourceForm> basicTreeSets = Lists.newArrayList();
            // 其他节点
            Multimap<String, SysResourceForm> multiMap = ArrayListMultimap.create();
            for (SysResourceForm tree : basicTreeBeans) {
                if (tree != null) {
                    if (rootLevelId.equals(tree.getId())) {
                        // 跟节点
                        basicTreeBean = tree;
                    } else if (rootLevelId.equals(tree.getParentId())) {
                        // 一级节点
                        basicTreeSets.add(tree);
                    } else {
                        // 其他节点
                        multiMap.put(tree.getParentId(), tree);
                    }
                }
            }
            if (basicTreeBean == null) {

                basicTreeBean = new SysResourceForm();
                basicTreeBean.setId(SysManageEnum.RESOURCE_ROOT.getCode());
                basicTreeBean.setResourceName("菜单根节点");
//                throw new SofnException("根节点信息不能为空");
            }
            if (CollectionUtils.isEmpty(basicTreeSets)) {
                basicTreeSets = Lists.newArrayList();
            }

            // 2. 递归生成树
            toTree(basicTreeSets, multiMap);
            basicTreeBean.setChildList(basicTreeSets);
            return basicTreeBean;
        }

        return null;
    }

    /**
     * 递归生成tree
     *
     * @param basicTreeSets 上级节点
     * @param multiMap      所有的子节点
     */
    private void toTree(List<SysResourceForm> basicTreeSets, Multimap<String, SysResourceForm> multiMap) {
        if (!CollectionUtils.isEmpty(basicTreeSets)) {
            for (SysResourceForm basicTreeSet : basicTreeSets) {
                String id = basicTreeSet.getId();
                List<SysResourceForm> basicTreeBeans = (List<SysResourceForm>) multiMap.get(id);
                toTree(basicTreeBeans, multiMap);
                basicTreeSet.setChildList(basicTreeBeans);
            }
        }
    }


    @Override
    public List<SysResourceForm> getAllResourceByAppIdWithOutPermission(String appId, String resourceName) {

        Map<String, Object> params = Maps.newHashMap();
        params.put("resourceName", resourceName);
        params.put("appId", appId);
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        List<SysResourceForm> sysResourceByCondition = sysResourceMapper.getSysResourceByCondition(params);
        if (!CollectionUtils.isEmpty(sysResourceByCondition)) {
            // 构造树
            SysResourceForm sysResourceForm = toTree(sysResourceByCondition, SysManageEnum.RESOURCE_ROOT.getCode());
            if (sysResourceForm != null) {
                return sysResourceForm.getChildList();
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<SysResourceForm> getAllResourceByAppId(String appId, String resourceName) {
        Set<String> appIds = Sets.newHashSet();
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        SysSubsystem subsystem = sysSubsystemMapper.getSysSubsystemAppId(appId);
        //获取可用的子系统集合
        List<SysSubsystem> sysSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(params);
        appIds.add(appId);
        ////获取所有和查询系统有关的子系统
        for (SysSubsystem sysSubsystem : sysSubsystemList) {
            String[] ids = sysSubsystem.getParentIds().split(SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
            for (String id : ids) {
                if (id.equals(subsystem.getId())) {
                    appIds.add(sysSubsystem.getAppId());
                }
            }
        }

        List<String> loginUserRoleIdList = UserUtil.getLoginUserRoleIdList();
        if (CollectionUtils.isEmpty(loginUserRoleIdList)) {
            throw new SofnException("当前用户没有角色！");
        }

        // 获取用户有的资源
        List<SysResourceForm> allRoleResourceList = this.getResourceByRoleIdsOrRoleCodesOrAppId(loginUserRoleIdList, Lists.newArrayList(appIds), true, true);
        SysResource sysResource = sysResourceMapper.selectById(SysManageEnum.RESOURCE_ROOT.getCode());
        allRoleResourceList.add(new SysResourceForm(sysResource));

        List<SysResourceForm> resultInfo = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(allRoleResourceList)) {
            resultInfo.addAll(allRoleResourceList);
        }
        // 筛选出名字符合要求的和其父节点
        if (StringUtils.isNotBlank(resourceName)) {
            if (!CollectionUtils.isEmpty(allRoleResourceList)) {
                Set<String> parentIds = Sets.newHashSet();
                allRoleResourceList.forEach(item -> {
                    // 使用模糊查询
                    if (item.getResourceName().contains(resourceName)) {
                        resultInfo.add(item);
                        String parentIds1 = item.getParentIds();
                        if (StringUtils.isNotBlank(parentIds1)) {
                            parentIds.addAll(Lists.newArrayList(parentIds1.split(SysManageEnum.TREE_NODE_SPLIT_STR.getCode())));
                        }
                    }
                });
                // 加上父节点
                resultInfo.addAll(allRoleResourceList.stream().filter(item -> parentIds.contains(item.getId())).collect(Collectors.toList()));
            }
        }
        SysResourceForm sysResourceForm = toTree(resultInfo, SysManageEnum.RESOURCE_ROOT.getCode());
        if (sysResourceForm != null) {
            return sysResourceForm.getChildList();
        }
        return Lists.newArrayList();

    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param sysResourceFormList 分类表
     * @param parentId            传入的父节点ID
     * @return String
     * @
     */
    @Override
    public List<SysResourceForm> getChildPerms(List<SysResourceForm> sysResourceFormList, String parentId, String appId) {
        if (!CollectionUtils.isEmpty(sysResourceFormList)) {
            Set<String> parentIdStrings = Sets.newHashSet();
            // 将parentIdStr找出来然后按照长度排序
            List<String> oldParentIdStrings = sysResourceFormList.stream().map(SysResourceForm::getParentIds).sorted((o1, o2) -> {
                if (StringUtils.isNotBlank(o1) && StringUtils.isNotBlank(o2)) {
                    return o1.length() - o2.length();
                }
                return 0;
            }).collect(Collectors.toList());
            // 这里排除一下根节点，不然就会所有都查询出来
            oldParentIdStrings.remove(SysManageEnum.RESOURCE_ROOT.getCode() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode());

            // 0/aaa/vvv/
            // 0/aaa/vvv/aaa
            // 如上述二个parentIdStrs，如果0/aaa/vvv/需要查询子节点，那么肯定是包含了以0/aaa/vvv/aaa开头的子节点的，0/aaa/vvv/aaa就没必要查询
            for (String oldParentIdString : oldParentIdStrings) {
                if (StringUtils.isNotBlank(oldParentIdString)) {
                    Optional<String> first = parentIdStrings.stream().filter(oldParentIdString::contains).findFirst();
                    if (!first.isPresent()) {
                        parentIdStrings.add(oldParentIdString);
                    }
                }

            }
            if (!CollectionUtils.isEmpty(parentIdStrings)) {
                List<SysResourceForm> resourceInfoByParentIdStrings = sysResourceMapper.getResourceInfoByParentIdStrings(Lists.newArrayList(parentIdStrings), appId);
                if (!CollectionUtils.isEmpty(resourceInfoByParentIdStrings)) {
                    Set<SysResourceForm> sysResourceFormSet = Sets.newHashSet();
                    sysResourceFormSet.addAll(sysResourceFormList);
                    sysResourceFormSet.addAll(resourceInfoByParentIdStrings);
                    return Lists.newArrayList(sysResourceFormSet);

                }
            }
        }
        return sysResourceFormList;
    }


//    @Override
//    public List<SysSubsystemForm> getAllRoleResource() {
//        List<String> loginUserRoleIdList = UserUtil.getLoginUserRoleIdList();
//        if(CollectionUtils.isEmpty(loginUserRoleIdList)){
//           throw new SofnException("当前用户没有角色！");
//        }
//        // 获取当前用户有的菜单包含父节点
//        List<SysResourceForm> allRoleResourceList =  this.getResourceByRoleIdsOrRoleCodesOrAppId(loginUserRoleIdList,null,true);
//
//        // 找子节点
//        List<SysResourceForm> resourceTree = getChildPerms(allRoleResourceList, SysManageEnum.RESOURCE_ROOT.getCode());
//
//
//
//        //获取当前用户有的系统树信息
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
//        List<SysSubsystem> sysSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(params);
//        List<SysSubsystemForm> sysSubsystemFormList = new ArrayList<>();
//        SysSubsystemServiceImpl.getSysSubsystemFormList(sysSubsystemList, sysSubsystemFormList, sysSubsystemMapper);
//        List<SysSubsystemForm> subsystemList = tree(sysSubsystemFormList, SysManageEnum.SUBSYSTEM_ROOT.getCode(), resourceTree);
//        //资源结构树
//        return subsystemList;
//    }

//    private List<SysSubsystemForm> tree(List<SysSubsystemForm> allList, String treeRoot, List<SysResourceForm> resourceTree) {
//        List<SysSubsystemForm> resultList = new ArrayList<>();
//        for (SysSubsystemForm sysSubsystem : allList) {
//            if (sysSubsystem.getParentId().equals(treeRoot)) {
//                resultList.add(sysSubsystem);
//            }
//        }
//        //一级子系统
//        for (SysSubsystemForm sysSubsystemForm : resultList) {
//            //无下级子系统
//            if (getSublist(sysSubsystemForm.getId(), allList, resourceTree) == null) {
//                List<SysResourceForm> resourceChildList = new ArrayList<>();
//                for (SysResourceForm resourceform : resourceTree) {
//                    //判断子系统Appid和每个资源的AppId是否相同
//                    if (sysSubsystemForm.getAppId().equals(resourceform.getAppId())) {
//                        resourceChildList.add(resourceform);
//                    }
//                }
//                sysSubsystemForm.setSysResourceList(resourceChildList);
//            } else {
//                sysSubsystemForm.setChildList(getSublist(sysSubsystemForm.getId(), allList, resourceTree));
//            }
//        }
//        return resultList;
//    }

//    private List<SysSubsystemForm> getSublist(String sysSubsystemId, List<SysSubsystemForm> allList, List<SysResourceForm> resourceTree) {
//        List<SysSubsystemForm> childSystem = new ArrayList<>();
//        for (SysSubsystemForm child : allList) {
//            //有下级子系统
//            if (sysSubsystemId.equals(child.getParentId())) {
//                childSystem.add(child);
//            }
//        }
//
//        for (SysSubsystemForm sysChild : childSystem) {
//            sysChild.setChildList(getSublist(sysChild.getId(), allList, resourceTree));
//        }
//
//        if (childSystem.isEmpty() || childSystem.size() == 0) {
//            return null;
//        }
//        return childSystem;
//    }

//    /**
//     * 获取当前用户有的资源
//     * @return List<SysResourceForm>
//     */
//    private List<SysResourceForm> getUserResourceList() {
//        // 获取当前用户有的角色
//        List<String> roleIdList = UserUtil.getLoginUserRoleIdList();
//        if (CollectionUtils.isEmpty(roleIdList)) {
//            log.warn("当前用户【{}】没有角色", UserUtil.getLoginUserName());
//            return Lists.newArrayList();
//        }
//        // 根据角色获取菜单
//        List<SysResourceForm> sysResourceForms;
//        if (permissionSuperAdminService.isSuperman()) {
//            // 超级管理员
//            sysResourceForms = this.getResourceByRoleIdsOrRoleCodesOrAppId(null,null,false);
//
//        } else {
//            // 普通用户
//            sysResourceForms = this.getResourceByRoleIdsOrRoleCodesOrAppId(roleIdList,null);
//            // 普通用户需要在查询出父节点
//            if (!CollectionUtils.isEmpty(sysResourceForms)) {
//                Set<String> parentIds = Sets.newHashSet();
//                sysResourceForms.forEach(item -> {
//                    String parentIdStrs = item.getParentIds();
//                    if (StringUtils.isNotBlank(parentIdStrs)) {
//                        String[] split = parentIdStrs.split(SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
//                        parentIds.addAll(Lists.newArrayList(split));
//                    }
//                });
//                // 去重
//                List<String> collect = sysResourceForms.stream().map(SysResourceForm::getId).collect(Collectors.toList());
//                parentIds.removeAll(collect);
//                if (!CollectionUtils.isEmpty(parentIds)) {
//                    // 根据parentIds查询出节点信息
//                    GetAllInfoByPageUtil<SysResourceForm> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();
//                    List<SysResourceForm> sysResourceFormList = getAllInfoByPageUtil.getAllInfo((param) -> sysResourceMapper.getListByIds((List) param[0]),
//                            0, false, "", Lists.newArrayList(parentIds));
//                    if (!CollectionUtils.isEmpty(sysResourceFormList)) {
//                        sysResourceForms.addAll(sysResourceFormList);
//                    }
//                }
//            }
//        }
//        return sysResourceForms;
//
//
//    }

//    @Override
//    public List<SysResourceForm> getSysResourceTreeList(String resourceName) {
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
//        params.put("resourceName", resourceName);
//        List<SysResource> sysResourceList = sysResourceMapper.getSysResourceByCondition(params);
//        List<SysResourceForm> resourceFormList = new ArrayList<>();
//        for (int i = 0; i < sysResourceList.size(); i++) {
//            //获取父菜单名
//            SysResourceForm sysResourceForm = new SysResourceForm(sysResourceList.get(i));
//            SysSubsystem sysSubsystem = sysSubsystemMapper.getSysSubsystemAppId(sysResourceList.get(i).getAppId());
//            SysResource sysResourceParent = sysResourceMapper.selectById(sysResourceList.get(i).getParentId());
//            //如果为一级菜单,父菜单名称为子系统名称
//            if (sysResourceList.get(i).getParentId().equals(SysManageEnum.RESOURCE_ROOT.getCode())) {
//                sysResourceForm.setParentName(sysSubsystem.getSubsystemName());
//            } else {
//                sysResourceForm.setParentName(sysResourceParent.getResourceName());
//            }
//            sysResourceForm.setSysSubsystemName(sysSubsystem.getSubsystemName());
//            resourceFormList.add(sysResourceForm);
//        }
//        List<SysResourceForm> resourceTree = getChildPerms(resourceFormList, SysManageEnum.RESOURCE_ROOT.getCode());
//        return resourceTree;
//
//    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importResource(List<SysResourceBatchSaveVo> sysResourceBatchSaveVos) {
        String appId = checkImport(sysResourceBatchSaveVos);
        // 根据数据的上下级关系生成真实数据
        // 数据中的父菜单名称必须有在数据库真实存在的父节点
        Set<String> parentNames = sysResourceBatchSaveVos.stream().map(SysResourceBatchSaveVo::getParentName).collect(Collectors.toSet());
        // 根节点不属于任何系统
        SysResource rootSysResource = null;
        if (parentNames.contains(SysManageEnum.RESOURCE_ROOT.getDescription())) {
            rootSysResource = sysResourceMapper.selectById(SysManageEnum.RESOURCE_ROOT.getCode());
            parentNames.remove(SysManageEnum.RESOURCE_ROOT.getDescription());
        }

        QueryWrapper<SysResource> eq = new QueryWrapper<>();
        if(!CollectionUtils.isEmpty(parentNames)){
            eq.in("RESOURCE_NAME", parentNames);
        }
        if(StringUtils.isNotBlank(appId)){
            eq.eq("APP_ID", appId);
        }
        List<SysResource> resourceName = sysResourceMapper.selectList(eq);
        resourceName.add(rootSysResource);
        if (CollectionUtils.isEmpty(resourceName)) {
            throw new SofnException("导入的数据的父菜单名称必须有一个数据库中存在的菜单名称，如果是根节点下的菜单，请输入父菜单名称为：菜单根节点");
        }

        Set<SysResource> dontDelParentResourceInfo = Sets.newHashSet();
        resourceName.forEach(item->{
            if(item != null && !BoolUtils.Y.equals(item.getDelFlag())){
                dontDelParentResourceInfo.add(item);
            }
        });
//        resourceName.stream().filter(item -> !BoolUtils.Y.equals(item.getDelFlag()));
//        Set<SysResource> dontDelParentResourceInfo = resourceName.stream().filter(item -> !BoolUtils.Y.equals(item.getDelFlag()))
//                .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(dontDelParentResourceInfo)) {
            throw new SofnException("导入的数据的父菜单名称必须有一个数据库中存在的菜单名称，如果是根节点下的菜单，请输入父菜单名称为：菜单根节点");
        }
        //找出第一级菜单并且设置值
        List<SysResource> allResource = Lists.newArrayList();
        allResource.addAll(dontDelParentResourceInfo);
        // 设置剩下的菜单的信息  根据权限去重
        List<SysResourceBatchSaveVo> collect = sysResourceBatchSaveVos.stream()
                .filter(item -> !allResource.stream().map(SysResource::getPermission).collect(Collectors.toSet()).contains(item.getPermission()))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            // 循环获取子节点信息
            getRealData(collect, allResource);
        }
        allResource.removeAll(dontDelParentResourceInfo);
        if (!CollectionUtils.isEmpty(allResource)) {
            // 批量添加
            sysResourceMapper.batchSave(allResource);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 批量删除直接删除下面的子节点
        List<SysResource> sysResourceList = sysResourceMapper.selectList(new QueryWrapper<SysResource>().in("ID", ids));
        if (CollectionUtils.isEmpty(sysResourceList)) {
            throw new SofnException("没有任何需要删除的信息！");
        }
        // 排除掉根节点，根节点不能直接删除
        if (ids.contains(SysManageEnum.RESOURCE_ROOT.getCode())) {
            throw new SofnException("根节点不能删除");
        }
        // 根据查询出来的信息组装ParentIds
        Set<String> collect = sysResourceList.stream().map(item -> item.getParentIds() + item.getId()).collect(Collectors.toSet());
        // 查询需要删除的子节点
        List<SysResource> sysResourceByParentStrIds = sysResourceMapper.getSysResourceByParentStrIds(collect);
        Set<String> delIds = Sets.newHashSet();
        delIds.addAll(ids);
        if (!CollectionUtils.isEmpty(sysResourceByParentStrIds)) {
            delIds.addAll(sysResourceByParentStrIds.stream().map(SysResource::getId).collect(Collectors.toSet()));
        }
        if (!CollectionUtils.isEmpty(delIds)) {
            sysResourceMapper.batchDelete(delIds, UserUtil.getLoginUserId(), new Date());
            // 清理菜单角色关系
            roleResourceMapper.batchDeleteByResourceIds(Lists.newArrayList(delIds));
        }
    }

    @Override
    public void updateParentIdsByIds(List<String> ids, String newParentIdPrefix, String oldParentIdPrefix) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        if (StringUtils.isBlank(newParentIdPrefix) || StringUtils.isBlank(oldParentIdPrefix)) {
            return;
        }
        sysResourceMapper.updateParentIdsByIds(ids, newParentIdPrefix, oldParentIdPrefix);
    }

    @Override
    public List<RoleHaveResourceVo> getResourceByRoleIdsOrRoleCodesOrAppId(List<String> roleIdsOrRoleCodes, List<String> appIds) {
        // TODO  这个方法可以做缓存  可以先从缓存中拿一下，然后找出没有的，然后再查询数据库，然后再放入缓存中   如果是超级管理员需要额外组装数据
        // 如果roleIdsOrRoleCodes为空或者包含了超级管理员的角色代码就只查询超级管理员的
        List<RoleHaveResourceVo> roleHaveResourceVos = Lists.newArrayList();
        if (roleIdsOrRoleCodes.contains(SysManageEnum.DEVELOPER_ROLE_ID.getCode()) ||
                roleIdsOrRoleCodes.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())
                || CollectionUtils.isEmpty(roleIdsOrRoleCodes)) {
            // 直接查询所有
            List<SysResourceForm> resourceInfoByAppId = sysResourceMapper.getResourceInfoByAppId(appIds);
            RoleHaveResourceVo roleHaveResourceVo = new RoleHaveResourceVo();
            roleHaveResourceVo.setRoleId(SysManageEnum.DEVELOPER_ROLE_ID.getCode());
            roleHaveResourceVo.setRoleCode(SysManageEnum.DEVELOPER_ROLE_CODE.getCode());
            roleHaveResourceVo.setSysResources(resourceInfoByAppId);
            roleHaveResourceVos.add(roleHaveResourceVo);
        } else {
            roleHaveResourceVos = sysResourceMapper.getResourceByRoleIdsOrRoleCodesOrAppId(roleIdsOrRoleCodes, appIds);
        }
        return roleHaveResourceVos;
    }

    @Override
    public List<SysResourceForm> getResourceByRoleIdsOrRoleCodesOrAppId(List<String> roleIdsOrRoleCodes, List<String> appIds, boolean carryParent, boolean isMyCreate) {
        List<RoleHaveResourceVo> resourceByRoleIdsOrRoleCodesOrAppId = this.getResourceByRoleIdsOrRoleCodesOrAppId(Lists.newArrayList(roleIdsOrRoleCodes), appIds);
        if (!CollectionUtils.isEmpty(resourceByRoleIdsOrRoleCodesOrAppId)) {

            // 如果是超级管理员直接取出超级管理员的
            if (roleIdsOrRoleCodes.contains(SysManageEnum.DEVELOPER_ROLE_ID.getCode()) ||
                    roleIdsOrRoleCodes.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())
                    || CollectionUtils.isEmpty(roleIdsOrRoleCodes)) {
                RoleHaveResourceVo roleHaveResourceVo = resourceByRoleIdsOrRoleCodesOrAppId.get(0);
                if (roleHaveResourceVo == null) {
                    throw new SofnException("当前用户没有任何菜单权限");
                }
                List<SysResourceForm> sysResources = roleHaveResourceVo.getSysResources();
                return listSort(sysResources);
            }

            // 将角色对应的菜单去重
            Set<SysResourceForm> sysResourceFormList = Sets.newHashSet();
            resourceByRoleIdsOrRoleCodesOrAppId.forEach(item -> {
                List<SysResourceForm> sysResources = item.getSysResources();
                if (!CollectionUtils.isEmpty(sysResources)) {
                    sysResourceFormList.addAll(sysResources);
                }
            });
            if (isMyCreate) {
                List<SysResourceForm> userCreateResource = sysResourceMapper.getUserCreateResource(UserUtil.getLoginUserId());
                if (!CollectionUtils.isEmpty(userCreateResource)) {
                    sysResourceFormList.addAll(userCreateResource);
                }
            }

            // 可能会子节点有了之后父节点没有  加上是否包含父节点的判断
            if (carryParent) {
                getAllParent(sysResourceFormList, 0);
            }

            // 排序
            List<SysResourceForm> sysResourceForms = Lists.newArrayList(sysResourceFormList);
            return listSort(sysResourceForms);
        }
        return Lists.newArrayList();
    }


    /**
     * 获取所有的父节点， 递归查找直到将所有的父节点全部查找出来
     * @param i   最多查找次数 ,避免垃圾数据的出现导致程序死循环
     * @param sysResourceFormList  Set<SysResourceForm>
     */
    private void getAllParent(Set<SysResourceForm> sysResourceFormList, int i){
        if (!CollectionUtils.isEmpty(sysResourceFormList)) {
            i ++;
            // 找一下父节点
            Set<String> resourceIds = sysResourceFormList.stream().map(SysResourceForm::getId).collect(Collectors.toSet());
            Set<String> allParentId = Sets.newHashSet();
            sysResourceFormList.forEach(item -> {
                if (item != null) {
                    String tempParentIds = item.getParentIds();
                    if (StringUtils.isNotBlank(tempParentIds)) {
                        List<String> idsByStr = IdUtil.getIdsByStr(tempParentIds, SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
                        allParentId.addAll(idsByStr);
                    }
                }
            });
            allParentId.removeAll(resourceIds);
            if (!CollectionUtils.isEmpty(allParentId)) {
                allParentId.remove(SysManageEnum.RESOURCE_ROOT.getCode());
                if (!CollectionUtils.isEmpty(allParentId)) {
                    List<SysResourceForm> resourceInfoByIds = getResourceInfoByIds(Lists.newArrayList(allParentId));
                    if (!CollectionUtils.isEmpty(resourceInfoByIds)) {
                        sysResourceFormList.addAll(resourceInfoByIds);
                        //  找父节点的父节点
                        if(i < 10){
                            getAllParent(sysResourceFormList, i );
                        }else{
                            log.warn("还有没有找到的父节点，ID为：{}", allParentId);
                        }
                    }
                }
            }
        }
    }




    /**
     * 将菜单排序
     *
     * @param sysResourceForms 待排序菜单
     */
    private List<SysResourceForm> listSort(List<SysResourceForm> sysResourceForms) {
        if (!CollectionUtils.isEmpty(sysResourceForms)) {
            try {
                // 筛选出不为空的进行比较！如果为空就不进行比较
                List<SysResourceForm> sysResourceFormCreateTimeIsNotNullList = Lists.newArrayList();
                List<SysResourceForm> sysResourceFormCreateTimeIsNullList = Lists.newArrayList();
                if (CollectionUtils.isEmpty(sysResourceForms)) {
                    return sysResourceForms;
                }
                sysResourceForms.forEach(item -> {
                    if (item.getCreateTime() == null) {
                        sysResourceFormCreateTimeIsNullList.add(item);
                    } else {
                        sysResourceFormCreateTimeIsNotNullList.add(item);
                    }
                });
                if (!CollectionUtils.isEmpty(sysResourceFormCreateTimeIsNotNullList)) {
                    sysResourceFormCreateTimeIsNotNullList.sort((o1, o2) -> {
                        // return o1 > o2 ? 1 : -1;
                        if (o1 == null || o2 == null) {
                            return -1;
                        }
                        if (o1.getCreateTime() == null || o2.getCreateTime() == null) {
                            return -1;
                        }
                        return o2.getCreateTime().compareTo(o1.getCreateTime());// 正确的方式
                    });
                }
                if (!CollectionUtils.isEmpty(sysResourceFormCreateTimeIsNullList)) {
                    sysResourceFormCreateTimeIsNotNullList.addAll(sysResourceFormCreateTimeIsNullList);
                }
                return sysResourceFormCreateTimeIsNotNullList;
            } catch (Exception e) {
                e.printStackTrace();
                return sysResourceForms;
            }
        }
        return sysResourceForms;
    }


    @Override
    public List<SysResourceForm> getResourceInfoByIds(List<String> resourceIds) {
        if (!CollectionUtils.isEmpty(resourceIds)) {
            GetAllInfoByPageUtil<SysResourceForm> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();
            List<SysResourceForm> sysResourceForms = getAllInfoByPageUtil.getAllInfo((param) -> sysResourceMapper.getListByIds((List) param[0]),
                    0, false, "", resourceIds);
            return sysResourceForms;
        }
        return Lists.newArrayList();
    }

    /**
     * 根据数据获取真实的数据
     *
     * @param sysResourceBatchSaveVos 导入的数据
     * @param allResource             目前已经有的节点信息
     */
    private void getRealData(List<SysResourceBatchSaveVo> sysResourceBatchSaveVos, List<SysResource> allResource) {
        Map<String, Boolean> isFindParent = new HashMap<>(10);
        sysResourceBatchSaveVos.forEach(item -> isFindParent.put(item.getPermission(), false));
        // 最多10层
        for (int i = 0; i < 10; i++) {
            for (SysResourceBatchSaveVo sysResourceBatchSaveVo : sysResourceBatchSaveVos) {
                // 找父节点
                Optional<SysResource> present = allResource.stream()
                        .filter(e -> e.getResourceName().equals(sysResourceBatchSaveVo.getParentName()))
                        .findFirst();
                if (present.isPresent()) {
                    // 根据permisison去重
                    boolean contains = allResource.stream().map(SysResource::getPermission).collect(Collectors.toSet()).contains(sysResourceBatchSaveVo.getPermission());
                    if (!contains) {
                        SysResource sysResource1 = present.get();
                        SysResource sysResource = SysResourceBatchSaveVo.getSysResource(sysResourceBatchSaveVo, sysResource1);
                        isFindParent.put(sysResource.getPermission(), true);
                        allResource.add(sysResource);
                    }
                }
            }
        }
        // 找出还没有找到父节点的数据
        Set<String> permissions = Sets.newHashSet();
        isFindParent.forEach((k, v) -> {
            if (!v) {
                permissions.add(k);
            }
        });
        if (!CollectionUtils.isEmpty(permissions)) {
            throw new SofnException(String.format("权限代码%s没有找到父节点", IdUtil.getStrIdsByList(Lists.newArrayList(permissions))));
        }
    }


    /**
     * 检查导入的数据是否符合规范
     *
     * @param sysResourceBatchSaveVos 导入的数据
     * @return 是哪个系统的菜单
     */
    private String checkImport(List<SysResourceBatchSaveVo> sysResourceBatchSaveVos) {
        // 1. 是否为空
        if (CollectionUtils.isEmpty(sysResourceBatchSaveVos)) {
            throw new SofnException("没有需要导入的数据");
        }
        // 2. 一次只能导入一个系统的菜单
        Set<String> collect = sysResourceBatchSaveVos.stream().map(SysResourceBatchSaveVo::getSubSystemCode).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(collect)) {
            throw new SofnException("没有需要导入的数据【没有找到子系统】");
        }
        if (collect.size() > 1) {
            throw new SofnException("一次只能导入一个系统的菜单");
        }
        // 3. 子系统是否存在
        String appId = Lists.newArrayList(collect).get(0);
        SysSubsystem sysSubsystemAppId = sysSubsystemMapper.getSysSubsystemAppId(appId);
        if (sysSubsystemAppId == null) {
            throw new SofnException("子系统不存在");
        }
        // 4. 名称可以重复 权限码不能重复
        List<String> permissionList = sysResourceBatchSaveVos.stream().map(SysResourceBatchSaveVo::getPermission).collect(Collectors.toList());
        Set<String> permissionSet = Sets.newHashSet(permissionList);
        if (permissionList.size() != permissionSet.size()) {
            throw new SofnException("文件中权限码有重复，请检查");
        }
        // 检查数据库中是否存在
        List<SysResource> dbPermission = sysResourceMapper.selectList(new QueryWrapper<SysResource>().in("PERMISSION", permissionSet));
        if (!CollectionUtils.isEmpty(dbPermission)) {
            List<SysResource> delPermission = dbPermission.stream().filter(item -> BoolUtils.Y.equals(item.getDelFlag())).collect(Collectors.toList());
            if (dbPermission.size() != delPermission.size()) {
                dbPermission.removeAll(delPermission);
                throw new SofnException(
                        String.format("权限码【%s】已经存在", IdUtil.getStrIdsByList(dbPermission.stream().map(SysResource::getPermission).collect(Collectors.toList())))
                );
            }
        }
        return appId;
    }

}
