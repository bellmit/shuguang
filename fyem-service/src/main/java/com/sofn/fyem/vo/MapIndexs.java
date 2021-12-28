package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Description: 指标对象
 * @Author: DJH
 * @Date: 2020/8/6 11:13
 */
@ApiModel(value = "指标对象")
@Data
public class MapIndexs {

    @ApiModelProperty("指标代码，例如年限指标：year")
    private String code;

    @ApiModelProperty("指标描述，例如年限描述：年限")
    private String desc;

    @ApiModelProperty("指标数据种类之列表")
    private Map<String,String> valueList;
}
