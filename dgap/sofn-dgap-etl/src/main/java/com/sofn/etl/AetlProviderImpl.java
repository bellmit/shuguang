package com.sofn.etl;

import com.apusic.etl.server.api.IRepositoryApi;
import com.apusic.etl.server.api.ITransApi;
import com.apusic.etl.server.api.client.IAETLServerApiClient;
import com.apusic.etl.server.api.client.factory.AETLServerApiClientFactory;
import com.apusic.etl.server.api.exception.AETLServerApiException;
import com.apusic.etl.server.api.model.WebResult;
import com.apusic.etl.server.api.model.repository.ResourceNode;
import com.apusic.etl.server.api.model.repository.ResourceType;
import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.dgap.TDgapTransExpandMapper;
import com.sofn.dao.generator.TDgapTransMapper;
import com.sofn.model.dgap.AetlTransformation;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapTrans;
import com.sofn.provider.dgap.AetlProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.util.ResourceUtils;


/** Created by helong on 17-5-23. */
@DubboService(interfaceClass = AetlProvider.class)
public class AetlProviderImpl implements AetlProvider {

  @Value("${aetl.address}")
  private String host = "10.0.51.15";

  @Value("${aetl.port}")
  private int port = 7001;

  @Value("${aelt.username}")
  private String username = "aetl";

  @Value("${aelt.password}")
  private String password = "aetl";

  @Autowired private TDgapTransMapper tDgapTransMapper;

  @Autowired private TDgapTransExpandMapper tDgapTransExpandMapper;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public static String getConfig(String key) {
    ResourceBundle resource = ResourceBundle.getBundle("dgap-etl");
    String value = resource.getString(key);
    return value;
  }

  @Override
  public File readlist(String resourceLocation) {
    File file;
    try {
      file = ResourceUtils.getFile("classpath:" + resourceLocation);
    } catch (FileNotFoundException e) {
      file = null;
    }
    return file;
  }

  @Override
  public boolean addTransformation(String resourceId, String transformation, String transName) throws Exception {
    logger.info("addTransformation start=================================");
    logger.info("resourceId：", resourceId);
    logger.info("transformation：", transformation);
    logger.info("transName：", transName);
    logger.info("addTransformation end=================================");
    IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
    String path = "/dataimport/" + resourceId;

    List<TDgapTrans> dgapTransList = tDgapTransExpandMapper.getAllByResourceId(resourceId);
    for(TDgapTrans trans:dgapTransList){
      if(trans.getTransname().equals(transName) && trans.getFlag().equals("N")){
        return false;
      }
    }

    try {
      WebResult webResult = AetlUtils.addTransformation(client, transformation, path);
      if ("OK".equals(webResult.getResult())) {
        TDgapTrans tDgapTrans = new TDgapTrans();
        String uuidString = StringUtils.getUUIDString();
        tDgapTrans.setId(uuidString);
        tDgapTrans.setResourceId(resourceId);
        tDgapTrans.setTransname(transName);
        tDgapTrans.setFlag("N");
        tDgapTransMapper.insert(tDgapTrans);
        return true;
      }
    } catch (AETLServerApiException e) {
      e.printStackTrace();
      throw e;
    }
    return false;
  }

  @Override
  public boolean deployTransFromLocalRepo(String resourceId, String transName) {
    IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
    Map<String,Object> params = new HashMap<>();
      params.put("transName", transName);
      params.put("resourceId", resourceId);
    TDgapTrans tDgapTrans = tDgapTransExpandMapper.getMetaID(params);
    String path = "/dataimport/" + resourceId;
    if (null == tDgapTrans.getMetaid()) {
      try {
        WebResult webResult = AetlUtils.deployTransFromLocalRepo(client, transName, path, null);
        if ("OK".equals(webResult.getResult())) {
          tDgapTrans.setMetaid(webResult.getId());
          tDgapTrans.setDispatcherornot("Y");
          int i = tDgapTransMapper.updateByPrimaryKey(tDgapTrans);
          return true;
        }
        return false;
      } catch (AETLServerApiException e) {
        e.printStackTrace();
        return false;
      }
    } else {
      String metaid = tDgapTrans.getMetaid();
      try {
        WebResult webResult = AetlUtils.deployTransFromLocalRepo(client, transName, path, metaid);
        if ("OK".equals(webResult.getResult())) {
          return true;
        } else {
          return false;
        }
      } catch (AETLServerApiException e) {
        e.printStackTrace();
        return false;
      }
    }
  }

