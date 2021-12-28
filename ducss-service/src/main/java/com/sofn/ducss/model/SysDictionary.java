package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@TableName("sys_dictionary")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysDictionary extends Model<SysDictionary> {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "id")
    private Integer id;

    @ApiModelProperty(name = "dictType", value = "字典类型")
    private String dictType;

    @ApiModelProperty(name = "dictKey", value = "字典key")
    private String dictKey;

    @ApiModelProperty(name = "dictValue", value = "字典Value")
    private String dictValue;

    @ApiModelProperty(name = "orderNo", value = "排序")
    private Integer orderNo;
}
