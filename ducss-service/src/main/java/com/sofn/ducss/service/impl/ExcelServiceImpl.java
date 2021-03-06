package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.*;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExcel;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExcel;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.ExcelDataUtil;
import com.sofn.ducss.util.ExportUtil;
import com.sofn.ducss.util.ListUtils;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.vo.ExportDetailUtil;
import com.sofn.ducss.vo.DataAnalysis.DataKing;
import com.sofn.ducss.vo.DisperseUtilizeVo;
import com.sofn.ducss.vo.ProStillVo;
import com.sofn.ducss.vo.StrawUtilizeVo;
import com.sofn.ducss.vo.excelVo.DataAnalysisExcelVo;
import com.sofn.ducss.vo.excelVo.DisperseUtilizeExcelVo;
import com.sofn.ducss.vo.excelVo.StrawUtilizeExcelVo;
import com.sofn.ducss.vo.excelVo.YieldAndReturnExcelVo;
import com.xiaoleilu.hutool.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @ClassName ExcelServiceImpl
 * @Description Excel??????????????????
 * @Author Administrator
 * @Date 2020/7/1 10:15
 * Version1.0
 **/
@Service
@Slf4j
public class ExcelServiceImpl extends ServiceImpl<ProStillDetailMapper, ProStillDetail> implements ExcelService {
    @Autowired
    private CountryTaskMapper countryTaskMapper;
    @Autowired
    private DisperseUtilizeMapper disperseUtilizeMapper;

    @Autowired
    DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    StoredProcedureMapper storedProcedureMapper;

    @Autowired
    private ProStillDetailService proStillDetailService;
    @Autowired
    private ProStillService proStillService;
    @Autowired
    private DisperseUtilizeDetailService disperseUtilizeDetailService;
    @Autowired
    private StrawUtilizeDetailService strawUtilizeDetailService;

    @Autowired
    private SysApi sysApi;
    @Autowired
    private AsyncService asyncService;

    @Autowired
    private SearchService searchService;

