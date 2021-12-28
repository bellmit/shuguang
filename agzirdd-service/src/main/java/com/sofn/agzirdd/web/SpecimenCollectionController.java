package com.sofn.agzirdd.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.enums.TypeEnum;
import com.sofn.agzirdd.excelmodel.CollectionAnimalSpecimenExcel;
import com.sofn.agzirdd.excelmodel.CollectionMicrobeSpecimenExcel;
import com.sofn.agzirdd.excelmodel.CollectionPlantSpecimenExcel;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import com.sofn.agzirdd.model.SpecimenCollection;
import com.sofn.agzirdd.service.SpecimenCollectionService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.AuditUtil;
import com.sofn.agzirdd.util.ExportDetailUtil;
import com.sofn.agzirdd.util.RoleCodeUtil;
import com.sofn.agzirdd.util.SofnExceptionUtil;
import com.sofn.agzirdd.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "外来入侵-物种采集模块接口", tags = "外来入侵-物种采集模块接口")
@RestController
@RequestMapping("/specimenCollection")
public class SpecimenCollectionController extends BaseController {

    /**
     * 物种采集模块-标本采集基本信息
     */
    @Autowired
    private SpecimenCollectionService specimenCollectionService;

    @SofnLog("获取物种标本采集信息(分页)")
    @ApiOperation(value="获取物种标本采集信息(分页)")
    @PostMapping("/specimenCollectionForm")
    public Result<PageUtils<SpecimenCollectionForm>> specimenCollectionForm(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpecimenCollectionQueryVo specimenCollectionQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = specimenCollectionQueryVo.getPageNo();
        Integer pageSize = specimenCollectionQueryVo.getPageSize();
        Map<String, Object> params = getParamsByLogin(specimenCollectionQueryVo, userId);

        PageUtils<SpecimenCollectionForm> specimenCollectionForm = specimenCollectionService.getSpecimenCollectionForm(params, pageNo, pageSize);
        return Result.ok(specimenCollectionForm);
    }

    /**
     * 获取查询条件
     * @param specimenCollectionQueryVo specimenCollectionQueryVo
     * @param userId userId
     * @return map
     */
    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式", required = true) @RequestBody @Validated SpecimenCollectionQueryVo specimenCollectionQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("gatherer", specimenCollectionQueryVo.getGatherer());
        if (StringUtils.isNotBlank(specimenCollectionQueryVo.getProvinceId())) {
            params.put("provinceId", specimenCollectionQueryVo.getProvinceId());
        }
        if (StringUtils.isNotBlank(specimenCollectionQueryVo.getCityId())) {
            params.put("cityId", specimenCollectionQueryVo.getCityId());
        }
        if (StringUtils.isNotBlank(specimenCollectionQueryVo.getCountyId())) {
            params.put("countyId", specimenCollectionQueryVo.getCountyId());
        }

