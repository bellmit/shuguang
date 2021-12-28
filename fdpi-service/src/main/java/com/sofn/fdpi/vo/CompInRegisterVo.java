package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("企业注册-》企业信息")
public class CompInRegisterVo implements Serializable {
    @ApiModelProperty("通讯地址")
    private String addr;
    @ApiModelProperty("法人代表")
    private String legal;
}
