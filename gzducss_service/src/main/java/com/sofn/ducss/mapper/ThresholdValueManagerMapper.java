package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.ThresholdValueManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThresholdValueManagerMapper extends BaseMapper<ThresholdValueManager> {


    /**
     * 批量保存阈值
     * @param thresholdValueManagers   阈值管理
     */
    void batchSaveThresholdValue(@Param("thresholdValueManagers") List<ThresholdValueManager> thresholdValueManagers);

}
