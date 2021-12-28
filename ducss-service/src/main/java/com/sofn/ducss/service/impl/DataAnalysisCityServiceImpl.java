/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-18 17:58
 */
package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.ducss.mapper.DataAnalysisCityMapper;
import com.sofn.ducss.model.DataAnalysisCity;
import com.sofn.ducss.service.DataAnalysisCityService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据分析市级实现
 *
 * @author jiangtao
 * @version 1.0
 **/
@Service
public class DataAnalysisCityServiceImpl extends ServiceImpl<DataAnalysisCityMapper, DataAnalysisCity> implements DataAnalysisCityService {

    @Override
    public List<DataAnalysisCity> listByYearAndCityIdsAndTotolRateTooMuch(String year, List<String> cityIds) {
        if (CollectionUtils.isEmpty(cityIds)) {
            return Lists.newArrayList();
        }
        return baseMapper.listByYearAndCityIdsAndTotolRateTooMuch(year, cityIds);
    }
}
