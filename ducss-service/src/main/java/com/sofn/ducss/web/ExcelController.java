package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExportExcel;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExportExcel;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.ExportUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.vo.AreaRegionCode;
import com.sofn.ducss.util.vo.ExportDetailUtil;
import com.sofn.ducss.vo.DisperseUtilizeQueryVo;
import com.sofn.ducss.vo.QueryOneVo;
import com.sofn.ducss.vo.StrawUtilizeQueryVo;
import com.sofn.ducss.vo.excelVo.DataAnalysisExcelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @ClassName ExcelController
 * @Description Excle导入导出相关
 * @Author Chlf
 * @Date2020/6/30 14:42
 * Version1.0
 **/
@Slf4j
@Api(value = "导入导出管理", tags = "excel导入导出相关")
@RestController
@RequestMapping("/excelManager")
public class ExcelController {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private ProStillDetailService proStillDetailService;
    @Autowired
    private DisperseUtilizeDetailService disperseUtilizeDetailService;
    @Autowired
    private StrawUtilizeDetailService strawUtilizeDetailService;
    @Autowired
    private DataAnalysisService dataAnalysisService;

    @SofnLog("导入产生量与直接还田量")
    @ApiOperation(value = "导入产生量与直接还田量")
    @PostMapping("/importProStill")
    public Result importProStill(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file,
                                 @ApiParam(value = "年份") @RequestParam(value = "year", required = true) String year
    ) {
        return excelService.importExcel(file, year);
    }

    @SofnLog("下载产生量与直接还田量导入模板")
    @ApiOperation(value = "下载产生量与直接还田量导入模板")
    @GetMapping(value = "/downloadProStillTemplate", produces = "application/octet-stream")
    public Result downloadProStillTemplate(HttpServletResponse response) {
        excelService.downloadTemplate(response);
        return Result.ok("下载成功！");
    }

    @SofnLog("导出产生量与直接还田量")
    @ApiOperation(value = "导出产生量与直接还田量【县级用户使用】")
    @GetMapping(value = "/exportProStill", produces = "application/octet-stream")
    public Result exportProStill(String proStillId, HttpServletResponse response) {
        List<YieldAndReturnExportExcel> excelList = proStillDetailService.getProStillExportExcelById(proStillId);
        ExportUtil.createExcel(YieldAndReturnExportExcel.class, excelList, response, "产生量与直接还田量.xlsx");
        return Result.ok("导出成功！");
    }

