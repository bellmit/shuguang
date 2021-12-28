package com.sofn.sys.vo;

import com.sofn.sys.model.SysResource;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

/**
 * @author heyongjie
 * @date 2019/12/18 16:14
 */
@Data
public class SysRoleVo {

    @ApiModelProperty("角色ID")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String describe;

    @ApiModelProperty("子系统ID")
    private String SubsystemId;

    @ApiModelProperty("子系统AppId")
    private String subsystemAppId;

    @ApiModelProperty("子系统名称")
    private String SubsystemName;

    @ApiModelProperty("状态标识")
    private String status;

    @ApiParam("角色码")
    private String roleCode;

    @ApiParam("角色拥有的资源ID并且,号隔开")
    private String resourceIds;

    @ApiParam("角色拥有的资源信息")
    private List<SysResource> resourceList;



}
