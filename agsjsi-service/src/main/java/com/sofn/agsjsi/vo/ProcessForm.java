package com.sofn.agsjsi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("审核表单")
public class ProcessForm {
    @NotBlank(message = "审核表单的id必须上传！")
    @Length(max = 32,message = "id长度超长！")
    @ApiModelProperty("审核数据id（列表中的id）")
    private String id;
    @Length(max = 255,message = "审核意见长度不能超过255！")
    @ApiModelProperty("审核意见，长度<=255")
    private String advice;
}
