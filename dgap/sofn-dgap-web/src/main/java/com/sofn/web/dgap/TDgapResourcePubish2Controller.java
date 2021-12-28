package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.StringUtils;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.Tree;
import com.sofn.model.generator.TDgapDataImportModel;
import com.sofn.model.generator.TDgapResource;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapResourcePubish2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * author tianjie
 *
 * @date 2016/11/24
 */
@RestController
@RequestMapping("resourcePublish")
@Api("资源发布的接口")
public class TDgapResourcePubish2Controller extends BaseController {

  @Autowired private TDgapResourcePubish2Service tDgapResourcePubish2Service;
  @Autowired private OuterApiService outerApiService;

  @Value("${pre.ws.baseurl}")
  private  String exportBaseUrl;

  @ApiOperation("获取仓库表")
  @RequestMapping(value = "/getTables", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询数据仓库中已经存在的表", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getTables(@RequestBody TDgapDataImportModel model) {
    if(model.getTableName().length()>30){
      return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
    }
    if (StringUtils.isNullBlank(model.getTableName())) {
        model.setTableName("");
    }
    List<Tree> list = new ArrayList<>();
    list.add(tDgapResourcePubish2Service.getvalidTables(model.getTableName()));
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation("获取表的字段列表")
  @RequestMapping(value = "/getFields", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询数据仓库表的字段列表", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getFields(String tableId) {
    assert !StringUtils.isNullBlank(tableId);
    return setSuccessModelMap(new ModelMap(), tDgapResourcePubish2Service.getFields(tableId));
  }

  @ApiOperation("获取仓库表及其表字段")
  @RequestMapping(value = "/getTablesDetails", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)获取仓库表及其表字段 ", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getTablesDetails( Integer start, Integer length) {
    Map<String, Object> params = new HashMap<>();
    params.put("start", start);
    params.put("length", length);
    Map<String, List<Map<String, Object>>> map=tDgapResourcePubish2Service.getAllTables(params);
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    long count =tDgapResourcePubish2Service.getAllTablesCount();

    List list=new ArrayList();
    list.add(map);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return setSuccessModelMap(new ModelMap(), pageInfo);
  }

  @ApiOperation("获取资源的已发布字段")
  @RequestMapping(value = "/getPublishedFiledsByResourceId", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询资源包含的发布的字段", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getPublishedFiledsByResourceId(String resourceId) {
    assert !StringUtils.isNullBlank(resourceId);
    return setSuccessModelMap(new ModelMap(), tDgapResourcePubish2Service.getPublishedFiledsByResourceId(resourceId));
  }

  @ApiOperation("获取仓库表的样例数据根据start和length")
  @RequestMapping(value = "/getPages", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询仓库表的样例数据", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getPages(Integer start, Integer length, String tableId) {
    Integer pageSize=length;
    Integer pageNum=(start/length)+1;
    if (tableId.indexOf("-") >= 0) {
      tableId = tableId.split("-")[0];
    }
    PageInfo<Map<String, Object>> list =
            tDgapResourcePubish2Service.getDwDatas(tableId, pageSize, pageNum, null);
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation("获取表的字段列表")
  @RequestMapping(value = "/getColumns", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询数据仓库表的字段列表", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getColumns(String tableId) {
    assert !StringUtils.isNullBlank(tableId);
    List<Map<String, Object>> fieldList = tDgapResourcePubish2Service.getFiledsByTableId(tableId);
    Map<String, Object> map = new HashMap<>();
    map.put("fields", fieldList);
    return setSuccessModelMap(new ModelMap(), map);
  }
  /** 得到默认资源URL */
  @ApiOperation(value = "得到默认资源URL")
  @RequestMapping(value = "/getbaseUrls")
  @SystemControllerLog(description = "(共享系统)得到默认资源URL", operationType = "查询")
  public Object getbaseUrls() {
    String baseUrls= Constants.getDataExportWsdlUrl(this.exportBaseUrl);
    return setSuccessModelMap(new ModelMap(), baseUrls);
  }

  @ApiOperation("发布资源")
  @RequestMapping(value = "/configResource2", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)新加仓库表资源", operationType = "新增")
  @Authorization
  public ResponseEntity<ModelMap> publishResource2(@RequestBody TDgapResource resource, @RequestHeader String tableId,
                                                   @RequestHeader String token) {

    if(resource.getName()==null||resource.getName().length()>30){
      return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
    }
    if(resource.getWsdlUrl()==null||resource.getWsdlUrl().length()>100){
      return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
    }
    if(resource.getFilePath()==null||resource.getFilePath().length()>100){
      return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
    }
    if(resource.getDescription()==null||resource.getDescription().length()>300){
      return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
    }
    String publishFields=resource.getPublishFields();
    String conditionFields=resource.getConditionFields();
    Assert.isNotBlank(publishFields, "publishFields");
    CurrentUser currentUser = outerApiService.getCurrentUser(token);
    try {
      String userId = null;
      if (currentUser != null) {
        userId = currentUser.getId();
      }
      tDgapResourcePubish2Service.publishResource(
              resource, conditionFields, publishFields, tableId, userId);
    } catch (Exception e) {
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    return setSuccessModelMap(new ModelMap());
  }
}
