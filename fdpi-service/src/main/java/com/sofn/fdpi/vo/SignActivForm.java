package com.sofn.fdpi.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "标识激活表单对象")
public class SignActivForm {

    @ApiModelProperty(value = "待激活IDS")
    private List<String> ids;

    @ApiModelProperty(value = "旧标识编码开始")
    private String oldCodeS;

    @ApiModelProperty(value = "旧标识编码结束")
    private String oldCodeE;

    @ApiModelProperty(value = "待修改数据")
    SignboardForm signboardForm;
}
