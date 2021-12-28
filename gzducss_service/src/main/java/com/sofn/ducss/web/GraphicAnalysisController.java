package com.sofn.ducss.web;

import com.sofn.ducss.map.MapViewData;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.basemodel.Result;;
import com.sofn.ducss.service.GraphicAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GraphicAnalysisController
 * @Description 图形分析
 * @Author Chlf
 * @Date2020/7/28 15:34
 * Version1.0
 **/
@Slf4j
@Api(value = "图形分析接口", tags = "图形分析接口")
@RestController
@RequestMapping("/graphicAnalysis")
public class GraphicAnalysisController{
    @Autowired
    private GraphicAnalysisService graphicAnalysisService;

    @SofnLog("图形分析")
    @ApiOperation(value="图形分析接口")
    @GetMapping("/getMapViewData")
    public Result<MapViewData> getMapViewData(@RequestParam(value = "index") @ApiParam(name="index", value="指标,左边固定值中的指标值,比如说产生量传OUTPUT",
            allowableValues = "OUTPUT,UTILIZATION,DIRECTRETURN,CALLIN,FIVEMATERIALS,OEE,COMPREHENSIVEUTILIZATION,CUCI,IUCI", required = true) String index,
                                              @RequestParam(value = "adLevel") @ApiParam(name="adLevel", value="行政级别,传值ad_county：国家 ad_province:省级；ad_city:市级；",allowableValues = "ad_county,ad_province,ad_city", required = true) String adLevel,
                                              @RequestParam(value = "adCode") @ApiParam(name="adCode", value="行政区域代码或行政区域名称；adLevel为国家时传100000；adLevel为省市级时候传相应省市的6位行政编码", required = true) String adCode,
                                              @RequestParam(value = "year",required = false) @ApiParam(name="year", value="年度查询条件,比如：2020;不传默认当前年") String year){
        Map<String,String> conditions = new HashMap<>();
        conditions.put("year", StringUtils.isEmpty(year)? String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) : year);

        return Result.ok(graphicAnalysisService.getMapViewData(index, adLevel, adCode, conditions));
    }

}
