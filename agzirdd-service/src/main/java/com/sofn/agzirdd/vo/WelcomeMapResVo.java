package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WelcomeMapResVo {
    @ApiModelProperty(value = "区域ID")
    private String areaId;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "监测")
    private WelcomeMonitorInvestVo monitor;

    @ApiModelProperty(value = "调查")
    private WelcomeMonitorInvestVo invest;

    public WelcomeMapResVo(){
        this.areaId = "";
        this.areaName = "";
        this.monitor= new WelcomeMonitorInvestVo();
        this.invest= new WelcomeMonitorInvestVo();
    }

    /**
     * 设置数据
     * @param wmv
     * @param isMonitor
     */
    public void setData(WelcomeMapVo wmv,boolean isMonitor) {
        WelcomeMonitorInvestVo vo = null;
        if(isMonitor)
            vo = monitor;
        else
            vo = invest;

        vo.setData(wmv,isMonitor);
    }
}
