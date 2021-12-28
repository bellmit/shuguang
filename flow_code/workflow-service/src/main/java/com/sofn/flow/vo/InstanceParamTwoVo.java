package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author liling
 * @date 2021/1/13 18:30
 */
@Data
@ApiModel(value="流程参数查询实体")
public class InstanceParamTwoVo {

    @ApiModelProperty(value = "参数key")
    private String key;

    @ApiModelProperty(value = "参数值，如果是in请使用英文逗号隔开")
    private List<String> values;

    @ApiModelProperty(value = "查询是否使用in")
    private String valueIsIn;


}
