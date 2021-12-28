package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.model.CountryTask;
import com.sofn.ducss.service.CollectFlowLogService;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.vo.AreaRegionCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Api(value = "县级进度管理", tags = "县级进度管理")
@RestController
@RequestMapping("/collectFlowLog")
public class CollectFlowLogController {

    @Autowired
    private CollectFlowLogService collectFlowLogService;

    @SofnLog("查询日志页面数据")
    @ApiOperation(value = "查询日志页面数据")
    @GetMapping("/getCollectFlowLogList")
    public Result<PageUtils<CollectFlowLog>> getCollectFlowLogList(@RequestParam(value = "pageNo") @ApiParam(value = "起始行数") Integer pageNo
            , @RequestParam(value = "pageSize") @ApiParam(value = "每页显示数") Integer pageSize
            , @RequestParam(value = "year") @ApiParam(value = "年度") String year
            , HttpServletRequest request) {
        // 根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        // 现接口调整： 任何级别的账号都可以查询，默认查询当前级别和当前下级的上报进度
        List<String> areaIds = Lists.newArrayList();
        String lastCode = SysRegionUtil.getRegLastCodeStr();
        List<String> childrenIds = SysRegionUtil.getChildrenRegionIdByYearList(lastCode, year);
        if (CollectionUtils.isNotEmpty(childrenIds)) {
            areaIds.addAll(childrenIds);
        }
        areaIds.add(lastCode);
        PageUtils<CollectFlowLog> logs = collectFlowLogService.getCollectFlowLogByPage(pageNo, pageSize, year, areaIds);
        return Result.ok(logs);
    }

    @SofnLog("查询退回信息")
    @ApiOperation(value = "查询退回信息")
    @GetMapping("/findCollectFlowLog")
    public Result<CollectFlowLog> findCollectFlowLog(@RequestParam(value = "areaId", required = true) @ApiParam(value = "区划ID", required = true) String areaId
            , @RequestParam(value = "year", required = true) @ApiParam(value = "年度", required = true) String year
            , HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        CollectFlowLog lastLog = collectFlowLogService.lastCollectFlowLog(year, areaId);
        return Result.ok(lastLog);
    }

}




