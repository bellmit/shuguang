package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@ApiModel(value="工作项历史数据")
public class HisWorkItemVo {

    @ApiModelProperty(value = "工作项完成时间", required = true)
    private Date workItemCompleteTime;

    @ApiModelProperty(value = "工作项完成人", required = true)
    private String workItemCompleteUserName;

    @ApiModelProperty(value = "工作项接收时间", required = true)
    private Date workItemReceiveTime;

    @ApiModelProperty(value = "工作项是否回退", required = true)
    private boolean workItemIsBack;

    @ApiModelProperty(value = "工作项完成状态", required = true)
    private String workItemState;

    @ApiModelProperty(value = "工作项参数", required = true)
    private Map<String,Object> workItemParams;
}
