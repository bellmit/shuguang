package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.WaterQuality;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 水质监测信息服务接口
 */
public interface WaterQualityService {
    /**
     * 新增
     */
    WaterQuality save(WaterQuality entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(WaterQuality entity);

    /**
     * 详情
     */
    WaterQuality get(String id);

    /**
     * 分页查询
     */
    PageUtils<WaterQuality> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);
}
