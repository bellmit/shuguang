package com.sofn.agzirdd.excelmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@ApiModel(value = "EnvironmentalFactorXlsx", description = "外来入侵生物环境因子信息列表")
@ExcelSheetInfo(title = "外来入侵生物环境因子信息列表", sheetName = "表一")
public class EnvironmentalFactorExcel {

    @ExcelField(title = "监测点名称")
    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ExcelField(title = "空气温度")
    @ApiModelProperty(name = "temperature", value = "空气温度")
    private double temperature;

    @ExcelField(title = "湿度")
    @ApiModelProperty(name = "humidity", value = "湿度")
    private double humidity;

    @ExcelField(title = "降水")
    @ApiModelProperty(name = "precipitation", value = "降水")
    private double precipitation;

    @ExcelField(title = "太阳总辐射")
    @ApiModelProperty(name = "totalRadiation", value = "太阳总辐射")
    private double totalRadiation;

    @ExcelField(title = "太阳有效辐射")
    @ApiModelProperty(name = "effectiveRadiation", value = "太阳有效辐射")
    private double effectiveRadiation;

    @ExcelField(title = "太阳紫辐射")
    @ApiModelProperty(name = "violetRadiation", value = "太阳紫辐射")
    private double violetRadiation;

    @ExcelField(title = "风力")
    @ApiModelProperty(name = "windPower", value = "风力")
    private double windPower;

    @ExcelField(title = "风向")
    @ApiModelProperty(name = "windDirection", value = "风向")
    private String windDirection;

    @ExcelField(title = "土壤温度")
    @ApiModelProperty(name = "soilTemperature", value = "土壤温度")
    private double soilTemperature;

    @ExcelField(title = "土壤湿度")
    @ApiModelProperty(name = "soilHumidity", value = "土壤湿度")
    private String soilHumidity;

    @ExcelField(title = "采集时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "collectTime", value = "采集时间")
    private Date collectTime;

    @ExcelField(title = "日常工作状况")
    @ApiModelProperty(name = "state", value = "日常工作状况")
    private String state;

    @ExcelField(title = "操作人")
    @ApiModelProperty(name = "createUserName", value = "创建人姓名")
    private String createUserName;

    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}