        params.put("inStatus",StatusEnum.getShowStatusByRole(RoleCodeUtil.getLoginUserAgzirddRoleCode()));
        params.put("status", specimenCollectionQueryVo.getStatus());
        params.put("beginDate", specimenCollectionQueryVo.getBeginDate());
        params.put("endDate", specimenCollectionQueryVo.getEndDate());
        String type = specimenCollectionQueryVo.getType();
        String speciesId = specimenCollectionQueryVo.getSpeciesId();
        //判断当前类型->植物,动物,微生物
        if(TypeEnum.PLANT.getCode().equals(type)){
            params.put("plantSpeciesId", speciesId);
        }else if(TypeEnum.ANIMAL.getCode().equals(type)){
            params.put("animalSpeciesId", speciesId);
        }else if(TypeEnum.MICROBE.getCode().equals(type)){
            params.put("microbeSpeciesId", speciesId);
        }
        params.put("type", type);
        //是否为县级人员
        if (roleColde != null && roleColde.contains("agzirdd_county")) {
            params.put("roleCode", "agzirdd_county");
            //params.put("countyId", regionLastCode);
            params.put("createUserId", userId);
        }
        //市级人员
        if (roleColde != null && roleColde.contains("agzirdd_city")) {
            params.put("roleCode", "agzirdd_county");
            params.put("cityId", regionLastCode);
        }
        //省级人员
        if (roleColde != null && roleColde.contains("agzirdd_province")) {
            params.put("roleCode", "agzirdd_county");
            params.put("provinceId", regionLastCode);
        }
        //是否为专家填报
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            params.put("roleCode", "agzirdd_expert");
            params.put("createUserId", userId);
        }
        return params;
    }

    @SofnLog("获取指定id的物种标本采集信息")
    @ApiOperation(value="获取指定id的物种标本采集信息")
    @GetMapping("/getSpecimenCollectionAllById")
    public Result<SpecimenCollectionVo> getSpecimenCollectionAllById(
            @ApiParam(name = "id",value = "物种采集信息ID",required = true)@RequestParam(value = "id")String id){

        SpecimenCollectionVo specimenCollectionAllById = specimenCollectionService.getSpecimenCollectionAllById(id);
        return Result.ok(specimenCollectionAllById);
    }

    @SofnLog("新增物种标本采集信息")
    @ApiOperation(value="新增物种标本采集信息")
    @PostMapping("/addSpecimenCollection")
    public Result<String> addSpecimenCollection(
            @Validated @RequestBody @ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true)SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            specimenCollectionVo.setStatus(StatusEnum.STATUS_9.getCode());
        }else{
            specimenCollectionVo.setStatus(StatusEnum.STATUS_0.getCode());
        }
        specimenCollectionService.addSpecimenCollection(specimenCollectionVo);
        return Result.ok("新增物种标本采集信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息")
    @ApiOperation(value="新增并上报生物监测点基本信息")
    @PostMapping("/addAndSubmit")
    public Result<String> addAndSubmit(
            @Validated @RequestBody @ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true)SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            specimenCollectionVo.setStatus(StatusEnum.STATUS_10.getCode());
        }else{
            specimenCollectionVo.setStatus(StatusEnum.STATUS_1.getCode());
        }
        specimenCollectionService.addSpecimenCollection(specimenCollectionVo);
        return Result.ok("新增并上报物种标本采集信息成功!");
    }

    @SofnLog("编辑物种标本采集信息")
    @ApiOperation(value="编辑物种标本采集信息")
    @PostMapping("/updateSpecimenCollection")
    public Result<String> updateSpecimenCollection(
            @Validated  @RequestBody@ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true) SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = specimenCollectionVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){
            specimenCollectionService.updateSpecimenCollection(specimenCollectionVo);
            return Result.ok("编辑物种标本采集信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");

    }

    @SofnLog("编辑并上报物种标本采集信息")
    @ApiOperation(value="编辑并上报物种标本采集信息")
    @PostMapping("/updateAndSubmit")
    public Result<String> updateAndSubmit(
            @Validated @RequestBody @ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true) SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(specimenCollectionVo.getId());
        String status = specimenCollection.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){

            //获取当前用户角色列表
            List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                specimenCollectionVo.setStatus(StatusEnum.STATUS_10.getCode());
            }else{
                specimenCollectionVo.setStatus(StatusEnum.STATUS_1.getCode());
            }
            specimenCollectionService.updateSpecimenCollection(specimenCollectionVo);
            return Result.ok("编辑并上报物种标本采集信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("驳回物种标本采集信息")
    @ApiOperation(value="驳回物种标本采集信息")
    @PostMapping("/backSpecimenCollection")
    public Result<String> backSpecimenCollection(
            @RequestBody @ApiParam(name = "审核退回对象", value = "传入json格式",required = true) BackObjVo backObjVo){
        if(StringUtils.isEmpty(backObjVo.getRemark()))
            return Result.error(500,"退回时审核意见不能为空");
        SpecimenCollection modal = specimenCollectionService.getSpecimenCollectionById(backObjVo.getId());
        if(modal==null)
            SofnExceptionUtil.throwError("数据未找到");

        AuditUtil.checkAuditRight(modal.getStatus());
        backObjVo.setStatus(AuditUtil.getRejectNewStatus(modal.getStatus()));

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",backObjVo.getId());
        params.put("status",backObjVo.getStatus());
        params.put("remark",backObjVo.getRemark());
        specimenCollectionService.updateStatus(params);
        return Result.ok("当前物种标本采集信息已驳回!");
    }

    @SofnLog("确认通过物种标本采集信息")
    @ApiOperation(value="确认通过物种标本采集信息")
    @PostMapping("/passSpecimenCollection")
    public Result<String> passSpecimenCollection(
            @RequestBody @ApiParam(name = "审核通过对象", value = "传入json格式",required = true) PassObjVo passObjVo){
        SpecimenCollection modal = specimenCollectionService.getSpecimenCollectionById(passObjVo.getId());
        if(modal==null)
            SofnExceptionUtil.throwError("数据未找到");

        AuditUtil.checkAuditRight(modal.getStatus());
        passObjVo.setStatus(AuditUtil.getAcceptNewStatus(modal.getStatus()));

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",passObjVo.getId());
        params.put("status",passObjVo.getStatus());
        params.put("remark",passObjVo.getRemark());
        specimenCollectionService.updateStatus(params);
        return Result.ok("已确认通过物种标本采集信息!");
    }

    @SofnLog("删除物种标本采集信息")
    @ApiOperation(value="删除物种标本采集信息")
    @GetMapping("/removeSpecimenCollection")
    public Result<String> removeSpecimenCollection(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(id);
        if(specimenCollection == null){
            return Result.error("当前物种标本采集信息已不存在,出现错误数据显示!");
        }
        //判断当前数据状态是否为'已保存'状态
        if(StatusEnum.canRemove(specimenCollection.getStatus())){
            specimenCollectionService.removeSpecimenCollection(id);
            return Result.ok("当前物种标本采集信息已删除!");
        }
        return Result.error("当前物种标本采集信息该状态下不可删除!");
    }

    @SofnLog("撤回当前物种标本采集信息")
    @ApiOperation(value="撤回当前物种标本采集信息")
    @GetMapping("/recallSpecimenCollection")
    public Result<String> recallSpecimenCollection(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status01 = StatusEnum.STATUS_1.getCode();
        String status10 = StatusEnum.STATUS_10.getCode();

        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(id);
        //判断当前数据状态是否为'已提交'状态
        if(status01.equals(specimenCollection.getStatus()) || status10.equals(specimenCollection.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            specimenCollectionService.updateStatus(params);
            return Result.ok("当前物种标本采集信息已撤回!");
        }
        return Result.error("当前物种标本采集信息该状态下不可撤回!");
    }

    @SofnLog(value = "物种采集信息导出")
    @ApiOperation(value = "物种采集信息导出")
    @PostMapping(value = "/exportSpecimenCollection", produces = "application/octet-stream")
    public void exportSpecimenCollection(
            @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式") SpecimenCollectionQueryVo specimenCollectionQueryVo,
            HttpServletResponse response,HttpServletRequest request) throws Exception {

        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        //获取当前用户所属机构区划信息
        Map<String, Object> params = getParamsByLogin(specimenCollectionQueryVo, userId);
        //获取采集标本类型
        String type = specimenCollectionQueryVo.getType();
        //获取导出数据的类型(植物,动物,微生物)
        List<SpecimenCollection> specimenCollectionList = specimenCollectionService.getSpecimenCollectionListByParam(params);

        if(TypeEnum.PLANT.getCode().equals(type)){
            //采集类型->植物
            ClassPathResource resource = new ClassPathResource("static/collectionPlantSpecimen.xlsx");
            InputStream inputStream = resource.getInputStream();
            ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, specimenCollectionList.size());
            Workbook workbook = exportDetailUtil.getWorkbook();
            for (int i = 0; i < specimenCollectionList.size(); i++) {

                CollectionPlantSpecimenExcel collectionPlantSpecimenExcel = specimenCollectionService.getCollectionPlantSpecimenExcel(specimenCollectionList.get(i).getId());

                JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(collectionPlantSpecimenExcel));
                //表格excel中sheet名称
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String timeStr = formatter.format(collectionPlantSpecimenExcel.getCollectTime());
                String sheetName = i+1+"-"+timeStr+"-"+collectionPlantSpecimenExcel.getAreaName();
                workbook.setSheetName(i,sheetName);
                exportDetailUtil.setSheet(workbook.getSheetAt(i));
                Sheet sheet = exportDetailUtil.getSheet();
                int colum=sheet.getColumnWidth(0);
                int rowNumber = sheet.getLastRowNum();
                for (int j = 0; j <= rowNumber; j++) {
                    Row row = sheet.getRow(j);
                    int colNumber = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < colNumber; k++) {
                        Cell cell = row.getCell(k);
                        if (cell != null) {
                            String cellValue = cell.getStringCellValue() == null ? "" : cell.getStringCellValue();
                            if (cellValue.contains("#")) {
                                exportDetailUtil.replaceCellValue(j, k, jo.get(cellValue.substring(1)));
                            }
                        }
                    }
                }
            }
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + "外来入侵物种采集数据.xlsx" + ";filename*=utf-8''"
                    + URLEncoder.encode("外来入侵物种采集数据.xlsx", "utf-8"));
            OutputStream os = response.getOutputStream();
            inputStream.close();
            exportDetailUtil.write(os);

        }else if(TypeEnum.ANIMAL.getCode().equals(type)){
            //采集类型->动物
            ClassPathResource resource = new ClassPathResource("static/collectionAnimalSpecimen.xlsx");
            InputStream inputStream = resource.getInputStream();
            ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, specimenCollectionList.size());
            Workbook workbook = exportDetailUtil.getWorkbook();
            for (int i = 0; i < specimenCollectionList.size(); i++) {

                CollectionAnimalSpecimenExcel collectionAnimalSpecimenExcel =
                        specimenCollectionService.getCollectionAnimalSpecimenExcel(specimenCollectionList.get(i).getId());

                JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(collectionAnimalSpecimenExcel));
                //表格excel中sheet名称
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String timeStr = formatter.format(collectionAnimalSpecimenExcel.getCollectTime());
                String sheetName = i+1+"-"+timeStr+"-"+collectionAnimalSpecimenExcel.getAreaName();
                workbook.setSheetName(i,sheetName);
                exportDetailUtil.setSheet(workbook.getSheetAt(i));
                Sheet sheet = exportDetailUtil.getSheet();
                int colum=sheet.getColumnWidth(0);
                int rowNumber = sheet.getLastRowNum();
                for (int j = 0; j <= rowNumber; j++) {
                    Row row = sheet.getRow(j);
                    int colNumber = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < colNumber; k++) {
                        Cell cell = row.getCell(k);
                        if (cell != null) {
                            String cellValue = cell.getStringCellValue() == null ? "" : cell.getStringCellValue();
                            if (cellValue.contains("#")) {
                                exportDetailUtil.replaceCellValue(j, k, jo.get(cellValue.substring(1)));
                            }
                        }
                    }

                }
            }
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + "外来入侵物种采集数据.xlsx" + ";filename*=utf-8''"
                    + URLEncoder.encode("外来入侵物种采集数据.xlsx", "utf-8"));
            OutputStream os = response.getOutputStream();
            inputStream.close();
            exportDetailUtil.write(os);
        }else{
            //采集类型->微生物
            ClassPathResource resource = new ClassPathResource("static/collectionMicrobeSpecimen.xlsx");
            InputStream inputStream = resource.getInputStream();
            ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, specimenCollectionList.size());
            Workbook workbook = exportDetailUtil.getWorkbook();
            for (int i = 0; i < specimenCollectionList.size(); i++) {

                CollectionMicrobeSpecimenExcel collectionMicrobeSpecimenExcel =
                        specimenCollectionService.getCollectionMicrobeSpecimenExcel(specimenCollectionList.get(i).getId());

                JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(collectionMicrobeSpecimenExcel));
                //表格excel中sheet名称
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String timeStr = formatter.format(collectionMicrobeSpecimenExcel.getCollectTime());
                String sheetName = i+1+"-"+timeStr+"-"+collectionMicrobeSpecimenExcel.getAreaName();
                workbook.setSheetName(i,sheetName);
                exportDetailUtil.setSheet(workbook.getSheetAt(i));
                Sheet sheet = exportDetailUtil.getSheet();
                int colum=sheet.getColumnWidth(0);
                int rowNumber = sheet.getLastRowNum();
                for (int j = 0; j <= rowNumber; j++) {
                    Row row = sheet.getRow(j);
                    int colNumber = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < colNumber; k++) {
                        Cell cell = row.getCell(k);
                        if (cell != null) {
                            String cellValue = cell.getStringCellValue() == null ? "" : cell.getStringCellValue();
                            if (cellValue.contains("#")) {
                                exportDetailUtil.replaceCellValue(j, k, jo.get(cellValue.substring(1)));
                            }
                        }
                    }
                }
            }
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + "外来入侵物种采集数据.xlsx" + ";filename*=utf-8''"
                    + URLEncoder.encode("外来入侵物种采集数据.xlsx", "utf-8"));
            OutputStream os = response.getOutputStream();
            inputStream.close();
            exportDetailUtil.write(os);
        }
    }

}
