package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "植物利用用途表单对象")
public class PlantUtilizationPurposeForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "利用ID")
    private String utilizationId;
    @ApiModelProperty(value = "用途ID")
    private String purposeId;
    @ApiModelProperty(value = "用途名称", example = "食用植物")
    private String purposeValue;
}
