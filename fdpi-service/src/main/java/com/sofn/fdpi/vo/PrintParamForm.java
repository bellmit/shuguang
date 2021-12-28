package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel("标识打印参数Form对象")
public class PrintParamForm {

    @ApiModelProperty(value = "待打印IDS")
    private List<String> ids;

    @ApiModelProperty(value = "打印规格 A 标签A B 标签B S 箱贴")
    private String label;

    @ApiModelProperty(value = "申请类型1国外2国内")
    private String applyType;
}
