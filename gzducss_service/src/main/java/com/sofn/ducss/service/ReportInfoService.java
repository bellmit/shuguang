/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:31
 */
package com.sofn.ducss.service;

import java.util.Map;

public interface ReportInfoService {

    /**
     * param_13——param_177报告部分数据
     *
     * @param year  年份
     *
     */
    Map<String, Object> getMyInfo(String year);

}
