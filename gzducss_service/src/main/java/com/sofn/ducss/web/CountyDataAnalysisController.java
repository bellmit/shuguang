/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-15 15:12
 */
package com.sofn.ducss.web;

/**
 * 数据分析县级控制器
 *
 * @author jiangtao
 * @version 1.0
 **/

import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.CountyDataAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据分析县级控制器
 *
 * @author jiangtao
 * @version 1.0
 **/
@Api(tags = "数据分析县级")
@RestController
@RequestMapping("/countyDataAnalysis")
public class CountyDataAnalysisController {

    @Autowired
    private CountyDataAnalysisService countyDataAnalysisService;

    @SofnLog("数据分析县级")
    @ApiOperation(value = "数据分析县级")
    @GetMapping(value = "/getDataAnalysis")
    public Result<String> getDataAnalysis(@RequestParam("year") String year,@RequestParam("areaCode") String areaCode) {
        countyDataAnalysisService.insertCountyDataAnalysis(year, areaCode);
        return Result.ok("1111");
    }
    @SofnLog("数据分析市级")
    @ApiOperation(value = "数据分析市级")
    @GetMapping(value = "/getCityAndProvinceDataAnalysis")
    public Result<String> getCityAndProvinceDataAnalysis(@RequestParam("year") String year,@RequestParam("areaCode") String areaCode) {
        countyDataAnalysisService.insertCityDataAnalysis(year, areaCode);
        return Result.ok("1111");
    }

    @SofnLog("数据分析省级")
    @ApiOperation(value = "数据分析省级")
    @GetMapping(value = "/getProvinceDataAndSixRegionAnalysis")
    public Result<String> getProvinceDataAndSixRegionAnalysis(@RequestParam("year") String year,@RequestParam("areaCode") String areaCode) {
        countyDataAnalysisService.insertProvinceDataAndSixRegionAnalysis(year, areaCode);
        return Result.ok("1111");
    }
}
