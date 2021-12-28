package com.sofn.ahhrm.web;

import com.sofn.ahhrm.enums.ProcessEnum;
import com.sofn.ahhrm.service.BaseinfoService;
import com.sofn.ahhrm.vo.BaseinfoForm;
import com.sofn.ahhrm.vo.BaseinfoVo;
import com.sofn.ahhrm.vo.DropDownVo;
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
import java.util.List;
import java.util.Map;

@Api(tags = "基础信息采集、上报模块接口")
@RestController
@RequestMapping("/baseinfo")
public class BaseinfoController extends BaseController {

    @Autowired
    private BaseinfoService baseinfoService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("ahhrm:baseinfo:create")
    public Result<BaseinfoVo> save(
            @Validated @RequestBody BaseinfoForm baseinfoForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(baseinfoService.save(baseinfoForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("ahhrm:baseinfo:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        baseinfoService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("ahhrm:baseinfo:view")
    public Result<BaseinfoVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(baseinfoService.get(id));
    }

    @ApiOperation(value = "获取最后一次填报基础信息")
    @GetMapping("/getLastCommit")
    public Result<BaseinfoVo> getLastCommit() {
        return Result.ok(baseinfoService.getLastCommit());
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("ahhrm:baseinfo:update")
    public Result update(
            @Validated @RequestBody BaseinfoForm baseinfoForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        baseinfoService.update(baseinfoForm);
        return Result.ok();
    }

    @ApiOperation(value = "根据当前用户获取对应状态列表")
    @GetMapping("/getStatus")
    public Result<List<DropDownVo>> getStatus() {
        return Result.ok(ProcessEnum.getStatus());
    }

    @ApiOperation(value = "撤回")
    @PutMapping("/cancel")
    @RequiresPermissions("ahhrm:baseinfo:cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        baseinfoService.cancel(id);
        return Result.ok();
    }

    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
    @RequiresPermissions("ahhrm:baseinfo:audit")
    public Result auditPass(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String auditOpinion) {
        if (auditOpinion.length() > 100) {
            return Result.error("审核意见不能超过100字");
        }
        baseinfoService.auditPass(id, auditOpinion);
        return Result.ok();
    }

    @ApiOperation(value = "审核(退回)")
    @PutMapping("/auditReturn")
    @RequiresPermissions("ahhrm:baseinfo:audit")
    public Result auditReturn(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String auditOpinion) {
        if (auditOpinion.length() > 100) {
            return Result.error("审核意见不能超过100字");
        }
        baseinfoService.auditReturn(id, auditOpinion);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<BaseinfoVo>> listPage(
            @ApiParam("监测点名称") @RequestParam(required = false) String pointName,
            @ApiParam("所属类型") @RequestParam(required = false) String type,
//            @ApiParam("品种名称ID") @RequestParam(required = false) String variety,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("状态") @RequestParam(required = false) String status,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(baseinfoService.listPage(getQueryMap(pointName, type, startTime, endTime, status),
                pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("ahhrm:baseinfo:export")
    public void export(
            @ApiParam("监测点名称") @RequestParam(required = false) String pointName,
            @ApiParam("所属类型") @RequestParam(required = false) String type,
//            @ApiParam("品种名称ID") @RequestParam(required = false) String variety,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("状态") @RequestParam(required = false) String status,
            HttpServletResponse response) {
        baseinfoService.export(getQueryMap(pointName, type, startTime, endTime, status), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String pointName, String type,
                                            String startTime, String endTime, String status) {
        Map map = new HashMap<String, Object>(5);
        map.put("pointName", pointName);
        map.put("type", type);
//        map.put("variety", variety);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        map.put("status", status);
        return map;
    }
}
