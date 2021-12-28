package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agzirdd.excelmodel.FacilityMaintenanceExcel;
import com.sofn.agzirdd.mapper.FacilityMaintenanceMapper;
import com.sofn.agzirdd.model.FacilityMaintenance;
import com.sofn.agzirdd.service.FacilityMaintenanceService;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 基础设施维护接口实现类
 */
@Service
public class FacilityMaintenanceServiceImpl extends ServiceImpl<FacilityMaintenanceMapper, FacilityMaintenance> implements FacilityMaintenanceService {
    @Autowired
    private FacilityMaintenanceMapper facilityMaintenanceMapper;

    @Override
    public boolean deleteById(String id) {
        return facilityMaintenanceMapper.deleteById(id)>0;
    }

    @Override
    public PageUtils<FacilityMaintenance> getFacilityByPage(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FacilityMaintenance> facilityMaintenanceList = facilityMaintenanceMapper.findFacilityByCondition(params);

        PageInfo<FacilityMaintenance> pageInfo = new PageInfo<>(facilityMaintenanceList);
        PageUtils pageUtils = PageUtils.getPageUtils(pageInfo);
        return pageUtils;
    }

    @Override
    public List<FacilityMaintenanceExcel> getFacilityMaintenanceLisToExport(Map<String, Object> params) {
        List<FacilityMaintenance> fmList = facilityMaintenanceMapper.findFacilityByCondition(params);
        List<FacilityMaintenanceExcel> fmExcelList = new ArrayList<>();
        fmList.forEach(
                facilityMaintenance ->{
                    FacilityMaintenanceExcel fmExcel = new FacilityMaintenanceExcel();
                    BeanUtils.copyProperties(facilityMaintenance,fmExcel);
                    fmExcelList.add(fmExcel);
                }
        );

        return fmExcelList;
    }

}
