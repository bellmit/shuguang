package com.sofn.fdpi.sysapi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *@Description 登录参数
 *@Author quzhijie
 *@Date 2019/10/14 16:43
 *@Version 1.0
 **/
@Data
@ApiModel(value="登录参数")
public class LoginVo {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码，MD5加密",required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码ID")
    private String captchaId;

    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "记住密码,Y:记住 N:不记住")
    private String rememberMe="N";


    public static LoginVo getDefaultUser(){
        LoginVo loginVo = new LoginVo();
        loginVo.setCaptcha("sofn");
        loginVo.setCaptchaId("sofn");
        loginVo.setUsername("fdpi_qy_default");
        //Abc123456#
        loginVo.setPassword("37373c0af301e6ad3a1ad2993cfc28b8");
        return loginVo;
    }
}
