package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneRecord;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ZoneRecordService {

    /**
     * 新增
     */
    void save(ZoneRecord entity);

    /**
     * 根据code查询最后一条数据
     */
    ZoneRecord getLastChangeByCode(String code);

    /**
     * 分页查询变更记录
     */
    PageUtils<ZoneRecord> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 分页查询变更记录(发布模块)
     */
    PageUtils<ZoneRecord> listByParamsForPublish(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 获取年份
     */
    List<DropDownVo> getYears();

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    /**
     * 导出名称变更数据
     */
    void exportForName(Map<String, Object> params, HttpServletResponse response);

    /**
     * 导出单位变更数据
     */
    void exportForCompany(Map<String, Object> params, HttpServletResponse response);

    /**
     * 导出范围变更数据
     */
    void exportForRange(Map<String, Object> params, HttpServletResponse response);
}
