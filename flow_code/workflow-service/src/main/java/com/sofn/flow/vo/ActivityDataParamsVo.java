package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author liling
 * @date 2021/1/13 18:50
 */
@Data
@ApiModel(value="流程活动查询参数")
public class ActivityDataParamsVo {

    @ApiModelProperty(value = "流程定义文件ID")
    private String defId;

    @ApiModelProperty(value = "主键名称")
    String idAttrName;

    @ApiModelProperty(value = "操作人")
    String activityMaker;

    @ApiModelProperty(value = "主键值")
    List<String> idAttrValues;

    @ApiModelProperty(value = "其他参数")
    private List<InstanceParamTwoVo> instanceParamVos;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "从哪开始查询")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示多少条")
    private Integer pageSize;

}
