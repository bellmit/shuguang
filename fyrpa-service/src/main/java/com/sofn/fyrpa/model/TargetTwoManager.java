package com.sofn.fyrpa.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("二级指标管理对象")
@Data
@TableName(value = "target_two_manager")
public class TargetTwoManager {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "targetType",value ="指标类型(二级)" )
    private String targetType;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "addPerson",value ="添加人" )
    private String addPerson;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "targetOneManagerId",value ="一级指标id" )
    private String targetOneManagerId;

    @ApiModelProperty(name = "isTargetName",value ="所属的一级指标名称" )
    private String isTargetName;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;
}