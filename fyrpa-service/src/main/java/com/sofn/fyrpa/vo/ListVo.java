package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListVo {

    @ApiModelProperty(name = "aquaticResourcesProtectionInfoVo",value ="水产保护区vo" )
    private AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo;

    @ApiModelProperty(name = "environmentResourcesVo",value ="环境资源vo" )
    private EnvironmentResourcesVo environmentResourcesVo;

    @ApiModelProperty(name = "managerOrgVo",value ="管理部门vo" )
    private ManagerOrgVo managerOrgVo;
}
