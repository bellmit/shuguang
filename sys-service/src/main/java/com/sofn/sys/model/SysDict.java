package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_dict")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDict extends BaseModel<SysDict> {

    @TableId(type=IdType.UUID)
    private String id;
    @TableField(exist = false)
    private SysDictType sysDictType;
    private String dicttypeid;
    private String dictname;
    private String dictcode;
    private String enable;
    private String remark;
  /*  private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;*/
}
