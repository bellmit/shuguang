package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.mapper.WaterQualityMapper;
import com.sofn.agsjdm.model.WaterQuality;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.service.WaterQualityService;
import com.sofn.agsjdm.util.AgsjdmRedisUtils;
import com.sofn.agsjdm.util.ExportUtil;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.exportBean.ExportWqBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 水质监测信息服务类
 *
 * @Author yumao
 * @Date 2020/4/14 11:21
 **/
@Service(value = "waterQualityService")
public class WaterQualityServiceImpl implements WaterQualityService {

    @Resource
    private WaterQualityMapper waterQualityMapper;
    @Resource
    private InformationManagementService informationManagementService;

    @Override
    @Transactional
    public WaterQuality save(WaterQuality entity) {
        //防止重复提交
        AgsjdmRedisUtils.checkReSubmit("WaterQualitySave", 5L);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        //防止重复录入
        String wetlandId = entity.getWetlandId();
        QueryWrapper<WaterQuality> wrapper = new QueryWrapper<>();
        wrapper.eq("wetland_id", wetlandId);
        if (waterQualityMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区信息已录入");
        }
        entity.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        entity.setOperatorTime(new Date());
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        entity.setOperator(operator);
        String[] regions = RegionUtil.getRegions();
        entity.setProvince(regions[0]);
        entity.setCity(regions[1]);
        entity.setCounty(regions[2]);
        waterQualityMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        waterQualityMapper.deleteById(id);
    }

    @Override
    public void update(WaterQuality entity) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        if (Objects.isNull(waterQualityMapper.selectById(entity.getId()))) {
            throw new SofnException("待修改数据不存在");
        }
        QueryWrapper<WaterQuality> wrapper = new QueryWrapper<>();
        wrapper.eq("wetland_id", entity.getWetlandId());
        wrapper.ne("id", entity.getId());
        if (waterQualityMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区信息已录入");
        }
        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        entity.setOperatorTime(new Date());
        entity.setOperator(operator);
        String[] regions = RegionUtil.getRegions();
        entity.setProvince(regions[0]);
        entity.setCity(regions[1]);
        entity.setCounty(regions[2]);
        waterQualityMapper.updateById(entity);
    }

    @Override
    public WaterQuality get(String id) {
        Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
        WaterQuality wq = waterQualityMapper.selectById(id);
        wq.setWetlandName(wetlandNameMap.get(wq.getWetlandId()));
        return wq;
    }

    @Override
    public PageUtils<WaterQuality> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<WaterQuality> wqList = waterQualityMapper.listByParams(params);
        Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
        for (WaterQuality wq : wqList) {
            wq.setWetlandName(wetlandNameMap.get(wq.getWetlandId()));
        }
        return PageUtils.getPageUtils(new PageInfo<>(wqList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        List<WaterQuality> wqList = waterQualityMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(wqList)) {
            List<ExportWqBean> exportList = new ArrayList<>(wqList.size());
            Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
            wqList.forEach(o -> {
                ExportWqBean wq = new ExportWqBean();
                BeanUtils.copyProperties(o, wq);
                wq.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
//                wq.setWaterSupplyName("待前端定义好规则");
//                wq.setFlowOut("待前端定义好规则");
                exportList.add(wq);
            });
            try {
                ExportUtil.createExcel(ExportWqBean.class, exportList, response, "水质监测信息采集.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
