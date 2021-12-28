package com.sofn.ducss.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/10/9  9:37
 * @description
 **/
//@Document(indexName = "disperse_utilize_detail", type = "doc")
@Data
public class ElascticsearchModel implements Serializable {

    //商品id，同时也是商品编号
    @Id
    private String id;

//    @Field(type = FieldType.Keyword)
    private String year;

//    @Field(index = true, store = true, type = FieldType.Text)
//    private String indexs;

//    @Field(type = FieldType.Keyword)
    private String areaId;

//    @Field(type = FieldType.Keyword)
    private String cityId;

//    @Field(type = FieldType.Keyword)
    private String proviceId;

//    @Field(type = FieldType.Keyword)
    private String strawType;

    private BigDecimal grainYield;

    private BigDecimal grassValleyRatio;

    private BigDecimal collectionRatio;

    private BigDecimal seedArea;

    private BigDecimal returnArea;

    private BigDecimal exportYield;

    private BigDecimal theoryResource;

    private BigDecimal collectResource;

    private BigDecimal marketEnt;

    private BigDecimal fertilizes;

    private BigDecimal feeds;

    private BigDecimal fuelleds;

    private BigDecimal baseMats;

    private BigDecimal materializations;

    private BigDecimal reuse;

    private BigDecimal fertilisingd;

    private BigDecimal foraged;

    private BigDecimal fueld;

    private BigDecimal based;

    private BigDecimal materiald;

    private BigDecimal returnResource;

    private BigDecimal other;

    private BigDecimal fertilize;

    private BigDecimal feed;

    private BigDecimal fuelled;

    private BigDecimal baseMat;

    private BigDecimal materialization;

    private BigDecimal strawUtilization;

    private BigDecimal totolRate;

    private BigDecimal comprUtilIndex;

    private BigDecimal induUtilIndex;
    //前端取值的区域名（动态）
    @ApiModelProperty(name = "areaName", value = "前端取值的区域名（动态）")
    private String areaName;

    //作物名
    private String strawName;

    //分析指标map
    private HashMap<String, Object> analysisIndexMap;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAreaId() {
        if (!StringUtils.isNotBlank(areaId)) {
            return "0";
        }
        return areaId;
    }

    public void setAreaId(String areaId) {
        if (!StringUtils.isNotBlank(areaId)) {
            this.areaId = "0";
        }
        this.areaId = areaId;
    }

    public String getCityId() {
        if (!StringUtils.isNotBlank(cityId)) {
            return "0";
        }
        return cityId;
    }

    public void setCityId(String cityId) {
        if (!StringUtils.isNotBlank(cityId)) {
            this.cityId = "0";
        }
        this.cityId = cityId;
    }

    public String getProviceId() {
        if (!StringUtils.isNotBlank(proviceId)) {
            return "0";
        }
        return proviceId;
    }
}
