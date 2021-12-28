package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapLocalAuditExpandMapper;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** 地方对接平台 */
@DubboService(interfaceClass = TDgapLocalAuditProvider.class)
public class TDgapLocalAuditProviderImpl extends BaseProviderImpl<TDgapLocalPlatFormAppli>
    implements TDgapLocalAuditProvider {

  @Autowired private TDgapLocalAuditExpandMapper auditExpandMapper;

  /*  地方对接平台分页展示*/
  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = auditExpandMapper.getPagesList(params);
    long count = auditExpandMapper.getPageCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public int addLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli) {
    return auditExpandMapper.insert(localPlatFormAppli);
  }

  @Override
  public int updateLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli) {
    return auditExpandMapper.updateByPrimaryKey(localPlatFormAppli);
  }
}
