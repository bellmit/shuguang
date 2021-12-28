package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.MonitoringWarning;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:22
 */
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

