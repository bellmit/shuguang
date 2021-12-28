package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:23
 **/
@Data
@ApiModel(value = "标识流程表单对象")
public class SignboardProcessForm {

    @NotBlank(message = "申请ID不能为空")
    @ApiModelProperty(value = "申请ID")
    private String applyId;

    @Size(max = 1, message = "审核状态值过长 3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过")
    @NotBlank(message = "审核状态不能为空")
    @ApiModelProperty(value = "审核状态 1未上报2已上报3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过")
    private String status;

    @Size(max = 100, message = "审核意见不能操作100")
    @ApiModelProperty(value = "审核意见")
    private String advice;

    @ApiModelProperty(value = "申请单号")
    private String applyCode;

}
