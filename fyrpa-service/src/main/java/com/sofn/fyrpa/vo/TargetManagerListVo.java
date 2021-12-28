package com.sofn.fyrpa.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fyrpa.model.TargetOneManager;
import com.sofn.fyrpa.model.TargetTwoManager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel("指标集合vo类")
@Data
public class TargetManagerListVo {

    @ApiModelProperty(name = "id",value ="id" )
    private String id;

    @ApiModelProperty(name = "targetName",value ="指标名称" )
    private String targetName;

    @ApiModelProperty(name = "targetType",value ="指标类型" )
    private String targetType;

    @ApiModelProperty(name = "addPerson",value ="添加人" )
    private String addPerson;

    @ApiModelProperty(name = "createTime",value ="添加时间" )
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(name = "referenceValue",value ="参考值" )
    private String referenceValue;

    @ApiModelProperty(name = "scoreValue",value ="分值" )
    private String scoreValue;

    @ApiModelProperty(name = "status",value ="启动或停用" )
    private String status;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    @ApiModelProperty(name = "isTargetName",value ="所属指标" )
    private String isTargetName;

}
