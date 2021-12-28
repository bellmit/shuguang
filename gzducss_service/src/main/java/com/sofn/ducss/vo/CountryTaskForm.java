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
*@Description //年度任务修改vo
*@Date 14:00 2020/4/29
*@Param
*@Return
**/
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CountryTaskForm implements Serializable {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "id,必须传值")
    private Integer id;

    @NotNull(message = "year不能为空")
    @ApiModelProperty(name = "year", value = "所属年度,int数据类型,不可修改")
    private Integer year;

    @NotNull(message = "预计填报数不能为空")
    @ApiModelProperty(name = "expectNum", value = "【可修改】预计填报数,前端页面在输入框给出默认值120")
    private Integer expectNum;

//    @NotNull(message = "总农户数不能为空")
//    @ApiModelProperty(name = "peasantTotal", value = "【可修改】总农户数")
//    private Integer peasantTotal;

    @NotNull(message = "状态值不能为空")
    @ApiModelProperty(name = "status", value = "状态值0-保存（未上报）;不可修改")
    private Byte status;

    @ApiModelProperty(name = "factNum", value = "实际填报数;不可修改")
    private Integer factNum;

    @ApiModelProperty(name = "mainNum", value = "主体个数;不可修改")
    private Integer mainNum;

    @NotBlank(message = "创建者昵称不能为空")
    @Size(max = 255,min = 1,message = "创建者昵称长度不符合要求")
    @ApiModelProperty(name = "createUserName", value = "创建者昵称,不可修改,前端显示即可")
    private String createUserName;


}
