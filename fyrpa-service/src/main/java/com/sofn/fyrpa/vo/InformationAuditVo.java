package com.sofn.fyrpa.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("信息审核对象vo")
@Data
public class InformationAuditVo {

    @ApiModelProperty(name = "auditMind",value ="审核意见" )
    private String auditMind;

    @ApiModelProperty(name = "checker",value ="审核人" )
    private String checker;

    @ApiModelProperty(name = "auditUnit",value ="审核单位" )
    private String auditUnit;


}