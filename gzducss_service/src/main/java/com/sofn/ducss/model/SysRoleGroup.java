package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.ducss.model.basemodel.BaseModel;
import lombok.Data;

@Data
@TableName("SYS_GROUP")
public class SysRoleGroup extends BaseModel<SysRoleGroup> {

    /**
     * 名称
     */
    private String groupName;
    /**
     * 描述
     */
    private String description;

}
