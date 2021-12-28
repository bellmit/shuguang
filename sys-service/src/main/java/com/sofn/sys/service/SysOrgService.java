package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author qzj
 *
 */
public interface SysOrgService extends IService<SysOrg> {

  /**
   * 根据id获取机构
   * @param id 机构id
   * @return SysOrg
   */
  SysOrg getOrgById(String id);

  /**
   * 保存机构信息
   * @param sysOrg  机构信息
   * @param appIds  所属系统
   * @return  String
   */
  String saveOrg(SysOrg sysOrg, List<String> appIds);


  /**
   * 根据AppId获取机构信息
   * @param appId  系统代码
   * @return  List<SysOrg>
   */
  List<SysOrg> listOfAllByAppId(String appId);

  /**
   * 根据父id获取用户有权限的直接子机构
   * @param parentId 父id
   * @param appId  系统代码
   * @return List<SysOrgVo>
   */
  List<SysOrgVo> listOfUserByParentId(String parentId, String appId);

  /**
   * 根据父id获取用户有权限的直接子机构（分页）
   * @param parentId 父id
   * @param pageNo 开始索引
   * @param appId   系统代码
   * @param pageSize  每页显示多少条
   * @return  PageInfo<SysOrgVo>
   */
  PageInfo<SysOrgVo> listOfUserByParentIdForPaging(String parentId, String appId, Integer pageNo, Integer pageSize);

  /**
   * 根据父id获取用户有权限的直接子机构
   * @param params 机构类型 Y 行政机构 N非行政机构
   *                 String appId, String orgName, String regionCode,String thirdOrg,String orgLevel,String isAuth
   * @return  List<SysOrgVo>
   */
  List<SysOrgVo> listOfUserTree(Map<String,String> params);

  /**
   * 根据区划获取直属行政机构列表
   * @param regionCode 区划代码
   * @return List<SysOrgVo>
   */
  List<SysOrgVo> getDirectOrgByRegionCode(String regionCode);

  /**
   * 根据机构ID获取系统Appid集合
   * @param orgId 机构集合
   * @return   List<String>
   */
  List<String> getAppIdsByOrgId(String orgId);

  /**
   * 根据机构ID获取区域信息
   * @param orgId  机构ID
   * @return  区域信息
   */
  SysRegionInfoVo getSysRegionInfoByOrgId(String orgId);

  /**
   * 根据系统appid获取系统下的全部机构
   * @param appId   系统代码
   * @param orgName  机构名称
   * @param regionId  区划ID
   * @return  List<SysOrgVo>
   */
  List<SysOrgVo> getAllByAppId(String appId, String orgName,String regionId);

  /**
   * 根据区划分页获取非行政机构列表
   * @param addressRegionCode   区划CODE
   * @param pageNo 哪个索引开始
   * @param pageSize  每页显示多少条
   * @return  PageUtils<SysOrg>
   */
  PageUtils<SysOrg> getOrgByAddressRegionCode(String addressRegionCode, Integer pageNo, Integer pageSize);

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
   * 根据ID获取机构信息
   * @param id  待查询ID
   * @return  SysOrganizationForm
   */
  SysOrganizationForm getInfoById(String id);

  /**
   * 获取所有的第三方机构
   * @param appId  系统代码
   * @return   List<SysOrgVo>
   */
  List<SysOrgVo> getAllThridOrgList(String appId);

  /**
   * 根据行政区划找到代理机构
   * @param regionId   行政区划
   * @param appId    子系统AppId
   * @param orgId     机构ID
   * @return  List<SysOrganizationTreeVo>
   */
  List<SysOrganizationTreeVo> getProxyOrgByRegionIdAndAppId(String regionId, String appId, String orgId);

  /**
   * 根据机构职能获取机构信息
   * @param orgFunction   机构职能
   * @param appId    系统APPID
   * @return  getOrgInfoByOrgFunction
   */
  List<SysOrg> getOrgInfoByOrgFunction(String orgFunction, String appId);

  /**
   * 根据区划和机构级别获取下级级别机构
   * @param region  区划CODE
   * @param level    机构几倍
   * @param appId    系统代码
   * @return   List<SysOrg>
   */
  List<SysOrg> getOrgByRegionAndLevel(String appId, String region, String level);

  /**
   * 检察组织机构名称是否重复
   * @param name   机构名称
   * @param id   机构ID
   * @return   true 重复
   */
  boolean checkOrgNameIsExist(String name, String id);

  /**
   * 获取当前用户能够访问的机构列表，包含当前用户的机构
   * @param appIds   系统代码
   * @param carryChildrenOrg    是否携带子机构
   * @param carryProxyOrg       是否携带代理机构
   * @return  List<SysOrg>
   */
  List<SysOrgVo> getAllOrgListAndLoginUserId(List<String> appIds,boolean carryChildrenOrg,boolean carryProxyOrg);


