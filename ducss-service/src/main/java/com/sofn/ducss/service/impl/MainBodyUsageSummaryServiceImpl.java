package com.sofn.ducss.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.enums.RegionEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.CheckInfoMapper;
import com.sofn.ducss.mapper.MainBodyUsageSummaryMapper;
import com.sofn.ducss.service.MainBodyUsageSummaryService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.AggregateSearchStatusUtil;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.util.SysOrgUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.MainBodyUsageSummaryVo;
import com.sofn.ducss.vo.StrawUtilizeInfoAndDetailInfoVo;
import com.sofn.ducss.vo.excelVo.MainBodyUsageSummaryExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MainBodyUsageSummaryServiceImpl implements MainBodyUsageSummaryService {

    @Autowired
    private MainBodyUsageSummaryMapper mainBodyUsageSummaryMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    /**
     * ????????????
     *
     * @param year   ??????????????????
     * @param areaId ??????????????????
     */
    private void checkParam(String year, String areaId) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("??????????????????");
        }

        if (StringUtils.isBlank(areaId)) {
            throw new SofnException("????????????????????????");
        }
    }

    @Override
    public List<MainBodyUsageSummaryVo> getList(String level, String year, String areaId, String orderBy, String isDesc) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("??????????????????");
        }
        if (StringUtils.isBlank(areaId)) {
            areaId = SysRegionUtil.getRegLastCodeStr();
            if (StringUtils.isBlank(areaId)) {
                throw new SofnException("??????ID????????????");
            }
        }

        syncSysRegionService.checkUserCanShow(Lists.newArrayList(areaId), year);

        String regionYear = SysRegionUtil.getYearByYear(year);
        // String regionYear = syncSysRegionService.getYearByYear(year);
        // ?????????????????????????????????
        List<String> parentIds = Lists.newArrayList();
        if (RegionEnum.VERB_CODES.getCode().contains(areaId)) {
            parentIds = SysRegionUtil.getChildrenRegionIdByYearList(areaId, regionYear);
            // parentIds = syncSysRegionService.getChildrenRegion(areaId, regionYear);
        } else {
            parentIds.add(areaId);
        }
        // select areaIds
        List<SysRegionTreeVo> areaTrees = Lists.newArrayList();
        for (String parentId : parentIds) {
            List<SysRegionTreeVo> childrenRegionList = SysRegionUtil.getChildrenRegionList(parentId, Integer.valueOf(regionYear));
            areaTrees.addAll(childrenRegionList);
        }
        Map<String, SysRegionTreeVo> regionMap = areaTrees.stream().collect(Collectors.toMap(SysRegionTreeVo::getRegionCode, Function.identity(), (key1, key2) -> key2));
        // ?????????????????? ?????????????????????
        List<String> statusList = AggregateSearchStatusUtil.getStatusList(areaId);
        List<MainBodyUsageSummaryVo> list = mainBodyUsageSummaryMapper.getList(year, new ArrayList<>(regionMap.keySet()), statusList, orderBy, isDesc);
        if (!CollectionUtils.isEmpty(list)) {
            for (MainBodyUsageSummaryVo vo : list) {
                if (regionMap.get(vo.getArea()) != null) {
                    vo.setAreaName(regionMap.get(vo.getArea()).getRegionName());
                }
            }
            List<String> areaId1 = syncSysRegionService.getAreaId(year, regionYear, parentIds, false);
            // String level1 = syncSysRegionService.getLevel(areaId, regionYear);
            String level1 = Objects.requireNonNull(SysRegionUtil.getRegionCodeByLastCode2(areaId, regionYear)).getRegionLevel();
            String columnName;
            if (RegionLevel.MINISTRY.getCode().equals(level1)) {
                columnName = "province_id";
            } else if (RegionLevel.PROVINCE.getCode().equals(level1)) {
                if (RegionEnum.VERB_CODES.getCode().contains(areaId)) {
                    columnName = "area_id";
                } else {
                    columnName = "city_id";
                }
            } else if (RegionLevel.CITY.getCode().equals(level1)) {
                columnName = "area_id";
            } else {
                throw new SofnException("????????????????????????????????????");
            }
            if (CollectionUtils.isEmpty(areaId1)) {
                throw new SofnException("???????????????????????????");
            }
            List<Map<String, Object>> mainBody = checkInfoMapper.getMainBody(columnName, year, areaId1);
            if (!CollectionUtils.isEmpty(mainBody)) {
                Map<String, String> mainBodyNumMap = Maps.newHashMap();
                mainBody.forEach(item -> {
                    String areaid = item.get("areaid").toString();
                    mainBodyNumMap.put(areaid, item.get("countnum") == null ? "0" : item.get("countnum").toString());
                });
                list.forEach(item -> {
                    String value = mainBodyNumMap.get(item.getArea());
                    if (StringUtils.isBlank(value)) {
                        item.setMainBodyNameOrCount("0");
                    } else {
                        item.setMainBodyNameOrCount(value);
                    }
                });
            }
        }
        // ????????????
        MainBodyUsageSummaryVo paramAddValue = getParamAddValue(list, year);
        paramAddValue.setArea(areaId);
        paramAddValue.setYear(year);
        // ????????????
        if (RegionEnum.ROOT_CODE.getCode().equals(areaId)) {
            paramAddValue.setAreaName("??????");
        } else {
            // String name = syncSysRegionService.getName(areaId, regionYear);
            String name = SysRegionUtil.getRegionNameByRegionCode(areaId, regionYear);
            paramAddValue.setAreaName(name);
        }
        list.add(0, paramAddValue);
        return list;
    }

    @Override
    public PageUtils<List<MainBodyUsageSummaryVo>> getListByCounty(String year, String areaId, String orderBy, String isDesc, Integer pageNo, Integer pageSize) {
        if (pageSize <= 0) {
            throw new SofnException("?????????????????????");
        }
        checkParam(year, areaId);
        List<String> list3 = syncSysRegionService.checkUserCanShow2(Lists.newArrayList(areaId), year);

        // ??????????????????
        String regionYear = SysRegionUtil.getYearByYear(year);
        // String regionYear = syncSysRegionService.getYearByYear(year);
        PageHelper.offsetPage(pageNo, pageSize);
        if (list3 != null) {
            if (!CollectionUtils.isEmpty(list3)) {
                PageInfo<MainBodyUsageSummaryVo> pageInfo = new PageInfo<>();
                return PageUtils.getPageUtils(pageInfo);
            }
        }
        List<MainBodyUsageSummaryVo> listByCounty = mainBodyUsageSummaryMapper.getListByCounty2(year, regionYear, areaId, orderBy, isDesc);
        PageInfo<MainBodyUsageSummaryVo> pageInfo = new PageInfo<>(listByCounty);
        // ??????area???????????????
        List<MainBodyUsageSummaryVo> list = pageInfo.getList();
        if (!CollectionUtils.isEmpty(list)) {
//            Set<String> collect = list.stream().map(MainBodyUsageSummaryVo::getArea).collect(Collectors.toSet());
//            Map<String, String> regionNameMapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(Lists.newArrayList(collect)), year);
//            if (CollectionUtils.isEmpty(regionNameMapsByCodes)) {
//                regionNameMapsByCodes = Maps.newHashMap();
//            }
//
//            Map<String, String> finalRegionNameMapsByCodes = regionNameMapsByCodes;
//            list.forEach(item -> {
//                String area = item.getArea();
//                String areaName = finalRegionNameMapsByCodes.get(area);
//                item.setAreaName(areaName);
//            });
        }
        transferList(list);
        pageInfo.setList(list);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<MainBodyUsageSummaryVo> getListByCityCount(String year, String areaId) {
        // ?????????????????????????????????
        checkParam(year, areaId);

        List<MainBodyUsageSummaryVo> listByCityCount = mainBodyUsageSummaryMapper.getListByCityCount(year, areaId);


        if (!CollectionUtils.isEmpty(listByCityCount)) {
            MainBodyUsageSummaryVo mainBodyUsageSummaryVo = getParamAddValue(listByCityCount, year);
            String areaName = SysRegionUtil.getReginNameByRegionCodes(areaId, year);
            mainBodyUsageSummaryVo.setAreaName(areaName);
            mainBodyUsageSummaryVo.setArea(areaId);
            mainBodyUsageSummaryVo.setYear(year);

            listByCityCount.add(0, mainBodyUsageSummaryVo);
        }
        return listByCityCount;
    }


    @Override
    public List<MainBodyUsageSummaryVo> getListByProvinceCount(String year, String areaId) {
        // ????????????????????????????????????
        checkParam(year, areaId);
        // ???????????????????????????      ?????????????????????????????????
        boolean isVerb = RegionEnum.VERB_CODES.getCode().contains(areaId);
        List<MainBodyUsageSummaryVo> listByProvinceCount;
        if (isVerb) {
            listByProvinceCount = mainBodyUsageSummaryMapper.getListByVerb(year, areaId);
        } else {
            listByProvinceCount = mainBodyUsageSummaryMapper.getListByProvinceCount(year, areaId);
        }

        if (!CollectionUtils.isEmpty(listByProvinceCount)) {
            MainBodyUsageSummaryVo paramAddValue = getParamAddValue(listByProvinceCount, year);
            String areaName = SysRegionUtil.getReginNameByRegionCodes(areaId, year);
            paramAddValue.setAreaName(areaName);
            paramAddValue.setArea(areaId);
            paramAddValue.setYear(year);
            listByProvinceCount.add(0, paramAddValue);
        }
        return listByProvinceCount;
    }

    @Override
    public List<MainBodyUsageSummaryVo> getListByChinaCount(String year) {
        // ?????????????????????????????????
        if (StringUtils.isBlank(year)) {
            throw new SofnException("????????????");
        }
        List<MainBodyUsageSummaryVo> listByChinaCount = mainBodyUsageSummaryMapper.getListByChinaCount(year);
        if (!CollectionUtils.isEmpty(listByChinaCount)) {
            MainBodyUsageSummaryVo paramAddValue = getParamAddValue(listByChinaCount, year);
            String areaName = "??????";
            paramAddValue.setAreaName(areaName);
            paramAddValue.setArea("100000");
            paramAddValue.setYear(year);
            listByChinaCount.add(0, paramAddValue);
        }
        return listByChinaCount;
    }

    @Override
    public List<MainBodyUsageSummaryVo> getListByLevel(String level, String year, String areaId) {
        // 2 ??????  3 ??????   4 ??????  ??????????????????????????????
        List<MainBodyUsageSummaryVo> mainBodyUsageSummaryVos = Lists.newArrayList();
        if ("2".equals(level)) {
            mainBodyUsageSummaryVos = this.getListByCityCount(year, areaId);
        } else if ("3".equals(level)) {
            mainBodyUsageSummaryVos = this.getListByProvinceCount(year, areaId);
        } else if ("4".equals(level)) {
            mainBodyUsageSummaryVos = this.getListByChinaCount(year);
        }
        return mainBodyUsageSummaryVos;
    }

    @Override
    public StrawUtilizeInfoAndDetailInfoVo getStrawUtilizeInfoAndDetailInfo(String utilizeId) {
        if (StringUtils.isBlank(utilizeId)) {
            throw new SofnException("??????ID????????????");
        }
        return mainBodyUsageSummaryMapper.getStrawUtilizeInfoAndDetailInfo(utilizeId);
    }

    @Override
    public void exportMainBodyUsageInfo(String year, String areaId, HttpServletResponse response) {
        // ?????????????????????
        // ?????????????????????
        // ?????????????????????
        // ?????????????????????
        syncSysRegionService.checkUserCanShow(Lists.newArrayList(areaId), year);
        SysOrganization sysOrganization = SysOrgUtil.getSysOrgInfo();
        String userLoginRegionCode = sysOrganization.getRegionLastCode();
        if (StringUtils.isBlank(userLoginRegionCode)) {
            throw new SofnException("?????????????????????????????????????????????????????????");
        }
        String regionYear = SysRegionUtil.getYearByYear(year);
        // String regionYear = syncSysRegionService.getYearByYear(year);
        List<String> childrenRegion = syncSysRegionService.getAreaId(year, regionYear, Lists.newArrayList(areaId), false);
        // ?????????????????? ???????????????
        List<MainBodyUsageSummaryExcelVo> mainBodyUsageSummaryExcelVos;
        if (!CollectionUtils.isEmpty(childrenRegion)) {
            mainBodyUsageSummaryExcelVos = mainBodyUsageSummaryMapper.getExportInfo(year, regionYear, childrenRegion);
        } else {
            // String level = syncSysRegionService.getLevel(areaId, regionYear);
            String level = Objects.requireNonNull(SysRegionUtil.getRegionCodeByLastCode2(areaId, regionYear)).getRegionLevel();
            if (RegionLevel.COUNTY.getCode().equals(level)) {
                childrenRegion.add(areaId);
                mainBodyUsageSummaryExcelVos = mainBodyUsageSummaryMapper.getExportInfo(year, regionYear, childrenRegion);
                if (CollectionUtils.isEmpty(mainBodyUsageSummaryExcelVos)) {
                    log.warn("?????????????????????????????????????????????????????????????????????????????? ?????????areaId={}???year={}", areaId, year);
                    mainBodyUsageSummaryExcelVos = Lists.newArrayList();
                }
            } else {
                log.warn("?????????????????????????????????????????????????????????????????????????????? ?????????areaId={}???year={}", areaId, year);
                mainBodyUsageSummaryExcelVos = Lists.newArrayList();
            }
        }
        try {
            getExcel(mainBodyUsageSummaryExcelVos, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("??????????????????");
        }
    }

    /**
     * ??????BigDecimal??????double???  ????????????
     *
     * @param bigDecimal BigDecimal
     * @return double
     */
    private double getDoubleValueByBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            return 0.00;
        }
    }

    /**
     * ??????Excel??????
     * ?????????????????????common???????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param mainBodyUsageSummaryExcelVos ?????????????????????
     * @param response                     ????????????
     * @throws Exception Exception
     */
    private synchronized void getExcel(List<MainBodyUsageSummaryExcelVo> mainBodyUsageSummaryExcelVos, HttpServletResponse response) throws Exception {
        ClassPathResource resource = new ClassPathResource("/static/???????????????????????????????????????.xlsx");
        InputStream inputStream = resource.getInputStream();

        // ?????????????????????HSSFWorkbook???97-03?????????xls?????????XSSFWorkbook???07?????????xlsx
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        if (!CollectionUtils.isEmpty(mainBodyUsageSummaryExcelVos)) {
            log.info("???????????????:" + mainBodyUsageSummaryExcelVos.size());
            for (int i = 0; i < mainBodyUsageSummaryExcelVos.size(); i++) {
                MainBodyUsageSummaryExcelVo mainBodyUsageSummaryExcelVo = mainBodyUsageSummaryExcelVos.get(i);
                // ??????
                // ??????????????????
                Row row = sheet.createRow(i + 2);
                // ??????
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(i + 1);
                // ??????
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(mainBodyUsageSummaryExcelVo.getYear());
                // ??????
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(mainBodyUsageSummaryExcelVo.getArea());
                // ??????????????????
                Cell cell4 = row.createCell(3);
                cell4.setCellValue(mainBodyUsageSummaryExcelVo.getMainBodyName());
                // ??????
                Cell cell5 = row.createCell(4);
                cell5.setCellValue(mainBodyUsageSummaryExcelVo.getAddress());
                // ????????????
                Cell cell6 = row.createCell(5);
                cell6.setCellValue(mainBodyUsageSummaryExcelVo.getCorporationName());
                // ????????????
                Cell cell7 = row.createCell(6);
                cell7.setCellValue(mainBodyUsageSummaryExcelVo.getMobilePhone());
                // ?????????
                Cell cell8 = row.createCell(7);
                cell8.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getFertilising()));
                // ?????????
                Cell cell9 = row.createCell(8);
                cell9.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getForage()));
                // ?????????
                Cell cell10 = row.createCell(9);
                cell10.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getFuel()));
                // ?????????
                Cell cell11 = row.createCell(10);
                cell11.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getBase()));
                // ?????????
                Cell cell12 = row.createCell(11);
                cell12.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getMaterial()));

                // ???????????????
                BigDecimal total = mainBodyUsageSummaryExcelVo.getTotal();
                // ??????
                Cell cell13 = row.createCell(12);
                cell13.setCellValue(getDoubleValueByBigDecimal(total));
                // ????????????
                Cell cell14 = row.createCell(13);
                cell14.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getThisCount()));
                // ????????????
                Cell cell15 = row.createCell(14);
                cell15.setCellValue(getDoubleValueByBigDecimal(mainBodyUsageSummaryExcelVo.getOther()));
            }

        }
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        //swagger?????????????????????????????????swagger???????????????;filename*=utf-8''??????????????????postman????????????????????????
        response.setHeader("Content-Disposition", "attachment;filename=" + "???????????????????????????????????????.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("???????????????????????????????????????.xlsx", "utf-8"));

//        response.setHeader("Content-Disposition", "attachment;filename=" + "excel.xlsx" + ";filename*=utf-8''"
//                + URLEncoder.encode("excel.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        // TODO : ?????????????????????????????????
//        String excelFilePath = ExcelPropertiesUtils.getExcelFilePath();
//        String excelFilePath = "/Users/heyongjie/work/temp";
//        String filePath =  excelFilePath + File.separator + "???????????????????????????????????????.xlsx";
//        File file = new File(filePath);
//        if(file.exists()){
//            file.delete();
//        }
//        file.createNewFile();
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        workbook.write(fileOutputStream);
        workbook.write(os);
        inputStream.close();
//        fileOutputStream.flush();
//        fileOutputStream.close();
//        FileDownloadUtils.downloadFile(filePath, response);
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param listByCityCount ????????????????????????
     * @return MainBodyUsageSummaryVo
     */
    private MainBodyUsageSummaryVo getParamAddValue(List<MainBodyUsageSummaryVo> listByCityCount, String year) {
        MainBodyUsageSummaryVo mainBodyUsageSummaryVo = new MainBodyUsageSummaryVo();
        BigDecimal fertilising = new BigDecimal("0");
        BigDecimal forage = new BigDecimal("0");
        BigDecimal fuel = new BigDecimal("0");
        BigDecimal base = new BigDecimal("0");
        BigDecimal material = new BigDecimal("0");
        BigDecimal other = new BigDecimal("0");
        BigDecimal thisCount = new BigDecimal("0");
        BigDecimal mainBodyNameOrCount = new BigDecimal("0");
        if (!CollectionUtils.isEmpty(listByCityCount)) {
            for (MainBodyUsageSummaryVo item : listByCityCount) {
                if (!StringUtils.isBlank(item.getFertilising())) {
                    fertilising = fertilising.add(new BigDecimal(item.getFertilising()));
                }
                if (!StringUtils.isBlank(item.getForage())) {
                    forage = forage.add(new BigDecimal(item.getForage()));
                }
                if (StringUtils.isNotBlank(item.getFuel())) {
                    fuel = fuel.add(new BigDecimal(item.getFuel()));
                }
                if (StringUtils.isNotBlank(item.getBase())) {
                    base = base.add(new BigDecimal(item.getBase()));
                }
                if (StringUtils.isNotBlank(item.getMaterial())) {
                    material = material.add(new BigDecimal(item.getMaterial()));
                }
                if (StringUtils.isNotBlank(item.getOther())) {
                    other = other.add(new BigDecimal(item.getOther()));
                }
                if (StringUtils.isNotBlank(item.getThisCount())) {
                    thisCount = thisCount.add(new BigDecimal(item.getThisCount()));
                }
                BigDecimal count = BigDecimalUtil.valueIsNull(new BigDecimal(item.getOther())).add(BigDecimalUtil.valueIsNull(new BigDecimal(item.getThisCount())));
                item.setCount(count.toString());
                // ???????????????????????????    ??????????????????????????????
                try {
                    if (StringUtils.isNotBlank(item.getMainBodyNameOrCount())) {
                        mainBodyNameOrCount = mainBodyNameOrCount.add(new BigDecimal(item.getMainBodyNameOrCount()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mainBodyUsageSummaryVo.setFertilising(fertilising.toString());
        mainBodyUsageSummaryVo.setForage(forage.toString());
        mainBodyUsageSummaryVo.setFuel(fuel.toString());
        mainBodyUsageSummaryVo.setBase(base.toString());
        mainBodyUsageSummaryVo.setMaterial(material.toString());
        mainBodyUsageSummaryVo.setOther(other.toString());
        mainBodyUsageSummaryVo.setThisCount(thisCount.toString());
        mainBodyUsageSummaryVo.setMainBodyNameOrCount(mainBodyNameOrCount.toString());
        mainBodyUsageSummaryVo.setCount(other.add(thisCount).toString());

        return mainBodyUsageSummaryVo;
    }

    private void transferList(List<MainBodyUsageSummaryVo> list) {
        for (MainBodyUsageSummaryVo sp : list) {
            sp.setFertilising(transferField(sp.getFertilising(), 2));
            sp.setForage(transferField(sp.getForage(), 2));
            sp.setFuel(transferField(sp.getFuel(), 2));
            sp.setBase(transferField(sp.getBase(), 2));
            sp.setMaterial(transferField(sp.getMaterial(), 2));
            sp.setCount(transferField(sp.getCount(), 2));
            sp.setThisCount(transferField(sp.getThisCount(), 2));
            sp.setOther(transferField(sp.getOther(), 2));
        }
    }

    private String transferField(String field, int length) {
        BigDecimal result = new BigDecimal(field);
        return result.setScale(length, RoundingMode.HALF_UP).toPlainString();
    }
}
