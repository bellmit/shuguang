package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value="组织机构代理对象")
@TableName("SYS_ORGANIZATION_AGENT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysOrganizationAgent {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "系统ID")
    private String subsystemId;

    @ApiModelProperty(value = "行政机构ID")
    private String orgId;

    @ApiModelProperty(value = "非行政机构ID")
    private String thirdOrgId;

    @ApiModelProperty(value = "代理标识")
    private String agentCode;

    public SysOrganizationAgent(String subsystemId, String orgId, String thirdOrgId, String agentCode) {
        this.subsystemId = subsystemId;
        this.orgId = orgId;
        this.thirdOrgId = thirdOrgId;
        this.agentCode = agentCode;
    }
}