  /**
   * 获取当前用户能够访问的机构列表，包含当前用户的机构
   * @param appIds   系统代码
   * @param carryChildrenOrg    是否携带子机构
   * @param carryProxyOrg       是否携带代理机构
   * @return  List<SysOrg>
   */
  List<String> getUserHaveOrgIds(List<String> appIds,boolean carryChildrenOrg,boolean carryProxyOrg);

  /**
   * 根据APPID和机构id集合获取机构列表
   * @param appId 系统代码
   * @param ids 机构ID集合
   * @return List<SysOrg>
   */
  List<SysOrgVo> listByAppIdAndIds(String appId, List<String> ids);

  /**
   * 获取子节点
   * @param orgIds   机构ID
   * @param appId    系统代码
   * @return    List<String>
   */
  Set<String> getChildrenOrgByIds(List<String> orgIds, String appId);

  /**
   * 查询系统下机构树
   * @param appId 系统代码
   * @param parentId 父id
   * @param level 机构级别
   * @return List<SysOrgVo>
   */
  List<SysOrgVo> listTreeByAppId(String appId, String parentId, String level);

  /**
   * 查询系统下机构列表
   * @param appId 系统代码
   * @param parentId 父id
   * @return List<SysOrgVo>
   */
  List<SysOrgVo> listByAppId(String appId, String parentId);

  /**
   * 将SysOrg列表翻译成SysOrgVo列表
   *
   * @param list    SysOrg
   * @return   List<SysOrgVo>
   */
   List<SysOrgVo> getVoList(List<SysOrg> list);


  /**
   * 按条件查询机构信息
   * @param params  条件
   * @return  List<SysOrg>
   */
   List<SysOrg> getInfoByCondition(Map<String,Object> params);



  /**
   * 根据系统appId获取系统下的全部机构
   * @param appId   系统appId
   * @param functionCode  职能代码
   * @return List<SysOrgVo>
   */
  List<SysOrgVo> getOrgInfoByAppIdAndFunctionCode(String appId, String functionCode);

  /**
   * 根据AppId和ParentId和其他条件查询机构信息
   * @param appId    系统标识
   * @param parentId    机构的ParentId
   * @param params  orgName: 机构名称
   *                isThird: 是否第三方机构
   *                regionCode: 行政区划
   * @return   List<SysOrg>
   */
  List<SysOrg> getOrgInfoByAppIdAndParentIdAndOtherParam(@Param("appId") String appId, @Param("parentId") String parentId,
                                                         @Param("otherParam") Map<String,Object> params);


  /**
   * 根据父ID和AppId查询机构信息
   * @param parentIdStr   sysorgroot/ID/ID。。。
   * @param appIds   系统代码
   * @return  List<SysOrgVo>
   */
  List<SysOrgVo> getOrgInfoByParentIdAndAppId(String parentIdStr, List<String> appIds);


  /**
   * 根据系统代码和区划代码查询当前系统下当前区划和下级区划的所有系统，如：传入省就查询当前省的所有机构，包含省市县
   * @param appId   系统代码
   * @param regionCodes   区划代码
   * @param orgLevel   机构级别
   * @return   List<String>
   */
  List<SysOrg> getOrgIdByRegionCodeAndAppId(String appId, List<String> regionCodes, String orgLevel);


  /**
   * 替换子节点的parentIds信息
   * @param ids  哪些节点需要替换
   * @param newParentIdsPrefix   新的前缀
   * @param oldParentIdsPrefix   待替换前缀
   */
  void replaceParentIds(List<String> ids,String newParentIdsPrefix,String oldParentIdsPrefix);

  /**
   * 根据AppId和parentId查询信息
   * @param appId   appId
   * @param parentId  ParentId
   * @param level  Level
   * @return List<SysOrg>
   */
  List<SysOrg> selectByAppId(@Param("appId") String appId, @Param("parentId") String parentId, @Param("level") String level);

  /**
   * 获取机构的经纬度
   * @param id 机构id
   * @return
   */
  Map<String,String> getOrgLocation(String id);

    /**
     * 根据机构id删除机构
     * @param ids 机构id
     */
  void deleteOrgByIds(String... ids);

  /**
   * 获取系统组织机构列表
   * @param appId 系统id
   * @param dictCode 字典值
   * @param regionCode 行政区域集
   * @author du.yang
   * @date 2021-03-04 14:00:00
   */
  List<SysOrgVo> getAllSysOgrList(String appId, String dictCode, List<String> regionCode);

  /**
   * 获取区域下使用系统的用户
   * @param appId 系统id
   * @param regionCode 行政区域集
   * @author du.yang
   * @date 2021-03-04 17:00:00
   */
  List<SysUserForm> getUserUsingApp(String appId, List<String> regionCode);

}
