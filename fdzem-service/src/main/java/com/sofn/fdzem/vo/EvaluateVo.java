package com.sofn.fdzem.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("评价管理")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvaluateVo {
    /**
     * id
     */
    @JSONField(serializeUsing= ToStringSerializer.class)
    @ApiModelProperty("评价管理id")
    private String id;
    /**
     * 年份
     */
    @ApiModelProperty("年份")
    private String submitYear;
    /**
     * 监测站名称
     */
    @ApiModelProperty("监测站名称")
    private String name;
    /**
     * 所属区域
     */
    @ApiModelProperty("所属区域")
    private String area;
    /**
     * 所属海域
     */
    @ApiModelProperty("所属海域")
    private String seaArea;
    /**
     * 分数
     */
    @ApiModelProperty("分数")
    private String score;
    /**
     * 是否已评分1是0否
     */
    @ApiModelProperty("是否已评分1是0否")
    private Integer state;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String provinceCity;

    /**
     * 縣
     */
    @ApiModelProperty("县")
    private String countyTown;
}
