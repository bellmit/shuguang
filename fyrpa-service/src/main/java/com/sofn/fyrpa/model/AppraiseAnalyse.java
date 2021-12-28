package com.sofn.fyrpa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("评价分析对象")
@Data
@TableName(value = "appraise_analyse")
public class AppraiseAnalyse {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "numericalValue",value ="数值" )
    private double numericalValue;

    @ApiModelProperty(name = "score",value ="得分" )
    private double score;

    @ApiModelProperty(name = "resourcesProtectionId",value ="保护区id" )
    private String resourcesProtectionId;

    @ApiModelProperty(name = "targetTwoManagerId",value ="二级指标id" )
    private String targetTwoManagerId;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    private Date createTime;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    @ApiModelProperty(name = "targetOneManagerId",value ="一级指标id" )
    private String targetOneManagerId;


}
