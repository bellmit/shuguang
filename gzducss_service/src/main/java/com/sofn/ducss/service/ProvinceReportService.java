/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:31
 */
package com.sofn.ducss.service;

import java.util.Map;

/**
 * 省级报告生成接口
 *
 * @author Chlf
 * @version 1.0
 **/
public interface ProvinceReportService {

    /**
     * 省级报告部分
     *
     * @param provinceId 省级ID
     * @param year       年份
     * @return map
     */
    Map<String, Object> getProvinceReport(String provinceId, String year);

}
