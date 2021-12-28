package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * @Description: 物种调查模块-调查内容
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
@TableName("INVESTIGAT_CONTENT")
@Data
public class InvestigatContent extends Model<InvestigatContent> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "speciesInvestigationId", value = "物种调查表id")
    private String speciesInvestigationId;

    @ApiModelProperty(name = "speciesType", value = "物种类型(0-植物,1-动物,2-微生物)")
    private String speciesType;

    @ApiModelProperty(name = "speciesTypeName", value = "物种类型名称")
    private String speciesTypeName;

    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;

    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @ApiModelProperty(name = "content", value = "调查内容")
    private String content;

    @ApiModelProperty(name = "newSpecies", value = "是否本省新发物种")
    private String newSpecies;

    @Size(max = 50,message = "新物种备注信息长度不能超过50")
    @ApiModelProperty(name = "speciesRemark", value = "新物种备注信息")
    private String speciesRemark;

    @Size(max = 50,message = "gps位置长度不能超过50")
    @ApiModelProperty(name = "gps", value = "gps位置")
    private String gps;

    @Size(max = 50,message = "发生面积长度不能超过50")
    @ApiModelProperty(name = "area", value = "发生面积")
    private String area;

    @Size(max = 50,message = "样方内株（个）数/平米长度不能超过50")
    @ApiModelProperty(name = "amount", value = "样方内株（个）数/平米")
    private String amount;

    @Size(max = 50,message = "覆盖度(%)/平长度不能超过50")
    @ApiModelProperty(name = "coverRatio", value = "覆盖度(%)/平米")
    private String coverRatio;

    @ApiModelProperty(name = "type", value = "生境类型")
    private String type;

    @ApiModelProperty(name = "typeName", value = "生境类型名称")
    private String typeName;

    @ApiModelProperty(name = "hasHarm", value = "是否造成伤害:0-否，1-是")
    private String hasHarm;

    @Size(max = 50,message = "危害对象长度不能超过50")
    @ApiModelProperty(name = "harmObject", value = "危害对象")
    private String harmObject;

    @Size(max = 50,message = "危害方式长度不能超过50")
    @ApiModelProperty(name = "harmMethod", value = "危害方式")
    private String harmMethod;

    @ApiModelProperty(name = "damageDegree", value = "危害程度:0-轻度；1-中度；2-重度")
    private String damageDegree;

    @Size(max = 50,message = "经济损失长度不能超过50")
    @ApiModelProperty(name = "economicLoss", value = "经济损失")
    private String economicLoss;

    @ApiModelProperty(name = "hasMeasures", value = "是否有防控措施:0-否，1-是")
    private String hasMeasures;

    @Size(max = 50,message = "防治描述长度不能超过50")
    @ApiModelProperty(name = "describe", value = "防治描述")
    private String describe;

    @Max(value = 100000000,message = "防治面积长度不能超过100000000")
    @ApiModelProperty(name = "preventionArea", value = "防治面积")
    private double preventionArea;

    @Max(value = 100000000,message = "防治成本长度不能超过100000000")
    @ApiModelProperty(name = "preventionCost", value = "防治成本")
    private double preventionCost;

    @Size(max = 50,message = "控制效果长度不能超过50")
    @ApiModelProperty(name = "result", value = "控制效果")
    private String result;

    @ApiModelProperty(name = "resultImg", value = "效果图")
    private String resultImg;

    @ApiModelProperty(name = "hasUtilize", value = "是否进行利用:0-否，1-是")
    private String hasUtilize;

    @Size(max = 50,message = "利用描述长度不能超过50")
    @ApiModelProperty(name = "utilizeDescribe", value = "利用描述")
    private String utilizeDescribe;

    @ApiModelProperty(name = "utilizeImg", value = "利用情况照片")
    private String utilizeImg;

    @Size(max = 50,message = "利用方式长度不能超过50")
    @ApiModelProperty(name = "utilizeWay", value = "利用方式")
    private String utilizeWay;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "speciesImg", value = "新发物种图")
    private String speciesImg;

}