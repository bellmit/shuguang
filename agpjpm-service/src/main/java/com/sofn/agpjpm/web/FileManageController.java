package com.sofn.agpjpm.web;



import com.google.common.collect.Maps;
import com.sofn.agpjpm.model.FileManage;
import com.sofn.agpjpm.service.FileManageService;
import com.sofn.agpjpm.vo.FileForm;
import com.sofn.agpjpm.vo.ServeyListVo;
import com.sofn.agpjpm.vo.SurveyForm;
import com.sofn.agpjpm.vo.SurveyVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "文件管理")
@RestController
@RequestMapping("/file")
public class FileManageController extends BaseController {
    @Autowired
    private FileManageService fileManageService;
    @RequiresPermissions(value = {"agpjpm:file:create","agpjyz:file:create"},logical = Logical.OR)
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody FileForm surveyForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        fileManageService.insert(surveyForm);
        return Result.ok();
    }
    @RequiresPermissions(value = {"agpjpm:file:view","agpjyz:file:view"},logical = Logical.OR)
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<FileManage> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(fileManageService.get(id));
    }
    @RequiresPermissions(value = {"agpjpm:file:update","agpjyz:file:update"},logical = Logical.OR)
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result update(
            @Validated @RequestBody FileForm surveyForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        fileManageService.update(surveyForm);
        return Result.ok();
    }
    @RequiresPermissions(value = {"agpjpm:file:delete","agpjyz:file:delete"},logical = Logical.OR)
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        fileManageService.del(id);
        return Result.ok();
    }
    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<FileManage>> listPage(
            @ApiParam("文件名称") @RequestParam(required = false) String fileName,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        Map map= Maps.newHashMap();
        map.put("fileName",fileName);
        return Result.ok(fileManageService.listPage(map, pageNo, pageSize));
    }
}
