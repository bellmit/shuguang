package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/4 10:34
 **/
@Data
@ApiModel("溯源图欧鳗组织信息")
public class Nodes {
    @ApiModelProperty("组织标识")
    private String id;
    @ApiModelProperty("组织名称")
    private String label;
}
