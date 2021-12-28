package com.sofn.agpjyz.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 监测预警模型构建与管理
 *
 * @Author yumao
 * @Date 2020/3/6 9:27
 **/
@Data
@TableName("MONITORING_WARNING")
@ApiModel(value = "监测预警模型构建与管理对象")
public class MonitoringWarning {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "检测类型1：生境检测2：目标保护植物分布3：伴生植物分布")
    private String testType;
    @ApiModelProperty(value = "重点保护植物ID")
    private String plantId;
    @ApiModelProperty(value = "重点保护植物名称")
    private String plantValue;
    @ApiModelProperty(value = "监测点ID")
    private String protectId;
    @ApiModelProperty(value = "监测点名称")
    private String protectValue;
    @ApiModelProperty(value = "指标分类ID（1:种群数量2：受损面积）")
    private String indexId;
    @ApiModelProperty(value = "指标分类名称（无用字段不用传）")
    private String indexValue;
    @ApiModelProperty(value = "预警推送方式")
    private String email;
    @ApiModelProperty(value = "阀值对象")
    @TableField(exist = false)
    private List<Threshold> thresholdList;

}
