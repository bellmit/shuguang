package com.sofn.fyrpa.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("一级指标管理对象")
@Data
@TableName(value = "target_one_manager")
public class TargetOneManager {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "targetType",value ="指标类型(一级)" )
    private String targetType;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(name = "addPerson",value ="添加人" )
    private String addPerson;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;
}