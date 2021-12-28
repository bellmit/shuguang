package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapLocalPlatFormAppliExpandMapper;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** 地方对接平台 */
@DubboService(interfaceClass = TDgapLocalPlatFormAppliProvider.class)
public class TDgapLocalPlatFormAppliProviderImpl extends BaseProviderImpl<TDgapLocalPlatFormAppli>
    implements TDgapLocalPlatFormAppliProvider {

  @Autowired private TDgapLocalPlatFormAppliExpandMapper platFormAppliExpandMapper;

  /*  地方对接平台分页展示*/
  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = platFormAppliExpandMapper.getPagesList(params);
    long count = platFormAppliExpandMapper.getPageCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public int addLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli) {
    return platFormAppliExpandMapper.insert(localPlatFormAppli);
  }

  @Override
  public int updateLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli) {
    return platFormAppliExpandMapper.updateByPrimaryKey(localPlatFormAppli);
  }
}
