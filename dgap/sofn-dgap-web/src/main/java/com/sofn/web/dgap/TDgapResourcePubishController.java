package com.sofn.web.dgap;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.StringUtils;
import com.sofn.model.dgap.Tree;
import com.sofn.model.generator.TDgapResource;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapResourcePubishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * author tianjie
 *
 * @date 2016/11/24
 */
@RestController
@RequestMapping("resourcePublish2")
@Api("资源发布的接口")
public class TDgapResourcePubishController extends BaseController {

  @Autowired private TDgapResourcePubishService tDgapResourcePubishService;
  @Autowired private OuterApiService outerApiService;

  @ApiOperation("获取仓库表")
  @RequestMapping(value = "/getTables", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询数据仓库中已经存在的表", operationType = "查询")
  @Authorization
  public ResponseEntity<ModelMap> getTables(String tableName) {

    if (StringUtils.isNullBlank(tableName)) {
      tableName = "";
    }
    List<Tree> list = new ArrayList<>();
    list.add(tDgapResourcePubishService.getvalidTables(tableName));
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation("获取表的字段列表")
  @RequestMapping(value = "/getFields", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询数据仓库表的字段列表", operationType = "查询")
  public ResponseEntity<ModelMap> getFields(String tableId) {
    assert !StringUtils.isNullBlank(tableId);
    return setSuccessModelMap(new ModelMap(), tDgapResourcePubishService.getFields(tableId));
  }

  @ApiOperation("获取仓库表的样例数据")
  @RequestMapping(value = "/getPage", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)查询仓库表的样例数据", operationType = "查询")
  public ResponseEntity<ModelMap> getPage(Integer pageSize, Integer pageNum, String tableId) {
    if (tableId.indexOf("-") >= 0) {
      tableId = tableId.split("-")[0];
    }
    List<Map<String, Object>> fieldList = tDgapResourcePubishService.getFiledsByTableId(tableId);
    List<Map<String, Object>> list =
        tDgapResourcePubishService.getDwDatas(tableId, pageSize, pageNum, null);
    Map<String, Object> map = new HashMap<>();
    map.put("table", list);
    map.put("fields", fieldList);

    return setSuccessModelMap(new ModelMap(), map);
  }

  @ApiOperation("发布资源")
  @RequestMapping(
    value = "/configResource",
    method = RequestMethod.POST,
    consumes = {"application/x-www-form-urlencoded"}
  )
  @SystemControllerLog(description = "(共享系统)新加仓库表资源", operationType = "新增")
  public ResponseEntity<ModelMap> publishResource(
      String conditionFields,
      String publishFields,
      TDgapResource resource,
      @RequestHeader String token) {
    Assert.isNotBlank(publishFields, "publishFields");
    CurrentUser currentUser = outerApiService.getCurrentUser(token);
    try {
      String userId = null;
      if (currentUser != null) {
        userId = currentUser.getId();
      }
      tDgapResourcePubishService.publishResource(resource, conditionFields, publishFields, userId);
    } catch (Exception e) {
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }

    return setSuccessModelMap(new ModelMap());
  }
}
