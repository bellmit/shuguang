package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.agsjdm.mapper.*;
import com.sofn.agsjdm.model.*;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.DropDownVo;
import com.sofn.agsjdm.vo.InformationManagementFrom;
import com.sofn.agsjdm.vo.InformationManagementTableVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 9:50
 **/
@Service
public class InformationManagementServiceImpl implements InformationManagementService {
    @Resource
    private InformationManagementMapper informationManagementMapper;
    @Resource
    private BasicsCollectionMapper basicsCollectionMapper;
    @Resource
    private BiomonitoringMapper biomonitoringMapper;
    @Resource
    private EnvironmentalFactorCollectMapper environmentalFactorCollectMapper;
    @Resource
    private FacilityMaintenanceMapper facilityMaintenanceMapper;
    @Resource
    private MonitoringWarningMapper monitoringWarningMapper;
    @Resource
    private ThreatFactorMapper threatFactorMapper;
    @Resource
    private WaterQualityMapper waterQualityMapper;

    @Override
    public void insertIm(InformationManagementFrom im) {
        InformationManagementModel imModel = new InformationManagementModel();
        BeanUtils.copyProperties(im, imModel);
        //插入前
        imModel.preInsert();
        //设置操作人
        imModel.setOperator(UserUtil.getLoginUserName());
        int insert = informationManagementMapper.insert(imModel);
        if (insert != 1) {
            throw new SofnException("插入失败");
        }
    }

    @Override
    public PageUtils<InformationManagementTableVo> listPage(Map<String, Object> map, int pageNo, int pageSize) {
        //设置偏移量
        PageHelper.offsetPage(pageNo, pageSize);
        QueryWrapper<InformationManagementModel> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("del_flag", BoolUtils.N)
                .like(Objects.nonNull(map.get("wetName")), "wet_areas_name", map.get("wetName"))
                .like(Objects.nonNull(map.get("wetCode")), "wet_areas_code", map.get("wetCode"))
                .like(Objects.nonNull(map.get("secondBasin")), "the_secondary_basin", map.get("secondBasin"))
                .between(Objects.nonNull(map.get("startTime")) && Objects.nonNull(map.get("endTime")), "create_time", (Date) map.get("startTime"), (Date) map.get("endTime"))
                .orderByDesc("update_time");
        List<InformationManagementModel> list = informationManagementMapper.selectList(queryWrapper);
        PageInfo<InformationManagementModel> informationManagementModelPageInfo = new PageInfo<>(list);
        ArrayList<InformationManagementTableVo> tableVos = new ArrayList<>(list.size());
        for (InformationManagementModel informationManagementModel : list) {
            InformationManagementTableVo informationManagementTableVo = new InformationManagementTableVo();
            BeanUtils.copyProperties(informationManagementModel, informationManagementTableVo);
            tableVos.add(informationManagementTableVo);
        }
        PageInfo<InformationManagementTableVo> tableVoPageInfo = new PageInfo<>(tableVos);
        tableVoPageInfo.setPageSize(pageSize);
        tableVoPageInfo.setTotal(informationManagementModelPageInfo.getTotal());
        tableVoPageInfo.setPageNum(tableVoPageInfo.getPageNum());
        return PageUtils.getPageUtils(tableVoPageInfo);
    }

