package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_basedata_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysBaseDataType extends BaseModel<SysBaseDataType> {
    @TableId(type= IdType.UUID)
    private String id;
    private String typename;
    private String typevalue;
    private String remark;
    private String enable;
}
