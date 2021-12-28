package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("disperse_utilize")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DisperseUtilize extends Model<DisperseUtilize> {
    private String id;

    @ApiModelProperty(name = "fillNo", value = "填报编号")
    private String fillNo;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "areaId", value = "县ID")
    private String areaId;

    @TableField(exist=false)
    @ApiModelProperty(name = "areaName", value = "区划名字")
    private String areaName;

    @ApiModelProperty(name = "reportArea", value = "填报单位")
    private String reportArea;

    @ApiModelProperty(name = "address", value = "详细地址")
    private String address;

    @ApiModelProperty(name = "farmerNo", value = "农户序号")
    private String farmerNo;

    @ApiModelProperty(name = "farmerName", value = "户主姓名")
    private String farmerName;

    @ApiModelProperty(name = "farmerPhone", value = "户主电话")
    private String farmerPhone;

    @ApiModelProperty(name = "createUserId", value = "创建者id", hidden = true)
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建者昵称")
    private String createUserName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(name = "provinceId", value = "省ID")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市ID")
    private String cityId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

}