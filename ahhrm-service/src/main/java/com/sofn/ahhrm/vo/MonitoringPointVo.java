package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 14:49
 */
@ApiModel(value = "监测点地区对象")
@Data
public class MonitoringPointVo {
    private String id;
    @ApiModelProperty(value="监测点名称")
    private  String  pointName;
    @ApiModelProperty(value="省code")
    private  String  province;
    @ApiModelProperty(value="市code")
    private  String  city;
    @ApiModelProperty(value="区code")
    private  String  county;
    /**
     * 经度
     */
    @ApiModelProperty(value="经度")
    private String longitude;
    /**
     * 纬度
     */
    @ApiModelProperty(value="纬度")
    private String latitude;
    @ApiModelProperty(value="省-市-区中文")
    private String regionInCh;
    @ApiModelProperty(value="颜色")
    private String color;
}
