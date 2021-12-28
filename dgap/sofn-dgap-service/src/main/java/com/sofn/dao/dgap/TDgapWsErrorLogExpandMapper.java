package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import java.util.List;
import java.util.Map;

/** Created by Administrator on 2016/10/13. */
@MyBatisDao
public interface TDgapWsErrorLogExpandMapper extends BaseExpandMapper {

  List<Map<String, Object>> getPagesList(Map<String, Object> params);

  long getPageCount(Map<String, Object> params);
}
