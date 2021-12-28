package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * @Description: 物种监测模块-监测内容
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@TableName("MONITOR_CONTENT")
@Data
public class MonitorContent extends Model<MonitorContent> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "speciesMonitorId", value = "物种监测表id")
    private String speciesMonitorId;

    @NotBlank(message = "发生面积不能为空!")
    @ApiModelProperty(name = "area", value = "发生面积")
    private String area;

    @ApiModelProperty(name = "type", value = "生境类型")
    private String type;

    @ApiModelProperty(name = "typeName", value = "生境类型名称")
    private String typeName;

    @Size(max = 50,message = "危害对象长度不能超过50")
    @ApiModelProperty(name = "harmObject", value = "危害对象")
    private String harmObject;

    @Size(max = 50,message = "危害方式长度不能超过50")
    @ApiModelProperty(name = "harmMethod", value = "危害方式")
    private String harmMethod;

    @ApiModelProperty(name = "damageDegree", value = "危害程度:0-轻度；1-中度；2-重度")
    private String damageDegree;

    @Size(max = 50,message = "经济损失长度不能超过50")
    @ApiModelProperty(name = "economicLoss", value = "经济损失")
    private String economicLoss;

    @ApiModelProperty(name = "hasMeasures", value = "是否有防控措施:0-否，1-是")
    private String hasMeasures;

    @Max(value = 100000000,message = "防治面积长度不能超过100000000")
    @ApiModelProperty(name = "preventionArea", value = "防治面积")
    private double preventionArea;

    @Max(value = 100000000,message = "防治成本不能超过100000000")
    @ApiModelProperty(name = "preventionCost", value = "防治成本")
    private double preventionCost;

    @Size(max = 50,message = "防治描述长度不能超过50")
    @ApiModelProperty(name = "describe", value = "防治描述")
    private String describe;

    @Size(max = 50,message = "控制效果长度不能超过50")
    @ApiModelProperty(name = "result", value = "控制效果")
    private String result;

    @ApiModelProperty(name = "resultImg", value = "效果图")
    private String resultImg;

    @ApiModelProperty(name = "hasUtilize", value = "是否进行利用:0-否，1-是")
    private String hasUtilize;

    @Size(max = 50,message = "利用描述长度不能超过50")
    @ApiModelProperty(name = "utilizeDescription", value = "利用描述")
    private String utilizeDescription;

    @Size(max = 50,message = "利用方式长度不能超过50")
    @ApiModelProperty(name = "utilizeManner", value = "利用方式")
    private String utilizeManner;

    @ApiModelProperty(name = "utilizeImg", value = "利用方式图片")
    private String utilizeImg;

    @ApiModelProperty(name = "workImg", value = "工作照片")
    private String workImg;

    @ApiModelProperty(name = "speciesImg", value = "物种照片")
    private String speciesImg;

    @NotBlank(message = "结果概述不能为空!")
    @Size(min = 50,max = 1000,message = "结果概述最少50字,最长1000字")
    @ApiModelProperty(name = "summary", value = "结果概述")
    private String summary;

    @Size(max = 50,message = "问题和建议长度不能超过50")
    @ApiModelProperty(name = "proposal", value = "问题和建议")
    private String proposal;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;


}