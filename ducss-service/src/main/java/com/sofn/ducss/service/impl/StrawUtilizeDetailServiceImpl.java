package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExportExcel;
import com.sofn.ducss.model.excelmodel.StrawUtilizeExcel;
import com.sofn.ducss.service.StrawUtilizeDetailService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.vo.ExportDetailUtil;
import com.sofn.ducss.vo.StrawUtilizeCombinVo;
import com.sofn.ducss.vo.StrawUtilizeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StrawUtilizeDetailServiceImpl extends ServiceImpl<StrawUtilizeDetailMapper, StrawUtilizeDetail> implements StrawUtilizeDetailService {
    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

    @Autowired
    private StrawUtilizeMapper strawUtilizeMapper;

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private CountryTaskMapper countryTaskMapper;

    @Autowired
    private StoredProcedureMapper storedProcedureMapper;

    @Autowired
    SysDictionaryMapper sysDictionaryMapper;

    @Override
    public List<StrawUtilizeDetail> getStrawUtilizeDetail(String strawUtilizeId) {
        List<StrawUtilizeDetail> list2 = strawUtilizeDetailMapper.getStrawUtilizeDetail(strawUtilizeId);
        if (list2.size() > 0) {
            List<StrawUtilizeDetail> result = new ArrayList<>();
            List<SysDictionary> list3 = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
            //List<SysDict> list3 = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
            for (SysDictionary sysDict : list3) {
                String name = sysDict.getDictValue();
                for (StrawUtilizeDetail strawUtilizeDetail : list2) {
                    String name2 = strawUtilizeDetail.getStrawName();
                    if (name.equals(name2)) {
                        result.add(strawUtilizeDetail);
                    }
                }
            }
            return result;
        } else {// ??????
            //List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
            List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
            List<StrawUtilizeDetail> result = new ArrayList<StrawUtilizeDetail>();
            for (SysDictionary sysDict : list) {
                StrawUtilizeDetail strawUtilizeDetail = new StrawUtilizeDetail();
                strawUtilizeDetail.setStrawName(sysDict.getDictValue());
                strawUtilizeDetail.setStrawType(sysDict.getDictKey());
                result.add(strawUtilizeDetail);
            }
            return result;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addOrUpdateStrawUtilizeDetail(StrawUtilizeVo strawUtilizeVo, String userId) {
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("??????????????????????????????????????????!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //?????????????????????????????????????????????
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("??????????????????????????????!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);

        /**
         * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
         * by:chlf  2019-04-15 14:35
         */
        //-------------------------------START-----------------------------------//
        List<ProStillDetail> ducProStilldetailList = proStillDetailMapper.getProStillDetailListByAreaId(countyId, strawUtilizeVo.getYear());
        Map<String, BigDecimal> dpsMap = new HashMap<String, BigDecimal>();
        HashSet dpsSet = new HashSet<>();
        BigDecimal count = new BigDecimal(0);
        for (ProStillDetail dpsd : ducProStilldetailList) {
            dpsMap.put(dpsd.getStrawName(), dpsd.getAssigned());
            count = count.add(dpsd.getAssigned());
            //???????????????????????????
            if (new BigDecimal(0).compareTo(dpsd.getAssigned()) < 0)
                dpsSet.add(dpsd.getStrawType());
        }
        //?????????????????????????????????????????????
        if (new BigDecimal(0).compareTo(count) > 0) {
            throw new SofnException("???????????????????????????????????????????????????!");
        }

        //??????????????????????????????????????????
        isDisperseUtilizeMatch(dpsSet, strawUtilizeVo.getStrawUtilizeDetailList());
        //-------------------------------END-----------------------------------//

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", strawUtilizeVo.getYear());
        params.put("areaId", countyId);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("????????????????????????????????????????????????/??????!");
            } else if (strawUtilizeVo.getStrawUtilizeId().equals("")) {// ????????????
//                Map<String, Object> params2 = Maps.newHashMap();
//                params2.put("year", strawUtilizeVo.getYear());
//                params2.put("areaId", countyId);
//                Integer count2 = strawUtilizeMapper.getStrawUtilizeTotalCount(params2);
//                if(count2>0){
//                    throw new SofnException("?????????????????????????????????????????????????????????");
//                }

                StrawUtilize strawUtilize = new StrawUtilize();
                strawUtilize.setId(IdUtil.getUUId());
                strawUtilize.setFillNo(createFillNumber(countyId));
                strawUtilize.setAreaId(countyId);
                strawUtilize.setCityId(cityId);
                strawUtilize.setProvinceId(provinceId);
                strawUtilize.setCreateDate(strawUtilizeVo.getAddTime());
                strawUtilize.setAddress(strawUtilizeVo.getAddress());
                strawUtilize.setCreateUserId(userId);
                strawUtilize.setCreateUserName(UserUtil.fetchLoginUserNameInToken());
                strawUtilize.setReportArea(strawUtilizeVo.getDepartment());
                strawUtilize.setMainName(strawUtilizeVo.getMainName());
                strawUtilize.setMainNo(strawUtilizeVo.getMainNo());
                strawUtilize.setCorporationName(strawUtilizeVo.getCorporationName());
                strawUtilize.setCompanyPhone(strawUtilizeVo.getCompanyPhone());
                strawUtilize.setMobilePhone(strawUtilizeVo.getMobilePhone());
                strawUtilize.setYear(strawUtilizeVo.getYear());
                strawUtilize.setCreateTime(new Date());
                strawUtilizeMapper.insertStrawUtilize(strawUtilize);
                updateCountryTask(0, 1, task);
                //?????????????????????????????????????????????
                String combinStr = provinceId + "," + cityId + "," + countyId;
                Integer year = strawUtilizeVo.getYear() == null ? null : Integer.valueOf(strawUtilizeVo.getYear());
                Result<String> result = sysApi.getRegionNamesByCodes(combinStr, year);
                List<StrawUtilizeDetail> detailList = strawUtilizeVo.getStrawUtilizeDetailList();
                for (StrawUtilizeDetail detail : detailList) {
                    detail.setId(IdUtil.getUUId());
                    detail.setUtilizeId(strawUtilize.getId());
                    detail.setMarketEnt(detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial()));
                    detail.setArea_Name(result.getData());     //????????????????????????
                    detail.setArea_Id(countyId);               //??????ID
                    setComprehensiveIndexValue(strawUtilize, detail);
                }

                List<StrawUtilizeDetail> list3 = new ArrayList<>();
                List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
                for (SysDict sysDict : list) {
                    String name = sysDict.getDictname();
                    for (StrawUtilizeDetail strawUtilizeDetail : detailList) {
                        String name2 = strawUtilizeDetail.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(strawUtilizeDetail);
                        }
                    }
                }

                strawUtilizeDetailMapper.insertList(list3);
                //??????????????????--->??????????????????
                StoredProcedure storedProcedure;
                for (StrawUtilizeDetail proStillDetail : list3) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(strawUtilize.getAreaId());
                    storedProcedure.setYearData(strawUtilize.getYear());
                    storedProcedureMapper.insert(storedProcedure);
                }
                LogUtil.addLog(LogEnum.LOG_TYPE_ADD.getCode(), "??????-" + strawUtilizeVo.getYear() + "-<??????????????????????????????>");
                return "???????????????";
            } else {
                StrawUtilize strawUtilize = new StrawUtilize();
                strawUtilize.setId(strawUtilizeVo.getStrawUtilizeId());
                strawUtilize.setAddress(strawUtilizeVo.getAddress());
                strawUtilize.setReportArea(strawUtilizeVo.getDepartment());
                strawUtilize.setMainName(strawUtilizeVo.getMainName());
                strawUtilize.setMainNo(strawUtilizeVo.getMainNo());
                strawUtilize.setCorporationName(strawUtilizeVo.getCorporationName());
                strawUtilize.setCompanyPhone(strawUtilizeVo.getCompanyPhone());
                strawUtilize.setMobilePhone(strawUtilizeVo.getMobilePhone());
                strawUtilizeMapper.updateStrawUtilize(strawUtilize);
                List<StrawUtilizeDetail> detailList = strawUtilizeVo.getStrawUtilizeDetailList();
                StrawUtilize strawUtilize1 = strawUtilizeMapper.selectById(strawUtilizeVo.getStrawUtilizeId());
                for (StrawUtilizeDetail detail : detailList) {
                    detail.setMarketEnt(detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial()));
                    setComprehensiveIndexValue(strawUtilize1, detail);
                }
                strawUtilizeDetailMapper.updateList(strawUtilizeVo.getStrawUtilizeDetailList());
                //??????????????????--->??????????????????
                StoredProcedure storedProcedure;
                for (StrawUtilizeDetail proStillDetail : strawUtilizeVo.getStrawUtilizeDetailList()) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(strawUtilize.getAreaId());
                    storedProcedure.setYearData(strawUtilize.getYear());
                    storedProcedureMapper.insert(storedProcedure);
                }
                LogUtil.addLog(LogEnum.LOG_TYPE_EDIT.getCode(), "??????-" + strawUtilizeVo.getYear() + "-<??????????????????????????????>");
                return "???????????????";
            }
        } else {
            throw new SofnException("?????????????????????????????????????????????????????????");
        }

    }

    //???????????????????????????????????????????????????????????????,????????????????????????
    //???????????????????????????????????????
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void isDisperseUtilizeMatch(HashSet set, List<StrawUtilizeDetail> list) {

        if (null == set || set.isEmpty()) {
            throw new SofnException("???????????????????????????????????????????????????!");
        }
        if (list.isEmpty()) {
            throw new SofnException("??????????????????!");
        }

        Map<String, String> detailMap = new HashMap<String, String>();
        BigDecimal countOther = new BigDecimal(0);
        for (StrawUtilizeDetail detail : list) {
            detail.setBase(detail.getBase() == null ? BigDecimal.ZERO : detail.getBase());
            detail.setFertilising(detail.getFertilising() == null ? BigDecimal.ZERO : detail.getFertilising());
            detail.setForage(detail.getForage() == null ? BigDecimal.ZERO : detail.getForage());
            detail.setFuel(detail.getFuel() == null ? BigDecimal.ZERO : detail.getFuel());
            detail.setMaterial(detail.getMaterial() == null ? BigDecimal.ZERO : detail.getMaterial());
            detail.setOther(detail.getOther() == null ? BigDecimal.ZERO : detail.getOther());
            BigDecimal count = new BigDecimal(0).add(detail.getBase())
                    .add(detail.getFertilising())
                    .add(detail.getForage())
                    .add(detail.getFuel())
                    .add(detail.getMaterial());

            countOther = countOther.add(detail.getOther());   //????????????????????????
            if (count.compareTo(detail.getOther()) == -1) {//????????????????????????????????????
                throw new SofnException("???????????????????????????????????????????????????!");
            }
            //???????????????????????????
            if (new BigDecimal(0).compareTo(count) < 0) {
                if (count.compareTo(detail.getOther()) == 0) {//??????????????????????????????????????????
                    set.add(detail.getStrawType());
                }
                detailMap.put(detail.getStrawType(), detail.getStrawName());
            }
        }

        //??????????????????
        if (new BigDecimal(0).compareTo(countOther) >= 0 && detailMap.isEmpty()) {
            throw new SofnException("????????????????????????0!");
        }

        //???????????????????????????????????????
        Set<String> detailSet = detailMap.keySet();
        for (String key : detailSet) {
            if (set.contains(key))     //????????????????????????
                continue;

            throw new SofnException("???" + detailMap.get(key) + "???????????????????????????????????????");
        }
    }

    /**
     * ???????????????????????????????????????????????????
     */
    private void checkYieldPerMuAndPercentageOfWuliao(List<DisperseUtilizeDetail> list) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", true);
        if (null == list || list.isEmpty()) {
            throw new SofnException("??????????????????!");
        }

        for (DisperseUtilizeDetail detail : list) {
            //?????????0????????????????????????
            if (CropsEnum.EARLY_RICE.getMin().compareTo(detail.getYieldPerMu()) == 0) {
                continue;
            }

            BigDecimal count = detail.getFertilising()
                    .add(detail.getForage())
                    .add(detail.getFuel())
                    .add(detail.getMaterial())
                    .add(detail.getBase());

            if (new BigDecimal(100).compareTo(count) == -1) {
                throw new SofnException("???????????????????????????100!");
            }

            //????????????????????????
            resultMap = CropsEnum.checkValue(resultMap, detail.getYieldPerMu(), detail.getStrawType(), detail.getStrawName());
            if (resultMap != null && resultMap.get("strId") != null) {
                break;
            }
        }

        if (resultMap.containsKey("strId")) {
            resultMap.put("success", false);
            if (resultMap.containsKey("maxthreshold"))
                throw new SofnException("???" + resultMap.get("strId") + "?????????????????????????????????????????????" + resultMap.get("maxthreshold"));
            else if (resultMap.containsKey("threshold"))
                throw new SofnException("???" + resultMap.get("strId") + "?????????????????????????????????" + resultMap.get("threshold"));
            else if (resultMap.containsKey("maxpercentage"))
                throw new SofnException("???" + resultMap.get("strId") + "???" + resultMap.get("maxpercentage"));
        }
    }

    /**
     * ????????????
     *
     * @param areaId
     * @return
     */
    protected String createFillNumber(String areaId) {
        long currentTime = System.currentTimeMillis();
        String fillNumber = areaId + currentTime + (int) (Math.random() * 10) + "";
        return fillNumber;
    }

    /**
     * ???????????????????????????????????????
     *
     * @param factFarmerCount
     * @param factMarketCount
     * @param
     * @throws Exception
     */
    protected void updateCountryTask(Integer factFarmerCount,
                                     Integer factMarketCount, CountryTask task) {
		/*task.setFactNum(task.getFactNum()+factFarmerCount);
		task.setMainNum(task.getMainNum()+factMarketCount);
		countryTaskDao.updateTaskById(task);*/
        //2019.4.8 ????????? ?????????????????????????????????????????????????????????????????????????????????*/
        countryTaskMapper.updateDynamicNumById(task.getId(), factFarmerCount, factMarketCount);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteStrawUtilizeById(String strawUtilizeId) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        StrawUtilize strawUtilize = strawUtilizeMapper.selectStrawUtilizeById(strawUtilizeId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", strawUtilize.getYear());
        params.put("areaId", strawUtilize.getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("???????????????????????????????????????????????????");
            } else {
                updateCountryTask(0, -1, task);
                strawUtilizeMapper.deleteById(strawUtilizeId);
                strawUtilizeDetailMapper.deleteByStrawUtilizeId(strawUtilizeId);
                LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "??????-" + strawUtilize.getYear() + "-<??????????????????????????????>");
            }
        } else {
            throw new SofnException("?????????????????????????????????????????????????????????");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteByUtilizeIds(List<String> utilizeIds) {
        List<StrawUtilize> strawUtilizeList = strawUtilizeMapper.selectBatchIds(utilizeIds);
        if (CollectionUtils.isEmpty(strawUtilizeList)) {
            throw new SofnException("????????????????????????,??????????????????");
        }
        // ??????????????????????????????
        Set<String> years = strawUtilizeList.stream().map(StrawUtilize::getYear).collect(Collectors.toSet());
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("areaId", strawUtilizeList.get(0).getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == years.size()) {
            for (CountryTask task : tasks) {
                if (Constants.ExamineState.REPORTED.equals(task.getStatus())
                        || Constants.ExamineState.READ.equals(task.getStatus())
                        || Constants.ExamineState.PASSED.equals(task.getStatus())) {
                    throw new SofnException("???????????????????????????????????????????????????");
                } else {
                    updateCountryTask(0, -1, task);
                }
            }
            // ????????????
            strawUtilizeMapper.deleteBatchIds(utilizeIds);
            deleteByUtilizeIds(utilizeIds);
            LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "????????????-" + StringUtils.join(years, ",") + "-<??????????????????????????????>");
        } else {
            throw new SofnException("?????????????????????????????????????????????????????????");
        }
    }

    @Override
    public void getStrawUtilizeExcelList(Map<String, Object> params, HttpServletResponse response) throws IOException {
        List<StrawUtilizeCombinVo> voList = strawUtilizeDetailMapper.selectCombinVoByCondition(params);
        List<StrawUtilizeExcel> excels = new ArrayList<>(voList.size());
        if (CollectionUtils.isNotEmpty(voList)) {
            // ????????????
            Set<String> areaIds = Sets.newHashSet();
            for (StrawUtilizeCombinVo detail : voList) {
                areaIds.add(detail.getAreaId().toString());
                areaIds.add(detail.getCityId().toString());
                areaIds.add(detail.getProvinceId().toString());
            }
            List<String> years = (List<String>) params.get("years");
            Map<String, String> areaMap = SysRegionUtil.getRegionNameMapsByCodes(new ArrayList<>(areaIds), CollectionUtils.isNotEmpty(years) ? years.get(years.size() - 1) : null);
            // ??????
            List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
            // Map<String, List<ProStillDetail>> detailMap = detailList.stream().collect(Collectors.groupingBy(ProStillDetail::getYearAndAreaId));
            LinkedHashMap<String, List<StrawUtilizeCombinVo>> detailMap = new LinkedHashMap<>();
            for (StrawUtilizeCombinVo utilizeDetail : voList) {
                List<StrawUtilizeCombinVo> utilizeDetails = detailMap.get(utilizeDetail.getUtilizeId());
                if (CollectionUtils.isEmpty(utilizeDetails)) {
                    List<StrawUtilizeCombinVo> firstUtilizeList = Lists.newArrayList(utilizeDetail);
                    detailMap.put(utilizeDetail.getUtilizeId(), firstUtilizeList);
                } else {
                    utilizeDetails.add(utilizeDetail);
                }
            }

            for (Map.Entry<String, List<StrawUtilizeCombinVo>> entry : detailMap.entrySet()) {
                for (SysDictionary dict : dictList) {
                    String dictValue = dict.getDictValue();
                    for (StrawUtilizeCombinVo detail : entry.getValue()) {
                        BigDecimal decimal = detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial());
                        if (dictValue.equals(detail.getStrawName()) && decimal.compareTo(BigDecimal.ZERO) != 0) {
                            StrawUtilizeExcel exportExcel = new StrawUtilizeExcel();
                            BeanUtils.copyProperties(detail, exportExcel);
                            String areaName = SysRegionUtil.getAreaName(areaMap, detail.getProvinceId().toString(), detail.getCityId().toString(), detail.getAreaId().toString());
                            exportExcel.setAreaName(areaName);
                            exportExcel.setOwnCountry(decimal.subtract(detail.getOther()));
                            excels.add(exportExcel);
                        }
                    }
                }
            }
        }
        //???????????????????????????
        ClassPathResource resource = new ClassPathResource("static/????????????????????????????????????.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, 1);
        Workbook workbook = exportDetailUtil.getWorkbook();
        String sheetName = "????????????????????????????????????";
        workbook.setSheetName(0, sheetName);
        exportDetailUtil.setSheet(workbook.getSheetAt(0));
        Sheet sheet = exportDetailUtil.getSheet();
        int size = excels.size();
        for (int i = 0; i < size; i++) {
            replaceCellValue(sheet, i + 2, 0, excels.get(i).getMainNo());
            replaceCellValue(sheet, i + 2, 1, excels.get(i).getYear());
            replaceCellValue(sheet, i + 2, 2, excels.get(i).getAreaName());
            replaceCellValue(sheet, i + 2, 3, excels.get(i).getMainName());
            replaceCellValue(sheet, i + 2, 4, excels.get(i).getCorporationName());
            replaceCellValue(sheet, i + 2, 5, excels.get(i).getMobilePhone());
            replaceCellValue(sheet, i + 2, 6, excels.get(i).getAddress());
            replaceCellValue(sheet, i + 2, 7, excels.get(i).getStrawName());
            replaceCellValue(sheet, i + 2, 8, excels.get(i).getFertilising());
            replaceCellValue(sheet, i + 2, 9, excels.get(i).getForage());
            replaceCellValue(sheet, i + 2, 10, excels.get(i).getFuel());
            replaceCellValue(sheet, i + 2, 11, excels.get(i).getBase());
            replaceCellValue(sheet, i + 2, 12, excels.get(i).getMaterial());
            replaceCellValue(sheet, i + 2, 13, excels.get(i).getOwnCountry());
            replaceCellValue(sheet, i + 2, 14, excels.get(i).getOther());
        }

        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        //swagger?????????????????????????????????swagger???????????????;filename*=utf-8''??????????????????postman????????????????????????
        response.setHeader("Content-Disposition", "attachment;filename=" + "????????????????????????????????????.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("????????????????????????????????????.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    public void replaceCellValue(Cell cell, Object value) {
        String val = value != null ? String.valueOf(value) : "";
        cell.setCellValue(val);
    }

    /**
     * ?????????????????????????????????
     *
     * @param rowNum ??????
     * @param colNum ??????
     * @param value  ???
     */
    public void replaceCellValue(Sheet sheet, int rowNum, int colNum, Object value) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        replaceCellValue(cell, value);
    }

    /**
     * ????????????????????????
     *
     * @param detail
     */
    private void setComprehensiveIndexValue(StrawUtilize strawUtilize, StrawUtilizeDetail detail) {
        //?????????????????????=??????????????????'?????????'+ '?????????'+ '?????????'+'?????????'+ '?????????'???
        detail.setMarketEnt(detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial()));
    }


    @Override
    public int getCountByCondition(String year, String provinceId, int marketEnd) {
        if (StringUtils.isEmpty(year) || StringUtils.isEmpty(provinceId)) {
            return 0;
        }
        return strawUtilizeDetailMapper.getCountByCondition(year, provinceId, marketEnd);
    }

    @Override
    public void deleteByUtilizeIds(List<String> utilizeIds) {
        UpdateWrapper<StrawUtilizeDetail> uw = new UpdateWrapper<>();
        uw.lambda().in(StrawUtilizeDetail::getUtilizeId, utilizeIds);
        remove(uw);
    }

    @Override
    public List<StrawUtilize> getCountByConditionV2(String year, String provinceId) {
        if (StringUtils.isEmpty(year) || StringUtils.isEmpty(provinceId)) {
            return Lists.newArrayList();
        }
        return baseMapper.getCountByConditionV2(year, provinceId);
    }
}
