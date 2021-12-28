package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.mq.MqMessage;
import com.sofn.common.mq.MqTopics;
import com.sofn.common.mq.rabbit.RabbitMqSendService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysOrgMapper;
import com.sofn.sys.mapper.SysSystemOrgMapper;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysSystemOrg;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysSplitUserOrgService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.SysUserForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 因为现在的SysOrgService会和SysUserService循环引用，所以把机构中要相互应用的给拆分成一个独立的Service
 * @author heyongjie
 * @date 2020/6/16 14:09
 */
@Slf4j
@Service
public class SysSplitUserOrgServiceImpl implements SysSplitUserOrgService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Autowired
    SysSystemOrgMapper sysSystemOrgMapper;

    @Autowired
    private SysOrgService sysOrgService;


    @Autowired
    private RabbitMqSendService<SysOrg> rabbitMqSendService;

    @Autowired
    PermissionSubSystemService permissionSubSystemService;


    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = {PermissionConstant.PERMISSION_ORG_KEY, PermissionConstant.PERMISSION_ORG_KEY})
    public void deleteOrg(String id) {
        SysOrg selectSysOrg = sysOrgMapper.selectById(id);
        List<SysSystemOrg> systemOrgList = sysSystemOrgMapper.getInfoByOrgId(id);
        if (selectSysOrg == null) {
            throw new SofnException("机构不存在");
        }

        // 有子节点的不能删
        Integer childCount = sysOrgMapper.selectCount(new QueryWrapper<SysOrg>()
                .ne("DEL_FLAG", BoolUtils.Y)
                .eq("PARENT_ID", id));

        if (childCount > 0) {
            throw new SofnException("当前机构下有子机构无法删除");
        }

        // 如果绑定了用户！ 那么也无法删除！ 先删除用户在删除机构
        if (sysUserService.hasUserByOrgId(id)) {
            throw new SofnException("当前机构有用户，请先删除用户");
        }

        // 使用软删除
        selectSysOrg.setDelFlag(BoolUtils.Y);
        selectSysOrg.preUpdate();
        sysOrgMapper.updateById(selectSysOrg);

        // 删除系统与机构关系
        sysSystemOrgMapper.delete(new UpdateWrapper<SysSystemOrg>().eq("ORG_ID", id));

        // 删除机构相关缓存
        SysCacheUtils.delCacheOrgByIds(id);

        try {
            if (!CollectionUtils.isEmpty(systemOrgList)){
                List<String> appIds = systemOrgList.stream().map(SysSystemOrg::getAppId).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(appIds)){
                    // 发送mq消息
                    rabbitMqSendService.send(MqTopics.SYS_ORG_DELETE, appIds, MqMessage.ok(selectSysOrg));
                }
            }
        } catch (Exception e) {
            log.error("send mq message failed");
        }
    }

    @Override
