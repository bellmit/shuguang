package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.PlantUtilization;
import com.sofn.agpjyz.vo.PlantUtilizationForm;
import com.sofn.agpjyz.vo.PlantUtilizationVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 植物利用模块服务接口
 */
public interface PlantUtilizationService {

    /**
     * 新增
     */
    PlantUtilizationVo save(PlantUtilizationForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(PlantUtilizationForm form);

    /**
     * 详情
     */
    PlantUtilizationVo get(String id);

    /**
     * 分页查询
     */
    PageUtils<PlantUtilizationVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);
}
