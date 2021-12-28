package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.EnvironmentalFactor;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 环境因子监测信息采集模块服务接口
 *
 **/
public interface EnvironmentalFactorCollectService {


    /**
     * 新增
     */
    EnvironmentalFactor save(EnvironmentalFactor entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(EnvironmentalFactor entity);

    /**
     * 详情
     */
    EnvironmentalFactor get(String id);

    /**
     * 分页查询
     */
    PageUtils<EnvironmentalFactor> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);
}
