package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_basedata")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysBaseData extends BaseModel<SysBaseData> {

    @TableId(type= IdType.UUID)
    private String id;
    private SysBaseDataType sysBaseDataType;
    private String BaseDataname;
    private String BaseDatacode;
    private String enable;
    private String basedatatypeid;
    private String remark;
}
