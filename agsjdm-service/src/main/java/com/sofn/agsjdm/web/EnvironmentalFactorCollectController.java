package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.EnvironmentalFactor;
import com.sofn.agsjdm.service.EnvironmentalFactorCollectService;
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

@Api(tags = "环境因子监测信息采集模块接口")
@RestController
@RequestMapping("/efc")
public class EnvironmentalFactorCollectController extends BaseController {

    @Autowired
    private EnvironmentalFactorCollectService efcService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agsjdm:efc:create")
    public Result<EnvironmentalFactor> save(
            @Validated @RequestBody EnvironmentalFactor environmentalFactor, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(efcService.save(environmentalFactor));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agsjdm:efc:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        efcService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agsjdm:efc:view")
    public Result<EnvironmentalFactor> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(efcService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agsjdm:efc:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody EnvironmentalFactor environmentalFactor, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        efcService.update(environmentalFactor);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<EnvironmentalFactor>> listPage(
            @ApiParam("湿地区名称Id") @RequestParam(required = false) String wetlandId,
            @ApiParam("气温") @RequestParam(required = false) String airTem,
            @ApiParam("积温") @RequestParam(required = false) String accTem,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(efcService.listPage(
                this.getQueryMap(wetlandId, airTem, accTem, startTime, endTime), pageNo, pageSize));
    }

    private Map<String, Object> getQueryMap(String wetlandId, String airTem, String accTem
            , String startTime, String endTime) {
        Map map = new HashMap<String, Object>(5);
        map.put("wetlandId", wetlandId);
        map.put("airTem", airTem);
        map.put("accTem", accTem);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agsjdm:efc:export")
    public void export(
            @ApiParam("湿地区名称Id") @RequestParam(required = false) String wetlandId,
            @ApiParam("气温") @RequestParam(required = false) String airTem,
            @ApiParam("积温") @RequestParam(required = false) String accTem,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        efcService.export(this.getQueryMap(wetlandId, airTem, accTem, startTime, endTime), response);
    }

}
