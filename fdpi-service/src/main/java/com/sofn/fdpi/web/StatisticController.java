package com.sofn.fdpi.web;

import com.sofn.common.map.MapConditions;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.fdpi.service.StatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计分析-数据展示相关接口
 * wXY
 * 2020-7-30 18:29:01
 */
@Api(value = "统计分析-数据展示相关接口", tags = "统计分析-数据展示相关接口")
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @ApiOperation(value = "数据展示-统计")
    @RequiresPermissions("fdpi:dataShowInMap:query")
    @GetMapping("/getMapViewData")
    public Result<MapViewData> getMapViewData(
            @RequestParam("index") @ApiParam(name = "index", value = "指标:1:物种分布;2:标识数量;3:标识总数；4：新注册企业数量；5：企业总数", required = true) String index
            , @RequestParam("adLevel") @ApiParam(name = "adLevel", value = "行政级别:国家级:ad_country;省级:ad_province;市级:ad_city", required = true) String adLevel
            , @RequestParam(value = "adCode", required = false) @ApiParam(name = "adCode", value = "行政区域代码") String adCode
            , @RequestParam(value = "speciesId", required = false) @ApiParam(name = "speciesId", value = "物种id，物种分布和标识数量必选物种") String speciesId
            , @RequestParam(value = "year") @ApiParam(name = "year", value = "年度", required = true) String year) {

        return Result.ok(statisticService.getMapViewData(index, adLevel, adCode, speciesId, year));
    }


    /**
     * 获取筛选条件值列表
     * wXY
     * 2020-7-30 18:29:12
     */
    @ApiOperation(value = "数据展示地图中-下拉数据")
    @GetMapping("/getMapConditions")
    public Result<List<MapConditions>> getMapConditions() {
        return Result.ok(statisticService.getMapConditions());
    }
}
