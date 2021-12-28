/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 9:37
 */
package com.sofn.ducss.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.service.DateShowService;
import com.sofn.ducss.vo.DateShow.*;
import com.sofn.ducss.vo.homePage.DataArea;
import com.sofn.ducss.vo.param.MaterialUtilizationPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据展示控制器
 *
 * @author jiangtao
 * @version 1.0
 **/
@Api(tags = "数据展示")
@RestController
@RequestMapping("/dataShow")
public class DataShowController {

    @Autowired
    private DateShowService dateShowService;

    @SofnLog("获取柱线图数据")
    @ApiOperation(value = "获取柱线图数据")
    @GetMapping(value = "/generateMonitorCode/{year}/{type}/{administrativeLevel}/{areaCode}")
    public Result<List<ColumnLineVo> > generateMonitorCode(@PathVariable("year") String year,@PathVariable("type") String type,@PathVariable("administrativeLevel") String administrativeLevel,@PathVariable("areaCode") String areaCode) {
        List<ColumnLineVo> columnLine = dateShowService.getColumnLine(year, type, administrativeLevel,areaCode);
        return Result.ok(columnLine);
    }

    @SofnLog("获取仪表板及子弹图数据")
    @ApiOperation(value = "获取仪表板及子弹图数据")
    @GetMapping(value = "/getInstrumentAndRoket/{year}/{administrativeLevel}/{areaCode}")
    public Result<InstrumentAndRoketVo> getInstrumentAndRoket(@PathVariable("year") String year,@PathVariable("administrativeLevel") String administrativeLevel,@PathVariable("areaCode") String areaCode) {
        InstrumentAndRoketVo roketVo = dateShowService.getInstrumentAndRoket(year, administrativeLevel, areaCode);
        return Result.ok(roketVo);
    }

    @SofnLog("获取五料化数据")
    @ApiOperation(value = "获取五料化数据")
    @GetMapping(value = "/getFiveMaterialVO/{year}/{administrativeLevel}/{areaCode}")
    public Result<FiveMaterialVO> getFiveMaterialVO(@PathVariable("year") String year,@PathVariable("administrativeLevel") String administrativeLevel,@PathVariable("areaCode") String areaCode, @RequestParam(required = false) String searchStr) {
        FiveMaterialVO fiveMaterialVO = dateShowService.getFiveMaterialVO(year, administrativeLevel, areaCode,searchStr);
        return Result.ok(fiveMaterialVO);
    }

    @SofnLog("材料分页列表")
    @ApiOperation(value = "材料分页列表")
    @PostMapping(value = "/getMaterialInfo")
    public Result<PageUtils<MaterialUtilizationVo>> getMaterialInfo(@RequestBody MaterialUtilizationPageParam pageParam) {
        PageUtils<MaterialUtilizationVo> pageUtils = dateShowService.getMaterialInfo(pageParam);
        return Result.ok(pageUtils);
    }


    @SofnLog("获取14种作物相关数据")
    @ApiOperation(value = "获取14种作物相关数据")
    @GetMapping(value = "/getHistogramByCondition/{year}/{administrativeLevel}/{searchStr}/{areaCode}")
    public Result<HistogramVo> getHistogramByCondition(@PathVariable("year") String year,@PathVariable("administrativeLevel") String administrativeLevel,@PathVariable("searchStr") String searchStr,@PathVariable("areaCode") String areaCode) {
        List<HistogramVo> histogramByCondition = dateShowService.getHistogramByCondition(year, areaCode, searchStr, administrativeLevel);
        return Result.ok(histogramByCondition);
    }

    @SofnLog("获取地图数据接口")
    @ApiOperation(value = "获取地图数据接口")
    @GetMapping(value = "/getMapViewData/{year}/{administrativeLevel}/{areaCode}")
    public Result<MapViewData> getMapViewData(@PathVariable("year") String year,@PathVariable("administrativeLevel") String administrativeLevel,@PathVariable("areaCode") String areaCode) {
        MapViewData mapViewData = dateShowService.getMapViewData(year, administrativeLevel, areaCode);
        return Result.ok(mapViewData);
    }


