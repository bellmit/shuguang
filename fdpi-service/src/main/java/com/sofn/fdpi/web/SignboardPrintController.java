package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.SignboardPrint;
import com.sofn.fdpi.service.SignboardPrintListService;
import com.sofn.fdpi.service.SignboardPrintService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2020/1/6 17:08
 **/
@Slf4j
@Api(value = "标识打印相关接口", tags = "标识打印相关接口")
@RestController
@RequestMapping(value = "/signboardPrint")
public class SignboardPrintController extends BaseController {

    @Resource
    private SignboardPrintService signboardPrintService;

    @Resource
    private SignboardPrintListService signboardPrintListService;

    @ApiOperation(value = "标识打印新增")
    @PostMapping("/save")
    @RequiresPermissions("fdpi:signboardPrintManage:print")
    @Deprecated
    public Result save(@Validated @RequestBody SignboardPrintForm signboardPrintForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        SignboardPrint entity = signboardPrintService.insertSignboardPrint(signboardPrintForm, UserUtil.getLoginUserId());
        return Result.ok(entity.getId());
    }

    @ApiOperation(value = "分页查询打印明细列表")
    @GetMapping("/listPagePrintList")
    @RequiresPermissions("fdpi:signboardPrintManage:listForSignboard")
    public Result<PageUtils<SignboardPrintListVo>> listPagePrintList(
            @RequestParam(value = "printId") @ApiParam(name = "printId", value = "打印ID", required = true) String printId,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"printId"};
        Object[] vals = {printId};
        return Result.ok(signboardPrintListService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }


    @ApiOperation(value = "打印国内鱼子酱标识")
    @PutMapping("/print")
    public Result print(@RequestParam(value = "printId") @ApiParam(name = "printId", value = "打印ID", required = true) String printId) {
        signboardPrintService.print(printId);
        return Result.ok();
    }


    @ApiOperation(value = "分页查询打印列表")
    @GetMapping("/listPage")
    @SofnLog("分页查询打印列表")
    public Result<PageUtils<SignboardPrintVo>> listPage(
            @RequestParam(required = false) @ApiParam("企业类型") String compType,
            @RequestParam(required = false) @ApiParam("企业名称") String compName,
            @RequestParam(required = false) @ApiParam("申请单号") String applyCode,
            @RequestParam(required = false) @ApiParam("合同单号") String contractNum,
            @RequestParam(required = false) @ApiParam("申请类型1标识申请2国内鱼子酱申请") String applyType,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"compName", "compType", "applyType", "contractNum", "applyCode"};
        Object[] vals = {compName, compType, applyType, contractNum, applyCode};
        return Result.ok(signboardPrintService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }


    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "下载打印信息", produces = "application/octet-stream")
    public void export(
            @RequestParam(value = "printId") @ApiParam(name = "printId", value = "打印ID", required = true) String printId,
            @RequestParam(required = false) @ApiParam(value = "申请类型1标识申请2国内鱼子酱申请") String applyType,
            HttpServletResponse response) {
        String[] keys = {"printId", "applyType"};
        Object[] vals = {printId, applyType};
        signboardPrintListService.export(MapUtil.getParams(keys, vals), response);
    }

    @PostMapping(value = "/upload", produces = "multipart/form-data")
    @ApiOperation(value = "上传打印信息")
    public Result upload(@RequestParam @ApiParam("文件导入地址（暂只支持txt）") MultipartFile multipartFile,
                         @RequestParam() @ApiParam(value = "打印ID", required = true) String id) {
        signboardPrintListService.upload(multipartFile, id);
        return Result.ok();
    }


}
