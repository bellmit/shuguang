package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 标识VO对象(证书年审中使用)
 * @Author: wuxy
 * @Date: 2019/12/26 17:23
 **/
@Data
@ApiModel(value = "标识VO对象(证书年审中使用)")
public class SignboardVoForInspect {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "标识编码")
    private String code;
    @ApiModelProperty(value = "标识状态 1 未激活 2 在养 3 已注销")
    private String status;
    @ApiModelProperty(value = "标识状态名称")
    private String statusName;
    @ApiModelProperty(value = "标识类型")
    private String signboardType;
}
