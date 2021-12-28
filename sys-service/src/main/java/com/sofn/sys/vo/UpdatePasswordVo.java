package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2019/12/3 14:19
 */
@ApiModel
@Data
public class UpdatePasswordVo {

    @ApiModelProperty("用户Id")
    @NotBlank(message = "用户ID必填")
    private String id;

    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码必填")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码必填")
    private String newPassword;

}
