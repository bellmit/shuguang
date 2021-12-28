package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = " 生境类型表单对象")
public class HabitatTypeForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "资源调查ID")
    private String sourceId;
    @ApiModelProperty(value = "生境类型ID")
    private String habitatId;
    @ApiModelProperty(value = "生境类型名称")
    private String habitatValue;
}
