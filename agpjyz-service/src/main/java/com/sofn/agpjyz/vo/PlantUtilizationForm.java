package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ApiModel(value = "植物利用表单对象")
public class PlantUtilizationForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "省", example = "510000")
    private String province;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "市", example = "510100")
    private String city;
    @ApiModelProperty(value = "市名")
    private String cityName;
    @ApiModelProperty(value = "县", example = "510112")
    private String county;
    @ApiModelProperty(value = "县名")
    private String countyName;
    @NotNull(message = "物种名称ID不能为空")
    @ApiModelProperty(value = "物种名称ID")
    private String specId;
    @NotNull(message = "物种名称不能为空")
    @ApiModelProperty(value = "物种名称", example = "物种名称")
    private String specValue;
    @NotNull(message = "拉丁学名不能为空")
    @ApiModelProperty(value = "拉丁学名", example = "拉丁学名")
    private String latin;
    @ApiModelProperty(value = "产业利用ID")
    private String industrialId;
    @ApiModelProperty(value = "产业利用名称", example = "产业利用名称")
    private String industrialValue;
//    @Size(max = 32, message = "利用单位名称不能超过32")
    @ApiModelProperty(value = "利用单位名称", example = "利用单位名称")
    private String utilizationUnit;
    @ApiModelProperty(value = "植物利用用途ID")
    private String purpose;
    @ApiModelProperty(value = "具体内容ID")
    private String concreteContent;
    @ApiModelProperty(value = "价值ID")
    @Size(max = 100, message = "效益描述不能超过100")
    private String worth;
//    @Digits(integer = 5, fraction = 2, message = "产值整数部分不能大于5位，小数部分保留2位")
    @ApiModelProperty(value = "产值(万元)", example = "5.6")
    private Double outputValue;
    @ApiModelProperty(value = "市场需求 1大2不大", example = "1")
    private String marketDemand;
    @ApiModelProperty(value = "人工栽培技术 1过关2不过关3其它", example = "1")
    private String artificial;
    @Size(max = 100, message = "人工栽培技术(其它)不能超过100")
    @ApiModelProperty(value = "人工栽培技术(其它)", example = "人工栽培技术(其它)")
    private String artificialOther;
//    @Size(max = 100, message = "其他超过100")
    @ApiModelProperty(value = "其他", example = "其他")
    private String other;
    @ApiModelProperty(value = "图片附件表单对象(其它)")
    private List<PictureAccessoriesForm> picOther;

}
