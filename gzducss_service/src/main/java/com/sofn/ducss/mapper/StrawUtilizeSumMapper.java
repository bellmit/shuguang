package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.vo.DateShow.ColumnPieChartVo;
import com.sofn.ducss.vo.StrawUtilizeResVo;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StrawUtilizeSumMapper extends BaseMapper<StrawUtilizeSum> {

    int deleteByPrimaryKey(Integer id);

    StrawUtilizeSum selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(StrawUtilizeSum record);

    void deleteStrawUtilizeSum(StrawUtilizeSum StrawUtilizeSum);

    List<StrawUtilizeResVo> selectStrawUtilize(@Param("childrenIds") String childrenIds, @Param("year") String year, @Param("status") String status);

    List<StrawUtilizeSumResVo> selectStrawUtilizeExamineSum(@Param("childrenIds") String childrenIds,@Param("year") String year,@Param("status") String status);

    List<StrawUtilizeSumResVo> selectStrawUtilizeExamineThisSum(@Param("areaId") String areaId, @Param("year") String year,@Param("status") String status);

    void insertBatchStrawUtilizeSum(List<StrawUtilizeSum> proList);

    List<StrawUtilizeSum> selectStrawUtilizeByAreaIds(@Param("childrenIds") List<String> childrenIds, @Param("year") String year);

    List<StrawUtilizeSum> sumStrawUtilizeSum(@Param("parentAreaId") String parentAreaId,
                                             @Param("year") String year, @Param("status") List<String> status,@Param("ids") List<String> ids);

    StrawUtilizeSum selectThanCountrySum(@Param("parentAreaId") String areaId, @Param("year") String year,
                                         @Param("status") List<String> status,@Param("ids") List<String> ids);

    void insertStrawUtilizeSumTotal(StrawUtilizeSum strawUtilizeSum);

    /**
     * 所选区域内不同秸秆类型汇总数据根据区域分组
     *
     * @param   year   年份
     * @param   areaCodes 区域集合
     * @param   status 状态集合
     * @param   dataType 数据类型
     * @param   strawType 数据类型
     *
     * @return   List<StrawUtilizeSumResVo> sum数组
     */
    List<ColumnPieChartVo> getStrawResourceByStrawTypeGroupByAreaId(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType,@Param("strawType") String strawType);
}