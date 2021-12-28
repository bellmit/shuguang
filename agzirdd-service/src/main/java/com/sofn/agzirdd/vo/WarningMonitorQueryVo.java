package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 预警图信息列表查询条件Vo
 * @author Chlf
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WarningMonitorQueryVo implements Serializable {

    @ApiModelProperty(name = "classificationId", value = "指标分类id, 必传",required = true)
    private String classificationId;

    @ApiModelProperty(name = "speciesName", value = "外来入侵物种名, 必传",required = true)
    private String speciesName;

    @ApiModelProperty(name = "belongYear", value = "所属年度, 必传",required = true)
    private String belongYear;

}
