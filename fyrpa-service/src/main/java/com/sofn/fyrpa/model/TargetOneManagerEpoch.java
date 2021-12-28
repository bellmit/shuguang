package com.sofn.fyrpa.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("一级指标历史数据对象")
@Data
@TableName(value = "target_one_manager_epoch")
public class TargetOneManagerEpoch {
    @ApiModelProperty(name = "targetId", value = "一级指标id")
    private String targetId;

    @ApiModelProperty(name = "targetName", value = "一级指标版本名称")
    private String targetName;

    @ApiModelProperty(name = "addPerson", value = "添加人")
    private String addPerson;

    @ApiModelProperty(name = "createTime", value = "对应版本创建时间")
    private Date createTime;
}
