package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 流程实例历史数据
 * @author quzhijie
 * @date 2020-12-31
 */
@Data
@ApiModel(value="流程实例历史数据")
public class HisProcInstVo {

    @ApiModelProperty(value = "流程定义ID")
    private String defId;

    @ApiModelProperty(value = "业务主键属性名称")
    private String idAttrName;

    @ApiModelProperty(value = "业务主键属性值")
    private String idAttrValue;

    @ApiModelProperty(value = "历史活动实例列表")
    private List<HisActInstVo> actInstList;

    @ApiModelProperty(value = "实例参数")
    private Map<String,Object> instParams;
}
