package com.sofn.ahhrm.web;

import com.baomidou.mybatisplus.extension.api.R;
import com.sofn.ahhrm.model.ThresholdSub;
import com.sofn.ahhrm.service.AnalysisService;
import com.sofn.ahhrm.vo.AnalysisVo;
import com.sofn.ahhrm.vo.EarlyWarning;
import com.sofn.ahhrm.vo.MonitoringPointVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
 * @Date: 2020-04-26 14:05
 */
@Api(tags = "检测预警分析接口")
@RestController
@RequestMapping("/as")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @ApiOperation(value = "默认查询")
    @GetMapping("/list")
    public Result<List<MonitoringPointVo>> list(@ApiParam(value = "年份",required = false) @RequestParam(required = false) String year,
                                                @ApiParam(value = "品种名称",required = false) @RequestParam(required = false) String variety,
                                                @ApiParam(value = "参数选择（群体数量）传不传都行后台没用",required = false) @RequestParam(required = false)  String amount) {
        Map map=new HashMap<>();
        map.put("year",year);
        map.put("variety",variety);
        map.put("amount",amount);
        return Result.ok(analysisService.getList(map));
    }
    @ApiOperation(value = "根据检测点名称查询详细信息")
    @GetMapping("/getOne")
    public Result<EarlyWarning> getResult(@ApiParam(value = "监测点名称",required = true) @RequestParam() String pointName){
        EarlyWarning result = analysisService.getMapResult(pointName);
        if (result!=null){
            List<AnalysisVo> analysis = result.getAnalysis();
            if (!CollectionUtils.isEmpty(analysis)){
                analysis.forEach(o->{
                    NumberTograde(o);
                });
            }
            return Result.ok(result);
        }else{
            throw  new SofnException("存在未设置阈值的品种，请先设置品种监控阈值");
        }

    }


    private AnalysisVo NumberTograde(AnalysisVo th){
        switch (th.getGrade()){
            case "0":
                th.setGrade("无危险");
                break;
            case "1":
                th.setGrade("濒危");
                break;
            case "2":
                th.setGrade("濒临灭绝");
                break;
            case "3":
                th.setGrade("灭绝");
                break;
            default:
                break;
        }
        return  th;
    }
}
