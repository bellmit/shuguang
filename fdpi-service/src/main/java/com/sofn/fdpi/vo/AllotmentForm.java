package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel("标识核发Form对象")
public class AllotmentForm  {

    @ApiModelProperty(value = "标识申请id")
    @NotBlank(message = "标识申请id不能为空")
    private String id;

    @ApiModelProperty(value = "标识编码列表")
    private List<String> codes;
}
