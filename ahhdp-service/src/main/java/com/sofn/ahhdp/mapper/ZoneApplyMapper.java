package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneApply;

import java.util.List;
import java.util.Map;

public interface ZoneApplyMapper extends BaseMapper<ZoneApply> {

    List<ZoneApply> listByParams(Map<String, Object> params);
}
