package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(value="活动实例历史数据")
public class HisActInstForm {

    @ApiModelProperty(value = "流程定义ID", required = true)
    private String defId;

    @ApiModelProperty(value = "业务id属性名称", required = true)
    private String idAttrName;

    @ApiModelProperty(value = "业务id属性值", required = true)
    private String idAttrValue;

    @ApiModelProperty(value = "属性", required = true)
    private Map<String, Object> attrs;

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小", required = true)
    private Integer pageSize;
}
