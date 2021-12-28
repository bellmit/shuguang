package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.Source;
import com.sofn.agpjyz.vo.TrendVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SourceMapper extends BaseMapper<Source> {

    List<Source> listByParams(Map<String, Object> params);

    void updateFeatures(@Param("id") String id, @Param("features") String features);

    void updateCharacteristic(@Param("id") String id, @Param("characteristic") String characteristic);

    void updateThreaten(@Param("id") String id, @Param("threaten") String threaten);

    void updateProtectionUtilization(@Param("id") String id, @Param("protectionUtilization") String protectionUtilization);

    void updateSuggest(@Param("id") String id, @Param("suggest") String suggest);

    List<Source> listSpecName(Map<String, Object> params);

    List<TrendVo> listTrend(Map<String, Object> params);
}
