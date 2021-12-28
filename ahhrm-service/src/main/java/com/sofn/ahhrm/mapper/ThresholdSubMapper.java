package com.sofn.ahhrm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhrm.model.ThresholdSub;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:24
 */
public interface ThresholdSubMapper extends BaseMapper<ThresholdSub> {
    int del(@Param("thresholdId") String thresholdId);

}
