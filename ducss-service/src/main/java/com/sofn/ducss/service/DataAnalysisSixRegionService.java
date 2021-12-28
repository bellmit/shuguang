/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-20 11:22
 */
package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.DataAnalysisSixArea;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据分析六大区
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface DataAnalysisSixRegionService extends IService<DataAnalysisSixArea> {

    Integer insertList(List<DataAnalysisSixArea> list);
}
