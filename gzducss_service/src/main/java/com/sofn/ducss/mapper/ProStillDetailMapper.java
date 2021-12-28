package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.vo.DataAnalysis.IndexDataVo;
import com.sofn.ducss.vo.DataAnalysisQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProStillDetailMapper extends BaseMapper<ProStillDetail> {

    List<ProStillDetail> getProStillDetail(String proStillId);

    Integer insertList(List<ProStillDetail> list);

    Integer updateList(List<ProStillDetail> list);

    Integer deleteByProStillId(String proStillId);

    List<ProStillDetail> getProStillDetailListByAreaId(@Param("areaId") String areaId, @Param("year") String year);

    List<ProStillDetail> getProStillDetailListByAreaId2(@Param("areaId") String areaId, @Param("year") String year,@Param("status") List<String>status);

    List<IndexDataVo> getListByCondition(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition2(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition3(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition4(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition5(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition6(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    /**
     * 数据分析-综合指标赋值操作--获取对应年份、区域的ProStillDetail
     *
     * @param year
     * @param areaId
     * @return
     */
    List<ProStillDetail> getPublicTableData(@Param("year") String year, @Param("areaId") String areaId);

    List<ProStillDetail> getListByYearAndAreaId(@Param("year") String year, @Param("areaId") String areaId, @Param("earlyRice") String earlyRice);

    ProStillDetail getProStillDetailListByAreaIdAndStrawType(@Param("areaId") String areaId, @Param("year") String year,@Param("strawType") String strawType);

    List<ProStillDetail> getProStillDetailListByAreaIdGroupByStrawType(@Param("areaId") String areaId, @Param("year") String year,@Param("strawType") String strawType);


    List<ProStillDetail> getProStillDetailListByAreaIdGroupByAreaId(@Param("areaIds") List<String> areaIds, @Param("year") String year,@Param("strawType") String strawType);

}