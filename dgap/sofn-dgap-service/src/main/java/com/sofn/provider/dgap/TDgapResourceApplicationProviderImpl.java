package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.dao.generator.TDgapResourceApplicationMapper;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by weiqiang on 2016/10/27. */
@DubboService(interfaceClass = TDgapResourceApplicationProvider.class)
public class TDgapResourceApplicationProviderImpl extends BaseProviderImpl<TDgapResourceApplication>
    implements TDgapResourceApplicationProvider{
  @Autowired private TDgapResourceApplicationExpandMapper applicationExpandMapper;

  @Autowired private TDgapResourceApplicationMapper applicationMapper;

  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    //  params.put("roleIds","'1','2'");
    List<Map<String, Object>> list = applicationExpandMapper.getList(params);
    long count = applicationExpandMapper.getCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public PageInfo<Map<String, Object>> pageList(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = applicationExpandMapper.getResourcePage(params);
    long count = applicationExpandMapper.getResourceCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public List<TDgapResource> getResource() {
    return applicationExpandMapper.getResource();
  }

  @Override
  public Map<String, Object> getResourceById(String id) {
    return applicationExpandMapper.getResourceById(id);
  }

  @Override
  public void batchDelete(String[] ids) {
    applicationExpandMapper.batchDelete(ids);
  }

  @Override
  public List<TDgapResourceApplication> getUser() {
    return applicationMapper.selectAll();
  }

  @Override
  public List<String> queryCallerToken() {
    return applicationExpandMapper.queryCallerToken();
  }

  @Override
  public int countByResourceId(String resourceId) {
    return applicationExpandMapper.countByResourceId(resourceId);
  }

  @Override
  public int countByResources(String[] ids) {
    return applicationExpandMapper.countByResources(ids);
  }

  @Override
  public TDgapResourceApplication getbyParam(String userId) {
    return applicationExpandMapper.getbyParam(userId);
  }

  @Override
  public List<TDgapResourceApplication> getByResourceType(String resourceType) {
    return applicationExpandMapper.getByResourceType(resourceType);
  }
  @Override
  public List<TDgapResourceApplication> getbyParams(String localPlatformAppliId) {
    return applicationExpandMapper.getbyParams(localPlatformAppliId);
  }

  @Override
  public TDgapResourceApplication getAppliByToken(String resourceId, String token) {
    return applicationExpandMapper.getAppliByToken(resourceId,token);
  }

  @Override
  public PageInfo<Map<String, Object>> listDeatails(Map<String, Object> map) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = applicationExpandMapper.getListDeatails(map);
    long count = applicationExpandMapper.getCountListDeatails(map);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public Map<String, Object> getResourceApplicationDetails(String id) {
    return applicationExpandMapper.getResourceApplicationDetails(id);
  }
}
