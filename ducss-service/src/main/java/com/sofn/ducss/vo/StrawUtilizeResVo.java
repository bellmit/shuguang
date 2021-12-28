package com.sofn.ducss.vo;

import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.util.StrawCalculatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@ApiModel(value="农作物秸秆利用量汇总数据实体")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class StrawUtilizeResVo {
    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "strawName", value = "秸秆名称")
    private String strawName;//秸秆名称

    @ApiModelProperty(name = "mainFertilising", value = "市场主体肥料化")
    private BigDecimal mainFertilising;

    @ApiModelProperty(name = "mainForage", value = "市场主体饲料化")
    private BigDecimal mainForage;

    @ApiModelProperty(name = "mainFuel", value = "市场主体燃料化")
    private BigDecimal mainFuel;

    @ApiModelProperty(name = "mainBase", value = "市场主体基料化")
    private BigDecimal mainBase;

    @ApiModelProperty(name = "mainMaterial", value = "市场主体原料化")
    private BigDecimal mainMaterial;

    @ApiModelProperty(name = "mainTotal", value = " 主体合计")
    private BigDecimal mainTotal;

    @ApiModelProperty(name = "disperseFertilising", value = "分散利用肥料化")
    private BigDecimal disperseFertilising;

    @ApiModelProperty(name = "disperseForage", value = "分散利用饲料化")
    private BigDecimal disperseForage;

    @ApiModelProperty(name = "disperseFuel", value = "分散利用燃料化")
    private BigDecimal disperseFuel;

    @ApiModelProperty(name = "disperseBase", value = "分散利用基料化")
    private BigDecimal disperseBase;

    @ApiModelProperty(name = "disperseMaterial", value = "分散利用原料化")
    private BigDecimal disperseMaterial;

    @ApiModelProperty(name = "disperseTotal", value = "分散利用合计")
    private BigDecimal disperseTotal;

    @ApiModelProperty(name = "proStillField", value = "直接还田量")
    private BigDecimal proStillField;

    @ApiModelProperty(name = "mainTotalOther", value = "其他县购入/市场化主体调入量")
    private BigDecimal mainTotalOther;

    @ApiModelProperty(name = "yieldAllExport", value = "区域调出量")
    private BigDecimal yieldAllExport;

    @ApiModelProperty(name = "proStrawUtilize", value = "秸秆利用量(综合利用量)")
    private BigDecimal proStrawUtilize;

    @ApiModelProperty(name = "comprehensive", value = "综合利用率")
    private BigDecimal comprehensive;

    @ApiModelProperty(name = "collectResource", value = "可收集资源量")
    private BigDecimal collectResource;

    @ApiModelProperty(name = "theoryResource", value = "产生量",hidden = true)
    private BigDecimal theoryResource;

    public StrawUtilizeResVo() {
        super();

        BigDecimal zero = BigDecimal.ZERO;
        this.mainFertilising=zero;
        this.mainForage=zero;
        this.mainFuel=zero;
        this.mainBase=zero;
        this.mainMaterial=zero;
        this.mainTotal=zero;
        this.mainTotalOther=zero;
        this.disperseFertilising=zero;
        this.disperseForage=zero;
        this.disperseFuel=zero;
        this.disperseBase=zero;
        this.disperseMaterial=zero;
        this.disperseTotal=zero;
        this.proStillField=zero;
        this.yieldAllExport=zero;
        this.proStrawUtilize=zero;
        this.comprehensive=zero;
        this.collectResource=zero;
        this.theoryResource=zero;
    }
}
