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
 * @Description: 物种监测模块-监测基本信息
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@TableName("SPECIES_MONITOR")
@Data
public class SpeciesMonitor extends Model<SpeciesMonitor> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "monitorTime", value = "监测时间")
    private Date monitorTime;

    @NotBlank(message = "表格编号不能为空!")
    @ApiModelProperty(name = "formNumber", value = "表格编号")
    private String formNumber;

    @NotBlank(message = "请选择监测地点!")
    @ApiModelProperty(name = "provinceId", value = "省级Id")
    private String provinceId;
    private String provinceName;

    @NotBlank(message = "请选择监测地点!")
    @ApiModelProperty(name = "cityId", value = "市级Id")
    private String cityId;
    private String cityName;

    @NotBlank(message = "请选择监测地点!")
    @ApiModelProperty(name = "countyId", value = "区县Id")
    private String countyId;
    private String countyName;

    @NotBlank(message = "乡村/镇不能为空!")
    @Size(max = 30,message = "乡村/镇长度不能超过30个字")
    @ApiModelProperty(name = "town", value = "乡村/镇")
    private String town;

    @NotBlank(message = "监测人不能为空!")
    @Size(max = 30,message = "监测人长度不能超过30个字")
    @ApiModelProperty(name = "monitor", value = "监测人")
    private String monitor;

    @ApiModelProperty(name = "telephone", value = "电话")
    private String telephone;

    @NotBlank(message = "监测单位不能为空!")
    @Size(max = 20,message = "监测单位长度不能超过20个字")
    @ApiModelProperty(name = "company", value = "单位")
    private String company;

    @ApiModelProperty(name = "status", value = "状态:0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回," +
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报,10-专家提交")
    private String status;

    @ApiModelProperty(name = "hasAlienSpecies", value = "是否有外来物种入侵；0-否，1-是")
    private String hasAlienSpecies;

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