package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.service.SturgeonProcessService;
import com.sofn.fdpi.service.SturgeonReprintService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.SturgeonProcessVo;
import com.sofn.fdpi.vo.SturgeonReprintForm;
import com.sofn.fdpi.vo.SturgeonReprintVo;
import com.sofn.fdpi.vo.SturgeonSignboardVo;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Api(value = "鲟鱼子酱标识补打相关接口", tags = "鲟鱼子酱标识补打相关接口")
@RestController
@RequestMapping(value = "/sturgeonReprint")
public class SturgeonRepringController extends BaseController {

    @Resource
    private SturgeonReprintService sturgeonReprintService;

    @Resource
    private SturgeonProcessService sturgeonProcessService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("fdpi:sturgeonReprintApply:create")
    public Result<SturgeonReprintVo> save(@Validated @RequestBody SturgeonReprintForm sturgeonReprintForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(sturgeonReprintService.save(sturgeonReprintForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("fdpi:sturgeonReprintApply:delete")
    public Result delete(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonReprintService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("fdpi:sturgeonReprintApply:update")
    public Result update(@Validated @RequestBody SturgeonReprintForm sturgeonReprintForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sturgeonReprintService.update(sturgeonReprintForm);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions(value = {"fdpi:sturgeonReprintApply:view", "fdpi:sturgeonReprintApprove:view",
            "fdpi:citesReprintAudit:view", "fdpi:citesReprint:view"}, logical = Logical.OR)
    public Result<SturgeonReprintVo> get(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(sturgeonReprintService.get(id));
    }

    @ApiOperation(value = "流程记录")
    @GetMapping("/getProcess")
//    @RequiresPermissions(value = {"fdpi:signboardApply:view", "fdpi:signboardApprove:view"}, logical = Logical.OR)
    public Result<List<SturgeonProcessVo>> getProcess(
            @ApiParam(name = "id", value = "申请ID", required = true) @RequestParam(value = "id") String id) {
        List<SturgeonProcessVo> list = Constants.WORKFLOW.equals(BoolUtils.N) ?
                sturgeonProcessService.listSturgeonProcess(id, "2") : sturgeonReprintService.listSturgeonProcess(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "上报(列表页使用)")
    @PutMapping("/report")
    @RequiresPermissions("fdpi:sturgeonReprintApply:report")
    public Result report(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonReprintService.report(id);
        return Result.ok();
    }

    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
    @RequiresPermissions("fdpi:sturgeonReprintApprove:approve")
    public Result auditPass(@ApiParam(value = "主键id", required = true) @RequestParam() String id,
                            @ApiParam("是否第三方打印企业打印 Y/N") @RequestParam(required = false) String thirdPrint) {
        sturgeonReprintService.auditPass(id, thirdPrint);
        return Result.ok();
    }

    @ApiOperation(value = "审核(退回)")
    @PutMapping("/auditReturn")
    @RequiresPermissions("fdpi:sturgeonReprintApprove:back")
    public Result auditReturn(@ApiParam(value = "主键id", required = true) @RequestParam() String id,
                              @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        if (opinion.length() > 50) {
            return Result.error("审核意见不能超过50字");
        }
        sturgeonReprintService.auditReturn(id, opinion);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    @SofnLog("分页查询")
    public Result<PageUtils<SturgeonReprintVo>> listPage(
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("进出口证书编号") @RequestParam(required = false) String credentials,
            @ApiParam(value = "申请类型1国外2国内") @RequestParam(required = false) String applyType,
            @ApiParam("审核状态 1未上报2已上报(待审核)3已退回4已通过") @RequestParam(required = false) String status,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"credentials", "compName", "status", "applyType"};
        Object[] vals = {credentials, compName, status, applyType};
        return Result.ok(sturgeonReprintService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "撤回")
    @PutMapping("/cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sturgeonReprintService.cancel(id);
        return Result.ok();
    }

    @ApiOperation(value = "查询可补打列表")
    @GetMapping("/listRepring")
    public Result<PageUtils<SturgeonSignboardVo>> listRepring(
            @RequestParam(required = false) @ApiParam(value = "标识ids，多id用英文逗号分隔") String signboardIds,
            @ApiParam(value = "企业Id") @RequestParam(required = false) String compId) {
        List<String> ids = StringUtils.hasText(signboardIds) ? Arrays.asList(signboardIds.split(",")) : Collections.EMPTY_LIST;
        return Result.ok(sturgeonReprintService.listRepring(compId, ids));
    }

    @ApiOperation(value = "国内鱼子酱补打确定")
    @PutMapping("/print")
    public Result print(@RequestParam(value = "id") @ApiParam(name = "id", value = "补打id", required = true) String id) {
        sturgeonReprintService.print(id);
        return Result.ok();
    }


    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "下载打印信息", produces = "application/octet-stream")
    public void export(
            @RequestParam(value = "id") @ApiParam(name = "id", value = "补打申请id", required = true) String id,
            HttpServletResponse response) {
        sturgeonReprintService.export(id, response);
    }

}
