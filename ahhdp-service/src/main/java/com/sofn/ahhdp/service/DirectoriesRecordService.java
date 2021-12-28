package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 11:38
 */
public interface DirectoriesRecordService {

    /**
     * 新增
     */
    void save(DirectoriesRecord entity);

    /**
     * 根据code查询最后一条数据
     */
    DirectoriesRecord getLastChangeByCode(String code);

    /**
     * 分页查询变更记录
     */
    PageUtils<DirectoriesRecord> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 分页查询变更记录(发布模块)
     */
    PageUtils<DirectoriesRecord> listByParamsForPublish(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 获取年份
     */
    List<DropDownVo> getYears();

    /**
     * 导出名称变更数据
     */
    void exportForName(Map<String, Object> params, HttpServletResponse response);

    /**
     * 导出单位变更数据
     */
    void exportForCompany(Map<String, Object> params, HttpServletResponse response);
}
