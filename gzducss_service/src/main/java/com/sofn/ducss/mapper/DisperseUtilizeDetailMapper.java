package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.vo.DataAnalysis.IndexDataVo;
import com.sofn.ducss.vo.DataAnalysisQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DisperseUtilizeDetailMapper extends BaseMapper<DisperseUtilizeDetail> {

    List<DisperseUtilizeDetail> getDisperseUtilizeDetail(String disperseUtilizeId);

    Integer insertList(List<DisperseUtilizeDetail> list);

    Integer updateList(List<DisperseUtilizeDetail> list);

    Integer deleteByDisperseUtilizeId(String id);

    // 计算秸秆产量
    List<DisperseUtilizeDetail> selectDetailByAreaId(@Param("areaId") String areaId, @Param("year") String year);

    // 计算秸秆产量带状态
    List<DisperseUtilizeDetail> selectDetailByAreaIdStatus(@Param("areaId") String areaId, @Param("year") String year,@Param("status") List<String> status);

    // 县级分散利用量数据导出
    List<DisperseUtilizeDetail> findDetailByAreaIdAndYear(@Param("areaId") String areaId,@Param("year") String year);
    // 调整县级分散利用量数据导出
    List<DisperseUtilizeDetail> findExportDetailByCondion(Map<String, Object> params);

    /**
     * 数据分析-条件查询详情（部级）
     *
     * @param dataAnalysisQueryVo
     * @return
     */
    List<IndexDataVo> getListByCondition(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition2(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition3(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition4(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition5(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    List<IndexDataVo> getListByCondition6(@Param("dataAnalysisQueryVo") DataAnalysisQueryVo dataAnalysisQueryVo);

    /**
     * 数据分析-综合指标赋值操作--获取对应年份、区域的DisperseUtilizeDetail
     *
     * @param year
     * @param areaId
     * @return
     */
    List<DisperseUtilizeDetail> getPublicTableData(@Param("year") String year, @Param("areaId") String areaId);

    Integer updateListByMe(List<DisperseUtilizeDetail> list);

    DisperseUtilizeDetail disAssignment(@Param("areaId") String areaId, @Param("year") String year, @Param("strawType") String strawType);
}