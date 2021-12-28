package com.sofn.fyrpa.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("详情对象vo")
@Data
public class AppraiseAnalyseDetailsVo {
    @ApiModelProperty(name = "numericalValue",value ="数值" )
    private double numericalValue;

    @ApiModelProperty(name = "score",value ="得分" )
    private double score;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;

    @ApiModelProperty(name = "targetTwoName",value ="二级指标名称" )
    private String targetTwoName;

    @ApiModelProperty(name = "targetTwoId",value ="二级指标id" )
    private String targetTwoId;

    @ApiModelProperty(name = "targetOneId",value ="一级指标id" )
    private String targetOneId;

    @ApiModelProperty(name = "targetOneName",value ="一级指标名称" )
    private String targetOneName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "appraiseTime", value = "评论时间")
    private Date appraiseTime;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "targetUpdateTime", value = "指标修改时间")
    private Date targetUpdateTime;
}
