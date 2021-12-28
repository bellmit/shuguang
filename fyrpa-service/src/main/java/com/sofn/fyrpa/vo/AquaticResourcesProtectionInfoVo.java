package com.sofn.fyrpa.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("水产种质资源保护区信息vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AquaticResourcesProtectionInfoVo {

    @ApiModelProperty(name = "regionCode",value ="保护区所在的地区code" )
    private String []regionCode;

    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ApiModelProperty(name = "departmentManager",value ="主管部门" )
    private String departmentManager;

    @ApiModelProperty(name = "approveTime",value ="当前级别批准时间" )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String approveTime;

    @ApiModelProperty(name = "approveDocNumber",value ="当前级别批准文号" )
    private String approveDocNumber;

    @ApiModelProperty(name = "isAdjust",value ="保护区设立以来是否有过调整" )
    private String isAdjust;

    @ApiModelProperty(name = "adjustTime",value ="调整时间" )
    private String adjustTime;

    @ApiModelProperty(name = "fileIds",value ="与文件上传相关的ids" )
    private String fileIds;

    @ApiModelProperty(name = "submitTime",value ="报送时间" )
    private String submitTime;

    @ApiModelProperty(name = "fileName",value ="上传文件名称" )
    private String fileName;

    @ApiModelProperty(name = "reportTime",value ="上报时间" )
    private String reportTime;

}