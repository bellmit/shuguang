package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.service.SturgeonPaperService;
import com.sofn.fdpi.service.SturgeonProcessService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.SturgeonPaperForm;
import com.sofn.fdpi.vo.SturgeonPaperVo;
import com.sofn.fdpi.vo.SturgeonProcessVo;
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

@Api(value = "鲟鱼子酱标签纸相关接口", tags = "鲟鱼子酱标签纸相关接口")
@RestController
@RequestMapping(value = "/sturgeonPaper")
public class SturgeonPaperController extends BaseController {

    @Resource
    private SturgeonPaperService sturgeonPaperService;

    @Resource
    private SturgeonProcessService sturgeonProcessService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions(value = {"fdpi:tagPaperApply:create", "fdpi:citesPaperApply:create"}, logical = Logical.OR)
    public Result<SturgeonPaperVo> save(@Validated @RequestBody SturgeonPaperForm sturgeonPaperForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(sturgeonPaperService.save(sturgeonPaperForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions(value = {"fdpi:tagPaperApply:delete", "fdpi:citesPaperApply:delete "}, logical = Logical.OR)
    public Result delete(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonPaperService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions(value = {"fdpi:tagPaperApply:update", "fdpi:citesPaperApply:update"}, logical = Logical.OR)
    public Result update(@Validated @RequestBody SturgeonPaperForm sturgeonPaperForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sturgeonPaperService.update(sturgeonPaperForm);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions(value = {"fdpi:tagPaperApply:view", "fdpi:tagPaperApprove:view", "fdpi:citesPaper:view",
            "fdpi:citesPaperAudit:view"}, logical = Logical.OR)
    public Result<SturgeonPaperVo> get(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(sturgeonPaperService.get(id));
    }

    @ApiOperation(value = "流程记录")
    @GetMapping("/getProcess")
//    @RequiresPermissions(value = {"fdpi:signboardApply:view", "fdpi:signboardApprove:view"}, logical = Logical.OR)
    public Result<List<SturgeonProcessVo>> getProcess(
            @ApiParam(name = "id", value = "申请ID", required = true) @RequestParam(value = "id") String id) {
        List<SturgeonProcessVo> list = Constants.WORKFLOW.equals(BoolUtils.N) ?
                sturgeonProcessService.listSturgeonProcess(id,"3") : sturgeonPaperService.listSturgeonProcess(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "上报(列表页使用)")
    @PutMapping("/report")
    @RequiresPermissions(value = {"fdpi:tagPaperApply:report", "fdpi:citesPaperApply:report"}, logical = Logical.OR)
    public Result report(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonPaperService.report(id);
        return Result.ok();
    }

    @ApiOperation(value = "填写快递信息")
    @PutMapping("/express")
    @RequiresPermissions(value = {"fdpi:tagPaperApprove:expressInfo", "fdpi:citesPaper:expressInfo"}, logical = Logical.OR)
    public Result express(@ApiParam(value = "主键id", required = true) @RequestParam() String id,
                          @ApiParam(value = "快递信息", required = true) @RequestParam() String express) {
        if (express.length() > 25) {
            return Result.error("快递信息不能超过25字");
        }
        sturgeonPaperService.express(id, express);
        return Result.ok();
    }

    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
    @RequiresPermissions(value = {"fdpi:tagPaperApprove:expressInfo", "fdpi:citesPaperAudit:audit"}, logical = Logical.OR)
    public Result auditPass(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonPaperService.auditPass(id);
        return Result.ok();
    }

    @ApiOperation(value = "审核(退回)")
    @PutMapping("/auditReturn")
    @RequiresPermissions(value = {"fdpi:tagPaperApprove:approve", "fdpi:citesPaperAudit:audit"}, logical = Logical.OR)
    public Result auditReturn(@ApiParam(value = "主键id", required = true) @RequestParam() String id,
                              @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        if (opinion.length() > 50) {
            return Result.error("审核意见不能超过50字");
        }
        sturgeonPaperService.auditReturn(id, opinion);
        return Result.ok();
    }

    //    @RequiresPermissions("agpjyz:specimen:cancel")
    @ApiOperation(value = "撤回")
    @PutMapping("/cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonPaperService.cancel(id);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    @SofnLog("分页查询")
    public Result<PageUtils<SturgeonPaperVo>> listPage(
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("审核状态 1未上报2已上报(待审核)3已退回4已通过") @RequestParam(required = false) String status,
            @ApiParam(value = "申请类型1国外2国内") @RequestParam(required = false) String applyType,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"status", "compName", "applyType"};
        Object[] vals = {status, compName, applyType};
        return Result.ok(sturgeonPaperService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }


}
