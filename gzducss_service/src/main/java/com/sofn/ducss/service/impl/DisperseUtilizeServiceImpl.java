package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.DisperseUtilizeMapper;
import com.sofn.ducss.model.DisperseUtilize;
import com.sofn.ducss.service.DisperseUtilizeService;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class DisperseUtilizeServiceImpl extends ServiceImpl<DisperseUtilizeMapper, DisperseUtilize> implements DisperseUtilizeService {
    @Autowired
    private DisperseUtilizeMapper disperseUtilizeMapper;

    @Override
    public PageUtils<DisperseUtilize> getDisperseUtilizeByPage(Integer pageNo, Integer pageSize, String year, String userName, String countyId, String dateBegin, String dateEnd, String orderBy, String sortOrder) {
        PageHelper.offsetPage(pageNo, pageSize);

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("userName", userName);
        params.put("countyId", countyId);
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
        params.put("orderBy", orderBy);
        params.put("sortOrder", sortOrder);
        List<DisperseUtilize> list = disperseUtilizeMapper.getDisperseUtilizeByPage(params);
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public PageUtils<DisperseUtilize> getDisperseUtilize(String year, String countyId, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("countyId", countyId);
        List<DisperseUtilize> disperseUtilize = disperseUtilizeMapper.getDisperseUtilize(params);
        PageInfo<DisperseUtilize> pageInfo = new PageInfo<>(disperseUtilize);
        List<DisperseUtilize> list = pageInfo.getList();
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> areaId = Sets.newHashSet();
            list.forEach(item -> {
                areaId.add(item.getAreaId());
                areaId.add(item.getCityId());
                areaId.add(item.getProvinceId());
            });
            Map<String, String> regionNameMapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(Lists.newArrayList(areaId)), year);
            list.forEach(item -> {
                String tempAreaName = SysRegionUtil.getAreaName(regionNameMapsByCodes, item.getProvinceId(), item.getCityId(), item.getAreaId());
                item.setAreaName(tempAreaName);
            });
            pageInfo.setList(list);
        }
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public DisperseUtilize selectDisperseUtilizeById(String id) {
        DisperseUtilize disperseUtilize = disperseUtilizeMapper.selectDisperseUtilizeById(id);
        if (disperseUtilize == null) {
            throw new SofnException("分散利用量信息异常,无相关详情!");
        }
        return disperseUtilize;
    }

    @Override
    public String findFarmerNo(String year, String areaId, String type) {
        return disperseUtilizeMapper.selectFarmerNo(year, areaId, type);
    }

    @Override
    public String selectDisperseUtilizeIdByYear(String countyId, String farmerName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("countyId", countyId);
        params.put("farmerName", farmerName);
        String id = disperseUtilizeMapper.selectDisperseUtilizeIdByYear(params);
        return id;
    }

    @Override
    public List<String> getFarmerNames(String countyId) {
        List<String> farmerNames = disperseUtilizeMapper.getFarmerNames(countyId);
        return farmerNames;
    }

}
