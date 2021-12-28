package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.FacilityMaintenance;

import java.util.List;
import java.util.Map;

public interface FacilityMaintenanceMapper extends BaseMapper<FacilityMaintenance> {

    List<FacilityMaintenance> listByParams(Map<String, Object> params);
}

