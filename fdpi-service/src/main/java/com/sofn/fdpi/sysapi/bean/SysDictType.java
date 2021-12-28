package com.sofn.fdpi.sysapi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class SysDictType   {

    private String id;
    private String typename;
    private String typevalue;
    private String remark;
    private String enable;

}
