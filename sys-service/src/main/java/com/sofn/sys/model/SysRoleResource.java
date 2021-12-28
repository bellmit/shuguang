package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("SYS_ROLE_RESOURCE")
@Data
public class SysRoleResource {
    // id
    private String id;

    private String roleId;

    private String resourceId;
    public  SysRoleResource(String id,String roleId,String resourceId) {
        this.id = id;
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
    public SysRoleResource(){}

}
