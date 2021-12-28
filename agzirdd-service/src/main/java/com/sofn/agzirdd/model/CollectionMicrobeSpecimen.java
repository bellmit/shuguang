package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 物种采集模块-微生物标本采集
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@TableName("COLLECTION_MICROBE_SPECIMEN")
@Data
public class CollectionMicrobeSpecimen extends Model<CollectionMicrobeSpecimen> {

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

    @ApiModelProperty(name = "classify", value = "分类")
    private String classify;

    @ApiModelProperty(name = "classifyName", value = "分类名称")
    private String classifyName;

    @ApiModelProperty(name = "img", value = "图片")
    private String img;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}