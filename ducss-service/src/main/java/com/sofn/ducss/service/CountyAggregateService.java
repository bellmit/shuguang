package com.sofn.ducss.service;


import com.sofn.ducss.vo.StrawUtilizeResVo3;

import java.util.List;

public interface CountyAggregateService {

    /**
     * 获取区县的利用量数据2
     */
    List<StrawUtilizeResVo3> getCountyStrawUtilize2(String areaId, String year);
}
