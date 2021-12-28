package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.WaterQuality;
import com.sofn.agsjdm.service.WaterQualityService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "水质监测信息采集模块接口")
@RestController
@RequestMapping("/wq")
public class WaterQualityController extends BaseController {

    @Autowired
    private WaterQualityService wqService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agsjdm:wq:create")
    public Result<WaterQuality> save(
            @Validated @RequestBody WaterQuality waterQuality, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(wqService.save(waterQuality));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agsjdm:wq:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        wqService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agsjdm:wq:view")
    public Result<WaterQuality> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(wqService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agsjdm:wq:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody WaterQuality waterQuality, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        wqService.update(waterQuality);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<WaterQuality>> listPage(
            @ApiParam("湿地区名称Id") @RequestParam(required = false) String wetlandId,
            @ApiParam("蓄水量") @RequestParam(required = false) Double waterDemand,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(wqService.listPage(getQueryMap(wetlandId, waterDemand, startTime, endTime), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agsjdm:wq:export")
    public void export(
            @ApiParam("湿地区名称Id") @RequestParam(required = false) String wetlandId,
            @ApiParam("蓄水量") @RequestParam(required = false) Double waterDemand,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        wqService.export(getQueryMap(wetlandId, waterDemand, startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String wetlandId, Double waterDemand, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(4);
        map.put("wetlandId", wetlandId);
        map.put("waterDemand", waterDemand);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
