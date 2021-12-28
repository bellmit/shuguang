package com.sofn.fyem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "水生生物增殖放流基础数据")
@TableName("BASIC_PROLIFERATION_RELEASE")
@Data
public class BasicProliferationRelease extends Model<BasicProliferationRelease> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @NotBlank(message = "上报年度不能为空")
    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @NotBlank(message = "流放地点不能为空")
    @ApiModelProperty(name = "releaseSite", value = "流放地点")
    private String releaseSite;

    @NotBlank(message = "省级id不能为空")
    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @NotBlank(message = "市级id不能为空")
    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @NotBlank(message = "区县id不能为空")
    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @NotBlank(message = "经度不能为空")
    @Digits(integer = 100,fraction = 6)
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @NotBlank(message = "纬度不能为空")
    @Digits(integer = 100,fraction = 6)
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @NotNull(message = "流放时间不能为空")
    @JSONField(format = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(name = "releaseTime", value = "流放时间")
    private Date releaseTime;

    @NotNull(message = "流放资金不能为空")
    @Digits(integer = 100,fraction = 2)
    @ApiModelProperty(name = "releaseMoney", value = "流放资金(万元)")
    private Double releaseMoney;

    @ApiModelProperty(name = "organizationId", value = "组织id")
    private String organizationId;

    @NotBlank(message = "组织名称不能为空")
    @ApiModelProperty(name = "organizationName", value = "组织名称")
    private String organizationName;

    @NotBlank(message = "流放级别不能为空")
    @ApiModelProperty(name = "releaseLevel", value = "流放级别")
    private String releaseLevel;

    @NotBlank(message = "流放品种不能为空")
    @ApiModelProperty(name = "releaseVarieties", value = "流放品种")
    private String releaseVarieties;

    @NotNull(message = "流放数量不能为空")
    @Digits(integer = 100,fraction = 4)
    @ApiModelProperty(name = "releaseNumber", value = "流放数量(万尾)")
    private Double releaseNumber;

    @NotNull(message = "流放规格不能为空")
    @Digits(integer = 100,fraction = 2)
    @ApiModelProperty(name = "releaseSpecification", value = "流放规格")
    private Double releaseSpecification;

    @NotNull(message = "中央投资不能为空")
    @Digits(integer = 100,fraction = 2)
    @ApiModelProperty(name = "nationInvestment", value = "中央投资")
    private Double nationInvestment;

    @NotNull(message = "省级投资不能为空")
    @Digits(integer = 100,fraction = 2)
    @ApiModelProperty(name = "provinceInvestment", value = "省级投资")
    private Double provinceInvestment;

    @NotNull(message = "市县投资不能为空")
    @Digits(integer = 100,fraction = 2)
    @ApiModelProperty(name = "cityInvestment", value = "市县投资")
    private Double cityInvestment;

    @NotNull(message = "社会投资不能为空")
    @Digits(integer = 100,fraction = 2)
    @ApiModelProperty(name = "societyInvestment", value = "社会投资")
    private Double societyInvestment;

    @ApiModelProperty(name = "provideOrganizationId", value = "供苗单位id")
    private String provideOrganizationId;

    @NotBlank(message = "供苗单位名称不能为空")
    @ApiModelProperty(name = "provideOrganizationName", value = "供苗单位名称")
    private String provideOrganizationName;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "accessory", value = "附件")
    private String accessory;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "releaseEvaluate", value = "放流效果评价")
    private Double releaseEvaluate;

    @ApiModelProperty(name = "roleCode", value = "角色code")
    private String roleCode;

    @ApiModelProperty(name = "createUserId", value = "填报人id")
    private String createUserId;

    @ApiModelProperty(name = "createOrganizationName", value = "填报单位名称")
    private String createOrganizationName;

    @ApiModelProperty(name = "createOrganizationId", value = "填报单位id")
    private String createOrganizationId;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "填报时间")
    private Date createTime;

    public BasicProliferationRelease() {
    }
}