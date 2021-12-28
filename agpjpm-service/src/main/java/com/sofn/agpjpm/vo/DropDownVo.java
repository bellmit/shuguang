package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 下拉列表
 * @Author: WXY
 * @Date: 2020-2-25 19:40:26
 */
@Data
@ApiModel
public class DropDownVo {
    //id
    @ApiModelProperty("id")
    private String id;

    //名称
    @ApiModelProperty("值")
    private String name;


    //其它需要返回的另一个字段，比如获取采集单位中，需要把采集人也返回，threeColumn就是采集人
    private String thirdColumn;
}
