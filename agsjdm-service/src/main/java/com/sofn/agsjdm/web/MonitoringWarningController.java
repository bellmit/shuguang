package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.MonitoringWarning;
import com.sofn.agsjdm.service.MonitoringWarningService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 11:09
 */
@Api(tags = "监测预警模型构建与管理模块接口")
@RestController
@RequestMapping("/mw")
public class MonitoringWarningController extends BaseController {

    @Autowired
    private MonitoringWarningService mwService;
    @RequiresPermissions("agsjdm:mw:create")
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result<MonitoringWarning> save(
            @Validated @RequestBody MonitoringWarning monitoringWarning, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        if (Objects.equals("生物分布监测", monitoringWarning.getTestType()) && StringUtils.isBlank(monitoringWarning.getChineseName())) {
            return Result.error("检测类型为生物分布监测时：需要传中文名");
        }

        return Result.ok(mwService.save(monitoringWarning));
    }
    @RequiresPermissions("agsjdm:mw:delete")
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        mwService.delete(id);
        return Result.ok();
    }
    @RequiresPermissions("agsjdm:mw:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<MonitoringWarning> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(mwService.get(id));
    }
    @RequiresPermissions("agsjdm:mw:update")
    @ApiOperation(value = "修改")
    @PutMapping("/update")
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
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("指标分类ID") @RequestParam(required = false) String indexId,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(mwService.listPage(this.getQueryMap(wetlandId, indexId), pageNo, pageSize));
    }

    private Map<String, Object> getQueryMap( String wetlandId, String indexId) {
        Map map = new HashMap<String, Object>(3);
        map.put("wetlandId", wetlandId);
        map.put("indexId", indexId);
        return map;
    }
    @RequiresPermissions("agsjdm:mw:export")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    public void export(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("指标分类ID") @RequestParam(required = false) String indexId,
            HttpServletResponse response) {
        mwService.export(this.getQueryMap(wetlandId,indexId), response);
    }

}