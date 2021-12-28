package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.service.dgap.TDgapTbResourceConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Created by Administrator on 2016/11/23. */
@RestController
@Api(value = "表资源结构", description = "表资源结构")
@RequestMapping(value = "/tbResourceConfig")
public class TDgapTbResourceConfigController extends BaseController {
  @Autowired TDgapTbResourceConfigService tDgapTbResourceConfigService;

  @ApiOperation(value = "表资源结构列表")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)表资源结构列表", operationType = "查询")
  @Authorization
  public Object list(String resourceId, int start, int length) {
    PageInfo<Map<String, Object>> list =
        tDgapTbResourceConfigService.list(resourceId, ((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }
}
