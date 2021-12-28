package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.mapper.SourceMapper;
import com.sofn.agpjyz.model.Source;
import com.sofn.agpjyz.service.IndexService;
import com.sofn.agpjyz.sysapi.SysFileApi;
import com.sofn.agpjyz.vo.DropDownVo;
import com.sofn.agpjyz.vo.SourceVo;
import com.sofn.agpjyz.vo.SurveyInfoVo;
import com.sofn.agpjyz.vo.TrendVo;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Resource
    private SourceMapper sourceMapper;

    @Resource
    private SysFileApi sysApi;

    @Override
    public List<String> listArea(String year, String specId, String areaCode, String queryCode) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("expert_report", BoolUtils.N).
                eq("status", ProcessEnum.FINAL_AUDIT.getKey()).eq("spec_id", specId);
        if (StringUtils.isEmpty(areaCode) || "100000".equals(areaCode)) {
            areaCode = "";
        } else if (areaCode.endsWith("0000")) {
            queryWrapper.eq("province", areaCode);
        } else if (areaCode.endsWith("00")) {
            queryWrapper.eq("city", areaCode);
        } else {
            return Collections.EMPTY_LIST;
        }
        queryWrapper.eq("to_char(update_time,'YYYY')", year);
        queryWrapper.select("province", "city", "county");
        List<Source> list = sourceMapper.selectList(queryWrapper);
        List<String> regionList;
        if (areaCode.endsWith("0000")) {
            regionList = list.stream().map(Source::getCity).collect(Collectors.toList());
        } else if (areaCode.endsWith("00")) {
            regionList = list.stream().map(Source::getCounty).collect(Collectors.toList());
        } else {
            regionList = list.stream().map(Source::getProvince).collect(Collectors.toList());
        }
        regionList = regionList.stream().distinct().collect(Collectors.toList());
        if (StringUtils.isEmpty(queryCode)) {
            return regionList;
        } else {
            String[] queryList = queryCode.split(",");
            List<String> result = Lists.newArrayListWithCapacity(queryList.length);
            for (String str : queryList) {
                if (regionList.contains(str))
                    result.add(str);
            }
            return result;
        }
    }

    @Override
    public Map<String, Object> getAmountAndNames(Map<String, Object> params) {
        List<Source> list = sourceMapper.listSpecName(params);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("amount", list.size());
        if (CollectionUtils.isEmpty(list)) {
            map.put("names", Collections.EMPTY_LIST);
        } else {
            List<DropDownVo> downVos = Lists.newArrayListWithCapacity(list.size());
            for (Source source : list) {
                DropDownVo dropDownVo = new DropDownVo();
                dropDownVo.setId(source.getId());
                dropDownVo.setName(source.getSpecValue());
                downVos.add(dropDownVo);
            }
            map.put("names", downVos);
        }
        return map;
    }

//    private String getAmountStr(Map<String, Object> params, int size) {
//        Object county = params.get("countys");
//        Object city = params.get("citys");
//        Object province = params.get("provinces");
//        StringBuilder sb = new StringBuilder();
//        try {
//            if (Objects.nonNull(county) || Objects.nonNull(city) || Objects.nonNull(province)) {
//                sb.append(ApiUtil.getResultStrArr(sysApi.getRegionNamesByCodes(Objects.nonNull(county)
//                        ? county.toString() : Objects.nonNull(city) ? city.toString() : province.toString()))[0]);
//            } else {
//                sb.append("全国");
//            }
//        } catch (Exception e) {
//            throw new SofnException("未找到行政区域名称");
//        }
//        sb.append("野生植物数量：").append(size);
//        return sb.toString();
//    }

    @Override
    public SurveyInfoVo getInfo(String areaCode, String year, String id) {
        SourceVo sourceVo = this.getInfo(id);
        String specId = sourceVo.getSpecId();
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("to_char(update_time,'YYYY')", year).
                eq("status", ProcessEnum.FINAL_AUDIT.getKey()).eq("spec_id", specId).
                eq("expert_report", BoolUtils.N).
                orderByDesc("survey").orderByDesc("create_time");
        if (StringUtils.isEmpty(areaCode) || "100000".equals(areaCode)) {
            areaCode = "";
        } else if (areaCode.endsWith("0000")) {
            queryWrapper.eq("province", areaCode);
        } else if (areaCode.endsWith("00")) {
            queryWrapper.eq("city", areaCode);
        } else {
            queryWrapper.eq("county", areaCode);
        }
        List<Source> list = sourceMapper.selectList(queryWrapper);
        Double distribution = 0d;
        Integer amount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> areaCodes = new HashSet<>();
            for (Source source : list) {
                if (!areaCodes.contains(source.getCounty())) {
                    String distributionStr = source.getDistribution();
                    distribution += Double.valueOf(StringUtils.hasText(distributionStr) ? distributionStr : "0");
                    String amountStr = source.getAmount();
                    amount += Integer.valueOf(StringUtils.hasText(amountStr) ? amountStr : "0");
                    areaCodes.add(source.getCounty());
                }
            }
        }
        return new SurveyInfoVo(specId, sourceVo.getSpecValue(), distribution, amount);
    }

    private SourceVo getInfo(String id) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id)
                .select("id", "spec_id", "spec_value", "distribution", "amount");
        return SourceVo.entity2Vo(sourceMapper.selectOne(queryWrapper));
    }

    @Override
    public List<TrendVo> listTrend(Map<String, Object> params) {
        SourceVo sourceVo = this.getInfo(params.get("id").toString());
        params.put("specId", sourceVo.getSpecId());
        String year = (String) params.get("year");
        Integer number = (Integer) params.get("number");
        params.put("startDate", DateUtils.addDateYears(DateUtils.stringToDate(
                this.getYearStart(year), DateUtils.DATE_TIME_PATTERN), 1 - number));
        params.put("endDate", DateUtils.stringToDate(this.getYearEnd(year), DateUtils.DATE_TIME_PATTERN));
        List<TrendVo> list = sourceMapper.listTrend(params);
        List<TrendVo> result = Lists.newArrayListWithCapacity(number);
        for (int i = 0; i < number; i++) {
            String currntYear = String.valueOf(Integer.parseInt(year) - i);
            List<TrendVo> subList = list.stream().filter(vo ->
                    currntYear.equals(vo.getYear())).collect(Collectors.toList());
            Double distribution = 0d;
            Integer amount = 0;
            for (TrendVo tv : subList) {
                distribution += tv.getDistribution();
                amount += tv.getAmount();
            }
            result.add(new TrendVo(currntYear, distribution, amount));
        }
        result.sort(Comparator.comparing(TrendVo::getYear));
        return result;
    }

    private String getYearStart(String year) {
        return year + "-01-01 00:00:00";
    }

    private String getYearEnd(String year) {
        return year + "-12-31 23:59:59";
    }

}
