package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.service.SignboardApplyService;
import com.sofn.fdpi.service.SignboardProcessService;
import com.sofn.fdpi.vo.SignboardProcessVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/31 10:08
 **/
@Slf4j
@Api(value = "标识申请进度(流程)管理相关接口", tags = "标识申请进度(流程)管理相关接口")
@RestController
@RequestMapping(value = "/signboardProcess")
public class SignboardProcessController extends BaseController {

    @Resource
    private SignboardProcessService spService;

    @Resource
    private SignboardApplyService signboardApplyService;


    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    @SofnLog("分页查询标识")
    public Result<PageUtils<SignboardProcessVo>> listPage(
            @ApiParam("申请日期起(yyyy-MM-dd)") @RequestParam(required = false) String applyDateS,
            @ApiParam("申请日期止(yyyy-MM-dd)") @RequestParam(required = false) String applyDateE,
            @ApiParam("审核状态") @RequestParam(required = false) String status,
            @ApiParam("申请物种(物种id)") @RequestParam(required = false) String speId,
            @ApiParam("申请物种(物种名称)") @RequestParam(required = false) String speName,
            @ApiParam("企业ID(如果不传,后端则取当前用户企业ID)") @RequestParam(required = false) String compId,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        return Result.ok(spService.listPage(getQueryMap(
                applyDateS, applyDateE, status, speId, speName, compId), pageNo, pageSize));
    }

    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    @SofnLog("列表查询标识")
    public Result<List<SignboardProcessVo>> listSignboard(
            @ApiParam("申请日期起(yyyy-MM-dd)") @RequestParam(required = false) String applyDateS,
            @ApiParam("申请日期止(yyyy-MM-dd)") @RequestParam(required = false) String applyDateE,
            @ApiParam("审核状态") @RequestParam(required = false) String status,
            @ApiParam("申请物种(物种id)") @RequestParam(required = false) String speId,
            @ApiParam("申请物种(物种名称)") @RequestParam(required = false) String speName,
            @ApiParam("企业ID(如果不传,后端则取当前用户企业ID)") @RequestParam(required = false) String compId) {
        return Result.ok(spService.listSignboardProcess(getQueryMap(
                applyDateS, applyDateE, status, speId, speName, compId)));
    }


    @ApiOperation(value = "标识申请流程记录")
    @GetMapping("/get")
//    @RequiresPermissions(value = {"fdpi:signboardApply:view", "fdpi:signboardApprove:view"}, logical = Logical.OR)
    public Result<SignboardProcessVo> get(
            @ApiParam(name = "id", value = "标识申请ID", required = true) @RequestParam(value = "id") String id) {
        List<SignboardProcessVo> result = Constants.WORKFLOW.equals(BoolUtils.N) ? spService.listSignboardProcess(id) :
                signboardApplyService.listSignboardProcess(id);
        return Result.ok(result);
    }

    private Map<String, Object> getQueryMap(
            String applyDateS, String applyDateE, String status, String speId, String speName, String compId) {
        Map map = new HashMap<String, Object>(6);
        if (StringUtils.hasText(applyDateS)) {
            applyDateS = applyDateS + " 00:00:00";
        }
        map.put("applyDateS", applyDateS);
        if (StringUtils.hasText(applyDateE)) {
            applyDateE = applyDateE + " 23:59:59";
        }
        map.put("applyDateE", applyDateE);
        map.put("status", status);
        map.put("speName", speName);
        map.put("speId", speId);
        if (!StringUtils.hasText(compId)) {
            compId = UserUtil.getLoginUserOrganizationId();
        }
        map.put("compId", compId);
        return map;
    }

}
