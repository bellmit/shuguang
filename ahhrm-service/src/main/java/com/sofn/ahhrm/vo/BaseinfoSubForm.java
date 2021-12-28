package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@ApiModel(value = "基础信息子表表单对象")
public class BaseinfoSubForm {

    @ApiModelProperty(value = "品种")
    private String variety;
    @ApiModelProperty(value = "群体数量(个)")
    @Max(value = 99999,message = "群体数量(个)不能超过99999")
    private Integer amount;
    @ApiModelProperty(value = "产地与分布")
    private String originDistribution;
    @ApiModelProperty(value = "公母比例")
    private String proportion;
    @Max(value = 99999,message = "有效群体含量不能超过99999")
    @ApiModelProperty(value = "有效群体含量")
    private Double effectiveGroup;
    @ApiModelProperty(value = "性能指标")
    private String perIndex;
}
