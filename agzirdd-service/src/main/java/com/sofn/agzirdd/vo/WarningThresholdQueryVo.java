package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 预警监测模块阈值列表查询条件Vo
 * @author Chlf
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WarningThresholdQueryVo implements Serializable {

    @ApiModelProperty(name = "classificationId", value = "指标分类id")
    private String classificationId;

    @ApiModelProperty(name = "speciesId", value = "外来入侵物种id")
    private String speciesId;

    @ApiModelProperty(name = "pageNo", value = "索引,必需传值", required = true)
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数,必需传值", required = true)
    private Integer pageSize;
}
