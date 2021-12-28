package com.sofn.sys.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysLog;
import com.sofn.sys.service.LogQueryService;
import com.sofn.sys.vo.SysBaseDataAndTypeVo;
import com.sofn.sys.vo.SysStatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/9/16 17:52
 */
@RestController
@RequestMapping("/log")
@Api(value = "日志相关接口")
public class LogQueryController  {

    @Autowired
    private LogQueryService logQueryService;

    @GetMapping("/getLoginInfo")
    @ApiOperation(value = "获取日志信息，转对象的操作由前台解决", notes = "sys:log:getLoginInfo")
    @SofnLog(value = "查询日志", type = "查询")
    public Result<Map<String,Object>> getLoginInfo(@RequestParam(required = false) @ApiParam(value = "查询日期") String date,
                                       @RequestParam(required = false) @ApiParam(value = "关键字") String keyword,
                                       @RequestParam @ApiParam(value = "系统代码",required = true) String appId,
                                       @RequestParam(required = false) @ApiParam(value = "开始时间") String startDate,
                                       @RequestParam(required = false) @ApiParam(value = "结束时间") String endDate,
                                       @RequestParam(required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                       @RequestParam(required = false) @ApiParam(value = "显示多少条") Integer pageSize){
        String message = logQueryService.getLogInfo(date, keyword,appId,startDate, endDate, pageNo, pageSize);
        Map<String,Object> params = Maps.newHashMap();
        params.put("data",message);
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);

        return Result.ok(params);
    }


    @GetMapping("/getSysLogInfo")
    @ApiOperation(value = "获取系统监控信息，转对象的操作由前台解决", notes = "sys:log:getSysLogInfo")
    @SofnLog(value = "查询系统日志", type = "查询")
    public Result<List<Map<String,Object>>> getSysLogInfo(){
        List<Map<String, Object>> sysLogInfo = logQueryService.getSysLogInfo();
        return Result.ok(sysLogInfo);
    }

    @GetMapping("/getSysInfo")
    @ApiOperation(value = "查询系统内存CP磁盘状况，转对象的操作由前台解决", notes = "sys:log:getSysInfo")
    @SofnLog(value = "查询系统内存CP磁盘状况", type = "查询")
    public Result<PageUtils<SysStatusVo>> getSysInfo(@RequestParam(required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                                @RequestParam(required = false) @ApiParam(value = "显示多少条") Integer pageSize){

        PageUtils<SysStatusVo> sysLogInfo = logQueryService.getSysInfo(pageNo,pageSize);
        return Result.ok(sysLogInfo);
    }


    @GetMapping("/getSysLogPage")
    @ApiOperation(value = "查询系统log", notes = "sys:log:getSysInfo")
    @SofnLog(value = "查询系统log", type = "查询系统log")
    public Result<PageUtils<SysLog>> getSysLogPage(@RequestParam(required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                                   @RequestParam(required = false) @ApiParam(value = "显示多少条") Integer pageSize,
                                                   @RequestParam(required = false) @ApiParam(value = "关键字") String keyword,
                                                   @RequestParam(required = false) @ApiParam(value = "开始时间") String startDate,
                                                   @RequestParam(required = false) @ApiParam(value = "结束时间") String endDate){

        PageUtils<SysLog> sysLogInfo = logQueryService.getSysLogPage(pageNo,pageSize,keyword,startDate,endDate);
        return Result.ok(sysLogInfo);
    }

}
