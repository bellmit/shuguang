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
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.mapper.CollectFlowLogMapper;
import com.sofn.ducss.mapper.ProStillMapper;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.model.ProStill;
import com.sofn.ducss.service.CollectFlowLogService;
import com.sofn.ducss.service.ProStillService;
import com.sofn.ducss.util.SysRegionUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class ProStillServiceImpl extends ServiceImpl<ProStillMapper, ProStill> implements ProStillService {
    @Autowired
    private ProStillMapper proStillMapper;

    @Override
    public PageUtils<ProStill> getProStillByPage(Integer pageNo, Integer pageSize, List<String> years, List<String> countyIds, String dateBegin, String dateEnd) {
        PageHelper.offsetPage(pageNo, pageSize);

        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("countyIds", countyIds);
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
        List<ProStill> list = proStillMapper.getProStillByPage(params);
        PageInfo<ProStill> proStillPageInfo = new PageInfo<>(list);
        List<ProStill> list1 = proStillPageInfo.getList();
        if (!CollectionUtils.isEmpty(list1)) {
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
                String userId = item.getCreateUserId();
                String userName = UserUtil.getUsernameById(userId);
                item.setCreateUserName(userName);
            });
        }
        proStillPageInfo.setList(list1);
        return PageUtils.getPageUtils(proStillPageInfo);
    }

    @Override
    public ProStill getProStill(List<String> years, List<String> countyIds) {

        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("countyIds", countyIds);
        ProStill proStill = proStillMapper.getProStill(params);
        if (proStill == null) {
            return null;
        } else {
            String userId = proStill.getCreateUserId();
            String userName = UserUtil.getUsernameById(userId);
            if (!StringUtils.isBlank(userName)) {
                proStill.setCreateUserName(userName);
            }
            //  翻译区划值
            String areaId = proStill.getAreaId();
            String cityId1 = proStill.getCityId();
            String provinceId = proStill.getProvinceId();
            Map<String, String> regionNameMapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(provinceId + "," + cityId1 + "," + areaId, null);
            if (!CollectionUtils.isEmpty(regionNameMapsByCodes)) {
                String areaName = SysRegionUtil.getAreaName(regionNameMapsByCodes, provinceId, cityId1, areaId);
                proStill.setAreaName(areaName);
            }
            return proStill;
        }
    }

    @Override
    public ProStill selectProStillById(String id) {
        ProStill proStill = proStillMapper.selectProStillById(id);
        if (proStill == null) {
            throw new SofnException("产生量与直接还田量信息异常,无相关详情!");
        }
        return proStill;
    }

    @Override
    public String selectProStillIdByYear(String countyId) {
        String id = proStillMapper.selectProStillIdByYear(countyId);
        return id;
    }

    @Override
    public PageUtils<ProStill> listGroupByYearAndAreaId(Map<String, Object> queryMap) {
        int pageNo = Integer.parseInt(queryMap.get("pageNo").toString());
        int pageSize = Integer.parseInt(queryMap.get("pageSize").toString());
        PageHelper.offsetPage(pageNo, pageSize);
        List<ProStill> proStillList = proStillMapper.listGroupByYearAndAreaId(queryMap);
        PageInfo<ProStill> proStillPageInfo = new PageInfo<>(proStillList);
        return PageUtils.getPageUtils(proStillPageInfo);
    }

}
