package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapResource;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** Created by Administrator on 2016/10/13. */
@MyBatisDao
public interface TDgapResourceExpandMapper extends BaseExpandMapper {

  List<Map<String, Object>> getPagesList(Map<String, Object> params);

  List<TDgapResource> getResourceNameList(String resouorcename);

  long getPageCount(Map<String, Object> params);

  List<TDgapResource> selectRes();
  /* 资源管理根据ID的修改数据回显*/
  List<Map> getResourceData(String id);

  List<TDgapResource> pagetest();

  void batchDelete(@Param("ids") String[] ids);

  List<TDgapResource> getResourceByDirId(String id);

  TDgapResource getResourceDelFlagByResourceId(String resourceId);

  List<TDgapResource> getResourceByName(@Param("resourceName") String resourceName);

  List<TDgapResource> getResourceList();

  List<TDgapResource> getResourceByFilePath(String filePath);

  List<TDgapResource> getResourceByType(String typeCode);
//查询所有的文件路径
    List<String> getAllPath();

    List<String> getAllPathsById(String resourceId);
}
