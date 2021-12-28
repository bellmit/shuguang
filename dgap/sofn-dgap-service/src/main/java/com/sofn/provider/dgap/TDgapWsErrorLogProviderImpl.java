package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.dgap.TDgapWsErrorLogExpandMapper;
import com.sofn.dao.generator.TDgapWsErrorLogMapper;
import com.sofn.model.generator.TDgapWsErrorLog;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/10/13. */
@DubboService(interfaceClass = TDgapWsErrorLogProvider.class)
public class TDgapWsErrorLogProviderImpl extends BaseProviderImpl<TDgapWsErrorLog>
    implements TDgapWsErrorLogProvider {
  @Autowired private TDgapWsErrorLogExpandMapper tDgapWsErrorLogExpandMapper;

  @Autowired private TDgapWsErrorLogMapper tDgapWsErrorLogMapper;

  @Override
  public PageInfo<List<Map<String, Object>>> list(Map<String, Object> params) {
    PageInfo pageInfo = new PageInfo();
    List<Map<String, Object>> list = tDgapWsErrorLogExpandMapper.getPagesList(params);
    long count = tDgapWsErrorLogExpandMapper.getPageCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public int insertWsErrorLog(TDgapWsErrorLog tDgapWsErrorLog) {
    tDgapWsErrorLog.setId(StringUtils.getUUIDString());
    tDgapWsErrorLog.setDelFlag("N");
    return tDgapWsErrorLogMapper.insert(tDgapWsErrorLog);
  }
}
