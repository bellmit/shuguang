package com.sofn.sys.web;

import com.google.common.collect.Maps;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.BaseData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.service.SysBaseDataService;
import com.sofn.sys.vo.Excel.ImportBaseDataVo;
import com.sofn.sys.vo.SysBaseDataAndTypeVo;
import com.sofn.sys.vo.SysBaseDataTypeVo;
import com.sofn.sys.vo.SysBaseDataVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "基础数据接口，权限码:(sys:basedata:index)")
@RequestMapping("/basedata")
@Slf4j
public class BaseDataController extends BaseController {

    @Autowired
    private SysBaseDataService sysBaseDataService;

    @ApiOperation(value = "查询基础数据列表接口",notes="权限码(sys:basedata:index)")
    @RequestMapping(value = "/getBaseDataInfo",method = RequestMethod.GET)
    @SofnLog(value = "查询基础数据列表接口")
    public Result<PageUtils<HashMap<String,Object>>> getBaseDataInfo(@RequestParam(name="typekeyname",required = false) @ApiParam(value = "基础数据类型名称或者基础数据名称关键字") String typekeyname,
                              @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                              @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize){

        PageUtils<HashMap<String,Object>> res = sysBaseDataService.getBaseDataInfo(typekeyname, pageNo, pageSize);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据基础数据名称查询基础数据数据接口",notes="权限码(sys:basedata:getBaseDataByName)")
//    @RequiresPermissions("sys:basedata:getBaseDataByName")
    @ResponseBody
    @RequestMapping(value = "/getBaseDataByName",method = RequestMethod.GET)
    @SofnLog(value = "根据基础数据名称查询基础数据数据接口")
    public Result<List<SysBaseData>> getBaseDataByName(@ApiParam(required = true, value = "基础数据名称") @RequestParam(value = "baseDataname") String basedataname){
        List<SysBaseData> res = sysBaseDataService.getBaseDataByName(basedataname);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据基础数据值和类型查询基础数据数据接口",notes="权限码(sys:basedata:getBaseDataByValueAndType)")
//    @RequiresPermissions("sys:basedata:getBaseDataByValueAndType")
    @RequestMapping(value = "/getBaseDataByValueAndType",method = RequestMethod.GET)
    @ResponseBody
    @SofnLog(value = "根据基础数据值和类型查询基础数据数据接口")
    public Result<List<SysBaseData>> getBaseDataByValueAndType(@ApiParam(required = true, value = "基础数据类型ID") @RequestParam(value = "basedatatypeid") String basedatatypeid,
                                        @ApiParam(value = "基础数据名称") @RequestParam(value = "baseDataname",required = false) String basedataname,
                                        @ApiParam(value = "基础数据值") @RequestParam(value = "baseDatacode",required = false) String basedatacode){
        List<SysBaseData> res = sysBaseDataService.getBaseDataByValueAndType(basedatatypeid,basedataname,basedatacode,null);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据基础数据值和类型查询基础数据名称数据接口",notes="权限码(sys:basedata:getBaseDataNameByValueAndType)")
//    @RequiresPermissions("sys:basedata:getBaseDataNameByValueAndType")
    @RequestMapping(value = "/getBaseDataNameByValueAndType",method = RequestMethod.GET)
    @ResponseBody
    @SofnLog(value = "根据基础数据值和类型查询基础数据名称数据接口")
    public Result<List<SysBaseData>> getBaseDataNameByValueAndType(@ApiParam(required = true, value = "基础数据类型名称") @RequestParam(value = "typename") String typename,
                                            @ApiParam(value = "基础数据值") @RequestParam(value = "baseDatacode",required = false) String basedatacode){
        List<SysBaseData> res = sysBaseDataService.getBaseDataNameByValueAndType(typename,basedatacode);
        return Result.ok(res);
    }

    @ApiOperation(value = "增加基础数据数据接口",notes = "权限码(sys:basedata:create)")
//    @RequiresPermissions("sys:basedata:saveBaseDataInfo")
    @RequestMapping(value = "/saveBaseDataInfo",method = RequestMethod.POST)
    @ResponseBody
    @SofnLog(value = "增加基础数据数据接口", type = "新增")
    public Result<String> saveBaseDataInfo(@ApiParam(value = "字典ID") @RequestParam(value = "id",required = false) String id,
                                @ApiParam(required = true, value = "基础数据类型") @RequestParam(value = "basedatatypeid") String basedatatypeid,
                               @ApiParam(required = true, value = "基础数据名字") @RequestParam(value = "basedataname") String basedataname,
                               @ApiParam(required = true, value = "基础数据代码") @RequestParam(value = "basedatacode") String basedatacode,
                               @ApiParam(value = "基础数据状态") @RequestParam(value = "enable",required = false) String enable,
                               @ApiParam(value = "备注") @RequestParam(value = "remark",required = false) String remark){

        SysBaseDataVo sysBaseDataVo = new SysBaseDataVo();
        sysBaseDataVo.setId(id);
        sysBaseDataVo.setBaseDataname(basedataname);
        sysBaseDataVo.setBaseDatacode(basedatacode);
        sysBaseDataVo.setEnable(enable);
        sysBaseDataVo.setRemark(remark);

        if (StringUtils.isBlank(id)){
            SysBaseDataTypeVo sysBaseDataTypeVo = new SysBaseDataTypeVo();
            sysBaseDataTypeVo.setId(basedatatypeid);
            sysBaseDataVo.setSysBaseDataTypeVo(sysBaseDataTypeVo);

            List<SysBaseData> checkRes = sysBaseDataService.getBaseDataByValueAndType(basedatatypeid, basedataname,basedatacode, null);
            if(!CollectionUtils.isEmpty(checkRes)){
                throw new SofnException("数据字典值代码或者名称已存在");
            }
            sysBaseDataService.saveBaseDataInfo(sysBaseDataVo);
            return Result.ok("添加成功");
        }else {
            List<SysBaseData> baseDataById = sysBaseDataService.getBaseDataById(id);
            if(CollectionUtils.isEmpty(baseDataById)){
                throw new SofnException("待修改信息不存在");
            }
            SysBaseData sysBaseData = baseDataById.get(0);
            sysBaseData.setBaseDatacode(basedatacode);
            sysBaseData.setBaseDataname(basedataname);
            sysBaseData.setRemark(remark);
            sysBaseData.setEnable(enable);
            sysBaseData.preUpdate();
            sysBaseDataService.updateBaseDataInfoById(sysBaseData);
            return Result.ok("修改成功");
        }


    }

    @ApiOperation(value = "修改基础数据数据接口",notes = "权限码(sys:basedata:update)")
    @ResponseBody
    @SofnLog(value = "修改基础数据数据接口", type = "修改")
//    @RequiresPermissions("sys:basedata:updateBaseDataInfo")
    @RequestMapping(value = "/updateBaseDataInfo",method = RequestMethod.PUT)
    public Result<String> updateBaseDataInfo(@ApiParam(required = true, value = "基础数据ID") @RequestParam(value = "id") String id,
                                 @ApiParam(required = true, value = "基础数据状态") @RequestParam(value = "enable") String enable){
        sysBaseDataService.updateBaseDataInfo(id,enable);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "刪除基础数据数据接口",notes = "权限码(sys:basedata:delete)")
//    @RequiresPermissions("sys:basedata:deleteBaseDataInfo")
    @RequestMapping(value = "/deleteBaseDataInfo",method = RequestMethod.DELETE)
    @ResponseBody
    @SofnLog(value = "刪除基础数据数据接口", type = "删除")
    public Result<String> deleteBaseDataInfo(@ApiParam(required = true, value = "基础数据ID") @RequestParam(value = "ids") String ids){
        sysBaseDataService.deleteBaseDataInfo(ids);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "查询当前类型下的所有基础数据",notes = "权限码(sys:basedata:getBaseDataListByType)")
    //    @RequiresPermissions("sys:basedata:getBaseDataListByType")
    @ResponseBody
    @GetMapping(value = "/getBaseDataListByType")
    @SofnLog(value = "查询当前类型下的所有基础数据")
    public Result<List<SysBaseData>> getBaseDataListByType(@ApiParam(required = true, value = "基础数据类型值") @RequestParam(value = "typevalue") String typevalue){
        List<SysBaseData> sysBaseDataList = sysBaseDataService.getBaseDataListByType(typevalue);
        return Result.ok(sysBaseDataList);
    }

    @ApiOperation(value = "批量导出接口",notes = "权限码(sys:basedata:export)")
//    @RequiresPermissions("sys:basedata:exportBaseData")
    @GetMapping( "/exportBaseData")
    public void exportBaseData(HttpServletResponse response){

//        String filePath =  "C:\\Users\\zhouqingchun\\Desktop\\export.xlsx";
        String fileName = "basedata.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + File.separator + fileName;
        try{
            sysBaseDataService.exportBaseData(filePath);
            FileDownloadUtils.downloadAndDeleteFile(filePath,response);
        }catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
    }

    @ApiOperation(value = "根据系统APPID、基础数据类型、基础数据名称查询基础数据",notes = "权限码(sys:basedata:getByAppIdAndTypeAndKey)")
    @ResponseBody
    @GetMapping(value = "/getByAppIdAndTypeAndKey")
    public Result<BaseData> getByAppIdAndTypeAndKey(@ApiParam(required = true, value = "系统appId") @RequestParam(value = "appId") String appId,
                                                       @ApiParam(required = true, value = "基础数据类型") @RequestParam(value = "type") String type,
                                                       @ApiParam(required = true, value = "基础数据名称") @RequestParam(value = "key") String key){
        BaseData baseData = sysBaseDataService.getByAppIdAndTypeAndKey(appId,type,key);
        return Result.ok(baseData);
    }

    @ApiOperation(value = "根据系统APPID、基础数据类型、基础数据值查询基础数据",notes = "权限码(sys:basedata:getByAppIdAndTypeAndValue)")
    @ResponseBody
    @GetMapping(value = "/getByAppIdAndTypeAndValue")
    public Result<BaseData> getByAppIdAndTypeAndValue(@ApiParam(required = true, value = "系统appId") @RequestParam(value = "appId") String appId,
                                                       @ApiParam(required = true, value = "基础数据类型") @RequestParam(value = "type") String type,
                                                       @ApiParam(required = true, value = "基础数据名称") @RequestParam(value = "value") String value){
        BaseData baseData = sysBaseDataService.getByAppIdAndTypeAndValue(appId,type,value);
        return Result.ok(baseData);
    }

    @ApiOperation(value = "根据系统APPID、基础数据类型查询基础数据列表",notes = "权限码(sys:basedata:getByAppIdAndType)")
    @ResponseBody
    @GetMapping(value = "/getByAppIdAndType")

    public Result<List<BaseData>> getByAppIdAndTypeAndValue(@ApiParam(required = true, value = "系统appId") @RequestParam(value = "appId") String appId,
                                                      @ApiParam(required = true, value = "基础数据类型") @RequestParam(value = "type") String type){
        List<BaseData> baseDataList = sysBaseDataService.getByAppIdAndType(appId,type);
        return Result.ok(baseDataList);
    }

    @ApiOperation(value = "根据名称和字典类型查询代码", notes = "权限码：(sys:basedata:getBaseDataByDataTypeCodeAndNames)")
    @ResponseBody
    @GetMapping(value = "/getBaseDataByDataTypeCodeAndNames")
    public Result<List<BaseData>> getBaseDataByDataTypeCodeAndNames(@ApiParam(required = true, value = "系统appId") @RequestParam(value = "appId") String appId,
                                                                    @ApiParam(required = true, value = "基础数据类型代码") @RequestParam(value = "baseDataType") String baseDataType,
                                                                    @ApiParam(required = true, value = "基础数据名称，使用英文逗号隔开") @RequestParam(value = "baseDataNames")String baseDataNames){
        List<BaseData> baseDataCodeByNames = sysBaseDataService.getBaseDataCodeByNames(appId, baseDataType, baseDataNames);
        return Result.ok(baseDataCodeByNames);

    }


    @ApiOperation(value = "根据字典类型代码分页查询数据字典值", notes = "权限码：(sys:basedata:getBaseDataByPage)")
    @GetMapping(value = "/getBaseDataByPage")
    @ResponseBody
    public Result<PageUtils<SysBaseData>> getBaseDataByPage(
            @RequestParam(name="keyword") @ApiParam(value = "基础数据值的名称获取代码")String keyword,
            @RequestParam(name="baseDataTypeCode") @ApiParam(value = "基础数据类型ID") String baseDataTypeCode,
            @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                    @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize){

        PageUtils<SysBaseData> sysBaseDataByPage = sysBaseDataService.getSysBaseDataByPage(keyword, baseDataTypeCode, pageNo, pageSize);
        return Result.ok(sysBaseDataByPage);
    }


    @GetMapping(value = "/getImportTemplate", produces = "application/octet-stream")
    @ApiOperation(value = "获取导入数据模板", produces = "application/octet-stream", notes = "权限码：(sys:basedata:getImportTemplate)")
    public void getImportTemplate(HttpServletResponse response){
        String filePath = ExcelPropertiesUtils.getExcelFilePath();
        filePath += "/基础数据导入模板.xlsx";
        File file = new File(filePath);
        if (file.exists()) {
            FileDownloadUtils.downloadFile(filePath, response);
            log.info("磁盘中存在基础数据导入模板，不在重新生成");
        } else {
            ExcelExportUtil.createTemplate(filePath, ImportBaseDataVo.class);
            FileDownloadUtils.downloadFile(filePath, response);
        }
//        ExcelExportUtil.createExcel(response, ImportBaseDataVo.class, null);
    }


    @ApiOperation(value = "批量导入数据字典", notes = "权限码(sys:basedata:import)")
    @PostMapping(value = "/importBaseDataByExcel")
    public Result<String> importBaseDataByExcel(@ApiParam(value = "Excel文件") @RequestPart(value = "file") MultipartFile file){

        List<ImportBaseDataVo> importSysUserVoList = ExcelImportUtil.getDataByExcel(file, ImportBaseDataVo.class);
        if(CollectionUtils.isEmpty(importSysUserVoList)){
            throw new SofnException("请在Excel中输入内容");
        }
        sysBaseDataService.importBaseData(importSysUserVoList);

        return Result.ok("导入成功");
    }


    /**
     * 分页获取数据字典值和数据字典类型信息
     * @param keyword    关键字
     * @param pageNo   从哪条数据显示
     * @param pageSize  每页显示多少条
     * @return     PageUtils<SysBaseDataAndTypeVo>
     */
    @GetMapping(value = "/getSysBaseDataAndType")
    @ApiOperation(value = "按条件获取数据字典类型和值", notes = "权限码(sys:basedata:getSysBaseDataAndType)")
    public Result<PageUtils<SysBaseDataAndTypeVo>> getSysBaseDataAndType(@RequestParam(name = "keyword", required = false) String keyword,
                                                                         @RequestParam(name = "ids", required = false)  @ApiParam(value = "数据字典IDS，多个使用逗号隔开")   String ids,
                                                                         @RequestParam(name="appId", required = false)  @ApiParam(value = "系统代码")  String appId,
                                                                         @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                                          @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize

    ){
        Map<String,Object> params = Maps.newHashMap();
        if(!StringUtils.isBlank(keyword)){
            params.put("keyword", keyword);
        }
        if(!StringUtils.isBlank(ids)){
            params.put("ids", IdUtil.getIdsByStr(ids));
        }

        if(!StringUtils.isBlank(appId)){
            params.put("appId", appId);
        }
        PageUtils<SysBaseDataAndTypeVo> sysBaseDataAndType = sysBaseDataService.getSysBaseDataAndType(params, pageNo, pageSize);
        return Result.ok(sysBaseDataAndType);
    }


}
