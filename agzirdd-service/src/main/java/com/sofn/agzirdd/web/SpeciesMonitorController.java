package com.sofn.agzirdd.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.excelmodel.SpeciesMonitorExcel;
import com.sofn.agzirdd.model.SpeciesMonitor;
import com.sofn.agzirdd.service.SpeciesMonitorService;
import com.sofn.agzirdd.sysapi.SysDropDownApi;
import com.sofn.agzirdd.sysapi.bean.DropDownVo;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.ExportDetailUtil;
import com.sofn.agzirdd.util.RoleCodeUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "外来入侵-物种监测模块接口", tags = "外来入侵-物种监测模块接口")
@RestController
@RequestMapping("/speciesMonitor")
public class SpeciesMonitorController extends BaseController {

    /**
     * 物种监测 -基本信息
     */
    @Autowired
    private SpeciesMonitorService speciesMonitorService;

    /**
     * 下拉框接口
     */
    @Autowired
    private SysDropDownApi sysDropDownApi;


    @SofnLog("获取物种监测信息表单(分页)")
    @ApiOperation(value="获取物种监测信息表单(分页)")
    @PostMapping("/speciesMonitorForm")
    public Result<PageUtils<SpeciesMonitorForm>> speciesMonitorForm(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpeciesMonitorQueryVo speciesMonitorQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = speciesMonitorQueryVo.getPageNo();
        Integer pageSize = speciesMonitorQueryVo.getPageSize();
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesMonitorQueryVo, userId);
        PageUtils<SpeciesMonitorForm> speciesMonitorForm = speciesMonitorService.getSpeciesMonitorForm(params, pageNo, pageSize);
        return Result.ok(speciesMonitorForm);
    }

    @SofnLog("获取指定id的物种监测信息")
    @ApiOperation(value="获取指定id的物种监测信息")
    @GetMapping("/getSpeciesMonitorAllById")
    public Result<SpeciesMonitorVo> getSpeciesMonitorAllById(
            @ApiParam(name = "id",value = "物种监测信息ID",required = true)@RequestParam(value = "id")String id){

        SpeciesMonitorVo speciesMonitorAllById = speciesMonitorService.getSpeciesMonitorAllById(id);
        return Result.ok(speciesMonitorAllById);
    }

    @SofnLog("新增物种监测信息")
    @ApiOperation(value="新增物种监测信息")
    @PostMapping("/addSpeciesMonitor")
    public Result<String> addSpeciesMonitor(
            @Validated @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true)SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesMonitorVo.setStatus(StatusEnum.STATUS_9.getCode());
        }else{
            speciesMonitorVo.setStatus(StatusEnum.STATUS_0.getCode());
        }
        speciesMonitorService.addSpeciesMonitor(speciesMonitorVo);
        return Result.ok("新增物种监测信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息")
    @ApiOperation(value="新增并上报生物监测点基本信息")
    @PostMapping("/addAndSubmit")
    public Result<String> addAndSubmit(
            @Validated @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true)SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesMonitorVo.setStatus(StatusEnum.STATUS_10.getCode());
        }else{
            speciesMonitorVo.setStatus(StatusEnum.STATUS_1.getCode());
        }
        speciesMonitorService.addSpeciesMonitor(speciesMonitorVo);
        return Result.ok("新增并上报物种监测信息成功!");
    }

    @SofnLog("编辑物种监测信息")
    @ApiOperation(value="编辑物种监测信息")
    @PostMapping("/updateSpeciesMonitor")
    public Result<String> updateSpeciesMonitor(
            @Validated @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true)SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = speciesMonitorVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){
            speciesMonitorService.updateSpeciesMonitor(speciesMonitorVo);
            return Result.ok("编辑物种监测信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("编辑并上报物种监测信息")
    @ApiOperation(value="编辑并上报物种监测信息")
    @PostMapping("/updateAndSubmit")
    public Result<String> updateAndSubmit(
            @Validated @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true)SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(speciesMonitorVo.getId());
        String status = speciesMonitor.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){
            //获取当前用户角色列表
            List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                speciesMonitorVo.setStatus(StatusEnum.STATUS_10.getCode());
            }else{
                speciesMonitorVo.setStatus(StatusEnum.STATUS_1.getCode());
            }
            speciesMonitorService.updateSpeciesMonitor(speciesMonitorVo);
            return Result.ok("编辑并上报物种监测信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("驳回物种监测信息")
    @ApiOperation(value="驳回物种监测信息")
    @PostMapping("/backSpeciesMonitor")
    public Result<String> backSpeciesMonitor(
            @RequestBody @ApiParam(name = "审核退回对象", value = "传入json格式",required = true) AuditDataVo vo){
        if(StringUtils.isEmpty(vo.getRemark()))
            return Result.error(500,"退回时审核意见不能为空");

        vo.setAccept(false);
        speciesMonitorService.updateAudit(vo);
        return Result.ok("当前物种监测信息已驳回!");
    }

    @SofnLog("确认通过物种监测信息")
    @ApiOperation(value="确认通过物种监测信息")
    @PostMapping("/passSpeciesMonitor")
    public Result<String> passSpeciesMonitor(
            @RequestBody @ApiParam(name = "审核通过对象", value = "传入json格式",required = true) AuditDataVo vo){
        vo.setAccept(true);
        speciesMonitorService.updateAudit(vo);
        return Result.ok("已确认通过物种监测信息!");
    }

    @SofnLog("删除物种监测信息")
    @ApiOperation(value="删除物种监测信息")
    @GetMapping("/removeSpeciesMonitor")
    public Result<String> removeSpeciesMonitor(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(id);
        if(speciesMonitor == null){
            return Result.error("当前物种监测信息已不存在,出现错误数据显示!");
        }
        //判断当前数据状态是否为'已保存',已撤回,专家填报
        if(StatusEnum.canRemove(speciesMonitor.getStatus())){
            speciesMonitorService.removeSpeciesMonitor(id);
            return Result.ok("当前物种监测信息已删除!");
        }
        return Result.error("当前物种监测信息该状态下不可删除!");
    }

    @SofnLog("撤回当前物种监测信息")
    @ApiOperation(value="撤回当前物种监测信息")
    @GetMapping("/recallSpeciesMonitor")
    public Result<String> recallSpeciesMonitor(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status01 = StatusEnum.STATUS_1.getCode();
        String status10 = StatusEnum.STATUS_10.getCode();

        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(id);
        //判断当前数据状态是否为'已提交'专家提交
        if(status01.equals(speciesMonitor.getStatus()) || status10.equals(speciesMonitor.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            speciesMonitorService.updateStatus(params);
            return Result.ok("当前物种监测信息已撤回!");
        }
        return Result.error("当前物种监测信息该状态下不可撤回!");
    }

    @SofnLog(value = "物种监测信息导出")
    @ApiOperation(value = "物种监测信息导出")
    @PostMapping(value = "/exportSpeciesMonitor", produces = "application/octet-stream")
    public void exportSpeciesMonitor(
            @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式") SpeciesMonitorQueryVo speciesMonitorQueryVo,
            HttpServletResponse response,HttpServletRequest request) throws Exception {

        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesMonitorQueryVo, userId);
        List<SpeciesMonitor> speciesMonitorList = speciesMonitorService.getSpeciesMonitorListByParam(params);

        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/speciesMonitor.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, speciesMonitorList.size());
        Workbook workbook = exportDetailUtil.getWorkbook();
        for (int i = 0; i < speciesMonitorList.size(); i++) {
            SpeciesMonitorExcel speciesMonitorExcel = speciesMonitorService.getSpeciesMonitorExcel(speciesMonitorList.get(i).getId());
            JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(speciesMonitorExcel));
            //表格excel中sheet名称
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String timeStr = formatter.format(speciesMonitorExcel.getMonitorTime());
            String sheetName = i+1+"-"+timeStr+"-"+speciesMonitorExcel.getAreaName();
            workbook.setSheetName(i, sheetName);
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

                        } else if (cellValue.contains("$")) {
                            JSONArray jatemp = (JSONArray) jo.get(cellValue.substring(1).split("\\.")[0]);
                            if (jatemp != null && jatemp.size() > 0) {
                                String[] fields = new String[colNumber];
                                while (k < colNumber) {
                                    if(null != cellValue && !"".equals(cellValue)){
                                        String[] tempString = cellValue.substring(1).split("\\.");
                                        fields[k] = tempString[1];
                                    }
                                    k++;
                                    if (k == colNumber) {
                                        sheet.removeRow(row);
                                        break;
                                    }
                                    cell = row.getCell(k);
                                    cellValue = cell.getStringCellValue() == null ? "" : cell.getStringCellValue();
                                }
                                List<Map<String, Object>> datas = new ArrayList<>();
                                for (Object o : jatemp) {
                                    Map data = new HashMap<>();
                                    for (String field : fields) {
                                        data.put(field, ((JSONObject) o).get(field));
                                    }
                                    datas.add(data);
                                }
                                exportDetailUtil.appendRows(j, datas, fields);
                                j = j + jatemp.size();
                                sheet.shiftRows(j + 1, sheet.getLastRowNum(), -1);
                                rowNumber = sheet.getLastRowNum();
                                break;
                            } else {
                                exportDetailUtil.replaceCellValue(j, k, "");
                            }
                        }
                    }
                }

            }

            for (int b = 0; b <= sheet.getLastRowNum(); b++) {
                sheet.getRow(b).setHeight(sheet.getRow(0).getHeight());
            }

            for (int b=0;b<sheet.getRow(0).getPhysicalNumberOfCells();b++){
                sheet.setColumnWidth(b,colum);
            }

        }
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
        response.setHeader("Content-Disposition", "attachment;filename=" + "外来入侵物种监测数据.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("外来入侵物种监测数据.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);

    }

    /**
     * 获取数据查询条件相关方法
     * @param speciesMonitorQueryVo speciesMonitorQueryVo
     * @param userId userId
     * @return map
     */
    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式") @RequestBody SpeciesMonitorQueryVo speciesMonitorQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("monitor", speciesMonitorQueryVo.getMonitor());
        if (StringUtils.isNotBlank(speciesMonitorQueryVo.getProvinceId())) {
            params.put("provinceId", speciesMonitorQueryVo.getProvinceId());
        }
        if (StringUtils.isNotBlank(speciesMonitorQueryVo.getCityId())) {
            params.put("cityId", speciesMonitorQueryVo.getCityId());
        }
        if (StringUtils.isNotBlank(speciesMonitorQueryVo.getCountyId())) {
            params.put("countyId", speciesMonitorQueryVo.getCountyId());
        }
        params.put("status", speciesMonitorQueryVo.getStatus());
        params.put("beginDate", speciesMonitorQueryVo.getBeginDate());
        params.put("endDate", speciesMonitorQueryVo.getEndDate());
        params.put("inStatus",StatusEnum.getShowStatusByRole(RoleCodeUtil.getLoginUserAgzirddRoleCode()));
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

}
