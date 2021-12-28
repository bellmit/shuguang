package com.sofn.fdpi.sysapi.bean;

import com.sofn.fdpi.vo.SysUserForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class Auth {

    @ApiModelProperty(name = "token",value = "token")
    private String token;
    @ApiModelProperty(name = "sysUser",value = "用户")
    private SysUserForm sysUser;

}
