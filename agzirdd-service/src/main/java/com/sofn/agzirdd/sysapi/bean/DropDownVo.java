package com.sofn.agzirdd.sysapi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 下拉列表
 * @Author: chlf
 * @Date: 2020-04-01 10:18:26
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
