package com.sofn.agpjyz.service;


import com.sofn.agpjyz.model.FacilityMaintenance;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 基础设施维护情况备案登记服务接口
 *
 * @Author yumao
 * @Date 2020/3/12 9:22
 **/
public interface FacilityMaintenanceService {
    /**
     * 新增
     */
    FacilityMaintenance save(FacilityMaintenance entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(FacilityMaintenance entity);

    /**
     * 详情
     */
    FacilityMaintenance get(String id);

    /**
     * 分页查询
     */
    PageUtils<FacilityMaintenance> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);
}
