package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapAlertConfigLogExpandMapper;
import com.sofn.model.generator.TDgapAlertLog;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/10/13. */
@DubboService(interfaceClass = TDgapAlertConfigLogProvider.class)
public class TDgapAlertConfigLogProviderImpl extends BaseProviderImpl<TDgapAlertLog>
    implements TDgapAlertConfigLogProvider {
  @Autowired private TDgapAlertConfigLogExpandMapper alertConfigLogExpandMapper;

  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = alertConfigLogExpandMapper.getConfigLogList(params);
    long count = alertConfigLogExpandMapper.getCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public boolean checkResourceAlert(String resourceId) {
    int count = alertConfigLogExpandMapper.selectIsHasAlert(resourceId);
    return count >= 1;
  }
}
