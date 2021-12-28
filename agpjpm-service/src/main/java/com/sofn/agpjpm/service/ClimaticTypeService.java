package com.sofn.agpjpm.service;

import com.sofn.agpjpm.model.ClimaticType;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:55
 */
public interface ClimaticTypeService {
    void save(ClimaticType climaticType, String surveyId);
    List<ClimaticType> getBySurveyId(String surveyId);
    void delete(String surveyId);
    void update(ClimaticType climaticType,String surveyId);
}
