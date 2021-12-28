package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.SturgeonSignboardDomesticService;
import com.sofn.fdpi.service.SturgeonSignboardService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "鲟鱼子酱标识打印相关接口", tags = "鲟鱼子酱标识打印相关接口")
@RestController
@RequestMapping(value = "/sturgeonPrint")
public class SturgeonPrintController extends BaseController {

    @Resource
    private SturgeonSignboardService sturgeonSignboardService;
    @Resource
    private SturgeonSignboardDomesticService sturgeonSignboardDomesticService;


    @ApiOperation(value = "打印")
    @PutMapping("/print")
    @RequiresPermissions(value = {"fdpi:sturgeonAPrint:print", "fdpi:sturgeonBPrint:print",
            "fdpi:sturgeonXTPrint:print"}, logical = Logical.OR)
    public Result print(@Validated @RequestBody PrintParamForm printParamForm, BindingResult result) {
        if ("2".equals(printParamForm.getApplyType())) {
            sturgeonSignboardDomesticService.print(printParamForm.getLabel(), printParamForm.getIds());
        } else {
            sturgeonSignboardService.print(printParamForm.getLabel(), printParamForm.getIds());
        }
        return Result.ok();
    }

    @ApiOperation(value = "获取打印箱号下拉数据")
    @GetMapping("/getCaseNum")
    public Result<List<SelectVo>> getCaseNum(
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("进出口证书编号") @RequestParam(required = false) String credentials,
            @ApiParam("标签打印状态(Y已打印N可打印)") @RequestParam(required = false) String labelPrintStatus,
            @ApiParam("标签打印状态(Y已打印N可打印)") @RequestParam(required = false) String stickerPrintStatus,
            @ApiParam(value = "申请类型1国外2国内") @RequestParam(required = false) String applyType,
            @ApiParam(value = "打印规格 A 标签A B 标签B S 箱贴", required = true) @RequestParam() String label) {
        String[] keys = {"label", "compName", "credentials", "labelPrintStatus", "stickerPrintStatus"};
        Object[] vals = {label, compName, credentials, labelPrintStatus, stickerPrintStatus};
        if ("2".equals(applyType)) {
            return Result.ok(sturgeonSignboardDomesticService.getCaseNum(MapUtil.getParams(keys, vals)));
        }
        return Result.ok(sturgeonSignboardService.getCaseNum(MapUtil.getParams(keys, vals)));
    }


    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<SturgeonSignboardVo>> listPage(
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("进出口证书编号") @RequestParam(required = false) String credentials,
            @ApiParam("箱号") @RequestParam(required = false) Integer caseNum,
            @ApiParam("申请单号") @RequestParam(required = false) String applyCode,
            @ApiParam("标签打印状态(Y已打印N可打印)") @RequestParam(required = false) String labelPrintStatus,
            @ApiParam("标签打印状态(Y已打印N可打印)") @RequestParam(required = false) String stickerPrintStatus,
            @ApiParam(value = "打印规格 A 标签A B 标签B S 箱贴", required = true) @RequestParam() String label,
            @ApiParam(value = "申请类型1国外2国内") @RequestParam(required = false) String applyType,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"label", "compName", "credentials", "caseNum", "labelPrintStatus", "stickerPrintStatus", "applyCode"};
        Object[] vals = {label, compName, credentials, caseNum, labelPrintStatus, stickerPrintStatus, applyCode};
        if ("2".equals(applyType)) {
            return Result.ok(sturgeonSignboardDomesticService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
        }
        return Result.ok(sturgeonSignboardService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public Result<List<SturgeonSignboardVo>> list(
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("进出口证书编号") @RequestParam(required = false) String credentials,
            @ApiParam("箱号") @RequestParam(required = false) Integer caseNum,
            @ApiParam("标签打印状态(Y已打印N可打印)") @RequestParam(required = false) String labelPrintStatus,
            @ApiParam("标签打印状态(Y已打印N可打印)") @RequestParam(required = false) String stickerPrintStatus,
            @ApiParam(value = "申请类型1国外2国内") @RequestParam(required = false) String applyType,
            @ApiParam("申请单号") @RequestParam(required = false) String applyCode,
            @ApiParam(value = "打印规格 A 标签A B 标签B S 箱贴", required = true) @RequestParam() String label) {
        if ("S".equals(label)) {
            labelPrintStatus = null;
            stickerPrintStatus = BoolUtils.N;
        } else {
            labelPrintStatus = BoolUtils.N;
            stickerPrintStatus = null;
        }
        String[] keys = {"label", "thirdPrint", "compName", "credentials", "applyCode", "caseNum", "labelPrintStatus", "stickerPrintStatus"};
        Object[] vals = {label, BoolUtils.N, compName, credentials, applyCode, caseNum, labelPrintStatus, stickerPrintStatus};
        if ("2".equals(applyType)) {
            return Result.ok(sturgeonSignboardDomesticService.list(MapUtil.getParams(keys, vals)));
        }
        return Result.ok(sturgeonSignboardService.list(MapUtil.getParams(keys, vals)));
    }

}
