package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.mapper.StrawUtilizeMapper;
import com.sofn.ducss.model.ProStill;
import com.sofn.ducss.model.StrawUtilize;
import com.sofn.ducss.service.StrawUtilizeService;
import com.sofn.ducss.util.SysRegionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class StrawUtilizeServiceImpl extends ServiceImpl<StrawUtilizeMapper, StrawUtilize> implements StrawUtilizeService {
    @Autowired
    private StrawUtilizeMapper strawUtilizeMapper;

    @Override
    public PageUtils<StrawUtilize> getStrawUtilizeByPage(Integer pageNo, Integer pageSize, List<String> years, String mainName, String countyId, String dateBegin, String dateEnd) {
        PageHelper.offsetPage(pageNo, pageSize);

        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("mainName", mainName);
        params.put("countyId", countyId);
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
        List<StrawUtilize> list = strawUtilizeMapper.getStrawUtilizeByPage(params);
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public PageUtils<StrawUtilize> getStrawUtilize(List<String> years, List<String> countyIds, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("countyIds", countyIds);
        List<StrawUtilize> strawUtilize = strawUtilizeMapper.getStrawUtilize(params);
        PageInfo<StrawUtilize> strawUtilizePageInfo = new PageInfo<>(strawUtilize);
        List<StrawUtilize> list = strawUtilizePageInfo.getList();
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> codeIds = Sets.newHashSet();
            list.forEach(item -> {
                codeIds.add(item.getAreaId());
                codeIds.add(item.getCityId());
                codeIds.add(item.getProvinceId());
            });
            Map<String, String> regionNameMapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(Lists.newArrayList(codeIds)), null);
            list.forEach(item -> {
                String areaName = SysRegionUtil.getAreaName(regionNameMapsByCodes, item.getProvinceId(), item.getCityId(), item.getAreaId());
                item.setAreaName(areaName);
            });
            strawUtilizePageInfo.setList(list);
        }
        return PageUtils.getPageUtils(strawUtilizePageInfo);
    }

    @Override
    public StrawUtilize selectStrawUtilizeById(String id) {
        StrawUtilize strawUtilize = strawUtilizeMapper.selectStrawUtilizeById(id);
        if (strawUtilize == null) {
            throw new SofnException("分散利用量信息异常,无相关详情!");
        }
        return strawUtilize;
    }

    @Override
    public String findFarmerNo(String year, String areaId, String type) {
        return strawUtilizeMapper.selectFarmerNo(year, areaId, type);
    }

    @Override
    public String selectStrawUtilizeDetailIdByYear(String countyId, String mainName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("countyId", countyId);
        params.put("mainName", mainName);
        String id = strawUtilizeMapper.selectStrawUtilizeDetailIdByYear(params);
        return id;
    }

    @Override
    public List<String> getMainNames(String countyId) {
        List<String> mainNames = strawUtilizeMapper.getMainNames(countyId);
        return mainNames;
    }

    @Override
    public int countCompanyByYearAndProvince(String year, String provinceId) {
        //防止查询大量数据，因此不传参数则直接返回0
        if (StringUtils.isEmpty(year) || StringUtils.isEmpty(provinceId)) return 0;
        Map<String, Object> condition = new HashMap<>();
        condition.put("year", year);
        condition.put("provinceId", provinceId);

        return strawUtilizeMapper.getCompanyCountByCondition(condition);
    }

    @Override
    public PageUtils<StrawUtilize> listGroupByYearAndAreaId(Map<String, Object> queryMap) {
        int pageNo = Integer.parseInt(queryMap.get("pageNo").toString());
        int pageSize = Integer.parseInt(queryMap.get("pageSize").toString());
        PageHelper.offsetPage(pageNo, pageSize);
        List<StrawUtilize> strawUtilizeList = strawUtilizeMapper.listGroupByYearAndAreaId(queryMap);
        PageInfo<StrawUtilize> strawUtilizePageInfo = new PageInfo<>(strawUtilizeList);
        return PageUtils.getPageUtils(strawUtilizePageInfo);
    }

    @Override
    public List<StrawUtilize> listByYearAndProvinceId(String year, String provinceId) {
        if (StringUtils.isEmpty(year) || StringUtils.isEmpty(provinceId)) {
            return Lists.newArrayList();
        }
        Map<String, Object> condition = new HashMap<>();
        condition.put("year", year);
        condition.put("provinceId", provinceId);
        return strawUtilizeMapper.getStrawUtilize(condition);
    }

}