    @SofnLog("数据展示首页(数据范围 展示填报县,抽样分散户数,市场主体数)")
    @ApiOperation(value = "数据展示首页(数据范围 展示填报县,抽样分散户数,市场主体数)")
    @GetMapping(value = "/getDataAreaInDataShow/{year}/{administrativeLevel}/{areaCode}")
    public Result<DataArea> getDataAreaInDataShow(@PathVariable(value = "year") @ApiParam(value="所选年份",required = true)  String year, @PathVariable("administrativeLevel") @ApiParam(value="当前地图等级",required = true) String administrativeLevel, @PathVariable("areaCode") @ApiParam(value="地图区域id",required = true) String areaCode) {
        DataArea dataShow = dateShowService.getDataAreaInDataShow(year, administrativeLevel, areaCode);
        return Result.ok(dataShow);
    }

    @SofnLog("数据展示首页(秸秆资源数据)")
    @ApiOperation(value = "数据展示首页(秸秆资源数据)")
    @GetMapping(value = "/getStrawResourceDataVo/{year}/{administrativeLevel}/{areaCode}")
    public Result<StrawResourceDataVo> getStrawResourceDataVo(@PathVariable(value = "year") @ApiParam(value="所选年份",required = true)  String year, @PathVariable("administrativeLevel") @ApiParam(value="当前地图等级",required = true) String administrativeLevel, @PathVariable("areaCode") @ApiParam(value="地图区域id",required = true) String areaCode) {
        StrawResourceDataVo strawResourceDataVo = dateShowService.getStrawResourceDataVo(year, administrativeLevel, areaCode);
        return Result.ok(strawResourceDataVo);
    }

    @SofnLog("获取首页地图数据接口数据")
    @ApiOperation(value = "获取首页地图数据接口数据")
    @GetMapping(value = "/getDataAreaMapView")
    public Result<MapViewData> getDataAreaMapView(@RequestParam(value = "year")
                                                      @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode,
            @RequestParam(value = "dataType") @ApiParam(value="数据类型:reportCounty 填报县数,strawUtilize 市场规模化主体数,disperseUtilize 抽样分散户数",required = true) String dataType) {
        MapViewData mapViewData = dateShowService.getDataAreaMapView(year, administrativeLevel, areaCode, dataType);
        return Result.ok(mapViewData);
    }

    @SofnLog("秸秆产生情况数据展示(柱图)")
    @ApiOperation(value = "秸秆产生情况数据展示(柱图)")
    @GetMapping(value = "/getTheoryResource")
    public Result<ColumnPieChartVo> getTheoryResource(@RequestParam(value = "year")
            @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
                                                         ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,产生量 theory_resource ,可收集量 collect_resource",required = true) String dataType,@RequestParam(value = "strawType",required = false) @ApiParam(value="秸秆类型",required = false) String strawType
    ) {
        List<ColumnPieChartVo> theoryResource = dateShowService.getTheoryResource(year, administrativeLevel, areaCode, dataType, strawType);
        return Result.ok(theoryResource);
    }

    @SofnLog("秸秆产生情况数据展示(饼图)")
    @ApiOperation(value = "秸秆产生情况数据展示(饼图)")
    @GetMapping(value = "/getTheoryResourceGroupByStrawType")
    public Result<ColumnPieChartVo> getTheoryResourceGroupByStrawType(@RequestParam(value = "year")
                                                      @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,产生量 theory_resource ,可收集量 collect_resource",required = true) String dataType
    ) {
        List<ColumnPieChartVo> theoryResource = dateShowService.getTheoryResourceGroupByStrawType(year, administrativeLevel, areaCode, dataType);
        return Result.ok(theoryResource);
    }

    @SofnLog("秸秆产生情况数据展示(地图一)")
    @ApiOperation(value = "秸秆产生情况数据展示(地图一)")
    @GetMapping(value = "/getTheoryResourceMapViewOne")
    public Result<MapViewData> getTheoryResourceMapViewOne(@RequestParam(value = "year")
                                                                      @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,产生量 theory_resource ,可收集量 collect_resource",required = true) String dataType
    ) {
        MapViewData resourceMapViewOne = dateShowService.getTheoryResourceMapViewOne(year, administrativeLevel, areaCode, dataType);
        return Result.ok(resourceMapViewOne);
    }

    @SofnLog("秸秆产生情况数据展示(六大区柱状图)")
    @ApiOperation(value = "秸秆产生情况数据展示(六大区柱状图)")
    @GetMapping(value = "/getTheoryResourceBySixRegions")
    public Result<ColumnPieChartVo> getTheoryResourceBySixRegions(@RequestParam(value = "year")
                                                           @ApiParam(value="所选年份",required = true)  String year,@RequestParam(value = "areaCode",required = false)@ApiParam(value="地图区域id") String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型",required = true) String dataType
    ) {
        List<ColumnPieChartVo> sixRegions = dateShowService.getTheoryResourceBySixRegions(year, areaCode, dataType);
        return Result.ok(sixRegions);
    }

