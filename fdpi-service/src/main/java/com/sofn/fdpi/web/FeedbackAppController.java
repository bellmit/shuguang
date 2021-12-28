package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Feedback;
import com.sofn.fdpi.service.FeedbackService;
import com.sofn.fdpi.vo.BackAdvice;
import com.sofn.fdpi.vo.FeedbackInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/30 14:38
 */
@Slf4j
@Api(value = "APP_反馈信息相关接口", tags = "APP_反馈信息相关接口")
@RestController
@RequestMapping("/app/Feedback")
public class FeedbackAppController extends BaseController {
    @Autowired
    private FeedbackService feedbackService;

//    @RequiresPermissions(value = {"fdpi:feedbackByGZ:query","fdpi:feedbackByZF:query"},logical = Logical.OR)
    @RequiresPermissions("fdpi:feedbackByZF:query")
    @SofnLog("根据条件获取执法部门反馈信息列表（分页）")
    @ApiOperation(value = "根据条件获取执法部门反馈信息列表（分页）")
    @GetMapping(value = "/list")
    public Result<List<Feedback>> getFeedbackListByPage(
            @ApiParam(name = "code",value = "标识编号")@RequestParam(value = "code",required = false)String code,
            @ApiParam(name = "ffUnit",value = "违法单位")@RequestParam(value = "ffUnit",required = false)String ffUnit,
            @ApiParam(name = "speName",value = "物种名")@RequestParam(value = "speName",required = false)String speName,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
            @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){
        HashMap<String, Object> map = Maps.newHashMap();
        if (StringUtils.hasText(startTime)) {
            startTime = startTime + " 00:00:00";
        }
        if (StringUtils.hasText(endTime)) {
            endTime = endTime + " 23:59:59";
        }
        map.put("code",code);
        map.put("speName",speName);
        map.put("ffUnit",ffUnit);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        PageUtils<Feedback> listByPage = feedbackService.getFeedbackListByPage(map,pageNo , pageSize);
        return Result.ok(listByPage);
    }

//    @RequiresPermissions(value = {"fdpi:feedbackByGZ:query","fdpi:feedbackByZF:query"},logical = Logical.OR)
//    @RequiresPermissions("fdpi:feedbackByGZ:query")
    @SofnLog("根据条件获取公众反馈信息列表（分页）")
    @ApiOperation(value = "根据条件获取公众反馈信息列表（分页）")
    @GetMapping(value = "/listPublic")
    public Result<List<Feedback>> listPage(
            @ApiParam(name = "code",value = "标识编号")@RequestParam(value = "code",required = false)String code,
            @ApiParam(name = "ffUnit",value = "违法单位")@RequestParam(value = "ffUnit",required = false)String ffUnit,
            @ApiParam(name = "speName",value = "物种名")@RequestParam(value = "speName",required = false)String speName,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
            @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){
        HashMap<String, Object> map = Maps.newHashMap();
        if (StringUtils.hasText(startTime)) {
            startTime = startTime + " 00:00:00";
        }
        if (StringUtils.hasText(endTime)) {
            endTime = endTime + " 23:59:59";
        }
        map.put("code",code);
        map.put("speName",speName);
        map.put("ffUnit",ffUnit);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        PageUtils<Feedback> listByPage = feedbackService.ListPage(map,pageNo , pageSize);
        return Result.ok(listByPage);
    }

//    @RequiresPermissions(value = {"fdpi:feedbackByGZ:view","fdpi:feedbackByZF:view"},logical = Logical.OR)
    @SofnLog("根据id获取反馈（公众/部门）信息")
    @ApiOperation(value = "根据id获取反馈（公众/部门）信息")
    @GetMapping("/get")
    public Result<Feedback> getFeedbackInfo (@RequestParam(value = "id")@ApiParam(name = "id" ,value = "反馈信息ID",required = true)String id){
        Feedback feedbackInfo = feedbackService.getFeedbackInfo(id);
        return Result.ok(feedbackInfo);
    }

//    @RequiresPermissions("fdpi:feedbackByZF:create")
//    @RequiresPermissions(value = {"fdpi:feedbackByGZ:create","fdpi:feedbackByZF:create"},logical = Logical.OR)
    @SofnLog("新增执法部门反馈信息")
    @ApiOperation(value = "新执法部门增反馈信息")
    @PostMapping("/save")
    public Result<Feedback> saveFeedback(
            @Validated @RequestBody @ApiParam(name = "反馈信息对象", value = "传入json格式", required = true)
                    FeedbackInfoVo feedbackInfoVo, BindingResult result) {
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
//        feedbackInfoVo.setFfFrom("2");
        int i = feedbackService.saveFeedback(feedbackInfoVo);
        if (i==1){
            return Result.ok("新增反馈信息成功");
        }else {
            return Result.error("已存在");
        }

    }


//    @RequiresPermissions("fdpi:feedbackByZF:create")
    @SofnLog("新增公众反馈信息")
    @ApiOperation(value = "新增公众反馈信息")
    @PostMapping("/savePublic")
    public Result<Feedback> save(
            @Validated @RequestBody @ApiParam(name = "反馈信息对象", value = "传入json格式", required = true)
                    FeedbackInfoVo feedbackInfoVo, BindingResult result) {
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
//        feedbackInfoVo.setFfFrom("1");
        int i = feedbackService.savePublic(feedbackInfoVo);
        if (i==1){
            return Result.ok("新增反馈信息成功");
        }else {
            return Result.error("已存在");
        }

    }


    @SofnLog("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete")
    public Result delCapture(@ApiParam(value = "id",required = true)@RequestParam("id")
                                     String id ) {
        return Result.ok(feedbackService.del(id));
    }


    @SofnLog("删除")
    @ApiOperation(value = "删除")
    @PutMapping(value = "/update")
    public Result delCapture(@Validated @RequestBody @ApiParam(name = "反馈信息对象", value = "传入json格式", required = true)
                                     FeedbackInfoVo feedbackInfoVo, BindingResult result) {
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        return Result.ok(feedbackService.update(feedbackInfoVo));

    }

    @SofnLog("反馈")
    @ApiOperation(value = "反馈")
    @PostMapping(value = "/advice")
    public Result advice(@Validated @RequestBody @ApiParam(name = "反馈对象", value = "传入json格式", required = true)
                                 BackAdvice backAdvice, BindingResult result) {
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        Map<String,String> map=Maps.newHashMap();
        map.put("id",backAdvice.getId());
        map.put("advice",backAdvice.getAdvice());
        map.put("record",backAdvice.getRecord());
        feedbackService.advice(map);
        return Result.ok();

    }


}
