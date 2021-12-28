package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author heyongjie
 * @date 2021/1/13 18:50
 */
@Data
@ApiModel(value="历史查询参数")
public class ProcessInstAllByAttrParam {

    @ApiModelProperty(value = "流程定义文件ID")
    private List<String> defIds;

    @ApiModelProperty(value = "主键名称")
    String idAttrName;

    @ApiModelProperty(value = "主键值")
    List<String> idAttrValues;

    @ApiModelProperty(value = "其他参数")
    private List<InstanceParamVo> instanceParamVos;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "从哪开始查询")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示多少条")
    private Integer pageSize;

}
