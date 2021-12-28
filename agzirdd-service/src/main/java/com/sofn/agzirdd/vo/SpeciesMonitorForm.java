package com.sofn.agzirdd.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.agzirdd.model.SpeciesMonitor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 物种监测模块-监测基本信息表单
 * @Author: mcc

 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpeciesMonitorForm {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "monitorTime", value = "监测时间")
    private Date monitorTime;

    @ApiModelProperty(name = "formNumber", value = "表格编号")
    private String formNumber;

    @ApiModelProperty(name = "provinceId", value = "省级Id")
    private String provinceId;

    private String provinceName;

    @ApiModelProperty(name = "cityId", value = "市级Id")
    private String cityId;

    private String cityName;

    @ApiModelProperty(name = "countyId", value = "区县Id")
    private String countyId;

    private String countyName;

    @ApiModelProperty(name = "town", value = "乡村/镇")
    private String town;

    @ApiModelProperty(name = "monitor", value = "监测人")
    private String monitor;

    @ApiModelProperty(name = "telephone", value = "电话")
    private String telephone;

    @ApiModelProperty(name = "company", value = "单位")
    private String company;

    @ApiModelProperty(name = "status", value = "状态:0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回," +
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报")
    private String status;

    @ApiModelProperty(name = "hasAlienSpecies", value = "是否有外来物种入侵；0-否，1-是")
    private String hasAlienSpecies;

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "hasAlienSpeciesName", value = "是否存在入侵物种")
    private String hasAlienSpeciesName;
}
