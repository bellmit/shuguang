package com.sofn.fyrpa.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.model.TargetOneManager;
import com.sofn.fyrpa.service.TargetTwoManagerService;
import com.sofn.fyrpa.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "一级与二级指标管理模块", tags = "一级与二级指标管理相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/targetManager")
public class TargetTwoManagerController {
    @Autowired
    private TargetTwoManagerService targetTwoManagerService;

     @PostMapping("/addTargetOneManager")
     @ApiOperation("新增一级指标")
    // @RequiresPermissions("fyrpa:targetManager:addTargetOneManager")
     public Result addTargetOneManager(@Valid @RequestBody TargetOneManagerAddVo targetOneManagerAddVo){
         Result result = this.targetTwoManagerService.addTargetOneManager(targetOneManagerAddVo);
         return result;
     }

    @PutMapping("/updateTargetOneManager")
    @ApiOperation("编辑一级指标")
   // @RequiresPermissions("fyrpa:targetManager:updateTargetOneManager")
    public Result updateTargetOneManager(@Valid @RequestBody TargetOneManagerEditVo targetOneManagerEditVo){
        Result result = this.targetTwoManagerService.updateTargetOneManager(targetOneManagerEditVo);
        return result;
    }

    @PostMapping("/addTargetTwoManager")
    @ApiOperation("新增二级指标")
   //@RequiresPermissions("fyrpa:targetManager:addTargetTwoManager")
    public Result addTargetTwoManager(@Valid @RequestBody TargetTwoManagerAddVo targetTwoManagerAddVo) {
        if (StringUtils.isBlank(targetTwoManagerAddVo.getIsTargetName())) {
            return Result.error("请填写所属一级指标");
        }
        Result result = this.targetTwoManagerService.addTargetTwoManager(targetTwoManagerAddVo);
        return result;
    }

    @PutMapping("/updateTargetTwoManager")
    @ApiOperation("编辑二级指标")
    //@RequiresPermissions("fyrpa:targetManager:updateTargetTwoManager")
    public Result updateTargetTwoManager(@Valid @RequestBody TargetTwoManagerEditVo targetTwoManagerEditVo){
        Result result = this.targetTwoManagerService.updateTargetTwoManager(targetTwoManagerEditVo);
        return result;
    }


     @GetMapping("/selectListData")
     @ApiOperation("分页查询")
     //@RequiresPermissions("fyrpa:targetManager:selectListData")
    public Result<IPage<TargetManagerListVo>> selectListData(@ApiParam(value = "指标名称")@RequestParam(required = false,value = "targetName") String targetName,
                                 @ApiParam(value = "开始时间")@RequestParam(required = false,value = "startTime")  String startTime,
                                 @ApiParam(value = "结束时间")@RequestParam(required = false,value = "endTime")  String endTime,
                                 @ApiParam(value = "当前页")@RequestParam( required = false,value = "pageNo") Integer pageNo,
                                 @ApiParam(value = "每页显示条数") @RequestParam( required = false,value = "pageSize") Integer pageSize,
                                 @ApiParam(value = "指标类型")@RequestParam( required = false,value = "targetType") String targetType){

         IPage<TargetManagerListVo> list = this.targetTwoManagerService.selectListData(targetName, startTime, endTime, pageNo, pageSize,targetType);
         return Result.ok(list);
     }


    @PutMapping("/startAndstopTarget")
    @ApiOperation("启用或者停用")
   // @RequiresPermissions("fyrpa:targetManager:startAndstopTarget")
    public Result startAndstopTarget(@ApiParam(value = "id")@RequestParam(required = true,value = "id") String id){
        Result result = this.targetTwoManagerService.startAndstopTarget(id);
        return result;
    }

    @GetMapping("/selectTargetOneManagerList")
    @ApiOperation("获取一级指标名称下拉集合")
    //@RequiresPermissions("fyrpa:targetManager:selectTargetOneManagerList")
    public Result<List<TargetOneManager> > selectTargetOneManagerList(){
        Result<List<TargetOneManager> > result = this.targetTwoManagerService.selectTargetOneManagerList();
        return result;
    }


  /*  @GetMapping("/getTwoDatilsById")
    @ApiOperation("二级指标详情页查询")
    public Result getTwoDatilsById(@ApiParam(value = "id")@RequestParam(required = true) String id){
        Result result = this.targetTwoManagerService.selectTargetTwoManagerById(id);
        return result;
    }

    @GetMapping("/getOneDatilsById")
    @ApiOperation("二级指标详情页查询")
    public Result getOneDatilsById(@ApiParam(value = "id")@RequestParam(required = true) String id){
        Result result = this.targetTwoManagerService.selectTargetOneManagerById(id);
        return result;
    }*/

}
