package com.sofn.fdpi.web;


import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Capture;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 10:11
 */
@Slf4j
@Api(value = "特许猎捕证相关接口", tags = "特许猎捕证相关接口")
@RequestMapping("/capture")
@RestController
public class CaptureController extends BaseController {
    @Autowired
    private com.sofn.fdpi.service.CaptureService captureService;
    @Autowired
    private SpeService speService;

    /*测*/
    @RequiresPermissions("fdpi:papersForBZ:create")
    @SofnLog("新增证书")
    @ApiOperation(value = "新增证书")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result addCapProvince(@Validated @RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式",
            required = true) CaptureForm captureForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        Capture capture = new Capture();
        BeanUtils.copyProperties(captureForm, capture);
        int i = captureService.addCapture(capture);
        if (i == 1) {
            return Result.ok("新增成功");
        } else {
            return Result.error("不能重复插入相同证书");
        }

    }

    /*测*/
    @RequiresPermissions("fdpi:papersForBZ:view")
    @SofnLog("根据证书编号查看证书详情")
    @ApiOperation(value = "根据证书编号查看证书详情")
    @GetMapping(value = "/get")
    public Result<Capture> getCaptureByNumber(@ApiParam(value = "证书编号", required = true) @RequestParam("papersNum") String papersNum) {
        return Result.ok(captureService.selectCaptureInfoByPapersNumber(papersNum));
    }

    /*测*/
    @RequiresPermissions(value = {"fdpi:papersForBZ:query"})
    @SofnLog("条件获取特许捕获证(分页)")
    @ApiOperation(value = "条件获取特许捕获证（分页）")
    @GetMapping(value = "/listPage")
    public Result<PageUtils<Capture>> getCaptureList(@ApiParam(name = "speName", value = "物种", required = false) @RequestParam(value = "speName", required = false) String speName,
                                                     @ApiParam(name = "papersNumber", value = "证书编号", required = false) @RequestParam(value = "papersNumber", required = false) String papersNumber,
                                                     @ApiParam(name = "capUnit", value = "持证单位", required = false) @RequestParam(value = "capUnit", required = false) String capUnit,
                                                     @ApiParam(name = "isPrint", value = "是否打印 0：未打印 1：打印", required = false) @RequestParam(value = "isPrint", required = false) String isPrint,
                                                     @ApiParam(name = "proLevel", value = "保护级别 1：一级2：二级", required = false) @RequestParam(value = "proLevel", required = false) String proLevel,
                                                     @ApiParam(name = "pageNo", required = true) @RequestParam("pageNo") int pageNo,
                                                     @ApiParam(name = "pageSize", required = true) @RequestParam("pageSize") int pageSize) {
        String[] keys = {"speName", "papersNumber", "capUnit", "proLevel", "isPrint"};
        Object[] vals = {speName, papersNumber, capUnit, proLevel, isPrint};
        PageUtils captureList = captureService.getCaptureList(MapUtil.getParams(keys, vals), pageNo, pageSize);
        return Result.ok(captureList);
    }

    /*测*/
    @RequiresPermissions("fdpi:papersForBZ:delete")
    @SofnLog("移除特许捕获证书")
    @ApiOperation(value = "移除特许捕获证书")
    @DeleteMapping(value = "/delete")
    public Result delCapture(@ApiParam(value = "特许捕获证书id", required = true) @RequestParam("id") String id,
                             @ApiParam(value = "当前证书的保护级别", required = true) @RequestParam("proLevel") String proLevel) {


        int i = captureService.delCapture(id, proLevel);
        if (i == 1) {
            return Result.ok("删除成功");
        } else {
            throw new SofnException("权限不足，删除失败");
        }

    }

    /*测*/
    @RequiresPermissions("fdpi:papersForBZ:update")
    @SofnLog("修改特许捕获证书信息")
    @ApiOperation(value = "修改特许捕获证书信息")
    @PutMapping(value = "/update")
    public Result updateCap(@Validated @RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式",
            required = true) CaptureForm captureForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        Capture capture = new Capture();
        BeanUtils.copyProperties(captureForm, capture);
        int i = captureService.updateCapture(capture);
        if (i == 1) {
            return Result.ok("修改成功");
        } else if (i == 2) {
            return Result.error("权限不足，无法修改");
        } else {
            return Result.error("证书编号，已存在");
        }
    }

    /*测*/
    @SofnLog("根据当前用户级别获取能添加的动物和级别")
    @ApiOperation(value = "下拉：根据当前用户级别获取能添加的动物和级别")
    @GetMapping("/getLevSpeName")
    public Result<List<SpeNameLevelVo>> getSecondLevel() {
        List<SpeNameLevelVo> speciesName = speService.getSecondLevel();
        return Result.ok(speciesName);
    }

    @SofnLog("导出")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void downDataCountModel(@ApiParam(name = "speName", value = "物种", required = false) @RequestParam(value = "speName", required = false) String speName,
                                   @ApiParam(name = "papersNumber", value = "证书编号", required = false) @RequestParam(value = "papersNumber", required = false) String papersNumber,
                                   @ApiParam(name = "capUnit", value = "持证单位", required = false) @RequestParam(value = "capUnit", required = false) String capUnit,
                                   @ApiParam(name = "isPrint", value = "是否打印 0：未打印 1：打印", required = false) @RequestParam(value = "isPrint", required = false) String isPrint,
                                   @ApiParam(name = "proLevel", value = "保护级别 1：一级2：二级", required = false) @RequestParam(value = "proLevel", required = false) String proLevel,
                                   HttpServletResponse hsr) throws IOException {
        String[] keys = {"speName", "papersNumber", "capUnit", "proLevel", "isPrint"};
        Object[] vals = {speName, papersNumber, capUnit, proLevel, isPrint};
        captureService.export(MapUtil.getParams(keys, vals),hsr);
    }

    @SofnLog("下载导入模板")
    @ApiOperation(value = "下载导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downDataCountTemplate", produces = "application/octet-stream")
    public void downDataCountModel(HttpServletResponse hsr) throws IOException {
        captureService.downDataCountTemplate(hsr);
    }

    @RequiresPermissions("fdpi:papersForBZ:importBatch")
    @SofnLog(value = "导入特许猎捕证")
    @ApiOperation(value = "导入特许猎捕证")
    @PostMapping(value = "importSite", produces = "multipart/form-data")
    public Result<String> importSite(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        String resultMsg;
        try {
//            List<CaptureExcel> importList = ExcelImportUtil.getDataByExcel(multipartFile, 1, CaptureExcel.class,null);
//            resultMsg = CaptureService.add(importList);
            captureService.importData(multipartFile, captureService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        return Result.ok();

    }

    @RequiresPermissions("fdpi:papersForBZ:printPapers")
    @SofnLog("特许捕获证打印")
    @ApiOperation(value = "特许捕获证打印")
    @PutMapping(value = "/print")
    public Result<Capture> print(@ApiParam("证书id") @RequestParam("id") String id) {
        return Result.ok(captureService.print(id));
    }

    @ApiOperation(value = "重新设置缓存")
    @PutMapping(value = "/setCache")
    public void setCache() {
        captureService.setCache();
    }

}
