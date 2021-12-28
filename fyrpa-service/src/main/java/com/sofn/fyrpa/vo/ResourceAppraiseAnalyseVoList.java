package com.sofn.fyrpa.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("保护区效果分析listVo")
@Data
public class ResourceAppraiseAnalyseVoList {

    @ApiModelProperty(name = "id",value ="保护区id" )
    private String id;

    @ApiModelProperty(name = "submitTime",value ="评价年度" )
    private String submitTime;

    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ApiModelProperty(name = "basinOrSeaArea",value ="所属流域或海区" )
    private String basinOrSeaArea;

    @ApiModelProperty(name = "approveTime",value ="当前级别批准时间" )
    @JSONField(format = "yyy-MM-dd")
    private Date approveTime;

    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String majorProtectObject;

    @ApiModelProperty(name = "managerOrgName",value ="保护区管理机构名称" )
    private String managerOrgName;

    @ApiModelProperty(name = "totalScore",value ="总评分" )
    private double totalScore;

    @ApiModelProperty(name = "isFlag",value ="是否启用" )
    private String isFlag;

    @ApiModelProperty(name = "lastTotalScore",value ="上个年度总评分" )
    private double lastTotalScore;
}
