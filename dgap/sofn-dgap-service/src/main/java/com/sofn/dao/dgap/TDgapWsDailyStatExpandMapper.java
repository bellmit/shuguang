package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapWsDailyStat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** Created by Administrator on 2016/10/14. */
@MyBatisDao
public interface TDgapWsDailyStatExpandMapper extends BaseExpandMapper {
  List<Map<String, Object>> getList(Map<String, Object> map);

  Long getCount(Map<String, Object> map);

  // 成功日志统计
  void statSuccDailyLog();
  // 失败日志统计
  void statFaildDailyLog();

  List<TDgapWsDailyStat> getDailyLogs(Date time);

  BigDecimal calcSuccessTimes(Map<String, Object> map);

  BigDecimal calcFailTimes(Map<String, Object> map);

  BigDecimal calcAvgTimeCost(Map<String, Object> map);

  int insert(TDgapWsDailyStat stat);

  TDgapWsDailyStat getBy(TDgapWsDailyStat stat);

  List<TDgapWsDailyStat> newGetBy(TDgapWsDailyStat stat);

  int update(TDgapWsDailyStat stat);
}
