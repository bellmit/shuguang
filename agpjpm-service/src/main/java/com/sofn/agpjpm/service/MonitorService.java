package com.sofn.agpjpm.service;

import com.sofn.agpjpm.vo.MonitorForm;
import com.sofn.agpjpm.vo.MonitorVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 监测表接口
 */
public interface MonitorService {

    /**
     * 新增
     */
    MonitorVo save(MonitorForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(MonitorForm form);

    /**
     * 详情
     */
    MonitorVo get(String id);


    /**
     * 分页查询
     */
    PageUtils<MonitorVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 根据模板导出
     */
    void exportByTemplate(Map<String, Object> params, HttpServletResponse response);
}
