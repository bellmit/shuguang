package com.sofn.fdzem.web;

import com.github.pagehelper.PageInfo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.MonitoringStationTaskService;
import com.sofn.fdzem.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "监测站任务信息审核管理接口" )
@RestController
@RequestMapping("/monitoringStationTaskCheck")
public class MonitoringStationTaskCheckController {

    @Autowired
    private MonitoringStationTaskService monitoringStationTaskService;

    @GetMapping("/listPage")
    @RequiresPermissions("fdzem:monitoringStationTaskCheck:list")
    @ApiOperation(value = "获取所有任务分页及条件查询")
    public Result<List<MonitoringStationTask>>  getSurveyTasksPage(@RequestParam(required = false,defaultValue = "1") @ApiParam(value = "当前页数") Integer pageNo,
                                                                   @RequestParam(required = false,defaultValue = "10") @ApiParam(value = "每页显示条数") Integer pageSize,
                                                                   @RequestParam(required = false) @ApiParam(value = "上报年度") String year,
                                                                   @RequestParam(required = false) @ApiParam(value = "审核状态") Integer status){
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageInfo<MonitoringStationTask> listPage = monitoringStationTaskService.selectListByQueryCheck(pageNo,pageSize,year,status);
        return Result.ok(PageUtils.getPageUtils(listPage));

    }
    /*@PutMapping("/report")
    @RequiresPermissions("fdzem:monitoringStationTaskCheck:report")
    @ApiOperation(value = "任务上报")
    public Result<MonitoringStationTask> report(@RequestParam(required = false) String id) {
        try {
            monitoringStationTaskService.reportAll(id);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }*/

    @PutMapping("/audit")
    @RequiresPermissions("fdzem:monitoringStationTaskCheck:audit")
    @ApiOperation(value = "任务审核")
    public Result<MonitoringStationTask> audit(@RequestParam(required = false) String id) {
        try {
            monitoringStationTaskService.audit(id);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/reject")
    @RequiresPermissions("fdzem:monitoringStationTaskCheck:reject")
    @ApiOperation(value = "任务驳回")
    public Result<MonitoringStationTask> reject(@RequestParam(required = false) String id) {
        try {
            monitoringStationTaskService.reject(id);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("getStatus")
    @SofnLog("获取审核状态下拉框")
    @ApiOperation(value = "获取审核状态下拉框", notes = "获取审核状态下拉框")
    public Result<List<StatusVo>> getStatus() {
        List<StatusVo> getStatus = monitoringStationTaskService.getStatus();
        Result<List<StatusVo>> listResult = new Result<>(getStatus);
        listResult.setCode(200);
        return listResult;
    }
}
