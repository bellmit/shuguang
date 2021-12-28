package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel(value = "删除用户对象")
public class SysDelUserForm {

    @ApiModelProperty("用户ID集合")
    @NotEmpty(message = "用户ID集合不能为空")
    private List<String> ids;

    @ApiModelProperty("系统appId")
    @NotBlank(message = "appId不能为空")
    private String appId;

}
