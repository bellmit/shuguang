package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.ThreatFactor;
import com.sofn.agsjdm.vo.ThreatFactorForm;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 15:23
 */
public interface ThreatFactorService {

    /**
     * 插入
     * @param ba
     *
     */
    void insert(ThreatFactorForm ba);

    /**
     * 修改
     * @param ba
     */
    void update(ThreatFactorForm ba);

    /**
     * 获取
     * @param id 主键
     * @return 基础信息
     */
    ThreatFactor get(String id);

    /**
     *
     * @param wetlandId
     * @return
     */
    ThreatFactor getThreat(String wetlandId);
    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     *
     */
    PageUtils<ThreatFactor> list(Map map, int pageNo, int pageSize);

    void export(Map<String, Object> params, HttpServletResponse response);
}
