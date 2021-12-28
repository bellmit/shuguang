package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * author tianjie
 *
 * @date 2016/10/17
 */
@MyBatisDao
public interface TDgapResourceApplicationExpandMapper extends BaseExpandMapper {
  /**
   * 根据资源id和接入用户的id查找该资源的申请信息。
   *
   * @param resourceId
   * @param userId
   * @return
   */
  List<TDgapResourceApplication> queryByResourceAndUser(
      @Param("resourceId") String resourceId, @Param("userId") String userId);

  List<Map<String, Object>> getList(Map<String, Object> params);

  long getCount(Map<String, Object> params);

  /**
   * 查询资源申请列表
   *
   * @param params
   * @return
   */
  List<Map<String, Object>> getResourcePage(Map<String, Object> params);

  long getResourceCount(Map<String, Object> params);

  Map<String, Object> getResourceById(String id);

  void batchDelete(@Param("ids") String[] ids);

  List<String> queryCallerToken();

  Map<String, Object> getTdgapResourceApplication(@Param("authCode") String authCode);

  List<TDgapResource> getResource();

  int countByResourceId(@Param("resourceId") String resourceId);

  int countByResources(@Param("ids") String[] ids);

  TDgapResourceApplication getbyParam(String userId);

  List<TDgapResourceApplication> getByResourceType(String resourceType);

  List<TDgapResourceApplication> getbyParams(String localPlatformAppliId);

  /**
   * 根据资源id和token获得地方平台对接申请
   * @param token
   * @return
   */
    TDgapResourceApplication getAppliByToken( @Param("resourceId") String resourceId,@Param("token") String token);

  /**
   * 所有资源审批详情列表
   * @param map
   * @return
   */
    List<Map<String, Object>> getListDeatails(Map<String, Object> map);

  /**
   * 所有资源审批详情列表 次数
   * @param map
   * @return
   */
  long getCountListDeatails(Map<String, Object> map);

  /**
   * 根据id获取资源申请详情
   * @param id
   * @return
   */
  Map<String, Object> getResourceApplicationDetails(String id);
}
