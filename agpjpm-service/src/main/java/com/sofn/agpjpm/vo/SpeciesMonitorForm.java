package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "监测（物种）表单对象")
public class SpeciesMonitorForm {

    @ApiModelProperty(value = "主键(新增不用管，修改更新时必传)")
    private String id;
    @ApiModelProperty(value = "种名id")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specName;
    @ApiModelProperty(value = "拉丁文种名")
    private String latinName;
    @ApiModelProperty(value = "属名")
    private String attrName;
    @ApiModelProperty(value = "科名")
    private String familyName;
    @ApiModelProperty(value = "生长状况")
    private String growth;
    @ApiModelProperty(value = "分布密度")
    private String density;
    @ApiModelProperty(value = "丰富度")
    private String richness;
    @ApiModelProperty(value = "分布面积（亩）")
    private Double area;

    @ApiModelProperty(value = "植株照片(目标物种)")
    private List<PictureAttForm> plantPic;
    @ApiModelProperty(value = "群落景观、盖度照片(目标物种)")
    private List<PictureAttForm> communityPic;


}
