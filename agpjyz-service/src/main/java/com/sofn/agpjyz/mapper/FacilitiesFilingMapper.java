package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.FacilitiesFiling;

import java.util.List;
import java.util.Map;

public interface FacilitiesFilingMapper extends BaseMapper<FacilitiesFiling> {

    List<FacilitiesFiling> listByParams(Map<String, Object> params);
}
