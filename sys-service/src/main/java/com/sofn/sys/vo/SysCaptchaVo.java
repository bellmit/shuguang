package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel
@Data
@AllArgsConstructor
public class SysCaptchaVo {
    @ApiModelProperty("验证码ID")
    private String id;
    @ApiModelProperty("验证码图片,Base64字符串")
    private String captcha;
}
