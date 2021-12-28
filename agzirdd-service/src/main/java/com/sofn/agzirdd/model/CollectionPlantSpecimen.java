package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description: 物种采集模块-植物标本采集
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@TableName("COLLECTION_PLANT_SPECIMEN")
@Data
public class CollectionPlantSpecimen extends Model<CollectionPlantSpecimen> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "specimenCollectionId", value = "标本采集id")
    private String specimenCollectionId;

    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;

    @ApiModelProperty(name = "speciesName", value = "物种名称")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;


    @Size(max = 50,message = "同物异名长度不能超过50")
    @ApiModelProperty(name = "synonyms", value = "同物异名")
    private String synonyms;

    @ApiModelProperty(name = "trait", value = "性状")
    private String trait;

    @ApiModelProperty(name = "traitName", value = "性状名称")
    private String traitName;

    @Size(max = 50,message = "植株高度长度不能超过50")
    @ApiModelProperty(name = "height", value = "植株高度")
    private String height;

    @ApiModelProperty(name = "rootImg", value = "根图片")
    private String rootImg;

    @ApiModelProperty(name = "stemImg", value = "茎图片")
    private String stemImg;

    @ApiModelProperty(name = "leafImg", value = "叶图片")
    private String leafImg;

    @ApiModelProperty(name = "flowerImg", value = "花图片")
    private String flowerImg;

    @ApiModelProperty(name = "fruitImg", value = "果图片")
    private String fruitImg;

    @ApiModelProperty(name = "seedImg", value = "种子图片")
    private String seedImg;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}