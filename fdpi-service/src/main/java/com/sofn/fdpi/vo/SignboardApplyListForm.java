package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 标识申请详细列表类
 *
 * @Author yumao
 * @Date 2019/1/2 17:29
 **/
@Data
@ApiModel(value = "标识申请列表表单对象")
public class SignboardApplyListForm  {

    @NotBlank(message = "申请ID不能为空")
    @ApiModelProperty(value = "申请ID")
    private String applyId;
    @NotBlank(message = "标识ID不能为空")
    @ApiModelProperty(value = "标识ID(必填)")
    private String signboardId;
    @NotBlank(message = "标识状态不能为空")
    @ApiModelProperty(value = "标识状态(必填)")
    private String status;


}
