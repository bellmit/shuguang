package com.sofn.fdpi.web;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.enums.PapersType2Enum;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.service.PapersService;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
@Api(value = "APP_人工繁育/驯养/经营许可证管理、企业证书相关接口", tags = "APP_人工繁育/驯养/经营许可证管理、企业证书相关接口")
@RequestMapping("/app/papers")
@RestController
public class PapersAppController extends BaseController {


    @Autowired
    private PapersService papersService;

    /**
     * 省级用户添加证书用于添加证书
     *
     * @param papersForm 证书
     * @return
     */
    @RequiresPermissions("fdpi:papersForJY:create")
    @SofnLog("新增经营许可证证书")
    @ApiOperation(value = "新增经营许可证证书")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result addMan(@Validated @RequestBody PapersForm papersForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = papersService.saveMan(papersForm);
        if (i == 1) {
            return Result.ok("新增成功");
        } else {
            return Result.error("证书编号已存在,或该企业已存在当前物种的证书");
        }

    }

    @RequiresPermissions("fdpi:papersInfo:create")
    @SofnLog("新增人工繁育许可证书")
    @ApiOperation(value = "新增人工繁育许可证书")
    @RequestMapping(value = "/saveArt", method = RequestMethod.POST)
    public Result ArtPaperFrom(@Validated @RequestBody ArtPaperFrom artPaperFrom, BindingResult result) {
//         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = papersService.saveArt(artPaperFrom);
        if (i == 1) {
            return Result.ok("新增成功");
        } else {
            return Result.error("证书编号已存在,或该企业已存在当前物种的证书");
        }

    }

    /*测*/
    @RequiresPermissions(value = {"fdpi:papersInfo:view", "fdpi:papersForJY:view"}, logical = Logical.OR)
    @SofnLog("根据证书编号获取证书信息")
    @ApiOperation(value = "根据证书编号获取证书信息")
    @GetMapping(value = "/get")
    public Result<Papers> getPaperById(@ApiParam("证书编号") @RequestParam("id") String id) {
        Papers papers = papersService.selectPaperInfoById(id);
        return Result.ok(papers);

    }

    @RequiresPermissions("fdpi:papersForJY:update")
    @SofnLog("修改经营许可证证书信息")
    @ApiOperation(value = "修改经营许可证证书信息")
    @PutMapping(value = "/update")
    public Result updatePapers(@Validated @RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式", required = true)
                                       PapersForm papersForm, BindingResult result) {

        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = papersService.updateMan(papersForm);
        if (i == 1) {
            return Result.ok("修改成功");
        } else {
            return Result.error("证书编号已存在,或改企业已存在当前物种的证书");
        }

    }

    @RequiresPermissions("fdpi:papersInfo:update")
    @SofnLog("修改人工繁育许可证证书信息")
    @ApiOperation(value = "修改人工繁育许可证证书信息")
    @PutMapping(value = "/updateArt")
    public Result updateArt(@Validated @RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式", required = true)
                                    ArtPaperFrom artPaperFrom, BindingResult result) {

        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = papersService.updateArt(artPaperFrom);
        if (i == 1) {
            return Result.ok("修改成功");
        } else {
            return Result.error("证书编号已存在,或该企业已存在当前物种的证书");
        }

    }

