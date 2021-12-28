package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/11/6 0:01
 * @Version 1.0
 * 新：秸秆利用汇总=分散+市场
 */
@Data
@TableName(value = "straw_usage_sum")
@ApiModel("秸秆利用汇总=分散+市场")
public class StrawUsageSum {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("所属年度")
    private String year;

    @ApiModelProperty("所属区域")
    private String areaId;

    @ApiModelProperty("秸秆类型")
    private String strawType;

    @ApiModelProperty("秸秆名称")
    private String strawName;

    @ApiModelProperty("秸秆利用量=(市场主体利用量农户分散利用量直接还田量调出量-调入量)")
    private BigDecimal strawUsage;

    @ApiModelProperty("综合利用率（%）：秸秆利用量/可收集量 *100%")
    private BigDecimal comprehensiveRate;

    @ApiModelProperty("五料化合计=（分散五料主体五料）")
    private BigDecimal allTotal;

    @ApiModelProperty("肥料化利用量：市场主体利用量（肥料）农户分散利用量（肥料）直接还田量")
    private BigDecimal fertilizer;

    @ApiModelProperty("燃料化利用量（吨）：市场主体利用量（燃料）农户分散利用量（燃料")
    private BigDecimal fuel;

    @ApiModelProperty("基料化利用量（吨）：市场主体利用量（基料）农户分散利用量（基料）")
    private BigDecimal basic;

    @ApiModelProperty("原料化利用量（吨）：市场主体利用量（原料）农户分散利用量（原料）")
    private BigDecimal rawMaterial;

    @ApiModelProperty("饲料：市场主体利用量（饲料）农户分散利用量（饲料）")
    private BigDecimal feed;

    @ApiModelProperty("市场化主体调入量(吨)")
    private BigDecimal other;

    @ApiModelProperty("区域调出量(吨)")
    private BigDecimal yieldExport;

    @ApiModelProperty("综合利用能力指数：市场主体规模化利用量农户分散利用量直接还田量/可收集量*100")
    private BigDecimal comprehensiveIndex;

    @ApiModelProperty("产业化利用能力指数：市场主体规模化利用量/可收集量*100")
    private BigDecimal industrializationIndex;

    //新增

    @ApiModelProperty("可收集资源量")
    private BigDecimal collectResource;

    @ApiModelProperty("市场主体利用总量")
    private BigDecimal mainTotal;

    @ApiModelProperty("直接还田量")
    private BigDecimal returnResource;

    /**
     * 该字段用于计算综合利用量得，不做其他展示
     */
    private BigDecimal collectResourceV2;

    private BigDecimal strawUtilizationV2;

    public StrawUsageSum() {
        super();
        BigDecimal zero = BigDecimal.ZERO;
        this.strawUsage = zero;
        this.comprehensiveRate = zero;
        this.allTotal = zero;
        this.fertilizer = zero;
        this.fuel = zero;
        this.basic = zero;
        this.rawMaterial = zero;
        this.feed = zero;
        this.other = zero;
        this.yieldExport = zero;
        this.comprehensiveIndex = zero;
        this.industrializationIndex = zero;
        this.collectResource = zero;
        this.mainTotal = zero;
        this.returnResource = zero;
        this.collectResourceV2 = zero;
        this.strawUtilizationV2 = zero;
    }
}