    @Autowired
    SysDictionaryMapper sysDictionaryMapper;

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        String fileName = "???????????????????????????????????????.xlsx";
        List<YieldAndReturnExcel> exportList = new ArrayList<>();
        //????????????????????????
        YieldAndReturnExcel basicInformationExcel = new YieldAndReturnExcel();
        basicInformationExcel.setCollectionRatio(new BigDecimal(0.00));
        basicInformationExcel.setExportYield(new BigDecimal(0.00));
        basicInformationExcel.setGrainYield(new BigDecimal(0.00));
        basicInformationExcel.setGrassValleyRatio(new BigDecimal(0.00));
        basicInformationExcel.setReturnArea(new BigDecimal(0.00));
        basicInformationExcel.setSeedArea(new BigDecimal(0.00));
        basicInformationExcel.setStrawType("??????");
        exportList.add(basicInformationExcel);
        response.setCharacterEncoding("utf-8");
        ExportUtil.createExcel(YieldAndReturnExcel.class, exportList, response, fileName);
    }

    //todo ?????????????????????????????????
    @Override
    public Result importExcel(MultipartFile file, String year) {
        //??????????????????
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("????????????????????????????????????");
        }
        //??????excel????????????,??????????????????????????????
        //????????????????????????????????????
        List<YieldAndReturnExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, YieldAndReturnExcelVo.class, null);
        Map<String, Object> resultMap = new HashMap<>();
        //??????????????????????????????????????????
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        for (YieldAndReturnExcelVo excel : dataList) {
            if (null == excel.getSeedArea()) {
                excel.setSeedArea(new BigDecimal(0));
            }
            if (null == excel.getReturnArea()) {
                excel.setReturnArea(new BigDecimal(0));
            }
            if (null == excel.getExportYield()) {
                excel.setExportYield(new BigDecimal(0));
            }

            if (excel.getSeedArea().compareTo(excel.getReturnArea()) < 0) {
                return Result.error(excel.getStrawType() + "-??????????????????????????????????????????????????????");
            }
            if (excel.getExportYield().compareTo(
                    excel.getGrainYield()
                            .multiply(excel.getGrassValleyRatio())
                            .multiply(excel.getCollectionRatio())) > 0) {
                return Result.error(excel.getStrawType() + "-???????????????????????????????????????????????????????????????");
            }

            resultMap = CropsEnum.checkValue(resultMap, excel.getGrainYield(), excel.getGrassValleyRatio(), excel.getStrawType(), excel.getStrawType());
            String message = (String) resultMap.get("strawStr") + resultMap.get("threshold");
            if (!resultMap.isEmpty())
                return Result.error(message);
        }
        //????????????????????????????????????????????????
        ProStillVo proStillVo = new ProStillVo();
        proStillVo.setYear((String) params.get("year"));
        proStillVo.setDepartment(organization.getOrganizationName());
        proStillVo.setProStillId("");
        proStillVo.setAddTime(new Date());
        List<ProStillDetail> proStillDetails = new ArrayList<>(dataList.size());
        proStillDetails = copyExcelValueToDetail(dataList, proStillDetails);
        proStillVo.setProStillDetails(proStillDetails);
        //??????????????????????????????
        String result = proStillDetailService.addOrUpdateProStill(proStillVo, UserUtil.getLoginUserId());
        return Result.ok(result);
    }

    @Override
    public void downloadDisperseUtilizeTemplate(HttpServletResponse response) throws IOException {
        //String fileName = "2020????????????????????????????????????.xlsx";
        /*
        List<DisperseUtilizeExcel> excelList = new ArrayList<>();
        //????????????????????????
        DisperseUtilizeExcel basicInformationExcel = new DisperseUtilizeExcel();
        basicInformationExcel.setAddress("xx???xx???");
        basicInformationExcel.setBase(new BigDecimal(0.00));
        basicInformationExcel.setFarmerNo("00001");
        basicInformationExcel.setFarmerName("??????");
        basicInformationExcel.setFarmerPhone("13800000000");
        basicInformationExcel.setFertilising(new BigDecimal(0.00));
        basicInformationExcel.setForage(new BigDecimal(0.00));
        basicInformationExcel.setFuel(new BigDecimal(0.00));
        basicInformationExcel.setMaterial(new BigDecimal(0.00));
        basicInformationExcel.setReuse(new BigDecimal(0.00));
        basicInformationExcel.setSownArea(new BigDecimal(0.00));
        basicInformationExcel.setStrawName("??????");
        basicInformationExcel.setYieldPerMu(new BigDecimal(0.00));

        excelList.add(basicInformationExcel);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        //swagger?????????????????????????????????swagger???????????????;filename*=utf-8''??????????????????postman????????????????????????
        response.setHeader("Content-Disposition", "attachment;filename=?????????????????????????????????.xlsx");
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        ExcelExportUtil.createExcel(response, DisperseUtilizeExcel.class, excelList, "?????????????????????????????????.xlsx");
        */
        //???????????????????????????
        ClassPathResource resource = new ClassPathResource("static/?????????????????????????????????.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, 1);
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("?????????????????????????????????.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    @Override
    public Result importDisperseUtilize(MultipartFile file, String year) {
        //??????????????????
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("????????????????????????????????????");
        }
        //?????????????????????????????????????????????
        List<String> years = Arrays.asList(params.get("year").toString().split(","));
        List<String> countyIds = Arrays.asList(params.get("areaId").toString().split(","));
        ProStill proStill = proStillService.getProStill(years, countyIds);
        if (null == proStill) {
            throw new SofnException("????????????????????????????????????????????????????????????");
        }
        //??????????????????????????????????????????
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        //??????excel????????????,??????????????????????????????
        //????????????????????????????????????
        List<DisperseUtilizeExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, DisperseUtilizeExcelVo.class, 0);
        checkDate2(dataList);
        //???????????????
        Map<String, List<DisperseUtilizeExcelVo>> voGroupMap = dataList.stream().collect(Collectors.groupingBy(
                DisperseUtilizeExcelVo::getFarmerNo));
        String result = "";
        //??????????????????????????????????????????
        for (Map.Entry<String, List<DisperseUtilizeExcelVo>> entry : voGroupMap.entrySet()) {
            List<DisperseUtilizeExcelVo> voList = entry.getValue();
            if (null == voList || voList.isEmpty()) continue;

            //????????????????????????????????????????????????
            DisperseUtilizeVo disperseUtilizeVo = new DisperseUtilizeVo();
            disperseUtilizeVo.setYear((String) params.get("year"));
            disperseUtilizeVo.setDepartment(organization.getOrganizationName());
            disperseUtilizeVo.setDisperseUtilizeId("");
            disperseUtilizeVo.setAddTime(new Date());
            disperseUtilizeVo.setAddress(voList.get(0).getAddress());
            disperseUtilizeVo.setFarmerName(voList.get(0).getFarmerName());
            disperseUtilizeVo.setFarmerNo(voList.get(0).getFarmerNo());
            disperseUtilizeVo.setFarmerPhone(voList.get(0).getFarmerPhone());
            List<DisperseUtilizeDetail> details = new ArrayList<>(voList.size());
            details = copyExcelValToDetail(voList, details);
            disperseUtilizeVo.setDisperseUtilizeDetailList(details);
            //??????????????????????????????
            result = disperseUtilizeDetailService.addOrUpdateDisperseUtilizeDetail(disperseUtilizeVo, UserUtil.getLoginUserId());

        }

//        //????????????????????????????????????????????????
//        DisperseUtilizeVo disperseUtilizeVo = new DisperseUtilizeVo();
//        disperseUtilizeVo.setYear((String) params.get("year"));
//        disperseUtilizeVo.setDepartment(organization.getOrganizationName());
//        disperseUtilizeVo.setDisperseUtilizeId("");
//        disperseUtilizeVo.setAddTime(new Date());
//        disperseUtilizeVo.setAddress(dataList.get(0).getAddress());
//        disperseUtilizeVo.setFarmerName(dataList.get(0).getFarmerName());
//        disperseUtilizeVo.setFarmerNo(dataList.get(0).getFarmerNo());
//        disperseUtilizeVo.setFarmerPhone(dataList.get(0).getFarmerPhone());
//        List<DisperseUtilizeDetail> details = new ArrayList<>(dataList.size());
//        details = copyExcelValToDetail(dataList, details);
//        disperseUtilizeVo.setDisperseUtilizeDetailList(details);
//        //??????????????????????????????
//        String result = disperseUtilizeDetailService.addOrUpdateDisperseUtilizeDetail(disperseUtilizeVo, UserUtil.getLoginUserId());
        return Result.ok(result);
    }

    /***
     * ????????????????????????excel xl 2021/03/29
     * @param file
     * @param year
     * @return
     */
    @Override
    public Result importDisperseUtilizeV2(MultipartFile file, String year) {
        //??????????????????
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("????????????????????????????????????");
        }
        if (tasks == null && tasks.size() <= 0) {
            throw new SofnException("????????????????????????????????????");
        }
        CountryTask task = tasks.get(0);
        if (task.getStatus() == Constants.ExamineState.REPORTED
                || task.getStatus() == Constants.ExamineState.READ
                || task.getStatus() == Constants.ExamineState.PASSED) {
            throw new SofnException("???????????????????????????????????????????????????");
        }
        //?????????????????????????????????????????????
        List<String> years = Arrays.asList(params.get("year").toString().split(","));
        List<String> countyIds = Arrays.asList(params.get("areaId").toString().split(","));
        ProStill proStill = proStillService.getProStill(years, countyIds);
        if (null == proStill) {
            throw new SofnException("?????????????????????????????????????????????????????????!");
        }
        //??????????????????????????????????????????
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        if (organization == null) {
            throw new SofnException("??????????????????????????????????????????!");
        }
        String regioncode = organization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //?????????????????????????????????????????????
        if (!RegionLevel.COUNTY.getCode().equals(organization.getOrganizationLevel())) {
            throw new SofnException("??????????????????????????????!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);
        List<DisperseUtilizeExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, DisperseUtilizeExcelVo.class, 0);
        if (dataList != null && dataList.size() > 0) { // ??????excel????????????
            checkDate2(dataList);
            //???????????????
            Map<String, List<DisperseUtilizeExcelVo>> voGroupMap = dataList.stream().collect(Collectors.groupingBy(
                    DisperseUtilizeExcelVo::getFarmerNo));
            List<DisperseUtilizeVo> disperseUtilizeVoList = new ArrayList<>();
            DisperseUtilizeVo disperseUtilizeVo = null;
            String utilizeId = null;
            List<DisperseUtilizeDetail> details = null;
            for (Map.Entry<String, List<DisperseUtilizeExcelVo>> entry : voGroupMap.entrySet()) {
                List<DisperseUtilizeExcelVo> voList = entry.getValue();
                Integer flag = disperseUtilizeMapper.isDisperseExists(year, voList.get(0).getFarmerName().trim(), voList.get(0).getFarmerPhone(), countyId);
                if (flag > 0) { // ????????????????????????????????????excel????????????
                    throw new SofnException("???excel??????????????????????????????,???????????????");
                }
                disperseUtilizeVo = new DisperseUtilizeVo();
                disperseUtilizeVo.setYear((String) params.get("year"));
                disperseUtilizeVo.setDepartment(organization.getOrganizationName());
                utilizeId = IdUtil.getUUId();
                disperseUtilizeVo.setDisperseUtilizeId(utilizeId);
                disperseUtilizeVo.setAddTime(new Date());
                disperseUtilizeVo.setAddress(voList.get(0).getAddress());
                disperseUtilizeVo.setFarmerName(voList.get(0).getFarmerName().trim());
                String farmerNo = disperseUtilizeDetailService.createFillNumber("FS" + countyId);
                disperseUtilizeVo.setFarmerNo(farmerNo);
                disperseUtilizeVo.setFarmerPhone(voList.get(0).getFarmerPhone());
                details = new ArrayList<>();
                details = copyExcelValToDetail(voList, details);
                for (int i = 0; i < details.size(); i++) {
                    if ((details.get(i).getReuse().compareTo(new BigDecimal(0)) == 1) && StringUtils.isEmpty(details.get(i).getApplication())) {
                        throw new SofnException("??????????????? " + details.get(i).getFarmerName() + " ???[" + details.get(i).getStrawName() + "]??????????????????????????????");
                    }
                    details.get(i).setId(IdUtil.getUUId());
                    details.get(i).setUtilizeId(utilizeId);
                }
                disperseUtilizeVo.setDisperseUtilizeDetailList(details);
                disperseUtilizeVoList.add(disperseUtilizeVo);
            }
            List<DisperseUtilize> disperseUtilizeList = null;  // ??????????????????
            List<DisperseUtilizeDetail> disperseUtilizeDetailList = null; // ??????????????????
            if (disperseUtilizeVoList != null && disperseUtilizeVoList.size() > 0) { // ??????????????????
                disperseUtilizeList = new ArrayList<>();
                disperseUtilizeDetailList = new ArrayList<>();
                DisperseUtilize disperseUtilize = null;
                for (int i = 0; i < disperseUtilizeVoList.size(); i++) { // ????????????????????????
                    disperseUtilize = new DisperseUtilize();
                    disperseUtilize.setId(disperseUtilizeVoList.get(i).getDisperseUtilizeId());
                    disperseUtilize.setFillNo(disperseUtilizeVoList.get(i).getFarmerNo().substring(2));
                    disperseUtilize.setAreaId(countyId);
                    disperseUtilize.setCityId(cityId);
                    disperseUtilize.setProvinceId(provinceId);
                    disperseUtilize.setCreateDate(new Date());
                    disperseUtilize.setAddress(disperseUtilizeVoList.get(i).getAddress());
                    disperseUtilize.setCreateUserId(UserUtil.getLoginUserId());
                    disperseUtilize.setCreateUserName(UserUtil.fetchLoginUserNameInToken());
                    disperseUtilize.setReportArea(disperseUtilizeVoList.get(i).getDepartment());
                    disperseUtilize.setFarmerName(disperseUtilizeVoList.get(i).getFarmerName().trim());
                    disperseUtilize.setFarmerNo(disperseUtilizeVoList.get(i).getFarmerNo());
                    disperseUtilize.setFarmerPhone(disperseUtilizeVoList.get(i).getFarmerPhone());
                    disperseUtilize.setYear(disperseUtilizeVoList.get(i).getYear());
                    disperseUtilize.setCreateTime(new Date());
                    disperseUtilizeList.add(disperseUtilize);
                    disperseUtilizeDetailList.addAll(disperseUtilizeVoList.get(i).getDisperseUtilizeDetailList());
                }
                Integer count = disperseUtilizeMapper.insertBatch(disperseUtilizeList); // ????????????
                if (count > 0) { // ??????????????????
                    countryTaskMapper.updateDynamicNumById(task.getId(), disperseUtilizeList.size(), disperseUtilizeList.size()); // ???????????????????????????
                }
                List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
                List<DisperseUtilizeDetail> list3 = new ArrayList<>();
                for (int i = 0; i < disperseUtilizeDetailList.size(); i++) { // ??????????????????????????????
                    if ((disperseUtilizeDetailList.get(i).getReuse().compareTo(new BigDecimal(0)) == 1) && StringUtils.isEmpty(disperseUtilizeDetailList.get(i).getApplication())) {
                        throw new SofnException("??????????????? " + disperseUtilizeDetailList.get(i).getFarmerName() + " ???[" + disperseUtilizeDetailList.get(i).getStrawName() + "]??????????????????????????????");
                    }
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getDictname().equals(disperseUtilizeDetailList.get(i).getStrawName())) {
                            list3.add(disperseUtilizeDetailList.get(i));
                        }
                    }
                }
                // ????????????
                if (list3 != null && list3.size() > 0) {//  // ????????????????????????
                    List<List<DisperseUtilizeDetail>> lists = com.sofn.ducss.util.ListUtils.groupList(list3, 1000);
                    CountDownLatch countDownLatch = new CountDownLatch(lists.size());
                    for (List<DisperseUtilizeDetail> listSub : lists) {
                        asyncService.executeAsync(listSub, disperseUtilizeDetailMapper, countDownLatch);
                    }
                    StoredProcedure storedProcedure = null;
                    List<StoredProcedure> storedProcedureList = new ArrayList<>();
                    for (DisperseUtilizeDetail proStillDetail : list3) {
                        storedProcedure = new StoredProcedure();
                        storedProcedure.setId(UUID.randomUUID().toString());
                        storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                        storedProcedure.setAreaIdData(countyId);
                        storedProcedure.setYearData(year);
                        storedProcedureList.add(storedProcedure);
                    }
                    List<List<StoredProcedure>> StoredProcedureLists = com.sofn.ducss.util.ListUtils.groupList(storedProcedureList, 1000);
                    for (List<StoredProcedure> listSub : StoredProcedureLists) {
                        asyncService.excuteAsyncByStored(listSub, storedProcedureMapper, countDownLatch);
                    }
                }
            }
        }
        LogUtil.addLog(LogEnum.LOG_TYPE_ADD.getCode(), "??????????????????-" + year + "-<?????????????????????>");
        return Result.ok("????????????");
    }

    private void checkDate2(List<DisperseUtilizeExcelVo> excelVos) {
        //????????????????????????
        int rows = 2;
        String message = "";

        for (DisperseUtilizeExcelVo excelVo : excelVos) {

            boolean b = excelVo.getFarmerPhone().matches("[0-9-()??????]{7,11}");
            if (!b) {
                message = message + "???" + rows + "??????3?????????????????????,";
            }

            if (!"".equals(message)) {
                String s = message.substring(0, message.length() - 1);
                throw new SofnException(s);
            }
            rows++;
        }


    }

    @Override
    public void downloadStrawUtilizeTemplate(HttpServletResponse response) throws IOException {
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        //???????????????????????????
        ClassPathResource resource = new ClassPathResource("static/????????????????????????????????????????????????.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, 1);
        response.reset();
        /*
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        */
        //swagger?????????????????????????????????swagger???????????????;filename*=utf-8''??????????????????postman????????????????????????
        /*
        response.setHeader("Content-Disposition", "attachment;filename=????????????????????????????????????.xlsx;"
                + URLEncoder.encode("????????????????????????????????????.xlsx", "utf-8"));
        */
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("????????????????????????????????????.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    @Override
    public Result importStrawUtilizeExcel(MultipartFile file, String year) {
        //??????????????????
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("????????????????????????????????????");
        }
        //?????????????????????????????????????????????
        List<String> years = Arrays.asList(params.get("year").toString().split(","));
        List<String> countyIds = Arrays.asList(params.get("areaId").toString().split(","));
        ProStill proStill = proStillService.getProStill(years, countyIds);
        if (null == proStill) {
            throw new SofnException("????????????????????????????????????????????????????????????");
        }

        //??????excel????????????,??????????????????????????????
        //????????????????????????????????????
        List<StrawUtilizeExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, StrawUtilizeExcelVo.class, null);
        //??????????????????????????????
        checkDate(dataList);
        //??????????????????????????????????????????
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        //???????????????????????????
        Map<String, List<StrawUtilizeExcelVo>> voGroupMap = dataList.stream().collect(Collectors.groupingBy(
                StrawUtilizeExcelVo::getMainNo));
        String result = "";
        //??????????????????????????????????????????
        for (Map.Entry<String, List<StrawUtilizeExcelVo>> entry : voGroupMap.entrySet()) {
            List<StrawUtilizeExcelVo> voList = entry.getValue();
            if (null == voList || voList.isEmpty()) continue;
            //????????????????????????????????????????????????
            StrawUtilizeVo strawUtilizeVo = new StrawUtilizeVo();
            strawUtilizeVo.setYear((String) params.get("year"));
            strawUtilizeVo.setStrawUtilizeId("");
            strawUtilizeVo.setAddTime(new Date());
            strawUtilizeVo.setAddress(voList.get(0).getAddress());
            strawUtilizeVo.setCorporationName(voList.get(0).getCorporationName());
            strawUtilizeVo.setMainName(voList.get(0).getMainName());
            strawUtilizeVo.setCorporationName(voList.get(0).getCorporationName());
            strawUtilizeVo.setMobilePhone(voList.get(0).getMobilePhone());
            strawUtilizeVo.setDepartment(organization.getOrganizationName());

            List<StrawUtilizeDetail> details = new ArrayList<>(entry.getValue().size());
            details = copyStrawExcelValToDetail(voList, details);
            strawUtilizeVo.setStrawUtilizeDetailList(details);
            //??????????????????????????????
            result = strawUtilizeDetailService.addOrUpdateStrawUtilizeDetail(strawUtilizeVo, UserUtil.getLoginUserId());
        }

        return Result.ok(result);
    }

    private void checkDate(List<StrawUtilizeExcelVo> dataList) {
        //????????????????????????
        int rows = 3;
        String message = "";
        for (StrawUtilizeExcelVo excelVo : dataList) {
            // ???????????????
            if (StringUtils.isBlank(excelVo.getMainNo())) {
                throw new SofnException("?????????????????????");
            }
            if (StringUtils.isBlank(excelVo.getMainName())) {
                throw new SofnException("???????????????????????????");
            }
            if (StringUtils.isBlank(excelVo.getCorporationName())) {
                throw new SofnException("?????????????????????");
            }
            if (StringUtils.isBlank(excelVo.getAddress())) {
                throw new SofnException("?????????????????????");
            }
            //????????????
            if (excelVo.getMainName() != null) {
                if (excelVo.getMainName().length() > 128) {
                    throw new SofnException("?????????????????????????????????128??????");
                }
            }

            if (excelVo.getCorporationName() != null) {
                if (excelVo.getCorporationName().length() > 16) {
                    throw new SofnException("???????????????????????????16??????");
                }
            }

            if (excelVo.getAddress() != null) {
                if (excelVo.getAddress().length() > 255) {
                    throw new SofnException("?????????????????????255??????");
                }
            }

            //?????????????????????????????????
            if (excelVo.getMobilePhone() != null) {
                boolean b = excelVo.getMobilePhone().matches("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
                if (excelVo.getMobilePhone().length() > 11) {
                    b = false;
                }
                if (!b) {
                    message = message + "???" + rows + "??????4?????????????????????,";
                }
            } else {
                message = message + "???" + rows + "??????4?????????????????????,";
            }
            if (excelVo.getFertilising() != null) {
                boolean b = excelVo.getFertilising().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "???" + rows + "??????7???????????????????????????,";
                }
            } else {
                excelVo.setFertilising(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getForage() != null) {
                boolean b = excelVo.getForage().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "???" + rows + "??????8???????????????????????????,";
                }
            } else {
                excelVo.setForage(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getFuel() != null) {
                boolean b = excelVo.getFuel().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "???" + rows + "??????9???????????????????????????,";
                }
            } else {
                excelVo.setFuel(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getBase() != null) {
                boolean b = excelVo.getBase().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "???" + rows + "??????10???????????????????????????,";
                }
            } else {
                excelVo.setBase(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getMaterial() != null) {
                boolean b = excelVo.getMaterial().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "???" + rows + "??????11???????????????????????????,";
                }
            } else {
                excelVo.setMaterial(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getOther() != null) {
                boolean b = excelVo.getOther().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "???" + rows + "??????12??????????????????????????????,";
                }
            } else {
                excelVo.setFertilising(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            //????????????????????????????????????
            excelVo.setOwnCountry(excelVo.getFertilising().add(excelVo.getForage()).add(excelVo.getFuel()).add(excelVo.getBase()).add(excelVo.getMaterial()));
            if (excelVo.getOwnCountry().compareTo(excelVo.getOther()) < 0) {
                message = message + "???" + rows + "????????????????????????????????????????????????,";
            }
            if (!"".equals(message)) {
                String s = message.substring(0, message.length() - 1);
                throw new SofnException(s);
            }
            rows++;
        }
    }

    //List??????,??????????????????
    private List<ProStillDetail> copyExcelValueToDetail(List<YieldAndReturnExcelVo> dataList, List<ProStillDetail> detailList) {
        if (dataList.isEmpty()) return new ArrayList<>();
        //??????????????????????????????????????????????????????
        if (dataList.size() > 14) {
            throw new SofnException("???????????????????????????????????????????????????14???");
        }
        Set<String> cropSet = new HashSet<>();
        for (YieldAndReturnExcelVo vo : dataList) {
            if (CropsEnum.getByChineseName(vo.getStrawType()) == null) {
                throw new SofnException(vo.getStrawType() + "?????????????????????");
            }
            ProStillDetail detail = new ProStillDetail();
            BeanUtils.copyProperties(vo, detail);
            detail.setStrawName(vo.getStrawType());
            detail.setStrawType(CropsEnum.getByChineseName(vo.getStrawType()) == null ? ""
                    : CropsEnum.getByChineseName(vo.getStrawType()).getName());
            cropSet.add(detail.getStrawType());
            detailList.add(detail);
        }

        //????????????????????????????????????????????????????????????????????????
        if (cropSet.size() < dataList.size()) {
            throw new SofnException("?????????????????????????????????????????????");
        }

        //????????????excel??????????????????????????????
        for (CropsEnum ce : CropsEnum.values()) {
            if (cropSet.contains(ce.getName()))
                continue;
            ProStillDetail detail = new ProStillDetail();
            detail.setStrawType(ce.getName());
            detail.setStrawName(ce.getChineseName());
            detailList.add(detail);
        }

        return detailList;
    }

    private List<DisperseUtilizeDetail> copyExcelValToDetail(List<DisperseUtilizeExcelVo> dataList, List<DisperseUtilizeDetail> detailList) {
        if (dataList.isEmpty()) return new ArrayList<>();
        //??????????????????????????????????????????????????????
        if (dataList.size() > 14) {
            throw new SofnException("???????????????????????????????????????????????????14???");
        }
        Set<String> cropSet = new HashSet<>();
        for (DisperseUtilizeExcelVo vo : dataList) {
            if (CropsEnum.getByChineseName(vo.getStrawName()) == null) {
                throw new SofnException(vo.getStrawName() + "?????????????????????");
            }
            DisperseUtilizeDetail detail = new DisperseUtilizeDetail();
            BeanUtils.copyProperties(vo, detail);
            detail.setStrawType(CropsEnum.getByChineseName(vo.getStrawName()) == null ? ""
                    : CropsEnum.getByChineseName(vo.getStrawName()).getName());
            detail.setStrawName(vo.getStrawName());
            cropSet.add(detail.getStrawType());

            detailList.add(detail);
        }
        //????????????????????????????????????????????????????????????????????????
        if (cropSet.size() < dataList.size()) {
            throw new SofnException("?????????????????????????????????????????????");
        }
        //????????????excel??????????????????????????????
        for (CropsEnum ce : CropsEnum.values()) {
            if (cropSet.contains(ce.getName()))
                continue;
            DisperseUtilizeDetail detail = new DisperseUtilizeDetail();
            detail.setStrawType(ce.getName());
            detail.setStrawName(ce.getChineseName());
            detailList.add(detail);
        }

        return detailList;
    }

    private List<StrawUtilizeDetail> copyStrawExcelValToDetail(List<StrawUtilizeExcelVo> dataList, List<StrawUtilizeDetail> detailList) {
        if (dataList.isEmpty()) return new ArrayList<>();
        //??????????????????????????????????????????????????????
        if (dataList.size() > 14) {
            throw new SofnException("???????????????????????????????????????????????????14???");
        }
        Set<String> cropSet = new HashSet<>();
        for (StrawUtilizeExcelVo vo : dataList) {
            if (CropsEnum.getByChineseName(vo.getStrawName()) == null) {
                throw new SofnException(vo.getStrawName() + "?????????????????????");
            }
            StrawUtilizeDetail detail = new StrawUtilizeDetail();
            BeanUtils.copyProperties(vo, detail);
            detail.setStrawType(CropsEnum.getByChineseName(vo.getStrawName()) == null ? ""
                    : CropsEnum.getByChineseName(vo.getStrawName()).getName());
            detail.setStrawName(vo.getStrawName());
            cropSet.add(detail.getStrawType());
            detailList.add(detail);
        }
        //????????????????????????????????????????????????????????????????????????
        if (cropSet.size() < dataList.size()) {
            throw new SofnException("?????????????????????????????????????????????");
        }
        //????????????excel??????????????????????????????
        for (CropsEnum ce : CropsEnum.values()) {
            if (cropSet.contains(ce.getName()))
                continue;
            StrawUtilizeDetail detail = new StrawUtilizeDetail();
            detail.setStrawType(ce.getName());
            detail.setStrawName(ce.getChineseName());
            detailList.add(detail);
        }
        return detailList;
    }

    //??????????????????
    private Map<String, Object> combinConditionMap(MultipartFile file, String year) {

        if (StringUtils.isEmpty(year)) {
            //?????????????????????????????????????????????????????????
            year = file.getOriginalFilename().indexOf("???") > -1 ? file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("???")) :
                    String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("??????????????????????????????????????????!");
        }

        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regionCode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regionCode, String.class);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("areaId", areaList.get(2));

        return params;
    }

    @Override
    public void dataAnalysisExport(List<DataAnalysisExcelVo> list, HttpServletResponse response) {
        try {
            ClassPathResource resource = new ClassPathResource("static/????????????.xlsx");
            InputStream inputStream = resource.getInputStream();
            ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream);
            Workbook workbook = exportDetailUtil.getWorkbook();
            workbook.setSheetName(0, "????????????????????????");
            exportDetailUtil.setSheet(workbook.getSheetAt(0));
            Sheet sheet = exportDetailUtil.getSheet();
            //TODO
//            exportDetailUtil.replaceCellValue(0, 4, 2019);
            Row row = sheet.getRow(0);
            String[] years = list.get(0).getGYear().split(",");
            String[] indexes = list.get(0).getIndexs().split(",");
            String[] Indicator = list.get(0).getIndicatorArrays().split(",");
            for (int i = 0; i < years.length; i++) {
                for (int j = 0; j < indexes.length; j++) {
                    Cell cell = row.createCell(2 + i * indexes.length + j);
                    if (j == 0) {
                        cell.setCellValue(years[i]);
                    }
                }
                if (indexes.length > 1) {
                    CellRangeAddress region = new CellRangeAddress(0, 0, 2 + i * indexes.length, 2 + (i + 1) * indexes.length - 1);
                    sheet.addMergedRegion(region);
                }
            }
            Row row_index = sheet.getRow(1);
            for (int i = 0; i < years.length; i++) {
                for (int j = 0; j < indexes.length; j++) {
                    Cell cell = row_index.createCell(2 + i * indexes.length + j);
                    cell.setCellValue(indexes[j]);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Row row_index_data = sheet.createRow(i + 2);
                //?????????????????????0
                if ("".equals(Indicator[0])) {
                    for (int j = 0; j < years.length + indexes.length + 2; j++) {
                        Cell cell = row_index_data.createCell(j);
                        if (j == 0) {
                            cell.setCellValue(list.get(i).getArea_Name());
                            continue;
                        }
                        if (j == 1) {
                            cell.setCellValue(list.get(i).getStrawName());
                            continue;
                        }
                        if (j == 2) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[0]);
                            }
                            continue;
                        }
                        if (j == 3) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[1]);
                            }
                            continue;
                        }
                        if (j == 4) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[2]);
                            }
                            continue;
                        }
                        if (j == 5) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[3]);
                            }
                            continue;
                        }
                        if (j == 6) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[4]);
                            }
                            continue;
                        }
                        if (j == 7) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[5]);
                            }
                            continue;
                        }
                        if (j == 8) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[6]);
                            }
                            continue;
                        }
                        if (j == 9) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[7]);
                            }
                            continue;
                        }
                        if (j == 10) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[8]);
                            }
                            continue;
                        }
                        if (j == 11) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[9]);
                            }
                            continue;
                        }
                        if (j == 12) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[10]);
                            }
                            continue;
                        }
                        if (j == 13) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[11]);
                            }
                            continue;
                        }
                        if (j == 14) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[12]);
                            }
                            continue;
                        }
                        if (j == 15) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[13]);
                            }
                            continue;
                        }
                        if (j == 16) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[14]);
                            }
                            continue;
                        }
                        if (j == 17) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[15]);
                            }
                            continue;
                        }
                        if (j == 18) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[16]);
                            }
                            continue;
                        }
                        if (j == 19) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[17]);
                            }
                            continue;
                        }
                        if (j == 20) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[18]);
                            }
                            continue;
                        }
                        if (j == 21) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[19]);
                            }
                            continue;
                        }
                        if (j == 22) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[20]);
                            }
                            continue;
                        }
                        if (j == 23) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[21]);
                            }
                            continue;
                        }
                        if (j == 24) {
                            cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[22]);
                            continue;
                        }
                        if (j == 25) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[23]);
                            }
                            continue;
                        }
                        if (j == 26) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[24]);
                            }
                            continue;
                        }
                    }
                } else {
                    for (int j = 0; j < Indicator.length + 2; j++) {
                        Cell cell = row_index_data.createCell(j);
                        if (j == 0) {
                            cell.setCellValue(list.get(i).getArea_Name());
                            continue;
                        }
                        if (j == 1) {
                            cell.setCellValue(list.get(i).getStrawName());
                            continue;
                        }
                        if (j == 2) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[0]);
                            }
                            continue;
                        }
                        if (j == 3) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[1]);
                            }
                            continue;
                        }
                        if (j == 4) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[2]);
                            }
                            continue;
                        }
                        if (j == 5) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[3]);
                            }
                            continue;
                        }
                        if (j == 6) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[4]);
                            }
                            continue;
                        }
                        if (j == 7) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[5]);
                            }
                            continue;
                        }
                        if (j == 8) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[6]);
                            }
                            continue;
                        }
                        if (j == 9) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[7]);
                            }
                            continue;
                        }
                        if (j == 10) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[8]);
                            }
                            continue;
                        }
                        if (j == 11) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[9]);
                            }
                            continue;
                        }
                        if (j == 12) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[10]);
                            }
                            continue;
                        }
                        if (j == 13) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[11]);
                            }
                            continue;
                        }
                        if (j == 14) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[12]);
                            }
                            continue;
                        }
                        if (j == 15) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[13]);
                            }
                            continue;
                        }
                        if (j == 16) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[14]);
                            }
                            continue;
                        }
                        if (j == 17) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[15]);
                            }
                            continue;
                        }
                        if (j == 18) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[16]);
                            }
                            continue;
                        }
                        if (j == 19) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[17]);
                            }
                            continue;
                        }
                        if (j == 20) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[18]);
                            }
                            continue;
                        }
                        if (j == 21) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[19]);
                            }
                            continue;
                        }
                        if (j == 22) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[20]);
                            }
                            continue;
                        }
                        if (j == 23) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[21]);
                            }
                            continue;
                        }
                        if (j == 24) {
                            cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[22]);
                            continue;
                        }
                        if (j == 25) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[23]);
                            }
                            continue;
                        }
                        if (j == 26) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[24]);
                            }
                            continue;
                        }
                    }
                }
            }
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger?????????????????????????????????swagger???????????????;filename*=utf-8''??????????????????postman????????????????????????
            response.setHeader("Content-Disposition", "attachment;filename=" + "????????????.xlsx" + ";filename*=utf-8''"
                    + URLEncoder.encode("????????????.xlsx", "utf-8"));
            OutputStream os = response.getOutputStream();
            inputStream.close();
            exportDetailUtil.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void datasAnalysisExport(Map<String, String> paramMap, HttpServletResponse response) throws Exception {
        // ??????????????????
        if (paramMap != null) {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger?????????????????????????????????swagger???????????????;filename*=utf-8''??????????????????postman????????????????????????
            response.setHeader("Content-Disposition", "attachment;filename=" + "????????????.xls" + ";filename*=utf-8''"
                    + URLEncoder.encode("????????????.xls", "utf-8"));
            String analysisIndex = paramMap.get("analysisIndex");
            List<String> headerList = ListUtils.splitToList(analysisIndex); // ??????
            List<String> strawNameList = new ArrayList<>(); // ???????????????????????????
            List<String> headList = new ArrayList<>();
            for (String item : headerList) {
                headList.add(AnalyIndexEnum.getValue(item));
                strawNameList.add(AnalyIndexEnum.getDataValue(item));
            }
            List<DataKing> dataList = searchService.getDataKingList(paramMap);
            String year = paramMap.get("year");
            HSSFWorkbook workbook = ExcelDataUtil.createWorkbook(headList, strawNameList, dataList, year);
            OutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
    }
}