package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.ThreatFactor;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 威胁因素基础信息收集模块服务接口
 *
 * @Author yumao
 * @Date 2020/3/4 9:30
 **/
public interface ThreatFactorCollectService {


    /**
     * 新增
     */
    ThreatFactor save(ThreatFactor form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(ThreatFactor entity);

    /**
     * 详情
     */
    ThreatFactor get(String id);

    /**
     * 分页查询
     */
    PageUtils<ThreatFactor> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);
    ThreatFactor getNum(String id);
}
