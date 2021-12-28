package com.sofn.fyrpa.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.service.ResourcesInfoGatherService;
import com.sofn.fyrpa.vo.AquaticResourcesProtectionInfoVoList;
import com.sofn.fyrpa.vo.ListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value = "保护区基本信息汇总模块", tags = "保护区基本信息汇总的相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/resourcesInfoGather")
public class ResourcesInfoGatherController {
    @Autowired
    private ResourcesInfoGatherService resourcesInfoGatherService;

    @GetMapping("/selectGatherByCondition")
    @ApiOperation("保护区信息汇总分页查询")
    //@RequiresPermissions("fyrpa:resourcesInfoGather:selectGatherByCondition")
    @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByCondition(@ApiParam(value = "当前页数")@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                                                           @ApiParam(value = "每页显示条数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                                           @ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                                                                           @RequestParam(value = "regionCodeArr", required = false) String[] regionCodeArr,
                                                                                           @ApiParam(value = "所属流域或海区")@RequestParam(value = "basinOrSeaArea", required = false) String basinOrSeaArea,
                                                                                           @ApiParam(value = "所属水系或海域")@RequestParam(value = "riverOrMaritimeSpace", required = false) String riverOrMaritimeSpace,
                                                                                           @ApiParam(value = "全文")@RequestParam(value = "keyword", required = false) String keyword,
                                                                                           @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name,
                                                                                           @ApiParam(value = "主要保护对象")@RequestParam(value = "majorProtectObject", required = false) String majorProtectObject){
        Result<IPage<AquaticResourcesProtectionInfoVoList>> result = this.resourcesInfoGatherService.selectGatherListByCondition(pageNo, pageSize, submitTime, regionCodeArr, basinOrSeaArea, riverOrMaritimeSpace, keyword,name,majorProtectObject);
        return result;
    }


    @ApiOperation("详情页查询")
    @GetMapping("/selectDetailsById")
   // @RequiresPermissions("fyrpa:resourcesInfoGather:selectDetailsById")
    public Result selectDetailsById(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result<ListVo> result = this.resourcesInfoGatherService.selectDetailsById(id);
        return  result;
    }

    @ApiOperation("审批意见查询")
    @GetMapping("/selectInfoAuditList")
   // @RequiresPermissions("fyrpa:resourcesInfoGather:selectInfoAuditList")
    public Result selectInfoAuditList(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result result = this.resourcesInfoGatherService.selectInfoAuditList(id);
        return result;
    }

    @GetMapping(value = "/export",produces = "application/octet-stream")
    @ApiOperation("导出")
   // @RequiresPermissions("fyrpa:resourcesInfoGather:export")
    @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public void export(@ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                       @RequestParam(value = "regionCodeArr", required = false) String[] regionCodeArr,
                       @ApiParam(value = "所属流域或海区")@RequestParam(value = "basinOrSeaArea", required = false) String basinOrSeaArea,
                       @ApiParam(value = "所属水系或海域")@RequestParam(value = "riverOrMaritimeSpace", required = false) String riverOrMaritimeSpace,
                       @ApiParam(value = "全文")@RequestParam(value = "keyword", required = false) String keyword, HttpServletResponse response,
                       @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name,
                       @ApiParam(value = "主要保护对象")@RequestParam(value = "majorProtectObject", required = false) String majorProtectObject){


         this.resourcesInfoGatherService.export("保护区汇总信息统计.xlsx", response, submitTime, regionCodeArr, basinOrSeaArea, riverOrMaritimeSpace, keyword, name, majorProtectObject);

    }

    @ApiOperation("按面积排序")
    @GetMapping("/selectListByAreasSort")
   // @RequiresPermissions("fyrpa:resourcesInfoGather:selectListByAreasSort")
    @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public Result selectGatherListByAreasSort(@ApiParam(value = "排序",defaultValue = "1")@RequestParam(value = "sort", required = false) String sort,
                                              @ApiParam(value = "当前页数")@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                              @ApiParam(value = "每页显示条数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                              @ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                              @RequestParam(value = "regionCodeArr", required = false) String[] regionCodeArr,
                                              @ApiParam(value = "所属流域或海区")@RequestParam(value = "basinOrSeaArea", required = false) String basinOrSeaArea,
                                              @ApiParam(value = "所属水系或海域")@RequestParam(value = "riverOrMaritimeSpace", required = false) String riverOrMaritimeSpace,
                                              @ApiParam(value = "全文")@RequestParam(value = "keyword", required = false) String keyword,
                                              @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name,
                                              @ApiParam(value = "主要保护对象")@RequestParam(value = "majorProtectObject", required = false) String majorProtectObject){
        Result result = this.resourcesInfoGatherService.selectGatherListByAreasSort(sort, pageNo, pageSize,submitTime,regionCodeArr,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);
        return result;
    }

    @ApiOperation("按时间排序")
    @GetMapping("/selectListByTimesSort")
   // @RequiresPermissions("fyrpa:resourcesInfoGather:selectListByTimesSort")
    @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public Result selectGatherListByTimesSort(@ApiParam(value = "排序",defaultValue = "1")@RequestParam(value = "sort", required = false) String sort,
                                              @ApiParam(value = "当前页数")@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                              @ApiParam(value = "每页显示条数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                              @ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                              @RequestParam(value = "regionCodeArr", required = false) String[] regionCodeArr,
                                              @ApiParam(value = "所属流域或海区")@RequestParam(value = "basinOrSeaArea", required = false) String basinOrSeaArea,
                                              @ApiParam(value = "所属水系或海域")@RequestParam(value = "riverOrMaritimeSpace", required = false) String riverOrMaritimeSpace,
                                              @ApiParam(value = "全文")@RequestParam(value = "keyword", required = false) String keyword,
                                              @ApiParam(value = "保护区名称")@RequestParam(value = "name", required = false) String name,
                                              @ApiParam(value = "主要保护对象")@RequestParam(value = "majorProtectObject", required = false) String majorProtectObject){
        Result result = this.resourcesInfoGatherService.selectGatherListByTimesSort(sort, pageNo, pageSize,submitTime,regionCodeArr,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);
        return result;
    }

}
