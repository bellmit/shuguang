package com.sofn.fdzem.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.Index;
import com.sofn.fdzem.service.EvaluateSerevice;
import com.sofn.fdzem.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "评价管理接口", description = "")
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateSerevice evaluateService;

    @GetMapping("/getIndices")
    @SofnLog("获取所有的一级指标，以及一级指标下的所有二级指标")
    @ApiOperation(value = "获取所有的一级指标，以及一级指标下的所有二级指标", notes = "获取所有的一级指标，以及一级指标下的所有二级指标")
    public Result<List<Map<String, Object>>> getIndices() {
        List<Index> maps = evaluateService.getIndices();
        return Result.ok(maps, "操作成功");
    }

    @GetMapping("/listPage")
    @SofnLog("评价管理分页查询")
    @RequiresPermissions("fdzem:evaluate:list")
    @ApiOperation(value = "评价管理分页查询", notes = "评价管理分页查询")
    public Result<PageUtils<Index>> listPage(@ApiParam(name = "pageNum", value = "当前页码", required = true) @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                             @ApiParam(name = "pageSize", value = "每页显示条数", required = true) @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                             @ApiParam(name = "organizationName", value = "机构名称", required = false) @RequestParam(value = "organizationName", required = false) String organizationName,
                                             @ApiParam(name = "submitYear", value = "年份", required = true) @RequestParam(value = "submitYear", required = false) String submitYear,
                                             @ApiParam(name = "lowestScore", value = "最低分", required = false) @RequestParam(value = "lowestScore", required = false) Double lowestScore,
                                             @ApiParam(name = "highestScore", value = "最高分", required = false) @RequestParam(value = "highestScore", required = false) Double highestScore,
                                             @ApiParam(name = "field", value = "排序字段(score)", required = true) @RequestParam(value = "field", required = false) String field,
                                             @ApiParam(name = "isAcs", value = "升序or降序(ASC 顺序，DESC倒序)", required = false) @RequestParam(value = "isAcs", required = false) String isAcs) {
        return Result.ok(evaluateService.listPage(pageNum, pageSize, organizationName, submitYear, lowestScore, highestScore,field,isAcs));
    }

    @PostMapping("/insert")
    @SofnLog("保存评分")
    @RequiresPermissions("fdzem:evaluate:insert")
    @ApiOperation(value = "保存评分", notes = "{topName:一级指标,name:二级指标,number:数值,consultValue:参考值,score:分值,getScore:得分}")
    public Result saveTopIndex(
            @ApiParam(name = "id", value = "评分数据id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "score", value = "评分json数据", required = true) @RequestParam(value = "score", required = true) String score,
            @ApiParam(name = "date", value = "年份", required = true) @RequestParam(value = "date", required = true) String date) {
        evaluateService.insert(id, score, date);
        return Result.ok("保存成功");
    }

    @GetMapping("/getById/{id}/{date}")
    @SofnLog("通过id获取评分数据")
    @RequiresPermissions("fdzem:evaluate:view")
    @ApiOperation(value = "通过id获取评分数据", notes = "通过id获取评分数据")
    @ResponseBody
    public Result<String> getById(@PathVariable("id") String id,
                                  @PathVariable("date") String date) {
        String score = evaluateService.getById(id,date);
        return Result.ok(score, "成功获取数据");
    }
}
