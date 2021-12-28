package com.sofn.agpjpm.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-15 9:57
 */
@Data
@ApiModel(value = "野生植物调查数据列表")
public class ServeyListVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "调查日期")
    private Date surveyDate;
    @ApiModelProperty(value = "调查点编号")
    private String  surveyNum;
    @ApiModelProperty(value = "省名")
    private String  provinceName;
    @ApiModelProperty(value = "市名")
    private String cityName;
    @ApiModelProperty(value = "县名")
    private String countyName;
    @ApiModelProperty(value = "海拔")
    private String altitude;



    @ApiModelProperty(value = "生境类型id")
    private String habitatId;
    @ApiModelProperty(value = "土壤类型id")
    private String soilId;
    @ApiModelProperty(value = "气候类型id")
    private String climaticId;
    @ApiModelProperty(value = "地形类型id")
    private String landformId;
    @ApiModelProperty(value = "调查人id")
    private String surveyor;
    @ApiModelProperty(value = "电话")
    private String  tel;
    @ApiModelProperty(value = "生境类型name")
    String habName;
    @ApiModelProperty(value = "土壤类型name")
    String soilName;
    @ApiModelProperty(value = "气候类型name")
    String cliName;
    @ApiModelProperty(value = "地形类型name")
    String landName;
}
