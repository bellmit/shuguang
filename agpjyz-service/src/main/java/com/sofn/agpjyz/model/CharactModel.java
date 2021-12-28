package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 性状
 * @Auther: xiaobo
 * @Date: 2020-03-11 15:10
 */
@Data
@TableName("CHARACTER")
public class CharactModel {
    @ApiModelProperty(value = "主键")
    private String  id;
//            性状ID
    @ApiModelProperty(value = "性状ID")
    private String characterId;
//           性状VALUE
    @ApiModelProperty(value = "性状名称")
    private String  characterValue;
//          标本ID
    @ApiModelProperty(value = "标本采集对象ID")
    private String specimenId;
    @ApiModelProperty(value = "类型 1:乔木 2:宜立")
    private String type;
}
