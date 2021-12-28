package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Description: 入侵-生物监测点基础信息
 * @Author: mcc
 * @Date: 2020\3\5 0005
 */
@TableName("BASIC_INFO")
@Data
public class BasicInfo extends Model<BasicInfo> {

    @ApiModelProperty(name = "id", value = "id(不用传递)")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度(不用传递)")
    private String belongYear;

    @ApiModelProperty(name = "monitorId", value = "监测点id")
    private String monitorId;

    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @ApiModelProperty(name = "provinceName", value = "省级name")
    private String provinceName;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @ApiModelProperty(name = "cityName", value = "市级Name")
    private String cityName;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "countyName", value = "区县Name")
    private String countyName;

    @ApiModelProperty(name = "areaName", value = "地区名,省市区全称组合(不用传递)")
    private String areaName;

    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @ApiModelProperty(name = "responsibleDepartment", value = "负责单位")
    private String responsibleDepartment;

    @ApiModelProperty(name = "leadingCadre", value = "负责人")
    private String leadingCadre;

    @ApiModelProperty(name = "telephone", value = "负责人电话")
    private String telephone;

    @ApiModelProperty(name = "executive", value = "主管领导")
    private String executive;

    @ApiModelProperty(name = "exePhone", value = "主管领导电话")
    private String exePhone;

    @ApiModelProperty(name = "status", value = "状态:0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回," +
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报,10-专家提交")
    private String status;

    @ApiModelProperty(name = "remark", value = "审核意见(用于审核时传递)")
    private String remark;

    @ApiModelProperty(name = "createUserId", value = "创建人id(不用传递)",hidden = true)
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建人姓名(不用传递)")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)")
    private Date createTime;


}