  @Override
  public PageInfo<AetlTransformation> listTransformation(
          String transName, int start, int length, List<TDgapResource> resources) {
    logger.info("list start=================================");
    logger.info("transName：", transName);
    logger.info("start：", start);
    logger.info("length：", length);
    logger.info("resources：", resources);
    logger.info("list end=================================");
    PageInfo<AetlTransformation> pageInfo = new PageInfo<>();
    List<AetlTransformation> pageList = new ArrayList();
    List<AetlTransformation> allList = new ArrayList();
    Map<String, Object> params = new HashMap<>();
    List<TDgapTrans> allTrans = tDgapTransExpandMapper.getByTransNameAndResourceId(params); //params为空时表示检索所有有效的转换
    List<String> allResourceIds = new ArrayList<>();
    for(TDgapTrans trans:allTrans){
      allResourceIds.add(trans.getResourceId());
    }
    for (TDgapResource tDgapResource : resources){
      String resouceId = tDgapResource.getId();
      if(!allResourceIds.contains(resouceId)) continue;
      String resourceName = tDgapResource.getName();
      params.put("transName", transName);
      params.put("resourceId", resouceId);
      //List<TDgapTrans> tDgapTrans = tDgapTransExpandMapper.getByTransNameAndResourceId(params);
      String path = "/dataimport/" + resouceId;

      List<TDgapTrans> byTransName = tDgapTransExpandMapper.getByTransNameAndResourceId(params);
      for (TDgapTrans trans : byTransName) {
        AetlTransformation aetlTransformation = new AetlTransformation();
        aetlTransformation.setResourceName(resourceName);
        aetlTransformation.setResourceID(resouceId);
        String statusDesc = trans.getMetaid() == null || trans.getMetaid().trim().equals("") ? "N" : "Y";
        aetlTransformation.setDeloyStatus(statusDesc);
        aetlTransformation.setDispatcher(trans.getDispatcherornot());
        aetlTransformation.setTransName(trans.getTransname());
        aetlTransformation.setDeloyPath(path);
        allList.add(aetlTransformation);
      }
    }
    // 分页
    int pageNumber = ((start + 1) / length) + 1;
    int currIdx = (pageNumber > 1 ? (pageNumber - 1) * length : 0);
    AetlTransformation transformation = null;
    for (int i = 0; i < length && i < allList.size() - currIdx; i++) {
      transformation = allList.get(currIdx + i);
      pageList.add(transformation);
    }
    pageInfo.setPageNum(pageNumber);
    pageInfo.setPageSize(length);
    pageInfo.setTotal(allList.size());
    pageInfo.setList(pageList);
    return pageInfo;
  }

