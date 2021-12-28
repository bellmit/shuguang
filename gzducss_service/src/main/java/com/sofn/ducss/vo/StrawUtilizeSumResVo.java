package com.sofn.ducss.vo;

import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.StrawUtilizeDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class StrawUtilizeSumResVo {

    @ApiModelProperty(name = "year", value = "年份")
    private String year;

    @ApiModelProperty(name = "areaId", value = "区域ID")
    private String areaId;

    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "mainFertilising", value = "市场主体肥料化")
    private BigDecimal mainFertilising = new BigDecimal(0.00);

    @ApiModelProperty(name = "mainForage", value = "市场主体饲料化")
    private BigDecimal mainForage = new BigDecimal(0.00);

    @ApiModelProperty(name = "mainFuel", value = "市场主体燃料化")
    private BigDecimal mainFuel = new BigDecimal(0.00);

    @ApiModelProperty(name = "mainBase", value = "市场主体基料化")
    private BigDecimal mainBase = new BigDecimal(0.00);

    @ApiModelProperty(name = "mainMaterial", value = "市场主体原料化")
    private BigDecimal mainMaterial = new BigDecimal(0.00);

    @ApiModelProperty(name = "mainTotal", value = " 主体合计")
    private BigDecimal mainTotal = new BigDecimal(0.00);

    @ApiModelProperty(name = "mainTotalOther", value = "其他县购入")
    private BigDecimal mainTotalOther = new BigDecimal(0.00);

    @ApiModelProperty(name = "disperseFertilising", value = "分散利用肥料化")
    private BigDecimal disperseFertilising = new BigDecimal(0.00);

    @ApiModelProperty(name = "disperseForage", value = "分散利用饲料化")
    private BigDecimal disperseForage = new BigDecimal(0.00);

    @ApiModelProperty(name = "disperseFuel", value = "分散利用燃料化")
    private BigDecimal disperseFuel = new BigDecimal(0.00);

    @ApiModelProperty(name = "disperseBase", value = "分散利用基料化")
    private BigDecimal disperseBase = new BigDecimal(0.00);

    @ApiModelProperty(name = "disperseMaterial", value = "分散利用原料化")
    private BigDecimal disperseMaterial = new BigDecimal(0.00);

    @ApiModelProperty(name = "disperseTotal", value = "分散利用合计")
    private BigDecimal disperseTotal = new BigDecimal(0.00);

    @ApiModelProperty(name = "proStillField", value = "直接还田量")
    private BigDecimal proStillField = new BigDecimal(0.00);

    @ApiModelProperty(name = "proStrawUtilize", value = "秸秆利用量")
    private BigDecimal proStrawUtilize = new BigDecimal(0.00);

    @ApiModelProperty(name = "collectResource", value = "可收集资源量")
    private BigDecimal collectResource = new BigDecimal(0.00);

    @ApiModelProperty(name = "theoryResource", value = "理论资源量=产生量")
    private BigDecimal theoryResource = new BigDecimal(0.00);

    @ApiModelProperty(name = "exportYieldTotal", value = "总调出量")
    private BigDecimal exportYieldTotal = new BigDecimal(0.00);

    private BigDecimal grassValleyRatio;// 草谷比
    private BigDecimal returnResource = new BigDecimal(0.00); // 直接还田量=可收集量*还田比例
    private BigDecimal collectionRatio;// 收集系数
    private BigDecimal yieldAllNum = new BigDecimal(0.00);// 秸秆产生量
    private BigDecimal yieldAllExport = new BigDecimal(0.00);//调出总量
    private BigDecimal returnArea;//还田面积
    private BigDecimal seedArea;//播种面积
    private String strawName;   //秸秆名称

    private BigDecimal fertilising = new BigDecimal(0.00);// 肥料
    private BigDecimal forage = new BigDecimal(0.00);// 饲料
    private BigDecimal fuel = new BigDecimal(0.00);// 燃料
    private BigDecimal base = new BigDecimal(0.00);// 基料
    private BigDecimal material = new BigDecimal(0.00);// 原料

    private BigDecimal returnRatio;// 还田比例
    private BigDecimal comprehensive = new BigDecimal(0.00);// 综合利用率
    private BigDecimal comprehensiveIndex = new BigDecimal(0.00);// 综合利用能力指数
    private BigDecimal industrializationIndex = new BigDecimal(0.00);// 产业化利用能力指数

    private Integer orderNo;//排序
    private String areaName;

    // 产生量与还田量
    public void setProStillDeatil(ProStillDetail deatil) {
        this.grassValleyRatio = deatil.getGrassValleyRatio();
        this.returnResource = deatil.getReturnResource();
        this.proStillField = deatil.getReturnResource();
        this.collectionRatio = deatil.getCollectionRatio();
        this.strawType = deatil.getStrawType();
        this.collectResource = deatil.getCollectResource();
        this.theoryResource = deatil.getTheoryResource();
        this.yieldAllExport = deatil.getExportYield();
        this.seedArea = deatil.getSeedArea();
        this.returnArea=deatil.getReturnArea();
    }

    // 分散量
    public void setDisperseUtilizeDetail(DisperseUtilizeDetail deatil) {
        this.base = deatil.getDisperseBase();
        this.fertilising = deatil.getDisperseFertilising();
        this.forage = deatil.getDisperseForage();
        this.fuel = deatil.getDisperseFuel();
        this.material = deatil.getDisperseMaterial();
        this.yieldAllNum = deatil.getYieldAllNum();
    }

    // 主体
    public void setStrawUtilizeDetail(StrawUtilizeDetail deatil) {
        this.mainBase = deatil.getBase();
        this.mainFertilising = deatil.getFertilising();
        this.mainForage = deatil.getForage();
        this.mainFuel = deatil.getFuel();
        this.mainMaterial = deatil.getMaterial();
        this.mainTotalOther = deatil.getOther();
    }

    // 计算
    public void calculateNum() {
        calculationDisperse();
        calculateNumNoDisperse();
    }

    // 计算分散利用率
    public void calculationDisperse() {
        if (yieldAllNum.compareTo(new BigDecimal(0)) > 0) {
            //2019.04.15修正计算公式, 分散利用率 = 分散利用量 * 可收集资源量 / yieldAllNum
            this.disperseBase = base.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
            this.disperseFertilising = fertilising.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
            this.disperseForage = forage.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
            this.disperseFuel = fuel.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
            this.disperseMaterial = material.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
        }
    }

    //总结处计算总数，但不计算分散量
    public void calculateNumNoDisperse() {
        //主体利用量 求和
        mainTotal = mainFertilising.add(mainForage).add(mainFuel).add(mainBase).add(mainMaterial);
        //分散利用量 求和
        disperseTotal = disperseFertilising.add(disperseForage).add(disperseFuel).add(disperseBase)
                .add(disperseMaterial);
        //总秸秆利用量 = 主体利用量 + 分散利用量 + 直接还田量
        //总利用量=本地来源+外县来源=五料化之和
        //直接还田量，被算作肥料化利用量
        proStrawUtilize = mainTotal.add(disperseTotal).add(proStillField);
        //还田比例 = 还田面积/播种面积
        if(seedArea.compareTo(BigDecimal.ZERO)==1){
            returnRatio = returnArea.multiply(new BigDecimal(100)).divide(seedArea,10, RoundingMode.HALF_UP);
        }

        if ((proStrawUtilize.compareTo(new BigDecimal(0)) > 0 || yieldAllExport.compareTo(new BigDecimal(0)) ==1)
                && collectResource.compareTo(new BigDecimal(0)) > 0) {
            // 综合利用能力指数 =某区域的秸秆总利用量/某区域的可收集量。
            comprehensiveIndex = proStrawUtilize.add(yieldAllExport).divide(collectResource, 10, RoundingMode.HALF_UP);
            // 产业化利用能力指数=本县的市场主体秸秆利用总量/本县秸秆可收集量
            industrializationIndex = mainTotal.divide(collectResource, 10, RoundingMode.HALF_UP);
            /*2019.04.16修改公式，*/
            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
            comprehensive = proStrawUtilize.subtract(mainTotalOther)
                    .add(yieldAllExport)
                    .multiply(new BigDecimal(100))
                    .divide(collectResource, 10, RoundingMode.HALF_UP);

        }
    }

}
