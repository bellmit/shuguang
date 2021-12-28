package com.sofn.ahhdp.web;


import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.service.DirectoriesService;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "下拉列表管理相关接口", tags = "下拉列表管理相关接口")
@Slf4j
@RestController
@RequestMapping("/dropDownList")
public class DropDownListController {
    @Autowired
    private DirectoriesService directoriesService;


    @ApiOperation(value = "《下拉列表》审核状态")
    @GetMapping("/listForPointCollect")
    public Result<List<DropDownVo>> listForPointCollect() {
        return Result.ok(AuditStatusEnum.getSelect());
    }

    @SofnLog("《下拉列表》获取当前省份下的品种名称")
    @ApiOperation(value = "《下拉列表》获取当前省份下的品种名称")
    @GetMapping("/listForName")
    public Result<List<DropDownVo>> listForName(@RequestParam()@ApiParam(name = "provinceCode",value = "省级区域编码",required = true) String provinceCode) {
        List<DropDownVo> dropDownList = directoriesService.getRusult(provinceCode);
        return Result.ok(dropDownList);
    }
    @SofnLog("《下拉列表》获取所有的品种名称")
    @ApiOperation(value = "《下拉列表》获取所有的品种名称")
    @GetMapping("/listForAllName")
    public Result<List<DropDownVo>> listForAllName() {
        List<DropDownVo> dropDownList = directoriesService.getOldName();
        return Result.ok(dropDownList);
    }
}