    @Override
    public List<DropDownVo> listForPointCollect(String regionLastCode) {
        QueryWrapper<InformationManagementModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "wet_areas_name");
        queryWrapper.eq("del_flag", BoolUtils.N)
                .and(StringUtils.isNotBlank(regionLastCode), wrapper ->
                wrapper.eq("province_code", regionLastCode).or().eq("city_code", regionLastCode).
                        or().eq("area_code", regionLastCode));
        List<InformationManagementModel> list = informationManagementMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<DropDownVo> result = Lists.newArrayListWithCapacity(list.size());
            for (InformationManagementModel model : list) {
                result.add(new DropDownVo(model.getId(), model.getWetAreasName()));
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Map<String, String> getPointMap() {
        List<DropDownVo> list = this.listForPointCollect(RegionUtil.getRegionLastCode());
        if (!CollectionUtils.isEmpty(list)) {
            return list.stream().collect(Collectors.toMap(DropDownVo::getId, DropDownVo::getName));
        }
        return Collections.EMPTY_MAP;
    }

    @Override
    public InformationManagementFrom searchByid(String id) {
        InformationManagementModel informationManagementModel = informationManagementMapper.selectById(id);
        InformationManagementFrom informationManagementFrom = new InformationManagementFrom();
        BeanUtils.copyProperties(informationManagementModel, informationManagementFrom);
        return informationManagementFrom;
    }

    @Override
    public void delete(String id) {
        //每个表都判断是否用到了
        //基础信息收集模块
        QueryWrapper<BasicsCollection> basicsCollectionQueryWrapper = new QueryWrapper<>();
        basicsCollectionQueryWrapper.eq("wetland_id", id);
        if (basicsCollectionMapper.selectList(basicsCollectionQueryWrapper) != null) {
            throw new SofnException("删除失败,基础信息收集模块用到了该湿地数据");
        }
        //生物监测信息采集模块接口
        QueryWrapper<Biomonitoring> biomonitoringMapperQueryWrapper = new QueryWrapper<>();
        biomonitoringMapperQueryWrapper.eq("wetland_id", id);
        if (biomonitoringMapper.selectList(biomonitoringMapperQueryWrapper) != null) {
            throw new SofnException("删除失败,基础信息收集模块用到了该湿地数据");
        }
        //环境因子监测信息采集模块
        QueryWrapper<EnvironmentalFactor> environmentalFactorQueryWrapper = new QueryWrapper<>();
        environmentalFactorQueryWrapper.eq("wetland_id", id);
        if (environmentalFactorCollectMapper.selectList(environmentalFactorQueryWrapper) != null) {
            throw new SofnException("删除失败,环境因子监测信息采集模块用到了该湿地数据");
        }
        //设施监控管理模块
        QueryWrapper<FacilityMaintenance> facilityMaintenanceQueryWrapper = new QueryWrapper<>();
        facilityMaintenanceQueryWrapper.eq("wetland_id", id);
        if (facilityMaintenanceMapper.selectList(facilityMaintenanceQueryWrapper) != null) {
            throw new SofnException("删除失败,设施监控管理模块用到了该湿地数据");
        }
        //监测预警模型构建与管理模块模块
        QueryWrapper<MonitoringWarning> monitoringWarningQueryWrapper = new QueryWrapper<>();
        monitoringWarningQueryWrapper.eq("wetland_id", id);
        if (monitoringWarningMapper.selectList(monitoringWarningQueryWrapper) != null) {
            throw new SofnException("删除失败,监测预警模型构建与管理模块模块用到了该湿地数据");
        }
        //威胁因素基础信息收集模块
        QueryWrapper<ThreatFactor> threatFactorQueryWrapper = new QueryWrapper<>();
        threatFactorQueryWrapper.eq("wetland_id", id);
        if (threatFactorMapper.selectList(threatFactorQueryWrapper) != null) {
            throw new SofnException("删除失败,威胁因素基础信息收集模块用到了该湿地数据");
        }
        //水质监测信息采集模块
        QueryWrapper<WaterQuality> waterQualityQueryWrapper = new QueryWrapper<>();
        waterQualityQueryWrapper.eq("wetland_id", id);
        if (waterQualityMapper.selectList(waterQualityQueryWrapper) != null) {
            throw new SofnException("删除失败,水质监测信息采集模块用到了该湿地数据");
        }
        InformationManagementModel info = new InformationManagementModel();
        info.setId(id);
        info.setDelFlag("Y");
        info.setUpdateTime(new Date());
        info.setUpdateUserId(UserUtil.getLoginUserId());
        int i = informationManagementMapper.updateById(info);
        if (i != 1) {
            throw new SofnException("删除失败");
        }

    }

    @Override
    public void updateIm(InformationManagementFrom im) {
        //判断是否有主键
        if (StringUtils.isBlank(im.getId())) {
            throw new SofnException("未携带主键");
        }
        //copy属性
        InformationManagementModel informationManagementModel = new InformationManagementModel();
        BeanUtils.copyProperties(im, informationManagementModel);
        informationManagementModel.preUpdate();
        int i = informationManagementMapper.updateById(informationManagementModel);
        if (i != 1) {
            throw new SofnException("更新失败");
        }
    }

}
