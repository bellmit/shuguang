package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.vo.AggregateMainUtilizeQueryVo;
import com.sofn.ducss.vo.DataAnalysis.IndexDataVo;
import com.sofn.ducss.vo.DataAnalysisQueryVo;
import com.sofn.ducss.vo.MainUtilizeResVo;
import com.sofn.ducss.vo.StrawUtilizeCombinVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StrawUtilizeDetailMapper extends BaseMapper<StrawUtilizeDetail> {

    List<StrawUtilizeDetail> getStrawUtilizeDetail(String strawUtilizeId);

    Integer insertList(List<StrawUtilizeDetail> list);

    Integer updateList(List<StrawUtilizeDetail> list);

    Integer deleteByStrawUtilizeId(String id);

    List<StrawUtilizeDetail> selectDetailSumByAreaId(@Param("areaId") String areaId, @Param("year") String year);
    List<StrawUtilizeDetail> selectDetailSumByAreaIdStatus(@Param("areaId") String areaId, @Param("year") String year,@Param("status") List<String> status);

    List<StrawUtilizeCombinVo> selectCombinVoByCondition(Map<String, Object> params);

    List<MainUtilizeResVo> selectMainUtilizeInfoByUtilizeIds(@Param("qvo") AggregateMainUtilizeQueryVo vo, @Param("utilizeIds") List<String> utilizeIds);

    List<MainUtilizeResVo> selectMainUtilizeInfoByUtilizeIdsPage(@Param("qvo") AggregateMainUtilizeQueryVo vo, @Param("utilizeIds") List<String> utilizeIds);

    List<IndexDataVo> getListByCondition(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition2(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition3(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition4(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition5(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition6(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    /**
     * 数据分析-综合指标赋值操作--获取对应年份、区域的StrawUtilizeDetail
     *
     * @param year
     * @param areaId
     * @return
     */
    List<StrawUtilizeDetail> getPublicTableData(@Param("year") String year, @Param("areaId") String areaId);

    int getCountByCondition(@Param("year") String year,@Param("provinceId") String provinceId,@Param("marketEnt") int marketEnt);

}