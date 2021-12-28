package com.sofn.fdpi.sysapi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class SysDict extends BaseModel<SysDict> {

    private String id;
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
