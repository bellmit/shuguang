package com.sofn.fdzem.web;

import com.github.pagehelper.PageInfo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.MonitoringStationTaskService;
import com.sofn.fdzem.vo.TrendAnalysisVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "趋势分析管理接口" )
@RestController
@RequestMapping("/trendAnalysis")
public class TrendAnalysisController {

    @Autowired
    private MonitoringStationTaskService monitoringStationTaskService;

    @GetMapping("/listPage")
    @RequiresPermissions("fdzem:trendAnalysis:list")
    @ApiOperation(value = "获取所有任务分页及条件查询")
    public Result<List<TrendAnalysisVo>>  getSurveyTasksPage(@RequestParam(required = false) @ApiParam(value = "上报年度") String year,
                                                             @RequestParam(required = false) @ApiParam(value = "监测站名称") String name,
                                                             @RequestParam(defaultValue = "1") @ApiParam(value = "数据类型:1-水质监测指标2-沉积物监测指标3-浮游生物监测指标4-生物残留监测指标(传数字)") String type){

        PageInfo<TrendAnalysisVo> listPage = monitoringStationTaskService.selectListByQueryTrendAnalysis(1,5,year,name,type);
        return Result.ok(PageUtils.getPageUtils(listPage));

    }
}
