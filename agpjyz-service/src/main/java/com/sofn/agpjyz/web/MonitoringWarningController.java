package com.sofn.agpjyz.web;

import com.sofn.agpjyz.model.MonitoringWarning;
import com.sofn.agpjyz.service.MonitoringWarningService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "监测预警模型构建与管理模块接口")
@RestController
@RequestMapping("/mw")
public class MonitoringWarningController extends BaseController {

    @Autowired
    private MonitoringWarningService mwService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agpjyz:mw:create")
    public Result<MonitoringWarning> save(
            @Validated @RequestBody MonitoringWarning monitoringWarning, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(mwService.save(monitoringWarning));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agpjyz:mw:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        mwService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agpjyz:mw:view")
    public Result<MonitoringWarning> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(mwService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agpjyz:mw:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody MonitoringWarning monitoringWarning, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        mwService.update(monitoringWarning);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:mw:menu")
    public Result<PageUtils<MonitoringWarning>> listPage(
            @ApiParam("重点保护植物ID") @RequestParam(required = false) String plantId,
            @ApiParam("监测点ID") @RequestParam(required = false) String protectId,
            @ApiParam("指标分类ID 1:种群数量2：受损面积") @RequestParam(required = false) String indexId,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(mwService.listPage(this.getQueryMap(plantId, protectId, indexId), pageNo, pageSize));
    }

    private Map<String, Object> getQueryMap(String plantId, String protectId, String indexId) {
        Map map = new HashMap<String, Object>(3);
        map.put("plantId", plantId);
        map.put("protectId", protectId);
        map.put("indexId", indexId);
        return map;
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agpjyz:mw:export")
    public void export(
            @ApiParam("重点保护植物名称") @RequestParam(required = false) String plantValue,
            @ApiParam("监测点名称") @RequestParam(required = false) String protectValue,
            @ApiParam("指标分类名称") @RequestParam(required = false) String indexValue,
            HttpServletResponse response) {
        mwService.export(this.getQueryMap(plantValue, protectValue, indexValue), response);
    }

}
