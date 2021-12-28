package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.BasicsCollection;
import com.sofn.agsjdm.model.FacilityMaintenance;
import com.sofn.agsjdm.vo.BasicsCollectionForm;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 9:23
 */
public interface FacilityMaintenanceService {
    /**
     * 插入
     * @param ba
     *
     */
    void insert(FacilityMaintenance ba);

    /**
     * 修改
     * @param ba
     */
    void update(FacilityMaintenance ba);

    /**
     * 获取
     * @param id 主键
     * @return 基础信息
     */
    FacilityMaintenance get(String id);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     *
     */
    PageUtils<FacilityMaintenance> list(Map map, int pageNo, int pageSize);

    void export(Map<String, Object> params, HttpServletResponse response);
}
