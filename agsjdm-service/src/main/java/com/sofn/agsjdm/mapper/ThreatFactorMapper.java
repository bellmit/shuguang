package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.ThreatFactor;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 15:20
 */
public interface ThreatFactorMapper extends BaseMapper<ThreatFactor> {
    List<ThreatFactor> listPage(Map map);
    ThreatFactor selectByWetlandId(String  wetlandId);
    ThreatFactor selectByWetlandId1(Map map);
}
