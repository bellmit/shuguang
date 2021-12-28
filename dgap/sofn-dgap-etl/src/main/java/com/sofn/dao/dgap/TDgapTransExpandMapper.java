package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.model.generator.TDgapTrans;
import java.util.List;
import java.util.Map;

/** Created by wangt on 2017/5/23. */
@MyBatisDao
public interface TDgapTransExpandMapper {
  TDgapTrans getMetaID(Map<String,Object> params);

  List<TDgapTrans> getByTransNameAndResourceId(Map<String, Object> params);

  List<TDgapTrans> getByScheduleStatus();

  TDgapTrans getByTransName(String name);

  TDgapTrans getByResourceId(String resourceId);

  List<TDgapTrans> getAllByResourceId(String resourceId);

  List<String> getAllResourceId();

  List<TDgapTrans> getAllTrans();
}
