package com.sofn.fyem.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.excelmodel.ProliferationReleaseExcel;
import com.sofn.fyem.service.BasicProliferationReleaseService;
import com.sofn.fyem.util.ExportDetailUtil;
import com.sofn.fyem.vo.MapIndexs;
import com.sofn.fyem.vo.ProliferationReleaseCountVo;
import com.sofn.fyem.vo.ProliferationReleaseInfosVo;
import com.sofn.fyem.vo.ProliferationReleaseLocationDistributionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 增殖放流统计汇总接口
 * @Author: DJH
 * @Date: 2020/5/9 13:50
 */
@Slf4j
@Api(value = "增殖放流统计汇总接口", tags = "增殖放流统计汇总接口")
@RestController
@RequestMapping("/releaseStatistics")
public class ReleaseStatisticsController extends BaseController {
    @Autowired
    private BasicProliferationReleaseService basicProliferationReleaseService;

    @SofnLog("获取增殖放流关键数据")
    @ApiOperation(value="获取增殖放流关键数据")
    @GetMapping("/getProliferationReleaseInfos")
    public Result<List<ProliferationReleaseInfosVo>> getProliferationReleaseInfos(
            @RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
            @RequestParam(value = "provinceId", required = false, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（非必传）", required = false) String provinceId,
            @RequestParam(value = "cityId", required = false, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（非必传）", required = false) String cityId,
            @RequestParam(value = "countyId", required = false, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（非必传）", required = false) String countyId,
            @RequestParam(value = "pageNo", required = true, defaultValue = "1") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        params.put("provinceId",provinceId);
        params.put("cityId",cityId);
        params.put("countyId",countyId);
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        PageUtils<ProliferationReleaseInfosVo> proliferationReleaseInfosVoList =
                basicProliferationReleaseService.getProliferationReleaseInfosByPage(params);
        return Result.ok(proliferationReleaseInfosVoList.getList());
    }

    @SofnLog("获取增殖放流关键数据-导出")
    @ApiOperation(value="获取增殖放流关键数据-导出")
    @GetMapping(value = "/exportProliferationReleaseInfos", produces = "application/octet-stream")
    public void exportProliferationReleaseInfos(
            @RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
            @RequestParam(value = "provinceId", required = false, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（非必传）", required = false) String provinceId,
            @RequestParam(value = "cityId", required = false, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（非必传）", required = false) String cityId,
            @RequestParam(value = "countyId", required = false, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（非必传）", required = false) String countyId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        params.put("provinceId",provinceId);
        params.put("cityId",cityId);
        params.put("countyId",countyId);
        List<ProliferationReleaseExcel> proliferationReleaseExcelList =
                basicProliferationReleaseService.getProliferationReleaseExcel(params);

        ProliferationReleaseCountVo proliferationReleaseCountVo = new ProliferationReleaseCountVo();
        proliferationReleaseCountVo.setCountList(proliferationReleaseExcelList);
        List<ProliferationReleaseCountVo> voList = new ArrayList<>();
        voList.add(proliferationReleaseCountVo);

        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/releaseStatistics.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, voList.size());
        Workbook workbook = exportDetailUtil.getWorkbook();
        for (int i = 0; i < voList.size(); i++) {
            ProliferationReleaseCountVo proliferationReleaseCount = voList.get(i);
            JSONObject jo = JSONObject.parseObject(JsonUtils.obj2json(proliferationReleaseCount));
            // 表格excel中sheet名称
            workbook.setSheetName(i, i+"");
            exportDetailUtil.setSheet(workbook.getSheetAt(i));
            Sheet sheet = exportDetailUtil.getSheet();
            int colum=sheet.getColumnWidth(0);
            // 获取行数
            int rowNumber = sheet.getLastRowNum();
            for (int j = 0; j <= rowNumber; j++) {
                Row row = sheet.getRow(j);
                // 获取每行的格子数(列数)
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
                                    Map data = new HashMap<>();
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
        response.setHeader("Content-Disposition", "attachment;filename=" + "各地区增殖放流关键数据统计表.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("各地区增殖放流关键数据统计表.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

//    @SofnLog("分布图示")
//    @ApiOperation(value="分布图示")
//    @GetMapping("/getProliferationReleaseDistributionInfos")
    @Deprecated
    public Result<List<ProliferationReleaseLocationDistributionVo>> getProliferationReleaseDistributionInfos(
            @RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传），查询全国分布情况传入年份", required = true) String belongYear,
            @RequestParam(value = "provinceId", required = false, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（非必传）,查询省分布情况传入id", required = false) String provinceId,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        params.put("provinceId",provinceId);
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseDistributionInfos =
                basicProliferationReleaseService.getProliferationReleaseDistributionInfos(params);
        return Result.ok(proliferationReleaseDistributionInfos);
    }

    @SofnLog("分布图示（新）")
    @ApiOperation(value="分布图示（新）")
    @GetMapping("/getMapViewData")
    public Result<MapViewData> getMapViewData(@RequestParam(value = "index", required = true, defaultValue = "release_points_distribute")  @ApiParam(name = "index", value = "指标（必传）,传入:release_points_distribute,release_count,invest_funds,release_activities_count",
                                                      allowableValues = "release_points_distribute,release_count,invest_funds,release_activities_count", required = true) String index,
                                              @RequestParam(value = "adLevel", required = true, defaultValue = "fyem_adMaster") @ApiParam(name = "adLevel", value = "地图行政级别（必传）,传值：fyem_adMaster:国家;fyem_adProv:省级;fyem_adCity:市级;fyem_adCounty:县级",
                                                      allowableValues = "fyem_adMaster,fyem_adProv,fyem_adCity,fyem_adCounty", required = true) String adLevel,
                                              @RequestParam(value = "adCode", required = false, defaultValue = "100000") @ApiParam(name = "adCode", value = "行政区域代码或行政区域名称（非必传）,查询省市县分布情况传入省市县id,查询全国则不传", required = false) String adCode,
                                              @RequestParam(value = "belongYear", required = true, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度（必传）,如：2020;不传或传0默认当前年", required = true) String belongYear,
                                              HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            belongYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        Map<String, String> conditions = new HashMap<>();
        conditions.put("belongYear", belongYear);
        MapViewData mapViewData = basicProliferationReleaseService.getMapViewData(index, adLevel, adCode, conditions);
        return Result.ok(mapViewData);
    }

    @SofnLog("分布图示指标")
    @ApiOperation(value="分布图示指标")
    @GetMapping("/getMapIndexs")
    public Result<List<MapIndexs>> getMapIndexs(
            @RequestParam(value = "desc", required = false, defaultValue = "")  @ApiParam(name = "desc", value = "指标名称（非必传）",
                     required = false) String desc,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        List<MapIndexs> mapMapIndexs = basicProliferationReleaseService.getMapIndexs(desc);

        return Result.ok(mapMapIndexs);
    }

//    @SofnLog("数据表指标维度")
//    @ApiOperation(value="数据表指标维度")
//    @GetMapping("/getMapConditions")
//    public Result<List<MapConditions>> getMapConditions(
//            @RequestParam(value = "index", required = true, defaultValue = "")  @ApiParam(name = "index", value = "指标code（必传）",
//                    allowableValues = "year,release_points_distribute,release_count,invest_funds,release_activities_count",required = true) String index,
//            HttpServletRequest request){
//        //根据token获取登录用户Id
//        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
//        if(StringUtils.isBlank(userId)){
//            throw new SofnException("当前登录用户异常");
//        }
//
//        List<MapConditions> mapConditions = basicProliferationReleaseService.getMapConditions(index);
//
//        return Result.ok(mapConditions);
//    }

}
