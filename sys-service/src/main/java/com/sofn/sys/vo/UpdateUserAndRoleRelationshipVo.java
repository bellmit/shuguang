package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2020/3/3 11:15
 */
@ApiModel(value="根据角色更新用户对象")
@Data
public class UpdateUserAndRoleRelationshipVo {

    @ApiModelProperty(value = "用户ID，多个使用英文逗号隔开，如果为空就进行删除操作")
    private String userIds;

    @ApiModelProperty(value = "角色ID")
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

}
