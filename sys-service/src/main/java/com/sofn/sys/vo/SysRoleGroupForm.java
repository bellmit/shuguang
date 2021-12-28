package com.sofn.sys.vo;

import com.sofn.sys.model.SysRoleGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysRoleGroupForm {

    /**
     * 编号
     */
    @ApiModelProperty("角色组ID")
    @Length(max = 32,message = "角色组ID超长")
    private String id;

    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    @ApiModelProperty("角色组名称")
    @NotBlank(message = "角色组标识不能为空")
    private String groupName;
    /**
     * 角色描述,UI界面显示使用
     */
    @ApiModelProperty("角色组描述")
    @Length(max = 100,message = "角色组描述最多100个字符")
    private String description;
    /**
     * 拥有的资源
     */
    @ApiModelProperty("角色组包含的角色Id(用\",\"分隔)")
    private String roleIds;
    @ApiModelProperty("角色组包含的角色名称(用\",\"分隔)")
    private String roleNames;

   /* @ApiModelProperty("角色组包含的子系统Id(用\",\"分隔)")
    private String subsystemIds;
    @ApiModelProperty("角色组包含的子系统名称(用\",\"分隔)")
    private String subsystemNames;*/
    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    @ApiModelProperty("删除标识Y/N")
    private String delFlag;
    // 创建人
    @ApiModelProperty("创建人ID")
    private String createUserId;
    // 创建时间
    @ApiModelProperty("创建时间")
    private Date createTime;
    // 更新人
    @ApiModelProperty("修改人ID")
    private String updateUserId;
    // 更新时间
    @ApiModelProperty("修改时间")
    private Date updateTime;

    public SysRoleGroupForm() {}
    public SysRoleGroupForm(SysRoleGroup group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.description = group.getDescription();
        this.delFlag = group.getDelFlag();
    }

    public static SysRoleGroup getSysGroup(SysRoleGroupForm sysGroupForm){
        SysRoleGroup sysGroup = new SysRoleGroup();
        BeanUtils.copyProperties(sysGroupForm,sysGroup);
        return sysGroup;
    }

}
