package com.sofn.agpjyz.sysapi;

import com.sofn.agpjyz.vo.ApiVO.*;
import com.sofn.common.config.FeignConfiguration;
import io.swagger.annotations.ApiOperation;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "agpjqf-service", configuration = FeignConfiguration.class)
public interface JqfApi {
    @ApiOperation(value = "外部API-查询某区域具备监测数据的监测点")
    @GetMapping("/monitor/listMonitorCountApi")
    Result<List<MonitorCount>> listMonitorCountApi(
            @RequestParam(required = false) @ApiParam("省") String province,
            @RequestParam(required = false) @ApiParam("市") String city,
            @RequestParam(required = false) @ApiParam("县") String county,
            @RequestParam(required = false) @ApiParam("年份") String year);

    @ApiOperation(value = "外部API-查询某监测点某年度的物种下拉列表")
    @GetMapping("/monitor/listSpeciesSelectApi")
    Result<List<SelectVO>> listSpeciesSelectApi(
            @RequestParam(required = true) @ApiParam("监测点ID") String monitorId,
            @RequestParam(required = true) @ApiParam("当前年份") String year);

    @ApiOperation(value = "外部API-查询监测点某年度某几年分布、受损面积趋势")
    @GetMapping("/monitor/listMonitorApi")
    Result<List<MonitorApi>> listMonitorApi(
            @RequestParam(required = true) @ApiParam("监测点ID") String monitorId,
            @RequestParam(required = true) @ApiParam("当前年份") String year,
            @RequestParam(required = true) @ApiParam("趋势的年数") int yearNumber);

    @ApiOperation(value = "外部API-查询某监测点某物种近几年趋势")
    @GetMapping("/monitor/listSpeciesApi")
    Result<List<SpeciesApiVO>> listSpeciesApi(
            @RequestParam(required = true) @ApiParam("监测点ID") String monitorId,
            @RequestParam(required = true) @ApiParam("物种ID") String speciesId,
            @RequestParam(required = true) @ApiParam("当前年份") String year,
            @RequestParam(required = true) @ApiParam("趋势的年数") int yearNumber);

//    @ApiOperation(value = "外部API-查询某监测点某物种近几年趋势")
//    @GetMapping("/monitor/listMonitorStationCountApi")
//    Result<List<MonitorStationApi>> listMonitorStationCountApi(
//            @RequestParam(required = false) @ApiParam("部级") String ministry,
//            @RequestParam(required = false) @ApiParam("省") String province,
//            @RequestParam(required = false) @ApiParam("市") String city,
//            @RequestParam(required = false) @ApiParam("年份") String year);
}
