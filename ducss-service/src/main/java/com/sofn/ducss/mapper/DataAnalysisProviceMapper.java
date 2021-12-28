package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DataAnalysisProvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sofn.ducss.vo.DataAnalyVo;
import org.apache.ibatis.annotations.Param;

public interface DataAnalysisProviceMapper extends BaseMapper<DataAnalysisProvice> {

    int insertSelective(DataAnalysisProvice record);

    List<DataAnalysisProvice> listByYearAndProvinceIdsAndTotolRateTooMuch(@Param("year") String year, @Param("provinceIds") List<String> provinceIds);

    List<DataAnalysisProvice> getList(@Param("paramMap") Map<String, Object> paramMap);

    List<DataAnalysisProvice> getLists(@Param("paramMap") Map<String, Object> paramMap);

    List<DataAnalysisProvice> getListByNoLimit(@Param("paramMap") Map<String, Object> paramMap);

    Integer getListsForPage(@Param("paramMap") HashMap<String, Object> map);

    List<DataAnalysisProvice> getListsForAll(@Param("paramMap") HashMap<String, Object> map);

    List<DataAnalysisProvice> getListForAll(@Param("paramMap") HashMap<String, Object> map);


    /***
     * 全部作物统计
     * @param year
     * @param regionCode
     * @return
     */
    // String getProviceDataListSum(@Param("year")String year, @Param("regionCode")String regionCode, @Param("keyword")String keyword,@Param("strawType") String strawType);

    /***
     * 全国数据统计
     * @param year
     * @param keyword
     * @param strawType
     * @return
     */
    String getProviceDataAllSum(@Param("year") String year, @Param("keyword") String keyword, @Param("strawType") String strawType);

    Map<String, String> getProviceDataAllSumSpecial(@Param("year") String year, @Param("strawType") String strawType);

    String getSixProviceDataSum(@Param("year") String year, @Param("regionCode") List<String> regionCode, @Param("keyword") String keyword, @Param("strawType") String strawType);

    Map<String, String> getSixProviceDataSumSpecial(@Param("year") String year, @Param("regionCode") List<String> regionCode, @Param("strawType") String strawType);

    List<DataAnalyVo> getProviceDataAllSumss(@Param("yearList") List<String> yearList, @Param("regionCode") String regionCode);

    void updateTotolRateByYearAndStrawTypeAndProviceId(@Param("year") String year, @Param("strawType") String strawType,
                                                       @Param("proviceId") String proviceId, @Param("provice") DataAnalysisProvice provice);

}