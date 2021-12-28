package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Customs;
import com.sofn.fdpi.service.CustomsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-07-14 11:24
 */
@Slf4j
@Api(value = "APP_海关进出口相关接口",tags = "APP_海关进出口相关接口")
@RequestMapping("/app/customs")
@RestController
public class CustomsAppController extends BaseController {
    @Autowired
    private CustomsService customsService;
    @RequiresPermissions("fdpi:customs:create")
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Customs peritoneum, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        customsService.save(peritoneum);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:customs:delete")
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        customsService.del(id);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:customs:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<Customs> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(customsService.get(id));
    }
    @RequiresPermissions("fdpi:customs:update")
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result update(
            @Validated @RequestBody Customs peritoneum, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        customsService.update(peritoneum);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:customs:query")
    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<Customs>> listPage(
            @ApiParam("报告单号") @RequestParam(required = false) String customsNumber,
            @ApiParam("货物") @RequestParam(required = false) String speciesName,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        Map map = Maps.newHashMap();
        map.put("customsNumber",customsNumber);
        map.put("speciesName",speciesName);
        return Result.ok(customsService.listPage(map, pageNo, pageSize));
    }
}

