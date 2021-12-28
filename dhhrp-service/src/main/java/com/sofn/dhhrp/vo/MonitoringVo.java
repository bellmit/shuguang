package com.sofn.dhhrp.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 15:18
 */
@Data
public class MonitoringVo {
    private String pointName;
    private List<SpeVo> spe;

}
