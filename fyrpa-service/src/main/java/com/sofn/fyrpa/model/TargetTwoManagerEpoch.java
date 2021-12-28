package com.sofn.fyrpa.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("二级指标历史数据对象")
@Data
@TableName(value = "target_two_manager_epoch")
public class TargetTwoManagerEpoch {

    @ApiModelProperty(name = "target_id",value ="二级指标id" )
    private String targetId;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;

    @ApiModelProperty(name = "targetOneManagerId", value = "所属一级指标id")
    private String targetOneManagerId;

    @ApiModelProperty(name = "addPerson", value = "添加人")
    private String addPerson;

    @ApiModelProperty(name = "createTime", value = "当前版本创建时间")
    private Date createTime;
}