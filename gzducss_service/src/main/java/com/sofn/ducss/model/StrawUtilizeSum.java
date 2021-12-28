package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.ducss.util.StrawCalculatorUtil2;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@TableName("straw_utilize_sum")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class StrawUtilizeSum extends Model<StrawUtilizeSum> {
    private String id;
    private String year;
    //报告导出时存大区名称
    private String areaName;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String strawType;
    private BigDecimal mainFertilising = new BigDecimal(0.00);// 市场主体肥料
    private BigDecimal mainForage = new BigDecimal(0.00);// 市场主体饲料
    private BigDecimal mainFuel = new BigDecimal(0.00);// 市场主体燃料
    private BigDecimal mainBase = new BigDecimal(0.00);// 市场主体基料
    private BigDecimal mainMaterial = new BigDecimal(0.00);// 市场主体原料
    private BigDecimal mainTotal = new BigDecimal(0.00);// 市场合计
    private BigDecimal mainTotalOther = new BigDecimal(0.00);// 市场调入量合计

    private BigDecimal disperseFertilising = new BigDecimal(0.00);// 分散利用肥料
    private BigDecimal disperseForage = new BigDecimal(0.00);// 分散利用饲料
    private BigDecimal disperseFuel = new BigDecimal(0.00);// 分散利用燃料
    private BigDecimal disperseBase = new BigDecimal(0.00);// 分散利用基料
    private BigDecimal disperseMaterial = new BigDecimal(0.00);// 分散利用原料
    private BigDecimal disperseTotal = new BigDecimal(0.00);// 农户合计
    private BigDecimal proStillField = new BigDecimal(0.00);// 直接还田量
    private BigDecimal proStrawUtilize = new BigDecimal(0.00);// 秸秆利用量
    private BigDecimal returnRatio = new BigDecimal(0.00);// 还田比例
    private BigDecimal comprehensive = new BigDecimal(0.00);// 综合利用率
    private BigDecimal comprehensiveIndex = new BigDecimal(0.00);// 综合利用能力指数
    private BigDecimal industrializationIndex = new BigDecimal(0.00);// 产业化利用能力指数

    private BigDecimal collectResource = new BigDecimal(0.00);// 可收集量
    private BigDecimal yieldAllNum = new BigDecimal(0.00);// 秸秆产量
    private BigDecimal theoryResource = new BigDecimal(0.00); // 产生量=粮食产量*草谷比
    private BigDecimal yieldAllExport = new BigDecimal(0.00);//调出总量

    private BigDecimal fertilising = new BigDecimal(0.00);// 肥料
    private BigDecimal forage = new BigDecimal(0.00);// 饲料
    private BigDecimal fuel = new BigDecimal(0.00);// 燃料
    private BigDecimal base = new BigDecimal(0.00);// 基料
    private BigDecimal material = new BigDecimal(0.00);// 原料
    private BigDecimal grassValleyRatio = new BigDecimal(0.00);// 草谷比
    private BigDecimal returnResource = new BigDecimal(0.00); // 直接还田量=可收集量*还田比例
    private BigDecimal collectionRatio = new BigDecimal(0.00);// 收集系数

    private BigDecimal returnArea = new BigDecimal(0.00);//还田面积
    private BigDecimal seedArea = new BigDecimal(0.00);//播种面积

    private BigDecimal grainYield = new BigDecimal(0.00);// 粮食产量

    //新增五料化利用量字段
    private BigDecimal fertilize = new BigDecimal(0.00);// 肥料化利用量
    private BigDecimal feed = new BigDecimal(0.00);// 饲料化利用量
    private BigDecimal fuelled = new BigDecimal(0.00);// 燃料化利用量
    private BigDecimal baseMat = new BigDecimal(0.00);// 基料化利用量
    private BigDecimal materialization = new BigDecimal(0.00);// 原料化利用量

    private static final long serialVersionUID = 1L;

    private String strawName;
    private Integer orderNo;
    private BigDecimal exportYieldTotal = new BigDecimal(0.00);

    @ApiModelProperty("还田方式")
    private String returnType;

    @ApiModelProperty("离田运输方式")
    private String leavingType;

    @ApiModelProperty("运输补贴")
    private BigDecimal transportAmount;

    public StrawUtilizeSum() {
        this.mainFertilising = new BigDecimal(0);// 市场主体肥料
        this.mainForage = new BigDecimal(0);// 市场主体饲料
        this.mainFuel = new BigDecimal(0);// 市场主体燃料
        this.mainBase = new BigDecimal(0);// 市场主体基料
        this.mainMaterial = new BigDecimal(0);// 市场主体原料
        this.mainTotal = new BigDecimal(0);// 市场合计
        this.disperseFertilising = new BigDecimal(0);// 分散利用肥料
        this.disperseForage = new BigDecimal(0);// 分散利用饲料
        this.disperseFuel = new BigDecimal(0);// 分散利用燃料
        this.disperseBase = new BigDecimal(0);// 分散利用基料
        this.disperseMaterial = new BigDecimal(0);// 分散利用原料
        this.disperseTotal = new BigDecimal(0);// 市场分散合计
        this.proStillField = new BigDecimal(0);// 直接还田量
        this.proStrawUtilize = new BigDecimal(0);
        this.returnRatio = new BigDecimal(0);
        this.comprehensive = new BigDecimal(0);
        this.collectResource = new BigDecimal(0);
        this.yieldAllNum = new BigDecimal(0);
        this.theoryResource = new BigDecimal(0);
        this.yieldAllExport = new BigDecimal(0);

        this.fertilising = new BigDecimal(0);
        this.forage = new BigDecimal(0);
        this.fuel = new BigDecimal(0);
        this.base = new BigDecimal(0);
        this.material = new BigDecimal(0);

        this.mainTotalOther = new BigDecimal(0);
        this.comprehensiveIndex = new BigDecimal(0);
        this.industrializationIndex = new BigDecimal(0);

        this.seedArea = new BigDecimal(0);//播种面积
        this.returnArea = new BigDecimal(0); //还田面积
    }

    // 生产量与直接还田量
    public void setDucProStillDeatil(ProStillDetail deatil) {
        this.grassValleyRatio = deatil.getGrassValleyRatio();
        this.returnResource = deatil.getReturnResource();
        this.proStillField = deatil.getReturnResource();
        this.collectionRatio = deatil.getCollectionRatio();
        this.strawType = deatil.getStrawType();
        this.collectResource = deatil.getCollectResource();
        this.theoryResource = deatil.getTheoryResource();
//	this.returnRatio = deatil.getReturnRatio();
        this.yieldAllExport = deatil.getExportYield();
        this.seedArea = deatil.getSeedArea();
        this.returnArea = deatil.getReturnArea();
        this.grainYield = deatil.getGrainYield();
        // 新增字段
        this.returnType = deatil.getReturnType();
        this.leavingType = deatil.getLeavingType();
        this.transportAmount = deatil.getTransportAmount();
    }

    // 分散量
    public void setDucDisperseUtilizeDetail(com.sofn.ducss.model.DisperseUtilizeDetail deatil) {
        //农户分散已经计算n位
        this.base = deatil.getDisperseBase();
        this.fertilising = deatil.getDisperseFertilising();
        this.forage = deatil.getDisperseForage();
        this.fuel = deatil.getDisperseFuel();
        this.material = deatil.getDisperseMaterial();
        this.yieldAllNum = deatil.getYieldAllNum();
    }

    // 计算分散量
    public void calculationDisperse() {
        if (yieldAllNum.compareTo(new BigDecimal(0)) > 0) {

            //2019.11.13修正计算公式
            this.disperseBase = StrawCalculatorUtil2.calculationDisperseRatio(base, collectResource, yieldAllNum);
            this.disperseFertilising = StrawCalculatorUtil2.calculationDisperseRatio(fertilising, collectResource, yieldAllNum);
            this.disperseForage = StrawCalculatorUtil2.calculationDisperseRatio(forage, collectResource, yieldAllNum);
            this.disperseFuel = StrawCalculatorUtil2.calculationDisperseRatio(fuel, collectResource, yieldAllNum);
            this.disperseMaterial = StrawCalculatorUtil2.calculationDisperseRatio(material, collectResource, yieldAllNum);

//            this.disperseBase = base.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
//            this.disperseFertilising = fertilising.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
//            this.disperseForage = forage.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
//            this.disperseFuel = fuel.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
//            this.disperseMaterial = material.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP);
        }
    }

    // 主体
    public void setDucStrawUtilizeDetail(StrawUtilizeDetail deatil) {
        this.mainBase = deatil.getBase();
        this.mainFertilising = deatil.getFertilising();
        this.mainForage = deatil.getForage();
        this.mainFuel = deatil.getFuel();
        this.mainMaterial = deatil.getMaterial();
        this.mainTotalOther = deatil.getOther();
    }

    public void calculateNum() {
        calculationDisperse();
        calculateNumNoDisperse();
		/*mainTotal = mainFertilising.add(mainForage).add(mainFuel).add(mainBase).add(mainMaterial);
		disperseTotal = disperseFertilising.add(disperseForage).add(disperseFuel).add(disperseBase)
			.add(disperseMaterial);
		proStrawUtilize = mainTotal.add(disperseTotal).add(proStillField);
		if (proStrawUtilize.compareTo(new BigDecimal(0)) > 0 && collectResource.compareTo(new BigDecimal(0)) > 0) {
			// 综合利用能力指数 =某区域的秸秆总利用量/某区域的可收集量。
			comprehensiveIndex = proStrawUtilize.divide(collectResource, 10, RoundingMode.HALF_UP);
			// 产业化利用能力指数=本县的市场主体秸秆利用总量/本县秸秆可收集量
			industrializationIndex = mainTotal.divide(collectResource, 10, RoundingMode.HALF_UP);

			*//*2019.04.16修改公式，*//*
			// 综合利用率 =（本县秸秆利用量-收购外县的秸秆总量）+调出秸秆量/可收集量
			comprehensive = proStrawUtilize.subtract(mainTotalOther)
					.add(yieldAllExport)
					.multiply(new BigDecimal(100))
					.divide(collectResource, 10, RoundingMode.HALF_UP);

		}*/
    }

    public void calculateNum2() {
        calculationDisperse();
        calculateNumNoDisperse();
        calculationUtilization();
    }

    //计算五料化利用量
    private void calculationUtilization() {
        fertilize = mainFertilising.add(disperseFertilising).add(proStillField);
        feed = mainForage.add(disperseForage);
        fuelled = mainFuel.add(disperseFuel);
        baseMat = mainBase.add(disperseBase);
        materialization = mainMaterial.add(disperseMaterial);
    }

    //总结处计算总数，但不计算分散量
    public void calculateNumNoDisperse() {
        mainTotal = mainFertilising.add(mainForage).add(mainFuel).add(mainBase).add(mainMaterial);
        disperseTotal = disperseFertilising.add(disperseForage).add(disperseFuel).add(disperseBase)
                .add(disperseMaterial);
        //秸秆利用量=市场+分散利用量+直接还田量+调出量-调入量
        proStrawUtilize = StrawCalculatorUtil2.calculationStrawUtilize(mainTotal, disperseTotal, returnResource, yieldAllExport, mainTotalOther);
        //proStrawUtilize = mainTotal.add(disperseTotal).add(proStillField);
        //还田比例计算，还田面积/播种面积
        if (seedArea.compareTo(BigDecimal.ZERO) == 1) {
            returnRatio = returnArea.multiply(new BigDecimal(100)).divide(seedArea, 10, RoundingMode.HALF_UP);
        }

        if ((proStrawUtilize.compareTo(new BigDecimal(0)) > 0 || yieldAllExport.compareTo(new BigDecimal(0)) == 1)
                && collectResource.compareTo(new BigDecimal(0)) > 0) {
            // 综合利用能力指数 =秸秆利用量+调出总量/某区域的可收集量。
            comprehensiveIndex = StrawCalculatorUtil2.calculationComprehensiveindex(mainTotal, disperseTotal, returnResource, collectResource);
            //comprehensiveIndex = proStrawUtilize.add(yieldAllExport).divide(collectResource, 10, RoundingMode.HALF_UP);
            // 产业化利用能力指数=本县的市场主体秸秆利用总量/本县秸秆可收集量
            industrializationIndex = StrawCalculatorUtil2.calculationinIndustrializationIndex(mainTotal, collectResource);
            //industrializationIndex = mainTotal.divide(collectResource, 10, RoundingMode.HALF_UP);

            //综合利用率=秸秆利用量/可收集量 *100
            comprehensive = StrawCalculatorUtil2.calculationComprehensiveRote(proStrawUtilize, collectResource);

//            comprehensive = proStrawUtilize.subtract(mainTotalOther)
//                    .add(yieldAllExport)
//                    .multiply(new BigDecimal(100))
//                    .divide(collectResource, 10, RoundingMode.HALF_UP);

        }
    }

}