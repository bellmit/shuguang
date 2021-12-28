package com.sofn.agsjdm.web;

import com.sofn.agsjdm.service.AnalysisService;
import com.sofn.agsjdm.vo.AnalysisVo;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @Date: 2020-04-16 15:08
 */
@Api(tags = "监测预警分析接口")
@RestController
@RequestMapping("/as")
public class AnalysisController {
    @Autowired
  private AnalysisService analysisService;

    @ApiOperation(value = "根据条件查询")
    @GetMapping("/listByParam")
    public Result<AnalysisVo> listByParam(@ApiParam(value = "年份",required = true) @RequestParam String year,
                                          @ApiParam(value = "保护点id",required = true) @RequestParam String wetlandId,
                                          @ApiParam(value = "物种名",required = false) @RequestParam(required = false) String chineseName,
                                          @ApiParam(value = "检测类型",required = true) @RequestParam String testType,
                                          @ApiParam(value = "参数选择（即指标分类1:污染面积 2种群数量）",required = true) @RequestParam String indexId) {
        return Result.ok(analysisService.getMapResult(getQueryMap(year,chineseName,wetlandId,testType,indexId)));
    }

    private Map<String, Object> getQueryMap(String year,String chineseName,String wetlandId, String testType, String indexId) {
        Map map = new HashMap<String, Object>(4);
        map.put("year", year);
        map.put("wetlandId", wetlandId);
        map.put("chineseName", chineseName);
        map.put("testType", testType);
        map.put("indexId", indexId);
        return map;
    }

//    @ApiOperation(value = "根据条件查询")
//    @GetMapping("/list")
//    public Result<AnalysisVo> list(@ApiParam(value = "年份",required = true) @RequestParam String year,
//                                          @ApiParam(value = "保护点id",required = true) @RequestParam String wetlandId,
//                                          @ApiParam(value = "物种名",required = false) @RequestParam(required = false) String chineseName,
//                                          @ApiParam(value = "检测类型",required = true) @RequestParam String testType,
//                                          @ApiParam(value = "参数选择（即指标分类1:污染面积 2种群数量）",required = true) @RequestParam String indexId) {
//        return Result.ok(analysisService.getMapResult(getQueryMap(year,chineseName,wetlandId,testType,indexId)));
//    }
}
