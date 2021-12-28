package com.sofn.agpjyz.web;

import com.sofn.agpjyz.model.ThreatFactor;
import com.sofn.agpjyz.service.ThreatFactorCollectService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "威胁因素基础信息收集模块接口")
@RestController
@RequestMapping("/tfc")
public class ThreatFactorCollectController extends BaseController {

    @Autowired
    private ThreatFactorCollectService tfcService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agpjyz:tfc:create")
    public Result<ThreatFactor> save(
            @Validated @RequestBody ThreatFactor threatFactor, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(tfcService.save(threatFactor));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agpjyz:tfc:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        tfcService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agpjyz:tfc:view")
    public Result<ThreatFactor> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(tfcService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agpjyz:tfc:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody ThreatFactor threatFactor, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        tfcService.update(threatFactor);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:tfc:menu")
    public Result<PageUtils<ThreatFactor>> listPage(
            @ApiParam("保护点ID") @RequestParam(required = false) String protectId,
            @ApiParam("采挖受损面积") @RequestParam(required = false) String excavation,
            @ApiParam("放牧受损面积") @RequestParam(required = false) String graze,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(tfcService.listPage(
                this.getQueryMap(protectId, excavation, graze, startTime, endTime), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agpjyz:tfc:export")
    public void export(
            @ApiParam("保护点ID") @RequestParam(required = false) String protectId,
            @ApiParam("采挖受损面积") @RequestParam(required = false) String excavation,
            @ApiParam("放牧受损面积") @RequestParam(required = false) String graze,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        tfcService.export(this.getQueryMap(protectId, excavation, graze, startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String protectId, String excavation, String graze
            , String startTime, String endTime) {
        Map map = new HashMap<String, Object>(5);
        map.put("protectId", protectId);
        map.put("excavation", excavation);
        map.put("graze", graze);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
