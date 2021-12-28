package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.PlantUtilization;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlantUtilizationMapper extends BaseMapper<PlantUtilization> {

    List<PlantUtilization> listByParams(Map<String, Object> params);

    void updateOther(@Param("id") String id, @Param("other") String other);
}
