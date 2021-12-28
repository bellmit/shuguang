package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 下拉列表
 * @Author: WXY
 * @Date: 2020-2-25 19:40:26
 */
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class DropDownVo {
    //id
    @ApiModelProperty("id")
    private String id;
 
    //名称
    @ApiModelProperty("值")
    private String name;
}
