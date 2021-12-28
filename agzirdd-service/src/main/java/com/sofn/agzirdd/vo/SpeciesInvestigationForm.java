package com.sofn.agzirdd.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 物种调查模块-调查基本信息表单
 * @Author: mcc

 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpeciesInvestigationForm{

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "investigationTime", value = "调查时间")
    private Date investigationTime;

    @ApiModelProperty(name = "formNumber", value = "表格编号")
    private String formNumber;

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

    @ApiModelProperty(name = "longitude", value = "经度:保留小数点后6位小数")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度:保留小数点后6位小数")
    private String latitude;

    @ApiModelProperty(name = "altitude", value = "海拔")
    private String altitude;

    @ApiModelProperty(name = "investigator", value = "调查人")
    private String investigator;

    @ApiModelProperty(name = "investigatorTelephone", value = "电话")
    private String investigatorTelephone;

    @ApiModelProperty(name = "investigatorCompany", value = "调查人单位")
    private String investigatorCompany;

    @ApiModelProperty(name = "respondents", value = "被调查人")
    private String respondents;

    @ApiModelProperty(name = "respondentsTelephone", value = "被调查人电话")
    private String respondentsTelephone;

    @ApiModelProperty(name = "respondentsCompany", value = "被调查人单位")
    private String respondentsCompany;

    @ApiModelProperty(name = "hasAlienSpecies", value = "是否有外来物种入侵:0-否，1-是")
    private String hasAlienSpecies;

    @ApiModelProperty(name = "status", value = "状态:0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回," +
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报")
    private String status;

    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;

    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "hasAlienSpeciesName", value = "是否存在入侵物种")
    private String hasAlienSpeciesName;
}
