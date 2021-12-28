package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_menu")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu extends BaseModel<SysMenu> {

    private String parentId;
    private String name;
    private String type;
    private String icon;
    private String title;
    private Integer level;
    private Integer order;


}
