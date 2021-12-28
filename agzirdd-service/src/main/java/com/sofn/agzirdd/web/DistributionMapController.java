package com.sofn.agzirdd.web;

import com.sofn.agzirdd.service.DistributionMapService;
import com.sofn.agzirdd.vo.DistributionMapVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chlf
 */
@Api(value = "外来入侵物种分布图接口", tags = "外来入侵-物种分布图接口")
@RestController
@RequestMapping("/distributionMap")
public class DistributionMapController extends BaseController {
    @Autowired
    private DistributionMapService distributionMapService;

    @SofnLog("【外来入侵物种分布图】")
    @ApiOperation(value="【外来入侵物种分布图】")
    @PostMapping("/getMapInfo")
    public Result<List<DistributionMapVo>> getMapInfo(){

        List<DistributionMapVo> list = distributionMapService.selectByOneYearDuring();
        return Result.ok(list);
    }

    @SofnLog("【外来入侵物种分布图(新)】")
    @ApiOperation(value="【(新)外来入侵物种分布图】")
    @PostMapping("/getMapInfoNew")
    public Result<MapViewData> getMapInfoNew(@RequestParam(value = "index") @ApiParam(name="index", value="指标,左边固定值中的指标值,无则传入EMPTY",
            allowableValues = "EMPTY", required = true) String index,
                                              @RequestParam(value = "adLevel") @ApiParam(name="adLevel", value="行政级别,传值ad_county：国家 ad_province:省级；ad_city:市级；",allowableValues = "ad_county,ad_province,ad_city", required = true) String adLevel,
                                              @RequestParam(value = "adCode") @ApiParam(name="adCode", value="行政区域代码或行政区域名称；adLevel为国家时传100000；adLevel为省市级时候传相应省市的6位行政编码", required = true) String adCode,
                                              @RequestParam(value = "year",required = false) @ApiParam(name="year", value="年度查询条件,比如：2020;不传默认当前年") String year){
        Map<String,String> conditions = new HashMap<>();
        conditions.put("year", StringUtils.isEmpty(year)? String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) : year);

        return Result.ok(distributionMapService.getMapViewData(index, adLevel, adCode, conditions));
    }

}
