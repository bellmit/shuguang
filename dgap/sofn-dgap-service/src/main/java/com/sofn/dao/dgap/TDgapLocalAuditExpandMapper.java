package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import java.util.List;
import java.util.Map;

@MyBatisDao
public interface TDgapLocalAuditExpandMapper extends BaseExpandMapper {
  List<Map<String, Object>> getPagesList(Map<String, Object> params);

  long getPageCount(Map<String, Object> params);

  int insert(TDgapLocalPlatFormAppli localPlatFormAppli);

  int updateByPrimaryKey(TDgapLocalPlatFormAppli localPlatFormAppli);
}
