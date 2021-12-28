package com.sofn.ducss.web;


import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.ThresholdValueManagerService;
import com.sofn.ducss.vo.ThresholdValueManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/thresholdValueManager")
@Api(value = "阈值管理", tags = "阈值管理")
@Slf4j
@Validated
public class ThresholdValueManagerController extends BaseController {

    @Autowired
    private ThresholdValueManagerService thresholdValueManagerService;

    @ApiOperation(value = "获取年度表格", notes = "ducss:thresholdValueManager:getYearTableList")
    @GetMapping("/getYearTableList/{year}")
    public Result<List<Map<String,String>>> getYearTableList(@PathVariable("year") String year){
        List<Map<String, String>> yearTableList = thresholdValueManagerService.getYearTableList(year);
        return Result.ok(yearTableList);
    }


    @ApiOperation(value = "编辑值", notes = "ducss:thresholdValueManager:editValue")
    @PostMapping("/editValue")
    public Result<String> editValue(@Valid @RequestBody  List<ThresholdValueManagerVo>  thresholdValueManagerVos, BindingResult result){
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        thresholdValueManagerService.editValue(thresholdValueManagerVos);
        return Result.ok("编辑成功");
    }

    @ApiOperation(value = "查询具体的阈值信息", notes = "ducss:thresholdValueManager:getInfo")
    @GetMapping("/getInfo")
    public Result<List<ThresholdValueManagerVo>> getInfo(@RequestParam("year") @ApiParam(value = "年份", required = true) String year,
                                                       @RequestParam("tableType") @ApiParam(value = "表格类型1.数据审核2. 产生情况汇总3. 利用情况汇总 4.还田离田情况", required = true)String tableType ){

        List<ThresholdValueManagerVo> info = thresholdValueManagerService.getInfo(year, tableType);
        return Result.ok(info);
    }

}
