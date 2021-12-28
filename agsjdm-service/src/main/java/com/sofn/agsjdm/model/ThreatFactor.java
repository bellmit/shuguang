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
 * @Date: 2020-04-13 15:04
 */
@ApiModel("威胁因素基础信息对象")
@Data
@TableName("THREAT_FACTOR")
public class ThreatFactor {
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
     * 基地城市化影响面积
     */
    @ApiModelProperty("基地城市化影响面积")
    private String cityUrbanization;
    /**
     * 围垦影响面积
     */
    @ApiModelProperty("围垦影响面积")
    private String reclamation;
    /**
     * 沙泥淤积影响面积
     */
    @ApiModelProperty("沙泥淤积影响面积")
    private String silt;
    /**
     * 污染影响面积
     */
    @ApiModelProperty("污染影响面积")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double pollute;
    /**
     * 捕捞和采集影响面积
     */
    @ApiModelProperty("捕捞和采集影响面积")
    private String fishingHarvesting;
    /**
     * 非法狩猎影响面积
     */
    @ApiModelProperty("非法狩猎影响面积")
    private String illegalHuntingue;


    /**
     * 盐碱化面积
     */
    @ApiModelProperty("盐碱化面积")
    private String salinization;
    /**
     * 外来物种入侵影响面积
     */
    @ApiModelProperty("外来物种入侵影响面积")
    private String alienSpecies;
    /**
     * 森林过度砍伐影响面积
     */
    @ApiModelProperty("森林过度砍伐影响面积")
    private String deforestation;
    /**
     * 沙化影响面积
     */
    @ApiModelProperty("沙化影响面积")
    private String desertification;
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
