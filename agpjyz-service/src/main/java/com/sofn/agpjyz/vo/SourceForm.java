package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "资源调查表单对象")
public class SourceForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @NotNull(message = "调查日期不能为空")
    @ApiModelProperty(value = "调查日期")
    private Date survey;
    @NotBlank(message = "调查人不能为空")
    @Size(max = 10, message = "调查人不能超过10")
    @ApiModelProperty(value = "调查人", example = "调查人")
    private String investigator;
//    @Pattern(regexp = "^((1\\d{10})|0(\\d{2,3}[\\-,]\\d{7,10}))$", message = "请输入正确手机号或固定电话")
    @ApiModelProperty(value = "联系电话", example = "13300000000")
    private String phone;
    @NotBlank(message = "物种名称ID不能为空")
    @ApiModelProperty(value = "物种名称ID")
    private String specId;
    @NotBlank(message = "物种名称不能为空")
    @ApiModelProperty(value = "物种名称", example = "物种名称")
    private String specValue;
    @NotBlank(message = "拉丁学名不能为空")
    @ApiModelProperty(value = "拉丁学名", example = "拉丁学名")
    private String latin;
    @Size(max = 20, message = "俗名不能超过20")
    @ApiModelProperty(value = "俗名", example = "俗名")
    private String commonName;
    @NotBlank(message = "省区域编码不能为空")
    @ApiModelProperty(value = "省", example = "510000")
    private String province;
//    @ApiModelProperty(value = "省名")
//    private String provinceName;
    @NotBlank(message = "市区域编码不能为空")
    @ApiModelProperty(value = "市", example = "510100")
    private String city;
//    @ApiModelProperty(value = "市名")
//    private String cityName;
//    @NotBlank(message = "县区域编码不能为空")
    @ApiModelProperty(value = "县", example = "510112")
    private String county;
//    @ApiModelProperty(value = "县名")
//    private String countyName;
    @ApiModelProperty(value = "海拔", example = "50")
    private String altitude;
    @NotNull(message = "分布面积不能为空")
    @ApiModelProperty(value = "分布面积", example = "100")
    private String distribution;
    @NotNull(message = "种群数量不能为空")
    @ApiModelProperty(value = "种群数量", example = "123")
    private String amount;
//    @Size(max = 1000, message = "形态特征不能超过1000")
    @ApiModelProperty(value = "形态特征", example = "形态特征")
    private String features;
//    @Size(max = 1000, message = "生物学特性不能超过1000")
    @ApiModelProperty(value = "生物学特性", example = "生物学特性")
    private String characteristic;
    @NotBlank(message = "濒危状况ID不能为空")
    @ApiModelProperty(value = "濒危状况ID")
    private String endangeredId;
    @NotBlank(message = "濒危状况名称不能为空")
    @ApiModelProperty(value = "濒危状况名称", example = "濒危状况名称")
    private String endangeredValue;
    @NotBlank(message = "威胁因素不能为空")
//    @Size(max = 1000, message = "威胁因素不能超过1000")
    @ApiModelProperty(value = "威胁因素")
    private String threaten;
    @ApiModelProperty(value = "年平均气温")
    private String temperature;
    @ApiModelProperty(value = "大于等于10℃年积温(℃)")
    private String greater;
    @ApiModelProperty(value = "年平均降水量")
    private String precipitation;
    @ApiModelProperty(value = "年平均日照时数")
    private String sunshine;
    @ApiModelProperty(value = "年蒸发量")
    private String evaporation;
    @NotBlank(message = "植被类型ID不能为空")
    @ApiModelProperty(value = "植被类型ID")
    private String vegetationId;
    @NotBlank(message = "植被类型名称不能为空")
    @ApiModelProperty(value = "植被类型名称", example = "植被类型名称")
    private String vegetationValue;
    @ApiModelProperty(value = "植被覆盖率", example = "植被覆盖率")
    private String vegetationCoverage;
    @ApiModelProperty(value = "土壤类型ID")
    private String soilId;
    @ApiModelProperty(value = "土壤类型名称", example = "土壤类型名称")
    private String soilValue;
    @Size(max = 20, message = "土壤肥力不能超过20")
    @ApiModelProperty(value = "土壤肥力", example = "土壤肥力")
    private String soilFertility;
    @NotBlank(message = "保护与利用状况不能为空")
//    @Size(max = 1000, message = "保护与利用状况不能超过1000")
    @ApiModelProperty(value = "保护与利用状况", example = "保护与利用状况")
    private String protectionUtilization;
//    @Size(max = 100, message = "评价和建议不能超过100")
    @ApiModelProperty(value = "评价和建议", example = "评价和建议")
    private String suggest;
    @Size(max = 1, message = "状态值过长 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过 ")
    @ApiModelProperty(value = "状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过", example = "0")
    private String status;
//    @NotEmpty(message = "至少选择一项生境类型")
    @ApiModelProperty(value = "生境类型表单对象")
    private List<HabitatTypeForm> habitatTypeForms;
    @ApiModelProperty(value = "图片附件表单对象(根)")
    private List<PictureAccessoriesForm> root;
    @ApiModelProperty(value = "图片附件表单对象(茎)")
    private List<PictureAccessoriesForm> stem;
    @ApiModelProperty(value = "图片附件表单对象(叶)")
    private List<PictureAccessoriesForm> leaf;
    @ApiModelProperty(value = "图片附件表单对象(花)")
    private List<PictureAccessoriesForm> flower;
    @ApiModelProperty(value = "图片附件表单对象(果)")
    private List<PictureAccessoriesForm> fruit;
    @ApiModelProperty(value = "图片附件表单对象(种子)")
    private List<PictureAccessoriesForm> seed;
    @ApiModelProperty(value = "图片附件表单对象(其它)")
    private List<PictureAccessoriesForm> other;

}
