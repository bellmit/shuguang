package com.sofn.ducss.web;

import com.google.common.collect.Maps;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.mapper.StrawUtilizeDetailMapper;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.excelmodel.StrawUtilizeExcel;
import com.sofn.ducss.service.*;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExportExcel;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExportExcel;
import com.sofn.ducss.util.ExportUtil;
import com.sofn.ducss.util.ExportDetailUtil;
import com.sofn.ducss.vo.DisperseUtilizeQueryVo;
import com.sofn.ducss.vo.QueryOneVo;
import com.sofn.ducss.vo.StrawUtilizeCombinVo;
import com.sofn.ducss.vo.StrawUtilizeQueryVo;
import com.sofn.ducss.vo.excelVo.DataAnalysisExcelVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sofn.ducss.model.basemodel.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

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
        List<YieldAndReturnExportExcel> excelList = proStillDetailService.getExportExcelByAredIdAndYear(queryVo.getCountyId(), queryVo.getYear());
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
        String year = queryVo.getYear();
        String userName = queryVo.getUserName();
        String dateBegin = queryVo.getDateBegin();
        String dateEnd = queryVo.getDateEnd();

        List<DisperseUtilizeExportExcel> detailExlList = disperseUtilizeDetailService.getDisperseUtilizeDetailExl(year, userName, countyId, dateBegin, dateEnd);

        ExportUtil.createExcel(DisperseUtilizeExportExcel.class, detailExlList, response, "农户分散利用量.xlsx");

        return Result.ok("导出成功！");
    }

    @SofnLog("导出农户分散利用量")
    @ApiOperation(value = "导出农户分散利用量【省市部级用户使用】")
    @GetMapping(value = "/exportDisperseUtilizeAdd", produces = "application/octet-stream")
    public Result exportDisperseUtilizeAdd(QueryOneVo queryVo, HttpServletResponse response) {
        String countyId = queryVo.getCountyId() == null ? "" : queryVo.getCountyId();
        String year = queryVo.getYear();
        List<DisperseUtilizeExportExcel> detailExlList = disperseUtilizeDetailService.getDisperseUtilizeDetailExl(year, null, countyId, null, null);
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

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", vo.getYear());
        params.put("areaId", countyId);
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
        String countyId = queryVo.getCountyId();
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", queryVo.getYear());
        params.put("areaId", StringUtils.isEmpty(countyId) ? "00" : countyId);
        strawUtilizeDetailService.getStrawUtilizeExcelList(params, response);
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

    @SofnLog("Zip数据导出")
    @ApiOperation(value = "Zip数据导出",produces = "application/octet-stream")
    @PostMapping(value = "/dataZipExport")
    public void dataZipExport(String year,HttpServletResponse response) throws IOException {
       excelService.zipExport(response,year);
    }

    @SofnLog("数据分析导出")
    @ApiOperation(value = "数据分析导出(v2)", produces = "application/octet-stream")
    @PostMapping(value = "/datasAnalysisExport")
    public void datasAnalysisExport(@RequestBody HashMap<String, String> paramMap, HttpServletResponse response) throws Exception {
        //excelService.datasAnalysisExport(paramMap,response);
        dataAnalysisService.datasAnalysisExport(paramMap, response);
    }


    /**
     * api接口返回结果封装对象
     * @author sofn
     */
  /*  @SuppressWarnings("ALL")
    @ApiModel("返回类")
    @Data*/
    /*public static class Result<T> implements Serializable {
        private static final long serialVersionUID = 1L;

        public static final Integer CODE = 200;

        @ApiModelProperty(value = "返回码", notes = "返回码")
        private Integer code;

        @ApiModelProperty(value = "描述消息")
        private String msg = "";

        @ApiModelProperty(value = "返回对象")
        private T data;

        public Result() {

        }

        public Result(Integer code) {
            this.code = code;
        }

        public Result(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Result(Integer code, T result) {
            this.code = code;
            this.data = result;
        }

        public Result(Integer code, String msg, T result) {
          this.code = code;
          this.msg = msg;
          this.data = result;
        }

        public Result(T result) {
            this.data = result;
        }

        public static Result error() {
            return error(500, "未知异常");
        }

        public static <T> Result<T> error(String msg) {
            return error(500, msg);
        }

        public static <T> Result<T> error(Object result) {
            return new Result(500, result);
        }

        public static Result error(Integer code, String msg) {
            return new Result(code, msg);
        }

        public static Result error(ExceptionType exceptionType) {
            return new Result(exceptionType.getCode(), exceptionType.getMsg());
        }

        public static <T> Result<T> ok(String msg) {
            return new Result(CODE, msg);
        }

        public static <T> Result<T> ok(Object data, String msg) {
          return new Result(CODE, msg, data);
        }

        public static <T> Result<T> ok(Object result) {
            return new Result(CODE, result);
        }

        public static <T> Result<T> ok() {
            return new Result(CODE);
        }

        *//**
         * 接口返回验证提示信息
         * @param msg 提示信息
         * @param <T>
         * @return result对象
         *//*
        public static <T> Result<T> alert(String msg){
            return new Result(600,msg);
        }

    }*/
}
