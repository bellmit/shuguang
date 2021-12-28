package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "监测表表单对象")
public class MonitorForm {

    @ApiModelProperty(value = "主键(新增不用管，修改更新时必传)")
    private String id;
    @NotBlank(message = "保护点id不能为空")
    @ApiModelProperty(value = "保护点id", example = "4600608b758f4d2a8e14c6aa6e7ad020")
    private String protectId;
    @NotNull(message = "调查日期不能为空")
    @ApiModelProperty(value = "调查日期")
    private Date surveyDate;
    @NotBlank(message = "调查单位不能为空")
    @Size(max = 50, message = "调查单位不能超过50")
    @ApiModelProperty(value = "调查单位", example = "测试调查单位")
    private String surveyDept;
    @NotBlank(message = "调查人不能为空")
    @Size(max = 20, message = "调查人不能超过20")
    @ApiModelProperty(value = "调查人", example = "测试调查人")
    private String surveyor;
    @ApiModelProperty(value = "省", example = "510000")
    private String province;
    @ApiModelProperty(value = "市", example = "510100")
    private String city;
    @NotBlank(message = "区县编码不能为空")
    @ApiModelProperty(value = "县", example = "510106")
    private String county;
    @Size(max = 100, message = "详细地址不能超过100")
    @ApiModelProperty(value = "详细地址")
    private String addr;
//    @Pattern(regexp = "^$|((1\\d{10})|0(\\d{2,3}[\\-,]\\d{7,10}))$", message = "请输入正确手机号或固定电话")
    @ApiModelProperty(value = "联系电话")
    private String tel;
    @ApiModelProperty(value = "目标物种")
    private String targetSpec;
    @DecimalMin(value = "0", message = "受损面积不能小于0")
    @DecimalMax(value = "9999999", message = "受损面积不能大于9999999")
    @ApiModelProperty(value = "受损面积(亩)")
    private Double damage;
    @ApiModelProperty(value = "(目标物种)其它")
    private String other;
    @ApiModelProperty(value = "围栏设施")
    private String fence;
    @ApiModelProperty(value = "看护房")
    private String nurse;
    @ApiModelProperty(value = "警示牌")
    private String warning;
    @ApiModelProperty(value = "巡护路")
    private String patrol;
    @ApiModelProperty(value = "瞭望塔")
    private String tower;
    @ApiModelProperty(value = "其他设施")
    private String otherFacilities;
    @DecimalMin(value = "0", message = "年均温不能小于0")
    @DecimalMax(value = "9999999", message = "年均温不能大于9999999")
    @ApiModelProperty(value = "年均温")
    private Double avgTemp;
    @DecimalMin(value = "0", message = "年均降雨量不能小于0")
    @DecimalMax(value = "9999999", message = "年均降雨量不能大于9999999")
    @ApiModelProperty(value = "年均降雨量")
    private Double avgRainfall;
    @DecimalMin(value = "0", message = "种数(优势伴生物种)不能小于0")
    @DecimalMax(value = "999999999", message = "种数(优势伴生物种)不能大于999999999")
    @ApiModelProperty(value = "种数(优势伴生物种）")
    private Integer superSpecies;
    @DecimalMin(value = "0", message = "总株、苗数(优势伴生物种)不能小于0")
    @DecimalMax(value = "999999999", message = "总株、苗数(优势伴生物种)不能大于999999999")
    @ApiModelProperty(value = "总株、苗数(优势伴生物种）")
    private Integer superSeedling;
    @DecimalMin(value = "0", message = "科数(优势伴生物种)不能小于0")
    @DecimalMax(value = "999999999", message = "科数(优势伴生物种)不能大于999999999")
    @ApiModelProperty(value = "科数(优势伴生物种）")
    private Integer superFamily;
    @DecimalMin(value = "0", message = "属数(优势伴生物种)不能小于0")
    @DecimalMax(value = "999999999", message = "属数(优势伴生物种)不能大于999999999")
    @ApiModelProperty(value = "属数(优势伴生物种）")
    private Integer superGenera;
    @Size(max = 30, message = "生长状况(优势伴生物种）不能超过30")
    @ApiModelProperty(value = "生长状况(优势伴生物种）")
    private String superGrowth;
    @ApiModelProperty(value = "群落覆盖度(%)(优势伴生物种）")
    private Double superCover;
    @DecimalMin(value = "0", message = "分布面积不能小于0")
    @DecimalMax(value = "9999999", message = "分布面积(优势伴生物种）不能大于9999999")
    @ApiModelProperty(value = "分布面积（亩）(优势伴生物种）")
    private Double superArea;
    @ApiModelProperty(value = "返青期(开始时间)")
    private Date trunGreenStart;
    @ApiModelProperty(value = "返青期(结束时间)")
    private Date trunGreenEnd;
    @ApiModelProperty(value = "枯萎/落叶期(开始时间)")
    private Date witherStart;
    @ApiModelProperty(value = "枯萎/落叶期(结束时间)")
    private Date witherEnd;
    @DecimalMin(value = "0", message = "种类数不能小于0")
    @DecimalMax(value = "9999999", message = "种类数(入侵物种）不能大于9999999")
    @ApiModelProperty(value = "种类数(入侵物种)")
    private Integer invadeSpecies;
    @Size(max = 30, message = "物种名称(入侵物种)不能超过30")
    @ApiModelProperty(value = "物种名称(入侵物种)")
    private String invadeSpecName;
    @DecimalMin(value = "0", message = "分布面积(入侵物种）不能小于0")
    @DecimalMax(value = "9999999", message = "分布面积(入侵物种）不能大于9999999")
    @ApiModelProperty(value = "分布面积（亩）(入侵物种）")
    private Double invadeArea;
    @Size(max = 2000, message = "危害程度不能超过2000")
    @ApiModelProperty(value = "危害程度(入侵物种）")
    private String invadeHazard;
    @Size(max = 2000, message = "铲除情况不能超过2000")
    @ApiModelProperty(value = "铲除情况(入侵物种）")
    private String invadeEradicate;

