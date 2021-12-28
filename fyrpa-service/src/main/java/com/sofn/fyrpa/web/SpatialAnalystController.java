package com.sofn.fyrpa.web;

import com.sofn.common.model.Result;
import com.sofn.fyrpa.service.SpatialAnalystService;
import com.sofn.fyrpa.vo.AreasVo;
import com.sofn.fyrpa.vo.CityAreasVo;
import com.sofn.fyrpa.vo.CountyAreasVo;
import com.sofn.fyrpa.vo.SpatialAnalystResourcesVoList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Api(value = "空间分析模块", tags = "空间分析的相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/spatialAnalyst")
public class SpatialAnalystController {

    @Autowired
    private SpatialAnalystService spatialAnalystService;

    @GetMapping("/selectSpatialAnalystList")
    @ApiOperation("按保护区分布查询")
    //@RequiresPermissions("fyrpa:spatialAnalyst:selectSpatialAnalystList")
   // @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public Result selectSpatialAnalystByCondition(@ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                                  @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name){
        if (StringUtils.isBlank(submitTime)) {
            submitTime = getCurrentYear();
        }
        List<SpatialAnalystResourcesVoList> list = this.spatialAnalystService.selectSpatialAnalystByCondition(submitTime, name);
        return Result.ok(list);
    }


    @GetMapping("/selectProtectionByCount")
    @ApiOperation("按保护区数量查询(省级)")
    public Result selectProtectionByCount(@ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                                  @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name){
        if (StringUtils.isBlank(submitTime)) {
            submitTime = getCurrentYear();
        }
        List<AreasVo> areasVoList = this.spatialAnalystService.selectProtectionByCount(submitTime, name);
        return Result.ok(areasVoList);
    }

    @GetMapping("/selectCityAreasList")
    @ApiOperation("按保护区数量查询(市级)")
    public Result selectCityAreasList(@ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                          @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name){
        if (StringUtils.isBlank(submitTime)) {
            submitTime = getCurrentYear();
        }
        List<CityAreasVo> cityAreasVoList = this.spatialAnalystService.selectCityAreasList(submitTime, name);
        return Result.ok(cityAreasVoList);
    }

    @GetMapping("/selectCountyAreasList")
    @ApiOperation("按保护区数量查询(县区级)")
    public Result selectCountyAreasList(@ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                      @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name){
        if (StringUtils.isBlank(submitTime)) {
            submitTime = getCurrentYear();
        }
        List<CountyAreasVo> countyAreasVoList = this.spatialAnalystService.selectCountyAreasList(submitTime, name);
        return Result.ok(countyAreasVoList);
    }

    private static String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }
}
