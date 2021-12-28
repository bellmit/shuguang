/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-20 15:49
 */
package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.ducss.mapper.DataAnalysisProviceMapper;
import com.sofn.ducss.model.DataAnalysisProvice;
import com.sofn.ducss.service.DataAnalysisProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据分析实现
 *
 * @author jiangtao
 * @version 1.0
 **/
@Service
public class DataAnalysisProvinceServiceImpl extends ServiceImpl<DataAnalysisProviceMapper, DataAnalysisProvice> implements DataAnalysisProvinceService {

    @Override
    public List<DataAnalysisProvice> listByYearAndProvinceIdsAndTotolRateTooMuch(String year, List<String> provinceIds) {
        return baseMapper.listByYearAndProvinceIdsAndTotolRateTooMuch(year, provinceIds);
    }
}
