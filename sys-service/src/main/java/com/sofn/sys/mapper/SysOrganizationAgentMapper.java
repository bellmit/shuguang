package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.model.SysOrganizationAgent;
import com.sofn.sys.vo.OrgAndProOrgInfoVo;
import com.sofn.sys.vo.SysOrgAgentForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
  * @Description: 组织机构代理mapper
  * @Author: quzhijie
  * @Date: 2020/1/3 15:00
  **/
public interface SysOrganizationAgentMapper extends BaseMapper<SysOrganizationAgent> {

  /**
   * 根据行政机构ID查询代理机构
   * @param subsystemId 系统ID
   * @param orgId 行政机构ID
   * @param agentCode 代理标识
   * @return 代理机构列表
   */
   List<SysOrganization> selectOrgAgentByOrgId(@Param("subsystemId")String subsystemId, @Param("orgId")String orgId, @Param("agentCode")String agentCode);

  /**
   * 根据代理机构查询行政机构列表
   * @param subsystemId 系统ID
   * @param agentOrgId 代理机构ID
   * @param agentCode 代理标识
   * @return 行政机构列表
   */
   List<SysOrganization> selectOrgListByAgentOrgId(@Param("subsystemId")String subsystemId, @Param("agentOrgId")String agentOrgId, @Param("agentCode")String agentCode);

    /**
     * 根据机构级别查询机构信息和代理机构信息
     * @param params    参数
     *          organizationLevel   机构级别
     *          appId      子系统
     *          proxyId    代理机构ID
     *          orgId      机构ID
     *          regionId   区划ID
     *          agentCode  代理标识
     * @return  List<OrgAndProOrgInfoVo>
     */
   List<OrgAndProOrgInfoVo> getOrgAndProOrgInfo(Map<String,String> params);

    /**
     * 批量保存代理数据
     * @param sysOrganizationAgents  代理数据
     */
   void batchInserInfo(@Param("sysOrganizationAgents") List<SysOrganizationAgent> sysOrganizationAgents);

    /**
     * 获取行政机构的关系
     * @param orgId   行政机构ID
     * @return   List<SysOrgAgentForm>
     */
   List<SysOrgAgentForm> getOrgProxyRelationship(@Param("orgId") String orgId);

  /**
   * 获取有代理机构的行政机构
   * @param appId   appID
   * @return   List<SysOrganization>
   */
  List<SysOrganization> getOrgListHasAgent(@Param("appId") String appId);
}
