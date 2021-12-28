package com.sofn.agpjyz.service;


import com.sofn.agpjyz.vo.PlantUtilizationPurposeForm;
import com.sofn.agpjyz.vo.PlantUtilizationPurposeVo;

import java.util.List;

/**
 * 植物利用模用途服务接口
 */
public interface PlantUtilizationPurposeService {
    /**
     * 新增
     */
    PlantUtilizationPurposeVo save(PlantUtilizationPurposeForm form);

    /**
     * 根据植物利用ID删除
     */
    void deleteByUtilizationId(String utilizationId);

    /**
     * 根据植物利用ID查找
     */
    List<PlantUtilizationPurposeVo> listByUtilizationId(String utilizationId);
}
