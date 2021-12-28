package com.sofn.agzirdd.excelmodel;

import com.sofn.agzirdd.model.SpeciesInvestigation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Administrator
 */
@Data
public class SpeciesInvestigationExcel extends SpeciesInvestigation {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "hasAlienSpeciesName", value = "是否存在入侵物种")
    private String hasAlienSpeciesName;

    @ApiModelProperty(name = "speciesTypeName", value = "物种类型名称")
    private String speciesTypeName;

    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @ApiModelProperty(name = "content", value = "调查内容")
    private String content;

    @ApiModelProperty(name = "newSpeciesName", value = "是否本省新发物种")
    private String newSpeciesName;

    @ApiModelProperty(name = "speciesRemark", value = "新物种备注信息")
    private String speciesRemark;

    @ApiModelProperty(name = "gps", value = "gps位置")
    private String gps;

    @ApiModelProperty(name = "area", value = "发生面积")
    private String area;

    @ApiModelProperty(name = "amount", value = "样方内株（个）数/平米")
    private String amount;

    @ApiModelProperty(name = "coverRatio", value = "覆盖度(%)/平米")
    private String coverRatio;

    @ApiModelProperty(name = "typeName", value = "生境类型")
    private String typeName;

    @ApiModelProperty(name = "hasHarmName", value = "是否造成危害:0-否，1-是")
    private String hasHarmName;

    @ApiModelProperty(name = "harmObject", value = "危害对象")
    private String harmObject;

    @ApiModelProperty(name = "harmMethod", value = "危害方式")
    private String harmMethod;

    @ApiModelProperty(name = "damageDegreeName", value = "危害程度:0-轻度；1-中度；2-重度")
    private String damageDegreeName;

    @ApiModelProperty(name = "economicLoss", value = "经济损失")
    private String economicLoss;

    @ApiModelProperty(name = "hasMeasuresName", value = "是否有防控措施:0-否，1-是")
    private String hasMeasuresName;

    @ApiModelProperty(name = "describe", value = "防治描述")
    private String describe;

    @ApiModelProperty(name = "preventionArea", value = "防治面积")
    private double preventionArea;

    @ApiModelProperty(name = "preventionCost", value = "防治成本")
    private double preventionCost;

    @ApiModelProperty(name = "result", value = "控制效果")
    private String result;

    @ApiModelProperty(name = "hasUtilizeName", value = "是否进行利用:0-否，1-是")
    private String hasUtilizeName;

    @ApiModelProperty(name = "utilizeDescribe", value = "利用描述")
    private String utilizeDescribe;

    @ApiModelProperty(name = "utilizeWay", value = "利用方式")
    private String utilizeWay;

    @ApiModelProperty(name = "resultImgUrl", value = "效果图Url")
    private String resultImgUrl;

    @ApiModelProperty(name = "utilizeImgUrl", value = "利用方式图片Url")
    private String utilizeImgUrl;

}
