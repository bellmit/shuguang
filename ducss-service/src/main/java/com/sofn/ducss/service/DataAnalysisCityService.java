/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-18 17:57
 */
package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.DataAnalysisCity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据分析市级接口
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface DataAnalysisCityService extends IService<DataAnalysisCity> {

    List<DataAnalysisCity> listByYearAndCityIdsAndTotolRateTooMuch(String year, List<String> cityIds);
}
