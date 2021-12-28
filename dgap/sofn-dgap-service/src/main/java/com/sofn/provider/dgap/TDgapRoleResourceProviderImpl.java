package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapRoleResourceExpandMapper;
import com.sofn.model.generator.TDgapRoleResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/10/24. */
@DubboService(interfaceClass = TDgapRoleResourceProvider.class)
public class TDgapRoleResourceProviderImpl extends BaseProviderImpl<TDgapRoleResource>
    implements TDgapRoleResourceProvider {
  @Autowired private TDgapRoleResourceExpandMapper tDgapRoleResourceExpandMapper;

  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> parames) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = tDgapRoleResourceExpandMapper.getPagesList(parames);
    long count = tDgapRoleResourceExpandMapper.getPageCount(parames);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public List<Map<String, Object>> listAll() {
    List<Map<String, Object>> pageInfo = new ArrayList<>();
    pageInfo = tDgapRoleResourceExpandMapper.getListAll();

    return pageInfo;
  }

  @Override
  public List<TDgapRoleResource> checkRepetition(String roleId) {
    List<TDgapRoleResource> list = tDgapRoleResourceExpandMapper.checkRepetition(roleId);
    return list;
  }
}
