package com.sofn.agzirdd.excelmodel;

import com.sofn.agzirdd.model.SpecimenCollection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class CollectionPlantSpecimenExcel extends SpecimenCollection {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "typeName", value = "采集标本0-植物,1-动物,2-微生物")
    private String typeName;

    @ApiModelProperty(name = "speciesName", value = "物种名称")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @ApiModelProperty(name = "synonyms", value = "同物异名")
    private String synonyms;

    @ApiModelProperty(name = "trait", value = "性状")
    private String trait;

    @ApiModelProperty(name = "height", value = "植株高度")
    private String height;

    @ApiModelProperty(name = "rootImgUrl", value = "根图片Url")
    private String rootImgUrl;

    @ApiModelProperty(name = "stemImgUrl", value = "茎图片Url")
    private String stemImgUrl;

    @ApiModelProperty(name = "leafImgUrl", value = "叶图片Url")
    private String leafImgUrl;

    @ApiModelProperty(name = "flowerImgUrl", value = "花图片Url")
    private String flowerImgUrl;

    @ApiModelProperty(name = "fruitImgUrl", value = "果图片Url")
    private String fruitImgUrl;

    @ApiModelProperty(name = "seedImgUrl", value = "种子图片Url")
    private String seedImgUrl;
}
