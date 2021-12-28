package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.EnvironmentalFactor;

import java.util.List;
import java.util.Map;

public interface EnvironmentalFactorCollectMapper extends BaseMapper<EnvironmentalFactor> {

    List<EnvironmentalFactor> listByParams(Map<String, Object> params);
}
