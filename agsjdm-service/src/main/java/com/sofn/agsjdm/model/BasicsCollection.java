package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 12:54
 */
@ApiModel("基础信息收集对象")
@Data
@TableName("BASICS_COLLECTION")
public class BasicsCollection {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;
    /**
     * 湿地区ID
     */
    @ApiModelProperty("湿地区ID")
    private String wetlandId;
    /**
     * 湿地区名称
     */
    @ApiModelProperty(value = "湿地区名称(前端新增/修改不用传)")
    @TableField(exist = false)
    private String wetlandName;
    /**
     * 目标物种名称
     */
    @ApiModelProperty("目标物种名称")
    private String specValue;
    /**
     * 数量
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("数量")
    private Double amount;
    /**
     * 生长状况
     */
    @ApiModelProperty("生长状况")
    private String grow;
    /**
     * 物种丰富度
     */
    @ApiModelProperty("物种丰富度")
    private String richness;
    /**
     * 伴生物种名称
     */
    @ApiModelProperty("伴生物种名称")
    private String comSpecies;
    /**
     * 伴生物种数量
     */
    @ApiModelProperty("伴生物种数量")
    private String speciesNumber;
    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;
    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private Date operatorTime;


    private String province;
    private String city;
    private String county;


}
