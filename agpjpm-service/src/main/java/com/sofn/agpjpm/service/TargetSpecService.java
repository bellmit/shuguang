package com.sofn.agpjpm.service;

import com.sofn.agpjpm.vo.DropDownWithLatinVo;
import com.sofn.agpjpm.vo.SpeciesMonitorForm;
import com.sofn.agpjpm.vo.SpeciesSurveyForm;
import com.sofn.agpjpm.vo.SpeciesSurveyVo;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 15:48
 */
public interface TargetSpecService {


    /**
     * 用于插入物种信息
     * @param speciesSurveyForm 调查页面的物种
     * @param speciesMonitorForm 监测页面的物种
     * @param sourceId
     */
     void save(SpeciesSurveyForm speciesSurveyForm, SpeciesMonitorForm speciesMonitorForm, String sourceId);

    /**
     *
     * @param sourceId 来源id
     * @param specSource 来源表
     * @param cls 返回类型
     * @param <T> 泛型
     * @return 返回当前souceid下的物种信息集合
     */

    <T>List<T> getBySourceId(String sourceId,String specSource,Class<T> cls);
    /**
     *
     * @param id 物种id
     * @return 物种信息以及物种的图片信息
     */

    Object getById(String id,String specSource);

    /**
     * 通过sourceId 批量删除 物种
     * @param sourceId
     */
    void deleteBySurveyId(String sourceId);

    /**
     *
     * @param id 目标物种id
     */
    void delete(String id);

    /**
     *
     * @param speciesSurveyForm
     * @param speciesMonitorForm
     */
    void update(SpeciesSurveyForm speciesSurveyForm, SpeciesMonitorForm speciesMonitorForm,String sourceId);

   List<DropDownWithLatinVo> getDropList();
   void updateBySourceId(List<SpeciesSurveyForm> speciesList,String id);
}
