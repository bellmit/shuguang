package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapRoleResource;
import java.util.List;
import java.util.Map;

/** Created by Administrator on 2016/10/24. */
@MyBatisDao
public interface TDgapRoleResourceExpandMapper extends BaseExpandMapper {
  List<Map<String, Object>> getPagesList(Map<String, Object> params);

  List<Map<String, Object>> getListAll();

  long getPageCount(Map<String, Object> params);

  List<TDgapRoleResource> checkRepetition(String roleId);
}
