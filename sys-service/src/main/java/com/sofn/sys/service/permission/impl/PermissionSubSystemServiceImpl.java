package com.sofn.sys.service.permission.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.treeutil.basic.TreeGenerate;
import com.sofn.common.utils.SqlEscapeUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysDictService;
import com.sofn.sys.service.impl.SysSubsystemServiceImpl;
import com.sofn.sys.service.permission.PermissionRoleGroupService;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SysSubsystemForm;
import com.sofn.sys.vo.SysSubsystemTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 子系统和树结构相关
 *
 * @author heyongjie
 * @date 2019/12/19 17:30
 */
@Service
@Slf4j
public class PermissionSubSystemServiceImpl implements PermissionSubSystemService {

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Autowired
    private PermissionRoleGroupService permissionRoleGroupService;

    @Autowired
    private PermissionRoleService permissionRoleService;

    @Autowired
    private SysDictService sysDictService;

    private TreeGenerate<SysSubsystemTreeVo> treeGenerate = new TreeGenerate<>(SysManageEnum.TREE_NODE_SPLIT_STR.getCode(),
            SysManageEnum.SUBSYSTEM_ROOT.getCode());

    /**
     * 所有的分系统
     */
    private volatile List<String> splitSystem = Lists.newArrayList();


    @Override
    public SysSubsystemTreeVo getUserHasSubsystem(String subsystemName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyword", SqlEscapeUtils.escape(subsystemName, SqlEscapeUtils.DB.ORACLE));
        return treeGenerate.toTreeByCondition(params, this, SysManageEnum.SUBSYSTEM_ROOT.getCode());
    }

    @Override
    public List<String> getUserHasSubsystemIds() {
        List<String> userAppIds = getUserAppIds();
        if(!CollectionUtils.isEmpty(userAppIds)){
            List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>().in("APP_ID", userAppIds)
                    .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
            if(CollectionUtils.isEmpty(sysSubsystems)){
                return sysSubsystems.stream().map(SysSubsystem::getId).collect(Collectors.toList());
            }
        }
        return Lists.newArrayList();
    }

    /**
     *
     * 根据已有的AppId判断是否含有拆分的子系统
     * @param appIds   已有的AppId
     * @return  getUserHaveSplitSystem
     */
    private List<String> getUserHaveSplitSystem(List<String> appIds){
        if(CollectionUtils.isEmpty(appIds)){
            return Lists.newArrayList();
        }
        List<String> haveSplitSystem = Lists.newArrayList();
        getSplitSystem();
        appIds.forEach(item->{
            Optional<String> first = splitSystem.stream().filter(item::contains).findFirst();
            first.ifPresent(haveSplitSystem::add);
        });
        return haveSplitSystem;
    }


    /**
     * 获取所有的已拆分的系统
     */
    private void getSplitSystem() {
        if(CollectionUtils.isEmpty(splitSystem)){
            synchronized (this){
                if(CollectionUtils.isEmpty(splitSystem)){
                    List<SysDict> dictListByType = sysDictService.getDictListByType(SysManageEnum.SPLIT_SYSTEM_DICT_TYPE.getCode());
                    if (!CollectionUtils.isEmpty(dictListByType)) {
                        List<String> collect = dictListByType.stream().map(SysDict::getDictcode).collect(Collectors.toList());
                        splitSystem.addAll(collect);
                    }
                }
            }
        }
    }



    /**
     * 获取登录用户所属系统的appid集合
     *
     * @return APPID集合
     */
    @Override
    public List<String> getUserAppIds() {
        // 获取用户所属系统
        return getUserAppIds(UserUtil.getLoginToken());
    }

