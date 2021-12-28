package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 入侵-生物监测点植被生理参数表
 * @Author: mcc
 * @Date: 2020\3\6 0006
 */
@TableName("PHYSIOLOGICAL_PARAMETERS")
@Data
public class PhysiologicalParameters extends Model<PhysiologicalParameters> {

    @ApiModelProperty(name = "id", value = "id(不用传递)")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度(不用传递)")
    private String belongYear;

    @ApiModelProperty(name = "monitorId", value = "监测点id")
    private String monitorId;

    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ApiModelProperty(name = "leafAreaIndex", value = "叶面积指数")
    private String leafAreaIndex;

    @ApiModelProperty(name = "effectiveRadiationComponent", value = "植物吸收性光合有效辐射分量")
    private String effectiveRadiationComponent;

    @ApiModelProperty(name = "photosyntheticQuantum", value = "表观光合量子效率")
    private String photosyntheticQuantum;

    @ApiModelProperty(name = "status", value = "状态:0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回," +
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报,10-专家提交")
    private String status;

    @ApiModelProperty(name = "remark", value = "审核意见(用于审核相关)")
    private String remark;

    @ApiModelProperty(name = "createUserId", value = "创建者id(不用传递)")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建者姓名(不用传递)")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)")
    private Date createTime;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @ApiModelProperty(name = "provinceName", value = "省级name")
    private String provinceName;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @ApiModelProperty(name = "cityName", value = "市级name")
    private String cityName;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "countyName", value = "区县name")
    private String countyName;
}