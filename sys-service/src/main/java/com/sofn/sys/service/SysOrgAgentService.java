package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysOrgAgent;
import com.sofn.sys.vo.OrgAndProOrgInfoVo;
import com.sofn.sys.vo.SysOrgAgentForm;

import java.util.List;
import java.util.Map;

public interface SysOrgAgentService extends IService<SysOrgAgent> {


    /**
     * 分配组织机构代理
     *
     * @param sysOrgAgentForm   参数对象
     */
    void allotOrgAgent(SysOrgAgentForm sysOrgAgentForm);

    /**
     * 根据行政机构ID查询代理机构
     *
     * @param orgId 行政机构ID
     * @return 代理机构列表
     */
    List<SysOrg> selectOrgAgentByOrgId(String subsystemId, String orgId, String agentCode);

    /**
     * 根据代理机构ID查询行政机构列表
     *
     * @param agentOrgId 代理机构ID
     * @return 行政机构列表
     */
    List<SysOrg> selectOrgListByAgentOrgId(String subsystemId, String agentOrgId, String agentCode);


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
    List<SysOrgAgentForm> getOrgProxyRelationship(String orgId);

    /**
     * 获取有代理机构的行政机构
     * @param appId   系统APPID
     * @return   List<SysOrgAgentForm>
     */
    List<SysOrg> getOrgListHasAgent(String appId);

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

    /**
     * 根据代理机构ID获取当前机构已经维护了哪些系统的关系
     * @param proxyOrgId   代理机构ID
     * @return   系统名称
     */
     List<String> getSubsystemByProxyOrgId(String proxyOrgId);

}
