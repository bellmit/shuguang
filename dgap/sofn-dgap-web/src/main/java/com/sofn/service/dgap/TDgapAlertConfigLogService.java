package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapAlertLog;
import com.sofn.provider.dgap.TDgapAlertConfigLogProvider;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/13. */
@Service
public class TDgapAlertConfigLogService
    extends BaseService<TDgapAlertConfigLogProvider, TDgapAlertLog> {
  @DubboReference
  public void setProvider(TDgapAlertConfigLogProvider provider) {
    this.provider = provider;
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
      String alertConfigId, String targetId, Date alertDate, Date solveDate,
      String solveStatus,String solveStatus2, int pageNum, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    params.put("alertConfigId", alertConfigId);
    params.put("targetId", targetId);
    params.put("alertDate", alertDate);
    params.put("solveDate", solveDate);
    params.put("solveStatus", solveStatus);
    params.put("solveStatus2", solveStatus2);

    return provider.list(params);
  }

  /**
   * 检查是否该webservice服务已经告警
   *
   * @param resourceId
   */
  public boolean checkHasAlert(String resourceId) {
    return provider.checkResourceAlert(resourceId);
  }


  public TDgapAlertLog add(TDgapAlertLog record){
    if(StringUtils.isBlank(record.getDelFlag())){
      record.setDelFlag("N");
    }
    return provider.update(record);
  }

  public void checkStatus(String id) {
    TDgapAlertLog tDgapAlertLog = provider.queryById(id);
    if(tDgapAlertLog.getSolveStatus()==null||"".equals(tDgapAlertLog.getSolveStatus())){
      tDgapAlertLog.setSolveStatus("1");
      tDgapAlertLog.setSolveDate(new Date());
      tDgapAlertLog.setUpdateTime(new Date());
      provider.update(tDgapAlertLog);
    }
  }
}
