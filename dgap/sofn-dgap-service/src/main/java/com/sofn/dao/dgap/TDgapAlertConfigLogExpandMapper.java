package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** Created by Administrator on 2016/10/14. */
@MyBatisDao
public interface TDgapAlertConfigLogExpandMapper extends BaseExpandMapper {
  List<Map<String, Object>> getConfigLogList(Map<String, Object> map);

  Long getCount(Map<String, Object> map);

  int selectIsHasAlert(@Param("resourceId") String resourceId);
}
