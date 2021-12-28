package com.sofn.agpjyz.web;

import com.google.common.collect.Maps;
import com.sofn.agpjyz.service.IndexService;
import com.sofn.agpjyz.sysapi.JqfApi;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.vo.*;
import com.sofn.agpjyz.vo.ApiVO.*;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "首页地图模块接口")
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Resource
    private IndexService indexService;

    @Resource
    private JzbApi jzbApi;

    @Resource
    private JqfApi jqfApi;

    @ApiOperation(value = "《下拉列表》获取农业野生植物物种")
    @GetMapping("/listForSpecies")
    public Result<List<DropDownWithLatinVo>> listForSpecies() {
        return jzbApi.listForSpecies();
    }

    @ApiOperation(value = "根据年份、物种ID获取区域(调查)")
    @GetMapping("/listArea")
    public Result<List<String>> listArea(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "物种ID", required = true) @RequestParam() String specId,
            @ApiParam("当前区域代码(默认为国家)") @RequestParam(required = false) String areaCode,
            @ApiParam("查询区域，多区域用英文','隔开") @RequestParam(required = false) String queryCode) {
        return Result.ok(indexService.listArea(year, specId, areaCode, queryCode));
    }

    @ApiOperation(value = "根据年份、区域显示调查物种数量和具体物种名称(调查)")
    @GetMapping("/getAmountAndNames")
    public Result<Map<String, Object>> getAmountAndNames(
            @ApiParam("省,多省用英文','隔开") @RequestParam(required = false) String province,
            @ApiParam("市,多市用英文','隔开") @RequestParam(required = false) String city,
            @ApiParam("县,多县用英文','隔开") @RequestParam(required = false) String county,
            @ApiParam(value = "年份", required = true) @RequestParam() String year) {
        return Result.ok(indexService.getAmountAndNames(this.getQueryMap(province, city, county, year, null, null)));
    }

    @ApiOperation(value = "根据调查id查区域具体物种信息(调查)")
    @GetMapping("/getInfo")
    public Result<SurveyInfoVo> getInfo(
            @ApiParam("当前查询区域代码(默认为国家)") @RequestParam(required = false) String areaCode,
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "调查Id", required = true) @RequestParam() String id) {
        return Result.ok(indexService.getInfo(areaCode, year, id));
    }

    @ApiOperation(value = "分布面积趋势(调查)")
    @GetMapping("/listDistributionTrend")
    public Result<List<TrendVo>> listDistributionTrend(
            @ApiParam("省,多省用英文','隔开") @RequestParam(required = false) String province,
            @ApiParam("市,多市用英文','隔开") @RequestParam(required = false) String city,
            @ApiParam("县,多县用英文','隔开") @RequestParam(required = false) String county,
            @ApiParam(value = "调查Id", required = true) @RequestParam() String id,
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "几年", required = true) @RequestParam() Integer number) {
        return Result.ok(indexService.listTrend(this.getQueryMap(province, city, county, year, id, number)));
    }

    @ApiOperation(value = "种群数量趋势(调查)")
    @GetMapping("/listAmountTrend")
    public Result<List<TrendVo>> listAmountTrend(
            @ApiParam("省,多省用英文','隔开") @RequestParam(required = false) String province,
            @ApiParam("市,多市用英文','隔开") @RequestParam(required = false) String city,
            @ApiParam("县,多县用英文','隔开") @RequestParam(required = false) String county,
            @ApiParam(value = "调查Id", required = true) @RequestParam() String id,
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "几年", required = true) @RequestParam() Integer number) {
        return Result.ok(indexService.listTrend(this.getQueryMap(province, city, county, year, id, number)));
    }

//    @ApiOperation(value = "查询某区域具备监测数据的监测点(监测)")
//    @GetMapping("/listMonitorCountApi")
//    Result<List<MonitorCount>> listMonitorCountApi(
//            @RequestParam(required = false) @ApiParam("省") String province,
//            @RequestParam(required = false) @ApiParam("市") String city,
//            @RequestParam(required = false) @ApiParam("县") String county,
//            @RequestParam(required = false) @ApiParam("年份") String year) {
//        return jqfApi.listMonitorCountApi(province, city, county, year);
//    }
//
//    @ApiOperation(value = "查询某监测点某年度的物种下拉列表(监测)")
//    @GetMapping("/listSpeciesSelectApi")
//    Result<List<SelectVO>> listSpeciesSelectApi(
//            @RequestParam(required = true) @ApiParam("监测点ID") String monitorId,
//            @RequestParam(required = true) @ApiParam("当前年份") String year) {
//        return jqfApi.listSpeciesSelectApi(monitorId, year);
//    }
//
//    @ApiOperation(value = "查询监测点某年度某几年分布、受损面积趋势(监测)")
//    @GetMapping("/listMonitorApi")
//    Result<List<MonitorApi>> listMonitorApi(
//            @RequestParam(required = true) @ApiParam("监测点ID") String monitorId,
//            @RequestParam(required = true) @ApiParam("当前年份") String year,
//            @RequestParam(required = true) @ApiParam("趋势的年数") int yearNumber) {
//        return jqfApi.listMonitorApi(monitorId, year, yearNumber);
//    }
//
//    @ApiOperation(value = "查询某监测点某物种近几年趋势(监测)")
//    @GetMapping("/listSpeciesApi")
//    Result<List<SpeciesApiVO>> listSpeciesApi(
//            @RequestParam(required = true) @ApiParam("监测点ID") String monitorId,
//            @RequestParam(required = true) @ApiParam("物种ID") String speciesId,
//            @RequestParam(required = true) @ApiParam("当前年份") String year,
//            @RequestParam(required = true) @ApiParam("趋势的年数") int yearNumber) {
//        return jqfApi.listSpeciesApi(monitorId, speciesId, year, yearNumber);
//    }
//
//    @ApiOperation(value = "查询地图上某区域的监测点列表(监测)")
//    @GetMapping("/listMonitorStationCountApi")
//    Result<List<MonitorStationApi>> listMonitorStationCountApi(
//            @RequestParam(required = false) @ApiParam("部级") String ministry,
//            @RequestParam(required = false) @ApiParam("省") String province,
//            @RequestParam(required = false) @ApiParam("市") String city,
//            @RequestParam(required = false) @ApiParam("年份") String year) {
//        return jqfApi.listMonitorStationCountApi(ministry, province, city, year);
//    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(
            String province, String city, String county, String year, String id, Integer number) {
        Map map = Maps.newHashMapWithExpectedSize(6);
        if (StringUtils.hasText(county)) {
            map.put("countys", IdUtil.getIdsByStr(county));
            city = "";
            province = "";
        }
        if (StringUtils.hasText(city)) {
            map.put("citys", IdUtil.getIdsByStr(city));
            province = "";
        }
        if (StringUtils.hasText(province)) {
            map.put("provinces", IdUtil.getIdsByStr(province));
        }
        map.put("year", year);
        map.put("id", id);
        map.put("number", number);
        return map;
    }

}
