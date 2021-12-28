package com.sofn.ducss.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.ducss.service.HomePageService;
import com.sofn.ducss.service.MessageService;
import com.sofn.ducss.vo.homePage.DataArea;
import com.sofn.ducss.vo.homePage.ReportProgressVo;
import com.sofn.ducss.vo.message.MessageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页展示控制器
 *
 * @author jiangtao
 * @date 2020/10/29
 */
@Api(tags = "首页展示")
@RestController
@RequestMapping("/homePage")
public class HomePageController {


    @Autowired
    private HomePageService homePageService;

    @SofnLog("填报县数,市场化主体,抽样分散量")
    @ApiOperation(value = "填报县数,市场化主体,抽样分散量")
    @GetMapping(value = "/getDataArea")
    public Result<DataArea> getDataArea(@RequestParam(value = "year",required = false) @ApiParam(value="年份",required = true) String year,
                                            @RequestParam(value = "areaCode",required = false) @ApiParam(value="区域代码",required = false) String areaCode,
    @RequestParam(value = "administrativeLevel",required = false) @ApiParam(value="区域等级",required = false) String administrativeLevel) {
        DataArea dataArea = homePageService.getDataArea(year, areaCode, administrativeLevel,null);
        return Result.ok(dataArea);
    }

    @SofnLog("首页饼状图展示")
    @ApiOperation(value = "首页饼状图展示")
    @GetMapping(value = "/getReportProgress")
    public Result<ReportProgressVo> getDataArea(@RequestParam(value = "year",required = true) @ApiParam(value="年份",required = true) String year,
                                        @RequestParam(value = "administrativeLevel",required = false) @ApiParam(value="区域等级",required = false) String administrativeLevel) {
        List<ReportProgressVo> reportProgress = homePageService.getReportProgress(year, administrativeLevel);
        return Result.ok(reportProgress);
    }
}