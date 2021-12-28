package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Peritoneum;
import com.sofn.fdpi.service.PeritoneumService;
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
 * @Date: 2020-07-14 11:22
 */
@Slf4j
@Api(value = "APP_濒管办进出口相关接口",tags = "APP_濒管办进出口相关接口")
@RequestMapping("/app/peritoneum")
@RestController
public class PeritoneumAppController  extends BaseController {
    @Autowired
    private PeritoneumService peritoneumService;
    @RequiresPermissions("fdpi:peritoneum:create")
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Peritoneum peritoneum, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        peritoneumService.save(peritoneum);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:peritoneum:delete")
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        peritoneumService.del(id);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:peritoneum:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<Peritoneum> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(peritoneumService.get(id));
    }
    @RequiresPermissions("fdpi:peritoneum:update")
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result update(
            @Validated @RequestBody Peritoneum peritoneum, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        peritoneumService.update(peritoneum);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:peritoneum:query")
    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<Peritoneum>> listPage(
            @ApiParam("证号") @RequestParam(required = false) String certificateNo,
            @ApiParam("品种") @RequestParam(required = false) String speciesName,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        Map map = Maps.newHashMap();
        map.put("certificateNo",certificateNo);
        map.put("speciesName",speciesName);
        return Result.ok(peritoneumService.listPage(map, pageNo, pageSize));
    }
}
