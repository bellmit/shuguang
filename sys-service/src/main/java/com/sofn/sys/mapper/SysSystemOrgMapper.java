package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysSystemOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysSystemOrgMapper extends BaseMapper<SysSystemOrg> {

  void saveBatch(@Param("list") List<SysSystemOrg> list);

  /**
   * 根据机构ID和AppId批量删除数据
   * @param orgAndAppIds  orgId String
   *                appIds  List
   */
  void deleteByOrgIdAndAppIds(@Param("orgAndAppIds") List<Map<String,Object>> orgAndAppIds);


  /**
   * 根据机构查询信息
   * @param orgId  机构ID
   * @return  List<SysSystemOrg>
   */
  List<SysSystemOrg> getInfoByOrgId(@Param("orgId") String orgId);

  /**
   * 根据机构批量删除关系
   * @param orgIds  机构ID
   */
  void deleteByOrgIds(@Param("orgIds") List<String> orgIds);

}
