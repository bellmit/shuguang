package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.CountyDataAnalysisService;
import com.sofn.ducss.vo.DateShow.*;
import com.sofn.ducss.vo.StrawUsageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;


@Api(tags = "汇总图例")
@RestController
@RequestMapping("/dataShow/V2")
public class DataShowV2Controller {

    @Autowired
    private CountyDataAnalysisService countyDataAnalysisService;

    @ApiOperation(value = "农作物秸秆产生量与利用量柱状图")
    @GetMapping(value = "/show/1")
    public Result<List<LinkedHashMap<String, Object>>> showColumnLine(@RequestParam(value = "year") @ApiParam(value = "所选年份", required = true) String year,
                                                                      @RequestParam(value = "areaId") @ApiParam(value = "区域id", required = true) String areaId) {
        /*AreaRegionCode regionCode = SysRegionUtil.getRegionCodeByLastCode(areaId);
        if (RegionLevel.COUNTY.getCode().equals(regionCode.getRegionLevel())) {
            // 如果是乡镇级
        } else if (RegionLevel.CITY.getCode().equals(regionCode.getRegionLevel())) {
            // 如果是县级
        }*/
        List<StrawUsageVo> data = countyDataAnalysisService.findDataByAreaIdsAndYears(Lists.newArrayList(areaId), Lists.newArrayList(year));
        List<LinkedHashMap<String, Object>> result = Lists.newArrayList();
        for (StrawUsageVo usageVo : data) {
            LinkedHashMap<String, Object> strawMap = Maps.newLinkedHashMap();
            strawMap.put("name", usageVo.getStrawName());
            strawMap.put("产生量", usageVo.getTheoryResource());
            strawMap.put("可收集量", usageVo.getCollectResource());
            strawMap.put("秸秆利用量", usageVo.getStrawUtilization());
            result.add(strawMap);
        }
        return Result.ok(result);
    }

    @ApiOperation(value = "秸秆五料化占比饼状图")
    @GetMapping(value = "/show/2")
    public Result<List<RoundVo>> showRound1(@RequestParam(value = "year") @ApiParam(value = "所选年份", required = true) String year,
                                            @RequestParam(value = "areaId") @ApiParam(value = "区域id", required = true) String areaId) {
        List<StrawUsageVo> data = countyDataAnalysisService.findDataByAreaIdsAndYears(Lists.newArrayList(areaId), Lists.newArrayList(year));
        BigDecimal fertilize = BigDecimal.ZERO;
        BigDecimal feed = BigDecimal.ZERO;
        BigDecimal fuelled = BigDecimal.ZERO;
        BigDecimal baseMat = BigDecimal.ZERO;
        BigDecimal materialization = BigDecimal.ZERO;
        for (StrawUsageVo usageVo : data) {
            fertilize = fertilize.add(usageVo.getFertilize());
            feed = feed.add(usageVo.getFeed());
            fuelled = fuelled.add(usageVo.getFuelled());
            baseMat = baseMat.add(usageVo.getBaseMat());
            materialization = materialization.add(usageVo.getMaterialization());
        }
        List<RoundVo> result = Lists.newArrayList();
        RoundVo vo1 = new RoundVo();
        vo1.setName("肥料化");
        vo1.setValue(fertilize);
        result.add(vo1);

        RoundVo vo2 = new RoundVo();
        vo2.setName("饲料化");
        vo2.setValue(feed);
        result.add(vo2);

        RoundVo vo3 = new RoundVo();
        vo3.setName("燃料化");
        vo3.setValue(fuelled);
        result.add(vo3);

        RoundVo vo4 = new RoundVo();
        vo4.setName("基料化");
        vo4.setValue(baseMat);
        result.add(vo4);

        RoundVo vo5 = new RoundVo();
        vo5.setName("原料化");
        vo5.setValue(materialization);
        result.add(vo5);
        return Result.ok(result);
    }

    @ApiOperation(value = "秸秆利用量占比饼状图")
    @GetMapping(value = "/show/3")
    public Result<List<RoundVo>> showRound2(@RequestParam(value = "year") @ApiParam(value = "所选年份", required = true) String year,
                                            @RequestParam(value = "areaId") @ApiParam(value = "区域id", required = true) String areaId) {
        List<StrawUsageVo> data = countyDataAnalysisService.findDataByAreaIdsAndYears(Lists.newArrayList(areaId), Lists.newArrayList(year));
        List<RoundVo> result = Lists.newArrayList();
        for (StrawUsageVo usageVo : data) {
            RoundVo vo = new RoundVo();
            vo.setName(usageVo.getStrawName());
            vo.setValue(usageVo.getStrawUtilization());
            result.add(vo);
        }
        return Result.ok(result);
    }

}
