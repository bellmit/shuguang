package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.MaintenanceRegistrationExcel;
import com.sofn.agzirdd.model.MaintenanceRegistration;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 基础设施维护备案记录接口
 */
public interface MaintenanceRegistrationService extends IService<MaintenanceRegistration> {

    /**
     * 删除信息
     */
    boolean deleteById(String id);

    /**
     * 获取满足条件的分页列表
     */
    PageUtils<MaintenanceRegistration> getMaintenanceRegistrationByPage(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 获取满足条件的导出数据
     * @param params 查询条件
     * @return List<MaintenanceRegistrationExcel>
     */
    List<MaintenanceRegistrationExcel> getMaintenanceRegistrationLisToExport(Map<String,Object> params);


}
