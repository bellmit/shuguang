package com.sofn.fdzem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/28 9:13
 * Description:
 * Version: V1.0
 */
@Data
@TableName("tb_distribute")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Distribute {
    @TableId
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("区域监测中心管理id")
    private Long id;
    /**
     * 年度
     */
    @ApiModelProperty("区域监测中心名称")
    private String name;
    /**
     * 监测中心id列表
     */
    @TableField(exist = false)
    @ApiModelProperty("监测中心id列表")
    private List<Long> monitroIds;

}