    @SofnLog("导出产生量与直接还田量")
    @ApiOperation(value = "导出产生量与直接还田量【省市部级用户使用】")
    @GetMapping(value = "/exportProStillAdd", produces = "application/octet-stream")
    public Result exportProStillAdd(QueryOneVo queryVo, HttpServletResponse response) {
        List<String> countyIds = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getCountyId())) {
            countyIds = Arrays.asList(queryVo.getCountyId().split(","));
        }
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        if (CollectionUtils.isEmpty(countyIds)) {
            throw new SofnException("未选择任何行政区划！");
        }
        String firstCode = countyIds.get(0);
        AreaRegionCode firstArea = SysRegionUtil.getRegionCodeByLastCode(firstCode, null);
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("years", years);
        if (RegionLevel.CITY.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("cityIds", countyIds);
            // queryMap.put("statusList", Lists.newArrayList("1", "5"));
        } else if (RegionLevel.PROVINCE.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("provinceIds", countyIds);
            // queryMap.put("statusList", Lists.newArrayList("1", "5"));
        } else if (RegionLevel.COUNTY.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("areaIds", countyIds);
        }
        List<YieldAndReturnExportExcel> excelList = proStillDetailService.getExportExcelByAredIdAndYear(queryMap);
        ExportUtil.createExcel(YieldAndReturnExportExcel.class, excelList, response, "产生量与直接还田量.xlsx");
        return Result.ok("导出成功！");
    }

    @SofnLog("导入农户分散利用量")
    @ApiOperation(value = "导入农户分散利用量")
    @PostMapping("/importDisperseUtilize")
    public Result importDisperseUtilize(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file,
                                        @ApiParam(value = "年份") @RequestParam(value = "year", required = true) String year
    ) {
        //return excelService.importDisperseUtilize(file,year);
        return excelService.importDisperseUtilizeV2(file, year);
    }

    @SofnLog("下载农户分散利用量模板")
    @ApiOperation(value = "下载农户分散利用量模板")
    @GetMapping(value = "/downloadDisperseUtilizeTemplate", produces = "application/octet-stream")
    public Result downloadDisperseUtilizeTemplate(HttpServletResponse response) throws IOException {
        excelService.downloadDisperseUtilizeTemplate(response);
        return Result.ok("下载成功！");
    }

    @SofnLog("导出农户分散利用量")
    @ApiOperation(value = "导出农户分散利用量【县级用户使用】")
    @GetMapping(value = "/exportDisperseUtilize", produces = "application/octet-stream")
    public Result exportDisperseUtilize(DisperseUtilizeQueryVo queryVo, HttpServletRequest request, HttpServletResponse response) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        List<String> countyIds = Lists.newArrayList(countyId);
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        String userName = queryVo.getUserName();
        String dateBegin = queryVo.getDateBegin();
        String dateEnd = queryVo.getDateEnd();
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("areaIds", countyIds);
        queryMap.put("years", years);
        queryMap.put("userName", userName);
        queryMap.put("dateBegin", dateBegin);
        queryMap.put("dateEnd", dateEnd);
        List<DisperseUtilizeExportExcel> detailExlList = disperseUtilizeDetailService.getDisperseUtilizeDetailExl(queryMap);
        ExportUtil.createExcel(DisperseUtilizeExportExcel.class, detailExlList, response, "农户分散利用量.xlsx");
        return Result.ok("导出成功！");
    }

    @SofnLog("导出农户分散利用量")
    @ApiOperation(value = "导出农户分散利用量【省市部级用户使用】")
    @GetMapping(value = "/exportDisperseUtilizeAdd", produces = "application/octet-stream")
    public Result exportDisperseUtilizeAdd(QueryOneVo queryVo, HttpServletResponse response) {
        List<String> countyIds = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getCountyId())) {
            countyIds = Arrays.asList(queryVo.getCountyId().split(","));
        }
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        if (CollectionUtils.isEmpty(countyIds)) {
            throw new SofnException("未选择任何行政区划！");
        }
        String firstCode = countyIds.get(0);
        AreaRegionCode firstArea = SysRegionUtil.getRegionCodeByLastCode(firstCode, null);
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("years", years);
        if (RegionLevel.CITY.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("cityIds", countyIds);
            // queryMap.put("statusList", Lists.newArrayList("1", "5"));
        } else if (RegionLevel.PROVINCE.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("provinceIds", countyIds);
            // queryMap.put("statusList", Lists.newArrayList("1", "5"));
        } else if (RegionLevel.COUNTY.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("areaIds", countyIds);
        }
        List<DisperseUtilizeExportExcel> detailExlList = disperseUtilizeDetailService.getDisperseUtilizeDetailExl(queryMap);
        ExportUtil.createExcel(DisperseUtilizeExportExcel.class, detailExlList, response, "农户分散利用量.xlsx");
        return Result.ok("导出成功！");
    }

    @SofnLog("导入市场主体规模化秸秆利用量")
    @ApiOperation(value = "导入市场主体规模化秸秆利用量")
    @PostMapping("/importStrawUtilize")
    public Result importStrawUtilize(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file,
                                     @ApiParam(value = "年份") @RequestParam(value = "year", required = true) String year
    ) {
        return excelService.importStrawUtilizeExcel(file, year);
    }

    @SofnLog("下载市场主体规模化秸秆利用量导入模板")
    @ApiOperation(value = "下载市场主体规模化秸秆利用量导入模板")
    @GetMapping(value = "/downloadStrawUtilizeTemplate", produces = "application/octet-stream")
    public Result downloadStrawUtilizeTemplate(HttpServletResponse response) throws IOException {
        excelService.downloadStrawUtilizeTemplate(response);
        return Result.ok("下载成功！");
    }

    @SofnLog("导出市场主体规模化秸秆利用量")
    @ApiOperation(value = "导出市场主体规模化秸秆利用量【县级用户使用】")
    @GetMapping(value = "/exportStrawUtilize", produces = "application/octet-stream")
    public Result exportStrawUtilize(StrawUtilizeQueryVo vo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        List<String> countyIds = Lists.newArrayList(countyId);
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(vo.getYear())) {
            years = Arrays.asList(vo.getYear().split(","));
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("areaIds", countyIds);
        params.put("dateBegin", vo.getDateBegin());
        params.put("dateEnd", vo.getDateEnd());
//        params.put("pageNo", vo.getPageNo());
//        params.put("pageSize" , vo.getPageSize());
        params.put("mainName", vo.getMainName());

        strawUtilizeDetailService.getStrawUtilizeExcelList(params, response);

        return Result.ok("导出成功！");
    }

    @SofnLog("导出市场主体规模化秸秆利用量")
    @ApiOperation(value = "导出市场主体规模化秸秆利用量【省市部级用户使用】")
    @GetMapping(value = "/exportStrawUtilizeAdd", produces = "application/octet-stream")
    public Result exportStrawUtilizeAdd(QueryOneVo queryVo, HttpServletResponse response) throws IOException {
        List<String> countyIds = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getCountyId())) {
            countyIds = Arrays.asList(queryVo.getCountyId().split(","));
        }
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        if (CollectionUtils.isEmpty(countyIds)) {
            throw new SofnException("未选择任何行政区划！");
        }
        String firstCode = countyIds.get(0);
        AreaRegionCode firstArea = SysRegionUtil.getRegionCodeByLastCode(firstCode, null);
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("years", years);
        if (RegionLevel.CITY.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("cityIds", countyIds);
            // queryMap.put("statusList", Lists.newArrayList("1", "5"));
        } else if (RegionLevel.PROVINCE.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("provinceIds", countyIds);
            // queryMap.put("statusList", Lists.newArrayList("1", "5"));
        } else if (RegionLevel.COUNTY.getCode().equals(firstArea.getRegionLevel())) {
            queryMap.put("areaIds", countyIds);
        }
        strawUtilizeDetailService.getStrawUtilizeExcelList(queryMap, response);
        return Result.ok("导出成功！");
    }

    @SofnLog("数据分析导出")
    @ApiOperation(value = "数据分析导出", produces = "application/octet-stream")
    @PostMapping(value = "/dataAnalysisExport")
    public void dataAnalysisExport(@RequestBody String data, HttpServletResponse response) {
        //JSONArray ja=JSONObject.parseArray(list);
        if (data.contains("isNull")) {
            try {
                ClassPathResource resource = new ClassPathResource("static/数据分析.xlsx");
                InputStream inputStream = resource.getInputStream();
                ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream);
                Workbook workbook = exportDetailUtil.getWorkbook();
                workbook.setSheetName(0, "数据分析数据导出");
                exportDetailUtil.setSheet(workbook.getSheetAt(0));
                Sheet sheet = exportDetailUtil.getSheet();
                response.reset();
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/octet-stream");
                //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
                response.setHeader("Content-Disposition", "attachment;filename=" + "数据分析.xlsx" + ";filename*=utf-8''"
                        + URLEncoder.encode("数据分析.xlsx", "utf-8"));
                OutputStream os = response.getOutputStream();
                inputStream.close();
                exportDetailUtil.write(os);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List temp = JsonUtils.json2List(data, DataAnalysisExcelVo.class);
        excelService.dataAnalysisExport(temp, response);
    }

    @SofnLog("数据分析导出")
    @ApiOperation(value = "数据分析导出(v2)", produces = "application/octet-stream")
    @PostMapping(value = "/datasAnalysisExport")
    public void datasAnalysisExport(@RequestBody HashMap<String, String> paramMap, HttpServletResponse response) throws Exception {
        //excelService.datasAnalysisExport(paramMap,response);
        dataAnalysisService.datasAnalysisExport(paramMap, response);
    }


    @ApiOperation(value = "导出产生量与直接还田量【开发备份数据时使用】", produces = "application/octet-stream")
    @GetMapping(value = "/real/exportProStill", produces = "application/octet-stream")
    public Result realExportProStill(QueryOneVo queryVo, HttpServletResponse response) {
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("years", Lists.newArrayList(queryVo.getYear()));
        queryMap.put("provinceIds", SysRegionUtil.getChildrenRegionIdByYearList("100000", null));
        List<YieldAndReturnExportExcel> excelList = proStillDetailService.getExportExcelByAredIdAndYear(queryMap);
        ExportUtil.createExcel(YieldAndReturnExportExcel.class, excelList, response, "产生量与直接还田量.xlsx");
        return Result.ok("导出成功！");
    }

    @ApiOperation(value = "导出农户分散利用量【开发备份数据时使用】", produces = "application/octet-stream")
    @GetMapping(value = "/real/exportDisperseUtilizeAdd", produces = "application/octet-stream")
    public Result realExportDisperseUtilizeAdd(QueryOneVo queryVo, Integer start, Integer end, HttpServletResponse response) {
        List<DisperseUtilizeExportExcel> result = Lists.newArrayList();
        List<String> provinceIds = SysRegionUtil.getChildrenRegionIdByYearList("100000", null);
        for (String provinceId : provinceIds) {
            Map<String, Object> queryMap = Maps.newHashMap();
            queryMap.put("years", Lists.newArrayList(queryVo.getYear()));
            queryMap.put("provinceIds", Lists.newArrayList(provinceId));
            List<DisperseUtilizeExportExcel> detailExlList = disperseUtilizeDetailService.realGetDisperseUtilizeDetailExl(queryMap);
            result.addAll(detailExlList);
        }
        if (end > result.size() - 1) {
            end = result.size() - 1;
        }
        result = result.subList(start, end);
        ExportUtil.createExcel(DisperseUtilizeExportExcel.class, result, response, "农户分散利用量.xlsx");
        return Result.ok("导出成功！");
    }

    @ApiOperation(value = "导出市场主体规模化秸秆利用量【开发备份数据时使用】", produces = "application/octet-stream")
    @GetMapping(value = "/real/exportStrawUtilizeAdd", produces = "application/octet-stream")
    public Result realExportStrawUtilizeAdd(QueryOneVo queryVo, HttpServletResponse response) throws IOException {
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("years", Lists.newArrayList(queryVo.getYear()));
        queryMap.put("provinceIds", SysRegionUtil.getChildrenRegionIdByYearList("100000", null));
        strawUtilizeDetailService.getStrawUtilizeExcelList(queryMap, response);
        return Result.ok("导出成功！");
    }

}
