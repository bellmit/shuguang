/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-20 15:57
 */
package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.ducss.mapper.DataAnalysisSixRegionMapper;
import com.sofn.ducss.model.DataAnalysisSixArea;
import com.sofn.ducss.service.DataAnalysisSixRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据分析六大区实现
 *
 * @author jiangtao
 * @version 1.0
 **/
@Service
public class DataAnalysisSixRegionServiceImpl extends ServiceImpl<DataAnalysisSixRegionMapper, DataAnalysisSixArea> implements DataAnalysisSixRegionService{


    @Autowired
    private DataAnalysisSixRegionMapper dataAnalysisSixRegionMapper;
    @Override
    public Integer insertList(List<DataAnalysisSixArea> list) {
        return dataAnalysisSixRegionMapper.insertList(list);
    }
}
