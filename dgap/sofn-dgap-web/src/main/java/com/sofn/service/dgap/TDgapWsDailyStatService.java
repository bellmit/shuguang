package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapWsDailyStat;
import com.sofn.provider.dgap.TDgapDataToolProvider;
import com.sofn.provider.dgap.TDgapWsDailyStatProvider;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/13. */
@Service
public class TDgapWsDailyStatService
    extends BaseService<TDgapWsDailyStatProvider, TDgapWsDailyStat> {
  @DubboReference
  public void setProvider(TDgapWsDailyStatProvider provider) {
    this.provider = provider;
  }

  protected TDgapDataToolProvider dataProvider;
  @DubboReference
  public void setProvider(TDgapDataToolProvider dataProvider) {
    this.dataProvider = dataProvider;
  }

  /**
   * 分页查询
   *
   * @param
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Map<String, Object>> list(
      TDgapWsDailyStat dailyStat,  int pageNum, int pageSize, Date startTime, Date endTime) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    params.put("resourceDir", dailyStat.getResourceDir());
    if (dailyStat.getWebServiceName() != null)
      params.put("webServiceName", dailyStat.getWebServiceName().trim());
    else params.put("webServiceName", dailyStat.getWebServiceName());
    if (dailyStat.getBussnessName() != null)
      params.put("bussnessName", dailyStat.getBussnessName().trim());
    else params.put("bussnessName", dailyStat.getBussnessName());
    if (dailyStat.getCallerUser() != null)
      params.put("callerUser", dailyStat.getCallerUser().trim());
    else params.put("callerUser", dailyStat.getCallerUser());
    params.put("startTime", startTime);
    endTime = DateUtil.addDate(endTime, Calendar.DAY_OF_MONTH, 1);
    params.put("endTime", endTime);
    return provider.list(params);
  }

  public int stateDailyLog() {
    return provider.stateDailyLog(new Date());
  }

  public int stateDailyLog(Date date) {
    return provider.stateDailyLog(date);
  }

  /**
   * 该方法是将正式数据库中的TTS_SCLTXXCJ_REGISTER_V2复制到 仓库数据库
   * @param sourceDataSource
   * @param destDataSource
   * @return
   */
  public boolean copyTTSEntities(String sourceDataSource, String destDataSource){
    return dataProvider.copyTTSEntities(sourceDataSource,destDataSource);
  }
}
