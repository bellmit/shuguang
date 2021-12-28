package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@ApiModel(value = "标识打印列表表单对象")
public class SignboardPrintListForm {

    @NotBlank(message = "标识ID不能为空")
    @ApiModelProperty(value = "标识ID")
    private String signboardId;


}
