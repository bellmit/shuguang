package com.sofn.dhhrp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "基础信息表单对象")
public class BaseinfoForm {

    @ApiModelProperty(value = "主键")
    private String id;

    @NotBlank(message = "检测点名称不能为空")
    @ApiModelProperty(value = "监测点名称")
    private String pointName;

    @NotBlank(message = "监测年份不能为空")
    @ApiModelProperty(value = "监测年份")
    private String year;

    @NotBlank(message = "省不能为空")
    @ApiModelProperty(value = "省", example = "510000")
    private String province;

    @NotBlank(message = "市不能为空")
    @ApiModelProperty(value = "市", example = "510100")
    private String city;

    @NotBlank(message = "县级代码不能为空")
    @ApiModelProperty(value = "县", example = "510107")
    private String county;

    @NotBlank(message = "经度不能为空")
//    @Pattern(regexp = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$",message = "经度范围应当介于0-180之间")
    @ApiModelProperty(value = "经度")
    private String longitude;

    @NotBlank(message = "纬度不能为空")
//    @Pattern(regexp = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$",message = "纬度范围应当介于0-90之间")
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @NotNull(message = "温度不能为空")
    @ApiModelProperty(value = "温度（℃）")
    private Double temperature;

//    @NotNull(message = "湿度不能为空")
    @ApiModelProperty(value = "湿度（克/立方米")
    private Double humidity;

//    @NotNull(message = "光照不能为空")
    @ApiModelProperty(value = "光照（Lux）")
    private Double illumination;

    @NotNull(message = "年降雨量不能为空")
    @ApiModelProperty(value = "年降雨量（mm）")
    private Double rainfall;

    @NotBlank(message = "引进品种名称不能为空")
    @ApiModelProperty(value = "引进品种名称")
    private String variety;

    @NotNull(message = "群体数量不能为空")
    @ApiModelProperty(value = "群体数量(个)")
    private Integer amount;

    @Pattern(regexp = "\\d+:\\d+",message = "公母比例不规范填写;示例x:y")
    @ApiModelProperty(value = "公母比例")
    private String proportion;

    @NotNull(message = "养殖户数量不能为空")
    @ApiModelProperty(value = "养殖户数量(个)")
    private Integer breeder;

//    @NotBlank(message = "空气质量不能为空")
    @ApiModelProperty(value = "空气质量")
    private String air;

//    @NotNull(message = "饲草料种植面积不能为空")
    @ApiModelProperty(value = "饲草料种植面积（m2）")
    private Double plant;

//    @NotNull(message = "饲草料产量不能为空")
    @ApiModelProperty(value = "饲草料产量（t）")
    private Double yield;


    @ApiModelProperty(value = "状态 0-保存  2-上报")
    private String status;

}


