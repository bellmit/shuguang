package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.vo.SysOrgVo;
import com.sofn.sys.vo.SysOrganizationTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysOrgMapper extends BaseMapper<SysOrg> {

  List<SysOrg> selectByParentIdAndAppIds(@Param("parentId") String parentId, @Param("appIds") List<String> appIds);

  List<SysOrg> selectByAppIdsOfUser(@Param("appIds") List<String> appIds,
                                    @Param("userOrgId") String userOrgId,
                                    @Param("orgName") String orgName,
                                    @Param("regionCode") String regionCode,
                                    @Param("thirdOrg") String thirdOrg,
                                    @Param("orgLevel") String orgLevel
                                  );

  List<SysOrg> selectAllByAppId(@Param("appId") String appId, @Param("orgName") String orgName, @Param("regionLastCode") String regionLastCode);

  List<Map<String,String>> getInfoByCondition(@Param("organizationName") String  orgName, @Param("ids") List<String> ids);

  List<SysOrgVo> selectJoin(@Param("params") Map<String, Object> params);

  List<SysOrganizationTreeVo> getProxyOrgByRegionIdAndAppId(@Param("regionId") String regionId, @Param("appId") String appId, @Param("orgId") String orgId);

  List<SysOrg> selectByAppIdAndFunctionCode(@Param("appId") String appId, @Param("functionCode") String functionCode);

  List<SysOrg> selectByRegionAndLevel(@Param("appId") String appId, @Param("region") String region, @Param("level") String level);

  /**
   * 获取机构ID和当前机构代理的机构ID
   * @param orgId   机构ID
   * @param systemAppIds    系统IDS
   * @return List<String>
   */
  List<String> getOrgAndProxyOrg(@Param("orgId") String orgId,@Param("systemAppIds")List<String> systemAppIds);

  /**
   * 获取子节点
   * @param parentIdss   机构ID
   * @param appId    系统ID
   * @return   List<String>
   */
  Set<String> getChildrenOrgByIds(@Param("parentIdss") List<String> parentIdss, @Param("appId") String appId);


  /**
   * 根据APPID和机构id集合获取机构列表
   * @param appId   系统ID
   * @param ids   机构IDS
   * @return List<SysOrg>
   */
  List<SysOrg> selectListByAppIdAndIds(@Param("appId") String appId, @Param("ids") List<String> ids);

    /**
     * 根据机构ID 获取parentIds
     * @param orgIds  机构ID
     * @return  List<String>  返回这些机构ID所代表的机构的parengIds然后拼接上/自己的ID
     */
  Set<String> getParentIdsByOrgIds(@Param("orgIds") List<String> orgIds);

  List<SysOrg> selectByAppId(@Param("appId") String appId, @Param("parentId") String parentId, @Param("level") String level);

  List<SysOrg> selectDrillByAppId(@Param("appId") String appId, @Param("parentId") String parentId);

  /**
   * 批量删除
   * @param deleteUserId 删除的用户
   * @param deleteDate   删除时间
   * @param ids  需要删除的菜单ID集合
   */
  void batchDelete(@Param("ids") Set<String> ids,@Param("deleteUserId") String deleteUserId, @Param("deleteDate") Date deleteDate);

  /**
   * 根据系统appId获取系统下的全部机构
   * @param appId   系统appId
   * @param functionCode  职能代码
   * @return List<SysOrg>
   */
  List<SysOrg> getOrgInfoByAppIdAndFunctionCode(@Param("appId") String appId, @Param("functionCode") String functionCode);


  /**
   * 按照条件分页查询
   * @param appId    系统标识
   * @param parentId    机构的ParentId
   * @param params  orgName: 机构名称
   *                parentIds   必传
   *                isThird: 是否第三方机构
   *                regionCode: 行政区划
   *                parentIdStrs  : 必传
   *                id : 必传
   * @return   List<SysOrg>
   */
  List<SysOrg> getOrgInfoByAppIdAndParentIdAndOtherParam(@Param("appId") String appId, @Param("parentId") String parentId,
                                                         @Param("otherParam") Map<String,Object> params);


  /**
   * 根据ParentId和AppIds查询符合条件的
   * @param parentIdStr  sysorgroot/ID/ID...
   * @param appIds     系统代码
   * @return   List<SysOrgVo>
   */
  List<SysOrgVo> getOrgInfoByParentIdAndAppId(@Param("parentIdStr") String parentIdStr, @Param("appIds") List<String> appIds);

  /**
   * 根据系统代码和区划代码获取当前区划下的所有机构  比如传入四川省就查找四川省的所有机构   regionCode的结构为["510000","510100"
   * and SO.REGIONCODE like '["510000","510100"%'
   * @param appId   系统代码
   * @param regionCode   区划代码
   * @return   List<String>
   */
  List<SysOrg> getOrgIdByRegionCodeAndAppId(@Param("appId") String appId,@Param("regionCode")String regionCode,@Param("orgLevel") String orgLevel);


  /**
   * 替换子节点的parentIds信息
   * @param ids  哪些节点需要替换
   * @param newParentIdsPrefix   新的前缀
   * @param oldParentIdsPrefix   待替换前缀
   */
  void replaceParentIds(@Param("ids") List<String> ids,@Param("newParentIdsPrefix")String newParentIdsPrefix,@Param("oldParentIdsPrefix")String oldParentIdsPrefix);

  /**
   * 根据ParentIds和AppIds查询机构
   * @param parentIds   父节点信息 如:/sysorgroot/aaxxa/adadf
   * @param appIds    系统代码集合
   * @return   List<SysOrg>
   */
  List<SysOrg> getChildInfoByParentIdsAndAppIds(@Param("parentIds") String parentIds,@Param("appIds") List<String> appIds);

  /**
   * 获取机构能够查看的机构
   * @param orgId    机构ID
   * @param appIds    系统代码
   * @param carryChildren   是否携带子节点   Y 携带
   * @param carryProxyOrg    是否携带代理机构  Y携带
   * @return   List<String>   用户能够访问的机构ID
   */
  List<String> getOrgCanShowOrgIds(@Param("orgId") String orgId,
                                   @Param("appIds") List<String> appIds,
                                   @Param("carryChildren") String carryChildren,
                                   @Param("carryProxyOrg") String carryProxyOrg);


  List<SysOrg> getAllSysOgrList(@Param("appId") String appId,
                                @Param("dictCode") String dictCode,
                                @Param("regionCodeList") List<String> regionCodeList);

}
