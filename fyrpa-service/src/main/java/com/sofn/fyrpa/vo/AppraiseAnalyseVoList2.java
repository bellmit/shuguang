package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("评价分析listVo")
@Data
public class AppraiseAnalyseVoList2 {

    @ApiModelProperty(name = "targetOneName",value ="一级指标名称" )
    private String targetOneName;

    @ApiModelProperty(name = "targetOneId",value ="一级指标id" )
    private String targetOneId;

    private List<AppraiseAnalyseDetailsVo>appraiseAnalyseDetailsVoList;

}
