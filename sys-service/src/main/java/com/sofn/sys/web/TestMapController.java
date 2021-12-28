package com.sofn.sys.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.map.MapConditions;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "地图页面接口", tags = "地图页面接口")
@RestController
@RequestMapping("/map")
public class TestMapController {


  @SofnLog("获取地图页相关统计数据")
  @ApiOperation(value="获取地图页统计数据")
  @GetMapping("/getData")
  public Result<MapViewData> getData(
    @ApiParam(name="index",value = "指标",required = true) @RequestParam(value = "index") String index,
    @ApiParam(name="adLevel",value = "行政级别",required = true) @RequestParam(value = "adLevel") String adLevel,
    @ApiParam(name="adCode",value = "区划代码",required = true) @RequestParam(value = "adCode") String adCode,
    @ApiParam(name="conditions",value = "其他条件") @RequestParam(value = "conditions",required = false) Map<String,String> conditions
  ){
    return null;
  }

  @SofnLog("获取筛选条件值列表")
  @ApiOperation(value="获取筛选条件值列表")
  @GetMapping("/getMapConditions")
  public Result<List<MapConditions>> getMapConditions(@ApiParam(name="index",value = "指标",required = true) @RequestParam(value = "index") String index) {
    return null;
  }
}
