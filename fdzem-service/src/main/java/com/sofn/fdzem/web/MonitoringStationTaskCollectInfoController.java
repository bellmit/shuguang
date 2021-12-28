package com.sofn.fdzem.web;

import com.github.pagehelper.PageInfo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.BaseData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BaseDataUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.enums.StatusEnum;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags = "信息汇总管理接口")
@RestController
@RequestMapping("/monitoringStationTaskCollectInfo")
public class MonitoringStationTaskCollectInfoController {

    @Autowired
    private MonitoringStationTaskService monitoringStationTaskService;

    @GetMapping("/listPage")
    @RequiresPermissions("fdzem:monitoringStationTaskCollectInfo:list")
    @ApiOperation(value = "获取所有任务分页及条件查询")
    public Result<List<MonitoringStationTask>> getSurveyTasksPage(@RequestParam(required = false, defaultValue = "1") @ApiParam(value = "当前页数") Integer pageNo,
                                                                  @RequestParam(required = false, defaultValue = "10") @ApiParam(value = "每页显示条数") Integer pageSize,
                                                                  @RequestParam(required = false) @ApiParam(value = "上报年度") String year,
                                                                  @RequestParam(required = false) @ApiParam(value = "监测站名称") String name,
                                                                  @RequestParam(required = false) @ApiParam(value = "省") String provice,
                                                                  @RequestParam(required = false) @ApiParam(value = "市") String city,
                                                                  @RequestParam(required = false) @ApiParam(value = "县") String county,
                                                                  @RequestParam(required = false) @ApiParam(value = "所属海域") String seaArea) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageInfo<MonitoringStationTask> listPage = monitoringStationTaskService.selectListByQueryInfo(pageNo, pageSize, year, name, provice, city, county, seaArea);
        return Result.ok(PageUtils.getPageUtils(listPage));
    }

}
