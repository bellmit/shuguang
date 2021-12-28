package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapTbResourceConfig;
import com.sofn.provider.dgap.TDgapTbResourceConfigProvider;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/11/23. */
@Service
public class TDgapTbResourceConfigService
    extends BaseService<TDgapTbResourceConfigProvider, TDgapTbResourceConfig> {
  @DubboReference
  public void setProvider(TDgapTbResourceConfigProvider provider) {
    this.provider = provider;
  }

  public PageInfo<Map<String, Object>> list(String resourceId, int pageNum, int pageSize) {
    Map<String, Object> map = new HashedMap();
    map.put("resourceId", resourceId);
    map.put(Constants.PAGE_NUM, pageNum);
    map.put(Constants.PAGE_SIZE, pageSize);
    return provider.list(map);
  }
}
