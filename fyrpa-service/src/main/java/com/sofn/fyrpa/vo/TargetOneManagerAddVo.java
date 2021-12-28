package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("一级指标管理对象添加的vo类")
@Data
public class TargetOneManagerAddVo {

    @ApiModelProperty(name = "targetType",value ="指标类型(一级)" )
    private String targetType;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "addPerson",value ="添加人" )
    private String addPerson;

}