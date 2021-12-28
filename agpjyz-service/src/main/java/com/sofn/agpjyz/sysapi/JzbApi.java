package com.sofn.agpjyz.sysapi;

import com.sofn.agpjyz.vo.AgricultureSpeciesVo;
import com.sofn.agpjyz.vo.DropDownVo;
import com.sofn.agpjyz.vo.DropDownWithLatinVo;
import com.sofn.agpjyz.vo.DropDownWithOther;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-13 11:29
 */
@FeignClient(value = "agpjzb-service", configuration = FeignConfiguration.class)
public interface JzbApi {

    @ApiOperation(value = "根据id，获取农业野生植物物种数据库管理")
    @GetMapping("/agricultureSpecies/get")
    Result<AgricultureSpeciesVo> get(@RequestParam(value = "id") @ApiParam(name = "id", value = "农业野生植物物种数据库管理中主键", required = true) String id);

    @ApiOperation(value = "《下拉列表》获取保护点名称")
    @GetMapping("/dropDownList/listForProtectPoints")
    Result<List<DropDownVo>> listForProtectPoints(@RequestParam(value = "provinceCode", required = false) @ApiParam(name = "provinceCode", value = "省级区域编码,如果传递的是空字符串，则取所有数据") String provinceCode);
    @ApiOperation(value = "《下拉列表》获取农业野生植物物种")
    @GetMapping("/dropDownList/listForSpecies")
    Result<List<DropDownWithLatinVo>> listForSpecies();

    @ApiOperation(value = "《下拉列表》获取生境类型")
    @GetMapping("/dropDownList/listForHabitatType")
    Result<List<DropDownVo>> listForHabitatType();

    @ApiOperation(value = "《下拉列表》获取产业利用")
    @GetMapping("/dropDownList/listForIndustrial")
    Result<List<DropDownVo>> listForIndustrial();

    @ApiOperation(value = "《下拉列表》获取用途")
    @GetMapping("/dropDownList/listForPurpose")
    Result<List<DropDownVo>> listForPurpose(@RequestParam(value = "industrialId",required = false) @ApiParam(name = "industrialId", value = "产业利用Id,下拉列表中的Id") String industrialId);

    @ApiOperation(value = "《下拉列表》获取具体内容")
    @GetMapping("/dropDownList/listForContent")
    Result<List<DropDownVo>> listForContent(@RequestParam(value = "purposeId") @ApiParam(name = "purposeId", value = "用途Id,用途下拉列表中的Id", required = true) String purposeId);

    @ApiOperation(value = "《下拉列表》获取价值列表")
    @GetMapping("/dropDownList/listForCost")
    Result<List<DropDownVo>> listForCost(@RequestParam(value = "contentId") @ApiParam(name = "contentId", value = "具体内容Id,具体内容下拉列表中的Id", required = true) String contentId);
    @ApiOperation(value = "《下拉列表》获取采集单位")
    @GetMapping("/dropDownList/listForCollectDept")
    Result<List<DropDownWithOther>> listForCollectDept();
}
