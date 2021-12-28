package com.sofn.agzirdd.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 物种采集模块-采集基本信息表单
 * @Author: mcc

 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecimenCollectionForm{

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "collectNumber", value = "采集号")
    private String collectNumber;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "collectTime", value = "采集时间")
    private Date collectTime;

    @ApiModelProperty(name = "gatherer", value = "采集人")
    private String gatherer;

    @ApiModelProperty(name = "company", value = "采集单位")
    private String company;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;
    private String provinceName;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;
    private String cityName;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;
    private String countyName;

    @ApiModelProperty(name = "town", value = "乡村/镇")
    private String town;

    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @ApiModelProperty(name = "altitude", value = "海拔")
    private String altitude;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "type", value = "标本类型：0-植物；1-动物；2-微生物")
    private String type;

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "typeName", value = "标本类型(0-植物；1-动物；2-微生物)")
    private String typeName;

    @ApiModelProperty(name = "plantSpeciesId", value = "植物标本id")
    private String plantSpeciesId;

    @ApiModelProperty(name = "plantSpeciesName", value = "植物标本名称")
    private String plantSpeciesName;

    @ApiModelProperty(name = "plantLatinName", value = "植物标本拉丁名称")
    private String plantLatinName;

    @ApiModelProperty(name = "animalSpeciesId", value = "动物标本id")
    private String animalSpeciesId;

    @ApiModelProperty(name = "animalSpeciesName", value = "动物标本名称")
    private String animalSpeciesName;

    @ApiModelProperty(name = "animalLatinName", value = "动物标本拉丁名称")
    private String animalLatinName;

    @ApiModelProperty(name = "microbeSpeciesId", value = "微生物标本id")
    private String microbeSpeciesId;

    @ApiModelProperty(name = "microbeSpeciesName", value = "微生物标本名称")
    private String microbeSpeciesName;

    @ApiModelProperty(name = "microbeLatinName", value = "微生物标本拉丁名称")
    private String microbeLatinName;

}
