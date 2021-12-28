package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 允许进出口说明书的kv结构
 * @Author wg
 * @Date 2021/5/31 17:24
 **/
@Data
@ApiModel("允许进出口说明书的kv结构")
public class CredKV {
    @ApiModelProperty(value = "key就是val")
    private String key;
    @ApiModelProperty(value = "val就是key")
    private String val;
}
