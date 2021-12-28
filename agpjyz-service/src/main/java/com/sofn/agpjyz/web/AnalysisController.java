package com.sofn.agpjyz.web;

import com.sofn.agpjyz.service.AnalysisService;
import com.sofn.agpjyz.service.TargetSpeciesCollectService;
import com.sofn.agpjyz.vo.AnalysisVo;
import com.sofn.agpjyz.vo.TargetSpeciesVo;
import com.sofn.agpjyz.vo.YearVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-13 9:02
 */

@Api(tags = "监测预警分析接口")
@RestController
@RequestMapping("/as")
public class AnalysisController {
    @Autowired
    private TargetSpeciesCollectService tscService;
    @Autowired
    private AnalysisService analysisService;

    @ApiOperation(value = "根据物种名进行模糊查询")
    @GetMapping("/listByName")
    public Result<List<TargetSpeciesVo>> listPage(
            @ApiParam(value = "目标物种名称", required = true) @RequestParam String specValue) {
        return Result.ok(tscService.listByName(specValue));
    }
    @ApiOperation(value = "下拉获取年份")
    @GetMapping("/year")
    public Result<List<YearVo>> listPage(
            ) {
        return Result.ok(tscService.getYear());
    }
//    @RequiresPermissions("agpjyz:as:menu")
    @ApiOperation(value = "根据条件查询")
    @GetMapping("/listByParam")
    public Result<AnalysisVo> listByParam(@ApiParam(value = "年份",required = true) @RequestParam String year,
                                          @ApiParam(value = "保护点id",required = true) @RequestParam String protectId,
                                          @ApiParam(value = "目标物种id",required = true) @RequestParam String specId,
                                          @ApiParam(value = "检测类型",required = true) @RequestParam String testType,
                                          @ApiParam(value = "参数选择（即指标分类id）",required = true) @RequestParam String indexId) {
        return Result.ok(analysisService.getMapResult(getQueryMap(year,protectId,specId,testType,indexId)));
    }

    private Map<String, Object> getQueryMap(String year,String protectId, String specId, String testType,String indexId) {
        Map map = new HashMap<String, Object>(5);
        map.put("year", year);
        map.put("protectId", protectId);
        map.put("specId", specId);
        map.put("testType", testType);
        map.put("indexId", indexId);
        return map;
    }
}
