package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 威胁因素基础信息类
 *
 * @Author yumao
 * @Date 2020/3/4 9:29
 **/
@Data
@TableName("THREAT_FACTOR")
@ApiModel(value = "威胁因素基础信息对象")
public class ThreatFactor {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectValue;
    @ApiModelProperty(value = "采挖受损面积")
//    @TableField(strategy = FieldStrategy.IGNORED)
    private Double excavation;
    @ApiModelProperty(value = "放牧受损面积")
    private String graze;
    @ApiModelProperty(value = "偷牧受损面积")
    private String stealing;
    @ApiModelProperty(value = "砍伐受损面积")
    private String felling;
    @ApiModelProperty(value = "火烧受损面积")
    private String destroyFire;
    @ApiModelProperty(value = "废渣数量及描述")
    private String wasteResidue;
    @ApiModelProperty(value = "道路数量及描述")
    private String roads;
    @ApiModelProperty(value = "厂矿数量及描述")
    private String factoriesMines;
    @ApiModelProperty(value = "建筑数量及描述")
    private String builds;
    @ApiModelProperty(value = "设施数量及描述")
    private String facilities;
    @ApiModelProperty(value = "操作人")
    private String inputer;
    @ApiModelProperty("操作时间")
    private Date inputerTime;

}
