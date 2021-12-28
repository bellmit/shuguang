package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.Zone;

import java.util.List;
import java.util.Map;

public interface ZoneMapper extends BaseMapper<Zone> {

    List<Zone> listByParams(Map<String, Object> params);
}
