package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DataAnalysisArea;
import com.sofn.ducss.vo.StrawUsageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public interface DataAnalysisAreaMapper extends BaseMapper<DataAnalysisArea> {
    int insert(DataAnalysisArea record);

    int insertSelective(DataAnalysisArea record);

    List<DataAnalysisArea> getList(@Param("paramMap") Map<String, Object> paramMap);

    List<DataAnalysisArea> getLists(@Param("paramMap") Map<String, Object> paramMap);

    Integer getListsForPage(@Param("paramMap") HashMap<String, Object> map);

    List<DataAnalysisArea> getListsForAll(@Param("paramMap") HashMap<String, Object> map);

    List<DataAnalysisArea> getListForAll(@Param("paramMap") HashMap<String, Object> map);

    List<DataAnalysisArea> getListByNoLimit(@Param("paramMap") HashMap<String, Object> map);

    Integer insertList(List<DataAnalysisArea> list);

    List<StrawUsageVo> findDataByAreaIdsAndYears(@Param("areaIds") List<String> areaIds, @Param("years") List<String> years);

    /***
     * 高州市统计
     * @return
     */
    Map<String,String> getProviceDataAllSumSpecial(@Param("year")String year, @Param("strawType") String strawType);


    String getProviceDataAllSum(@Param("year")String year, @Param("keyword")String keyword,@Param("strawType") String strawType);

}