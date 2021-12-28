package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.model.SysOrganizationAgent;
import com.sofn.sys.vo.OrgAndProOrgInfoVo;
import com.sofn.sys.vo.SysOrgAgentForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysOrganizationAgentService extends IService<SysOrganizationAgent> {


    /**
     * 分配组织机构代理
     *
     * @param subSystemId 系统ID
     * @param agentOrgIds 机构代理ID列表
     */
    void allotOrgAgent(String subSystemId, String orgId, String agentCode, List<String> agentOrgIds);

    /**
     * 根据行政机构ID查询代理机构
     *
     * @param orgId 行政机构ID
     * @return 代理机构列表
     */
    List<SysOrganization> selectOrgAgentByOrgId(String subsystemId, String orgId, String agentCode);

    /**
     * 根据代理机构ID查询行政机构列表
     *
     * @param agentOrgId 代理机构ID
     * @return 行政机构列表
     */
    List<SysOrganization> selectOrgListByAgentOrgId(String subsystemId, String agentOrgId, String agentCode);


    /**
     * 根据行政机构ID和子系统查询代理机构
     *
     * @param subsystemIds 子系统IDS
     * @param orgIds       机构ID
     * @param agentCode    授权码
     * @return List<String>
     */
    List<String> selectOrgAgentByOrgId(List<String> subsystemIds, List<String> orgIds, String agentCode);

    /**
     * 根据机构级别查询机构信息和代理机构信息
     *
     * @param params    参数
     *               organizationLevel   机构级别
     *               appId      子系统
     *               proxyId    代理机构ID
     *               orgId      机构ID
     *               regionId   区划ID
     *               agentCode  代理标识
     * @return List<OrgAndProOrgInfoVo>
     */
    List<OrgAndProOrgInfoVo> getOrgAndProOrgInfo(Map<String, String> params);

    /**
     * 获取行政机构的关系
     * @param orgId   行政机构ID
     * @return   List<SysOrgAgentForm>
     */
    List<SysOrgAgentForm> getOrgProxyRelationship( String orgId);

    /**
     * 获取有代理机构的行政机构
     * @param appId   系统APPID
     * @return   List<SysOrgAgentForm>
     */
    List<SysOrganization> getOrgListHasAgent( String appId);

    /**
     * 根据子系统和代理机构获取所有的授权码
     * @param subsystemId  子系统ID
     * @param proxyOrgId 代理机构ID
     * @return  所有的授权码
     */
     List<String> getAllAgentCodeBySubsystemIdAndProxyOrgId(String subsystemId, String proxyOrgId) ;

    /**
     * 根据代理机构 子系统ID   行政机构ID 删除代理关系
     * @param proxyId   代理机构ID
     * @param subsystemId  子系统ID
     * @param orgId  行政机构ID
     */
     void delProxyRelationshipByProxyIdAndSubsystemId(String proxyId, String subsystemId, String orgId);


    }
