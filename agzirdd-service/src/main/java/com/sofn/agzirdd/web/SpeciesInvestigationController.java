package com.sofn.agzirdd.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.excelmodel.SpeciesInvestigationExcel;
import com.sofn.agzirdd.model.InvestigatContent;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import com.sofn.agzirdd.service.InvestigatContentService;
import com.sofn.agzirdd.service.SpeciesInvestigationService;
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
@Api(value = "外来入侵-物种调查模块接口", tags = "外来入侵-物种调查模块接口")
@RestController
@RequestMapping("/speciesInvestigation")
public class SpeciesInvestigationController extends BaseController {

    /**
     * 物种调查模块-调查基本信息
     */
    @Autowired
    private SpeciesInvestigationService speciesInvestigationService;
    @Autowired
    private InvestigatContentService investigatContentService;


    @SofnLog("获取物种调查信息表单(分页)")
    @ApiOperation(value="获取物种调查信息表单(分页)")
    @PostMapping("/speciesInvestigationForm")
    public Result<PageUtils<SpeciesInvestigationForm>> speciesInvestigationForm(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpeciesInvestigationQueryVo speciesInvestigationQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = speciesInvestigationQueryVo.getPageNo();
        Integer pageSize = speciesInvestigationQueryVo.getPageSize();
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesInvestigationQueryVo, userId);
        PageUtils<SpeciesInvestigationForm> speciesInvestigationForm = speciesInvestigationService.getSpeciesInvestigationForm(params, pageNo, pageSize);
        return Result.ok(speciesInvestigationForm);
    }

    @SofnLog("获取指定id的物种调查信息")
    @ApiOperation(value="获取指定id的物种调查信息")
    @GetMapping("/getSpeciesInvestigationAllById")
    public Result<SpeciesInvestigationVo> getSpeciesInvestigationAllById(
            @ApiParam(name = "id",value = "物种调查信息ID",required = true)@RequestParam(value = "id")String id){

        SpeciesInvestigationVo speciesInvestigationAllById =
                speciesInvestigationService.getSpeciesInvestigationAllById(id);
        return Result.ok(speciesInvestigationAllById);
    }

    @SofnLog("新增物种调查信息")
    @ApiOperation(value="新增物种调查信息")
    @PostMapping("/addSpeciesInvestigation")
    public Result<String> addSpeciesInvestigation(
            @Validated @RequestBody @ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true)SpeciesInvestigationVo speciesInvestigationVo,
            HttpServletRequest request){
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_9.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_expert");
        }else{
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_0.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_county");
        }
        speciesInvestigationService.addSpeciesInvestigation(speciesInvestigationVo);
        return Result.ok("新增物种调查信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息")
    @ApiOperation(value="新增并上报生物监测点基本信息")
    @PostMapping("/addAndSubmit")
    public Result<String> addAndSubmit(
            @Validated @RequestBody @ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true)SpeciesInvestigationVo speciesInvestigationVo){

        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_10.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_expert");
        }else{
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_1.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_county");

        }
        speciesInvestigationService.addSpeciesInvestigation(speciesInvestigationVo);
        return Result.ok("新增并上报物种调查信息成功!");
    }

    @SofnLog("编辑物种调查信息")
    @ApiOperation(value="编辑物种调查信息")
    @PostMapping("/updateSpeciesInvestigation")
    public Result<String> updateSpeciesInvestigation(
            @Validated  @RequestBody@ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true) SpeciesInvestigationVo speciesInvestigationVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = speciesInvestigationVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){

            speciesInvestigationService.updateSpeciesInvestigation(speciesInvestigationVo);
            return  Result.ok("编辑物种调查信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");
    }

    @SofnLog("编辑并上报物种调查信息")
    @ApiOperation(value="编辑并上报物种调查信息")
    @PostMapping("/updateAndSubmit")
    public Result<String> updateAndSubmit(
            @Validated @RequestBody @ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true) SpeciesInvestigationVo speciesInvestigationVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = speciesInvestigationVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){

            //获取当前用户角色列表
            List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                speciesInvestigationVo.setStatus(StatusEnum.STATUS_10.getCode());
            }else{
                speciesInvestigationVo.setStatus(StatusEnum.STATUS_1.getCode());

            }
            speciesInvestigationService.updateSpeciesInvestigation(speciesInvestigationVo);
            return  Result.ok("编辑并上报物种调查信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }


    @SofnLog("驳回物种调查信息")
    @ApiOperation(value="驳回物种调查信息")
    @PostMapping("/backSpeciesInvestigation")
    public Result<String> backSpeciesInvestigation(
            @RequestBody @ApiParam(name = "审核退回对象", value = "传入json格式",required = true) BackObjVo backObjVo){
        if(StringUtils.isEmpty(backObjVo.getRemark()))
            return Result.error(500,"退回时审核意见不能为空");

        SpeciesInvestigation modal = speciesInvestigationService.getSpeciesInvestigationById(backObjVo.getId());
        if(modal==null)
            SofnExceptionUtil.throwError("数据未找到");

        AuditUtil.checkAuditRight(modal.getStatus());
        backObjVo.setStatus(AuditUtil.getRejectNewStatus(modal.getStatus()));

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",backObjVo.getId());
        params.put("status",backObjVo.getStatus());
        params.put("remark",backObjVo.getRemark());

        speciesInvestigationService.updateStatus(params);
        return Result.ok("当前物种调查信息已驳回!");
    }

    @SofnLog("确认通过物种调查信息")
    @ApiOperation(value="确认通过物种调查信息")
    @PostMapping("/passSpeciesInvestigation")
    public Result<String> passSpeciesInvestigation(
            @RequestBody @ApiParam(name = "审核通过对象", value = "传入json格式",required = true) PassObjVo passObjVo){
        SpeciesInvestigation modal = speciesInvestigationService.getSpeciesInvestigationById(passObjVo.getId());
        if(modal==null)
            SofnExceptionUtil.throwError("数据未找到");

        AuditUtil.checkAuditRight(modal.getStatus());
        passObjVo.setStatus(AuditUtil.getAcceptNewStatus(modal.getStatus()));

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",passObjVo.getId());
        params.put("status",passObjVo.getStatus());
        params.put("remark",passObjVo.getRemark());
        speciesInvestigationService.updateStatus(params);
        return Result.ok("已确认通过物种调查信息!");
    }

    @SofnLog("批量审核物种调查信息-总站")
    @ApiOperation(value="批量审核物种调查信息-总站")
    @PostMapping("/batchAuditSpeciesInvestigation")
    public Result<String> batchAuditSpeciesInvestigation(
            @RequestBody @ApiParam(name = "审核数据对象", value = "传入json格式",required = true) BatchAuditVo vo){
        if(StringUtils.isEmpty(vo.getRemark()))
            return Result.error(500,"审核意见不能为空");

        speciesInvestigationService.batchAudit(vo.getRemark(),vo.getItems());
        return Result.ok("审核物种调查信息成功!");
    }

    @SofnLog("删除物种调查信息")
    @ApiOperation(value="删除物种调查信息")
    @GetMapping("/removeSpeciesInvestigation")
    public Result<String> removeSpeciesInvestigation(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        SpeciesInvestigation speciesInvestigation = speciesInvestigationService.getSpeciesInvestigationById(id);
        if(speciesInvestigation == null){
            return Result.error("当前物种调查信息已不存在,出现错误数据显示!");
        }
        //判断当前数据状态是否为'已保存'已撤回,专家填报
        if(StatusEnum.canRemove(speciesInvestigation.getStatus())){
            speciesInvestigationService.removeSpeciesInvestigation(id);
            return Result.ok("当前物种调查信息已删除!");
        }
        return Result.error("当前物种调查信息该状态下不可删除!");
    }

    @SofnLog("撤回当前物种调查信息")
    @ApiOperation(value="撤回当前物种调查信息")
    @GetMapping("/recallSpeciesInvestigation")
    public Result<String> recallSpeciesInvestigation(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status01 = StatusEnum.STATUS_1.getCode();
        String status10 = StatusEnum.STATUS_10.getCode();

        SpeciesInvestigation speciesInvestigation = speciesInvestigationService.getSpeciesInvestigationById(id);
        //判断当前数据状态是否为'已提交'状态
        if(status01.equals(speciesInvestigation.getStatus()) || status10.equals(speciesInvestigation.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            speciesInvestigationService.updateStatus(params);
            return Result.ok("当前物种调查信息已撤回!");
        }
        return Result.error("当前物种调查信息该状态下不可撤回!");
    }

    @SofnLog(value = "物种调查信息导出")
    @ApiOperation(value = "物种调查信息导出")
    @PostMapping(value = "/exportSpeciesInvestigation", produces = "application/octet-stream")
    public void exportSpeciesInvestigation(
            @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式") SpeciesInvestigationQueryVo speciesInvestigationQueryVo,
            HttpServletResponse response,HttpServletRequest request) throws Exception {

        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesInvestigationQueryVo, userId);
        List<SpeciesInvestigation> speciesInvestigationList =
                speciesInvestigationService.getSpeciesInvestigationListByParam(params);

        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/speciesInvestigation.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, speciesInvestigationList.size());
        Workbook workbook = exportDetailUtil.getWorkbook();
        for (int i = 0; i < speciesInvestigationList.size(); i++) {
            //获取导出数据对象
            SpeciesInvestigationExcel speciesInvestigationExcel = speciesInvestigationService.getSpeciesInvestigationExcel(speciesInvestigationList.get(i).getId());

            JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(speciesInvestigationExcel));
            //表格excel中sheet名称
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String timeStr = formatter.format(speciesInvestigationExcel.getInvestigationTime());
            String sheetName = i+1+"-"+timeStr+"-"+speciesInvestigationExcel.getAreaName();
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
        response.setHeader("Content-Disposition", "attachment;filename=" + "外来入侵物种调查数据.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("外来入侵物种调查数据.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    /**
     * 获取查询条件
     * @param speciesInvestigationQueryVo speciesInvestigationQueryVo
     * @param userId userId
     * @return map
     */
    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式") @RequestBody SpeciesInvestigationQueryVo speciesInvestigationQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();

        params.put("inStatus",StatusEnum.getShowStatusByRole(RoleCodeUtil.getLoginUserAgzirddRoleCode()));
        params.put("formNumber", speciesInvestigationQueryVo.getFormNumber());
        params.put("investigator", speciesInvestigationQueryVo.getInvestigator());
        if (StringUtils.isNotBlank(speciesInvestigationQueryVo.getProvinceId())) {
            params.put("provinceId", speciesInvestigationQueryVo.getProvinceId());
        }
        if (StringUtils.isNotBlank(speciesInvestigationQueryVo.getCityId())) {
            params.put("cityId", speciesInvestigationQueryVo.getCityId());
        }
        if (StringUtils.isNotBlank(speciesInvestigationQueryVo.getCountyId())) {
            params.put("countyId", speciesInvestigationQueryVo.getCountyId());
        }
        params.put("status", speciesInvestigationQueryVo.getStatus());
        params.put("beginDate", speciesInvestigationQueryVo.getBeginDate());
        params.put("endDate", speciesInvestigationQueryVo.getEndDate());
        params.put("speciesId", speciesInvestigationQueryVo.getSpeciesId());
        //是否为县级人员
        if (roleColde != null && roleColde.contains("agzirdd_county")) {
            //params.put("roleCode", "agzirdd_county");
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

    @SofnLog("判断入侵物种是否为本省新发物种")
    @ApiOperation(value="判断入侵物种是否为本省新发物种,0-否;1-是")
    @GetMapping("/isBreakthroughInProvince")
    public Result<String> isBreakthroughInProvince(@ApiParam(name = "speciesName", value = "传入物种名", required = true)@RequestParam(value = "speciesName") String speciesName){
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("speciesName", speciesName);
        List<InvestigatContent> list = investigatContentService.getInvestigatContentByQuery(params);
        return Result.ok(list.isEmpty()?"1":"0");
    }
}
