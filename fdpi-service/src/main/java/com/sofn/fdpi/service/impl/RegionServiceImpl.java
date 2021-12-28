package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.fdpi.mapper.RegionMapper;
import com.sofn.fdpi.model.Region;
import com.sofn.fdpi.service.RegionService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.vo.SysRegionTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("RegionService")
public class RegionServiceImpl extends BaseService<RegionMapper, Region> implements RegionService {
    @Resource
    RegionMapper regionMapper;

    @Resource
    SysRegionApi sysRegionApi;

    @Override
    @Transactional
    public void handleAreaData() {
        List<Region> list = this.getAreaData();
        if (!CollectionUtils.isEmpty(list)) {
            regionMapper.deleteAll();
            this.saveBatch(list, 500);
        }
    }

    @Override
    public Map<String, String> getAreaDataMap() {
        List<Region> list = this.getAreaData();
        if (!CollectionUtils.isEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Region::getRegionCode, Region::getRegionName));
        }
        return Collections.EMPTY_MAP;
    }

    List<Region> res = Collections.EMPTY_LIST;

    //通过支撑平台接口获取区树形数据
    private List<Region> getAreaData() {
        Result<SysRegionTreeVo> sysResult =
                sysRegionApi.getSysRegionTree(null, null, null, null, null, null, null, null, null);
        SysRegionTreeVo sysData = sysResult.getData();
        if (Objects.nonNull(sysData)) {
            res = Lists.newArrayList();
            Date now = new Date();
            this.convertAreaData(sysData, "", "", now);
        }
        return res;
    }

    //树型数据转list
    private void convertAreaData(SysRegionTreeVo sysData, String parentIds, String parentNames, Date now) {
        Region region = new Region();
        BeanUtils.copyProperties(sysData, region);
        region.setParentIds(StringUtils.hasText(parentIds) ? parentIds : "/");
        region.setParentNames(StringUtils.hasText(parentNames) ? parentNames : "/");
        region.setRegionType(this.getRegionType(sysData.getRegionCode()));
        region.setCreateTime(now);
        res.add(region);
        List<SysRegionTreeVo> childrenList = sysData.getChildren();
        //遍历子类，递归转换
        if (!CollectionUtils.isEmpty(childrenList)) {
            for (SysRegionTreeVo srtv : childrenList) {
                this.convertAreaData(srtv, parentIds + "/" + sysData.getRegionCode(), parentNames + "/" + sysData.getRegionName(), now);
            }
        }
    }

    private String getRegionType(String regionCode) {
        if ("100000".equals(regionCode)) {
            return "0";
        } else if (regionCode.endsWith("0000")) {
            return "1";
        } else if (regionCode.endsWith("00")) {
            return "2";
        } else {
            return "3";
        }
    }

    @Override
    public Region getByCode(String code) {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("region_code", code).eq("del_flag", BoolUtils.N);
        return regionMapper.selectOne(queryWrapper);
    }

    @Override
    public Region getById(String id) {
        List<Region> list = this.getAreaData();
        for (Region r : list) {
            if (id.equals(r.getId())) {
                return r;
            }
        }
        return null;
    }
}
