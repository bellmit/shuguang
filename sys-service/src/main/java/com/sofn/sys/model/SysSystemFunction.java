package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value="组织机构系统职能")
@TableName("SYS_SYSTEM_FUNCTION")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysSystemFunction {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "系统APPID")
    private String appId;

    @ApiModelProperty(value = "机构ID")
    private String orgId;

    @ApiModelProperty(value = "机构职能代码ID")
    private String functionCode;

    @ApiModelProperty(value = "机构职能代码")
    @TableField(exist = false)
    private String functionDictCode;

}
