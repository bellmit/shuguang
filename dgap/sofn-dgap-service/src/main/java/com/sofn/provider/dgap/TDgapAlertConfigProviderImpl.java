package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapAlertConfigExpandMapper;
import com.sofn.model.generator.TDgapAlertConfig;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/10/13. */
@DubboService(interfaceClass = TDgapAlertConfigProvider.class)
public class TDgapAlertConfigProviderImpl extends BaseProviderImpl<TDgapAlertConfig>
    implements TDgapAlertConfigProvider {
  @Autowired private TDgapAlertConfigExpandMapper alertConfigExpandMapper;

  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = alertConfigExpandMapper.getConfigList(params);
    long count = alertConfigExpandMapper.getCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public List<TDgapAlertConfig> queryAllAlertConfig() {
    return alertConfigExpandMapper.getAllConfigs();
  }

  @Override
  public List<Map> getAlertById(String id) {
    return alertConfigExpandMapper.getAlertById(id);
  }

  @Override
  public void batchDelete(String[] ids) {
    alertConfigExpandMapper.batchDelete(ids);
  }

  @Override
  public int countByResourceId(String id) {
    return alertConfigExpandMapper.countByResourceId(id);
  }

  @Override
  public int countByResources(String[] ids) {
    return alertConfigExpandMapper.countByResources(ids);
  }
}
