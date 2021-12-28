package com.sofn.ducss.vo;

import com.sofn.ducss.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(value = "农作物秸秆利用量汇总数据实体3")
@EqualsAndHashCode(callSuper = false)
public class StrawUtilizeResVo3 {

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty("所属年度")
    private String year;

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty("所属区域")
    private String areaId;

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty("秸秆类型")
    private String strawType;

    @ApiModelProperty("秸秆名称")
    @ExcelField(title = "作物种类")
    private String strawName;

    @ApiModelProperty("秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)")
    @ExcelField(title = "秸秆利用量（吨）")
    private BigDecimal strawUsage;

    @ApiModelProperty("综合利用率（%）：秸秆利用量/可收集量 *100%")
    @ExcelField(title = "综合利用率（%）")
    private BigDecimal comprehensiveRate;

    //合计会加直接还田量 //如果肥料已加，那么合计就不加。
    @ApiModelProperty("五料化合计=（分散五料+主体五料）")
    @ExcelField(title = "合计（吨）")
    private BigDecimal allTotal;

    @ApiModelProperty("肥料化利用量：市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量")
    @ExcelField(title = "肥料化（吨）")
    private BigDecimal fertilizer;

    @ApiModelProperty("饲料：市场主体利用量（饲料）+农户分散利用量（饲料）")
    @ExcelField(title = "饲料化（吨）")
    private BigDecimal feed;

    @ApiModelProperty("燃料化利用量（吨）：市场主体利用量（燃料）+农户分散利用量（燃料")
    @ExcelField(title = "燃料化（吨）")
    private BigDecimal fuel;

    @ApiModelProperty("基料化利用量（吨）：市场主体利用量（基料）+农户分散利用量（基料）")
    @ExcelField(title = "基料化（吨）")
    private BigDecimal basic;

    @ApiModelProperty("原料化利用量（吨）：市场主体利用量（原料）+农户分散利用量（原料）")
    @ExcelField(title = "原料化（吨）")
    private BigDecimal rawMaterial;

    @ApiModelProperty("市场化主体调入量(吨)")
    @ExcelField(title = "市场化主体调入量(吨)", isShow = false)
    private BigDecimal other;

    @ApiModelProperty("区域调出量(吨)")
    @ExcelField(title = "区域调出量(吨)", isShow = false)
    private BigDecimal yieldExport;

    @ApiModelProperty("综合利用能力指数：市场主体规模化利用量农户分散利用量直接还田量/可收集量*100")
    @ExcelField(title = "综合利用能力指数")
    private BigDecimal comprehensiveIndex;

    @ApiModelProperty("产业化利用能力指数：市场主体规模化利用量/可收集量*100")
    @ExcelField(title = "产业化利用能力指数")
    private BigDecimal industrializationIndex;

    //--
    @ApiModelProperty(value = "可收集资源量", hidden = true)
    @ExcelField(title = "", isShow = false)
    private BigDecimal collectResource;

    //市场利用总量则不加直接还田量
    @ApiModelProperty(value = "市场主体利用总量", hidden = true)
    @ExcelField(title = "", isShow = false)
    private BigDecimal mainTotal;

    @ApiModelProperty(value = "直接还田量", hidden = true)
    @ExcelField(title = "", isShow = false)
    private BigDecimal returnResource;

    //调出量
    //产生量

    public StrawUtilizeResVo3() {
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
    }
}
