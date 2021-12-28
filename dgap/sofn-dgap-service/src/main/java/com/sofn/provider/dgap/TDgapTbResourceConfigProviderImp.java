package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapTbResourceConFigExpandMapper;
import com.sofn.model.generator.TDgapTbResourceConfig;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/11/23. */
@DubboService(interfaceClass = TDgapTbResourceConfigProvider.class)
public class TDgapTbResourceConfigProviderImp extends BaseProviderImpl<TDgapTbResourceConfig>
    implements TDgapTbResourceConfigProvider {
  @Autowired private TDgapTbResourceConFigExpandMapper tDgapTbResourceConFigExpandMapper;

  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> parames) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    long count = tDgapTbResourceConFigExpandMapper.getPageCount(parames);
    List<Map<String, Object>> lists = tDgapTbResourceConFigExpandMapper.getPagesList(parames);
    pageInfo.setList(lists);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  public void batchInsert(List<TDgapTbResourceConfig> list) {}

  @Override
  public List<TDgapTbResourceConfig> getByResourceId(String resourceId) {
    return tDgapTbResourceConFigExpandMapper.getByResourceId(resourceId);
  }
}
