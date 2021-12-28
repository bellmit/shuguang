package com.sofn.agzirz.sysapi.bean;

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
}
