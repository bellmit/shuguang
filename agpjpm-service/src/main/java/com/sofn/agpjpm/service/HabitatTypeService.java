package com.sofn.agpjpm.service;

import com.sofn.agpjpm.model.HabitatType;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:55
 */
public interface HabitatTypeService {
    void save(HabitatType habitatType, String surveyId);
    List<HabitatType> getBySurveyId(String surveyId);
    void delete(String surveyId);
    void update(HabitatType habitatType,String surveyId);
}
