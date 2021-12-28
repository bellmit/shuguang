package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import com.sofn.agzirdd.vo.SpeciesInvestigationForm;
import com.sofn.agzirdd.vo.WelcomeMapVo;
import com.sofn.agzirdd.vo.WelcomeTableDBVo;
import com.sofn.agzirdd.vo.WelcomeTableVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-调查基本信息mapper
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
@Mapper
public interface SpeciesInvestigationMapper extends BaseMapper<SpeciesInvestigation> {

    /**
     * 获取满足条件的物种调查模块-调查基本信息List
     * @param params 表格编号 调查人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpeciesInvestigation>
     */
    List<SpeciesInvestigation> getSpeciesInvestigationByCondition(Map<String,Object> params);

    /**
     * 获取满足条件的物种调查模块-调查基本信息List
     * @param params 表格编号 调查人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpeciesInvestigation>
     */
    List<SpeciesInvestigation> getSpeciesInvestigationByParam(Map<String,Object> params);

    /**
     * 获取满足条件的物种调查模块-调查基本信息FormList
     * @param params 表格编号 调查人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpeciesInvestigationForm>
     */
    List<SpeciesInvestigationForm> getSpeciesInvestigationForm(Map<String,Object> params);

    /**
     * 获取指定id的物种调查模块-调查基本信息
     * @param id id
     * @return SpeciesInvestigation
     */
    SpeciesInvestigation getSpeciesInvestigationById(String id);

    /**
     * 修改物种调查模块-调查基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 查询欢迎页地图物种调查数据
     * @param params
     * @return
     */
    List<WelcomeMapVo> getWelcomeMapInfo(Map<String, Object> params);

    /**
     * 更新之前的数据为旧数据
     * @param provinceId
     * @param cityId
     * @param countyId
     */
    void updateStatusToOld(@Param("provinceId") String provinceId, @Param("cityId") String cityId, @Param("countyId") String countyId
            , @Param("belongYear") String belongYear, @Param("roleCode") String roleCode);

    /**
     * 更新新数据的is_new状态码
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param belongYear
     * @param roleCode
     */
    void updateStatusToNew(@Param("provinceId") String provinceId, @Param("cityId") String cityId, @Param("countyId") String countyId
            , @Param("belongYear") String belongYear, @Param("roleCode") String roleCode);

    /**
     * 查询调查，发生面积，大屏边图数据
     * @param params
     * @return
     */
    List<WelcomeTableDBVo> selectDCFSMJInfo(Map<String, Object> params);

    /**
     * 查询调查，发生面积，大屏边图数据
     * @param params
     * @return
     */
    List<WelcomeTableDBVo> selectDCZQSLInfo(Map<String, Object> params);

    /**
     * 获取默认调查物种名-发生面积
     * @param year
     * @return
     */
    String getDefaultFSMJSpName(@Param("year") String year);

    /**
     * 获取默认调查物种名-种群数量
     * @param year
     * @return
     */
    String getDefaultZQSLSpName(@Param("year") String year);
}