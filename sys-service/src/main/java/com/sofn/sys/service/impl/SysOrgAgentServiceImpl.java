package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysOrgAgentMapper;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysOrgAgent;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysOrgAgentService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.vo.OrgAndProOrgInfoVo;
import com.sofn.sys.vo.SysOrgAgentForm;
import com.sofn.sys.vo.SysOrgAgentStatusVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@SuppressWarnings("ALL")
public class SysOrgAgentServiceImpl extends ServiceImpl<SysOrgAgentMapper, SysOrgAgent>
        implements SysOrgAgentService {

    @Autowired
    private SysOrgAgentMapper sysOrgAgentMapper;

    @Autowired
    private SysSubsystemService sysSubsystemService;

//    @Autowired
//    private SysOrgService sysOrgService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void allotOrgAgent(SysOrgAgentForm sysOrgAgentForm) {
        // 子系统是否存在
        SysSubsystem sysSubsystem = sysSubsystemService.getSubsystemByIdOrAppId(sysOrgAgentForm.getSubsystemId());
        if (sysSubsystem == null) {
            throw new SofnException("子系统不存在");
        }
        if (SysManageEnum.DEL_FLAG_Y.getCode().equals(sysSubsystem.getDelFlag())) {
            log.info("子系统ID={}", sysOrgAgentForm.getSubsystemId());
            throw new SofnException("子系统已经删除");
        }
        // 删除已有的代理关系
        UpdateWrapper<SysOrgAgent> eq = new UpdateWrapper<SysOrgAgent>()
                .eq("SUBSYSTEM_ID", sysOrgAgentForm.getSubsystemId())
                .eq("ORG_ID", sysOrgAgentForm.getOrgId());
        if (!StringUtils.isBlank(sysOrgAgentForm.getAgentCode())) {
            eq.eq("AGENT_CODE", sysOrgAgentForm.getAgentCode());
        }
        sysOrgAgentMapper.delete(eq);
        if (CollectionUtils.isEmpty(sysOrgAgentForm.getAgentOrgIds())) {
            //  代理机构ID列表为空清空对应的代理机构
            return;
        }
        List<SysOrgAgentStatusVo> sysOrgAgentStatusVos = sysOrgAgentForm.getSysOrgAgentStatusVos();
        if (CollectionUtils.isEmpty(sysOrgAgentStatusVos)) {
            throw new SofnException("是否启用状态信息为空");
        }

        // 添加新的代理关系
        List<SysOrgAgent> sysOrganizationAgents = Lists.newArrayList();
        // 并去重
        List<String> tempLists = Lists.newArrayList();
        sysOrgAgentForm.getAgentOrgIds().forEach(agentOrgId -> {
            if (!StringUtils.isBlank(agentOrgId)) {
                if (!tempLists.contains(agentOrgId)) {
                    tempLists.add(agentOrgId);
                    SysOrgAgent orgAgent = new SysOrgAgent(sysOrgAgentForm.getSubsystemId(), sysOrgAgentForm.getOrgId(), agentOrgId, sysOrgAgentForm.getAgentCode());
                    Optional<SysOrgAgentStatusVo> first = sysOrgAgentStatusVos.stream().filter(item -> item.getId().equals(agentOrgId)).findFirst();
                    if (first.isPresent()) {
                        SysOrgAgentStatusVo sysOrgAgentStatusVo = first.get();
                        orgAgent.setStatus(sysOrgAgentStatusVo.getStatus());
                    } else {
                        throw new SofnException("未找到ID为" + agentOrgId + "的启用状态");
                    }
                    orgAgent.setId(IdUtil.getUUId());
                    sysOrganizationAgents.add(orgAgent);
                }


            }
        });
        if (!CollectionUtils.isEmpty(sysOrganizationAgents)) {
            sysOrgAgentMapper.batchInserInfo(sysOrganizationAgents);
        }
    }

    @Override
    public List<SysOrg> selectOrgAgentByOrgId(String subsystemId, String orgId, String agentCode) {
        return sysOrgAgentMapper.selectOrgAgentByOrgId(subsystemId, orgId, agentCode);
    }

    @Override
    public List<OrgAndProOrgInfoVo> getOrgAndProOrgInfo(Map<String, String> params) {
        if (!CollectionUtils.isEmpty(params)) {
            return sysOrgAgentMapper.getOrgAndProOrgInfo(params);
        }
        return null;
    }

    @Override
    public List<SysOrgAgentForm> getOrgProxyRelationship(String orgId) {
        return sysOrgAgentMapper.getOrgProxyRelationship(orgId);
    }

    @Override
    public List<SysOrg> getOrgListHasAgent(String appId) {
        return sysOrgAgentMapper.getOrgListHasAgent(appId);
    }

    @Override
    public List<SysOrg> selectOrgListByAgentOrgId(String subsystemId, String agentOrgId, String agentCode) {
        return sysOrgAgentMapper.selectOrgListByAgentOrgId(subsystemId, agentOrgId, agentCode);
    }

    @Override
    public List<String> selectOrgAgentByOrgId(List<String> subsystemIds, List<String> orgIds, String agentCode) {
        if (!CollectionUtils.isEmpty(subsystemIds)) {
            QueryWrapper<SysOrgAgent> queryWrapper = new QueryWrapper<SysOrgAgent>();
            if (!CollectionUtils.isEmpty(orgIds)) {
                queryWrapper.in("ORG_ID", orgIds);
            }

            // 如果含有根节点 那么就显示所有的子系统
            if (!subsystemIds.contains(SysManageEnum.SUBSYSTEM_ROOT.getCode())) {
                queryWrapper.in("SUBSYSTEM_ID", subsystemIds);
            }
            if (!StringUtils.isBlank(agentCode)) {
                queryWrapper.eq("AGENT_CODE", agentCode);
            }
            List<SysOrgAgent> sysOrganizationAgents = sysOrgAgentMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(sysOrganizationAgents)) {
                return sysOrganizationAgents.stream().map(SysOrgAgent::getThirdOrgId).collect(Collectors.toList());
            }
        }

        return null;
    }


    @Override
    public List<String> getAllAgentCodeBySubsystemIdAndProxyOrgId(String subsystemId, String proxyOrgId) {
        if (StringUtils.isBlank(subsystemId) || StringUtils.isBlank(proxyOrgId)) {
            return null;
        }
        SysSubsystem sysSubsystem = sysSubsystemService.getSubsystemByIdOrAppId(subsystemId);
        if(sysSubsystem == null){
            throw new SofnException("子系统不存在");
        }
        List<SysOrgAgent> sysOrganizationAgents = sysOrgAgentMapper.selectList(new QueryWrapper<SysOrgAgent>()
                .eq("THIRD_ORG_ID", proxyOrgId)
                .and((item)->
                        item.eq("SUBSYSTEM_ID", sysSubsystem.getId())
                                .or()
                                .eq("SUBSYSTEM_ID", sysSubsystem.getAppId())
                )
        );

        if (!CollectionUtils.isEmpty(sysOrganizationAgents)) {
            List<String> collect = sysOrganizationAgents.stream().map(SysOrgAgent::getAgentCode).collect(Collectors.toList());
            Set<String> distinctStr = Sets.newHashSet(collect);
            return Lists.newArrayList(distinctStr);
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delProxyRelationshipByProxyIdAndSubsystemId(String proxyId, String subsystemId, String orgId) {
        if (StringUtils.isBlank(subsystemId) || StringUtils.isBlank(proxyId) || StringUtils.isBlank(orgId)) {
            log.warn("传入的参数有为空的值：proxyId={}，subsystemId={}，orgId={}", proxyId, subsystemId, orgId);
            return;
        }
        // 可以根据AppId或者系统ID删除
        if (StringUtils.isEmpty(subsystemId)) {
            throw new SofnException("系统ID不能为空");
        }
        SysSubsystem sysSubsystem = sysSubsystemService.getSubsystemByIdOrAppId(subsystemId);
        if(sysSubsystem == null){
            throw new SofnException("子系统不存在");
        }
        int delete = sysOrgAgentMapper.delete(new UpdateWrapper<SysOrgAgent>()
                .eq("THIRD_ORG_ID", proxyId)
                .eq("ORG_ID", orgId)
                .and((item)->
                     item.eq("SUBSYSTEM_ID", sysSubsystem.getId())
                            .or()
                         .eq("SUBSYSTEM_ID", sysSubsystem.getAppId())
                )

        );
        if (delete <= 0) {
            log.warn("传入的参数无效，没有删除任何值：proxyId={}，subsystemId={}，orgId={}", proxyId, subsystemId, orgId);
        }
    }

    @Override
    public List<String> getSubsystemByProxyOrgId(String proxyOrgId) {
        if(StringUtils.isBlank(proxyOrgId)){
            return Lists.newArrayList();
        }
        List<SysOrgAgent> sysOrgAgentList = sysOrgAgentMapper.selectList(new QueryWrapper<SysOrgAgent>().eq("THIRD_ORG_ID", proxyOrgId));
        if(!CollectionUtils.isEmpty(sysOrgAgentList)){
            Set<String> appIds = sysOrgAgentList.stream().map(SysOrgAgent::getSubsystemId).collect(Collectors.toSet());
            if(!CollectionUtils.isEmpty(appIds)){
                List<SysSubsystem> list = sysSubsystemService.list(new QueryWrapper<SysSubsystem>().in("APP_ID", appIds).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
                if(!CollectionUtils.isEmpty(list)){
                    Set<String> collect = list.stream().map(SysSubsystem::getSubsystemName).collect(Collectors.toSet());
                    return Lists.newArrayList(collect);

                }
            }
        }
        return Lists.newArrayList();
    }

}
