package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapRoleResource;
import com.sofn.provider.dgap.TDgapRoleResourceProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/24. */
@Service
public class TDgapRoleResourceService
    extends BaseService<TDgapRoleResourceProvider, TDgapRoleResource> {
  @DubboReference
  public void setProvider(TDgapRoleResourceProvider provider) {
    this.provider = provider;
  }

  public PageInfo<Map<String, Object>> list(
      String reosurceDirectoryName,
      String reosurceName,
      String roleId,
      String sb,
      int pageNum,
      int pageSize) {
    Map<String, Object> params = new HashMap<>();
    params.put("reosurceDirectoryName", reosurceDirectoryName);
    params.put("reosurceName", reosurceName);
    params.put("roleId", roleId);
    params.put("roleList", sb);
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);

    return provider.list(params);
  }

  /**
   *
   * <!--  资源权限设置表不分页作用在下拉列表里面-->
   *
   * @return
   */
  public List<Map<String, Object>> listAll() {
    return provider.listAll();
  }
  /** 验证数据是否重复 */
  public List<TDgapRoleResource> checkRepetition(String roleId) {
    List<TDgapRoleResource> list = provider.checkRepetition(roleId);
    return list;
  }
}
