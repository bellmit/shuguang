package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.TargetClassify;
import com.sofn.agsjdm.service.TargetClassifyService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Api(tags = "指标分类服务模块接口")
@RestController
@RequestMapping("/tc")
public class TargetClassifyController extends BaseController {

    @Autowired
    private TargetClassifyService tcService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agsjdm:tc:create")
    public Result<TargetClassify> save(
            @Validated @RequestBody TargetClassify targetClassify, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(tcService.save(targetClassify));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agsjdm:tc:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        tcService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agsjdm:tc:view")
    public Result<TargetClassify> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(tcService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agsjdm:tc:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody TargetClassify targetClassify, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        tcService.update(targetClassify);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<TargetClassify>> listPage(
            @ApiParam("指标分类") @RequestParam(required = false) String targetVal,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(tcService.listPage(targetVal, pageNo, pageSize));
    }

    @ApiOperation(value = "指标分类下拉接口,用于本系统预警模型构建与管理模块")
    @GetMapping("/list")
    public Result<List<TargetClassify>> listForPointCollect() {
        return Result.ok(tcService.list(null));
    }
}
