package com.sofn.agzirdd.web;

import com.sofn.agzirdd.enums.ClassifyEnum;
import com.sofn.agzirdd.service.WelcomeService;
import com.sofn.agzirdd.vo.WelcomeMapResVo;
import com.sofn.agzirdd.vo.WelcomeSearchVo;
import com.sofn.agzirdd.vo.WelcomeTableVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "外来入侵-全国外来入侵特种调查与监测点位图接口", tags = "外来入侵-全国外来入侵特种调查与监测点位图接口")
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @SofnLog("查询-地图数据")
    @ApiOperation(value="查询-地图数据")
    @PostMapping("/getMapInfo")
    public Result<WelcomeMapResVo> getMapInfo(
            @Validated @RequestBody @ApiParam(value = "欢迎页搜索数据对象", required = true) WelcomeSearchVo vo
    ){
        List<WelcomeMapResVo> res = welcomeService.getMapInfo(vo);
        return Result.ok(res);
    }

    @SofnLog("查询-调查-发生面积数据")
    @ApiOperation(value="查询-发生面积数据")
    @PostMapping("/getDCFSMJInfo")
    public Result<WelcomeTableVo> getDCFSMJInfo(
            @Validated @RequestBody @ApiParam(value = "欢迎页搜索数据对象", required = true) WelcomeSearchVo vo
    ){
        List<WelcomeTableVo> res =welcomeService.getDCFSMJInfo(vo);

        return Result.ok(res);
    }

    @SofnLog("查询-调查-默认发生面积物种名称")
    @ApiOperation(value="查询-默认发生面积物种名称")
    @PostMapping("/getDefaultFSMJSpName")
    public Result getDefaultFSMJSpName(
            @RequestParam("year") @ApiParam(value = "欢迎页搜索数据对象", required = true) String year
    ){
        String res =welcomeService.getDefaultFSMJSpName(year);

        return Result.ok(res);
    }

    @SofnLog("查询-调查-种群数量数据")
    @ApiOperation(value="查询-种群数量数据")
    @PostMapping("/getDCZQSLInfo")
    public Result<WelcomeTableVo> getDCZQSLInfo(
            @Validated @RequestBody @ApiParam(value = "欢迎页搜索数据对象", required = true) WelcomeSearchVo vo
    ){
        List<WelcomeTableVo> res =welcomeService.getDCZQSLInfo(vo);
        return Result.ok(res);
    }

    @SofnLog("查询-调查-种群数量默认物种")
    @ApiOperation(value="查询-种群数量默认物种")
    @PostMapping("/getDefaultZQSLSpName")
    public Result getDefaultZQSLSpName(
            @RequestParam("year") @ApiParam(value = "欢迎页搜索数据对象", required = true)String year
            ){
        String res =welcomeService.getDefaultZQSLSpName(year);
        return Result.ok(res);
    }


    @SofnLog("查询-监测-发生面积数据")
    @ApiOperation(value="查询-监测-发生面积数据")
    @PostMapping("/getFSMJInfo")
    public Result<WelcomeTableVo> getFSMJInfo(
            @Validated @RequestBody @ApiParam(value = "欢迎页搜索数据对象", required = true) WelcomeSearchVo vo
    ){
        vo.setClassificationName(ClassifyEnum.AREA.getDescription());
        List<WelcomeTableVo> res =welcomeService.getFSMJInfo(vo);

        return Result.ok(res);
    }

    @SofnLog("查询-监测-种群数量数据")
    @ApiOperation(value="查询-种群数量数据")
    @PostMapping("/getZQSLInfo")
    public Result<WelcomeTableVo> getZQSLInfo(
            @Validated @RequestBody @ApiParam(value = "欢迎页搜索数据对象", required = true) WelcomeSearchVo vo
    ){
        List<WelcomeTableVo> res =welcomeService.getZQSLInfo(vo);
        return Result.ok(res);
    }




}
