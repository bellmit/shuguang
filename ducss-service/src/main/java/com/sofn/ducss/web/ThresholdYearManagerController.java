package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.ducss.service.ThresholdYearManagerService;
import com.sofn.ducss.vo.ThresholdYearManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/thresholdYearManager")
@Api(value = "阈值年度管理", tags = "阈值年度管理")
@Slf4j
public class ThresholdYearManagerController extends BaseController {

    @Autowired
    private ThresholdYearManagerService thresholdYearManagerService;

    @ApiOperation(value = "新增阈值年度管理", notes = "ducss:thresholdYearManager:insert")
    @PostMapping("/insert")
    public Result<String> insert(@Validated @RequestBody ThresholdYearManagerVo thresholdYearManagerVo, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        thresholdYearManagerService.insert(thresholdYearManagerVo);
        return Result.ok("添加成功");
    }

    @ApiOperation(value = "更新阈值年度管理", notes = "ducss:thresholdYearManager:update")
    @PostMapping("/update")
    public Result<String> update(@Validated @RequestBody ThresholdYearManagerVo thresholdYearManagerVo, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        thresholdYearManagerService.update(thresholdYearManagerVo);
        return Result.ok("更新成功");
    }

    @ApiOperation(value = "删除阈值年度管理", notes = "ducss:thresholdYearManager:delete")
    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable("id") String id) {
        thresholdYearManagerService.delete(id);
        return Result.ok("删除成功");

    }

    @ApiOperation(value = "按条件分页查询", notes = "ducss:thresholdYearManager:getList")
    @GetMapping("/getList")
    public Result<PageUtils<ThresholdYearManagerVo>> getList(@RequestParam(value = "year", required = false) @ApiParam("年度") String year,
                                                             @RequestParam(value = "pageNo", required = false) @ApiParam("从哪条记录开始") Integer pageNo,
                                                             @RequestParam(value = "pageSize", required = false) @ApiParam("显示多少条") Integer pageSize) {
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(year)) {
            years = Arrays.asList(year.split(","));
        }
        PageUtils<ThresholdYearManagerVo> list = thresholdYearManagerService.getList(years, pageNo, pageSize);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取数据库中已有的年度", notes = "ducss:thresholdYearManager:getHaveYear")
    @GetMapping("/getHaveYear")
    public Result<List<String>> getHaveYear() {
        List<String> haveYear = thresholdYearManagerService.getHaveYear();
        return Result.ok(haveYear);
    }

}
