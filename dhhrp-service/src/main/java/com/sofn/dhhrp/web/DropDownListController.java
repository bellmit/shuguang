package com.sofn.dhhrp.web;


import com.sofn.dhhrp.Api.AhhdpApi;
import com.sofn.dhhrp.service.BaseinfoService;
import com.sofn.dhhrp.util.RedisUserUtil;
import com.sofn.dhhrp.vo.DropDownVo;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "下拉列表管理相关接口", tags = "下拉列表管理相关接口")
@Slf4j
@RestController
@RequestMapping("/dropDownList")
public class DropDownListController {

    @Resource
    private BaseinfoService baseinfoService;

    @Resource
    private AhhdpApi ahhdpApi;


    @ApiOperation(value = "获取年份(下拉列表)")
    @GetMapping("/getYears")
    public Result<List<DropDownVo>> getYears() {
        return Result.ok(baseinfoService.getYears());
    }

    @ApiOperation(value = "获取监测点名称(下拉列表) 基础信息模块不用")
    @GetMapping("/getPointNames")
    public Result<List<DropDownVo>> getPointNames() {
        return Result.ok(baseinfoService.getPointNames());
    }

    @ApiOperation(value = "获取当前省份下的品种名称(下拉列表)")
    @GetMapping("/dropDownList/listForName")
    public Result<List<DropDownVo>> listForName() {
        return ahhdpApi.listForName(RedisUserUtil.getProvinceCode());
    }


}
