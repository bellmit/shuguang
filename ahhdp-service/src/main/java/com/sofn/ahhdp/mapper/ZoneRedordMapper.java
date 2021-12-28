package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.ZoneRecord;

import java.util.List;
import java.util.Map;

public interface ZoneRedordMapper extends BaseMapper<ZoneRecord> {

    List<ZoneRecord> listByParams(Map<String, Object> params);

    List<ZoneRecord> listByParamsForPublish(Map<String, Object> params);

    List<String> getYears();
}
