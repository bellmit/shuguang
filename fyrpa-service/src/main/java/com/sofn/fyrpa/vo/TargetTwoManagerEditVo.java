package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("二级指标管理对象编辑的的vo类")
@Data
public class TargetTwoManagerEditVo {

    @ApiModelProperty(name = "id",value ="主键id" )
    private String id;

    @ApiModelProperty(name = "targetType",value ="指标类型(二级指标)" )
    private String targetType;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "addPerson",value ="添加人" )
    private String addPerson;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;

    @ApiModelProperty(name = "isTargetName",value ="所属的一级指标名称" )
    private String isTargetName;
}