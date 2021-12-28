package com.sofn.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色组VO
 * @author heyongjie
 * @date 2019/12/19 16:11
 */
@Data
public class SysRoleGroupVo {

    @ApiModelProperty("角色组ID")
    private String id;


    @ApiModelProperty("角色组名称")
    private String groupName;

    @ApiModelProperty("角色组描述")
    private String description;

    @ApiModelProperty("角色组包含的角色Id(用\",\"分隔)")
    private String roleIds;

    @ApiModelProperty("角色组包含的角色名称(用\",\"分隔)")
    private String roleNames;


}
