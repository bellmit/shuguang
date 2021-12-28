package com.sofn.fdzem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/19 9:13
 * Description:
 * Version: V1.0
 */
@Data
@TableName("tb_indexs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Index {
    @TableId
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("指标管理主键id")
    private Long id;
    /**
     * 指标类型
     */
    @ApiModelProperty("指标类型")
    private String indexType;
    /**
     * 上级id
     */
    @ApiModelProperty("上级id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long parentId;
    /**
     * 所属指标
     */
    @TableField(exist = false)
    @ApiModelProperty("所属指标")
    private String parentName;
    /**
     * 指标名称
     */
    @ApiModelProperty("指标名称")
    private String indexName;
    /**
     * 参考值
     */
    @ApiModelProperty("参考值")
    private String consultValue;
    /**
     * 分数
     */
    @ApiModelProperty("分值")
    private Double score;
    /**
     * 添加人
     */
    @ApiModelProperty("添加人")
    private String operator;
    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date addDate;
    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用（1是0否）")
    private int state;
    /**
     * 二级菜单
     */
    @TableField(exist = false)
    @ApiModelProperty("二级菜单")
    private List<Index> indexList;
}
