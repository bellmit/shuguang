package com.sofn.fdzem.web;

import com.github.pagehelper.PageInfo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.BatchManagement;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.BatchManagementService;
import com.sofn.fdzem.service.MonitoringStationTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "监测站任务信息填报管理接口" )
@RestController
@RequestMapping("/monitoringStationTask")
public class MonitoringStationTaskController {

    @Autowired
    private MonitoringStationTaskService monitoringStationTaskService;

    @GetMapping("/listPage")
    @RequiresPermissions("fdzem:monitoringStationTask:list")
    @ApiOperation(value = "获取所有任务分页及条件查询")
    public Result<List<MonitoringStationTask>>  getSurveyTasksPage(@RequestParam(required = false,defaultValue = "1") @ApiParam(value = "当前页数") Integer pageNo,
                                                                   @RequestParam(required = false,defaultValue = "10") @ApiParam(value = "每页显示条数") Integer pageSize,
                                                                   @RequestParam(required = false) @ApiParam(value = "上报年度") String year,
                                                                   @RequestParam(required = false) @ApiParam(value = "审核状态") Integer status){
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageInfo<MonitoringStationTask> listPage = monitoringStationTaskService.selectListByQuery(pageNo,pageSize,year,status);
        return Result.ok(PageUtils.getPageUtils(listPage));

    }

    @PostMapping("/insert")
    @RequiresPermissions("fdzem:monitoringStationTask:insert")
    @ApiOperation(value = "添加对应任务")
    public Result<MonitoringStationTask> insertMonitoringStationTask(String year) {
        try {
            monitoringStationTaskService.insertMonitoringStationTask(year);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @RequiresPermissions("fdzem:monitoringStationTask:delete")
    @ApiOperation(value = "通过id删除对应任务")
    public Result<MonitoringStationTask> deleteById(String id) {
        try {
            monitoringStationTaskService.remove(id);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/update")
    @RequiresPermissions("fdzem:monitoringStationTask:update")
    @ApiOperation(value = "更新对应调查任务")
    public Result<MonitoringStationTask> updateSurveyTasksById(@RequestBody MonitoringStationTask monitoringStationTask) {
        try {
            monitoringStationTaskService.update(monitoringStationTask);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/report")
    @RequiresPermissions("fdzem:monitoringStationTask:report")
    @ApiOperation(value = "任务上报")
    public Result<MonitoringStationTask> report(@RequestParam(required = false) String id) {
        try {
            monitoringStationTaskService.report(id);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("getMonitorStation")
    @SofnLog("获取监测站名称下拉框")
    @ApiOperation(value = "获取监测站名称下拉框", notes = "获取监测站名称下拉框")
    public Result<List<String>> getMonitorStation() {
        List<String> list = monitoringStationTaskService.getMonitorStation();
        Result<List<String>> listResult = new Result<>(list);
        listResult.setCode(200);
        return listResult;
    }
}
