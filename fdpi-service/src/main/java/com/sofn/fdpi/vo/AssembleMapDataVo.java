package com.sofn.fdpi.vo;

import com.sofn.common.map.AdAreaData;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AssembleMapDataVo {
    private List<AdAreaData> adAreaDataList;
    private List<Map<String,Object>> statisticDataList;
}
