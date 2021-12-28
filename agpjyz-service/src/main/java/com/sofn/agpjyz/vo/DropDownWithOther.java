package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 下拉列表，包含三个值返回
 * @Author: WXY
 * @Date: 2020-6-3 15:32:52
 */
@Data
@ApiModel
public class DropDownWithOther {
    @ApiModelProperty("id,key")
    private String id;
    //名称
    @ApiModelProperty("值,value")
    private String name;
    @ApiModelProperty("其它需要返回的另一个字段，比如获取采集单位中，需要把采集人也返回，threeColumn就是采集人")
    private String thirdColumn;
}
