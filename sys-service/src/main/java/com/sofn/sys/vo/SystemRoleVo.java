package com.sofn.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统和角色VO
 * @author heyongjie
 * @date 2020/9/2 14:29
 */
@Data
public class SystemRoleVo {

    @ApiModelProperty("系统ID")
    private String systemId;

    @ApiModelProperty("系统名称")
    private String systemName;

    @ApiModelProperty("系统代码")
    private String systemAppId;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色代码")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;


}
