package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@TableName("SYS_USER_GROUP")
@Data
public class SysUserGroup implements Serializable {
    // id
    private String id;

    private String userId;

    private String groupId;

}
