package com.sofn.ducss.web;

import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.model.DucssOperateLog;
import com.sofn.ducss.service.DucssOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ducssOperateLog")
@Api(tags = "秸秆系统操作日志")
public class DucssOperateLogController {

    @Autowired
    private DucssOperateLogService ducssOperateLogService;

    @GetMapping("/getLogInfo")
    @ApiOperation(value = "查询日志信息", notes = "权限点(ducss:ducssOperateLog:getLogInfo)")
    @ResponseBody
    public Result<PageUtils<DucssOperateLog>> getLogInfo(@RequestParam(value = "startDate", required = false) @ApiParam(value = "开始时间") String startDate,
                                                         @RequestParam(value = "endDate", required = false) @ApiParam(value = "结束时间") String endDate,
                                                         @RequestParam(value = "operateType", required = false) @ApiParam(value = "操作类型，查看返回类上的操作类型") String operateType,
                                                         @RequestParam(value = "operateDetail", required = false) @ApiParam(value = "操作详情") String operateDetail,
                                                         @RequestParam(value = "areaId") @ApiParam(value = "区划ID。当前登录用户的区划ID", required = true) String areaId,
                                                         @RequestParam(value = "pageNo") @ApiParam(value = "从哪条记录开始", required = true) Integer pageNo,
                                                         @RequestParam(value = "pageSize") @ApiParam(value = "每页显示多少条", required = true) Integer pageSize) {
        PageUtils<DucssOperateLog> logList = ducssOperateLogService.getLogList(startDate, endDate, operateType, operateDetail, areaId, pageNo, pageSize);
        return Result.ok(logList);
    }

}
