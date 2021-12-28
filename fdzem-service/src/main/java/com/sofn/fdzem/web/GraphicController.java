package com.sofn.fdzem.web;

import com.sofn.common.model.Result;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.MonitoringStationTaskService;
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
@Api(tags = "分布图示管理接口" )
@RestController
@RequestMapping("/graphic")
public class GraphicController {

    @Autowired
    private MonitoringStationTaskService monitoringStationTaskService;

    @GetMapping("/listPage")
    @RequiresPermissions("fdzem:graphic:list")
    @ApiOperation(value = "获取所有任务图示数据")
    public Result<List<MonitoringStationTask>>  getSurveyTasksPage(@RequestParam(required = false) @ApiParam(value = "省") String provice,
                                                                   @RequestParam(required = false) @ApiParam(value = "市") String city,
                                                                   @RequestParam(required = false) @ApiParam(value = "县") String county){

        List<MonitoringStationTask> listPage = monitoringStationTaskService.selectGraphic(provice,city,county);
        return new Result<>(200, "获取成功", listPage);

    }
}
