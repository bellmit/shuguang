package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.ducss.mapper.DataAnalysisAreaMapper;
import com.sofn.ducss.mapper.DataAnalysisCityMapper;
import com.sofn.ducss.model.DataAnalysisArea;
import com.sofn.ducss.model.DataAnalysisCity;
import com.sofn.ducss.service.DataAnalysisCityService;
import com.sofn.ducss.util.ListUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.enums.AnalyIndexEnum;
import com.sofn.ducss.mapper.DataAnalysisProviceMapper;
import com.sofn.ducss.mapper.SysDictionaryMapper;
import com.sofn.ducss.model.DataAnalysisProvice;
import com.sofn.ducss.model.SysDictionary;
import com.sofn.ducss.service.DataAnalysisService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysRegionForm;
import com.sofn.ducss.util.ExcelDataAnalyUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.DataAnalyVo;
import com.sofn.ducss.vo.DataKingDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/***
 * 分析数据展示列表(新)
 * @author xl
 * @date 2021/06/07 15:03
 */
@Service
@Slf4j
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Autowired
    SysApi sysApi;

    @Autowired
    private DataAnalysisProviceMapper dataAnalysisProviceMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Autowired
    private DataAnalysisCityMapper dataAnalysisCityMapper;

    @Autowired
    private DataAnalysisAreaMapper dataAnalysisAreaMapper;

    @Override
    public Result<PageUtils<DataKingDto>> getDataList(HashMap<String, String> paramMap) {
        List<DataKingDto> dataKingDtoList = null;
        if (paramMap != null) {
            dataKingDtoList = new ArrayList<>();
            List<String> yearList = getYearList(paramMap); // 年份
            List<String> dictTypeList = getDictTypeList(paramMap.get("cropType")); // 作物类型
            List<String> titleList = getTitleList(paramMap); // 指标内容
            String reginCodes = paramMap.get("area");
            // 区域查询四种格式,查询区域分页列表
            if ("2".contains(paramMap.get("allCropType"))) { // 自定义
                HashMap<String, String> requstMap = new HashMap<>();
                requstMap.put("pageNo", paramMap.get("pageNum"));
                requstMap.put("pageSize", paramMap.get("pageSize"));
                requstMap.put("regionCode", reginCodes);
                requstMap.put("parentId", null);
                requstMap.put("versionYear", null);
                requstMap.put("versionCode", null);
                Result<PageUtils<SysRegionForm>> result = sysApi.getSysRegionByContionsPage(requstMap);
                if (result != null && result.getCode() == 200) {
                    List<SysRegionForm> sysRegionFormList = (List<SysRegionForm>) result.getData().getList();
                    if (!ListUtils.isEmpty(sysRegionFormList)) { // 数据
                        DataKingDto dataKingDto = null;
                        for (SysRegionForm item : sysRegionFormList) {
                            dataKingDto = new DataKingDto();
                            dataKingDto.setId(item.getId());
                            String areName = "";
                            if (!item.getRegionCode().equals("100000")) {
                                areName = ListUtils.splitToArrayString(item.getAreaName(), item.getRegionName());
                            } else {
                                areName = item.getAreaName();
                            }
                            dataKingDto.setAreaName(areName);
                            dataKingDto.setRegionCode(item.getRegionCode());
                            dataKingDtoList.add(dataKingDto);
                        }
                    }
                    Map<String, Map<String, Object>> map = null;
                    Map<String, Object> s = null;
                    Map<String, Object> dataList = null;
                    // 装载数据
                    for (int i = 0; i < dataKingDtoList.size(); i++) { // 根据年份, 表头 挂载表头和年份
                        List<DataAnalyVo> mapLists = dataAnalysisProviceMapper.getProviceDataAllSumss(yearList, dataKingDtoList.get(i).getRegionCode());
                        map = new LinkedHashMap<>();
                        for (int j = 0; j < yearList.size(); j++) {
                            s = new LinkedHashMap<>();
                            Map<String, Map<String, List<DataAnalyVo>>> stringListMap = mapLists.stream().collect(Collectors.groupingBy(DataAnalyVo::getYear, Collectors.groupingBy(DataAnalyVo::getStrawType)));
                            for (int k = 0; k < titleList.size(); k++) {
                                dataList = new LinkedHashMap<>();
                                for (int l = 0; l < dictTypeList.size(); l++) {
                                    if (dataKingDtoList.get(i).getRegionCode().equals("100000")) { // 全国
                                        dataKingDtoList.get(i).setAreaName("全国");
                                        if (dictTypeList.get(l).equals("all_type")) { // 全部作物
                                            if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                Map<String, String> datasMap = dataAnalysisProviceMapper.getProviceDataAllSumSpecial(yearList.get(j), null);
                                                mathAnalyAllProvice(titleList.get(k), datasMap, dataList, dictTypeList.get(l));
                                            } else { // 普通指标
                                                String num = dataAnalysisProviceMapper.getProviceDataAllSum(yearList.get(j), AnalyIndexEnum.getSqlValue(titleList.get(k)), null);
                                                dataList.put(dictTypeList.get(l), formatNum(num));
                                            }
                                        } else {
                                            // 普通作物（已经排好序列,单个取值后面可以不用遍历排序）
                                            if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                Map<String, String> datasMap = dataAnalysisProviceMapper.getProviceDataAllSumSpecial(yearList.get(j), dictTypeList.get(l));
                                                mathAnalyAllProvice(titleList.get(k), datasMap, dataList, dictTypeList.get(l));
                                            } else { // 普通指标
                                                String num = dataAnalysisProviceMapper.getProviceDataAllSum(yearList.get(j), AnalyIndexEnum.getSqlValue(titleList.get(k)), dictTypeList.get(l));
                                                dataList.put(dictTypeList.get(l), formatNum(num));
                                            }
                                        }
                                    } else {
                                        if (dictTypeList.get(l).equals("all_type")) { // 统计所有作物
                                            if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                mathAnalysisAllNum(titleList.get(k), dictTypeList.get(l), dataList, yearList.get(j), stringListMap);
                                            } else { // 普通指标
                                                String num = statisticsAllType(stringListMap, yearList.get(j), titleList.get(k));
                                                dataList.put(dictTypeList.get(l), num);
                                            }
                                        } else { // 普通作物
                                            if (stringListMap != null) {
                                                List<DataAnalyVo> data = null;
                                                if (stringListMap.get(yearList.get(j)) != null && stringListMap.get(yearList.get(j)).get(dictTypeList.get(l)) != null) {
                                                    data = stringListMap.get(yearList.get(j)).get(dictTypeList.get(l));
                                                }
                                                if (data != null && data.size() > 0) {
                                                    DataAnalyVo dataAnalysisCity = data.get(0);
                                                    try {
                                                        Map<String, Object> map1 = Obj2Map(dataAnalysisCity);
                                                        /*if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                            mathAnalysisNum(titleList.get(k), dictTypeList.get(l), dataList, map1);
                                                        } else {
                                                        }*/
                                                        // 普通指标
                                                        Object ss = map1.get(titleList.get(k));
                                                        String num = "";
                                                        if (ss != null) {
                                                            num = ss.toString();
                                                        }
                                                        dataList.put(dictTypeList.get(l), formatNum(num));
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    if (titleList.get(k).equals("totolRate")) {
                                                        dataList.put(dictTypeList.get(l), "0.00%");
                                                    } else {
                                                        dataList.put(dictTypeList.get(l), "0.00");
                                                    }
                                                }
                                            } else {
                                                if (titleList.get(k).equals("totolRate")) {
                                                    dataList.put(dictTypeList.get(l), "0.00%");
                                                } else {
                                                    dataList.put(dictTypeList.get(l), "0.00");
                                                }
                                            }
                                        }
                                    }
                                }
                                s.put(titleList.get(k), dataList);
                            }
                            map.put(yearList.get(j), s);
                        }
                        dataKingDtoList.get(i).setIndicatorArrays(map);
                    }

                    PageUtils pageUtils = new PageUtils();
                    pageUtils.setList(dataKingDtoList);
                    pageUtils.setCurrPage(result.getData().getCurrPage());
                    pageUtils.setTotalPage(result.getData().getTotalPage());
                    pageUtils.setPageSize(result.getData().getPageSize());
                    pageUtils.setTotalCount(result.getData().getTotalCount());
                    return Result.ok(pageUtils);
                }
            }
            if ("1".equals(paramMap.get("allCropType")) || "3".equals(paramMap.get("allCropType"))) { // 六大区
                //List<String> regionCodeList = ListUtils.springStringToList(reginCodes);
                dataKingDtoList = getSixDataKing(dataKingDtoList, paramMap.get("allCropType"), reginCodes);
                // 挂载数据
                Map<String, Map<String, Object>> map = null;
                Map<String, Object> s = null;
                Map<String, Object> dataList = null;
                for (int i = 0; i < dataKingDtoList.size(); i++) { // 根据年份, 表头 挂载表头和年份
                    map = new LinkedHashMap<>();
                    s = new LinkedHashMap<>();
                    for (int j = 0; j < yearList.size(); j++) {

                        for (int k = 0; k < titleList.size(); k++) {
                            dataList = new LinkedHashMap<>();
                            for (int l = 0; l < dictTypeList.size(); l++) { // 统计数据
                                if (dictTypeList.get(l).equals("all_type")) { // 全部作物
                                    if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                        Map<String, String> datasMap = dataAnalysisProviceMapper.getSixProviceDataSumSpecial(yearList.get(j), ListUtils.springStringToList(dataKingDtoList.get(i).getRegionCode()), null);
                                        mathAnalyAllProvice(titleList.get(k), datasMap, dataList, dictTypeList.get(l));
                                    } else {
                                        String num = dataAnalysisProviceMapper.getSixProviceDataSum(yearList.get(j), ListUtils.springStringToList(dataKingDtoList.get(i).getRegionCode()), AnalyIndexEnum.getSqlValue(titleList.get(k)), null);
                                        dataList.put(dictTypeList.get(l), formatNum(num));
                                    }
                                } else { // 普通作物（已经排好序列,单个取值后面可以不用遍历排序）
                                    if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                        Map<String, String> datasMap = dataAnalysisProviceMapper.getSixProviceDataSumSpecial(yearList.get(j), ListUtils.springStringToList(dataKingDtoList.get(i).getRegionCode()), dictTypeList.get(l));
                                        mathAnalyAllProvice(titleList.get(k), datasMap, dataList, dictTypeList.get(l));
                                    } else {
                                        String num = dataAnalysisProviceMapper.getSixProviceDataSum(yearList.get(j), ListUtils.springStringToList(dataKingDtoList.get(i).getRegionCode()), AnalyIndexEnum.getSqlValue(titleList.get(k)), dictTypeList.get(l));
                                        dataList.put(dictTypeList.get(l), formatNum(num));
                                    }
                                }
                                s.put(titleList.get(k), dataList);
                            }
                            map.put(yearList.get(j), s);
                        }
                    }
                    dataKingDtoList.get(i).setIndicatorArrays(map);
                }
                PageUtils pageUtils = new PageUtils();
                pageUtils.setList(dataKingDtoList);
                pageUtils.setCurrPage(1);
                pageUtils.setTotalPage(1);
                pageUtils.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
                pageUtils.setTotalCount(1);
                return Result.ok(pageUtils);
            }
        }
        return null;
    }

    /***
     * 获取作物类型
     * @return
     */
    private List<String> getDictTypeList(String cropType) { // 获取查询的作物类型
        if (!cropType.contains(",")) {
            List<String> list = new ArrayList<>();
            list.add(cropType);
            return list;
        }
        List<String> croTypeList = ListUtils.springStringToList(cropType);
        if (!ListUtils.isEmpty(croTypeList)) {
            List<String> dictList = sysDictionaryMapper.getDictKeyListBy(croTypeList);
            croTypeList.forEach(item -> {
                if (item.equals("all_type")) {
                    dictList.add(0, "all_type");
                }
            });
            return dictList;
        }
        return null;
    }

    public Map<String, Object> Obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    private void mathAnalysisNum(String titleName, String strawName, Map<String, Object> dataList, Map<String, Object> map) {
        BigDecimal totol = new BigDecimal(formatNumObject(map.get("totol")));
        BigDecimal collectResource = new BigDecimal(formatNumObject(map.get("collectResource")));
        BigDecimal marketEnt = new BigDecimal(formatNumObject(map.get("marketEnt")));
        BigDecimal reuse = new BigDecimal(formatNumObject(map.get("reuse")));
        BigDecimal returnResource = new BigDecimal(formatNumObject(map.get("returnResource")));
        BigDecimal heJi = marketEnt.add(reuse).add(returnResource);
        if (titleName.equals("totolRate")) { // 综合利用率
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                dataList.put(strawName, formatPercentage(new BigDecimal(0)));
            } else {
                dataList.put(strawName, formatPercentage(totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }
        } else if (titleName.equals("comprUtilIndex")) { //综合利用能力指数
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                dataList.put(strawName, formatString(new BigDecimal(0)));
            } else {
                dataList.put(strawName, formatString(heJi.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        } else if (titleName.equals("induUtilIndex")) { // 产业化利用能力指数
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                dataList.put(strawName, formatString(new BigDecimal(0)));
            } else {
                dataList.put(strawName, formatString(marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }
    }

    private void mathAnalysisAllNum(String titleName, String strawName, Map<String, Object> dataList, String year, Map<String, Map<String, List<DataAnalyVo>>> stringListMap) {
        BigDecimal totol = new BigDecimal(statisticsAllType(stringListMap, year, "totol"));
        BigDecimal totolV2 = new BigDecimal(statisticsAllType(stringListMap, year, "totolV2"));
        BigDecimal collectResource = new BigDecimal(statisticsAllType(stringListMap, year, "collectResource"));
        BigDecimal collectResourceV2 = new BigDecimal(statisticsAllType(stringListMap, year, "collectResourceV2"));
        BigDecimal marketEnt = new BigDecimal(statisticsAllType(stringListMap, year, "marketEnt"));
        BigDecimal reuse = new BigDecimal(statisticsAllType(stringListMap, year, "reuse"));
        BigDecimal returnResource = new BigDecimal(statisticsAllType(stringListMap, year, "returnResource"));
        BigDecimal heJi = marketEnt.add(reuse).add(returnResource);
        if (titleName.equals("totolRate")) {
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                dataList.put(strawName, formatPercentage(new BigDecimal(0)));
            } else {
                dataList.put(strawName, formatPercentage(totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }
        } else if (titleName.equals("comprUtilIndex")) {
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                dataList.put(strawName, formatString(new BigDecimal(0)));
            } else {
                dataList.put(strawName, formatString(heJi.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        } else if (titleName.equals("induUtilIndex")) {
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                dataList.put(strawName, formatString(new BigDecimal(0)));
            } else {
                dataList.put(strawName, formatString(marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }
    }

    private void mathAnalyAllProvice(String titleName, Map<String, String> datasMap, Map<String, Object> map, String strawName) {
        BigDecimal totol = new BigDecimal(formatNumObject(datasMap.get("totol")));
        BigDecimal totolV2 = new BigDecimal(formatNumObject(datasMap.get("totolV2")));
        BigDecimal collectResource = new BigDecimal(formatNumObject(datasMap.get("collectResource")));
        BigDecimal collectResourceV2 = new BigDecimal(formatNumObject(datasMap.get("collectResourceV2")));
        BigDecimal marketEnt = new BigDecimal(formatNumObject(datasMap.get("marketEnt")));
        BigDecimal reuse = new BigDecimal(formatNumObject(datasMap.get("reuse")));
        BigDecimal returnResource = new BigDecimal(formatNumObject(datasMap.get("returnResource")));
        BigDecimal heJi = marketEnt.add(reuse).add(returnResource);
        if (titleName.equals("totolRate")) {
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                map.put(strawName, formatPercentage(new BigDecimal(0)));
            } else {
                map.put(strawName, formatPercentage(totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }
        } else if (titleName.equals("comprUtilIndex")) {
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                map.put(strawName, formatString(new BigDecimal(0)));
            } else {
                map.put(strawName, formatString(heJi.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        } else if (titleName.equals("induUtilIndex")) {
            if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                map.put(strawName, formatString(new BigDecimal(0)));
            } else {
                map.put(strawName, formatString(marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }
    }

    private List<String> getYearList(HashMap<String, String> paramMap) {
        List<String> yearList = null;
        if (paramMap.get("year").contains(",")) { // 取出年份
            yearList = ListUtils.springStringToList(paramMap.get("year"));
        } else {
            yearList = new ArrayList<>();
            yearList.add(paramMap.get("year"));
        }
        return yearList;
    }

    private List<String> getTitleList(HashMap<String, String> paramMap) {
        List<String> yearList = null;
        List<String> titleList = new ArrayList<>();
        if (paramMap.get("analysisIndex").contains(",")) { // 取出年份
            yearList = ListUtils.springStringToList(paramMap.get("analysisIndex"));
        } else {
            yearList = new ArrayList<>();
            yearList.add(paramMap.get("analysisIndex"));
        }
        yearList.forEach(item -> {
            String value = AnalyIndexEnum.getDataValue(item);
            titleList.add(value);
        });
        return titleList;
    }

    /***
     * 统计全部作物
     * @return
     */
    private String statisticsAllType(Map<String, Map<String, List<DataAnalyVo>>> stringListMap, String year, String title) {
        System.out.println(stringListMap);
        BigDecimal total = new BigDecimal("0.00");
        Map<String, List<DataAnalyVo>> userMap = stringListMap.get(year);
        DataAnalyVo dataAnalysisCity = null;
        if (userMap == null) {
            return "0.00";
        }
        for (String item : userMap.keySet()) {
            List<DataAnalyVo> list = userMap.get(item);
            dataAnalysisCity = new DataAnalyVo();
            if (list != null && list.size() > 0) {
                dataAnalysisCity = userMap.get(item).get(0);
            }
            try {
                Map<String, Object> map1 = Obj2Map(dataAnalysisCity);
                // 判断是否是特殊指标
                Object object = map1.get(title);
                if (object == null) {
                    object = new BigDecimal("0.00");
                }
                /*if ("totol".equals(title) || "collectResource".equals(title)) {
                    Object totol = map1.get("totol");
                    Object collectResource = map1.get("collectResource");
                    if (totol != null && collectResource != null) {
                        BigDecimal totalNum = new BigDecimal(totol.toString());
                        BigDecimal collectResourceNum = new BigDecimal(collectResource.toString());
                        if (totalNum.compareTo(BigDecimal.ZERO) > 0
                                && collectResourceNum.compareTo(BigDecimal.ZERO) > 0) {
                            total = total.add(new BigDecimal(object.toString()));
                        }
                    }
                } else {

                }*/
                total = total.add(new BigDecimal(object.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formatNum(total.toEngineeringString());
    }

    /***
     * 格式化数值
     * @return
     */
    private String formatNum(String num) {
        if (StringUtils.isEmpty(num)) {
            return "0.00";
        }
        BigDecimal decimal = new BigDecimal(num);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString();
    }

    private String formatNumObject(Object object) {
        if (object != null) {
            return object.toString();
        }
        return "0";
    }

    private String formatPercentage(BigDecimal decimal) {
        if (decimal == null || decimal.compareTo(new BigDecimal(0)) == 0) {
            return "0.00%";
        }
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString() + "%";
    }

    private String formatString(BigDecimal decimal) {
        if (decimal == null || decimal.compareTo(new BigDecimal("0")) == 0) {
            return "0.00";
        }
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString();
    }

    /***
     *  返回六大区列表
     * @param dataKingDtoList
     * @param allCropType
     * @param reginCodes
     * @return
     */
    private List<DataKingDto> getSixDataKing(List<DataKingDto> dataKingDtoList, String allCropType, String reginCodes) {
        List<SysDictionary> list = null;
        if (allCropType.equals("1")) {// 单个大区
            list = sysDictionaryMapper.getSysDictionaryListByKey("six_region", reginCodes);
        }
        if (allCropType.equals("3")) {// 全部大区
            list = sysDictionaryMapper.getSysDictionaryListByKey("six_region", null);

        }
        if (!ListUtils.isEmpty(list)) {
            DataKingDto dataKingDto = null;
            for (SysDictionary item : list) {
                dataKingDto = new DataKingDto();
                dataKingDto.setId(item.getId().toString());
                dataKingDto.setAreaName(item.getDictValue());
                dataKingDto.setRegionCode(item.getDictKey());
                dataKingDtoList.add(dataKingDto);
            }
        }
        return dataKingDtoList;
    }


    @Override
    public void datasAnalysisExport(HashMap<String, String> paramMap, HttpServletResponse response) throws Exception {
        // 获取动态表头
        if (paramMap != null) {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + "数据分析.xls" + ";filename*=utf-8''"
                    + URLEncoder.encode("数据分析.xls", "utf-8"));
            String analysisIndex = paramMap.get("analysisIndex");
            List<String> headerList = com.sofn.ducss.util.ListUtils.splitToList(analysisIndex); // 表头
            List<String> headList = new ArrayList<>(); // 表头获取结果值列表
            List<String> contextHeadList = new ArrayList<>();
            for (String item : headerList) {
                contextHeadList.add(AnalyIndexEnum.getDataValue(item));
                headList.add(AnalyIndexEnum.getValue(item));
            }
            paramMap.put("pageSize", "10000");
            paramMap.put("pageNum", "1");
            List<String> strawNameList = new ArrayList<>();
            List<String> contextStrawNameList = getDictTypeList(paramMap.get("cropType"));
            for (String item : contextStrawNameList) {
                if (item.equals("all_type")) {
                    strawNameList.add("全部作物");
                } else {
                    String value = sysDictionaryMapper.getDictValue(item);
                    strawNameList.add(value);
                }
            }
            Result<PageUtils<DataKingDto>> result = getDataList(paramMap);
            if (result != null && result.getCode() == 200) {
                List<DataKingDto> dataList = result.getData().getList();
                String year = paramMap.get("year");
                HSSFWorkbook workbook = ExcelDataAnalyUtil.createWorkbook(headList, contextHeadList, strawNameList, contextStrawNameList, dataList, com.sofn.ducss.util.ListUtils.splitToList(year));
                OutputStream ouputStream = response.getOutputStream();
                workbook.write(ouputStream);
                ouputStream.flush();
                ouputStream.close();
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void real() {
        dataAnalysisAreaMapper.updateTotolRate();
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void real2() {
        List<DataAnalysisCity> all = dataAnalysisCityMapper.selectList(new QueryWrapper<>());
        for (DataAnalysisCity city : all) {
            String cityId = city.getCityId();
            String year = city.getYear();
            String strawType = city.getStrawType();
            List<String> childrenAreaIds = SysRegionUtil.getChildrenRegionIdByYearList(cityId, year);
            if (CollectionUtils.isNotEmpty(childrenAreaIds)) {
                List<DataAnalysisArea> areaList = dataAnalysisAreaMapper.listByYearAndStrawTypeAndAreaIds(year, strawType, childrenAreaIds);
                if (CollectionUtils.isNotEmpty(areaList)) {
                    BigDecimal totalCollectResource = BigDecimal.ZERO;
                    BigDecimal totalStrawUtilization = BigDecimal.ZERO;
                    for (DataAnalysisArea area : areaList) {
                        if (area.getCollectResource().compareTo(BigDecimal.ZERO) > 0 && area.getStrawUtilization().compareTo(BigDecimal.ZERO) > 0) {
                            totalCollectResource = totalCollectResource.add(area.getCollectResource());
                            totalStrawUtilization = totalStrawUtilization.add(area.getStrawUtilization());
                        }
                    }
                    if (totalCollectResource.compareTo(BigDecimal.ZERO) > 0) {
                        city.setTotolRate(totalStrawUtilization.multiply(new BigDecimal("100").divide(totalCollectResource, 10, RoundingMode.HALF_UP)));
                    } else if (totalCollectResource.compareTo(BigDecimal.ZERO) == 0) {
                        city.setTotolRate(BigDecimal.ZERO);
                    }
                    city.setCollectResourceV2(totalCollectResource);
                    city.setStrawUtilizationV2(totalStrawUtilization);
                }
            }
        }
        for (DataAnalysisCity city : all) {
            dataAnalysisCityMapper.updateTotolRateByYearAndStrawTypeAndCityId(city.getYear(), city.getStrawType(), city.getCityId(), city);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void real3() {
        List<DataAnalysisProvice> all = dataAnalysisProviceMapper.selectList(new QueryWrapper<>());
        for (DataAnalysisProvice province : all) {
            String provinceId = province.getProviceId();
            String year = province.getYear();
            String strawType = province.getStrawType();
            List<String> childrenCityIds = SysRegionUtil.getChildrenRegionIdByYearList(provinceId, year);
            if (CollectionUtils.isNotEmpty(childrenCityIds)) {
                List<DataAnalysisCity> cityList = dataAnalysisCityMapper.listByYearAndStrawTypeAndCityIds(year, strawType, childrenCityIds);
                if (CollectionUtils.isNotEmpty(cityList)) {
                    BigDecimal totalCollectResource = BigDecimal.ZERO;
                    BigDecimal totalStrawUtilization = BigDecimal.ZERO;
                    for (DataAnalysisCity city : cityList) {
                        if (city.getCollectResourceV2().compareTo(BigDecimal.ZERO) > 0 && city.getStrawUtilizationV2().compareTo(BigDecimal.ZERO) > 0) {
                            totalCollectResource = totalCollectResource.add(city.getCollectResourceV2());
                            totalStrawUtilization = totalStrawUtilization.add(city.getStrawUtilizationV2());
                        }
                    }
                    if (totalCollectResource.compareTo(BigDecimal.ZERO) > 0) {
                        province.setTotolRate(totalStrawUtilization.multiply(new BigDecimal("100").divide(totalCollectResource, 10, RoundingMode.HALF_UP)));
                    } else if (totalCollectResource.compareTo(BigDecimal.ZERO) == 0) {
                        province.setTotolRate(BigDecimal.ZERO);
                    }
                    province.setCollectResourceV2(totalCollectResource);
                    province.setStrawUtilizationV2(totalStrawUtilization);
                }
            }
        }
        for (DataAnalysisProvice province : all) {
            dataAnalysisProviceMapper.updateTotolRateByYearAndStrawTypeAndProviceId(province.getYear(), province.getStrawType(), province.getProviceId(), province);
        }
    }

    @Override
    public void real4(List<String> areaIds) {
        List<String> areaIdList = new ArrayList<>();
        if (CollectionUtils.isEmpty(areaIds)) {
            String areaIdStr = "110101,110102,110105,110106,110107,110108,110109,110111,110112,110113,110114,110115,110116,110117,110118,110119,110120,120101,120102,120103,120104,120105,120106,120110,120111,120112,120113,120114,120115,120116,120117,120118,120119,120120,130102,130104,130105,130107,130108,130109,130110,130111,130121,130123,130125,130126,130127,130128,130129,130130,130131,130132,130133,130183,130184,130185,130186,139181,130202,130203,130204,130205,130207,130208,130209,130223,130224,130225,130227,130229,130271,130272,130273,130274,130281,130283,130302,130303,130304,130306,130321,130322,130324,130325,130371,130402,130403,130404,130406,130407,130408,130423,130424,130425,130426,130427,130430,130431,130432,130433,130434,130435,130473,130481,130482,130502,130503,130521,130522,130523,130524,130525,130526,130527,130528,130529,130530,130531,130532,130533,130534,130535,130581,130582,130583,130602,130606,130607,130608,130609,130623,130624,130626,130627,130628,130630,130631,130633,130634,130635,130636,130637,130681,130683,130684,130686,139682,130629,130632,130638,130702,130703,130705,130706,130708,130709,130722,130723,130724,130725,130726,130727,130728,130730,130731,130732,130771,130772,130773,130802,130803,130804,130821,130822,130824,130825,130826,130827,130828,130871,130881,130902,130903,130914,130921,130922,130923,130924,130925,130926,130927,130928,130929,130930,130981,130982,130983,130984,130985,130986,131002,131003,131022,131023,131024,131025,131026,131028,131081,131082,131083,131102,131103,131121,131122,131123,131124,131125,131126,131127,131128,131172,131174,131182,140105,140106,140107,140108,140109,140110,140121,140122,140123,140181,140182,140183,140184,140212,140213,140214,140215,140221,140222,140223,140224,140225,140226,140228,140302,140303,140311,140321,140322,140403,140404,140423,140424,140425,140426,140427,140428,140429,140430,140431,140481,140502,140521,140522,140524,140525,140581,140582,140602,140603,140621,140622,140623,140624,140702,140721,140722,140723,140724,140725,140726,140727,140728,140729,140781,140802,140821,140822,140823,140824,140825,140826,140827,140828,140829,140830,140881,140882,140902,140921,140922,140923,140924,140925,140926,140927,140928,140929,140930,140931,140932,140971,140981,141002,141021,141022,141023,141024,141025,141026,141027,141028,141029,141030,141031,141032,141033,141034,141081,141082,141102,141121,141122,141123,141124,141125,141126,141127,141128,141129,141130,141181,141182,150102,150103,150104,150105,150121,150122,150123,150124,150125,150202,150203,150204,150205,150206,150207,150221,150222,150223,150302,150303,150304,150402,150403,150404,150421,150422,150423,150424,150425,150426,150428,150429,150430,150502,150521,150522,150523,150524,150525,150526,150581,150602,150603,150621,150622,150623,150624,150625,150626,150627,150702,150703,150721,150722,150723,150724,150725,150726,150727,150781,150782,150783,150784,150785,150802,150821,150822,150823,150824,150825,150826,150902,150921,150922,150923,150924,150925,150926,150927,150928,150929,150981,152201,152202,152221,152222,152223,152224,152225,152501,152502,152522,152523,152524,152525,152526,152527,152528,152529,152530,152531,152532,152921,152922,152923,210102,210103,210104,210105,210106,210111,210112,210113,210114,210115,210123,210124,210181,210182,210202,210203,210204,210211,210212,210213,210214,210224,210281,210283,210284,210285,210286,210287,210288,210302,210303,210304,210311,210321,210323,210381,210382,210383,210384,210402,210403,210404,210411,210412,210421,210422,210423,210502,210503,210504,210505,210506,210521,210522,210602,210603,210604,210606,210607,210624,210681,210682,210702,210703,210711,210726,210727,210781,210782,210783,210784,210785,210802,210803,210804,210811,210881,210882,210902,210903,210904,210905,210911,210921,210922,211002,211003,211004,211005,211011,211021,211081,211102,211103,211104,211122,211202,211204,211205,211221,211223,211224,211281,211282,211302,211303,211321,211322,211324,211381,211382,211402,211403,211404,211421,211422,211481,211501,220102,220103,220104,220105,220106,220112,220113,220119,220120,220122,220182,220183,220184,220185,220186,220187,220188,220202,220203,220204,220211,220221,220281,220282,220283,220284,220285,220302,220303,220322,220323,220382,220390,220402,220403,220421,220422,220502,220503,220521,220523,220524,220582,220590,220602,220605,220621,220622,220623,220681,220702,220721,220722,220723,220781,220802,220821,220822,220881,220882,222401,222402,222403,222404,222405,222406,222424,222426,230102,230103,230104,230108,230109,230110,230111,230112,230113,230123,230124,230125,230126,230127,230128,230129,230183,230184,230185,230186,230202,230203,230204,230205,230206,230207,230208,230221,230223,230224,230225,230227,230229,230230,230231,230281,230282,230302,230303,230304,230305,230306,230307,230321,230381,230382,230402,230403,230404,230405,230406,230407,230421,230422,230502,230503,230505,230506,230521,230522,230523,230524,230602,230603,230604,230605,230606,230621,230622,230623,230624,230625,230702,230703,230705,230706,230707,230708,230709,230710,230711,230712,230713,230714,230715,230716,230717,230718,230719,230722,230723,230724,230725,230726,230751,230781,230803,230804,230805,230811,230822,230826,230828,230881,230882,230883,230902,230903,230904,230905,230921,231002,231003,231004,231005,231025,231081,231083,231084,231085,231086,231102,231103,231121,231123,231124,231181,231182,231183,231202,231221,231222,231223,231224,231225,231226,231281,231282,231283,231284,232701,232702,232703,232704,232721,232722,232723,232801,232802,232803,232804,232805,232806,232807,232808,232809,232810,232901,232902,232903,232904,232905,232906,232907,232908,232909,232910,232911,232912,232913,232914,232915,232916,232917,232918,232919,232920,232921,232922,232923,233001,233002,233003,233004,233005,233006,233007,233008,233009,233010,310101,310104,310105,310106,310107,310109,310110,310112,310113,310114,310115,310116,310117,310118,310120,310151,310231,310232,310233,310234,310235,310236,320102,320104,320105,320106,320111,320113,320114,320115,320116,320117,320118,320119,320120,320205,320206,320211,320213,320214,320281,320282,320292,320302,320303,320305,320311,320312,320321,320322,320324,320381,320382,320383,320384,320385,320402,320404,320411,320412,320413,320481,320482,320505,320506,320507,320508,320509,320581,320582,320583,320585,320586,320587,320588,320602,320611,320612,320621,320623,320681,320682,320684,320685,320686,320703,320706,320707,320722,320723,320724,320725,320726,320727,320728,320729,320730,320803,320804,320812,320813,320826,320830,320831,320832,320902,320903,320904,320921,320922,320923,320924,320925,320981,321002,321003,321012,321023,321081,321084,321102,321111,321112,321181,321182,321183,321184,321186,321202,321203,321204,321271,321281,321282,321283,321302,321311,321322,321323,321324,321325,330102,330103,330104,330105,330106,330108,330109,330110,330111,330122,330127,330182,330185,330186,330187,330203,330205,330206,330211,330212,330213,330225,330226,330281,330282,330284,330285,330286,330302,330303,330304,330305,330306,330324,330326,330327,330328,330329,330381,330382,330383,330402,330411,330421,330424,330481,330482,330483,330502,330503,330521,330522,330523,330602,330603,330604,330624,330681,330683,330702,330703,330704,330723,330726,330727,330781,330782,330783,330784,330802,330803,330822,330824,330825,330881,330902,330903,330921,330922,331002,331003,331004,331021,331022,331023,331024,331081,331082,331102,331121,331122,331123,331124,331125,331126,331127,331181,331201,331202,331203,331204,331205,331206,331207,331208,331209,331210,340102,340103,340104,340111,340121,340122,340123,340124,340181,340184,340185,340186,340187,340188,340189,340202,340203,340207,340208,340221,340222,340223,340225,340302,340303,340304,340311,340321,340322,340323,340324,340325,340402,340403,340404,340405,340406,340421,340422,340423,340424,340425,340503,340504,340506,340521,340522,340523,340602,340603,340604,340621,340705,340706,340711,340722,340723,340802,340803,340811,340822,340824,340825,340826,340827,340828,340881,340898,341002,341003,341004,341021,341022,341023,341024,341102,341103,341122,341124,341125,341126,341181,341182,341202,341203,341204,341221,341222,341225,341226,341282,341283,341302,341321,341322,341323,341324,341325,341502,341503,341504,341522,341523,341524,341525,341602,341621,341622,341623,341702,341721,341722,341723,341724,341802,341821,341822,341823,341824,341825,341881,350102,350103,350104,350105,350111,350121,350122,350123,350124,350125,350181,350182,350183,350203,350205,350206,350211,350212,350213,350302,350303,350304,350305,350322,350402,350403,350421,350423,350424,350425,350426,350427,350428,350429,350430,350481,350502,350503,350504,350505,350521,350524,350525,350526,350527,350581,350582,350583,350584,350585,350586,350587,350602,350603,350622,350623,350624,350625,350626,350627,350628,350629,350681,350702,350703,350721,350722,350723,350724,350725,350781,350782,350783,350802,350803,350821,350823,350824,350825,350881,350902,350921,350922,350923,350924,350925,350926,350981,350982,350983,350128,360102,360103,360104,360105,360111,360112,360121,360123,360124,360125,360126,360127,360128,360129,360130,360131,360202,360203,360222,360281,360302,360313,360321,360322,360323,360402,360403,360421,360423,360424,360425,360426,360428,360429,360430,360481,360482,360483,360484,360485,360486,360502,360503,360504,360521,360602,360622,360681,360682,360683,360702,360703,360704,360722,360723,360724,360725,360726,360727,360728,360729,360730,360731,360732,360733,360734,360735,360736,360737,360781,360782,360802,360803,360821,360822,360823,360824,360825,360826,360827,360828,360829,360830,360881,360902,360921,360922,360923,360924,360925,360926,360981,360982,360983,361002,361003,361021,361022,361023,361024,361025,361026,361027,361028,361030,361102,361103,361121,361123,361124,361125,361126,361127,361128,361129,361130,361181,370102,370103,370104,370105,370106,370107,370112,370113,370114,370116,370117,370124,370125,370126,370182,370202,370203,370211,370212,370213,370214,370281,370282,370283,370285,370286,370287,370302,370303,370304,370305,370306,370321,370322,370323,370324,370325,370326,370402,370403,370404,370405,370406,370481,370502,370503,370505,370522,370523,370602,370611,370612,370613,370634,370681,370682,370683,370684,370685,370686,370687,370688,370689,370702,370703,370704,370705,370724,370725,370781,370782,370783,370784,370785,370786,370787,370788,370811,370812,370826,370827,370828,370829,370830,370831,370832,370881,370883,370884,370902,370911,370921,370923,370982,370983,370984,370985,370986,371002,371003,371004,371005,371006,371007,371082,371083,371102,371103,371121,371122,371202,371203,371302,371311,371312,371321,371322,371323,371324,371325,371326,371327,371328,371329,371371,371402,371403,371422,371423,371424,371425,371426,371427,371428,371481,371482,371483,371484,371502,371521,371522,371523,371524,371525,371526,371527,371528,371529,371581,371602,371603,371621,371622,371623,371625,371626,371629,371702,371703,371721,371722,371723,371724,371725,371726,371728,410102,410103,410104,410105,410106,410108,410109,410122,410181,410182,410183,410184,410185,410186,410187,410188,410189,410202,410203,410204,410205,410211,410212,410221,410222,410223,410225,410226,410302,410303,410304,410305,410306,410309,410310,410311,410322,410323,410324,410325,410326,410327,410328,410329,410381,410382,410383,410402,410403,410404,410409,410410,410411,410421,410422,410423,410425,410481,410482,410502,410503,410505,410506,410522,410523,410526,410527,410581,410582,410602,410603,410611,410621,410622,410702,410703,410704,410708,410709,410710,410711,410721,410724,410725,410726,410727,410728,410781,410782,410802,410803,410804,410811,410821,410822,410823,410825,410871,410882,410883,410902,410922,410923,410926,410927,410928,410971,410972,411002,411003,411004,411005,411006,411024,411025,411081,411082,411102,411103,411104,411105,411106,411107,411121,411122,411202,411203,411221,411224,411270,411271,411281,411282,411302,411303,411304,411305,411306,411307,411321,411322,411323,411324,411325,411326,411327,411328,411329,411330,411381,411402,411403,411404,411421,411422,411423,411424,411425,411426,411481,411502,411503,411521,411522,411523,411524,411525,411526,411527,411528,411602,411603,411621,411622,411623,411624,411625,411626,411627,411628,411681,411682,411683,411702,411705,411706,411707,411721,411722,411723,411724,411725,411726,411727,411728,411729,419011,419012,419013,419014,419015,419016,419017,419018,419019,419020,419021,419022,419023,419024,419025,419026,420102,420103,420104,420105,420106,420107,420111,420112,420113,420114,420115,420116,420117,420118,420202,420203,420204,420205,420222,420281,420282,420302,420303,420304,420322,420323,420324,420325,420381,420502,420503,420504,420505,420506,420525,420526,420527,420528,420529,420581,420582,420583,420584,420602,420606,420607,420624,420625,420626,420682,420683,420684,420685,420686,420702,420703,420704,420802,420804,420821,420822,420881,420902,420921,420922,420923,420981,420982,420984,421002,421003,421022,421023,421024,421081,421083,421087,421102,421103,421121,421122,421123,421124,421125,421126,421127,421181,421182,421202,421221,421222,421223,421224,421281,421303,421321,421381,422801,422802,422822,422823,422825,422826,422827,422828,429401,429402,429403,429404,429405,429406,429407,429408,429409,429410,429411,429412,429413,429414,429415,429416,429417,429418,429419,429420,429421,429422,429423,429424,429425,429426,429427,429428,429429,429501,429502,429503,429504,429505,429506,429507,429508,429509,429510,429511,429512,429513,429514,429515,429516,429517,429518,429519,429520,429521,429522,429523,429524,429525,429601,429602,429603,429604,429605,429606,429607,429608,429609,429610,429611,429612,429613,429614,429615,429616,429617,429618,429619,429620,429621,429622,429623,429624,429625,429626,429627,429628,429629,429630,429022,429023,429024,429025,429026,429027,429028,429029,429030,430102,430103,430104,430105,430111,430112,430121,430124,430181,430182,430202,430203,430204,430211,430221,430223,430224,430225,430281,430282,430302,430304,430321,430381,430382,430383,430405,430406,430407,430408,430412,430421,430422,430423,430424,430426,430481,430482,430483,430502,430503,430511,430521,430522,430523,430524,430525,430527,430528,430529,430581,430602,430603,430611,430621,430623,430624,430626,430681,430682,430683,430702,430703,430721,430722,430723,430724,430725,430726,430781,430802,430811,430821,430822,430902,430903,430904,430921,430922,430923,430981,431002,431003,431021,431022,431023,431024,431025,431026,431027,431028,431081,431102,431103,431104,431105,431121,431122,431123,431124,431125,431126,431127,431128,431129,431202,431221,431222,431223,431224,431225,431226,431227,431228,431229,431230,431281,431282,431302,431321,431322,431381,431382,433101,433122,433123,433124,433125,433126,433127,433130,440103,440104,440105,440106,440111,440112,440113,440114,440115,440117,440118,440203,440204,440205,440222,440224,440229,440232,440233,440281,440282,440303,440304,440305,440306,440307,440308,440309,440310,440311,440312,440402,440403,440404,440405,440406,440507,440511,440512,440513,440514,440515,440523,440604,440605,440606,440607,440608,440703,440704,440705,440781,440783,440784,440785,440802,440803,440804,440811,440823,440825,440881,440882,440883,440884,440902,440904,440981,440982,440983,441202,441203,441204,441223,441224,441225,441226,441284,441302,441303,441322,441323,441324,441325,441326,441402,441403,441422,441423,441424,441426,441427,441481,441502,441521,441523,441581,441602,441621,441622,441623,441624,441625,441702,441704,441721,441781,441802,441803,441821,441823,441825,441826,441881,441882,441901,441902,441903,441904,441905,441906,441907,441908,441909,441910,441911,441912,441913,441914,441915,441916,441917,441918,441919,441920,441921,441922,441923,441924,441925,441926,441927,441928,441929,441930,441931,441932,441933,442001,442002,442003,442004,442005,442006,442007,442008,442009,442010,442011,442012,442013,442014,442015,442016,442017,442018,442019,442020,442021,442022,442023,442024,445102,445103,445122,445202,445203,445222,445224,445225,445226,445281,445302,445303,445321,445322,445381,450102,450103,450105,450107,450108,450109,450110,450123,450124,450125,450126,450127,450128,450202,450203,450204,450205,450206,450222,450223,450224,450225,450226,450227,450302,450303,450304,450305,450311,450312,450321,450323,450324,450325,450326,450327,450328,450329,450330,450331,450332,450403,450405,450406,450421,450422,450423,450481,450502,450503,450512,450521,450602,450603,450621,450681,450702,450703,450721,450722,450802,450803,450804,450821,450881,450902,450903,450921,450922,450923,450924,450981,450982,450983,451002,451021,451022,451023,451024,451026,451027,451028,451029,451030,451031,451081,451102,451103,451121,451122,451123,451202,451203,451221,451222,451223,451224,451225,451226,451227,451228,451229,451302,451321,451322,451323,451324,451381,451402,451421,451422,451423,451424,451425,451481,460105,460106,460107,460108,460202,460203,460204,460205,460206,460409,460501,469091,469092,469095,469096,469097,469921,469922,469923,469924,469925,469926,469927,469928,469929,469930,500101,500102,500103,500104,500105,500106,500107,500108,500109,500110,500111,500112,500113,500114,500115,500116,500117,500118,500119,500120,500151,500152,500153,500154,500155,500156,500229,500230,500231,500233,500235,500236,500237,500238,500240,500241,500242,500243,500244,500301,500302,510104,510105,510106,510107,510108,510112,510113,510114,510115,510116,510117,510121,510129,510131,510132,510181,510182,510183,510184,510185,510189,510302,510303,510304,510311,510321,510322,510402,510403,510411,510421,510422,510502,510503,510504,510521,510522,510524,510525,510603,510623,510626,510681,510682,510683,510703,510704,510705,510722,510723,510725,510726,510727,510781,510802,510811,510812,510821,510822,510823,510824,510903,510904,510921,510922,510923,511002,511011,511024,511025,511083,511102,511111,511112,511113,511123,511124,511126,511129,511132,511133,511181,511302,511303,511304,511321,511322,511323,511324,511325,511381,511402,511403,511421,511423,511424,511425,511502,511503,511521,511523,511524,511525,511526,511527,511528,511529,511602,511603,511621,511622,511623,511681,511702,511703,511722,511723,511724,511725,511781,511802,511803,511822,511823,511824,511825,511826,511827,511902,511903,511921,511922,511923,512002,512021,512022,513201,513221,513222,513223,513224,513225,513226,513227,513228,513230,513231,513232,513233,513301,513322,513323,513324,513325,513326,513327,513328,513329,513330,513331,513332,513333,513334,513335,513336,513337,513338,513401,513422,513423,513424,513425,513426,513427,513428,513429,513430,513431,513432,513433,513434,513435,513436,513437,520102,520103,520111,520112,520113,520115,520121,520122,520123,520181,520182,520183,520201,520203,520221,520281,520302,520303,520304,520305,520322,520323,520324,520325,520326,520327,520328,520329,520330,520381,520382,520402,520403,520404,520405,520422,520423,520424,520425,520502,520503,520504,520521,520522,520523,520524,520525,520526,520527,520602,520603,520604,520605,520621,520622,520623,520624,520625,520626,520627,520628,522301,522302,522322,522323,522324,522325,522326,522327,522328,522601,522622,522623,522624,522625,522626,522627,522628,522629,522630,522631,522632,522633,522634,522635,522636,522701,522702,522722,522723,522725,522726,522727,522728,522729,522730,522731,522732,530102,530103,530111,530112,530113,530114,530115,530124,530125,530126,530127,530128,530129,530181,530182,530183,530184,530302,530303,530321,530322,530323,530324,530325,530326,530381,530402,530403,530422,530423,530424,530425,530426,530427,530428,530502,530521,530523,530524,530581,530602,530621,530622,530623,530624,530625,530626,530627,530628,530629,530630,530702,530721,530722,530723,530724,530802,530821,530822,530823,530824,530825,530826,530827,530828,530829,530902,530921,530922,530923,530924,530925,530926,530927,532301,532322,532323,532324,532325,532326,532327,532328,532329,532331,532501,532502,532503,532504,532523,532524,532525,532527,532528,532529,532530,532531,532532,532601,532622,532623,532624,532625,532626,532627,532628,532801,532822,532823,532901,532922,532923,532924,532925,532926,532927,532928,532929,532930,532931,532932,533102,533103,533122,533123,533124,533301,533323,533324,533325,533401,533422,533423,540102,540103,540121,540122,540123,540124,540126,540127,540202,540221,540222,540223,540224,540225,540226,540227,540228,540229,540230,540231,540232,540233,540234,540235,540236,540237,540302,540321,540322,540323,540324,540325,540326,540327,540328,540329,540330,540402,540421,540422,540423,540424,540425,540426,540502,540521,540522,540523,540524,540525,540526,540527,540528,540529,540530,540531,542421,542422,542423,542424,542425,542426,542427,542428,542429,542430,542431,542521,542522,542523,542524,542525,542526,542527,610102,610103,610104,610111,610112,610113,610114,610115,610116,610117,610118,610122,610124,610125,610127,610128,610129,610202,610203,610204,610205,610222,610302,610303,610304,610322,610323,610324,610326,610327,610328,610329,610330,610331,610332,610402,610403,610404,610422,610423,610424,610425,610426,610427,610428,610429,610430,610431,610481,610482,610502,610503,610504,610522,610523,610524,610525,610526,610527,610528,610581,610582,610602,610603,610621,610622,610623,610625,610626,610627,610628,610629,610630,610631,610632,610702,610721,610722,610723,610724,610725,610726,610727,610728,610729,610730,610802,610803,610822,610824,610825,610826,610827,610828,610829,610830,610831,610881,610902,610921,610922,610923,610924,610925,610926,610927,610928,610929,611002,611021,611022,611023,611024,611025,611026,620102,620103,620104,620105,620111,620121,620122,620123,620125,620126,620201,620302,620321,620402,620403,620421,620422,620423,620502,620503,620521,620522,620523,620524,620525,620602,620621,620622,620623,620702,620721,620722,620723,620724,620725,620802,620821,620822,620823,620824,620825,620826,620902,620921,620922,620923,620924,620981,620982,621002,621021,621022,621023,621024,621025,621026,621027,621102,621121,621122,621123,621124,621125,621126,621202,621221,621222,621223,621224,621225,621226,621227,621228,622901,622921,622922,622923,622924,622925,622926,622927,623001,623021,623022,623023,623024,623025,623026,623027,620124,630102,630103,630104,630105,630121,630122,630123,630202,630203,630222,630223,630224,630225,632221,632222,632223,632224,632321,632322,632323,632324,632521,632522,632523,632524,632525,632621,632622,632623,632624,632625,632626,632701,632722,632723,632724,632725,632726,632801,632802,632821,632822,632823,640104,640105,640106,640121,640122,640181,640182,640202,640205,640221,640222,640302,640303,640323,640324,640381,640402,640422,640423,640424,640425,640502,640521,640522,650001,659001,659101,659102,659103,659104,659105,659106,659107,659108,659002,659201,659202,659203,659204,659205,659206,659003,659301,659302,659303,659304,659305,659306,659307,659308,659309,659004,659401,659402,659403,659404,659005,659501,659502,659503,659504,659505,659506,659006,659601,659602,659603,659604,659007,659701,659702,659703,659704,659705,659706,659008,659801,659802,659803,659804,659805,659009,659901,659902,659903,659904,650102,650103,650104,650105,650106,650107,650109,650121,650202,650203,650204,650205,650402,650421,650422,650502,650521,650522,652301,652302,652323,652324,652325,652327,652328,652701,652702,652722,652723,652801,652822,652823,652824,652825,652826,652827,652828,652829,652901,652922,652923,652924,652925,652926,652927,652928,652929,653001,653022,653023,653024,653101,653121,653122,653123,653124,653125,653126,653127,653128,653129,653130,653131,653201,653221,653222,653223,653224,653225,653226,653227,654002,654003,654004,654021,654022,654023,654024,654025,654026,654027,654028,654201,654202,654221,654223,654224,654225,654226,654301,654321,654322,654323,654324,654325,654326,660101,660102,660103,660104,660105,660106,660107,660108,660109,660110,660111,660112,660113,660114,660201,660202,660203,660204,660205,660206,660207,660208,660209,660210,660211,660212,660213,660301,660302,660303,660304,660305,660306,660307,660308,660309,660310,660311,660312,660313,660314,660315,660401,660402,660403,660404,660405,660406,660407,660408,660409,660410,660411,660412,660413,660414,660415,660416,660417,660418,660419,660502,660503,660504,660505,660506,660507,660508,660509,660510,660601,660602,660603,660604,660605,660606,660607,660608,660609,660610,660611,660612,660613,660614,660615,660701,660702,660703,660704,660705,660706,660707,660708,660709,660710,660711,660712,660801,660802,660803,660804,660805,660806,660807,660808,660809,660810,660811,660812,660813,660814,660815,660901,660902,660903,660904,660905,660906,660907,660908,660909,660910,661001,661002,661003,661004,661005,661006,661007,661008,661101,661201,661202,661203,661204,661205,661206,661207,661208,661301,661302,661303,661304,661305,661306,661307,661308,661401,661402,661403,661404,710101,710102,710103,710104,710105,710106,710107,710108,710109,710110,710111,710112,710201,710202,710203,710204,710205,710206,710207,710208,710209,710210,710211,710212,710213,710214,710215,710216,710217,710218,710219,710220,710221,710222,710223,710224,710225,710226,710227,710228,710229,710230,710231,710232,710233,710234,710235,710236,710237,710238,710301,710302,710303,710304,710305,710306,710307,710401,710402,710403,710404,710405,710406,710407,710408,710409,710410,710411,710412,710413,710414,710415,710416,710417,710418,710419,710420,710421,710422,710423,710424,710425,710426,710427,710428,710429,710501,710502,710504,710506,710507,710508,710509,710510,710511,710512,710513,710514,710515,710516,710517,710518,710519,710520,710521,710522,710523,710524,710525,710526,710527,710528,710529,710530,710531,710532,710533,710534,710535,710536,710537,710538,710539,710601,710602,710603,710701,710702,710801,710802,710803,710804,710805,710806,710807,710808,710809,710810,710811,710812,710813,710814,710815,710816,710817,710818,710819,710820,710821,710822,710823,710824,710825,710826,710827,710828,710829,712201,712221,712222,712223,712224,712225,712226,712227,712228,712229,712230,712231,712301,712302,712303,712304,712305,712306,712321,712324,712325,712327,712329,712330,712331,712401,712421,712422,712423,712424,712425,712426,712427,712428,712429,712430,712431,712432,712501,712521,712522,712523,712524,712525,712526,712527,712528,712529,712530,712531,712532,712533,712534,712535,712536,712537,712701,712721,712722,712723,712724,712725,712726,712727,712728,712729,712730,712731,712732,712733,712734,712735,712736,712737,712738,712739,712740,712741,712742,712743,712744,712745,712801,712821,712822,712823,712824,712825,712826,712827,712828,712829,712830,712831,712832,712901,712921,712922,712923,712924,712925,712926,712927,712928,712929,712930,712931,712932,712933,712934,712935,712936,712937,712938,712939,713001,713002,713023,713024,713025,713026,713027,713028,713029,713030,713031,713032,713033,713034,713035,713036,713037,713038,713301,713321,713322,713323,713324,713325,713326,713327,713328,713329,713330,713331,713332,713333,713334,713335,713336,713337,713338,713339,713340,713341,713342,713343,713344,713345,713346,713347,713348,713349,713350,713351,713352,713401,713421,713422,713423,713424,713425,713426,713427,713428,713429,713430,713431,713432,713433,713434,713435,713501,713521,713522,713523,713524,713525,713526,713527,713528,713529,713530,713531,713532,713601,713621,713622,713623,713624,713625,713701,713702,713703,713704,713705,713706,713801,713802,713803,713804,810101,810102,810103,810104,810201,810202,810203,810204,810205,810301,810302,810303,810304,810305,810306,810307,810308,810309,820101,820102,820103,820104,820105,820201,820301,230730,230731";
            areaIdList = Arrays.asList(areaIdStr.split(","));
        } else {
            areaIdList = areaIds;
        }
        List<String> result = Lists.newArrayList();

        List<DataAnalysisCity> cityList = dataAnalysisCityMapper.listByAreaId();
        for (DataAnalysisCity city : cityList) {
            String cityId = city.getCityId();
            List<String> childrenList = SysRegionUtil.getChildrenRegionIdByYearList(cityId, "2020");
            Iterator<String> iterator = childrenList.iterator();
            while (iterator.hasNext()) {
                String areaId = iterator.next();
                if (!areaIdList.contains(areaId)) {
                    iterator.remove();
                }
            }
            BigDecimal th = BigDecimal.ZERO;
            if (CollectionUtils.isNotEmpty(childrenList)) {
                th = dataAnalysisAreaMapper.listByAreaIds(childrenList);
            }

            BigDecimal theoryResource = city.getTheoryResource();
            BigDecimal abs = theoryResource.subtract(th).abs();
            if (abs.compareTo(BigDecimal.ONE) > 0) {
                String childrenStr = childrenList.toString().replaceAll("(?:\\[|null|\\]| +)", "");
                result.add(cityId + "---" + theoryResource + "----" + childrenStr + "---" + th);
            }
        }
        log.info("输出————————");
        for (String s : result) {
            log.info("area" + s);
        }
        log.info("输出完成————————");
    }

    public static void main(String[] args) {
        List<DataAnalysisProvice> list = new ArrayList<>();
        DataAnalysisProvice dataAnalysisProvice = new DataAnalysisProvice();
        dataAnalysisProvice.setYear("2019");
        dataAnalysisProvice.setReturnArea("grainYield");
        dataAnalysisProvice.setStrawType("grassValleyRatio");
        DataAnalysisProvice dataAnalysisProvice1 = new DataAnalysisProvice();
        dataAnalysisProvice1.setYear("2018");
        dataAnalysisProvice1.setReturnArea("grainYield");
        dataAnalysisProvice1.setStrawType("corn");
        list.add(dataAnalysisProvice);
        list.add(dataAnalysisProvice1);
        Map<String, Map<String, List<DataAnalysisProvice>>> userMap = list.stream().collect(Collectors.groupingBy(DataAnalysisProvice::getYear, Collectors.groupingBy(DataAnalysisProvice::getReturnArea)));

//遍历分组后的结果
        userMap.forEach((key1, map) -> {
            System.out.println(key1 + "：");
            map.forEach((key2, user) ->
            {
                System.out.println(key2 + "：");
                user.forEach(System.out::println);
            });
            System.out.println("--------------------------------------------------------------------------");
        });
    }
}
