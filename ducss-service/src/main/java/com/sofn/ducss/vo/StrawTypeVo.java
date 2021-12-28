package com.sofn.ducss.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 秸秆类型和具体值
 */
@ApiModel("市场主体规模化利用信息和主体信息")
@Data
public class StrawTypeVo {

    @ApiModelProperty("秸秆类型代码")
    private String typeCode;

    @ApiModelProperty("秸秆类型名称")
    private String typeName;

    @ApiModelProperty( "肥料化")
    private String fertilising;

    @ApiModelProperty( "饲料化")
    private String forage;

    @ApiModelProperty( "燃料化")
    private String fuel;

    @ApiModelProperty( "基料化")
    private String base;

    @ApiModelProperty( "原料化")
    private String material;

    @ApiModelProperty( "外县来源")
    private String other;

    @ApiModelProperty( "本县来源")
    private String thisCount;
}
