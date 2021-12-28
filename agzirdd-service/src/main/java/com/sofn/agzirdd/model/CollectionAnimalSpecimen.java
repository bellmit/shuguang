package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description: 物种采集模块-动物标本采集
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@TableName("COLLECTION_ANIMAL_SPECIMEN")
@Data
public class CollectionAnimalSpecimen extends Model<CollectionAnimalSpecimen> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "specimenCollectionId", value = "标本id")
    private String specimenCollectionId;

    @ApiModelProperty(name = "speciesId", value = "物种ID")
    private String speciesId;

    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁名称")
    private String latinName;

    @Size(max = 50,message = "门长度不能超过50")
    @ApiModelProperty(name = "phylum", value = "门")
    private String phylum;

    @Size(max = 50,message = "纲长度不能超过50")
    @ApiModelProperty(name = "compendium", value = "纲")
    private String compendium;

    @Size(max = 50,message = "目长度不能超过50")
    @ApiModelProperty(name = "item", value = "目")
    private String item;

    @Size(max = 50,message = "科长度不能超过50")
    @ApiModelProperty(name = "section", value = "科")
    private String section;

    @ApiModelProperty(name = "classify", value = "类")
    private String classify;

    @ApiModelProperty(name = "classifyName", value = "分类名称")
    private String classifyName;

    @ApiModelProperty(name = "img", value = "图片")
    private String img;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}