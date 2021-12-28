package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 环境因子监测信息类
 * @Author yumao
 * @Date 2020/3/6 11:13
 **/
@Data
@TableName("ENVIRONMENTAL_FACTOR")
@ApiModel(value = "环境因子监测信息对象")
public class EnvironmentalFactor {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectValue;
    @ApiModelProperty(value = "空气温度")
    private String tair;
    @ApiModelProperty(value = "空气湿度")
    private String dampness;
    @ApiModelProperty(value = "降水")
    private String rain;
    @ApiModelProperty(value = "太阳总辐射")
    private String radiation;
    @ApiModelProperty(value = "太阳有效辐射")
    private String validRadiation;
    @ApiModelProperty(value = "太阳紫辐射")
    private String exposure;
    @ApiModelProperty(value = "风力")
    private String windPower;
    @ApiModelProperty(value = "风向")
    private String windDirection;
    @ApiModelProperty(value = "土壤温度")
    private String soilTemperature;
    @ApiModelProperty(value = "土壤湿度")
    private String soilMoisture;
    @ApiModelProperty(value = "采集时间")
    private Date collectTime;
    @ApiModelProperty(value = "日常工作状况")
    private String status;
    @ApiModelProperty(value = "操作人")
    private String inputer;
    @ApiModelProperty(value = "操作时间")
    private Date inputerTime;








}
