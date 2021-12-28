package com.sofn.common.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 地图页面Controller
 * @author qzj
 */
@Slf4j
@Api(value = "地图页面接口", tags = "地图页面接口")
@RestController
@RequestMapping("/map")
public abstract class BaseMapController extends BaseController{

  @SofnLog("获取地图页相关统计数据")
  @ApiOperation(value="获取地图页统计数据")
  @GetMapping("/getData")
  public abstract Result<MapViewData> getData(
    @ApiParam(name="index",value = "指标",required = true) @RequestParam(value = "index") String index,
    @ApiParam(name="adLevel",value = "行政级别",required = true) @RequestParam(value = "adLevel") String adLevel,
    @ApiParam(name="conditions",value = "其他条件") @RequestParam(value = "conditions",required = false) String... conditions
  );

}
