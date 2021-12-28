package com.sofn.ducss.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(value = "产生量与与利用量汇总数据实体")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ExcelSheetInfo(sheetName = "产生量与与利用量汇总数据列表")
public class StrawProduceUtilizeResVo {

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "areaId", value = "区域")
    private String areaId;

    @ExcelField(title = "区域")
    @ApiModelProperty(name = "areaName", value = "区域名称")
    private String areaName;

    @ExcelField(title = "产生量")
    @ApiModelProperty(name = "theoryResource", value = "产生量=粮食产量（吨）*草谷比")
    private BigDecimal theoryResource;

    @ExcelField(title = "可收集量")
    @ApiModelProperty(name = "collectResource", value = "可收集量")
    private BigDecimal collectResource;

    @ExcelField(title = "调入量", isShow = false)
    @ApiModelProperty(name = "mainTotalOther", value = "调入量")
    private BigDecimal mainTotalOther;

    @ExcelField(title = "区域调出量", isShow = false)
    @ApiModelProperty(name = "yieldAllExport", value = "区域调出量")
    private BigDecimal yieldAllExport;

    @ExcelField(title = "秸秆利用量")
    @ApiModelProperty(name = "proStrawUtilize", value = "秸秆利用量")
    private BigDecimal proStrawUtilize;

    @ExcelField(title = "肥料化利用量")
    @ApiModelProperty(name = "fertilising", value = "肥料化利用量")
    private BigDecimal fertilising;

    @ExcelField(title = "饲料化利用量")
    @ApiModelProperty(name = "forage", value = "饲料化利用量")
    private BigDecimal forage;

    @ExcelField(title = "燃料化利用量")
    @ApiModelProperty(name = "fuel", value = "燃料化利用量")
    private BigDecimal fuel;

    @ExcelField(title = "基料化利用量")
    @ApiModelProperty(name = "base", value = "基料化利用量")
    private BigDecimal base;

    @ExcelField(title = "原料化利用量")
    @ApiModelProperty(name = "material", value = "原料化利用量")
    private BigDecimal material;

    @ExcelField(title = "综合利用率")
    @ApiModelProperty(name = "comprehensive", value = "综合利用率")
    private BigDecimal comprehensive;

    @ExcelField(title = "综合利用能力指数")
    @ApiModelProperty(name = "comprehensiveIndex", value = "综合利用能力指数")
    private BigDecimal comprehensiveIndex;

    @ExcelField(title = "产业化利用能力指数")
    @ApiModelProperty(name = "industrializationIndex", value = "产业化利用能力指数")
    private BigDecimal industrializationIndex;


    @ExcelField(title = "合计")
    @ApiModelProperty(name = "sum", value = "分散+主体+直接还田")
    private BigDecimal sum;


    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "proStillField", value = "直接还田量", hidden = true)
    private BigDecimal proStillField;


    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "mainTotal", value = "主体总利用量", hidden = true)
    private BigDecimal mainTotal;

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "disperseTotal", value = "分散总利用量", hidden = true)
    private BigDecimal disperseTotal;


    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "collectResourceV2", value = "可收集量(用于计算综合利用率)")
    private BigDecimal collectResourceV2;

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "proStrawUtilizeV2", value = "秸秆利用量(用于计算综合利用率)")
    private BigDecimal proStrawUtilizeV2;


    public StrawProduceUtilizeResVo() {
        super();

        BigDecimal zero = BigDecimal.ZERO;
        this.theoryResource = zero;
        this.collectResource = zero;
        this.proStrawUtilize = zero;
        this.fertilising = zero;
        this.forage = zero;
        this.fuel = zero;
        this.base = zero;
        this.material = zero;
        this.comprehensive = zero;
        this.comprehensiveIndex = zero;
        this.industrializationIndex = zero;
        this.sum = zero;
        this.proStillField = zero;
        this.mainTotal = zero;
        this.disperseTotal = zero;
        this.mainTotalOther = zero;
        this.yieldAllExport = zero;
        this.collectResourceV2 = zero;
        this.proStrawUtilizeV2 = zero;
    }
}
