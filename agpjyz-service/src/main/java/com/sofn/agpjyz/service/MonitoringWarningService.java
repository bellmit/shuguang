package com.sofn.agpjyz.service;


import com.sofn.agpjyz.model.MonitoringWarning;
import com.sofn.agpjyz.model.Threshold;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 监测预警模型构建与管理模块服务接口
 **/
public interface MonitoringWarningService {

    /**
     * 新增
     */
    MonitoringWarning save(MonitoringWarning entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(MonitoringWarning entity);

    /**
     * 详情
     */
    MonitoringWarning get(String id);

    /**
     * 分页查询
     */
    PageUtils<MonitoringWarning> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);
    /**
     *
     */
    MonitoringWarning listByParams(Map<String, Object> params);

}
