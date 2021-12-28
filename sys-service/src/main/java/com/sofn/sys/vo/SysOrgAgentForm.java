package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *@Description 机构代理分配参数
 *@Author quzhijie
 *@Date 2019/10/14 16:43
 *@Version 1.0
 **/
@ApiModel(value="机构代理分配参数")
@Data
public class SysOrgAgentForm {

    @ApiModelProperty(value = "系统ID",required = true)
    @NotBlank(message = "系统ID不能为空")
    private String subsystemId;

    @ApiModelProperty(value = "系统名称，用于显示")
    private String subsystemName;

    @ApiModelProperty(value = "行政机构ID",required = true)
    @NotBlank(message = "行政机构ID不能为空")
    private String orgId;

    @ApiModelProperty(value = "代理机构标识")
    private String agentCode;

    @ApiModelProperty(value = "代理机构ID集合")
    private List<String> agentOrgIds;

    @ApiModelProperty(value = "代理机构ID和是否启用集合，KEY为机构ID，value为启用禁用状态")
    private List<SysOrgAgentStatusVo> sysOrgAgentStatusVos;
}
