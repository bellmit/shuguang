package com.sofn.fyrpa.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.model.AppraiseAnalyse;
import com.sofn.fyrpa.service.AppraiseAnalyseService;
import com.sofn.fyrpa.vo.AppraiseAnalyseDetailsVo;
import com.sofn.fyrpa.vo.AppraiseAnalyseVo;
import com.sofn.fyrpa.vo.AppraiseAnalyseVoList;
import com.sofn.fyrpa.vo.ResourceAppraiseAnalyseVoList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "评价分析模块", tags = "评价分析的相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/appraiseAnalyse")
public class AppraiseAnalyseController {
    @Autowired
    private AppraiseAnalyseService appraiseAnalyseService;


    @PostMapping("/add")
    @ApiOperation("评分")
    //@RequiresPermissions("fyrpa:appraiseAnalyse:add")
    public Result add(@Validated @RequestBody List<AppraiseAnalyse> appraiseAnalyseList){
        Result result = this.appraiseAnalyseService.add(appraiseAnalyseList);
        return result;
    }

    @GetMapping("/selectResourceAnalyseList")
    @ApiOperation("查询保护区效果分析集合")
    //@RequiresPermissions("fyrpa:appraiseAnalyse:selectResourceAnalyseList")
    public Result<IPage<ResourceAppraiseAnalyseVoList>> selectResourceAnalyseList(@ApiParam("当前页数") @RequestParam(required = false,value = "pageNo") Integer pageNo,
                                                                                  @ApiParam("每页显示条数") @RequestParam(required = false,value = "pageSize") Integer pageSize,
                                                                                  @ApiParam("保护区名称") @RequestParam(required = false,value = "name") String name,
                                                                                  @ApiParam("评价年度") @RequestParam(required = false,value = "submitTime") String submitTime,
                                                                                  @ApiParam("开始评分") @RequestParam(required = false,value = "startTotalScore") Double startTotalScore,
                                                                                  @ApiParam("结束评分") @RequestParam(required = false,value = "endTotalScore") Double endTotalScore,
                                                                                  @ApiParam("所在流域或海区") @RequestParam(required = false,value = "basinOrSeaArea") String basinOrSeaArea){

        Result<IPage<ResourceAppraiseAnalyseVoList>>result = this.appraiseAnalyseService.selectResourceAnalyseList(pageNo, pageSize, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea);
        return result;
    }

    @GetMapping("/selectListByTimeSort")
    @ApiOperation("按批准时间排序")
    //@RequiresPermissions("fyrpa:appraiseAnalyse:selectListByTimeSort")
    public Result<IPage<ResourceAppraiseAnalyseVoList>> selectListByTimeSort(@ApiParam("当前页数") @RequestParam(required = false,value = "pageNo") Integer pageNo,
                                            @ApiParam("每页显示条数") @RequestParam(required = false,value = "pageSize") Integer pageSize,
                                            @ApiParam("保护区名称") @RequestParam(required = false,value = "name") String name,
                                            @ApiParam("评价年度") @RequestParam(required = false,value = "submitTime") String submitTime,
                                            @ApiParam("开始评分") @RequestParam(required = false,value = "startTotalScore") Double startTotalScore,
                                            @ApiParam("结束评分") @RequestParam(required = false,value = "endTotalScore") Double endTotalScore,
                                            @ApiParam("所在流域或海区") @RequestParam(required = false,value = "basinOrSeaArea") String basinOrSeaArea,
                                            @ApiParam(value = "排序",defaultValue = "1") @RequestParam(required = false,value = "sort") String sort){

        Result<IPage<ResourceAppraiseAnalyseVoList>> result = this.appraiseAnalyseService.selectListByTimeSort(pageNo, pageSize, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea,sort);
        return result;
    }

    @GetMapping("/selectListByScoreSort")
    @ApiOperation("按评分效果排序")
    //@RequiresPermissions("fyrpa:appraiseAnalyse:selectListByScoreSort")
    public Result<IPage<ResourceAppraiseAnalyseVoList>> selectListByScoreSort(@ApiParam("当前页数") @RequestParam(required = false,value = "pageNo") Integer pageNo,
                                       @ApiParam("每页显示条数") @RequestParam(required = false,value = "pageSize") Integer pageSize,
                                       @ApiParam("保护区名称") @RequestParam(required = false,value = "name") String name,
                                       @ApiParam("评价年度") @RequestParam(required = false,value = "submitTime") String submitTime,
                                       @ApiParam("开始评分") @RequestParam(required = false,value = "startTotalScore") Double startTotalScore,
                                       @ApiParam("结束评分") @RequestParam(required = false,value = "endTotalScore") Double endTotalScore,
                                       @ApiParam("所在流域或海区") @RequestParam(required = false,value = "basinOrSeaArea") String basinOrSeaArea,
                                       @ApiParam(value = "排序",defaultValue = "1") @RequestParam(required = false,value = "sort") String sort){

        Result<IPage<ResourceAppraiseAnalyseVoList>> result = this.appraiseAnalyseService.selectListByScoreSort(pageNo, pageSize, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea,sort);
        return result;
    }

    @GetMapping("/selectDetailsList")
    @ApiOperation("查询评分详情页")
    //@RequiresPermissions("fyrpa:appraiseAnalyse:selectDetailsList")
    public Result< List<AppraiseAnalyseDetailsVo>> selectDetailsList(@ApiParam("resourceId")@RequestParam(required = true,value ="resourceId" )String resourceId){
        Result< List<AppraiseAnalyseDetailsVo>> result = this.appraiseAnalyseService.selectAppraiseAnalyseDetails(resourceId);
        return result;
    }

    @GetMapping("/selectAnalyseList")
    @ApiOperation("查询评价指标集合")
    //@RequiresPermissions("fyrpa:appraiseAnalyse:selectAnalyseList")
    public Result< List<AppraiseAnalyseVoList>> selectAnalyseList(){
        Result< List<AppraiseAnalyseVoList>>  result = this.appraiseAnalyseService.selectAnalyseList();
        return result;
    }
}
