package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName("disperse_utilize_detail")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ApiModel("农户分散利用量填报基础表")
public class DisperseUtilizeDetail extends Model<DisperseUtilizeDetail> {
    private String id;

    @ApiModelProperty(name = "utilizeId", value = "农户分散利用量填报表ID")
    private String utilizeId;

    @ApiModelProperty(name = "strawName", value = "秸秆名称")
    private String strawName;//秸秆名称

    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "fertilising", value = "肥料化")
    @Length(max = 38, message = "肥料化超长")
    private BigDecimal fertilising;

    @ApiModelProperty(name = "forage", value = "饲料化")
    @Length(max = 38, message = "饲料化超长")
    private BigDecimal forage;

    @ApiModelProperty(name = "fuel", value = "燃料化")
    @Length(max = 38, message = "燃料化超长")
    private BigDecimal fuel;

    @ApiModelProperty(name = "base", value = "基料化")
    @Length(max = 38, message = "基料化超长")
    private BigDecimal base;

    @ApiModelProperty(name = "material", value = "原料化")
    @Length(max = 38, message = "原料化超长")
    private BigDecimal material;

    @ApiModelProperty(name = "sownArea", value = "播种面积")
    @Length(max = 38, message = "播种面积超长")
    private BigDecimal sownArea;

    @ApiModelProperty(name = "yieldPerMu", value = "亩产")
    @Length(max = 38, message = "粮食单产超长")
    private BigDecimal yieldPerMu;

    @ApiModelProperty(name = "reuse", value = "利用量")
    @Length(max = 38, message = "收集利用量超长")
    private BigDecimal reuse;

    @ApiModelProperty(name = "application", value = "用途")
    private String application;

    //ByDataAnalysis-start
    @ApiModelProperty(name = "disperseFertilising", value = "分散肥料化")
    private BigDecimal disperseFertilisingData;

    @ApiModelProperty(name = "disperseForage", value = "分散饲料化")
    private BigDecimal disperseForageData;

    @ApiModelProperty(name = "disperseFuel", value = "分散燃料化")
    private BigDecimal disperseFuelData;

    @ApiModelProperty(name = "disperseBase", value = "分散基料化")
    private BigDecimal disperseBaseData;

    @ApiModelProperty(name = "disperseMaterial", value = "分散原料化")
    private BigDecimal disperseMaterialData;

    @ApiModelProperty(name = "yieldAllNumData", value = "分散原料化")
    private BigDecimal yieldAllNumData;
    //ByDataAnalysis-end

    /**
     * 扩展字段
     */
    @TableField(exist = false)
    private String strawStr;
    @TableField(exist = false)
    private BigDecimal disperseFertilising; // 肥料
    @TableField(exist = false)
    private BigDecimal disperseForage;// 饲料
    @TableField(exist = false)
    private BigDecimal disperseFuel;// 燃料
    @TableField(exist = false)
    private BigDecimal disperseBase;// 基料
    @TableField(exist = false)
    private BigDecimal disperseMaterial;// 原料
    @TableField(exist = false)
    private BigDecimal disperseSumAll; // 合计比例化
    @TableField(exist = false)
    private String farmerName;
    /***
     * 户主姓名
     */
    @TableField(exist = false)
    private String farmerNo;
    @TableField(exist = false)
    private String countryName;      //区县名
    @TableField(exist = false)
    private BigDecimal yieldAllNum;// 秸秆产量=早稻的播种面积（亩）*早稻的单产（公斤/亩）*0.001*草谷比*收集系数
    @ApiModelProperty(name = "areaId", value = "县ID")
    @TableField(exist = false)
    private Integer areaId;//区id
    @TableField(exist = false)
    private Integer cityId;//市id
    @TableField(exist = false)
    private Integer provinceId;//省id


    //ByDataAnalysis-start
    @ApiModelProperty("时间")
    @TableField(exist = false)
    private String gtime;

    @ApiModelProperty("区域")
    @TableField(exist = false)
    private String area_Id;


    @ApiModelProperty("区域名称")
    @TableField(exist = false)
    private String area_Name;

    @ApiModelProperty("主表实体类")
    @TableField(exist = false)
    private DisperseUtilize mainTableenTityClass;

    @ApiModelProperty(name = "indicatorArray", value = "指标数组")
    @TableField(exist = false)
    private HashMap<String, Map<String, Object>> indicatorArray = new HashMap<>();
    //ByDataAnalysis-end


    @ApiModelProperty("农户电话")
    @TableField(exist = false)
    private String farmerPhone;

    @ApiModelProperty("年份")
    @TableField(exist = false)
    private String year;

    // 计算资源量
    public void calculateDisperseNum(BigDecimal grassValleyRatio, BigDecimal collectionRatio) {
        if (sownArea != null && yieldPerMu != null) {
            // 公斤转换为吨
            //秸秆产量（吨）=早稻的播种面积（亩）*早稻的单产（公斤/亩）*0.001*草谷比*收集系数
            yieldAllNum = sownArea.multiply(yieldPerMu).multiply(grassValleyRatio).multiply(collectionRatio)
                    .divide(new BigDecimal(1000), 10, RoundingMode.HALF_UP);
            if (fertilising != null) {
                disperseFertilising = yieldAllNum.multiply(fertilising).multiply(new BigDecimal(0.01));
            }
            if (forage != null) {
                disperseForage = yieldAllNum.multiply(forage).multiply(new BigDecimal(0.01));
            }
            if (fuel != null) {
                disperseFuel = yieldAllNum.multiply(fuel).multiply(new BigDecimal(0.01));
            }
            if (base != null) {
                disperseBase = yieldAllNum.multiply(base).multiply(new BigDecimal(0.01));
            }
            if (material != null) {
                disperseMaterial = yieldAllNum.multiply(material).multiply(new BigDecimal(0.01));
            }
            if (reuse != null && StringUtils.isNotBlank(application))
                switch (application) {
                    case "0":
                        disperseFertilising = disperseFertilising.add(reuse);
                        break;
                    case "1":
                        disperseForage = disperseForage.add(reuse);
                        break;
                    case "2":
                        disperseFuel = disperseFuel.add(reuse);
                        break;
                    case "3":
                        disperseBase = disperseBase.add(reuse);
                        break;
                    case "4":
                        disperseMaterial = disperseMaterial.add(reuse);
                        break;

                    default:
                        break;
                }
            disperseSumAll = fertilising.add(forage).add(fuel).add(base).add(material);
        }

    }

    public DisperseUtilizeDetail() {
        this.fertilising = new BigDecimal(0);
        this.forage = new BigDecimal(0);
        this.fuel = new BigDecimal(0);
        this.base = new BigDecimal(0);
        this.material = new BigDecimal(0);
        this.reuse = new BigDecimal(0);
        this.application = "肥料化";
        this.yieldPerMu = new BigDecimal(0);
        this.sownArea = new BigDecimal(0);
    }
}