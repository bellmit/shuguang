package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Question;
import com.sofn.fdpi.service.QuestionService;
import com.sofn.fdpi.vo.AnswerVo;
import com.sofn.fdpi.vo.QuestionFrom;
import com.sofn.fdpi.vo.QuestionVo;
import com.sofn.fdpi.vo.QuestionVoOne;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/17 10:22
 */
@Deprecated
@Api(value = "问题相关接口",tags = "问题相关接口")
@RestController
@Slf4j
@RequestMapping(value = "question")
public class QuestionController extends BaseController {
    @Autowired
    QuestionService qService;
    @RequiresPermissions("fdpi:questions:query")
    @ApiOperation(value = " 获取问题信息(分页)")
    @GetMapping("/list")
    @SofnLog("获取问题信息(分页)")
    public Result<List<Question>> getQuestionListByPage(@ApiParam(name = "queStatus",value = "问题状态0未解答1解答")@RequestParam(value = "queStatus",required = false)String queStatus,
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
        map.put("queStatus",queStatus);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        PageUtils<Question> list = qService.getQuestionList(map,pageNo ,pageSize );
        return Result.ok(list);
    }
    @ApiOperation(value = "新增问题（不带token门户使用）")
    @PostMapping("/save")
    @SofnLog("/新增问题")
    public Result<Question> saveQuestion(@Validated @RequestBody @ApiParam(name = "问题信息对象", value = "传入json格式", required = true)
            QuestionVoOne questionVoOne, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = qService.saveQuestion(questionVoOne);
        if ("1".equals(s)){
            return Result.ok("新增问题信息成功");
        }else {
            return Result.error(s);
        }

    }
    @RequiresPermissions("fdpi:questions:create")
    @ApiOperation(value = "新增问题(带token) 公司使用")
    @PostMapping("/saveQuestion")
    @SofnLog("/新增问题")
    public Result<Question> saveQuestionWithToken(@Validated @RequestBody @ApiParam(name = "问题信息对象", value = "传入json格式", required = true)
                                                 QuestionVoOne questionVoOne, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = qService.saveQuestionWithToken(questionVoOne);
        if ("1".equals(s)){
            return Result.ok("新增问题信息成功");
        }else {
            return Result.error(s);
        }

    }

    @RequiresPermissions("fdpi:questions:view")
    @ApiOperation(value = " 根据id查询问题详细信息")
    @GetMapping("/get")
    @SofnLog("根据id查询问题详细信息")
    public Result<Question> getQuestion(@RequestParam(value = "id") String id){
        Question question = qService.getQuestion(id);
        return Result.ok(question);
    }
    @RequiresPermissions("fdpi:questions:answer")
    @ApiOperation(value = " 解答问题信息qqqqq")
    @PostMapping("/update")
    @SofnLog("解答1邮件")
    public Result updateQuestion(@Validated @RequestBody @ApiParam(name = "解答对象",
            value = "传入json格式", required = true) AnswerVo questionFrom, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        int i = qService.answerQuestion(questionFrom);
        if (i==1) {
            return Result.ok("解答问题成功");
        }
        return Result.error("解答问题失败");
    }
}
