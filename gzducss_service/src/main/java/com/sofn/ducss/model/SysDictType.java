package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.ducss.model.basemodel.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_dict_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDictType extends BaseModel<SysDictType> {

    @TableId(type= IdType.UUID)
    private String id;
    private String typename;
    private String typevalue;
   /* private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String delFlag;*/
    private String remark;
    private String enable;

}
