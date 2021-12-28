package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.mapper.FacilityMaintenanceMapper;
import com.sofn.agsjdm.model.FacilityMaintenance;
import com.sofn.agsjdm.service.FacilityMaintenanceService;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.AgsjdmRedisUtils;
import com.sofn.agsjdm.util.ExportUtil;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.exportBean.FacilityMainExcel;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 9:23
 */
@Service("facilityMaintenance")
public class FacilityMaintenanceServiceImpl implements FacilityMaintenanceService {
    @Resource
    private FacilityMaintenanceMapper fMapper;
    @Resource
    private InformationManagementService informationManagementService;

    /**
     * 插入
     *
     * @param ba
     */
    @Override
    public void insert(FacilityMaintenance ba) {
        //防止重复提交
        AgsjdmRedisUtils.checkReSubmit("FacilityMaintenanceSave", 5L);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        //防止重复录入
        String wetlandId = ba.getWetlandId();
        QueryWrapper<FacilityMaintenance> wrapper = new QueryWrapper<FacilityMaintenance>();
        wrapper.eq("wetland_id", wetlandId);
        if (fMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区已经录入信息");
        }

        ba.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        ba.setOperator(operator);
        ba.setOperatorTime(new Date());
        String[] regions = RegionUtil.getRegions();
        ba.setProvince(regions[0]);
        ba.setCity(regions[1]);
        ba.setCounty(regions[2]);
        fMapper.insert(ba);
    }

    /**
     * 修改
     *
     * @param ba
     */
    @Override
    public void update(FacilityMaintenance ba) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        if (Objects.isNull(fMapper.selectById(ba.getId()))) {
            throw new SofnException("待修改数据不存在");
        }
        QueryWrapper<FacilityMaintenance> wrapper = new QueryWrapper<FacilityMaintenance>();
        wrapper.eq("wetland_id", ba.getWetlandId());
        wrapper.ne("id", ba.getId());
        if (fMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区已经录入信息");
        }
        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        ba.setOperatorTime(new Date());
        ba.setOperator(operator);
        String[] regions = RegionUtil.getRegions();
        ba.setProvince(regions[0]);
        ba.setCity(regions[1]);
        ba.setCounty(regions[2]);
        fMapper.updateById(ba);
    }

    /**
     * 获取
     *
     * @param id 主键
     * @return 基础信息
     */
    @Override
    public FacilityMaintenance get(String id) {
        FacilityMaintenance facilityMaintenance = fMapper.selectById(id);
        Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
        facilityMaintenance.setWetlandName(wetlandNameMap.get(facilityMaintenance.getWetlandId()));
        return facilityMaintenance;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        fMapper.deleteById(id);
    }

    /**
     * @param map
     * @param pageNo
     * @param pageSize
     */
    @Override
    public PageUtils<FacilityMaintenance> list(Map map, int pageNo, int pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<FacilityMaintenance> basicsCollections = fMapper.listPage(map);
        Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
        for (FacilityMaintenance bc :
                basicsCollections) {
            bc.setWetlandName(wetlandNameMap.get(bc.getWetlandId()));
        }
        PageInfo<FacilityMaintenance> basicsCollectionsPageInfo = new PageInfo<>(basicsCollections);
        return PageUtils.getPageUtils(basicsCollectionsPageInfo);
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        List<FacilityMaintenance> tsList = fMapper.listPage(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<FacilityMainExcel> exportList = new ArrayList<>(tsList.size());
            Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
            tsList.forEach(o -> {
                FacilityMainExcel etb = new FacilityMainExcel();
                BeanUtils.copyProperties(o, etb);
                etb.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(FacilityMainExcel.class, exportList, response, "设施监控管理.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
