package com.sofn.ducss.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("straw_utilize")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class StrawUtilize extends Model<StrawUtilize> {
    private String id;

    @ApiModelProperty(name = "fillNo", value = "填报编号")
    private String fillNo;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "provinceId", value = "省ID")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市ID")
    private String cityId;

    @ApiModelProperty(name = "areaId", value = "县ID")
    private String areaId;

    @TableField(exist = false)
    @ApiModelProperty(name = "areaName", value = "区划名字")
    private String areaName;

    @ApiModelProperty(name = "reportArea", value = "填报单位")
    private String reportArea;

    @ApiModelProperty(name = "address", value = "详细地址")
    private String address;

    @ApiModelProperty(name = "mainNo", value = "市场主体序号")
    private String mainNo;

    @ApiModelProperty(name = "mainName", value = "市场主体名称")
    private String mainName;

    @ApiModelProperty(name = "corporationName", value = "法人名称")
    private String corporationName;

    @ApiModelProperty(name = "companyPhone", value = "单位电话")
    private String companyPhone;

    @ApiModelProperty(name = "mobilePhone", value = "手机号码")
    private String mobilePhone;

    @ApiModelProperty(name = "createUserId", value = "创建者id", hidden = true)
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建者昵称")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(exist = false)
    @ApiModelProperty(name = "areaName", value = "区划id")
    private String lastCode;

    @TableField(exist = false)
    private BigDecimal marketEnt;
}