package com.sofn.agpjpm.web;

import com.sofn.agpjpm.service.TargetSpecService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.vo.DropDownVo;
import com.sofn.agpjpm.vo.DropDownWithLatinVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-11 10:41
 */
@Api(value = "下拉列表管理相关接口", tags = "下拉列表管理相关接口")
@Slf4j
@RestController
@RequestMapping("/dropDownList")
public class DropDownListController {
    @Autowired
    TargetSpecService targetSpecService;
    @Autowired
    private JzbApi jzbApi;

    @SofnLog("《下拉列表》获取农业野生植物物种")
    @ApiOperation(value = "《下拉列表》获取农业野生植物物种")
    @GetMapping("/listForSpecies")
    public Result<List<DropDownWithLatinVo>> listForSpecies() {
        List<DropDownWithLatinVo> dropDownList = targetSpecService.getDropList();
        return Result.ok(dropDownList);
    }

    @ApiOperation(value = "《下拉列表》获取生境类型")
    @GetMapping("/listForHabitatType")
    public Result<List<DropDownVo>> listForHabitatType1(){
        return  Result.ok(jzbApi.listForHabitatType().getData());
    }

    @SofnLog("《下拉列表》获取土壤类型")
    @ApiOperation(value = "《下拉列表》获取土壤类型")
    @GetMapping("/listForSoilType")
    public Result<List<DropDownVo>> listForSoilType() {
        return Result.ok(jzbApi.listForSoilType().getData());
    }
    @ApiOperation(value = "《下拉列表》获取地形列表")
    @GetMapping("/listForTopography")
    public Result<List<DropDownVo>> listForTopography(){
        return  Result.ok(jzbApi.listForTopography().getData());
    }

    @ApiOperation(value = "《下拉列表》获取气候类型列表")
    @GetMapping("/listForClimateType")
    public Result<List<DropDownVo>> listForClimateType(){
        return  Result.ok(jzbApi.listForClimateType().getData());
    }
}
