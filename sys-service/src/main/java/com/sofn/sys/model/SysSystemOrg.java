package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value="组织机构系统职能")
@TableName("SYS_SYSTEM_ORG")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysSystemOrg {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "系统APPID")
    private String appId;

    @ApiModelProperty(value = "机构ID")
    private String orgId;

}
