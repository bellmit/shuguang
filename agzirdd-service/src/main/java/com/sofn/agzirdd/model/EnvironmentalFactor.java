package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 */
@TableName("ENVIRONMENTAL_FACTOR")
@Data
public class EnvironmentalFactor extends Model<EnvironmentalFactor> {

    @ApiModelProperty(name = "id", value = "id(不用传递)")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度(不用传递)")
    private String belongYear;

    @ApiModelProperty(name = "monitorId", value = "监测点id")
    private String monitorId;

    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ApiModelProperty(name = "temperature", value = "空气温度")
    private double temperature;

    @ApiModelProperty(name = "humidity", value = "湿度")
    private double humidity;

    @ApiModelProperty(name = "precipitation", value = "降水")
    private double precipitation;

    @ApiModelProperty(name = "totalRadiation", value = "太阳总辐射")
    private double totalRadiation;

    @ApiModelProperty(name = "effectiveRadiation", value = "太阳有效辐射")
    private double effectiveRadiation;

    @ApiModelProperty(name = "violetRadiation", value = "太阳紫辐射")
    private double violetRadiation;

    @ApiModelProperty(name = "windPower", value = "风力")
    private double windPower;

    @ApiModelProperty(name = "windDirection", value = "风向")
    private String windDirection;

    @ApiModelProperty(name = "soilTemperature", value = "土壤温度")
    private double soilTemperature;

    @ApiModelProperty(name = "soilHumidity", value = "土壤湿度")
    private String soilHumidity;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "collectTime", value = "采集时间")
    private Date collectTime;

    @ApiModelProperty(name = "state", value = "日常工作状况")
    private String state;

    @ApiModelProperty(name = "createUserId", value = "创建人id(不用传递)")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建人姓名(不用传递)")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)")
    private Date createTime;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @ApiModelProperty(name = "provinceName", value = "省级Name")
    private String provinceName;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @ApiModelProperty(name = "cityName", value = "市级Name")
    private String cityName;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "countyName", value = "区县name")
    private String countyName;

}