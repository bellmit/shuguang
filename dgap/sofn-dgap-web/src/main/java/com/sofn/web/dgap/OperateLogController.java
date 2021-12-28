package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.Constants;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.base.RedisService;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.OperateLog;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.model.sys.OperateLogQueryParam;
import com.sofn.model.sys.OperateLogStats;
import com.sofn.service.dgap.SysOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by: dong4j.
 * Date: 2016-10-27.
 * Time: 22:53.
 * Description:
 */
@RestController
@Api(value = "字典管理接口", description = "操作日志")
@RequestMapping(value = "/log")
public class OperateLogController extends BaseController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private SysOperateLogService sysOperateLogService;
    private static final String DATE_FORMAT_PATTERN = "yyyy/M/d"; // 日期格式字符串
    private static final int START_ROW_NUM = 2; // 导出excel的数据起始行号
    private static final int EXPORT_PAGE_SIZE = 1000; // 导出日志的pageSize
    public static final String OPERATE_TYPES_KEY = "logOperateTypes";

    @ApiOperation(value = "查询日志")
    @RequestMapping(value = "/queryOperateLog", method = RequestMethod.POST)
    @SystemControllerLog(description = "查询日志", operationType = "增加")
    public Object queryOperateLog(ModelMap modelMap, Long start, Long length, @RequestParam("operateType") String operateType, @CookieValue(value = "account", defaultValue = "") String account) {
        List<OperateLog> list = new ArrayList<>();
        if (Constants.SUPERADMIN_ACCOUNT.equals(account)) {
            list = redisService.queryOperateLog(); // superadmin账户查询所有记录
        } else {
            list = redisService.queryOperateLog(account); // 其他登录账户自己所属记录
        }

        List<OperateLog> resultList = new ArrayList<>();
        List<OperateLog> operateTypeList = new ArrayList<>();
        if (list.size() != 0 && StringUtils.isNotEmpty(operateType)) {
            for (int i = 0; i < list.size(); i++) {
                if (operateType.equals(list.get(i).getOperateType())) {
                    operateTypeList.add(list.get(i));
                }
            }
        }
        long startIndex = (start - 1) == -1 ? 0 : (start - 1);
        if (StringUtils.isNotEmpty(operateType)) {
            for (long i = startIndex; i <= (start - 1 + length); i++) {
                if (operateTypeList.size() < i + 1)
                    break;
                resultList.add(operateTypeList.get((int) i));
            }
        } else {
            for (long i = startIndex; i <= (start - 1 + length); i++) {
                if (list.size() < i + 1)
                    break;
                resultList.add(list.get((int) i));
            }
        }
        Page page = new Page();
        if (!"".equals(operateType))
            page.setRecordsTotal(operateTypeList.size());
        else
            page.setRecordsTotal(list.size());
        page.setRecordsFiltered(resultList.size());
        page.setStart(start);
        page.setLength(length);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", resultList);
        return setSuccessModelMap(modelMap);
    }

    @Authorization
    @ApiOperation(value = "查询历史日志")
    @RequestMapping(value = "/queryOperatehistoryLog", method = RequestMethod.POST)
    public Object getPageInfo(ModelMap modelMap,
                              @RequestParam(value = "operateType", required = false) String operateType,
                              @RequestParam(value = "operateUsername", required = false) String operateUsername,
                              @RequestParam(value = "operateDesc", required = false) String operateDesc,
                              @RequestParam(value = "operateIp", required = false) String operateIp,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime, Long start, Long length, @CookieValue(value = "account", defaultValue = "") String account) {

        if (!org.springframework.util.StringUtils.isEmpty(account) && !Constants.SUPERADMIN_ACCOUNT.equals(account)) {
            operateUsername = account; // 按当前登录用户查询日志
        }
        Map<String, Object> map2 = new HashedMap();
        map2.put("operateType", operateType);
        map2.put("operateDescribe", operateDesc);
        map2.put("operateUsername", operateUsername);
        map2.put("operateIp", operateIp);
        if (startTime != "" && startTime != null) {
            startTime += " 00:00:00";
        }
        map2.put("startTime", startTime);
        if (endTime != "" && endTime != null) {
            endTime += " 23:59:59";
        }
        map2.put("endTime", endTime);
        long recordsTotal = sysOperateLogService.getCount(map2);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pager", pager);
        map.put("operateType", operateType);
        map.put("operateUsername", operateUsername);
        map.put("operateIp", operateIp);
        map.put("startTime", startTime);
        map.put("operateDescribe", operateDesc);
        map.put("endTime", endTime);
        List<SysOperateLog> list = sysOperateLogService.getPageInfo(map);
        modelMap.addAttribute("page", pager);

        modelMap.addAttribute("list", list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据IP查询
     *
     * @param oIp
     * @return
     */
    @ApiOperation(value = "根据IP查询历史日志")
    @RequestMapping(value = "/findByIp", method = RequestMethod.POST)
    public Map<String, Object> findByIp(@RequestParam(value = "oIp", required = false) String oIp) {
        List<SysOperateLog> list = sysOperateLogService.findByIp(oIp);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }


    @ApiOperation(value = "查询共享系统日志")
    @RequestMapping(value = "/queryDataOperateLog", method = RequestMethod.POST)
    public Object queryDataOperateLog(ModelMap modelMap, Long start, Long length, @RequestParam("operateType") String operateType, @CookieValue(value = "account", defaultValue = "") String account) {
        List<OperateLog> list = redisService.queryDataOperateLog(); // superadmin账户查询所有记录

        List<OperateLog> resultList = new ArrayList<>();
        List<OperateLog> operateTypeList = new ArrayList<>();
        if (list.size() != 0 && StringUtils.isNotEmpty(operateType)) {
            for (int i = 0; i < list.size(); i++) {
                if (operateType.equals(list.get(i).getOperateType())) {
                    operateTypeList.add(list.get(i));
                }
            }
        }
        long startIndex = (start - 1) == -1 ? 0 : (start - 1);
        if (StringUtils.isNotEmpty(operateType)) {
            for (long i = startIndex; i <= (start - 1 + length); i++) {
                if (operateTypeList.size() < i + 1)
                    break;
                resultList.add(operateTypeList.get((int) i));
            }
        } else {
            for (long i = startIndex; i <= (start - 1 + length); i++) {
                if (list.size() < i + 1)
                    break;
                resultList.add(list.get((int) i));
            }
        }
        Page page = new Page();
        if (!"".equals(operateType))
            page.setRecordsTotal(operateTypeList.size());
        else
            page.setRecordsTotal(list.size());
        page.setRecordsFiltered(resultList.size());
        page.setStart(start);
        page.setLength(length);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", resultList);
        return setSuccessModelMap(modelMap);
    }


    @ApiOperation(value = "查询数据共享历史日志")
    @RequestMapping(value = "/queryDataOperatehistoryLog", method = RequestMethod.POST)
    public Object getDataPageInfo(ModelMap modelMap,
                                  @RequestParam(value = "operateType", required = false) String operateType,
                                  @RequestParam(value = "operateUsername", required = false) String operateUsername,
                                  @RequestParam(value = "operateDesc", required = false) String operateDesc,
                                  @RequestParam(value = "operateIp", required = false) String operateIp,
                                  @RequestParam(value = "startTime", required = false) String startTime,
                                  @RequestParam(value = "endTime", required = false) String endTime, Long start, Long length, @CookieValue(value = "account", defaultValue = "") String account) {


        Map<String, Object> map2 = new HashedMap();

        map2.put("operateType", operateType);
        map2.put("operateDescribe", operateDesc);
        map2.put("operateUsername", operateUsername);
        map2.put("operateIp", operateIp);
        if (startTime != "" && startTime != null) {
            startTime += " 00:00:00";
        }
        map2.put("startTime", startTime);
        if (endTime != "" && endTime != null) {
            endTime += " 23:59:59";
        }
        map2.put("endTime", endTime);
        long recordsTotal = sysOperateLogService.getDataCount(map2);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pager", pager);
        map.put("operateType", operateType);
        map.put("operateUsername", operateUsername);
        map.put("operateIp", operateIp);
        map.put("startTime", startTime);
        map.put("operateDescribe", operateDesc);
        map.put("endTime", endTime);
        List<SysOperateLog> list = sysOperateLogService.getDataPageInfo(map);
        modelMap.addAttribute("page", pager);

        modelMap.addAttribute("list", list);
        return setSuccessModelMap(modelMap);
    }

    // 获取标题单元格样式
    private CellStyle getTitleCellStyle(SXSSFWorkbook workbook) {
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中对齐
        titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中对齐
        titleCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        titleCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        titleCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        titleCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        Font titleFont = workbook.createFont();
        titleFont.setFontName("微软雅黑"); // 字体
        titleFont.setFontHeightInPoints((short)16); // 字体大小
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD); // 设置粗体
        titleCellStyle.setFont(titleFont);

        return titleCellStyle;
    }

    // 获取列名单元格样式
    private CellStyle getColumnNameCellStyle(SXSSFWorkbook workbook) {
        Font columnNameFont = workbook.createFont();
        columnNameFont.setFontName("微软雅黑"); // 字体
        columnNameFont.setBoldweight(Font.BOLDWEIGHT_BOLD); // 设置粗体
        CellStyle columnNameCellStyle = workbook.createCellStyle();
        columnNameCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中对齐
        columnNameCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        columnNameCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        columnNameCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        columnNameCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        columnNameCellStyle.setFont(columnNameFont);

        return columnNameCellStyle;
    }

    // 获取普通单元格样式
    private CellStyle getNormalCellStyle(SXSSFWorkbook workbook) {
        CellStyle normalCellStyle = workbook.createCellStyle();
        normalCellStyle.cloneStyleFrom(getColumnNameCellStyle(workbook));
        Font normalFont = workbook.getFontAt(normalCellStyle.getFontIndex());
        normalFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);

        return normalCellStyle;
    }

    // 获取状态对应的名称
    private String getStatusName(String status) {
        if (org.springframework.util.StringUtils.isEmpty(status)) {
            return "";
        } else {
            if (status.equals("Y")) {
                return "成功";
            } else if (status.equals("N")) {
                return "失败";
            } else {
                return "";
            }
        }
    }

    @Authorization
    @RequestMapping(value = "/exportStatsExcel", method = RequestMethod.GET)
    public void exportOperateLogStats(@RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws UnsupportedEncodingException {
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String excelFileName = "系统操作日志安全审计报表.xlsx";
        // 设置导出excel文件时浏览器保存对话框显示的文件名
        WebUtil.setFileNameForDownload(request, response, excelFileName);
        List<OperateLogStats> operateLogStatsList = sysOperateLogService.getOperateLogStats(startTime, endTime);

        SXSSFWorkbook workbook = new SXSSFWorkbook(500);
        workbook.setCompressTempFiles(true);
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(0, 5600); // 设置列宽度
        sheet.setColumnWidth(1, 5600);
        sheet.setColumnWidth(3, 3000);
        sheet.setDefaultRowHeightInPoints(16); // 设置默认行高

        Row titleRow = sheet.createRow(0);
        titleRow.setHeight((short) 1000); // 设置标题行高度

        CellStyle titleCellStyle = getTitleCellStyle(workbook);
        Cell titleCell = titleRow.createCell(0);
        Cell titleCell1 = titleRow.createCell(1);
        Cell titleCell2 = titleRow.createCell(2);
        Cell titleCell3 = titleRow.createCell(3);

        titleCell.setCellStyle(titleCellStyle);
        titleCell.setCellValue("系统操作日志安全审计报表");
        titleCell1.setCellStyle(titleCellStyle);
        titleCell2.setCellStyle(titleCellStyle);
        titleCell3.setCellStyle(titleCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3)); // 合并标题单元格

        Row columnNameRow = sheet.createRow(1);
        CellStyle columnNameCellStyle = getColumnNameCellStyle(workbook);
        Cell columnNameCell1 = columnNameRow.createCell(0);
        columnNameCell1.setCellStyle(columnNameCellStyle);
        columnNameCell1.setCellValue("操作时间");

        Cell columnNameCell2 = columnNameRow.createCell(1);
        columnNameCell2.setCellStyle(columnNameCellStyle);
        columnNameCell2.setCellValue("操作类型");

        Cell columnNameCell3 = columnNameRow.createCell(2);
        columnNameCell3.setCellStyle(columnNameCellStyle);
        columnNameCell3.setCellValue("结果");

        Cell columnNameCell4 = columnNameRow.createCell(3);
        columnNameCell4.setCellStyle(columnNameCellStyle);
        columnNameCell4.setCellValue("统计");

        if (org.springframework.util.StringUtils.isEmpty(startTime)) {
            startTime = sysOperateLogService.getMinDate(DATE_FORMAT_PATTERN);
        } else {
            startTime = DateUtil.format(DateUtil.stringToDate(startTime), DATE_FORMAT_PATTERN);
        }
        if (org.springframework.util.StringUtils.isEmpty(endTime)) {
            endTime = DateUtil.format(new Date(), DATE_FORMAT_PATTERN);
        } else {
            endTime = DateUtil.format(DateUtil.stringToDate(endTime), DATE_FORMAT_PATTERN);
        }
        String operateTimeRange = String.format("%s-%s", startTime, endTime);
        CellStyle normalCellStyle = getNormalCellStyle(workbook);
        CellStyle amountCellStyle = workbook.createCellStyle();
        amountCellStyle.cloneStyleFrom(normalCellStyle);
        amountCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);

        int rowNum = START_ROW_NUM;
        String operateTypeTemp = "";
        // 将数据填充到单元格
        for (OperateLogStats operateLogStats : operateLogStatsList) {
            Row dataRow = sheet.createRow(rowNum);

            Cell dataCell1 = dataRow.createCell(0); // 操作时间
            dataCell1.setCellStyle(normalCellStyle);
            dataCell1.setCellValue(operateTimeRange);

            Cell dataCell2 = dataRow.createCell(1); // 操作类型
            dataCell2.setCellStyle(normalCellStyle);
            dataCell2.setCellValue(operateLogStats.getOperateType());

            Cell dataCell3 = dataRow.createCell(2); // 结果
            dataCell3.setCellStyle(normalCellStyle);
            dataCell3.setCellValue(getStatusName(operateLogStats.getStatus()));

            Cell dataCell4 = dataRow.createCell(3); // 统计
            dataCell4.setCellStyle(amountCellStyle);
            dataCell4.setCellValue(operateLogStats.getCount());

            if (rowNum > START_ROW_NUM && operateLogStats.getOperateType().equals(operateTypeTemp)) {
                // 合并操作时间单元格
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum, 0, 0));
                // 合并操作类型单元格
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum, 1, 1));
            }
            operateTypeTemp = operateLogStats.getOperateType();
            rowNum++;
        }

        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 清理临时文件
            sysOperateLogService.disposeExcelTempFile(workbook);
        }
    }

    @Authorization
    @RequestMapping(value = "/exportLogsExcel", method = RequestMethod.GET)
    public void exportOperateLogs(OperateLogQueryParam param, @CookieValue(value = "account", defaultValue = "") String account,
                                  HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        int pageNum = 1;
        long count = 0;
        param.setPageNum(pageNum);
        param.setPageSize(EXPORT_PAGE_SIZE);
        if (!org.springframework.util.StringUtils.isEmpty(account) && !Constants.SUPERADMIN_ACCOUNT.equals(account)) {
             param.setOperateUsername(account); // 按当前登录用户查询日志
        }

        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String excelFileName = String.format("系统操作审计日志%s.xlsx", DateUtil.getDateTime(DateUtil.DATE_PATTERN.YYYYMMDDHHMMSS));
        // 设置导出excel文件时浏览器保存对话框显示的文件名
        WebUtil.setFileNameForDownload(request, response, excelFileName);

        boolean hasNextPage = false;
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        workbook.setCompressTempFiles(true);

        do {
            PageInfo<SysOperateLog> pageInfo = sysOperateLogService.getOperateLogs(param);
            Sheet sheet = workbook.createSheet("page" + pageNum);
            // 设置列宽度
            sheet.setColumnWidth(0, 2000);
            sheet.setColumnWidth(1, 5600);
            sheet.setColumnWidth(2, 7200);
            sheet.setColumnWidth(4, 4200);
            sheet.setColumnWidth(5, 5600);
            sheet.setColumnWidth(6, 4200);
            sheet.setDefaultRowHeightInPoints(16); // 设置默认行高

            // 创建标题行
            Row titleRow = sheet.createRow(0);
            titleRow.setHeight((short) 1000); // 设置标题行高度
            CellStyle titleCellStyle = getTitleCellStyle(workbook);
            titleCellStyle.setBorderTop(CellStyle.BORDER_NONE);
            titleCellStyle.setBorderRight(CellStyle.BORDER_NONE);
            titleCellStyle.setBorderLeft(CellStyle.BORDER_NONE);
            titleCellStyle.setBorderBottom(CellStyle.BORDER_NONE);
            // 创建标题行所在单元格
            Cell titleCell0 = titleRow.createCell(0);
            Cell titleCell1 = titleRow.createCell(1);
            Cell titleCell2 = titleRow.createCell(2);
            Cell titleCell3 = titleRow.createCell(3);
            Cell titleCell4 = titleRow.createCell(4);
            Cell titleCell5 = titleRow.createCell(5);
            Cell titleCell6 = titleRow.createCell(6);
            // 设置标题单元格样式
            titleCell0.setCellStyle(titleCellStyle);
            titleCell0.setCellValue("系统操作审计日志");
            titleCell1.setCellStyle(titleCellStyle);
            titleCell2.setCellStyle(titleCellStyle);
            titleCell3.setCellStyle(titleCellStyle);
            titleCell4.setCellStyle(titleCellStyle);
            titleCell5.setCellStyle(titleCellStyle);
            titleCell6.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,6)); // 合并标题单元格

            // 创建列名行
            Row columnNameRow = sheet.createRow(1);
            CellStyle columnNameCellStyle = getColumnNameCellStyle(workbook);
            columnNameCellStyle.setBorderBottom(CellStyle.BORDER_NONE);
            columnNameCellStyle.setBorderLeft(CellStyle.BORDER_NONE);
            columnNameCellStyle.setBorderRight(CellStyle.BORDER_NONE);
            columnNameCellStyle.setBorderTop(CellStyle.BORDER_NONE);
            Cell columnNameCell0 = columnNameRow.createCell(0);
            columnNameCell0.setCellStyle(columnNameCellStyle);
            columnNameCell0.setCellValue("序号");

            Cell columnNameCell1 = columnNameRow.createCell(1);
            columnNameCell1.setCellStyle(columnNameCellStyle);
            columnNameCell1.setCellValue("操作类型");

            Cell columnNameCell2 = columnNameRow.createCell(2);
            columnNameCell2.setCellStyle(columnNameCellStyle);
            columnNameCell2.setCellValue("操作说明");

            Cell columnNameCell3 = columnNameRow.createCell(3);
            columnNameCell3.setCellStyle(columnNameCellStyle);
            columnNameCell3.setCellValue("结果");

            Cell columnNameCell4 = columnNameRow.createCell(4);
            columnNameCell4.setCellStyle(columnNameCellStyle);
            columnNameCell4.setCellValue("操作人");

            Cell columnNameCell5 = columnNameRow.createCell(5);
            columnNameCell5.setCellStyle(columnNameCellStyle);
            columnNameCell5.setCellValue("操作时间");

            Cell columnNameCell6 = columnNameRow.createCell(6);
            columnNameCell6.setCellStyle(columnNameCellStyle);
            columnNameCell6.setCellValue("操作IP");

            // 创建数据单元格样式
            CellStyle dataCellStyle = getNormalCellStyle(workbook);
            dataCellStyle.setBorderBottom(CellStyle.BORDER_NONE);
            dataCellStyle.setBorderLeft(CellStyle.BORDER_NONE);
            dataCellStyle.setBorderRight(CellStyle.BORDER_NONE);
            dataCellStyle.setBorderTop(CellStyle.BORDER_NONE);

            int dataRowNum = START_ROW_NUM;
            // 将数据填充到单元格
            for (SysOperateLog log : pageInfo.getList()) {
                count++;
                Row dataRow = sheet.createRow(dataRowNum);
                Cell dataCell0 = dataRow.createCell(0); // 序号
                dataCell0.setCellStyle(dataCellStyle);
                dataCell0.setCellValue(count);

                Cell dataCell1 = dataRow.createCell(1); // 操作类型
                dataCell1.setCellStyle(dataCellStyle);
                dataCell1.setCellValue(log.getOperateType());

                Cell dataCell2 = dataRow.createCell(2); // 操作说明
                dataCell2.setCellStyle(dataCellStyle);
                dataCell2.setCellValue(log.getOperateDescribe());

                Cell dataCell3 = dataRow.createCell(3); // 结果
                dataCell3.setCellStyle(dataCellStyle);
                dataCell3.setCellValue(log.getStatus().equals("Y") ? "成功" : "失败");

                Cell dataCell4 = dataRow.createCell(4); // 操作人
                dataCell4.setCellStyle(dataCellStyle);
                dataCell4.setCellValue(log.getOperateUsername());

                Cell dataCell5 = dataRow.createCell(5); // 操作时间
                dataCell5.setCellStyle(dataCellStyle);
                String operateTime = DateUtil.format(log.getOperateTime(), DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
                dataCell5.setCellValue(operateTime);

                Cell dataCell6 = dataRow.createCell(6); // 操作IP
                dataCell6.setCellStyle(dataCellStyle);
                dataCell6.setCellValue(log.getOperateIp());
                dataRowNum++;
            }

            hasNextPage = pageInfo.isHasNextPage();
            if (hasNextPage) {
                pageNum++;
                param.setPageNum(pageNum);
            }
        } while (hasNextPage);

        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 清理临时文件
            sysOperateLogService.disposeExcelTempFile(workbook);
        }
    }

    @RequestMapping(value = "/getOperateTypes", method = RequestMethod.GET)
    public Object getOperateTypes() {
        HashMap<String, List> operateTypeData = (HashMap<String, List>) RedisUtil.get(OPERATE_TYPES_KEY);
        List<String> operateTypeList = null;
        if (operateTypeData == null) {
            operateTypeList = sysOperateLogService.getOperateTypes();
            HashMap<String, List> data = new HashMap<>();
            data.put("data", operateTypeList);
            RedisUtil.set(OPERATE_TYPES_KEY, data);
        } else {
            operateTypeList = operateTypeData.get("data");
        }

        return setSuccessResultMap(operateTypeList);
    }
}
