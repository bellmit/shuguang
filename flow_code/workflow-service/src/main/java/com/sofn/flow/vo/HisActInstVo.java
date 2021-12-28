package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(value="活动实例历史数据")
public class HisActInstVo {

    @ApiModelProperty(value = "活动名称", required = true)
    private String actName;

    @ApiModelProperty(value = "活动定义ID", required = true)
    private String actDefId;

    @ApiModelProperty(value = "活动实例完成时间", required = true)
    private Date actInstCompleteTime;

    @ApiModelProperty(value = "活动实例完成人", required = true)
    private String actInstCompleteUserName;

    @ApiModelProperty(value = "活动实例接收时间", required = true)
    private Date actInstReceiveTime;

    @ApiModelProperty(value = "活动实例是否回退", required = true)
    private boolean actInstIsBack;

    @ApiModelProperty(value = "活动实例状态", required = true)
    private String actInstState;

    @ApiModelProperty(value = "活动实例参数", required = true)
    private Map<String,Object> actInstParams;

    @ApiModelProperty(value = "工作项列表")
    private List<HisWorkItemVo> workItemList;
}
