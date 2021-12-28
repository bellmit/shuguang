package com.sofn.fyem.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.service.ProliferationReleaseStatisticsService;
import com.sofn.fyem.util.ExportDetailUtil;
import com.sofn.fyem.vo.FinanceStatisticsVo;
import com.sofn.fyem.vo.ReleaseStatisticsCountVo;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "中央财政信息统计汇总-接口", tags = "中央财政信息统计汇总-接口")
@RestController
@RequestMapping("/financeStatistics")
public class FinanceStatisticsController extends BaseController {

    @Autowired
    private ProliferationReleaseStatisticsService proliferationReleaseStatisticsService;

    @SofnLog("单独统计增值流程信息")
    @ApiOperation(value="单独统计增值流程信息")
    @GetMapping("/getFinanceStatistics")
    public Result<ReleaseStatisticsCountVo> getFinanceStatistics(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)String countyId){

        Map<String, Object> params = getParamsByLogin(belongYear, provinceId, cityId, countyId);

        ReleaseStatisticsCountVo releaseStatisticsCount = proliferationReleaseStatisticsService.getReleaseStatisticsCount(params);
        return Result.ok(releaseStatisticsCount);
    }

    @NotNull
    private Map<String, Object> getParamsByLogin(@RequestParam("belongYear") @ApiParam(name = "belongYear", value = "所属年度", required = true) String belongYear, @RequestParam(value = "provinceId", required = false) @ApiParam(name = "provinceId", value = "省()") String provinceId, @RequestParam(value = "cityId", required = false) @ApiParam(name = "cityId", value = "市()") String cityId, @RequestParam(value = "countyId", required = false) @ApiParam(name = "countyId", value = "区县()") String countyId) {
        String userId = UserUtil.getLoginUserId();
        Map<String, Object> params = Maps.newHashMap();
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        params.put("belongYear", belongYear);
        params.put("provinceId", provinceId);
        params.put("cityId", cityId);
        params.put("countyId", countyId);
        params.put("provinceId", provinceId);
        params.put("countyId", countyId);
        params.put("cityId", cityId);
        if (roleColde.contains("fyem_county")) {
            params.put("createUserId", userId);
        }
        if (roleColde.contains("fyem_city_add") || roleColde.contains("fyem_city")) {
            params.put("countyId", countyId);
        }
        if (roleColde.contains("fyem_prov_add") || roleColde.contains("fyem_prov")) {
            params.put("cityId", cityId);
            params.put("countyId", countyId);
        }
        return params;
    }


    @SofnLog("导出单独统计增值流程信息")
    @ApiOperation(value="导出单独统计增值流程信息")
    @GetMapping(value = "/exportFinanceStatistics", produces = "application/octet-stream")
    public void exportFinanceStatistics(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省(省级用户必填)")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市(市级用户必填)")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县(区县级用户必填)")@RequestParam(value = "countyId",required = false)String countyId,
            HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, Object> params = getParamsByLogin(belongYear, provinceId, cityId, countyId);
        ReleaseStatisticsCountVo releaseStatisticsCount = proliferationReleaseStatisticsService.getReleaseStatisticsCount(params);
        List<ReleaseStatisticsCountVo> countList = new ArrayList<>();
        countList.add(releaseStatisticsCount);

        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/financeStatistics.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, countList.size());
        Workbook workbook = exportDetailUtil.getWorkbook();
        for (int i = 0; i < countList.size(); i++) {
            //获取导出数据对象
            ReleaseStatisticsCountVo countVo = countList.get(i);

            JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(countVo));
            //表格excel中sheet名称

            workbook.setSheetName(i,i+"");
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
        response.setHeader("Content-Disposition", "attachment;filename=" + "统计数据.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("统计数据.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }


    @SofnLog("全部统计增值流程信息")
    @ApiOperation(value="全部统计增值流程信息")
    @GetMapping("/getFinanceStatisticsAll")
    public Result<List<ReleaseStatisticsCountVo>> getFinanceStatisticsAll(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)String countyId){

        Map<String, Object> params = getParamsByLogin(belongYear, provinceId, cityId, countyId);

        List<ReleaseStatisticsCountVo> releaseStatisticsCount = proliferationReleaseStatisticsService.getReleaseStatisticsAllCount(params);
        return Result.ok(releaseStatisticsCount);
    }

    @SofnLog("导出全部统计增值流程信息")
    @ApiOperation(value="导出全部统计增值流程信息")
    @GetMapping(value ="/exportFinanceStatisticsAll", produces = "application/octet-stream")
    public void exportFinanceStatisticsAll(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省(省级用户必填)")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市(市级用户必填)")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县(区县级用户必填)")@RequestParam(value = "countyId",required = false)String countyId,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        Map<String, Object> params = getParamsByLogin(belongYear, provinceId, cityId, countyId);

        FinanceStatisticsVo financeStatistics = new FinanceStatisticsVo();
        List<ReleaseStatisticsCountVo> countList = proliferationReleaseStatisticsService.getReleaseStatisticsAllCount(params);
        financeStatistics.setCountList(countList);
        List<FinanceStatisticsVo> voList = new ArrayList<>();
        voList.add(financeStatistics);
        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/allFinanceStatistics.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, voList.size());
        Workbook workbook = exportDetailUtil.getWorkbook();
        for (int i = 0; i < voList.size(); i++) {
            FinanceStatisticsVo financeStatisticsVo = voList.get(i);
            JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(financeStatisticsVo));
            //表格excel中sheet名称
            workbook.setSheetName(i, i+"");
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
                        if (cellValue.contains("$")) {
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
                                    Map data = Maps.newHashMap();
                                    for (String field : fields) {
                                        data.put(field, ((JSONObject) o).get(field));
                                    }
                                    datas.add(data);
                                }
                                exportDetailUtil.appendRows(j, datas, fields);
                               /* j = j + jatemp.size();
                                sheet.shiftRows(j + 1, sheet.getLastRowNum(), -1);
                                rowNumber = sheet.getLastRowNum();*/
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
        response.setHeader("Content-Disposition", "attachment;filename=" + "统计数据.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("统计数据.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);


    }
}
