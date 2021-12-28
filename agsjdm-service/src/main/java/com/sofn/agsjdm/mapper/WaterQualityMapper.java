package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.WaterQuality;

import java.util.List;
import java.util.Map;

public interface WaterQualityMapper extends BaseMapper<WaterQuality> {

    List<WaterQuality> listByParams(Map<String, Object> params);
}
