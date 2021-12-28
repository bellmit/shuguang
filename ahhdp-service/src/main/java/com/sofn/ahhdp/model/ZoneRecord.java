package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author yumao
 * @Date 2020/4/17 11:15
 **/
@ApiModel("保护区记录对象")
@Data
@TableName("ZONE_RECORD")
public class ZoneRecord {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "保护区名称")
    private String areaName;
    @ApiModelProperty(value = "新保护区名称")
    private String newName;
    @ApiModelProperty(value = "建设单位")
    private String company;
    @ApiModelProperty(value = "新建设单位")
    private String newCompany;
    @ApiModelProperty(value = "保护区范围")
    private String areaRange;
    @ApiModelProperty(value = "新保护区范围")
    private String newRange;
    @ApiModelProperty(value = "变更人")
    private String operator;
    @ApiModelProperty(value = "变更时间")
    private Date changeTime;

}
