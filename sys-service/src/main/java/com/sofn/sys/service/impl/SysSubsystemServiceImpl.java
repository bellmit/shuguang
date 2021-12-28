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
import com.sofn.common.utils.RedisUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysResourceMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.mapper.SysSystemOrgMapper;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.model.SysSystemOrg;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.util.UserHaveAppIdUtil;
import com.sofn.sys.vo.SysResourceForm;
import com.sofn.sys.vo.SysSubsystemForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;
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
@SuppressWarnings({"ALL"})
@Service
@Slf4j
public class SysSubsystemServiceImpl extends ServiceImpl<SysSubsystemMapper, SysSubsystem>
        implements SysSubsystemService {

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysSystemOrgMapper sysSystemOrgMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_SUBSYSTEM_KEY, PermissionConstant.PERMISSION_ORG_KEY})
    public void addSysSubsystem(SysSubsystem sysSubsystem) {
        // 1. 校验内容是否重复
        checkSysSubsystemIsExists(sysSubsystem.getSubsystemName(), sysSubsystem.getAppId(), null);
        // 2. 设置默认值
        sysSubsystem.setId(IdUtil.getUUId());
        sysSubsystem.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        // 如果父ID为空 那么就是一级系统 在顶级系统之下
        if (sysSubsystem.getParentId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode()) || "".equals(sysSubsystem.getParentId())) {
            sysSubsystem.setParentIds(SysManageEnum.SUBSYSTEM_ROOT.getCode() + "/");
        } else {
            SysSubsystem subsystem = sysSubsystemMapper.selectById(sysSubsystem.getParentId());
            sysSubsystem.setParentIds(subsystem.makeSelfAsParentIds());
        }
        sysSubsystem.setCreateTime(new Date());
        sysSubsystem.setCreateUserId(UserUtil.getLoginUserId());

        // 3. 保存
        sysSubsystemMapper.insert(sysSubsystem);

        // 4. 将子系统绑定到更节点  ROOT_ORG
        List<SysSystemOrg> sysSystemOrgs = Lists.newArrayList(new SysSystemOrg(IdUtil.getUUId(), sysSubsystem.getAppId(), SysManageEnum.ROOT_ORG.getCode()));
        sysSystemOrgMapper.saveBatch(sysSystemOrgs);
        // 删除缓存
        deleteRedis(sysSubsystem.getParentId());
        SysCacheUtils.delSubsystemList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_SUBSYSTEM_KEY, PermissionConstant.PERMISSION_ORG_KEY})
    public void updateSysSubsystem(SysSubsystem sysSubsystem) {
        // 1. 校验内容是否存在
        SysSubsystem selectSysSubsystem = sysSubsystemMapper.selectById(sysSubsystem.getId());
        if (selectSysSubsystem == null || SysManageEnum.DEL_FLAG_Y.getCode().equals(selectSysSubsystem.getDelFlag())) {
            throw new SofnException("待修改内容不存在");
        }
        if (SysManageEnum.SUBSYSTEM_ROOT.getCode().equals(sysSubsystem.getId())) {
            throw new SofnException("根节点信息无法修改");
        }

        // 父ID不能是自己
        if (sysSubsystem.getId().equals(sysSubsystem.getParentId())) {
            throw new SofnException("父节点不能是自己");
        }

        SysSubsystem parentSysSubsystem = sysSubsystemMapper.selectById(sysSubsystem.getParentId());
        if(parentSysSubsystem == null){
            throw new SofnException("父节点不能为空");
        }

        // 如果父节点更改了需要将子节点也改了
        if(!StringUtils.isBlank(selectSysSubsystem.getParentId())){
            if(!selectSysSubsystem.getParentId().equals(sysSubsystem.getParentId())){
                List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>()
                        .eq("parent_id", sysSubsystem.getId())
                        .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
                if(!CollectionUtils.isEmpty(sysSubsystems)){
                    String oldParentIdsPrefix = selectSysSubsystem.getParentIds() + selectSysSubsystem.getId() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode();
                    String newParentIdsPrefix = parentSysSubsystem.getParentIds() + parentSysSubsystem.getId() +
                            SysManageEnum.TREE_NODE_SPLIT_STR.getCode() +sysSubsystem.getId() +  SysManageEnum.TREE_NODE_SPLIT_STR.getCode()  ;
                    List<String> ids = sysSubsystems.stream().map(SysSubsystem::getId).collect(Collectors.toList());
                    this.updateParentIdsByIds(ids,newParentIdsPrefix,oldParentIdsPrefix);
                }
            }
        }

        // 2. 校验内容是否重复
        checkSysSubsystemIsExists(sysSubsystem.getSubsystemName(), sysSubsystem.getAppId(), sysSubsystem.getId());
        //若更改了AppID,把该子系统下菜单权限的数据的appid都更改.
        String appId = sysSubsystem.getAppId();
        if (!appId.equals(selectSysSubsystem.getAppId())) {
           // TODO  :  根据AppId修改AppId

        }
        // 更改parentIds
        if(SysManageEnum.SUBSYSTEM_ROOT.getCode().equals(sysSubsystem.getParentId())){
            selectSysSubsystem.setParentIds(SysManageEnum.SUBSYSTEM_ROOT.getCode()+ SysManageEnum.TREE_NODE_SPLIT_STR.getCode() );
        }else{
            selectSysSubsystem.setParentIds(parentSysSubsystem.getParentIds() + parentSysSubsystem.getId()+ SysManageEnum.TREE_NODE_SPLIT_STR.getCode() );
        }


        // 3. 更改内容
        BeanUtils.copyProperties(sysSubsystem, selectSysSubsystem);
        selectSysSubsystem.setUpdateTime(new Date());
        selectSysSubsystem.setUpdateUserId(UserUtil.getLoginUserId());
        sysSubsystemMapper.updateById(selectSysSubsystem);

        // 4. 判断appId是否更改,如果AppId被更改就需要给机构根节点新绑定关系
        if (!selectSysSubsystem.getAppId().equals(sysSubsystem.getAppId())) {
            List<SysSystemOrg> sysSystemOrgs = Lists.newArrayList(new SysSystemOrg(IdUtil.getUUId(), sysSubsystem.getAppId(), SysManageEnum.ROOT_ORG.getCode()));
            sysSystemOrgMapper.saveBatch(sysSystemOrgs);
        }
        // 删除缓存
        deleteRedis(selectSysSubsystem.getParentId());
        SysCacheUtils.delSubsystemList();
    }

    @Override
    public void deleteSysSubsystem(String id) {
        SysSubsystem selectSysSubsystem = sysSubsystemMapper.selectById(id);
        // 2. 如果下面有子节点的不能删除
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("parentId", selectSysSubsystem.getId());
        List<SysSubsystem> sysSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(params);
        if (sysSubsystemList != null && sysSubsystemList.size() > 0) {
            throw new SofnException("当前系统下有子系统，删除失败");
        }

        //子系统下有菜单资源不能删除
        Map<String, Object> params2 = Maps.newHashMap();
        params2.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params2.put("appId", selectSysSubsystem.getAppId());
        List<SysResourceForm> sysResourceList = sysResourceMapper.getSysResourceByCondition(params2);
        if (sysResourceList != null && sysResourceList.size() > 0) {
            throw new SofnException("当前系统下有菜单资源，删除失败");
        }
        // 使用软删除
        selectSysSubsystem.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
        updateSysSubsystem(selectSysSubsystem);
        SysCacheUtils.delSubsystemList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRedis(String parentId) {
        try {
            // 删除缓存
            redisUtils.delete(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode());
            redisUtils.delete(SysManageEnum.SYS_SUBSYSTEM_CACHE_KEY.getCode());
            SysCacheUtils.delSubsystemTreeCache();
            // 删除父ID的缓存
            redisUtils.delete(SysManageEnum.SYS_SUBSYSTEM_CACHE_KEY.getCode() + "_" + parentId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("Redis异常");
        }
    }

    @Override
    public boolean checkSysSubsystemIsExists(String name, String appId, String id) {
        //检查是否重复
        if (id != null) {
            // 修改
            // 检查名称是否重复
            Integer number = sysSubsystemMapper.getSysSubsystemByNameOrAppId(name, null, id);
            if (number > 0) {
                throw new SofnException("系统名称重复");
            }
            // 检查Code是否重复
            number = sysSubsystemMapper.getSysSubsystemByNameOrAppId(null, appId, id);
            if (number > 0) {
                throw new SofnException("系统APPID重复");
            }
        } else {
            // 添加
            // 检查名称是否重复
            Integer number = sysSubsystemMapper.getSysSubsystemByNameOrAppId(name, null, null);
            if (number > 0) {
                throw new SofnException("系统名称重复");
            }
            // 检查Code是否重复
            number = sysSubsystemMapper.getSysSubsystemByNameOrAppId(null, appId, null);
            if (number > 0) {
                throw new SofnException("系统APPID重复");
            }
        }
        return false;
    }

    @Override
    public PageUtils<SysSubsystemForm> getSysSubsystemByContion(Map<String, Object> paramas, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SysSubsystem> sysSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(paramas);
        List<SysSubsystemForm> subsystemFormList = Lists.newArrayList();
        getSysSubsystemFormList(sysSubsystemList, subsystemFormList, sysSubsystemMapper);
        PageInfo<SysSubsystemForm> pageInfo = new PageInfo<>(subsystemFormList);
        return PageUtils.getPageUtils(pageInfo);
    }

    static void getSysSubsystemFormList(List<SysSubsystem> sysSubsystemList, List<SysSubsystemForm> subsystemFormList, SysSubsystemMapper sysSubsystemMapper) {
        for (SysSubsystem sysSubsystem : sysSubsystemList) {
            //获取父菜单名
            SysSubsystemForm sysSubsystemForm = new SysSubsystemForm(sysSubsystem);
            SysSubsystem sysSubsystemParent = sysSubsystemMapper.selectById(sysSubsystem.getParentId());
            if (sysSubsystem.getParentId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())) {
                sysSubsystemForm.setParentName(SysManageEnum.SUBSYSTEM_ROOT.getDescription());
            } else {
                sysSubsystemForm.setParentName(sysSubsystemParent.getSubsystemName());
            }
            subsystemFormList.add(sysSubsystemForm);
        }
    }

    @Override
    public SysSubsystemForm getSysSubsystemTree(String name) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("subsystemName", name);
        List<SysSubsystem> sysSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(params);
        if (CollectionUtils.isEmpty(sysSubsystemList)) {
            return null;
        }
        // 将当前用户有的子系统放在线程缓存中
        List<SysSubsystemForm> treeSysSubsystemList = tree(sysSubsystemList, SysManageEnum.SUBSYSTEM_ROOT.getCode());
        SysSubsystem systemRoot = new SysSubsystem();
        systemRoot.setId(SysManageEnum.SUBSYSTEM_ROOT.getCode());
        systemRoot.setSubsystemName(SysManageEnum.SUBSYSTEM_ROOT.getDescription());
        SysSubsystemForm systemRootForm = new SysSubsystemForm(systemRoot, UserHaveAppIdUtil.getValue());
        systemRootForm.setChildList(treeSysSubsystemList);
        SysCacheUtils.cacheSubsystemTree(treeSysSubsystemList);
        return systemRootForm;
    }

    private List<SysSubsystemForm> getSubsystemTree(List<SysSubsystem> sysSubsystemList) {
        Set<SysSubsystem> ssfSet = new HashSet<>();
        for (SysSubsystem ss : sysSubsystemList) {
            if (ss == null || StringUtils.isBlank(ss.getParentIds())) {
                continue;
            }
            // 获取父系统
            String[] ids = ss.getParentIds().split("/");
            List<SysSubsystem> ssfList = new ArrayList<>();
            for (String id : ids) {
                if (!id.equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())) {
                    SysSubsystem ssys = sysSubsystemMapper.selectById(id);
                    ssfList.add(ssys);
                }
            }
            ssfList.add(ss);
            ssfSet.addAll(ssfList);
        }

        List<SysSubsystem> ssfList2 = new ArrayList<>(ssfSet);
        return tree(ssfList2, SysManageEnum.SUBSYSTEM_ROOT.getCode());
    }

    @Override
    public SysSubsystemForm getSysSubsystemTreeByUser() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        List<String> roleIdList = UserUtil.getLoginUserRoleIdList();
        //获取所有角色的子系统
        List<SysSubsystem> allSubsystemList;
        if (CollectionUtils.isEmpty(roleIdList)) {
            throw new SofnException("当前用户没有角色！");
        }
        if (roleIdList.contains(SysManageEnum.DEVELOPER_ROLE_ID.getCode())) {
            System.out.println("you are admin,have all permission");
            allSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(params);
        } else {
            allSubsystemList = sysSubsystemMapper.getSysSubsystemByRoleId(roleIdList);
        }
        List<SysSubsystemForm> treeSysSubsystemList = getSubsystemTree(allSubsystemList);
        SysSubsystem systemRooot = new SysSubsystem();
        systemRooot.setId(SysManageEnum.SUBSYSTEM_ROOT.getCode());
        systemRooot.setSubsystemName(SysManageEnum.SUBSYSTEM_ROOT.getDescription());
        SysSubsystemForm systemRoootForm = new SysSubsystemForm(systemRooot);
        systemRoootForm.setChildList(treeSysSubsystemList);
        return systemRoootForm;
    }

    private List<SysSubsystemForm> tree(List<SysSubsystem> allList, String treeRoot) {
        // 找一级节点
        List<SysSubsystemForm> resultList = new ArrayList<>();
        for (SysSubsystem sysSubsystem : allList) {
            if (sysSubsystem != null) {
                SysSubsystemForm sysSubsystemForm = new SysSubsystemForm(sysSubsystem, UserHaveAppIdUtil.getValue());
                if (sysSubsystem.getParentId().equals(treeRoot)) {
                    resultList.add(sysSubsystemForm);
                }
            }

        }
        // 找根节点
        Optional<SysSubsystem> first = allList.stream().filter(item -> item.getId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())).findFirst();
        SysSubsystemForm sysSubsystemForm = null;
        if (first.isPresent()) {
            sysSubsystemForm = new SysSubsystemForm(first.get());
        }
        if (sysSubsystemForm == null) {
            throw new SofnException("未找到根节点信息");
        }
        for (SysSubsystemForm sysSubsystemForm2 : resultList) {
            if (sysSubsystemForm2.getId().equals(treeRoot)) {
                continue;
            }
            getSublist(sysSubsystemForm2, allList);
        }

        sysSubsystemForm.setChildList(resultList);
        return Lists.newArrayList(sysSubsystemForm);
    }

    private void getSublist(SysSubsystemForm sysSubsystemForm, List<SysSubsystem> allList) {
        // 找子节点

        List<SysSubsystemForm> sonSubsystems = allList.stream()
                .filter(item -> item.getParentId().equals(sysSubsystemForm.getId()))
                .map(item -> new SysSubsystemForm(item, UserHaveAppIdUtil.getValue()))
                .collect(Collectors.toList());


        if (!CollectionUtils.isEmpty(sonSubsystems)) {
            for (SysSubsystemForm child : sonSubsystems) {
                if (child != null) {
                    getSublist(child, allList);
                }
            }
        }
        sysSubsystemForm.setChildList(sonSubsystems);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_SUBSYSTEM_KEY})
    public void batchDeleteSubsystem(List<String> ids) {
        String msg;
        if (!CollectionUtils.isEmpty(ids)) {
            // 父ID 用于清理缓存
            Set<String> parentIds = Sets.newHashSet();
            List<String> subsystemNames = new ArrayList<>();
            // 如果有子节点不让删除
            ids.forEach((id) -> {
                Map<String, Object> params = Maps.newHashMap();
                params.put("parentId", id);
                params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
                SysSubsystem sysSubsystem = sysSubsystemMapper.selectById(id);
                List<SysSubsystem> sysSubsystemList = sysSubsystemMapper.getSysSubsystemByCondition(params);
                if (sysSubsystemList != null && sysSubsystemList.size() > 0) {
                    subsystemNames.add(sysSubsystem.getSubsystemName());
                }
                if (sysSubsystem != null) {
                    parentIds.add(sysSubsystem.getParentId());
                }
            });
            if (subsystemNames.size() > 0) {
                msg = "系统[" + String.join(",", subsystemNames) + "]下有子系统，删除失败";
                throw new SofnException(msg);
            } else {
                sysSubsystemMapper.batchDelete(ids, UserUtil.getLoginUserId(), new Date());
                try {
                    redisUtils.delete(SysManageEnum.SYS_SUBSYSTEM_CACHE_KEY.getCode());
                    // 删除父ID的缓存
                    if (!CollectionUtils.isEmpty(parentIds)) {
                        parentIds.forEach((str) -> {
                            redisUtils.delete(SysManageEnum.SYS_SUBSYSTEM_CACHE_KEY.getCode() + "_" + str);
                            redisUtils.delete(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode());
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SofnException("删除系统失败，缓存清理失败！");
                }
            }

            SysCacheUtils.delSubsystemList();
        }
    }

    @Override
    public List<SysSubsystem> getSubSystemByIds(String[] ids) {
        return sysSubsystemMapper.selectSubsystemByIds(ids);
    }

    @Override
    public List<String> getAllAppidList() {
        return sysSubsystemMapper.selectAllAppIdList();
    }

    @Override
    public List<SysSubsystem> getSysSubsystemTreeById(String parentId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("parentId", parentId);
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());

        return sysSubsystemMapper.getSysSubsystemByCondition(params);
    }

    @Override
    public SysSubsystem getSysSubsystemAppId(String appId) {

        return sysSubsystemMapper.getSysSubsystemAppId(appId);
    }

    @Override
    public SysSubsystem getSubsystemByIdOrAppId(String idOrAppId) {

        if (!StringUtils.isEmpty(idOrAppId)) {
            List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>().eq("ID", idOrAppId).or().eq("APP_ID", idOrAppId));
            if (!CollectionUtils.isEmpty(sysSubsystems)) {
                return sysSubsystems.get(0);
            }
        }
        return null;
    }

    @Override
    public List<SysSubsystemForm> getSubsystemByType(String type) {
        if (StringUtils.isBlank(type)) {
            throw new SofnException("菜单类型必传");
        }
        List<SysSubsystem> subsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>().eq("SYSTEM_TYPE", type));
        return getSysSubsystemForms(subsystems);
    }

    @Override
    public void updateParentIdsByIds(List<String> ids, String newParentIdPrefix, String oldParentIdPrefix) {
        if(CollectionUtils.isEmpty(ids)){
            return;
        }

        if(StringUtils.isBlank(newParentIdPrefix) || StringUtils.isBlank(oldParentIdPrefix)){
            return;
        }
        sysSubsystemMapper.updateParentIdsByIds(ids,newParentIdPrefix,oldParentIdPrefix);
        SysCacheUtils.delSubsystemList();
    }

    @Override
    public List<SysSubsystem> getSubsystemListByIds(Set<String> ids) {
        Map<String,SysSubsystem> map = SysCacheUtils.getSubSubsystemList(ids);
        if (CollectionUtils.isEmpty(map)){
            getAllSubsystemList();
            map = SysCacheUtils.getSubSubsystemList(ids);
        }

        List<SysSubsystem> subsystemList = null;
        if (CollectionUtils.isEmpty(map)){
            subsystemList = map.values().stream().collect(Collectors.toList());
        }

        return subsystemList;
    }

    @Override
    public List<SysSubsystem> getAllSubsystemList() {
        Map<String,SysSubsystem> map = SysCacheUtils.getSubsystemList();
        List<SysSubsystem> list = null;
        if (CollectionUtils.isEmpty(map)) {
            list = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>()
                .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
            SysCacheUtils.cacheSubsystemListById(list);
        }else {
            list = map.values().stream().collect(Collectors.toList());
        }

        return list;
    }

    @Nullable
    public static List<SysSubsystemForm> getSysSubsystemForms(List<SysSubsystem> subsystems) {
        if (!CollectionUtils.isEmpty(subsystems)) {
            List<SysSubsystem> dontDeleteInfo = subsystems.stream()
                    .filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(dontDeleteInfo)) {
                return dontDeleteInfo.stream().map(SysSubsystemForm::new).collect(Collectors.toList());
            }
        }
        return null;
    }

}
