package com.sofn.ahhrm.web;

import com.sofn.ahhrm.service.AnalysieService;
import com.sofn.ahhrm.service.BaseinfoService;
import com.sofn.ahhrm.vo.ChartVo;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "信息分析模块接口")
@RestController
@RequestMapping("/analyse")
public class AnalyseController extends BaseController {

    @Autowired
    private AnalysieService analysieService;


    @ApiOperation(value = "查询有效群体数据")
    @GetMapping("/getEffective")
    public Result<List<ChartVo>> getEffective(
            @ApiParam(value = "监测点名称", required = true) @RequestParam() String pointName,
            @ApiParam(value = "年份", example = "2020", required = true) @RequestParam() String year,
            @ApiParam(value = "指标(可传可不传,后端没用)") @RequestParam(required = false) String index,
            @ApiParam(value = "品种名称ID", required = true) @RequestParam() String variety) {
        return Result.ok(analysieService.getEffective(pointName, year, index, variety));
    }

    @ApiOperation(value = "查询公母比例")
    @GetMapping("/getRatio")
    public Result<List<ChartVo>> getRatio(
            @ApiParam(value = "监测点名称", required = true) @RequestParam() String pointName,
            @ApiParam(value = "年份", example = "2020", required = true) @RequestParam() String year,
            @ApiParam(value = "指标(可传可不传,后端没用)") @RequestParam(required = false) String index,
            @ApiParam(value = "品种名称ID", required = true) @RequestParam() String variety) {
        return Result.ok(analysieService.getRatio(pointName, year, index, variety));
    }


}
