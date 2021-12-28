package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.FarmRecord;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:25
 */
public interface FarmRecordService {
    /**
     * 新增
     */
    void save(FarmRecord entity);

    /**
     * 根据code查询最后一条数据
     */
    FarmRecord getLastChangeByCode(String code);

    /**
     * 分页查询变更记录
     */
    PageUtils<FarmRecord> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 分页查询变更记录(发布模块)
     */
    PageUtils<FarmRecord> listByParamsForPublish(Map<String, Object> params, Integer pageNo, Integer pageSize);

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
