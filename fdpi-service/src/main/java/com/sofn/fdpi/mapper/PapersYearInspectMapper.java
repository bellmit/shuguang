package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PapersYearInspect;
import com.sofn.fdpi.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 证书年审表
 */
public interface PapersYearInspectMapper extends BaseMapper<PapersYearInspect> {

    List<PapersYearInspectVo> list(Map<String,Object> map);

    List<PapersYearInspectVo> listByParams(Map<String,Object> map);

    /**
     * 证书年审中修改/详情-企业信息
     * @param inspectId 证书年审id
     * @return 对象
     */
    PapersYearInspectViewVo getInspectById(@Param("inspectId")String inspectId);
    /**
     * 证书年审中新增-企业信息
     * @param compId 企业编号
     * @return 对象
     */
    PapersYearInspectViewVo getCompanyByComId(@Param("compId")String compId);
    /**
     * 证书年审中修改/详情-证件物种信息
     * @param inspectId  证书年审id
     * @return 对象
     */
    List<SpecieVoInPapersYear> listSpeciesById(@Param("inspectId")String inspectId);
    /**
     * 证书年审中新增-证件物种信息
     * @param compId  企业编号
     * @return 对象
     */
    List<SpecieVoInPapersYear> listSpeciesByCompId(@Param("compId") String compId);

    /**
     * 年审/企业明细中获取年审记录历史
     * @param map 查询条件
     * @return 列表
     */
    List<PapersYearInspectHistoryVo> listForInspectHistory(Map<String,Object> map);

    /**
     * 证书年审中新增-物种信息列表
     * @param compId  企业编号
     * @return 对象
     */
    List<SpecieVoInPapersYear> listSpeciesByCompIdNew(@Param("compId") String compId);
    /**
     * 证书年审中新增-证件信息列表
     * @param compId  企业编号
     * @return 对象
     */
    List<PapersVoInPapersYear> listPapersInYearByCompId(@Param("compId") String compId);

    /**
     * 证书年审中修改/详情-物种信息列表
     * @param inspectId  证书年审id
     * @return 对象
     */
    List<SpecieVoInPapersYear> listSpeciesByIdNew(@Param("inspectId")String inspectId);

    /**
     * 证书年审中修改/详情-物种信息列表
     * @param inspectId  证书年审id
     * @return 对象
     */
    List<PapersVoInPapersYear> listPapersInYearById(@Param("inspectId")String inspectId);

    String getTodayMaxApplyNum(String todayStr);
}
