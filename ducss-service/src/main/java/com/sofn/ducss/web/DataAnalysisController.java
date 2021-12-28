package com.sofn.ducss.web;

import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.ducss.service.DataAnalysisService;
import com.sofn.ducss.sysapi.SysApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Api(tags = "数据分析接口", value = "数据分析页面的表现层")
@Slf4j
@RequestMapping("/dataAnalysis")
public class DataAnalysisController extends BaseController {
    @Autowired
    DataAnalysisService dataAnalysisService;

    @ApiOperation(value = "查询分析数据列表", httpMethod = "POST")
    @RequestMapping(value = "/getDataList")
    public Result getDataList(@RequestBody HashMap<String, String> paramMap){
        // 区域查询四种格式,查询区域分页列表
        return dataAnalysisService.getDataList(paramMap);
    }
}
