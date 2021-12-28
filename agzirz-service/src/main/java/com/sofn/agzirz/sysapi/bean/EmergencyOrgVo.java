package com.sofn.agzirz.sysapi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("应急机构")
public class EmergencyOrgVo implements Serializable {
    @ApiModelProperty(value="主键")
    private String id;
    @ApiModelProperty(value="应急机构")
    private String name;
}
