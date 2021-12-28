package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.TDgapWsLogMapper;
import com.sofn.model.generator.TDgapWsLog;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author tianjie
 *
 * @date 2016/10/19
 */
@DubboService(interfaceClass = TDgapWsLogProvider.class)
public class TDgapWsLogProviderImpl extends BaseProviderImpl<TDgapWsLog>
    implements TDgapWsLogProvider {

  @Autowired private TDgapWsLogMapper tDgapWsLogMapper;

  @Override
  public int insertInvokeLog(TDgapWsLog tDgapWsLog) {
    tDgapWsLog.setDelFlag("N");
    tDgapWsLog.setId(StringUtils.getUUIDString());
    return tDgapWsLogMapper.insert(tDgapWsLog);
  }
}