    @ApiModelProperty(value = "监测（物种）对象")
    private List<SpeciesMonitorForm> speciesMonitorForms;

    @ApiModelProperty(value = "保护点照片")
    private List<PictureAttForm> protectPic;

    /************************* 目标物种和基础设施保护情况 *************************/
    @ApiModelProperty(value = "目标物种照片")
    private List<PictureAttForm> specPic;
    @ApiModelProperty(value = "围栏设施照片")
    private List<PictureAttForm> fencePic;
    @ApiModelProperty(value = "看护房照片")
    private List<PictureAttForm> nursePic;
    @ApiModelProperty(value = "警示牌照片")
    private List<PictureAttForm> warningPic;
    @ApiModelProperty(value = "巡护路照片")
    private List<PictureAttForm> patrolPic;
    @ApiModelProperty(value = "瞭望塔照片")
    private List<PictureAttForm> towerPic;
    @ApiModelProperty(value = "其他设施照片")
    private List<PictureAttForm> otherFacilitiesPic;
    /*************************  优势伴生物种  *************************/
    @ApiModelProperty(value = "群落景观、盖度照片(优势伴生物种）")
    private List<PictureAttForm> superCommunityPic;
    @ApiModelProperty(value = "其它照片(优势伴生物种）")
    private List<PictureAttForm> otherPic;
    /************************* 入侵物种 **************************/
    @ApiModelProperty(value = "植株照片")
    private List<PictureAttForm> plantPic;
    @ApiModelProperty(value = "群落景观、盖度照片(入侵物种)")
    private List<PictureAttForm> invadeCommunityPic;
    @ApiModelProperty(value = "铲除前照片")
    private List<PictureAttForm> beforePic;
    @ApiModelProperty(value = "铲除后照片")
    private List<PictureAttForm> afterPic;


}
