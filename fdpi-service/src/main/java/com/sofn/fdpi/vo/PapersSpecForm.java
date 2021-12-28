package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-23 11:01
 */
@ApiModel(value = "证书物种表单对象")
@Data
public class PapersSpecForm {
    @ApiModelProperty(value = "主键，新增不传")
    private String id;
    @ApiModelProperty(value = "证书id，新增不传")
    private String papersId;
    @ApiModelProperty(value = "物种id")
    private String specId;
    @Length(max = 30, message = "来源长度不能超过30")
    @ApiModelProperty(value = "来源")
    private String source;
    @Length(max = 30, message = "方式不能超过30")
    @ApiModelProperty(value = "方式")
    private String mode;
    @Min(value = 0, message = "最小值不得小于0")
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "单位")
    private String unit;
}
