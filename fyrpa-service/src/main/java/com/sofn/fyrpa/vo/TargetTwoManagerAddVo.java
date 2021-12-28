package com.sofn.fyrpa.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("二级指标管理对象添加的vo类")
@Data
public class TargetTwoManagerAddVo {

    @ApiModelProperty(name = "targetType",value ="指标类型(二级指标)" )
    private String targetType;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "addPerson",value ="添加人" )
    private String addPerson;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;

    @ApiModelProperty(name = "isTargetName",value ="所属的一级指标名称" )
    private String isTargetName;
}