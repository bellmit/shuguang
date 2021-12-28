package com.sofn.ahhrm.web;

import com.sofn.ahhrm.model.Threshold;
import com.sofn.ahhrm.service.ThresholdService;
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
 * @Date: 2020-04-27 9:29
 */
@Api(tags = "阈值设置接口")
@RestController
@RequestMapping("/tc")
public class ThresholdController extends BaseController {
    @Autowired
    private ThresholdService thresholdService;
    @RequiresPermissions("ahhrm:threshold:create")
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Threshold threshold, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        thresholdService.save(threshold);
        return Result.ok();
    }

    @RequiresPermissions("ahhrm:threshold:delete")
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        thresholdService.delete(id);
        return Result.ok();
    }

    @RequiresPermissions("ahhrm:threshold:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<Threshold> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(thresholdService.getOne(id));
    }

    @RequiresPermissions("ahhrm:threshold:update")
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result insertSignboardProcess(
            @Validated @RequestBody Threshold monitoringWarning, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        thresholdService.update(monitoringWarning);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<Threshold>> listPage(
            @ApiParam("监测点名称") @RequestParam(required = false) String pointName,
            @ApiParam("品种") @RequestParam(required = false) String variety,
            @ApiParam("指标参数") @RequestParam(required = false) String indexPar,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(thresholdService.listPage(this.getQueryMap(pointName, variety,indexPar), pageNo, pageSize));
    }

    @ApiOperation(value = "获取可添加阈值的品种名称(下拉列表)")
    @GetMapping("/listName")
    public Result<List<DropDownVo>> listName() {
        return Result.ok(thresholdService.listName());
    }

    private Map<String, Object> getQueryMap(String pointName, String variety,String indexPar) {
        Map map = new HashMap<String, Object>(3);
        map.put("pointName", pointName);
        map.put("variety", variety);
        map.put("indexPar", indexPar);
        return map;
    }


}
