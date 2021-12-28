package com.sofn.agzirdd.excelmodel;

import com.sofn.agzirdd.model.SpecimenCollection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Administrator
 */
@Data
public class CollectionMicrobeSpecimenExcel extends SpecimenCollection {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "typeName", value = "采集标本0-植物,1-动物,2-微生物")
    private String typeName;

    @ApiModelProperty(name = "speciesName", value = "物种名称")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @ApiModelProperty(name = "classifyName", value = "分类")
    private String classifyName;

    @ApiModelProperty(name = "imgUrl", value = "图片Url")
    private String imgUrl;
}
