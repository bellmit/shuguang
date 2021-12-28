package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CountryTaskMinistryVo implements Serializable {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "id,必须传值(新增的时候ID值传空字符串)")
    private String id;

    @Max(value = 2999, message = "年度不能大于2999")
    @Min(value = 1900, message = "年度不能小于1900")
    @NotNull(message = "所属年度不能为空")
    @ApiModelProperty(name = "year", value = "所属年度,如：2020")
    private String year;

    @NotNull(message = "预计填报数不能为空")
    @ApiModelProperty(name = "expectNum", value = "预计填报数,前端页面在输入框给出默认值120,用户可修改")
    private Integer expectNum;

}
