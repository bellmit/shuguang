package com.sofn.agpjpm.service;

import com.sofn.agpjpm.model.SoilType;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:52
 */
public interface SoilTypeService {
    void save(SoilType soilType,String surveyId);
    List<SoilType> getBySurveyId(String surveyId);
    void delete(String surveyId);
    void update(SoilType soilType,String surveyId);

}
