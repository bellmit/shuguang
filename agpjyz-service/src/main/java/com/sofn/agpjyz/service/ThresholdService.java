package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.Threshold;

import java.util.List;

/**
 * 阀值服务接口
 **/
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
