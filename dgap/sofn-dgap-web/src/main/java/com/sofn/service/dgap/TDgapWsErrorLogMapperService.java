package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapWsErrorLog;
import com.sofn.provider.dgap.TDgapWsErrorLogProvider;
import java.util.*;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/13. */
@Service
public class TDgapWsErrorLogMapperService
    extends BaseService<TDgapWsErrorLogProvider, TDgapWsErrorLog> {
  @DubboReference
  public void setProvider(TDgapWsErrorLogProvider provider) {
    this.provider = provider;
  }

  /**
   * 分页查询
   *
   * @param startTime
   * @param endTime @return
   */
  public PageInfo<List<Map<String, Object>>> list(
      String resourceDirectoryId,
      String webServiceName,
      Date startTime,
      Date endTime,
      String methodName,
      String callerUser,
      String errorType,
      int pageNum,
      int pageSize) {
    Map<String, Object> params = new HashMap<>();
    // page
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);

    if (webServiceName != null) webServiceName = webServiceName.trim();
    if (methodName != null) methodName = methodName.trim();
    if (callerUser != null) callerUser = callerUser.trim();
    // query
    params.put("resourceDirectoryId", resourceDirectoryId);
    params.put("webServiceName", webServiceName);
    params.put("startTime", startTime);
    endTime = DateUtil.addDate(endTime, Calendar.DAY_OF_MONTH, 1);
    params.put("endTime", endTime);
    params.put("methodName", methodName);
    params.put("callerUser", callerUser);
    params.put("errorType", errorType);
    PageInfo<List<Map<String, Object>>> pageInfo = provider.list(params);
    return pageInfo;
  }
}
