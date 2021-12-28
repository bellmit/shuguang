package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * @Description:  审核/退回表单
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("审核/退回表单")
public class ProcessForm implements Serializable {
    @NotBlank(message = "审核数据主键id必填")
    @Length(max = 32,message = "id长度超长！")
    @ApiModelProperty("审核/退回数据的主键id，如：证书审核中papersId的值")
    private String id;
    @Length(max = 200,message = "意见输入长度<=200！")
    @ApiModelProperty("意见")
    private String advice;

}
