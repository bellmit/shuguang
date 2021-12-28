package com.sofn.fdpi.vo;

import lombok.Data;

@Data
public class StatisticVo {
    //区划父节点编码,/拼接
    private String parentIds;
    //区划父节点名称,/拼接
    private String parentNames;
    //当前区划编码
    private String regionCode;
    //当前区划名称
    private String regionName;
    private int totalNumber;
}
