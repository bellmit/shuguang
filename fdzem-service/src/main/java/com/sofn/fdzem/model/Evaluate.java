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
@TableName("tb_evaluate")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evaluate {
    @TableId
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("评分主键id")
    private Long id;
    /**
     * 年度
     */
    @ApiModelProperty("年度")
  /*  @JSONField(format = "yyyy")
    @JsonFormat(pattern = "yyyy" ,timezone = "GMT+8")*/
    private String submitYear;
    /**
     * 总分
     */
    @ApiModelProperty("总分")
    private Double score;
    /**
     * 对应监测站ID
     */
    @ApiModelProperty("对应监测站ID")
    private String monitorId;
    /**
     * 分数记录
     */
    @ApiModelProperty("分数记录")
    private String scoreRecord;
}