    @SofnLog("秸秆产生情况数据展示(六大区饼状图)")
    @ApiOperation(value = "秸秆产生情况数据展示(六大区饼状图)")
    @GetMapping(value = "/resourcePieBySixRegions")
    public Result<ColumnPieChartVo> resourcePieBySixRegions(@RequestParam(value = "year")
                                                                  @ApiParam(value="所选年份",required = true)  String year,@RequestParam(value = "areaCode",required = false)@ApiParam(value="地图区域id") String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型",required = true) String dataType,@RequestParam(value = "strawType",required = false) @ApiParam(value="秸秆类型") String strawType
    ) {
        List<ColumnPieChartVo> resourcePieBySixRegions = dateShowService.getResourcePieBySixRegions(year, areaCode, dataType, strawType);
        return Result.ok(resourcePieBySixRegions);
    }


    @SofnLog("秸秆产生情况数据展示(地图二)")
    @ApiOperation(value = "秸秆产生情况数据展示(地图二)")
    @GetMapping(value = "/getTheoryResourceMapViewTwo")
    public Result<MapViewData> getTheoryResourceMapViewTwo(@RequestParam(value = "year")
                                                           @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级",required = true) String administrativeLevel,@RequestParam(value = "dataType") @ApiParam(value="数据类型",required = true) String dataType
    ) {
        MapViewData mapViewTwo = dateShowService.getTheoryResourceMapViewTwo(year, administrativeLevel,dataType);
        return Result.ok(mapViewTwo);
    }

    @SofnLog("秸秆综合利用情况数据展示(柱图)")
    @ApiOperation(value = "秸秆综合利用情况数据展示(柱图)")
    @GetMapping(value = "/getProStrawUtilize")
    public Result<ColumnPieChartVo> getProStrawUtilize(@RequestParam(value = "year")
                                                      @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,综合利用量数据 pro_straw_utilize,综合利用率comprehensive,肥料化利用比例 fertilising,饲料化利用比例 forage,燃料化利用比例 fuel,基料化利用比例 base,原料化利用比例 material,综合利用能力指数 comprehensive_index,产业化利用能力指数 industrialization_index",required = true) String dataType,@RequestParam(value = "strawType",required = false) @ApiParam(value="秸秆类型",required = false) String strawType
    ) {
        List<ColumnPieChartVo> theoryResource = dateShowService.getProStrawUtilize(year, administrativeLevel, areaCode, dataType, strawType);
        return Result.ok(theoryResource);
    }

    @SofnLog("秸秆综合利用情况数据展示,展示当前区域五料占比或其他8种数据(柱图二)")
    @ApiOperation(value = "秸秆综合利用情况数据展示,展示当前区域五料占比或其他8种数据(柱图二)")
    @GetMapping(value = "/getProStrawUtilizeGroupByStrawType")
    public Result<ColumnPieChartVo> getProStrawUtilizeGroupByStrawType(@RequestParam(value = "year")
                                                       @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,综合利用量数据 pro_straw_utilize,综合利用率comprehensive,肥料化利用比例 fertilising,饲料化利用比例 forage,燃料化利用比例 fuel,基料化利用比例 base,原料化利用比例 material,综合利用能力指数 comprehensive_index,产业化利用能力指数 industrialization_index",required = true) String dataType
    ) {
        List<ColumnPieChartVo> theoryResource = dateShowService.getProStrawUtilizeGroupByStrawType(year, administrativeLevel, areaCode, dataType);
        return Result.ok(theoryResource);
    }

    @SofnLog("秸秆综合利用情况数据展示地图")
    @ApiOperation(value = "秸秆综合利用情况数据展示地图")
    @GetMapping(value = "/getProStrawUtilizeMapView")
    public Result<MapViewData> getProStrawUtilizeMapView(@RequestParam(value = "year")
                                                                       @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,综合利用量数据 pro_straw_utilize,综合利用率comprehensive,肥料化利用比例 fertilising,饲料化利用比例 forage,燃料化利用比例 fuel,基料化利用比例 base,原料化利用比例 material ,industrialization_index,综合利用能力指数 comprehensive_index,产业化利用能力指数 industrialization_index",required = true) String dataType
    ) {
        MapViewData proStrawUtilizeMapView = dateShowService.getProStrawUtilizeMapView(year, administrativeLevel, areaCode, dataType);
        return Result.ok(proStrawUtilizeMapView);
    }

