package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "目标物种基础信息表单对象")
public class TargetSpeciesForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectValue;
    @ApiModelProperty(value = "目标物种ID")
    private String specId;
    @ApiModelProperty(value = "目标物种名称")
    private String specValue;
    @ApiModelProperty(value = "拉丁学名")
    private String latinName;
    @ApiModelProperty(value = "数量")
    private Double amount;
    @ApiModelProperty(value = "生长状况")
    private String grow;
    @ApiModelProperty(value = "物种丰富度")
    private String richness;


}
