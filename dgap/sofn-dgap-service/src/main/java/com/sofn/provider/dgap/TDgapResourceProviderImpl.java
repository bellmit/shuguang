package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.dao.dgap.TDgapResourceExpandMapper;
import com.sofn.dao.generator.TDgapResourceMapper;
import com.sofn.model.generator.TDgapResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/10/13. */
@DubboService(interfaceClass = TDgapResourceProvider.class)
public class TDgapResourceProviderImpl extends BaseProviderImpl<TDgapResource>
    implements TDgapResourceProvider {
  @Autowired private TDgapResourceExpandMapper dgapResourceMapper;
  @Autowired private TDgapResourceApplicationExpandMapper applicationExpandMapper;
  @Autowired private TDgapResourceMapper resourceMapper;

  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = dgapResourceMapper.getPagesList(params);
    for (Map<String, Object> s : list) {
      String id = (String) s.get("ID");
      int countByResourceId = applicationExpandMapper.countByResourceId(id);
      if (countByResourceId == 0) {
        s.put("flagStatus", "enable");
      } else s.put("flagStatus", "disEnable");
    }
    long count = dgapResourceMapper.getPageCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  /*<!--通过资源名字级联资源目录表查询-->*/
  @Override
  public List<TDgapResource> getResourceNameList(String name) {
    List<TDgapResource> resourcesList = new ArrayList<>();
    resourcesList = dgapResourceMapper.getResourceNameList(name);

    return resourcesList;
  }

  @Override
  public TDgapResource getDgapResource(String resourceId) {

    return resourceMapper.selectByPrimaryKey(resourceId);
  }

  @Override
  public List<TDgapResource> getRes() {
    return dgapResourceMapper.selectRes();
  }

  /**
   * 资源管理根据ID的修改数据回显
   *
   * @param
   * @return
   */
  @Override
  public List<Map> getResourceData(String id) {

    return dgapResourceMapper.getResourceData(id);
  }

  @Override
  public void batchDelete(String[] ids) {
    dgapResourceMapper.batchDelete(ids);
  }

  @Override
  public List<TDgapResource> getResourceByDirId(String id) {
    return dgapResourceMapper.getResourceByDirId(id);
  }

  @Override
  public TDgapResource getResourceDelFlagByResourceId(String resourceId) {
    return dgapResourceMapper.getResourceDelFlagByResourceId(resourceId);
  }

  @Override
  public List<TDgapResource> getResourceByName(String resourceName) {
    return dgapResourceMapper.getResourceByName(resourceName);
  }

  @Override
  public List<TDgapResource> getResourceList() {
    return dgapResourceMapper.getResourceList();
  }

  @Override
  public List<TDgapResource> getResourceByFilePath(String filePath) {
    return dgapResourceMapper.getResourceByFilePath(filePath);
  }

  @Override
  public List<TDgapResource> getResourceByType(String typeCode) {
    return dgapResourceMapper.getResourceByType(typeCode);
  }

  @Override
  public List<String> getAllPath() {
     return dgapResourceMapper.getAllPath();
  }

  @Override
  public List<String> getAllPathsById(String resourceId) {
    return dgapResourceMapper.getAllPathsById(resourceId);
  }
}
