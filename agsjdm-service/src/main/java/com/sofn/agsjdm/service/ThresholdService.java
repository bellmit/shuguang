package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.Threshold;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:33
 */
public interface ThresholdService {
    /**
     * 新增
     */
    Threshold save(Threshold entity);

    /**
     * 根据监测预警ID删除
     */
    void deleteByWarningId(String warningId);

    /**
     * 根据监测预警ID查找
     */
//    d95364e2d90d4d42be4da4a094e94caf
//    fc88d72379e04433be16520255a92a45
    List<Threshold> listByWarningId(String warningId);
}
