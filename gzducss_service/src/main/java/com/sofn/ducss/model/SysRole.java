package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.ducss.model.basemodel.BaseModel;
import lombok.Data;

/**
 * Created by sofn
 */
@TableName("SYS_ROLE")
@Data
public class SysRole extends BaseModel<SysRole> {

    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    private String roleName;
    private String subsystemId;
    /**
     * 角色描述,UI界面显示使用
     */
    @TableField(value = "`desc`", exist = false)
    private String desc;

    @TableField(value = "`describe`")
    private String describe;

    private String status;

    /**
     * 角色码
     */
    private String roleCode;

    public SysRole() {
    }

    public SysRole(String roleName) {
    }
}
