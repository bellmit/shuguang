package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.sofn.ahhdp.model.FarmRecord;


import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 14:20
 */
public interface FarmRecordMapper extends BaseMapper<FarmRecord> {
    List<FarmRecord> listByParams(Map<String, Object> params);

    List<FarmRecord> listByParamsForPublish(Map<String, Object> params);

    List<String> getYears();
}
