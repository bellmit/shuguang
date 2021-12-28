package com.sofn.fyem.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description: 统计图例-放流区位置分布Vo
 * @Author: DJH
 * @Date: 2020/5/9 14:13
 */
@ApiModel(value = "ProliferationReleaseLocationDistributionVo", description = "放流区位置分布Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProliferationReleaseLocationDistributionVo {

    @ApiModelProperty(name = "region", value = "区域")
    private String region;

    @ApiModelProperty(name = "regionName", value = "区域名")
    private String regionName;

    @ApiModelProperty(name = "releaseCountTotal", value = "放流总次数")
    private String releaseCountTotal;

    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @ApiModelProperty(name = "releaseSite", value = "流放地点")
    private String releaseSite;

    @JSONField(format = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(name = "releaseTime", value = "流放时间")
    private Date releaseTime;

    @ApiModelProperty(name = "releaseMoney", value = "流放资金(万元)")
    private Double releaseMoney;

    @ApiModelProperty(name = "organizationName", value = "组织名称")
    private String organizationName;

    @ApiModelProperty(name = "releaseLevel", value = "流放级别")
    private String releaseLevel;

    @ApiModelProperty(name = "releaseVarieties", value = "流放品种")
    private String releaseVarieties;

    @ApiModelProperty(name = "releaseNumber", value = "流放数量(万尾)")
    private Double releaseNumber;

    @ApiModelProperty(name = "releaseSpecification", value = "流放规格")
    private Double releaseSpecification;

    @ApiModelProperty(name = "provideOrganizationName", value = "供苗单位名称")
    private String provideOrganizationName;

    @ApiModelProperty(name = "nationInvestment", value = "中央投资")
    private String nationInvestment;

    @ApiModelProperty(name = "provinceInvestment", value = "省级投资")
    private String provinceInvestment;

    @ApiModelProperty(name = "cityInvestment", value = "市县投资")
    private String cityInvestment;

    @ApiModelProperty(name = "societyInvestment", value = "社会投资")
    private String societyInvestment;

    @ApiModelProperty(name = "investmentTotal", value = "投入资金")
    private String investmentTotal;

    @ApiModelProperty(name = "releaseNumberTotal", value = "放流数量")
    private String releaseNumberTotal;

    @ApiModelProperty(name = "releaseOther", value = "其他放流")
    private String releaseOther;

    @ApiModelProperty(name = "releaseCity", value = "市级放流")
    private String releaseCity;

    @ApiModelProperty(name = "releaseProv", value = "省级放流")
    private String releaseProv;

    @ApiModelProperty(name = "releaseCountry", value = "国家级放流")
    private String releaseCountry;
}
