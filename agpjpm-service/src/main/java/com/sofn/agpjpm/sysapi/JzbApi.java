package com.sofn.agpjpm.sysapi;

import com.sofn.agpjpm.vo.DropDownVo;
import com.sofn.agpjpm.vo.DropDownWithLatinVo;

import com.sofn.agpjpm.vo.YearClimateEnvironmentVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-13 11:29
 */
@FeignClient(value = "agpjzb-service", configuration = FeignConfiguration.class)
public interface JzbApi {



    @ApiOperation(value = "《下拉列表》获取保护点名称")
    @GetMapping("/dropDownList/listForProtectPoints")
    Result<List<DropDownVo>> listForProtectPoints(@RequestParam(value = "provinceCode", required = false) @ApiParam(name = "provinceCode", value = "省级区域编码,如果传递的是空字符串，则取所有数据") String provinceCode);
    @ApiOperation(value = "《下拉列表》获取农业野生植物物种")
    @GetMapping("/dropDownList/listForSpecies")
    Result<List<DropDownWithLatinVo>> listForSpecies();

    @ApiOperation(value = "《下拉列表》获取生境类型")
    @GetMapping("/dropDownList/listForHabitatType")
    Result<List<DropDownVo>> listForHabitatType();
    @ApiOperation(value = "《下拉列表》获取土壤类型")
    @GetMapping("/dropDownList/listForSoilType")
    Result<List<DropDownVo>> listForSoilType();
    @ApiOperation(value = "《下拉列表》获取地形列表")
    @GetMapping("/dropDownList/listForTopography")
    Result<List<DropDownVo>> listForTopography();

    @ApiOperation(value = "《下拉列表》获取气候类型列表")
    @GetMapping("/dropDownList/listForClimateType")
    Result<List<DropDownVo>> listForClimateType();

    @ApiOperation(value = "根据年度和地区区划获取一条年度气候环境信息")
    @GetMapping("/dropDownList/getYearClimateEnvironment")
    Result<YearClimateEnvironmentVo> getYearClimateEnvironment(@RequestParam("year") @ApiParam(name = "year", value = "年度", required = true, example = "2020") String year
            , @RequestParam("provinceCode") @ApiParam(name = "provinceCode", value = "省编号", required = true, example = "2020") String provinceCode
            , @RequestParam("cityCode") @ApiParam(name = "cityCode", value = "市编号", required = true, example = "2020") String cityCode
            , @RequestParam("areaCode") @ApiParam(name = "areaCode", value = "区编号", required = true, example = "2020") String areaCode);
}
