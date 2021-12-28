package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.provider.dgap.ApiSourcesZipProvider;
import com.sofn.provider.dgap.TDgapDataImportTableProvider;
import com.sofn.provider.dgap.TDgapResourceApplicationProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/27. */
@Service
public class TDgapResourceApplicationService
    extends BaseService<TDgapResourceApplicationProvider, TDgapResourceApplication> {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @DubboReference
  public void setProvider(TDgapResourceApplicationProvider provider) {
    this.provider = provider;
  }

  private ApiSourcesZipProvider apiSourcesZipProvider;


  @DubboReference
  public void setProvider(ApiSourcesZipProvider apiSourcesZipProvider) {
    this.apiSourcesZipProvider = apiSourcesZipProvider;
  }
  TDgapDataImportTableProvider dataImportTableProvider;

  @DubboReference
  public void setProvider(TDgapDataImportTableProvider dataImportTableProvider) {
    this.dataImportTableProvider = dataImportTableProvider;
  }
  @Value("${db.pre.url}")
  String CONNURL;

  @Value("${db.pre.username}")
  String USERNAME;

  @Value("${db.pre.password}")
  String PASSWORD;

//  @Value("${db.pre.schema}")
  String SCHEMA="DGAP_PRE";




  public PageInfo<Map<String, Object>> list(
      String resourceType,
      String userName,
      String loginedUserId,
      String resourceName,
      String roleIds,
      int pageNum,
      int pageSize) {
    Map<String, Object> map = new HashMap<>();
    map.put(Constants.PAGE_NUM, pageNum);
    map.put(Constants.PAGE_SIZE, pageSize);
    map.put("resourceType", resourceType);
    map.put("userName", userName);
    map.put("loginedUserId", loginedUserId);
    map.put("resourceName", resourceName);
    map.put("roleIds", roleIds);
    return provider.list(map);
  }

  public PageInfo<Map<String, Object>> pageList(
      String userId, String name, String type, int pageNum, int pageSize) {
    Map<String, Object> paramas = new HashMap<>();
    paramas.put("name", name);
    paramas.put("type", type);
    paramas.put("userId", userId);
    paramas.put(Constants.PAGE_NUM, pageNum);
    paramas.put(Constants.PAGE_SIZE, pageSize);
    return provider.pageList(paramas);
  }

  public List<TDgapResource> getResource() {
    return provider.getResource();
  }

  public Map<String, Object> getResourceById(String id) {
    return provider.getResourceById(id);
  }

  public void batchDelete(String[] ids) {
    provider.batchDelete(ids);
  }

  public List<TDgapResourceApplication> getUser() {
    return provider.getUser();
  }

  public List<String> queryCallerToken() {
    return provider.queryCallerToken();
  }

  public int countByResourceId(String resourceId) {
    return provider.countByResourceId(resourceId);
  }

  public int countByResources(String[] ids) {
    return provider.countByResources(ids);
  }

  public TDgapResourceApplication addResourceApplication(
      TDgapResourceApplication tDgapResourceApplication) {
    return provider.update(tDgapResourceApplication);
  }

  public TDgapResourceApplication updateResourceApplication(
      TDgapResourceApplication tDgapResourceApplication) {
    return provider.update(tDgapResourceApplication);
  }

  public byte[] batchDown( String loginedUserId) {
    List<String> names=dataImportTableProvider.getAlltableNames(loginedUserId);
    int size=names.size();
    String[] nameArray = (String[])names.toArray(new String[size]);
    byte[] data=null;
    try {
        logger.debug("table names:"+ Arrays.toString(nameArray));
        data=apiSourcesZipProvider.getZipFileContent(CONNURL,USERNAME,PASSWORD,SCHEMA,nameArray);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }

  /**
   * 资源详情列表
   * @param resourceType
   * @param userName
   * @param resourceName
   * @param pageNum
   * @param pageSize
   * @return
   */
    public PageInfo<Map<String, Object>> listDetails(String resourceType, String userName, String resourceName, int pageNum, int pageSize) {
      Map<String, Object> map = new HashMap<>();
      map.put(Constants.PAGE_NUM, pageNum);
      map.put(Constants.PAGE_SIZE, pageSize);
      map.put("resourceType", resourceType);
      map.put("userName", userName);
      map.put("resourceName", resourceName);
      return provider.listDeatails(map);
    }

  /**
   * 根据id获取资源申请详情
   * @param id
   * @return
   */
  public Map<String,Object> getResourceApplicationDetails(String id) {
    return provider.getResourceApplicationDetails(id);
  }
}
