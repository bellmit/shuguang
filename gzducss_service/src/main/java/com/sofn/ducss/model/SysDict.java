package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.ducss.model.basemodel.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@TableName("sys_dict")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDict extends BaseModel<SysDict> {

    @TableId(type = IdType.UUID)
    private String id;
    @TableField(exist = false)
    private SysDictType sysDictType;
    private String dicttypeid;
    private String dictname;
    private String dictcode;
    private String enable;
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
  /*  private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;*/

    private String delFlag;

    @TableField(exist = false)
    private String createName;
}
