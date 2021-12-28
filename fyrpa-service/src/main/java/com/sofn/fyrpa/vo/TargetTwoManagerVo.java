package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("二级指标管理对象的vo类")
@Data
public class TargetTwoManagerVo {

    @ApiModelProperty(name = "tagegetTwoId",value ="二级指标id" )
    private String targetTwoId;

    @ApiModelProperty(name = "targetTwoName",value ="二级指标名称" )
    private String targetTwoName;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;
}