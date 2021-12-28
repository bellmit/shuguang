package com.sofn.ahhrm.web;

import com.sofn.ahhrm.model.IndexPar;
import com.sofn.ahhrm.service.IndexParService;
import com.sofn.ahhrm.util.RedisUserUtil;
import com.sofn.ahhrm.vo.DropDownVo;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-28 14:08
 */

@Api(tags = "指标参数接口")
@RestController
@RequestMapping("/ip")
public class IndexParController  extends BaseController {
    @Autowired
    private IndexParService indexParService;

    @RequiresPermissions("ahhrm:parameter:create")
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody IndexPar IndexPar, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        indexParService.save(IndexPar);
        return Result.ok();
    }

    @RequiresPermissions("ahhrm:parameter:delete")
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        indexParService.delete(id);
        return Result.ok();
    }
   @RequiresPermissions("ahhrm:parameter:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<IndexPar> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(indexParService.get(id));
    }
    @RequiresPermissions("ahhrm:parameter:update")
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result insertSignboardProcess(
            @Validated @RequestBody IndexPar monitoringWarning, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        indexParService.update(monitoringWarning);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:mw:menu")
    public Result<PageUtils<IndexPar>> listPage(
            @ApiParam("指标参数") @RequestParam(required = false) String value,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
         Map map=new HashMap();
         map.put("value",value);
         return Result.ok(indexParService.listPage( map,pageNo, pageSize));
    }

    @ApiOperation(value = "(下拉列表)指标参数")
    @GetMapping("/listName")
    public Result<List<DropDownVo>> listName() {
        return Result.ok(indexParService.listName());
    }

}