    @Override
    public List<String> getUserAppIds(String token) {
        if (StringUtils.isBlank(token)) {
            throw new SofnException("TOKEN必传");
        }
        String loginUserId = UserUtil.getLoginUserId(token);
        if (StringUtils.isBlank(loginUserId)) {
            throw new SofnException("TOKEN失效");
        }

        // 添加缓存



        // 如果是超级管理员直接查询所有
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList(token);
        if (CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            throw new SofnException("当前用户没有任何角色");
        }
        if (loginUserRoleCodeList.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())) {
            List<SysSubsystem> subsystems = sysSubsystemMapper.selectList(null);
            if (!CollectionUtils.isEmpty(subsystems)) {
                List<SysSubsystem> collect = subsystems.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    Set<String> collect1 = collect.stream().map(SysSubsystem::getAppId).collect(Collectors.toSet());
                    return Lists.newArrayList(collect1);
                }
            }
        }
        // 根据角色获取系统代码
        List<String> userHaveAppId = sysSubsystemMapper.getUserHaveAppId(loginUserId);

        // 查询父节点
        List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(
                new QueryWrapper<SysSubsystem>().in("APP_ID", userHaveAppId)
                        .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode())
        );
        Set<String> parentIds = Sets.newHashSet();
        if(!CollectionUtils.isEmpty(sysSubsystems)){
            sysSubsystems.forEach(item->{
                String tempParentIds = item.getParentIds();
                String[] split = tempParentIds.split(SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
                parentIds.addAll(Arrays.asList(split));
            });
            if(!CollectionUtils.isEmpty(parentIds)){
                Map<String,Object> params = Maps.newHashMap();
                params.put("ids",parentIds);
                List<SysSubsystemTreeVo> sysSubsystemTreeVos = sysSubsystemMapper.selectSysSystemTreeVo(params);
                if(!CollectionUtils.isEmpty(sysSubsystemTreeVos)){
                    sysSubsystemTreeVos.forEach(item->{
                        if(!userHaveAppId.contains(item.getAppId())){
                            userHaveAppId.add(item.getAppId());
                        }
                    });
                }
            }
        }

        // 如果是拆分系统需要加上拆分系统
        List<String> userHaveSplitSystem = getUserHaveSplitSystem(userHaveAppId);
        if(!CollectionUtils.isEmpty(userHaveSplitSystem)){
            List<SysSubsystemTreeVo> systemInfoLikeAppIds = sysSubsystemMapper.getSystemInfoLikeAppIds(userHaveSplitSystem);
            systemInfoLikeAppIds.forEach(item->{
                if(!userHaveAppId.contains(item.getAppId())){
                    userHaveAppId.add(item.getAppId());
                }
            });
        }

        return userHaveAppId;
    }

    @Override
    public List<SysSubsystemForm> getUserHaveSystemList() {
        // 1. 获取用户有的角色
        List<String> userAppIds = getUserAppIds();
        if (!CollectionUtils.isEmpty(userAppIds)) {
            List<SysSubsystem> subsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>().in("APP_ID", userAppIds));
            return  SysSubsystemServiceImpl.getSysSubsystemForms(subsystems);
        }
        return null;
    }

    @Override
    public void checkUserCanAccessByAppId(List<String> appIds) {
        if(CollectionUtils.isEmpty(appIds)){
            throw new SofnException("当前用户没有可以访问的系统");
        }
        List<String> userAppIds = this.getUserAppIds();
        if(CollectionUtils.isEmpty(userAppIds)){
            throw new SofnException("当前用户没有访问当前子系统的权限");
        }
        appIds.forEach(item->{
            if(!userAppIds.contains(item)){
                log.info("当前用户【{}】无访问【{}】系统的权限，可以访问的系统为：【{}】",UserUtil.getLoginUserName(),item,userAppIds);
                throw new SofnException("当前用户没有访问当前子系统的权限");
            }
        });
    }

    @Override
    public void checkUserCanAccessById(List<String> ids) {



    }

    @Override
    public List<SysSubsystemTreeVo> getInfoByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("ids", ids);
            return sysSubsystemMapper.selectSysSystemTreeVo(params);
        }
        return null;
    }

    @Override
    public List<SysSubsystemTreeVo> getInfoByCondition(Map<String, Object> params) {
        // 当前方法是生成树结构的主要方法！ 所以这里先做一个权限限制之后再返回
        boolean isSuperman = permissionSuperAdminService.isSuperman();
        List<SysSubsystemTreeVo> sysSubsystemTreeVos;
        if (CollectionUtils.isEmpty(params)) {
            params = Maps.newHashMap();
        }
        if (isSuperman) {
            params.put("isSuperMan", "Y");
            sysSubsystemTreeVos = sysSubsystemMapper.selectUserHasSubsystem(params);
        } else {
            params.put("loginUserId", UserUtil.getLoginUserId());
            params.put("isSuperMan", "N");
            params.put("rootLevel", SysManageEnum.SUBSYSTEM_ROOT.getCode());
            // 获取用户拥有的角色
            List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRole();
            if (!CollectionUtils.isEmpty(loginUserHaveRole)) {
                Set<String> collect = loginUserHaveRole.stream().map(SysRoleVo::getId).collect(Collectors.toSet());
                params.put("roleIds", Lists.newArrayList(collect));
                //  getSysSubsystemForms
                sysSubsystemTreeVos = sysSubsystemMapper.selectUserHasSubsystem(params);

                // 需要判断是否是由一个子系统拆分成的子系统，如果是，需要加上
                if(!CollectionUtils.isEmpty(sysSubsystemTreeVos)){
                    List<String> allAppIds = sysSubsystemTreeVos.stream().map(SysSubsystemTreeVo::getAppId).collect(Collectors.toList());
                    List<String> userHaveSplitSystem = getUserHaveSplitSystem(allAppIds);
                    if(!CollectionUtils.isEmpty(userHaveSplitSystem)){
                        List<SysSubsystemTreeVo> systemInfoLikeAppIds = sysSubsystemMapper.getSystemInfoLikeAppIds(userHaveSplitSystem);
                        if(!CollectionUtils.isEmpty(systemInfoLikeAppIds)){
                            systemInfoLikeAppIds.forEach(item->{
                                if(!allAppIds.contains(item.getAppId())){
                                    sysSubsystemTreeVos.add(item);
                                }
                            });
                        }
                    }
                }
            } else {
                log.warn("登录用户【{}】没有任何角色", UserUtil.getLoginUserId());
                sysSubsystemTreeVos = null;
            }
        }
        if (!CollectionUtils.isEmpty(sysSubsystemTreeVos)) {
            return  sysSubsystemTreeVos;
        }
        return null;
    }

    @Override
    public void deleteByIds(List<String> ids) {
        log.warn("根据ID删除没有具体实现");
    }

    @Override
    public List<SysSubsystemTreeVo> getInfoByParentId(List<String> parentIds) {
        if (!CollectionUtils.isEmpty(parentIds)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("parentIdList", parentIds);
            return sysSubsystemMapper.selectSysSystemTreeVo(params);
        }
        return null;
    }

    @Override
    public List<SysSubsystemTreeVo> getInfoByLikeParentIds(String parentIds) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("parentIds", parentIds);
        return sysSubsystemMapper.selectSysSystemTreeVo(params);
    }

    @Override
    public void updateParentId(String parentIds, List<String> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            sysSubsystemMapper.updateParentId(parentIds, ids);
        }
    }
}
