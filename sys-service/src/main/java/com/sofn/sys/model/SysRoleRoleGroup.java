package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@TableName("SYS_ROLE_GROUP")
@Data
public class SysRoleRoleGroup implements Serializable {
    // id
    private String id;

    private String roleId;

    private String groupId;

}
