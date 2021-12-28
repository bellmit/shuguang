package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description: 物种调查模块-调查基本信息
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
@TableName("SPECIES_INVESTIGATION")
@Data
public class SpeciesInvestigation extends Model<SpeciesInvestigation> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "investigationTime", value = "调查时间")
    private Date investigationTime;

    @NotBlank(message = "表格编号不能为空!")
    @ApiModelProperty(name = "formNumber", value = "表格编号")
    private String formNumber;

    @NotBlank(message = "省级不能为空!")
    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    private String provinceName;

    @NotBlank(message = "市级不能为空!")
    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    private String cityName;

    @NotBlank(message = "区县不能为空!")
    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    private String countyName;

    @NotBlank(message = "乡村/不能为空!")
    @ApiModelProperty(name = "town", value = "乡村/镇")
    private String town;

    @NotBlank(message = "经度不能为空!")
    @ApiModelProperty(name = "longitude", value = "经度:保留小数点后6位小数")
    private String longitude;

    @NotBlank(message = "纬度不能为空!")
    @ApiModelProperty(name = "latitude", value = "纬度:保留小数点后6位小数")
    private String latitude;

    @ApiModelProperty(name = "altitude", value = "海拔")
    private String altitude;

    @NotBlank(message = "调查人不能为空!")
    @Size(max = 30,message = "调查人长度不能超过30个字")
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
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报,10-专家提交")
    private String status;

    @ApiModelProperty(name = "createUserId", value = "创建者id(不用传递)")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建者姓名(不用传递)")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)")
    private Date createTime;

    @ApiModelProperty(name = "roleCode", value = "角色码(不用传递)")
    private String roleCode;

    @ApiModelProperty(name = "isNew", value = "是否当年最新数据1是0否")
    private String isNew;
}