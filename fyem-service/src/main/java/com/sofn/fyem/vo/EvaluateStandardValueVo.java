package com.sofn.fyem.vo;

import com.sofn.fyem.model.EvaluateStandardValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 放流评价-分数vo
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class EvaluateStandardValueVo {

    @ApiModelProperty(name = "basicId", value = "水生物放流信息id")
    private String basicId;

    @ApiModelProperty(name = "releaseEvaluate", value = "放流效果评价")
    private Double releaseEvaluate;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    private List<EvaluateStandardValue> evaluateStandardValueList;
}