    @SofnLog("(六大区)秸秆利用情况展示柱图(可选秸秆类型)(根据区域分组)")
    @ApiOperation(value = "(六大区)秸秆利用情况展示柱图(可选秸秆类型)(根据区域分组)")
    @GetMapping(value = "/getProStrawUtilizeBySixRegions")
    public Result<ColumnPieChartVo> getProStrawUtilizeBySixRegions(@RequestParam(value = "year")
                                                       @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode",required = false)@ApiParam(value="地图区域id,不传为六大区分组,传为指定区域分组",required = false) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,综合利用量数据 pro_straw_utilize,综合利用率comprehensive,肥料化利用比例 fertilising,饲料化利用比例 forage,燃料化利用比例 fuel,基料化利用比例 base,原料化利用比例 material,综合利用能力指数 comprehensive_index,产业化利用能力指数 industrialization_index",required = true) String dataType,@RequestParam(value = "strawType",required = false) @ApiParam(value="秸秆类型",required = false) String strawType
    ) {
        List<ColumnPieChartVo> voList = dateShowService.getProStrawUtilizeBySixRegions(year, administrativeLevel, areaCode, dataType, strawType);
        return Result.ok(voList);
    }


    @SofnLog("(六大区)秸秆利用情况展示第二个柱图(可选秸秆类型)(根据秸秆类型分组)")
    @ApiOperation(value = "(六大区)秸秆利用情况展示第二个柱图(可选秸秆类型)(根据秸秆类型分组)")
    @GetMapping(value = "/getProStrawUtilizeBySixRegionsTwo")
    public Result<ColumnPieChartVo> getProStrawUtilizeBySixRegionsTwo(@RequestParam(value = "year")
                                                                   @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode",required = false)@ApiParam(value="地图区域id,不传为六大区分组,传为指定区域分组",required = false) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,综合利用量数据 pro_straw_utilize,综合利用率comprehensive,肥料化利用比例 fertilising,饲料化利用比例 forage,燃料化利用比例 fuel,基料化利用比例 base,原料化利用比例 material,综合利用能力指数 comprehensive_index,产业化利用能力指数 industrialization_index",required = true) String dataType,@RequestParam(value = "strawType",required = false) @ApiParam(value="秸秆类型",required = false) String strawType
    ) {
        List<ColumnPieChartVo> voList = dateShowService.getProStrawUtilizeBySixRegionsTwo(year, administrativeLevel, areaCode, dataType, strawType);
        return Result.ok(voList);
    }

    @SofnLog("秸秆综合利用情况数据六大区展示地图")
    @ApiOperation(value = "秸秆综合利用情况数据六大区展示地图")
    @GetMapping(value = "/getProStrawUtilizeMapViewTwo")
    public Result<MapViewData> getProStrawUtilizeMapViewTwo(@RequestParam(value = "year")
                                                         @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "dataType") @ApiParam(value="数据类型,综合利用量数据 pro_straw_utilize,综合利用率comprehensive,肥料化利用比例 fertilising,饲料化利用比例 forage,燃料化利用比例 fuel,基料化利用比例 base,原料化利用比例 material ,industrialization_index,综合利用能力指数 comprehensive_index,产业化利用能力指数 industrialization_index",required = true) String dataType
    ) {
        MapViewData proStrawUtilizeMapView = dateShowService.getProStrawUtilizeMapViewTwo(year, administrativeLevel, areaCode, dataType);
        return Result.ok(proStrawUtilizeMapView);
    }


    @SofnLog("数据对比")
    @ApiOperation(value = "数据对比")
    @GetMapping(value = "/getDataCompare")
    public Result<DataCompareVo> getDataCompare(@RequestParam(value = "year")
                                                            @ApiParam(value="所选年份",required = true)  String year, @RequestParam(value = "administrativeLevel") @ApiParam(value="当前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county",required = true) String administrativeLevel,@RequestParam(value = "areaCode")@ApiParam(value="地图区域id",required = true) String areaCode
            ,@RequestParam(value = "strawType",required = false) @ApiParam(value="秸秆类型",required = false) String strawType
    ) {
        DataCompareVo dataCompare = dateShowService.getDataCompare(year, administrativeLevel, areaCode, strawType);
        return Result.ok(dataCompare);
    }
}
