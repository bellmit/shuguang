package com.sofn.ducss.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.ducss.model.StrawUtilizeDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StrawUtilizeCombinVo extends StrawUtilizeDetail implements Serializable {

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "department", value = "填报单位")
    private String department;

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

    @ApiModelProperty(value = "农作物类型")
    private String strawName;

    @ApiModelProperty(name = "fertilising", value = "肥料化")
    private BigDecimal fertilising = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty(name = "forage", value = "饲料化")
    private BigDecimal forage = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty(name = "fuel", value = "燃料化")
    private BigDecimal fuel = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty(name = "base", value = "基料化")
    private BigDecimal base = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty(name = "material", value = "原料化")
    private BigDecimal material = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty(name = "other", value = "外县购入")
    private BigDecimal other = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty(name = "ownCountry", value = "本县来源")
    private BigDecimal ownCountry = new BigDecimal(BigDecimal.ZERO.toString());

    @ApiModelProperty("县id")
    private Integer areaId;

    @ApiModelProperty("市id")
    private Integer cityId;

    @ApiModelProperty("省id")
    private Integer provinceId;

}
