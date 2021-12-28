package com.sofn.fdzem.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Created by sofn
 */
@TableName("SYS_USER")
@Data
public class SysUser extends User {

    /* ---------- 以下字段来自联表查询 ------------*/
    /**
     * 用户的角色信息
     */
    @TableField(exist = false)
    private List<SysRole> roleList;
    @TableField(exist = false)
    private List<SysRoleGroup> groupList;
    /**
     * 用户的角色对应的权限
     */
    @TableField(exist = false)
    private Set<String> resourceList;

    public SysUser() {
    }
}
