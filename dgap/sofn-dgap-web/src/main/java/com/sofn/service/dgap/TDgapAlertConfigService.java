package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.provider.dgap.TDgapAlertConfigProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/13. */
@Service
public class TDgapAlertConfigService
    extends BaseService<TDgapAlertConfigProvider, TDgapAlertConfig> {
  @DubboReference
  public void setProvider(TDgapAlertConfigProvider provider) {
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
      String alertType, String targetId, int pageNum, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    params.put("alertType", alertType);
    params.put("targetId", targetId);
    return provider.list(params);
  }

  public List<Map> getAlertById(String id) {
    return provider.getAlertById(id);
  }

  public void batchDelete(String[] ids) {
    provider.batchDelete(ids);
  }

  public List<TDgapAlertConfig> queryAllAlertConfig() {
    return provider.queryAllAlertConfig();
  }

  public int countByResourceId(String id) {
    return provider.countByResourceId(id);
  }

  public int countByResources(String[] ids) {
    return provider.countByResources(ids);
  }
}