    /*测*/
    @RequiresPermissions(value = {"fdpi:papersForJY:delete", "fdpi:papersInfo:delete"}, logical = Logical.OR)
    @SofnLog("删除证书信息")
    @ApiOperation(value = "删除证书信息")
    @DeleteMapping(value = "/delete")
    public Result delPapers(@ApiParam("证书id") @RequestParam("id") String id) {
        int i = papersService.delPapers(id);
        if (i == 1) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @RequiresPermissions("fdpi:papersForRG:query")
    @ApiOperation(value = "获取人工/繁育证书列表")
    @GetMapping(value = "ListArt")
    public Result<PageUtils<PapersListVo>> getArtPaperListPage(@ApiParam(name = "papersType", value = "证书类型", required = false) @RequestParam(value = "papersType", required = false) String papersType,
                                                               @ApiParam(name = "papersNumber", value = "证书编号", required = false) @RequestParam(value = "papersNumber", required = false) String papersNumber,
                                                               @ApiParam(name = "issueSpe", value = "物种名", required = false) @RequestParam(value = "issueSpe", required = false) String issueSpe,
                                                               @ApiParam(name = "compName", value = "企业名", required = false) @RequestParam(value = "compName", required = false) String compName,
                                                               @ApiParam(name = "pageNo", required = true) @RequestParam("pageNo") int pageNo,
                                                               @ApiParam(name = "pageSize", required = true) @RequestParam("pageSize") int pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("papersType", papersType);
        map.put("papersNumber", papersNumber);
        map.put("issueSpe", issueSpe);
        map.put("compName", compName);
        return Result.ok(papersService.getArtificialPaperListPage(map, pageNo, pageSize));
    }

    @RequiresPermissions("fdpi:papersForJY:query")
    @ApiOperation(value = "获取经营证书列表")
    @GetMapping(value = "ListMan")
    public Result<PageUtils<PapersListVo>> getManPaperListPage(@ApiParam(name = "papersNumber", value = "证书编号", required = false) @RequestParam(value = "papersNumber", required = false) String papersNumber,
                                                               @ApiParam(name = "issueSpe", value = "物种名", required = false) @RequestParam(value = "issueSpe", required = false) String issueSpe,
                                                               @ApiParam(name = "compName", value = "企业名", required = false) @RequestParam(value = "compName", required = false) String compName,
                                                               @ApiParam(name = "pageNo", required = true) @RequestParam("pageNo") int pageNo,
                                                               @ApiParam(name = "pageSize", required = true) @RequestParam("pageSize") int pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("papersNumber", papersNumber);
        map.put("issueSpe", issueSpe);
        map.put("compName", compName);
        return Result.ok(papersService.getManagementPaperListPage(map, pageNo, pageSize));
    }


    //wuXY
    @ApiOperation(value = "企业信息-》证书信息《列表》")
    @GetMapping(value = "/listForComp")
    public Result<List<PapersVo>> listForComp(
            @RequestParam(value = "papersType", required = false) @ApiParam(name = "papersType", value = "证书类型：1：人工繁育；2：驯养繁殖；3：经营利用") String papersType
            , @RequestParam(value = "papersNumber", required = false) @ApiParam(name = "papersNumber", value = "证书编号") String papersNumber
            , @RequestParam(value = "issueSpe", required = false) @ApiParam(name = "issueSpe", value = "物种") String issueSpe
            , @RequestParam(value = "compName", required = false) @ApiParam(name = "compName", value = "企业名称") String compName
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("papersType", papersType);
        map.put("papersNumber", papersNumber);
        map.put("parStatus", "4");  //审核通过
        map.put("issueSpe", issueSpe);
        map.put("compId", UserUtil.getLoginUserOrganizationId());
        map.put("compName", compName);

        return Result.ok(papersService.listForBinding(map, pageNo, pageSize));
    }

    //wuXY
    @ApiOperation(value = "企业信息-》证书信息-》编辑/查看")
    @GetMapping(value = "/getPapers")
    public Result<PapersBindingVo> getPapersAndYearInspect(@RequestParam("papersId") @ApiParam(name = "papersId", value = "证书id", required = true) String papersId) {
        return Result.ok(papersService.getPapersInfo(papersId));
    }

    //wuXY
    @ApiOperation(value = "企业信息-》证书信息-》编辑中保存")
    @PutMapping(value = "/updatePapersFile")
    public Result updatePapersFile(@RequestBody @ApiParam(name = "papersFileForm", value = "证书信息编辑json对象", required = true) PapersFileForm papersFileForm
            , BindingResult result) {
        if (result.hasErrors()) {
            Result.error(getErrMsg(result));
        }
        String returnInfo = papersService.updatePapersFile(papersFileForm);
        if (!"1".equals(returnInfo)) {
            return Result.error(returnInfo);
        }
        return Result.ok();
    }

    @SofnLog("下载经营许可证导入模板")
    @ApiOperation(value = "下载经营许可证导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downManPaperTemplate", produces = "application/octet-stream")
    public void downDataCountModel(HttpServletResponse hsr) {
        papersService.downManPaperTemplate(hsr, PapersType2Enum.MANAGEMENT_UTILIZATION.getKey());
    }

    @SofnLog("下载人工繁育许可证导入模板")
    @ApiOperation(value = "下载人工繁育许可证导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downArtPaperTemplate", produces = "application/octet-stream")
    public void downAtrDataCountModel(HttpServletResponse hsr) {
        papersService.downArtPaperTemplate(hsr);
    }

    @RequiresPermissions("fdpi:papersInfo:importBatch")
    @SofnLog(value = "导入人工繁育")
    @ApiOperation(value = "导入人工繁育")
//    @RequiresPermissions(value = "pmlcsc:slaveSiteImport:importSite")
    @PostMapping(value = "importArtPaper", produces = "multipart/form-data")
    public Result<String> importArtPaper(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        List<ArtPaperExcelFrom> importList = ExcelImportUtil.getDataByExcel(multipartFile, 2, ArtPaperExcelFrom.class, null);
        try {
            if (!CollectionUtils.isEmpty(importList)) {
                List<PaperExcel> ape = Lists.newArrayListWithCapacity(importList.size());
                for (ArtPaperExcelFrom from : importList) {
                    ape.add(PaperExcel.form2Vo(from));
                }
                papersService.importArt(ape);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        return Result.ok();
    }

    @RequiresPermissions("fdpi:papersForJY:importBatch")
    @SofnLog(value = "导入经营利用")
    @ApiOperation(value = "导入经营利用")
//    @RequiresPermissions(value = "pmlcsc:slaveSiteImport:importSite")
    @PostMapping(value = "importManPaper", produces = "multipart/form-data")
    public Result<String> importManPaper(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        try {
            List<ManPaperExeclFrom> importList = ExcelImportUtil.getDataByExcel(multipartFile, 2, ManPaperExeclFrom.class, null);
            if (!CollectionUtils.isEmpty(importList)) {
                List<PaperExcel> voList = Lists.newArrayListWithCapacity(importList.size());
                for (ManPaperExeclFrom from : importList) {
                    voList.add(PaperExcel.form2Vo(from));
                }
                papersService.importMan(voList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        return Result.ok();
    }

    //    @RequiresPermissions(value = {"fdpi:papersZBPrint:query","fdpi:papersFBPrint:query"},logical = Logical.OR)
    @ApiOperation(value = "许可证打印列表")
    @GetMapping(value = "listPrint")
    public Result<PageUtils<PaperPrintVo>> printListPage(@ApiParam(name = "papersType", value = "证书类型", required = false) @RequestParam(value = "papersType", required = false) String papersType,
                                                         @ApiParam(name = "papersNumber", value = "证书编号", required = false) @RequestParam(value = "papersNumber", required = false) String papersNumber,
                                                         @ApiParam(name = "isPrint", value = "许可证正本是否打印 0：未打印 1：打印", required = false) @RequestParam(value = "isPrint", required = false) String isPrint,
                                                         @ApiParam(name = "isCopyPrint", value = "许可证副本是否打印0：未打印 3：打印", required = false) @RequestParam(value = "isCopyPrint", required = false) String isCopyPrint,
                                                         @ApiParam(name = "compName", value = "企业名", required = false) @RequestParam(value = "compName", required = false) String compName,
                                                         @ApiParam(name = "pageNo", required = true) @RequestParam("pageNo") int pageNo,
                                                         @ApiParam(name = "pageSize", required = true) @RequestParam("pageSize") int pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("papersType", papersType);
        map.put("papersNumber", papersNumber);
        map.put("isPrint", isPrint);
        map.put("compName", compName);
        map.put("isCopyPrint", isCopyPrint);
        return Result.ok(papersService.listPrint(map, pageNo, pageSize));
    }

    @RequiresPermissions("fdpi:papersZBPrint:print")
    @SofnLog("许可证正本打印")
    @ApiOperation(value = "许可证正本打印")
    @PutMapping(value = "/print")
    public Result<Papers> print(@ApiParam("证书id") @RequestParam("id") String id) {
        return Result.ok(papersService.print(id));
    }
//    @RequiresPermissions(value = {"fdpi:papersFBPrint:fMPrint","fdpi:papersFBPrint:nYPrint"},logical = Logical.OR)
//    @SofnLog("许可证副本打印")
//    @ApiOperation(value = "许可证副本打印")
//    @PutMapping(value = "/copyPrint")
//    public Result<Papers> printCopy(@ApiParam("证书id") @RequestParam("id")String id,@ApiParam("type:封面打印：1，内页打印:2") @RequestParam("type") String type) {
//        return Result.ok(papersService.copyPrint(id,type));
//    }

    @RequiresPermissions(value = {"fdpi:papersForBZ:query", "fdpi:papersForRG:index", "fdpi:papersForJY:index"}, logical = Logical.AND)
    @SofnLog("许可证列表")
    @ApiOperation(value = "许可证列表")
    @GetMapping(value = "/licenceList")
    public Result<PageUtils<LicenceVo>> licence(@ApiParam(name = "compName", value = "企业名", required = false) @RequestParam(value = "compName", required = false) String compName,
                                                @ApiParam(name = "pageNo", required = true) @RequestParam("pageNo") int pageNo,
                                                @ApiParam(name = "pageSize", required = true) @RequestParam("pageSize") int pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("compName", compName);
        return Result.ok(papersService.licence(map, pageNo, pageSize));
    }

    @RequiresPermissions(value = {"fdpi:papersInfo:delete", "fdpi:papersForJY:delete", "fdpi:papersForBZ:delete"}, logical = Logical.AND)
    @SofnLog("许可证删除")
    @ApiOperation(value = "许可证删除")
    @DeleteMapping(value = "/licenceDel")
    public Result licenceDel(@ApiParam("证书id") @RequestParam("id") String id, @ApiParam("证书类型") @RequestParam("papersType") String papersType) {
        int i = papersService.delLicence(id, papersType);
        if (i == 1) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }

    }

}