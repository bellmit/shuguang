package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.service.SturgeonProcessService;
import com.sofn.fdpi.service.SturgeonService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "鲟鱼子酱标识申请相关接口", tags = "鲟鱼子酱标识申请相关接口")
@RestController
@RequestMapping(value = "/sturgeon")
public class SturgeonController extends BaseController {

    @Resource
    private SturgeonService sturgeonService;

    @Resource
    private SturgeonProcessService sturgeonProcessService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("fdpi:sturgeonApply:create")
    public Result<SturgeonVo> save(@Validated @RequestBody SturgeonForm sturgeonForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(sturgeonService.save(sturgeonForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("fdpi:sturgeonApply:delete")
    public Result delete(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("fdpi:sturgeonApply:update")
    public Result update(@Validated @RequestBody SturgeonForm sturgeonForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sturgeonService.update(sturgeonForm);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions(value = {"fdpi:sturgeonApply:view", "fdpi:sturgeonApprove:view",
            "fdpi:citesAudit:view"}, logical = Logical.OR)
    public Result<SturgeonVo> get(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(sturgeonService.get(id));
    }

    @ApiOperation(value = "流程记录")
    @GetMapping("/getProcess")
//    @RequiresPermissions(value = {"fdpi:signboardApply:view", "fdpi:signboardApprove:view"}, logical = Logical.OR)
    public Result<SturgeonProcessVo> getProcess(
            @ApiParam(name = "id", value = "申请ID", required = true) @RequestParam(value = "id") String id) {
        List<SturgeonProcessVo> list = Constants.WORKFLOW.equals(BoolUtils.N) ?
                sturgeonProcessService.listSturgeonProcess(id, "1") : sturgeonService.listSturgeonProcess(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "上报(列表页使用)")
    @PutMapping("/report")
    @RequiresPermissions("fdpi:sturgeonApply:report")
    public Result report(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonService.report(id);
        return Result.ok();
    }

    @ApiOperation(value = "撤回")
    @PutMapping("/cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonService.cancel(id);
        return Result.ok();
    }


    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
//    @RequiresPermissions("fdpi:sturgeonApprove:approve")
    public Result auditPass(@ApiParam(value = "主键id", required = true) @RequestParam() String id,
                            @ApiParam("是否第三方打印企业打印 Y/N") @RequestParam(required = false) String thirdPrint) {
        sturgeonService.auditPass(id, thirdPrint);
        return Result.ok();
    }

    @ApiOperation(value = "审核(退回)")
    @PutMapping("/auditReturn")
//    @RequiresPermissions("fdpi:sturgeonApprove:back")
    public Result auditReturn(@ApiParam(value = "主键id", required = true) @RequestParam() String id,
                              @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        if (opinion.length() > 50) {
            return Result.error("审核意见不能超过50字");
        }
        sturgeonService.auditReturn(id, opinion);
        return Result.ok();
    }

    @ApiOperation(value = "获取证书编号下拉数据")
    @GetMapping("/getCredentials")
    public Result<List<SelectVo>> getCredentials() {
        return Result.ok(sturgeonService.getCredentials());
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    @SofnLog("分页查询")
    public Result<PageUtils<SturgeonVo>> listPage(
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("进出口证书编号") @RequestParam(required = false) String credentials,
            @ApiParam("贸易类型") @RequestParam(required = false) String trade,
            @ApiParam("申请品种") @RequestParam(required = false) String variety,
            @ApiParam("直属审核人员") @RequestParam(required = false) String auditer,
            @ApiParam(value = "申请类型1国外2国内") @RequestParam(required = false) String applyType,
            @ApiParam("申请日期起(yyyy-MM-dd)") @RequestParam(required = false) String applyDateS,
            @ApiParam("申请日期止(yyyy-MM-dd)") @RequestParam(required = false) String applyDateE,
            @ApiParam("审核状态 1未上报2已上报(待审核)3已退回4已通过5撤回") @RequestParam(required = false) String status,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"applyDateS", "auditer", "applyDateE", "compName", "credentials", "trade", "status", "applyType", "variety"};
        Object[] vals = {applyDateS, auditer, applyDateE, compName, credentials, trade, status, applyType, variety};
        if (StringUtils.hasText(applyDateS))
            vals[0] = applyDateS + " 00:00:00";
        if (StringUtils.hasText(applyDateE))
            vals[1] = applyDateE + " 23:59:59";
        return Result.ok(sturgeonService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }


    @ApiOperation(value = "获取可用鱼子酱列表")
    @GetMapping("/getCitesList")
    public Result<List<SelectVo>> getCitesList() {
        return Result.ok(sturgeonService.getCitesList());
    }
}
