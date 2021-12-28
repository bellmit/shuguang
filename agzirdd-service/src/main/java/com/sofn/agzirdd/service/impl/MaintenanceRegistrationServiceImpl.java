package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agzirdd.excelmodel.MaintenanceRegistrationExcel;
import com.sofn.agzirdd.mapper.MaintenanceRegistrationMapper;
import com.sofn.agzirdd.model.MaintenanceRegistration;
import com.sofn.agzirdd.service.MaintenanceRegistrationService;
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
public class MaintenanceRegistrationServiceImpl extends ServiceImpl<MaintenanceRegistrationMapper, MaintenanceRegistration> implements MaintenanceRegistrationService {
    @Autowired
    private MaintenanceRegistrationMapper maintenanceRegistrationMapper;

    @Override
    public boolean deleteById(String id) {
        return this.deleteById(id);
    }

    @Override
    public PageUtils<MaintenanceRegistration> getMaintenanceRegistrationByPage(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MaintenanceRegistration> facilityMaintenanceList = maintenanceRegistrationMapper.findMaintenanceRegistrationByCondition(params);

        PageInfo<MaintenanceRegistration> pageInfo = new PageInfo<>(facilityMaintenanceList);
        PageUtils pageUtils = PageUtils.getPageUtils(pageInfo);
        return pageUtils;
    }

    @Override
    public List<MaintenanceRegistrationExcel> getMaintenanceRegistrationLisToExport(Map<String, Object> params) {
        List<MaintenanceRegistration> mrList = maintenanceRegistrationMapper.findMaintenanceRegistrationByCondition(params);
        List<MaintenanceRegistrationExcel> mrExcelList = new ArrayList<>();
        mrList.forEach(
                MaintenanceRegistration ->{
                    MaintenanceRegistrationExcel mrExcel = new MaintenanceRegistrationExcel();
                    BeanUtils.copyProperties(MaintenanceRegistration,mrExcel);
                    mrExcelList.add(mrExcel);
                }
        );

        return mrExcelList;
    }
}
