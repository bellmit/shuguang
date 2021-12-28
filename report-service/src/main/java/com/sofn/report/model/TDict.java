package com.sofn.report.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("t_dict")
@Data
public class TDict {

    @TableId(value = "dict_id", type = IdType.AUTO)
    private long dictId;
    private String dictName;
    private String dictValue;
}
