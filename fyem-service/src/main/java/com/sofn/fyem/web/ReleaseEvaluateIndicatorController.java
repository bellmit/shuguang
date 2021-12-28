package com.sofn.fyem.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.model.ReleaseEvaluateIndicator;
import com.sofn.fyem.service.ReleaseEvaluateIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 放流评价指标管理-接口
 * @author Administrator
 */
@Api(value = "放流评价指标管理-接口", tags = "放流评价指标管理-接口")
@RestController
@RequestMapping("/releaseEvaluateIndicator")
public class ReleaseEvaluateIndicatorController extends BaseController {

    @Autowired
    private ReleaseEvaluateIndicatorService releaseEvaluateIndicatorService;

    @SofnLog("获取放流评价指标信息")
    @ApiOperation(value="获取放流评价指标信息")
    @GetMapping("/releaseEvaluateIndicatorList")
    public Result<List<ReleaseEvaluateIndicator>> releaseEvaluateIndicatorList(
            @ApiParam(name = "indicatorName",value = "指标名称(非必传)")@RequestParam(value = "indicatorName", required = false)String indicatorName,
            @ApiParam(name = "indicatorType",value = "指标类型(非必传)")@RequestParam(value = "indicatorType",required = false)String indicatorType,
            @ApiParam(name = "beginDate",value = "开始时间(非必传)")@RequestParam(value = "beginDate",required = false)String beginDate,
            @ApiParam(name = "endDate",value = "结束时间(非必传)")@RequestParam(value = "endDate",required = false)String endDate,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("indicatorName",indicatorName);
        params.put("indicatorType",indicatorType);
        params.put("beginDate",beginDate);
        params.put("endDate",endDate);
        List<ReleaseEvaluateIndicator> releaseEvaluateIndicatorList = releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorListByQuery(params);
        return Result.ok(releaseEvaluateIndicatorList);
    }

    @SofnLog("获取启用的一级指标信息")
    @ApiOperation(value="获取启用的一级指标信息")
    @GetMapping("/getEvaluateIndicatorList")
    public Result<List<ReleaseEvaluateIndicator>> getEvaluateIndicatorList(
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("indicatorType","0");
        params.put("status","0");
        List<ReleaseEvaluateIndicator> releaseEvaluateIndicatorList = releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorListByQuery(params);
        return Result.ok(releaseEvaluateIndicatorList);
    }

    @SofnLog("获取放流评价指标信息详情")
    @ApiOperation(value="获取放流评价指标信息详情")
    @GetMapping("/getReleaseEvaluateIndicator")
    public Result<ReleaseEvaluateIndicator> getReleaseEvaluateIndicator(
            @ApiParam(name = "id",value = "id(必传)",required = true)@RequestParam(value = "id")String id){

        ReleaseEvaluateIndicator releaseEvaluateIndicator = releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorById(id);
        return Result.ok(releaseEvaluateIndicator);
    }

    @SofnLog("改变放流评价指标信息状态")
    @ApiOperation(value="改变放流评价指标信息状态")
    @GetMapping("/updateStatus")
    public Result<String> updateStatus(
            @ApiParam(name = "id",value = "id(必传)",required = true)@RequestParam(value = "id")String id,
            @ApiParam(name = "status",value = "状态(必传)",required = true)@RequestParam(value = "status")String status){

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",id);
        params.put("status",status);
        if("0".equals(status)){
            releaseEvaluateIndicatorService.updateStatus(params);
            return Result.ok("当前放流评价指标已启用!");
        }else if("1".equals(status)){
            ReleaseEvaluateIndicator releaseEvaluateIndicator = releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorById(id);
            String indicatorType = releaseEvaluateIndicator.getIndicatorType();
            if("0".equals(indicatorType)){
                Map<String, Object> oneParams = Maps.newHashMap();
                oneParams.put("status",status);
                oneParams.put("parentId",releaseEvaluateIndicator.getId());
                releaseEvaluateIndicatorService.updateStatus(oneParams);
            }
            releaseEvaluateIndicatorService.updateStatus(params);
            return Result.ok("当前放流评价指标已停用!");
        }else{
            return Result.ok("请输入正确的状态!");
        }
    }

    @SofnLog("新增放流评价指标信息")
    @ApiOperation(value="新增放流评价指标信息")
    @PostMapping("/addReleaseEvaluateIndicator")
    public Result<String> addReleaseEvaluateIndicator(
            @Validated @RequestBody @ApiParam(name = "放流评价指标信息对象", value = "传入json格式", required = true) ReleaseEvaluateIndicator releaseEvaluateIndicator,
            HttpServletRequest request){

        releaseEvaluateIndicatorService.addReleaseEvaluateIndicator(releaseEvaluateIndicator);
        return Result.ok("新增放流评价指标信息成功");
    }

    @SofnLog("编辑放流评价指标信息")
    @ApiOperation(value="编辑放流评价指标信息")
    @PostMapping("/updateReleaseEvaluateIndicator")
    public Result<String> updateReleaseEvaluateIndicator(
            @Validated @RequestBody @ApiParam(name = "放流评价指标信息对象", value = "传入json格式", required = true)ReleaseEvaluateIndicator releaseEvaluateIndicator,
            HttpServletRequest request){

        releaseEvaluateIndicatorService.updateReleaseEvaluateIndicator(releaseEvaluateIndicator);
        return Result.ok("编辑放流评价指标信息成功!");
    }

    @SofnLog("删除放流评价指标信息")
    @ApiOperation(value="删除放流评价指标信息")
    @GetMapping("/removeReleaseEvaluateIndicator")
    public Result<String> removeReleaseEvaluateIndicator(
            @ApiParam(name = "id",value = "id(必传)",required = true)@RequestParam(value = "id")String id){

        releaseEvaluateIndicatorService.removeReleaseEvaluateIndicator(id);
        return Result.ok("当前增值流放信息已删除!");

    }

}
