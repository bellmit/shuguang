package com.sofn.agpjyz.vo;

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
public class DropDownWithLatinVo {
    //id
    @ApiModelProperty("id")
    private String id;

    //中文名称
    @ApiModelProperty("中文名")
    private String name;

    //latin名称
    @ApiModelProperty("latin名称")
    private String latinName;

    @ApiModelProperty("科名")
    private String familyName;

    @ApiModelProperty("属名")
    private String attrName;
}
