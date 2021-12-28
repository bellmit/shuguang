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
import com.sofn.fdpi.model.Capture;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.service.ImportsExportsService;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 16:13
 */
@Slf4j
@Api(value = "进出口相关接口",tags = "进出口相关接口")
@RequestMapping("/importsExports")
@RestController
public class ImportsExportsController extends BaseController {
    @Autowired
    private ImportsExportsService importsexportsservice;
    @Autowired
    private SpeService speService;
    /*测*/
    @RequiresPermissions("fdpi:importsExports:create")
    @SofnLog("新增出入口记录")
    @ApiOperation(value = "新增出入口记录")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result addImportsExports(@Validated @RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式",
            required = true) ImportsExports importsExports, BindingResult result){
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = 0;
        try {
            i = importsexportsservice.insertImportsExports(importsExports);
            if (i==1){
                return Result.ok();
            }else {
                return Result.error("审批编号已存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }


    }

    /*测*/
    @RequiresPermissions("fdpi:importsExports:view")
    @SofnLog("进出口详情")
    @ApiOperation(value = "进出口详情")
    @GetMapping(value = "/get")
    public Result<ImportsExports> getImportsExportsById(@ApiParam("进出口id")@RequestParam("id") String id){
        ImportsExports importsExportsById = importsexportsservice.getImportsExportsById(id);
        return Result.ok(importsExportsById);
    }

    /*测*/
    @RequiresPermissions(value = {"fdpi:importsExports:query"})
    @SofnLog("进出口列表(分页条件)")
    @ApiOperation(value = "进出口列表(分页条件)")
    @GetMapping(value = "/listPage")
    public Result<PageUtils<ImportsExports>>getImportsExportsList( @ApiParam(name = "impAuform",value = "审批编号",required = false)@RequestParam(value = "impAuform",required = false)String impAuform,
                                                                  @ApiParam(name = "impComp",value = "单位名称",required = false)@RequestParam(value = "impComp",required = false)String impComp,
                                                                   @ApiParam(name = "isPrint",value = "是否打印 0：未打印 1：打印",required = false)@RequestParam(value = "isPrint",required = false)String isPrint,
                                                                   @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
                                                                   @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
                                                                  @RequestParam(value = "pageNo") Integer pageNo,
                                                                  @RequestParam(value = "pageSize") Integer pageSize ){

        Map<String,Object> map= Maps.newHashMap();
        if (StringUtils.hasText(startTime)) {
            startTime = startTime + " 00:00:00";
        }
        if (StringUtils.hasText(endTime)) {
            endTime = endTime + " 23:59:59";
        }
        map.put("impAuform",impAuform);
        map.put("impComp",impComp);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("isPrint",isPrint);
        return  Result.ok(importsexportsservice.getImportsExportsListPage(map, pageNo, pageSize));
    }

    /*测*/
    @RequiresPermissions("fdpi:importsExports:update")
    @SofnLog("修改出入口记录")
    @ApiOperation(value = "修改出入口记录")
    @PostMapping(value = "/update")
    public Result updateImportsExports(@Validated @RequestBody @ApiParam(name = "进出口参数对象", value = "传入json格式",
            required = true) ImportsExportsForm importsExports, BindingResult result){
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        importsexportsservice.updateImportsExports(importsExports);
        return Result.ok();
    }
/*测*/
    @RequiresPermissions("fdpi:importsExports:delete")
    @SofnLog("删除进出口信息")
    @ApiOperation(value = "删除进出口信息")
    @DeleteMapping(value = "/delete")
    public Result delImportsExportsById(@ApiParam("进出口id")@RequestParam(value = "id") String id){

            int i = importsexportsservice.delImportsExports(id);
            if(i==1){
                return Result.ok("删除成功");
            }else{
                return Result.ok("删除失败");
            }

    }

    /*测*/

    @ApiOperation(value = "下拉：获取能够添加的物种名字")
    @GetMapping("/getSpeName")
    public Result<List<SpeNameLevelVo>> getSpeciesName() {
        List<SpeNameLevelVo> speciesName = speService.getSpeciesName(null);
        return  Result.ok(speciesName);
    }
    @SofnLog("下载导入模板")
    @ApiOperation(value = "下载导入模板",produces = "application/octet-stream")
    @GetMapping(value ="/downDataCountTemplate",produces = "application/octet-stream")
    public void downDataCountModel( HttpServletResponse hsr) throws IOException {
        importsexportsservice.downDataCountTemplate(hsr);
    }

    @RequiresPermissions("fdpi:importsExports:importBatch")
    @SofnLog(value = "导入进出口管理")
    @ApiOperation(value = "导入进出口管理")
    @PostMapping(value = "importSite", produces = "multipart/form-data")
    public Result<String> importSite(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile){
//        String resultMsg;
        try {
//            List<ImportExcel> importList = ExcelImportUtil.getDataByExcel(multipartFile, 1, ImportExcel.class,null);
           importsexportsservice.importData(multipartFile,importsexportsservice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }

        return Result.ok();

    }
    @RequiresPermissions("fdpi:importsExportsPrint:print")
    @SofnLog("进出口打印")
    @ApiOperation(value = "进出口打印")
    @PutMapping(value = "/print")
    public Result<ImportsExports> print(@ApiParam("证书id") @RequestParam("id")String id) {
        return Result.ok(importsexportsservice.print(id));
    }
    @ApiOperation(value = "重设缓存，（主键，审批编号，物种id）")
    @GetMapping(value = "/test")
    public void print1111() {
        importsexportsservice.saveCache();
    }

}
