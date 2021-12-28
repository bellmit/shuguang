package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2020/3/3 17:25
 */
@ApiModel(value = "保存角色组和用户关系")
@Data
public class SaveUserAndRoleGroupRelationshipVo {

    @ApiModelProperty("用户ID，用英文逗号隔开")
    private String userIds;

    @ApiModelProperty("角色组ID")
    @NotBlank(message = "角色组ID不能为空")
    private String roleGroupId;

}
