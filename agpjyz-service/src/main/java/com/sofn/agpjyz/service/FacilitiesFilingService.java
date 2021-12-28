package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.FacilitiesFiling;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 基础设施备案登记服务接口
 *
 * @Author yumao
 * @Date 2020/3/10 8:52
 **/
public interface FacilitiesFilingService {

    /**
     * 新增
     */
    FacilitiesFiling save(FacilitiesFiling entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(FacilitiesFiling entity);

    /**
     * 详情
     */
    FacilitiesFiling get(String id);

    /**
     * 分页查询
     */
    PageUtils<FacilitiesFiling> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 列表查询
     */
    List<FacilitiesFiling> list(Map<String, Object> params);
    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);


}
