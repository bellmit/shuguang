package com.sofn.fyrpa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("保护区保护效果信息对象")
@Data
@TableName(value = "aquatic_resources_protection_effect")
public class AquaticResourcesProtectionEffect {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "protectObjectAppearRate",value ="保护对象出现频率" )
    private String protectObjectAppearRate;

    @ApiModelProperty(name = "dominanceExponent",value ="优势度指数" )
    private String dominanceExponent;

    @ApiModelProperty(name = "speciesAbundance",value ="物种丰度" )
    private String speciesAbundance;

    @ApiModelProperty(name = "variety",value ="多样性" )
    private String variety;

    @ApiModelProperty(name = "homogeneousDegree",value ="均匀度" )
    private String homogeneousDegree;

    @ApiModelProperty(name = "kindCorrelation",value ="种类相似度" )
    private String kindCorrelation;

    @ApiModelProperty(name = "communitySimilarity",value ="群落相似性" )
    private String communitySimilarity;

    @ApiModelProperty(name = "dissolvedOxygen",value ="溶解氧DO" )
    private String dissolvedOxygen;

    @ApiModelProperty(name = "debiochemicalOxygenDemand",value ="生化需氧量BOD" )
    private String debiochemicalOxygenDemand;

    @ApiModelProperty(name = "chemicalOxygenDemand",value ="化学需氧量COD" )
    private String chemicalOxygenDemand;

    @ApiModelProperty(name = "totalOrganicCarbon",value ="总有机碳 TOC" )
    private String totalOrganicCarbon;

    @ApiModelProperty(name = "totalOxygenDemand",value ="总需氧量 TOD" )
    private String totalOxygenDemand;

    @ApiModelProperty(name = "compositeIndex",value ="综合指数" )
    private String compositeIndex;

    @ApiModelProperty(name = "tnb",value ="总氮TN" )
    private String tnb;

    @ApiModelProperty(name = "totalPhosphorus",value ="总磷" )
    private String totalPhosphorus;

    @ApiModelProperty(name = "chlorophyll",value ="叶绿素a" )
    private String chlorophyll;

    @ApiModelProperty(name = "vegetativeState",value ="营养状态" )
    private String vegetativeState;

    @ApiModelProperty(name = "phytoplankton",value ="浮游植物" )
    private String phytoplankton;

    @ApiModelProperty(name = "zooplankton",value ="浮游动物" )
    private String zooplankton;

    @ApiModelProperty(name = "benthon",value ="底栖生物" )
    private String benthon;

    @ApiModelProperty(name = "foodBiologicalLevel",value ="饵料生物水平" )
    private String foodBiologicalLevel;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    private Date createTime;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

  }