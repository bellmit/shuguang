package com.sofn.ducss.vo.DataAnalysis;

import com.sofn.ducss.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/9/11  11:12
 * @description
 **/
@Data
@ApiModel(value = "Mybatis映射实体类")
public class IndexDataVo implements Serializable {

    private static final long serialVersionUID = 4560051153895728734L;
    @ApiModelProperty(value = "区域")
    private String area_id = "";

    @ApiModelProperty(value = "省")
    private String province_id = "";

    @ApiModelProperty(value = "市")
    private String city_id = "";

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "作物类型")
    private String straw_name;

    @ApiModelProperty(value = "Dis详情实体类")
    private DisperseUtilizeDetail disperseUtilizeDetail;

    @ApiModelProperty(value = "Pro详情实体类")
    private ProStillDetail proStillDetail;

    @ApiModelProperty(value = "Str详情实体类")
    private StrawUtilizeDetail strawUtilizeDetail;

    @ApiModelProperty(value = "Dis主表实体类")
    private DisperseUtilize disperseUtilize;

    @ApiModelProperty(value = "Pro主表实体类")
    private ProStill proStill;

    @ApiModelProperty(value = "Str主表实体类")
    private StrawUtilize strawUtilize;

    @ApiModelProperty(value = "指标数组")
    private HashMap<String, Map<String, Object>> indicatorArray = new HashMap<>();

    //Dis表指标字段-start
    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal sown_area = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal reuse = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal fertilising = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal forage = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal fuel = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal base = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal material = new BigDecimal(0);
    //Dis表指标字段-end

    //Pro表指标字段-start
    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal grain_yield = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal grass_valley_ratio = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal collection_ratio = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal seed_area = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal return_area = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal export_yield = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal theory_resource = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal collect_resource = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal return_resource = new BigDecimal(0);

    //Pro表指标字段-end
    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal market_ent = new BigDecimal(0);

    @ApiModelProperty(value = "Dis表指标字段")
    private BigDecimal other = new BigDecimal(0);


}
