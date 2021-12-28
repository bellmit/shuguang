package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.Biomonitoring;

import java.util.List;
import java.util.Map;

public interface BiomonitoringMapper extends BaseMapper<Biomonitoring> {

    List<String> getYears();

    List<Biomonitoring> listByParams(Map<String, Object> params);
    Biomonitoring getByParams(Map map);
}
