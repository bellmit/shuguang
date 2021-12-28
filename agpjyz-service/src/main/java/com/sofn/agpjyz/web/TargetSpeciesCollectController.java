package com.sofn.agpjyz.web;

import com.sofn.agpjyz.service.TargetSpeciesCollectService;
import com.sofn.agpjyz.vo.TargetSpeciesForm;
import com.sofn.agpjyz.vo.TargetSpeciesVo;
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

@Api(tags = "目标物种基础信息收集模块接口")
@RestController
@RequestMapping("/tsc")
public class TargetSpeciesCollectController extends BaseController {

    @Autowired
    private TargetSpeciesCollectService tscService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agpjyz:tsc:create")
    public Result<TargetSpeciesVo> save(
            @Validated @RequestBody TargetSpeciesForm targetSpeciesForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(tscService.save(targetSpeciesForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agpjyz:tsc:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        tscService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agpjyz:tsc:view")
    public Result<TargetSpeciesVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(tscService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agpjyz:tsc:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody TargetSpeciesForm targetSpeciesForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        tscService.update(targetSpeciesForm);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:tsc:menu")
    public Result<PageUtils<TargetSpeciesVo>> listPage(
            @ApiParam("保护点ID") @RequestParam(required = false) String protectId,
            @ApiParam("目标物种ID") @RequestParam(required = false) String specId,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(tscService.listPage(getQueryMap(protectId, specId, startTime, endTime), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agpjyz:tsc:export")
    public void export(
            @ApiParam("保护点ID") @RequestParam(required = false) String protectId,
            @ApiParam("目标物种ID") @RequestParam(required = false) String specId,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        tscService.export(getQueryMap(protectId, specId, startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String protectId, String specId, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(4);
        map.put("protectId", protectId);
        map.put("specId", specId);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
