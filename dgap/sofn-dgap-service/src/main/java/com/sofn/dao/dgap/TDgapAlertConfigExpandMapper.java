package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.model.generator.TDgapCommon;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** Created by Administrator on 2016/10/14. */
@MyBatisDao
public interface TDgapAlertConfigExpandMapper extends BaseExpandMapper {
  List<Map<String, Object>> getConfigList(Map<String, Object> map);

  Long getCount(Map<String, Object> map);

  List<TDgapAlertConfig> getAllConfigs();

  List<Map> getAlertById(String id);

  void batchDelete(@Param("ids") String[] ids);

  int countByResourceId(String id);

  int countByResources(@Param("ids") String[] ids);

  List<TDgapCommon> checkRepeat(@Param("targetId")String targetId,@Param("id")String id);
}
