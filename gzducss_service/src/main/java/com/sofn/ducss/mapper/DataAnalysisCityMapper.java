package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DataAnalysisCity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface DataAnalysisCityMapper extends BaseMapper<DataAnalysisCity> {
    //int insert(DataAnalysisCity record);

    int insertSelective(DataAnalysisCity record);

    List<DataAnalysisCity> getList(@Param("paramMap") Map<String, Object> paramMap);
    List<DataAnalysisCity> getLists(@Param("paramMap") Map<String, Object> paramMap);

    Integer getListsForPage(@Param("paramMap")HashMap<String, Object> map);

    List<DataAnalysisCity> getListsForAll(@Param("paramMap")HashMap<String, Object> map);

    List<DataAnalysisCity> getListForAll(@Param("paramMap")HashMap<String, Object> map);

    List<DataAnalysisCity> getListByNoLimit(@Param("paramMap")HashMap<String, Object> map);
}