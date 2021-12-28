package com.sofn.service.dgap;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.model.generator.TDgapCommon;
import com.sofn.provider.dgap.TDgapCommomProvider;
import java.util.List;
import org.springframework.stereotype.Service;

/** Created by weiqiang on 2016/11/3. */
@Service
public class TDgapCommonService extends BaseService<TDgapCommomProvider, TDgapAlertConfig> {

  @DubboReference private TDgapCommomProvider provider;
  /**
   * 添加预警配置信息
   *
   * @param
   * @return
   */
  public Object adds(String json, String token) {
    return provider.add(json, token);
  }

  public Object updates(String json, String token) {
    return provider.update(json, token);
  }

  public List<TDgapCommon> checkRepeat(String targetId,String id) {
    List<TDgapCommon> commons = provider.checkRepeat(targetId,id);
    return commons;
  }

  public List checkUser(String alertId) {
    List users = provider.checkUser(alertId);
    return users;
  }
}
