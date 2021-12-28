package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.model.generator.TDgapTbResourceConfig;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** Created by Administrator on 2016/11/23. */
@MyBatisDao
public interface TDgapTbResourceConFigExpandMapper {
  List<Map<String, Object>> getPagesList(Map<String, Object> params);

  long getPageCount(Map<String, Object> params);

  void batchInsert(List<TDgapTbResourceConfig> list);

  List<TDgapTbResourceConfig> getByResourceId(@Param("resourceId") String resourceId);
}
