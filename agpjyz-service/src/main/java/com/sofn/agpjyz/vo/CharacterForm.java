package com.sofn.agpjyz.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 15:20
 */
@Data
@ApiModel(value = " 性状表单对象")
public class CharacterForm {
    @ApiModelProperty(value = "主键")
    private String  id;
    //            性状ID
    @ApiModelProperty(value = "性状ID")
    private String characterId;
    //           性状VALUE
    @ApiModelProperty(value = "性状值")
    private String  characterValue;
    //          标本ID
    @ApiModelProperty(value = "标本ID")
    private String specimenId;
    @ApiModelProperty(value = "类型 1:乔木 2:宜立")
    private String type;
}
