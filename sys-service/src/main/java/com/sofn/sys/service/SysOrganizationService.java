package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.vo.SysOrgTreeVo;
import com.sofn.sys.vo.SysOrganizationTreeVo;
import com.sofn.sys.vo.SysRegionInfoVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sofn
 */
public interface SysOrganizationService extends IService<SysOrganization> {
    void initOrgData();

    /**
     * 更新机构信息
     * @param organization  机构信息
     * @param infos    凭证信息
     */
    void updateOrganization(SysOrganization organization, Map<String, List<String>> infos);

    void deleteOrganization(String id);
    PageUtils<SysOrganization> getSysOrganizationByContion(Map<String,Object> paramas, Integer pageNo, Integer pageSize);

    /**
     * 添加机构信息
     * @param organization  机构信息
     * @param infos   资质文件信息
     */
    String createOrganization(SysOrganization organization, Map<String, List<String>> infos);

    SysOrganization getSysOrganizationById(String id);

    /**
     * 根据机构ID获取下边所有机构列表(无层级结构)
     * @param id 机构id
     * @return 机构列表
     */
    Set<SysOrganization> getOrgByParentId(String id);

    SysOrgTreeVo getAllSysOgrInfo(String orgname, String regionid);

    /**
     * 按条件查询信息
     * @param ids   ID 使用,隔开
     * @param name  机构名称
     * @param pageNo  从哪条记录开始
     * @param pageSize  显示多少条
     * @return    List<Map<String,String>>   机构信息
     */
    List<Map<String,String>> getOrgInfoByIdsAndName(String ids,String name,Integer pageNo, Integer pageSize);

    /**
     * 根据机构ID获取区域信息
     * @param orgId  机构ID
     * @return  区域信息
     */
    SysRegionInfoVo getSysRegionInfoByOrgId(String orgId);

  /**
   * 根据区域获取直属行政机构列表
   * @param regionCode 区划代码
   */
    PageUtils<SysOrganization> getDirectOrgByRegionCode(String regionCode, Integer pageNo, Integer pageSize);

    /**
     * 根据区域获取直属行政机构列表
     * @param regionCode 区划代码
     */
    List<SysOrganization> getDirectOrgByRegionCode(String regionCode);

    /**
     * 根据所在地址区划获取非行政机构列表
     * @param addressRegionCode 区划代码
     */
    PageUtils<SysOrganization> getOrgByAddressRegionCode(String addressRegionCode, Integer pageNo, Integer pageSize);

    /**
     * 根据机构级别获取机构信息
     * @param orgLevel  机构级别
     * @return   List<SysOrganization>
     */
    List<SysOrganization> getOrgInfoByOrgLevel(String orgLevel);

    /**
     * 根据行政区划找到代理机构
     * @param regionId   行政区划
     * @param appId    子系统AppId
     * @param orgId     机构ID
     * @return  List<SysOrganizationTreeVo>
     */
    List<SysOrganizationTreeVo> getProxyOrgByRegionIdAndAppId(String regionId, String appId,String orgId);

    /**
     * 根据机构职能获取机构信息
     * @param orgFunction   机构职能
     * @param appId    系统APPID
     * @return  getOrgInfoByOrgFunction
     */
    List<SysOrganization> getOrgInfoByOrgFunction(String orgFunction,String appId);
}
