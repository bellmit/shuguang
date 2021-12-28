package com.sofn.ducss.web;


import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.CollectFlowLogService;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value="查询日志页面数据")
    @GetMapping("/getCollectFlowLogList")
    public Result<PageUtils<CollectFlowLog>> getCollectFlowLogList(@RequestParam(value="pageNo") @ApiParam(value="起始行数") Integer pageNo
            , @RequestParam(value = "pageSize") @ApiParam(value="每页显示数") Integer pageSize
            , @RequestParam(value = "year") @ApiParam(value="年度") String year
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if(!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);

        PageUtils<CollectFlowLog> countryTasks = collectFlowLogService.getCollectFlowLogByPage(pageNo,pageSize,year,countyId);

        return Result.ok(countryTasks);
    }

    @SofnLog("查询退回信息")
    @ApiOperation(value="查询退回信息")
    @GetMapping("/findCollectFlowLog")
    public Result<CollectFlowLog> findCollectFlowLog(@RequestParam(value = "areaId",required = true) @ApiParam(value="区划ID",required = true) String areaId
            , @RequestParam(value = "year",required = true) @ApiParam(value="年度",required = true) String year
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        List<CollectFlowLog> collectFlowLogList = collectFlowLogService.findCollectFlowLog(year,areaId);
        if (!collectFlowLogList.isEmpty()) {
            return Result.ok(collectFlowLogList.get(0));
        }
        return Result.ok();
    }

}