  @Override
  public boolean unDeployTrans(String resourceId, String transName) {
    String path = "/dataimport/" + resourceId;
    IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
    Map<String,Object> params = new HashMap<>();
      params.put("transName", transName);
      params.put("resourceId", resourceId);
    TDgapTrans tDgapTrans = tDgapTransExpandMapper.getMetaID(params);
    String metaid = tDgapTrans.getMetaid();
    try {
      WebResult webResult = AetlUtils.unDeployTrans(client, transName, path, metaid);
      if ("OK".equals(webResult.getResult())) {
        tDgapTrans.setMetaid(null);
        int i = tDgapTransMapper.updateByPrimaryKey(tDgapTrans);
        return true;
      }
      return false;
    } catch (AETLServerApiException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean removeTransFromRepo(String resourceId, String transName) {
    String path = "/dataimport/" + resourceId;
    IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
    try {
      WebResult webResult = AetlUtils.removeTransFromRepo(client, transName, path);
      if ("OK".equals(webResult.getResult())) {
        Map<String,Object> params = new HashMap<>();
        params.put("transName", transName);
        params.put("resourceId", resourceId);
        TDgapTrans byResourceId = tDgapTransExpandMapper.getMetaID(params);
        byResourceId.setFlag("Y");
        int i = tDgapTransMapper.updateByPrimaryKey(byResourceId);
        return true;
      } else {
        return false;
      }
    } catch (AETLServerApiException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean setScheduleStatus(String resourceId, String transName, String enable) {
      Map<String,Object> params = new HashMap<>();
      params.put("transName", transName);
      params.put("resourceId", resourceId);
    TDgapTrans tDgapTrans = tDgapTransExpandMapper.getMetaID(params);
    if ("Y".equals(enable)) {
      tDgapTrans.setDispatcherornot("Y");
      int i = tDgapTransMapper.updateByPrimaryKey(tDgapTrans);
      return true;
    }
    tDgapTrans.setDispatcherornot("N");
    int t = tDgapTransMapper.updateByPrimaryKey(tDgapTrans);
    return true;
  }

  /**
   * 调度执行转换
   *
   * @return
   */
  @Override
  public boolean excuteTrans() {
    boolean result = false;
    IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
    List<TDgapTrans> byScheduleStatus = tDgapTransExpandMapper.getByScheduleStatus();
    for (TDgapTrans trans : byScheduleStatus) {
      try {
        WebResult webResult = AetlUtils.execTrans(client, trans.getTransname(), trans.getMetaid());
        if ("OK".equals(webResult.getResult())) {
          result = true;
        } else
        result = false;
      } catch (AETLServerApiException e) {
        e.printStackTrace();
        result = false;
      }
    }
    return result;
  }

  public static void main(String[] args) throws Exception{
    String host = "10.0.51.15";
    int port = 7001;
    String userName = "aetl";
    String password = "aetl";

    IAETLServerApiClient client = AETLServerApiClientFactory.createServletApiClient( host,port,userName,password);
    ITransApi transApi = client.getTransApi();
    File transFile = new File("C:\\Users\\czx\\Desktop\\农产品分类表.ktr");
    transApi.addTransToRepo(FileUtils.readFileToString( transFile,
            "UTF-8" ), "abc");
    WebResult result =
            client.getTransApi().deployTransFromLocalRepo("农产品分类表", "abc", null );
    result = client.getTransApi().execTransFromServer( "农产品分类表", result.getId(), null );

    int a=0;
  }
    @Override
    public boolean execTrans(String resourceId, String transName, String resourceName) {
        Map<String,Object> params = new HashMap<>();
        params.put("transName", transName);
        params.put("resourceId", resourceId);
        TDgapTrans tDgapTrans = tDgapTransExpandMapper.getMetaID(params);
        IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
        try {
            WebResult webResult = AetlUtils.execTrans(client, transName, tDgapTrans.getMetaid());
            if ("OK".equals(webResult.getResult())) {
                return true;
            }
        } catch (AETLServerApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
   * 批量删除
   *
   * @param resourceList
   * @return
   */
  @Override
  public boolean batchDeleteTransform(List<String> resourceList) {
    boolean result = false;
    List resultList = new ArrayList();
    IAETLServerApiClient client = AetlUtils.getClient(host, port, username, password);
    for (String resourceID : resourceList) {
      TDgapTrans byResourceId = tDgapTransExpandMapper.getByResourceId(resourceID);
      String path = "/dataimport/" + resourceID;
      String transname = byResourceId.getTransname();
      try {
        WebResult webResult = AetlUtils.removeTransFromRepo(client, transname, path);
        if ("OK".equals(webResult.getResult())) {
          byResourceId.setFlag("Y");
          int i = tDgapTransMapper.updateByPrimaryKey(byResourceId);
          result = true;
        } else {
          result = false;
        }
      } catch (AETLServerApiException e) {
        e.printStackTrace();
        result = false;
      }
    }
    return result;
  }
}
