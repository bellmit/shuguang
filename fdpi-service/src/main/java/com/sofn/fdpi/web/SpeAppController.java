
package com.sofn.fdpi.web;

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
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.vo.SpeExcel;
import com.sofn.fdpi.vo.SpeInfo;
import com.sofn.fdpi.vo.SpeNameLevelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Auther:
 * @Date: 2019/11/28 11:44
 * @Description:
 */

@Slf4j
@Api(value = "APP_物种", tags = "APP_物种相关接口")
@RestController
@RequestMapping("/app/spe")

public class SpeAppController extends BaseController {
    @Autowired
    private SpeService speService;

    @RequiresPermissions("fdpi:speciesInfo:view")
    //根据物种id  查询物种信息
    @SofnLog("根据id查询物种信息")
    @ApiOperation(value = "根据id查询物种信息")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<Spe> querySpeListBySpeId(@RequestParam(value = "id") String Id) {
        Spe speBySpeId = speService.getSpeBySpeId(Id);
        return Result.ok(speBySpeId);
    }

    //根据条件查询物种列表（分页）
    @SofnLog("根据条件查询物种列表（分页）")
    @ApiOperation(value = "根据条件查询物种列表（分页）")
    @GetMapping(value = "/list")
    public Result<PageUtils<Spe>> getSpeByPage(@ApiParam(name = "cites", value = "CITES级别，1级：1,2级：2,3级：3", required = false) @RequestParam(value = "cites", required = false) String cites,
                                               @ApiParam(name = "proLevel", value = "中国保护水平写死 一级:1 ，2级：2 ，特殊管理要求：3", required = false) @RequestParam(value = "proLevel", required = false) String proLevel,
                                               @ApiParam(name = "pedigree", value = "是否进行谱系写死，否：0是：1", required = false) @RequestParam(value = "pedigree", required = false) String pedigree,
                                               @ApiParam(name = "speName", value = "物种名", required = false) @RequestParam(value = "speName", required = false) String speName,
                                               @ApiParam(name = "identify", value = "是否使用标识写死，0:不使用,1 :全程使用,2:销售使用", required = false) @RequestParam(value = "identify", required = false) String identify,
                                               @ApiParam(name = "pageNo", required = true) @RequestParam("pageNo") int pageNo,
                                               @ApiParam(name = "pageSize", required = true) @RequestParam("pageSize") int pageSize) {
        HashMap<String, Object> map = Maps.newHashMap();

        map.put("speName", speName);
        map.put("identify", identify);
        map.put("pedigree", pedigree);
        map.put("proLevel", proLevel);
        map.put("cites", cites);

        return Result.ok(speService.listSpeByPage(map, pageNo, pageSize));
    }

    @RequiresPermissions("fdpi:speciesInfo:update")
    @SofnLog("修改物种信息")
    @ApiOperation(value = "修改物种信息")
    @PostMapping("/update")
    public Result updateCheckingDept(@Validated @RequestBody @ApiParam(name = "物种对象",
            value = "传入json格式", required = true) SpeInfo speInfo
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = speService.updateSpeInfo(speInfo);
        if (i == 1) {
            return Result.ok("修改物种信息成功！");
        } else {
            return Result.error("物种名已存在不能修改");
        }

    }

    @RequiresPermissions("fdpi:speciesInfo:delete")
    @SofnLog("删除物种信息")
    @ApiOperation(value = "删除物种信息")
    @GetMapping("/delete")
    public Result deleteById(@RequestParam(value = "Id") @ApiParam(name = "Id", value = "物种信息Id", required = true) String Id) {
        String info = speService.deleteSpeInfo(Id);
        if (!"1".equals(info)) {
            return Result.ok(info);
        }
        return Result.ok("删除物种信息成功");
    }

    @RequiresPermissions("fdpi:speciesInfo:create")
    @SofnLog("新增物种信息")
    @ApiOperation(value = "新增物种信息")
    @PostMapping(value = "/save")
    public Result saveSpe(@Validated @RequestBody @ApiParam(name = "物种对象", value = "传入json格式", required = true) SpeInfo speInfo
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String s = speService.saveCheckingSpe(speInfo);
        if ("1".equals(s)) {
            return Result.ok("新增物种信息成功");
        }
        return Result.error(s);
    }
//    getSpeciesName

    @SofnLog("獲取物种名字")
    @ApiOperation(value = "獲取物種名字")
    @GetMapping(value = "/getSpeciesName")
    public Result<List<SpeNameLevelVo>> getSpeciesName(
            @ApiParam(name = "type", value = "0-证书备案") @RequestParam(value = "type", required = false) String type) {
        return Result.ok(speService.getSpeciesName(type));
    }

    //, notes = "权限码:(fdpi:spe:importSite)"
    @SofnLog(value = "导入物种")
    @ApiOperation(value = "导入物种")
    @RequiresPermissions("fdpi:speciesInfo:export")
    @PostMapping(value = "importSite", produces = "multipart/form-data")
    public Result<String> importSite(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        String resultMsg;
        try {
            List<SpeExcel> importList = ExcelImportUtil.getDataByExcel(multipartFile, 1, SpeExcel.class, null);
            resultMsg = speService.add(importList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        return Result.ok(resultMsg);

    }


    @SofnLog("下载导入模板")
    @ApiOperation(value = "下载导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downDataCountTemplate", produces = "application/octet-stream")
    public void downDataCountModel(HttpServletResponse hsr) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/重点保护物种物种管理信息.xls";
        ExcelExportUtil.createTemplate(filePath, SpeExcel.class);
        SpeExcel at = new SpeExcel();
        at.setId("1");
        at.setSpeName("龙头虾");
        at.setLatinName("long");
        at.setLocalName("小虾米");
        at.setTradeName("大龙虾");
        at.setIntro("水生");
        at.setHabit("水生适宜淡水");
        at.setDistribution("长江下游");
        at.setIdentify("1");
        at.setPedigree("1");
        at.setProLevel("1");
        at.setCites("1");
        List<SpeExcel> exportList = new ArrayList<>(1);
        exportList.add(at);
        String fileName = "重点保护物种物种管理信息导入模板.xls";
        ExcelExportUtil.createExcel(hsr, SpeExcel.class, exportList, ExcelField.Type.IMPORT, fileName);
        FileDownloadUtils.downloadAndDeleteFile(filePath, hsr);
    }
}

