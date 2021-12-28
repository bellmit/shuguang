package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.FacilityMaintenanceExcel;
import com.sofn.agzirdd.model.FacilityMaintenance;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 基础设施维护接口
 */
public interface FacilityMaintenanceService extends IService<FacilityMaintenance> {

    /**
     * 删除信息
     */
    boolean deleteById(String id);

    /**
     * 获取满足条件的分页列表
     */
    PageUtils<FacilityMaintenance> getFacilityByPage(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 获取满足条件的导出数据
     * @param params 查询条件
     * @return List<FacilityMaintenanceExcel>
     */
    List<FacilityMaintenanceExcel> getFacilityMaintenanceLisToExport(Map<String,Object> params);


}
