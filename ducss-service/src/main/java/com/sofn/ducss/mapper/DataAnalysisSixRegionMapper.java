/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-20 15:55
 */
package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DataAnalysisArea;
import com.sofn.ducss.model.DataAnalysisSixArea;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据分析六大区Mapper
 *
 * @author jiangtao
 * @version 1.0
 **/
@Component
public interface DataAnalysisSixRegionMapper extends BaseMapper<DataAnalysisSixArea> {

    Integer insertList(List<DataAnalysisSixArea> list);
}
