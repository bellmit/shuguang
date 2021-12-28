package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.ThreatFactor;

import java.util.List;
import java.util.Map;

public interface ThreatFactorCollectMapper extends BaseMapper<ThreatFactor> {

    List<ThreatFactor> listByParams(Map<String, Object> params);
    ThreatFactor getNum(String protectId);
    void updateExcavation(String id);
}
