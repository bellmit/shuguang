package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.service.SignboardApplyService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:08
 **/
@Slf4j
@Api(value = "APP_标识申请(配发/补发/换发/注销)相关接口", tags = "APP_标识申请(配发/补发/换发/注销)相关接口")
@RestController
@RequestMapping(value = "/app/signboardApply")
public class SignboardApplyAppController extends BaseController {

    @Autowired
    private SignboardApplyService signboardApplyService;

    @ApiOperation(value = "标识申请新增")
    @PostMapping("/save")
    @SofnLog("标识申请新增")
    public Result save(@Validated @RequestBody SignboardApplyForm signboardApplyForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        SignboardApply entity = signboardApplyService.insertSignboardApply(signboardApplyForm);
        return Result.ok(entity.getId());
    }

    @ApiOperation(value = "标识申请修改")
    @PutMapping("/update")
    @SofnLog("标识申请修改")
    public Result update(@Validated @RequestBody SignboardApplyForm signboardApplyForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        signboardApplyService.updateSignboardApply(signboardApplyForm);
        return Result.ok();
    }

    @ApiOperation(value = "标识申请删除")
    @DeleteMapping("/delete")
    @SofnLog("标识申请删除")
    public Result delete(@ApiParam(name = "id", value = "标识ID", required = true) @RequestParam(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        signboardApplyService.deleteLogic(id);
        return Result.ok();
    }

    @ApiOperation(value = "标识申请审核")
    @PostMapping("/audit")
    @SofnLog("标识申请审核")
    public Result audit(@Validated @RequestBody SignboardProcessForm signboardProcessForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        signboardApplyService.audit(signboardProcessForm);
        return Result.ok();
    }

    @ApiOperation(value = "标识核发")
    @PutMapping("/allotment")
    public Result allotment(@Validated @RequestBody AllotmentForm allotmentForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        signboardApplyService.allotment(allotmentForm);
        return Result.ok();
    }

    @ApiOperation(value = "上报(列表页使用)")
    @PutMapping("/report")
//    @RequiresPermissions("fdpi:signboardApply:report")
    public Result report(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        signboardApplyService.report(id);
        return Result.ok();
    }

    @ApiOperation(value = "标识申请详情")
    @GetMapping("/get")
    @SofnLog("标识申请详情")
    public Result<SignboardApplyVo> get(
            @ApiParam(name = "id", value = "标识申请ID", required = true) @RequestParam(value = "id") String id) {
        return Result.ok(signboardApplyService.getSignboardApply(id));
    }

    @ApiOperation(value = "获取标识信息")
    @GetMapping("/getSignboardApplyList")
    public Result<SignboardApplyListVo> getSignboardApplyList(
            @ApiParam(name = "id", value = "标识申请ID", required = true) @RequestParam(value = "id") String id,
            @ApiParam("标识编码") @RequestParam(required = false) String code,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        return Result.ok(signboardApplyService.getSignboardApplyList(id, code, pageNo, pageSize));
    }

    @ApiOperation(value = "标识申请分页查询")
    @GetMapping("/listPage")
    @SofnLog("标识申请分页查询")
    public Result<PageUtils<SignboardApplyVo>> listPage(
            @ApiParam("申请日期起(yyyy-MM-dd)") @RequestParam(required = false) String applyDateS,
            @ApiParam("申请日期止(yyyy-MM-dd)") @RequestParam(required = false) String applyDateE,
            @ApiParam("申请单号") @RequestParam(required = false) String applyCode,
            @ApiParam("申请类型(1配发2换发3补发4注销)") @RequestParam(required = false) String applyType,
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("直属审核人员") @RequestParam(required = false) String auditer,
            @ApiParam("申请物种(物种名称)") @RequestParam(required = false) String speName,
            @ApiParam("申请物种ID") @RequestParam(required = false) String speId,
            @ApiParam("审核状态(2已上报3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过)") @RequestParam(required = false) String processStatus,
//            @ApiParam("测试阶段-企业/政府机构ID(如果不传,后端则取当前用户企业/政府机构ID)") @RequestParam(required = false) String orgId,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        Object[] vals = {applyDateS, applyDateE, applyType, speName, auditer, speId, processStatus, compName, "applyCode"};
        String[] keys = {"applyDateS", "applyDateE", "applyType", "speName", "auditer", "speId", "processStatus", "compName", applyCode};
        if (StringUtils.hasText(applyDateS))
            vals[0] = applyDateS + " 00:00:00";
        if (StringUtils.hasText(applyDateE))
            vals[1] = applyDateE + " 23:59:59";
        return Result.ok(signboardApplyService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }
}
