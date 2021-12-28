package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
*@Author Chlf
*@Description //年度任务添加vo
*@Date 16:43 2020/4/28
*@Param
*@Return
**/
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CountryTaskVo implements Serializable {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "id,必须传值")
    private String id;

    @NotNull(message = "预计填报数不能为空")
    @ApiModelProperty(name = "expectNum", value = "预计填报数,前端页面在输入框给出默认值120,用户可修改")
    private Integer expectNum;



}
