package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapResource;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapAlertConfigService;
import com.sofn.service.dgap.TDgapResourceApplicationService;
import com.sofn.service.dgap.TDgapResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/** Created by Administrator on 2016/10/13. */
@RestController
@Api(value = "资源管理", description = "资源管理")
@RequestMapping(value = "/resource")
public class TDgapResourceController extends BaseController {
  @Autowired private TDgapResourceService resourceMapperService;
  @Autowired private TDgapResourceApplicationService resourceApplicationService;
  @Autowired private TDgapAlertConfigService alertConfigService;
  @Autowired private OuterApiService resourceOuterApiService;

  @Value("${pre.ws.baseurl}")
  private  String baseUrl;

  /**
   * 分页列表
   *
   * @param resourcename
   * @param start
   * @param length
   * @return
   */
  @ApiOperation(value = "资源列表")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)查询资源列表", operationType = "查询")
  @Authorization
  public Object list(String resourcename, String type, String directoryId, int start, int length) {
    if(resourcename.length()>30){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }

    if (resourcename != null && !resourcename.equals("")) {
      resourcename = resourcename.trim();
    }
    PageInfo<Map<String, Object>> list =
        resourceMapperService.list(
            resourcename, type, directoryId, ((start + 1) / length) + 1, length);

    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 新增资源
   *
   * @param resource
   * @return
   */
  @ApiOperation(value = "新增资源")
  @PostMapping(value = "/add")
  @SystemControllerLog(description = "(共享系统)新增资源", operationType = "新增")
  public Object addResource(@RequestBody TDgapResource resource) {
    if(resource.getName()==null||resource.getName().length()>30){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resource.getWsdlUrl()==null||resource.getWsdlUrl().length()>100){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resource.getFilePath()==null||resource.getFilePath().length()>100){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resource.getDescription()==null||resource.getDescription().length()>300){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    String filePath=resource.getFilePath();
    //查询所有的文件路径
    List<String> allPath=resourceMapperService.getAllPath();
    if(allPath.contains(filePath)){
      return setModelMap(new ModelMap(), HttpCode.CONFLICT, "文件路径重复");
    }
    // 判断新增资源类型是否是仓库资源
    String type = resource.getType();
    if (type == null) {
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    } else {
      if (type.equals("ds")) {
        return setModelMap(new ModelMap(), HttpCode.CONFLICT,"不能新增资源类型为仓库资源的数据");
      } else {
        TDgapResource add = resourceMapperService.add(resource);
        if (add == null) {
          return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        } else {
          return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
        }
      }
    }
  }

  /**
   * 修改资源
   *
   * @param resource
   * @return
   */
  @ApiOperation(value = "修改资源")
  @RequestMapping(value = "/update")
  @SystemControllerLog(description = "(共享系统)修改资源", operationType = "修改")
  public Object updateResource(@RequestBody TDgapResource resource) {
    if(resource.getName().length()>30){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resource.getWsdlUrl().length()>100){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resource.getFilePath().length()>100){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resource.getDescription().length()>300){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    String filePath=resource.getFilePath();
    //查询所有的文件路径
    List<String> allPath=resourceMapperService.getAllPathsById(resource.getId());
    if(allPath.contains(filePath)){
      return setModelMap(new ModelMap(), HttpCode.CONFLICT, "文件路径重复");
    }
    if (StringUtils.isNotBlank(resource.getId())) {
      // 需要查询出原有数据,再更新最新数据
      TDgapResource oldResource = resourceMapperService.queryById(resource.getId());
      if (oldResource != null) {
        oldResource.setName(resource.getName());
        oldResource.setType(resource.getType());
        oldResource.setDirectoryId(resource.getDirectoryId());
        oldResource.setWsdlUrl(resource.getWsdlUrl());
        oldResource.setDescription(resource.getDescription());
        oldResource.setFilePath(resource.getFilePath());
        oldResource.setDataNotifier(resource.getDataNotifier());
        resource = oldResource;
        resourceMapperService.update(resource);
      } else {
        return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
      }
    }
    return setSuccessModelMap(new ModelMap(), HttpCode.OK);
  }

  /** 数据回显查询 */
  @ApiOperation(value = "修改的数据回显")
  @RequestMapping(value = "/resourceData")
  @SystemControllerLog(description = "(共享系统)查询资源", operationType = "查询")
  public Object listResourceDate(@RequestBody TDgapResource resource) {
    Map list = resourceMapperService.getResoueceData(resource.getId()).get(0);
    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 删除资源
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "删除资源")
  @RequestMapping(value = "/delete")
  @SystemControllerLog(description = "(共享系统)删除资源", operationType = "删除")
  public Object deleteResource(String id) {
    TDgapResource tDgapResource = resourceMapperService.queryById(id);
    int applicationCount = resourceApplicationService.countByResourceId(id);
    int alertConfigCount = alertConfigService.countByResourceId(id);
    if (tDgapResource != null && applicationCount == 0 && alertConfigCount == 0) {
      tDgapResource.setDelFlag("Y");
      resourceMapperService.update(tDgapResource);
      return setModelMap(new ModelMap(), HttpCode.OK);
    } else {
      return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
    }
  }

  /**
   * 批量删除资源
   *
   * @return
   */
  @ApiOperation(value = "批量删除资源")
  @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)批量删除资源", operationType = "删除")
  public Object delete(String dataInfo) {
    String[] ids = dataInfo.split("\\|");
    ModelMap modelMap = new ModelMap();

    if (ids.length > 0) {
      int applicationCount = resourceApplicationService.countByResources(ids);
      int alertConfigCount = alertConfigService.countByResources(ids);
      if (applicationCount == 0 && alertConfigCount == 0) {
        resourceMapperService.batchDelete(ids);
        return setSuccessModelMap(modelMap);
      } else {
        return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
      }
    } else {
      return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
    }
  }

  /*资源状态列表*/
  @ApiOperation(value = "资源状态列表")
  @RequestMapping(value = "/resourceFlag", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)获取可选的资源状态", operationType = "查询")
  public Object getResourceType() {
    List<OuterApiService.ListItem> resourceTypes = resourceOuterApiService.getResourceStatus();
    return setSuccessModelMap(new ModelMap(), resourceTypes);
  }

  /**
   * 删除资源
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "修改资源状态")
  @RequestMapping(value = "/flagResourceUpdate")
  @SystemControllerLog(description = "(共享系统)修改资源状态", operationType = "修改")
  public Object flagResourceUpdates(String id) {
    TDgapResource tDgapResource = resourceMapperService.queryById(id);
    if (tDgapResource != null) {
      if (tDgapResource.getFlag() == null || tDgapResource.getFlag().equals("")) {
        tDgapResource.setFlag("N");
      }
      if (tDgapResource.getFlag().equals("A")) {
        tDgapResource.setFlag("N");
      } else {
        tDgapResource.setFlag("A");
      }

      resourceMapperService.update(tDgapResource);
      return setModelMap(new ModelMap(), HttpCode.OK);
    } else {
      return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
    }
  }

  /** 得到默认资源URL */
  @ApiOperation(value = "得到默认资源URL")
  @RequestMapping(value = "/getbaseUrls")
  @SystemControllerLog(description = "(共享系统)得到默认资源URL", operationType = "查询")
  public Object getbaseUrls() {
    String baseUrls= Constants.getDataImportWsdlUrl(this.baseUrl);
    return setSuccessModelMap(new ModelMap(), baseUrls);
  }

  /** 得到默认资源URL */
  @ApiOperation(value = "得到默认资源URL")
  @RequestMapping(value = "/getDataNotifier")
  @SystemControllerLog(description = "(共享系统)得到默认资源URL", operationType = "查询")
  public Object getDataNotifier() {
      List<OuterApiService.ListItem> list = new ArrayList<OuterApiService.ListItem>();
      for(Constants.DataNotifier dnf:Constants.DataNotifier.values()){
          list.add(new OuterApiService.ListItem(dnf.code(), dnf.name()));
      }
    return setSuccessModelMap(new ModelMap(), list);
  }



  /**
   * 数据导入
   * @param request
   * @return
   */
  @ApiOperation(value = "数据导入")
  @SystemControllerLog(description = "(共享系统)数据导入",operationType = "添加")
  @RequestMapping(value = "/importCsvData")
  public Object importCsvData(@RequestHeader String token, @ApiParam(value = "file", required = true)
              @RequestParam(value = "file") MultipartFile file, @RequestParam(value = "resourceId") String resourceId, HttpServletRequest request) throws Exception{

      try {
        ModelMap modelMap = resourceMapperService.importCsvData(file, resourceId, request);
        return setSuccessModelMap(modelMap);
      }catch(Exception e){
        return setModelMap(new ModelMap(), HttpCode.CONFLICT, "数据导入出错，请联系系统维护人员");
      }
  }

  /** 数据应用 */
  @ApiOperation(value = "数据应用")
  @RequestMapping(value = "/fixAppliedData")
  @SystemControllerLog(description = "(共享系统)数据应用", operationType = "查询")
  public Object fixAppliedData(String id) {

    ModelMap modelMap=resourceMapperService.fixAppliedData(id);
    return setSuccessModelMap(modelMap);
  }
}
