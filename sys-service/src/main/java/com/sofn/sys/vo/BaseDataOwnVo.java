package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *@Author quzhijie
 *@Date 2019/10/14 16:43
 *@Version 1.0
 **/
@Data
@ApiModel(value="分配给子系统基础数据参数")
public class BaseDataOwnVo {

    @ApiModelProperty(value = "系统APPID",required = true)
    @NotBlank(message = "系统AppID不能为空")
    private String appId;

    @ApiModelProperty(value = "基础数据类型ID",required = true)
    @NotBlank(message = "基础数据类型ID不能为空")
    private String dataBaseTypeId;

    @ApiModelProperty(value = "基础数据ID集合")
    private List<String> dataBaseIds;
}
