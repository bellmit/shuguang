package com.sofn.agzirdd.excelmodel;

import com.sofn.agzirdd.model.SpecimenCollection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class CollectionAnimalSpecimenExcel extends SpecimenCollection {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "typeName", value = "采集标本0-植物,1-动物,2-微生物")
    private String typeName;

    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @ApiModelProperty(name = "phylum", value = "门")
    private String phylum;

    @ApiModelProperty(name = "compendium", value = "纲")
    private String compendium;

    @ApiModelProperty(name = "item", value = "目")
    private String item;

    @ApiModelProperty(name = "section", value = "科")
    private String section;

    @ApiModelProperty(name = "classifyName", value = "分类")
    private String classifyName;

    @ApiModelProperty(name = "imgUrl", value = "图片Url")
    private String imgUrl;
}
