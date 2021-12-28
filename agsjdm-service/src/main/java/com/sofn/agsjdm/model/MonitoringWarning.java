package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:15
 */
@Data
@TableName("MONITORING_WARNING")
@ApiModel(value = "监测预警模型构建与管理对象")
public class MonitoringWarning {

    @ApiModelProperty(value = "主键")
    private String id;

    @NotBlank(message = "检测类型不可为空")
    @Length(max = 64)
    @ApiModelProperty(value = "检测类型")
    private String testType;

    @ApiModelProperty(value = "如果检测类型为生物分布监测：需要传中文名")
    private String chineseName;

    @NotBlank(message = "湿地区ID不可为空")
    @ApiModelProperty(value = "湿地区ID")
    private String wetlandId;

    @ApiModelProperty(value = "湿地区名称(前端新增/修改不用传)")
    @TableField(exist = false)
    private String wetlandName;

    @Length(max = 64)
    @NotBlank(message = "指标分类为必填项")
    @ApiModelProperty(value = "指标分类ID（1:污染面积 2种群数量）")
    private String indexId;

    @ApiModelProperty(value = "指标分类名称(前端新增/修改不用传)")
    private String indexValue;

    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

    @Valid
    @ApiModelProperty(value = "阀值对象")
    @TableField(exist = false)
    private List<Threshold> thresholdList;

}
