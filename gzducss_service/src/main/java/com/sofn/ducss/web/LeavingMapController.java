package com.sofn.ducss.web;

import com.sofn.ducss.map.MapViewData;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.LeavingMapService;
import com.sofn.ducss.util.BoolUtils;
import com.sofn.ducss.vo.DateShow.ColumnPieChartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leavingMap")
@Api(tags = "还田离田地图相关接口")
public class LeavingMapController {

    @Autowired
    private LeavingMapService leavingMapService;

    @GetMapping("getLeavingRatio")
    @ApiOperation(value = "获取还田离田情况地图", notes = "ducss:leavingMap:getLeavingRatio")
    public Result<MapViewData> getLeavingRatio(@ApiParam(value = "年度", required = true) @RequestParam String year,
                                               @ApiParam(value = "前地图等级,全国(部级) ministry,省级 province ,市级 city,县级 county", required = true) @RequestParam String administrativeLevel,
                                               @ApiParam(value = "父级区划代码，全国传入100000", required = true) @RequestParam String areaCode,
                                               @ApiParam(value = "需要查询的数据1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例", required = true) @RequestParam String dataType,
                                               @ApiParam(value = "是否查询六大区数据，Y 是  ") @RequestParam(required = false) String isSixRegion,
                                               @ApiParam(value = "查询六大区某一个区的数据。 请传入各个区包含的省的ID") @RequestParam(required = false) String sixRegionCodes,
                                               @ApiParam(value = "作物类型：参考支撑平台数据字典作物类型   all表示查询所有 ") @RequestParam(required = false) String strawType) {
        if (StringUtils.isBlank(dataType)) {
            dataType = "1";
        }
        if(StringUtils.isNotBlank(sixRegionCodes) && "all".equals(sixRegionCodes)){
            sixRegionCodes = null;
        }
        if(StringUtils.isNotBlank(strawType) && "all".equals(strawType)){
            strawType = null;
        }

        boolean isSixRegionB =  getIsSixRegionBoolean(isSixRegion);

        MapViewData leavingRatio = leavingMapService.getLeavingRatio(year, administrativeLevel, areaCode, dataType, isSixRegionB,sixRegionCodes, strawType);
        return Result.ok(leavingRatio);
    }

    @GetMapping("getColumnPieChartVo")
    @ApiOperation(value = "获取还田离田情况柱图", notes = "ducss:leavingMap:getColumnPieChartVo")
    public Result<List<ColumnPieChartVo>> getColumnPieChartVo(@ApiParam(value = "年度", required = true) @RequestParam String year,
                                                              @ApiParam(value = "父级区划代码，全国传入100000", required = true) @RequestParam String areaCode,
                                                              @ApiParam(value = "需要查询的数据1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例", required = true) @RequestParam String dataType,
                                                              @ApiParam(value = "是否查询六大区数据，Y 是  ") @RequestParam(required = false) String isSixRegion,
                                                              @ApiParam(value = "查询六大区某一个区的数据。 请传入各个区包含的省的ID") @RequestParam(required = false) String sixRegionCodes,
                                                              @ApiParam(value = "作物类型：参考支撑平台数据字典作物类型， all表示查询所有") @RequestParam(required = false) String strawType) {
        if (StringUtils.isBlank(dataType)) {
            dataType = "1";
        }

        if(StringUtils.isNotBlank(sixRegionCodes) && "all".equals(sixRegionCodes)){
            sixRegionCodes = null;
        }
        if(StringUtils.isNotBlank(strawType) && "all".equals(strawType)){
            strawType = null;
        }
        boolean isSixRegionB =  getIsSixRegionBoolean(isSixRegion);
        List<ColumnPieChartVo> columnPieChartVo = leavingMapService.getColumnPieChartVo(year, areaCode, dataType, isSixRegionB,sixRegionCodes, strawType);
        return Result.ok(columnPieChartVo);
    }

    @GetMapping("getLeavingPieChart")
    @ApiOperation(value = "获取还田离田情况饼图", notes = "ducss:leavingMap:getLeavingPieChart")
    public Result<List<ColumnPieChartVo>> getLeavingPieChart(@ApiParam(value = "年度", required = true) @RequestParam String year,
                                                             @ApiParam(value = "需要查询的数据1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例", required = true) @RequestParam String dataType,
                                                             @ApiParam(value = "父级区划代码，全国传入100000", required = true) @RequestParam String areaCode,
                                                             @ApiParam(value = "是否查询六大区数据，Y 是  ") @RequestParam(required = false) String isSixRegion,
                                                             @ApiParam(value = "查询六大区某一个区的数据。 请传入各个区包含的省的ID") @RequestParam(required = false) String sixRegionCodes
                                                             ){
        if (StringUtils.isBlank(dataType)) {
            dataType = "1";
        }
        if(StringUtils.isNotBlank(sixRegionCodes) && "all".equals(sixRegionCodes)){
            sixRegionCodes = null;
        }
        boolean isSixRegionB = getIsSixRegionBoolean(isSixRegion);
        List<ColumnPieChartVo> columnPieChartVo = leavingMapService.getLeavingPieChart(year, areaCode, dataType, isSixRegionB, sixRegionCodes);
        return Result.ok(columnPieChartVo);
    }

    /**
     * 根据传入的是否六大区获取boolean
     * @param isSixRegion 是否查询六大区数据，Y 是
     * @return  true or false
     */
    private boolean getIsSixRegionBoolean(String isSixRegion){
        if (StringUtils.isBlank(isSixRegion)) {
            isSixRegion = "1";
        }
        boolean isSixRegionB = false;
        if (!StringUtils.isBlank(isSixRegion) && BoolUtils.Y.equals(isSixRegion)) {
            isSixRegionB = true;
        }
        return isSixRegionB;
    }

}