//    @RedisCache(key = PermissionConstant.PERMISSION_ORG_KEY + "getUserHaveOrgIds", isCarryParams = true)
    public List<String> getUserHaveOrgIds(String userId, String appId) {
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("用户ID不能为空");
        }
        // 1. 查询用户信息，如果传入的用户ID和当前登录用户ID相同就直接使用缓存的
        String loginUserId = UserUtil.getLoginUserId();
        String orgId = "";
        if (!StringUtils.isBlank(userId) && loginUserId.equals(userId)) {
            orgId = UserUtil.getLoginUserOrganizationId();
        } else {
            SysUser sysUser = sysUserService.getById(userId);
            if (sysUser == null || BoolUtils.Y.equals(sysUser.getDelFlag())) {
                throw new SofnException("用户不存在");
            }
            orgId = sysUser.getOrganizationId();
        }
        if (StringUtils.isEmpty(orgId)) {
            throw new SofnException("用户机构不存在");
        }
        List<String> appIds = Lists.newArrayList();
        // 2. 查询用户可访问的机构和代理机构(限制只能查看当前登录用户能够访问的子系统)
        if (StringUtils.isBlank(appId)) {
            appIds = permissionSubSystemService.getUserAppIds();
        } else {
            appIds.add(appId);
        }
        List<String> orgAndProxyOrg = sysOrgMapper.getOrgAndProxyOrg(orgId, appIds);
        if (CollectionUtils.isEmpty(orgAndProxyOrg)) {
            return null;
        }
        // 3. 查找机构下的子机构
        Set<String> childrenOrgByIds = sysOrgService.getChildrenOrgByIds(orgAndProxyOrg,appId);
        if (!CollectionUtils.isEmpty(childrenOrgByIds)) {
            orgAndProxyOrg.addAll(childrenOrgByIds);
        }
        return orgAndProxyOrg;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RedisDel(key = PermissionConstant.PERMISSION_ORG_KEY)
    public void batchDelete(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return;
        }
        if(ids.contains(SysManageEnum.ROOT_ORG.getCode())){
            throw new SofnException("机构根节点不能删除");
        }
        List<SysOrg> sysOrgs = sysOrgMapper.selectList(new QueryWrapper<SysOrg>().in("ID", ids));
        if(CollectionUtils.isEmpty(sysOrgs)){
            throw new SofnException("没有需要删除的机构");
        }
        Set<SysOrg> collect = sysOrgs.stream().filter(item -> SysManageEnum.DEL_FLAG_N.getCode().equals(item.getDelFlag())).collect(Collectors.toSet());
        if(CollectionUtils.isEmpty(collect)){
            throw new SofnException("没有需要删除的机构");
        }
        // 如果有用户则不能删除
        Map<String,Object> params = Maps.newHashMap();
        params.put("orgIds",ids);
        List<SysUserForm> userByCondition = sysUserService.getUserByCondition(params);
        if(!CollectionUtils.isEmpty(userByCondition)){
            Set<String> collect1 = userByCondition.stream().map(SysUserForm::getOrganizationName).collect(Collectors.toSet());
            throw new SofnException(String.format("机构【%s】下有用户，删除失败！",IdUtil.getStrIdsByList(Lists.newArrayList(collect1))));
        }
        // 找子节点
        Set<String> parentIdsStrs = collect.stream().map(item -> item.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + item.getId()).collect(Collectors.toSet());
        Set<String> childrenOrgByIds = sysOrgMapper.getChildrenOrgByIds(Lists.newArrayList(parentIdsStrs), null);
        if(!CollectionUtils.isEmpty(childrenOrgByIds)){
            params.put("orgIds",childrenOrgByIds);
            userByCondition = sysUserService.getUserByCondition(params);
            if(!CollectionUtils.isEmpty(userByCondition)){
                Set<String> collect1 = userByCondition.stream().map(SysUserForm::getOrganizationId).collect(Collectors.toSet());
                List<SysOrg> sysOrgs1 = sysOrgMapper.selectList(new QueryWrapper<SysOrg>().in("ID", collect1));
                if(CollectionUtils.isEmpty(sysOrgs1)){
                    throw new SofnException(String.format("机构【%s】为待删除机构的子机构，机构下有用户，删除失败！", IdUtil.getStrIdsByList(Lists.newArrayList(collect1))));
                }else{
                    Set<String> collect2 = sysOrgs1.stream().filter(item -> SysManageEnum.DEL_FLAG_N.getCode().equals(item.getDelFlag()))
                            .map(SysOrg::getOrganizationName).collect(Collectors.toSet());
                    throw new SofnException(String.format("机构【%s】为待删除机构的子机构，机构下有用户，删除失败！",IdUtil.getStrIdsByList(Lists.newArrayList(collect2))));
                }


            }
        }
        // 需要删除的机构ID
        List<String> delAllIds = Lists.newArrayList();
        delAllIds.addAll(childrenOrgByIds);
        delAllIds.addAll(ids);


        // 批量删除
        if(!CollectionUtils.isEmpty(delAllIds)){
            sysOrgMapper.batchDelete(Sets.newHashSet(delAllIds),UserUtil.getLoginUserId(),new Date());
        }
        // 删除绑定关系
        // 机构与系统绑定关系
        sysSystemOrgMapper.deleteByOrgIds(delAllIds);

        // 删除机构相关缓存
        SysCacheUtils.delCacheOrgByIds(delAllIds.toArray());
    }
}
