package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.FacilityMaintenance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FacilityMaintenanceMapper extends BaseMapper<FacilityMaintenance> {
    int deleteByPrimaryKey(String id);

    int updateByPrimaryKey(FacilityMaintenance record);

    List<FacilityMaintenance> findFacilityByCondition(Map<String,Object> params);

}