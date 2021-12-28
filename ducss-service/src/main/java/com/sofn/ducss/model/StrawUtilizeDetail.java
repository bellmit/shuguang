package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName("straw_utilize_detail")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("市场主体利用量填报表")
public class StrawUtilizeDetail extends Model<StrawUtilizeDetail> {
    private String id;

    @ApiModelProperty(name = "utilizeId", value = "utilizeId")
    private String utilizeId;

    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "strawName", value = "秸秆名称")
    private String strawName;//秸秆名称

    @ApiModelProperty(name = "fertilising", value = "肥料化")
    @Length(max = 38,message = "肥料化超长")
    private BigDecimal fertilising = new BigDecimal(0);

    @ApiModelProperty(name = "forage", value = "饲料化")
    @Length(max = 38,message = "饲料化超长")
    private BigDecimal forage = new BigDecimal(0);

    @ApiModelProperty(name = "fuel", value = "燃料化")
    @Length(max = 38,message = "燃料化超长")
    private BigDecimal fuel = new BigDecimal(0);

    @ApiModelProperty(name = "base", value = "基料化")
    @Length(max = 38,message = "基料化超长")
    private BigDecimal base = new BigDecimal(0);

    @ApiModelProperty(name = "material", value = "原料化")
    @Length(max = 38,message = "原料化超长")
    private BigDecimal material = new BigDecimal(0);

    @ApiModelProperty(name = "other", value = "外县购入")
    private BigDecimal other = new BigDecimal(0);

    //ByDataAnalysis-start
    @ApiModelProperty(name = "marketEnt", value = "市场主体利用量=某区域所有秸秆市场化利用主体利用秸秆资源量之和")
    private BigDecimal marketEnt = new BigDecimal(0);
    //ByDataAnalysis-end

    /**
     * 新增字段
     */
    @ApiModelProperty(name = "ownCountry", value = "本县来源")
    @TableField(exist = false)
    private BigDecimal ownCountry = new BigDecimal(0);

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
    private StrawUtilize mainTableenTityClass;

    @ApiModelProperty(name = "indicatorArray", value = "指标数组")
    @TableField(exist = false)
    private HashMap<String, Map<String, Object>> indicatorArray = new HashMap<>();
    //ByDataAnalysis-end
}