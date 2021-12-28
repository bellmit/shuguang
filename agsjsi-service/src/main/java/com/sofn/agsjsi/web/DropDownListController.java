package com.sofn.agsjsi.web;


import com.sofn.agsjsi.service.WetExamplePointCollectService;
import com.sofn.agsjsi.vo.DropDownVo;
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

/**
 * @Description: 下拉列表管理相关接口(农业湿地示范点系统)
 * @Author: WXY
 * @Date: 2020-4-14 10:54:06
 */
@Api(value = "下拉列表管理相关接口(农业湿地示范点系统)", tags = "下拉列表管理相关接口(农业湿地示范点系统)")
@Slf4j
@RestController
@RequestMapping("/dropDownManage")
public class DropDownListController {
    @Autowired
    private WetExamplePointCollectService collectService;

    @SofnLog("《下拉列表》获取农业湿地示范点收集列表")
    @ApiOperation(value = "《下拉列表》获取农业湿地示范点收集列表")
    @GetMapping("/listForPointCollect")
    public Result<List<DropDownVo>> listForPointCollect(@ApiParam(name="lastRegionCode",value = "最后一位行政区划编码") @RequestParam(value = "lastRegionCode",required = false) String lastRegionCode) {
        List<DropDownVo> dropDownList = collectService.listForSelect(lastRegionCode);
        return Result.ok(dropDownList);
    }
}
