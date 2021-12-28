package com.sofn.ducss.util;

import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.vo.StrawProduceUtilizeResVo;
import com.sofn.ducss.vo.StrawUtilizeResVo;
import com.sofn.ducss.vo.StrawUtilizeResVo2;
import com.sofn.ducss.vo.StrawUtilizeResVo3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StrawCalculatorUtil {
    /**
     * 计算分散利用量
     *  分散利用量 = （分散五料化秸秆产量 * 可收集资源量） / 秸秆产生量
     *  先乘再除，结果保留两10位小数
     * @param disperse  分散五料化秸秆产量
     * @param collectResource  可收集资源量
     * @param produce   秸秆产生量
     * @return
     */
    public static BigDecimal calculationDisperseRatio(BigDecimal disperse, BigDecimal collectResource, BigDecimal produce){
        //防空
        if(disperse==null) disperse = BigDecimal.ZERO;
        if(collectResource==null) collectResource = BigDecimal.ZERO;
        if(produce==null) produce = BigDecimal.ZERO;

        if(produce.compareTo(BigDecimal.ZERO)>0){
            return disperse.multiply(collectResource).divide(produce, 10, RoundingMode.HALF_UP);
        }else
            return BigDecimal.ZERO; //如果秸秆产生量为0，则利用率必为0
    }

    /**
     * 计算总主体利用量
     * 主体利用量 = 主体肥料化量 + 主体饲料化量 + 主体燃料化量 + 主体基料化量 + 主体原料化量
     * @param detail    主体利用量数据
     * @return
     */
    public static BigDecimal calculationMainUtilize(StrawUtilizeDetail detail){
        //防空
        if(detail==null)
            return BigDecimal.ZERO;

        return calculationUtilize(detail.getFertilising(),detail.getForage(),detail.getFuel(),detail.getBase(),detail.getMaterial());
    }

    /**
     * 计算总分散利用量
     * 分散利用量 = 分散肥料化量 + 分散饲料化量 + 分散燃料化量 + 分散基料化量 + 分散原料化量
     * @param detail    分散利用量数据
     * @return
     */
    public static BigDecimal calculationDisperseUtilize(DisperseUtilizeDetail detail){
        //防空
        if(detail==null)
            return BigDecimal.ZERO;

        return calculationUtilize(detail.getDisperseFertilising(),detail.getDisperseForage(),detail.getDisperseFuel(),detail.getDisperseBase(),detail.getDisperseMaterial());
    }

    /**
     * 计算五料化总利用量
     *  五料化总利用量 = 肥料化量 + 饲料化量 + 燃料化量 + 基料化量 + 原料化量
     * @param fertilising   肥料化量
     * @param forage    饲料化量
     * @param fuel  燃料化量
     * @param base  基料化量
     * @param material  原料化量
     * @return
     */
    public static BigDecimal calculationUtilize(BigDecimal fertilising,BigDecimal forage, BigDecimal fuel, BigDecimal base,BigDecimal material){
        //防空
        if(fertilising==null)   fertilising=BigDecimal.ZERO;
        if(forage==null)   forage=BigDecimal.ZERO;
        if(fuel==null)   fuel=BigDecimal.ZERO;
        if(base==null)   base=BigDecimal.ZERO;
        if(material==null)   material=BigDecimal.ZERO;

        return fertilising.add(forage).add(fuel).add(base).add(material);
    }

    /**
     * 计算总秸秆利用量
     * 总秸秆利用量 = 主体利用量 + 分散利用量 + 直接还田量
     * @param mainUtilize   主体利用量
     * @param disperseUtilize   分散利用量
     * @param returnField   直接还田量
     * @return
     */
    public static BigDecimal calculationStrawUtilize(BigDecimal mainUtilize,BigDecimal disperseUtilize,BigDecimal returnField){
        //防空
        if(mainUtilize==null) mainUtilize = BigDecimal.ZERO;
        if(disperseUtilize == null) disperseUtilize = BigDecimal.ZERO;
        if(returnField ==null ) returnField = BigDecimal.ZERO;

        return mainUtilize.add(disperseUtilize).add(returnField);
    }

    /**
     * 计算总秸秆利用量2 2020-11-6
     * 总秸秆利用量 = 主体利用量 + 分散利用量 + 直接还田量+调出量+调入量
     * @param mainUtilize   主体利用量
     * @param disperseUtilize   分散利用量
     * @param returnField   直接还田量
     * @param yieldAllExport   调出量
     * @param other   调入量
     * @return
     */
    public static BigDecimal calculationStrawUtilize2(BigDecimal mainUtilize,BigDecimal disperseUtilize,BigDecimal returnField,BigDecimal yieldAllExport ,BigDecimal other ){
        //防空
        if(mainUtilize==null) mainUtilize = BigDecimal.ZERO;
        if(disperseUtilize == null) disperseUtilize = BigDecimal.ZERO;
        if(returnField ==null ) returnField = BigDecimal.ZERO;
        if(yieldAllExport ==null ) yieldAllExport = BigDecimal.ZERO;
        if(other ==null ) other = BigDecimal.ZERO;

        return mainUtilize.add(disperseUtilize).add(returnField).add(yieldAllExport).subtract(other);
    }



    /**
     * 计算产生量/理论资源量
     * @param grainYield    粮食产量
     * @param grassValleyRatio  草谷比
     * @return
     */
    public static BigDecimal calculationTheoryResource(BigDecimal grainYield,BigDecimal grassValleyRatio){
        //防空
        if(grainYield==null) grainYield = BigDecimal.ZERO;
        if(grassValleyRatio ==null) grassValleyRatio = BigDecimal.ZERO;

        return grainYield.multiply(grassValleyRatio).setScale(10, BigDecimal.ROUND_UP);
    }

    /**
     * 计算可收集资源量
     * @param theoryResource    产生量
     * @param collectionRatio  收集系数比例
     * @return
     */
    public static BigDecimal calculationCollectResource(BigDecimal theoryResource,BigDecimal collectionRatio){
        //防空
        if(theoryResource==null) theoryResource = BigDecimal.ZERO;
        if(collectionRatio ==null) collectionRatio = BigDecimal.ZERO;

        return theoryResource.multiply(collectionRatio).setScale(10, BigDecimal.ROUND_UP);
    }

    /**
     * 计算直接还田量
     * @param collectResource    可收集资源量
     * @param returnRatio  还田比例
     * @return
     */
    public static BigDecimal calculationReturnResource(BigDecimal collectResource,BigDecimal returnRatio){
        //防空
        if(collectResource==null) collectResource = BigDecimal.ZERO;
        if(returnRatio ==null) returnRatio = BigDecimal.ZERO;

        return collectResource.multiply(returnRatio).divide(new BigDecimal("100"),10, RoundingMode.HALF_UP);
    }

    /**
     * 计算还田比例
     * 还田比例 = 还田面积/播种面积
     * @param seedArea 播种面积
     * @param returnArea    还田面积
     * @return
     */
    public static BigDecimal calculationReturnRatio(BigDecimal seedArea, BigDecimal returnArea){
        //防空
        if(seedArea==null) seedArea = BigDecimal.ZERO;
        if(returnArea==null) returnArea = BigDecimal.ZERO;

        if(returnArea.compareTo(BigDecimal.ZERO)==1){ //还田面积大于0
            return returnArea.multiply(new BigDecimal(100)).divide(seedArea,10, RoundingMode.HALF_UP);
        }else
            return BigDecimal.ZERO;
    }


    /**
     * 计算综合利用能力指数
     * 综合利用能力指数 = （秆总利用量+调出总量) / 可收集资源量
     * @param proStrawUtilize
     * @param yieldAllExport
     * @param collectResource
     * @return
     */
    public static BigDecimal calculationComprehensiveIndex(BigDecimal proStrawUtilize,BigDecimal yieldAllExport,BigDecimal collectResource){
        //防空
        if(proStrawUtilize==null) proStrawUtilize = BigDecimal.ZERO;
        if(yieldAllExport==null) yieldAllExport = BigDecimal.ZERO;
        if(collectResource==null) collectResource = BigDecimal.ZERO;

        if(collectResource.compareTo(BigDecimal.ZERO)==1)   //可收集资源量大于0
            return proStrawUtilize.add(yieldAllExport).divide(collectResource, 10, RoundingMode.HALF_UP);
        else
            return BigDecimal.ZERO;
    }

    /**
     * 计算产业化利用能力指数
     * 产业化利用能力指数=本县的市场主体秸秆利用总量/本县秸秆可收集量
     * @param mainUtilize   总主体利用量
     * @param collectResource   可收集资源量
     * @return
     */
    public static BigDecimal calculationinIndustrializationIndex(BigDecimal mainUtilize,BigDecimal collectResource){
        //防空
        if(mainUtilize==null) mainUtilize=BigDecimal.ZERO;
        if(collectResource==null) collectResource = BigDecimal.ZERO;

        if(collectResource.compareTo(BigDecimal.ZERO)==1)   //可收集资源量大于0
            return mainUtilize.divide(collectResource, 10, RoundingMode.HALF_UP);
        else
            return BigDecimal.ZERO;
    }

    /**
     * 计算综合利用率
     * 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
     * @param proStrawUtilize   秸秆利用量
     * @param totalOther        收购外县的秸秆总量
     * @param yieldAllExport    总调出秸秆量
     * @param collectResource   可收集资源量
     * @return
     */
    public static BigDecimal calculationComprehensiveUtilizeRatio(BigDecimal proStrawUtilize,BigDecimal totalOther
            ,BigDecimal yieldAllExport,BigDecimal collectResource) {
        if(proStrawUtilize==null) proStrawUtilize = BigDecimal.ZERO;
        if(totalOther==null) totalOther = BigDecimal.ZERO;
        if(yieldAllExport==null) yieldAllExport = BigDecimal.ZERO;
        if(collectResource==null) collectResource = BigDecimal.ZERO;

        if(collectResource.compareTo(BigDecimal.ZERO)==1)   //可收集资源量大于0
            return proStrawUtilize.subtract(totalOther)
                    .add(yieldAllExport)
                    .multiply(new BigDecimal(100))
                    .divide(collectResource, 10, RoundingMode.HALF_UP);
        else
            return BigDecimal.ZERO;
    }

    /**
     * 计算综合利用量
     * @param proStrawUtilize 秸秆利用量
     * @param totalOther    收购外县的秸秆总量
     * @param yieldAllExport    总调出秸秆量
     * @return
     */
    public static BigDecimal calculationComprehensiveUtilize(BigDecimal proStrawUtilize,BigDecimal totalOther,BigDecimal yieldAllExport){
        if(proStrawUtilize==null) proStrawUtilize = BigDecimal.ZERO;
        if(totalOther==null) totalOther = BigDecimal.ZERO;
        if(yieldAllExport==null) yieldAllExport = BigDecimal.ZERO;

        BigDecimal cu = proStrawUtilize.subtract(totalOther).add(yieldAllExport);
        if(cu.compareTo(BigDecimal.ZERO)<0) cu = BigDecimal.ZERO;

        return cu;
    }

    /**
     * 组装利用量汇总数据，并返回结果
     * @param psd                 产生量数据
     * @param disperseDetail    分散利用数据
     * @param mainDetail    主体利用数据
     * @return  利用量汇总数据
     */
    public static StrawUtilizeResVo assemblyUtilize(ProStillDetail psd, DisperseUtilizeDetail disperseDetail, StrawUtilizeDetail mainDetail) {
        StrawUtilizeResVo utilize = new StrawUtilizeResVo();
        utilize.setStrawType(psd.getStrawType());
        utilize.setStrawName(psd.getStrawName());
        utilize.setYieldAllExport(psd.getExportYield());  //调出量
        //产生量
        BigDecimal theoryResource = calculationTheoryResource(psd.getGrainYield(), psd.getGrassValleyRatio());
        utilize.setTheoryResource(theoryResource);
        //可收集资源量
        BigDecimal collectResource= calculationCollectResource(theoryResource, psd.getCollectionRatio());
        utilize.setCollectResource(collectResource);

        if(disperseDetail!=null){
            //分散五料化利用
            utilize.setDisperseFertilising(calculationDisperseRatio(disperseDetail.getDisperseFertilising(),collectResource,disperseDetail.getYieldAllNum()));
            utilize.setDisperseForage(calculationDisperseRatio(disperseDetail.getDisperseForage(),collectResource,disperseDetail.getYieldAllNum()));
            utilize.setDisperseFuel(calculationDisperseRatio(disperseDetail.getDisperseFuel(),collectResource,disperseDetail.getYieldAllNum()));
            utilize.setDisperseBase(calculationDisperseRatio(disperseDetail.getDisperseBase(),collectResource,disperseDetail.getYieldAllNum()));
            utilize.setDisperseMaterial(calculationDisperseRatio(disperseDetail.getDisperseMaterial(),collectResource,disperseDetail.getYieldAllNum()));

            utilize.setDisperseTotal(calculationUtilize(utilize.getDisperseFertilising(),utilize.getDisperseForage(),utilize.getDisperseFuel(),utilize.getDisperseBase(),utilize.getDisperseMaterial())); //总分散利用
        }

        if(mainDetail!=null){
            //主体五料化利用
            utilize.setMainFertilising(mainDetail.getFertilising());
            utilize.setMainForage(mainDetail.getForage());
            utilize.setMainFuel(mainDetail.getFuel());
            utilize.setMainBase(mainDetail.getBase());
            utilize.setMainMaterial(mainDetail.getMaterial());

            utilize.setMainTotal(calculationMainUtilize(mainDetail));   //总主体利用
            utilize.setMainTotalOther(mainDetail.getOther());   //调入量
        }

        //直接还田量
        BigDecimal returnResource = calculationReturnResource(collectResource, psd.getReturnRatio());
        utilize.setProStillField(returnResource);
        //秸秆利用量
        BigDecimal proStrawUtilize = calculationStrawUtilize(utilize.getMainTotal(), utilize.getDisperseTotal(), returnResource);
        utilize.setProStrawUtilize(proStrawUtilize);
        //综合利用率
        BigDecimal comprehensiveUtilizeRatio = calculationComprehensiveUtilizeRatio(proStrawUtilize, utilize.getMainTotalOther(), utilize.getYieldAllExport(), collectResource);
        utilize.setComprehensive(comprehensiveUtilizeRatio);


        return utilize;
    }

    /**
     * 组装利用量汇总数据，并返回结果2
     * @param psd                 产生量数据
     * @param disperseDetail    分散利用数据
     * @param mainDetail    主体利用数据
     * @return  利用量汇总数据
     */
    public static StrawUtilizeResVo3 assemblyUtilize2(ProStillDetail psd, DisperseUtilizeDetail disperseDetail, StrawUtilizeDetail mainDetail) {
        StrawUtilizeResVo3 utilize = new StrawUtilizeResVo3();
        utilize.setStrawType(psd.getStrawType());
        utilize.setStrawName(psd.getStrawName());
        utilize.setYieldExport(psd.getExportYield());  //调出量
        //产生量
        BigDecimal theoryResource = calculationTheoryResource(psd.getGrainYield(), psd.getGrassValleyRatio());


        //可收集资源量
        BigDecimal collectResource= calculationCollectResource(theoryResource, psd.getCollectionRatio());
        utilize.setCollectResource(collectResource);

        BigDecimal disperseFertilising=null;
        BigDecimal disperseForage=null;
        BigDecimal disperseFuel=null;
        BigDecimal disperseBase=null;
        BigDecimal disperseMaterial=null;
        BigDecimal disAll=null;
        if(disperseDetail!=null){
            //分散五料化利用
             disperseFertilising = calculationDisperseRatio(disperseDetail.getDisperseFertilising(), collectResource, disperseDetail.getYieldAllNum());
             disperseForage = calculationDisperseRatio(disperseDetail.getDisperseForage(), collectResource, disperseDetail.getYieldAllNum());
             disperseFuel = calculationDisperseRatio(disperseDetail.getDisperseFuel(), collectResource, disperseDetail.getYieldAllNum());
             disperseBase = calculationDisperseRatio(disperseDetail.getDisperseBase(), collectResource, disperseDetail.getYieldAllNum());
             disperseMaterial = calculationDisperseRatio(disperseDetail.getDisperseMaterial(), collectResource, disperseDetail.getYieldAllNum());
             disAll = calculationUtilize(disperseFertilising, disperseForage, disperseFuel, disperseBase, disperseMaterial);//总分散利用
        }

        //主体利用总量
        BigDecimal mainTotal = calculationMainUtilize(mainDetail);
        utilize.setMainTotal(mainTotal);

        //直接还田量
        BigDecimal returnResource = calculationReturnResource(collectResource, psd.getReturnRatio());
        utilize.setReturnResource(returnResource);

        BigDecimal proStrawUtilize=new BigDecimal(0);
        if(mainDetail!=null){
            //主体五料化利用
            utilize.setFertilizer(mainDetail.getFertilising().add(disperseFertilising).add(returnResource));
            utilize.setFeed(mainDetail.getForage().add(disperseForage));
            utilize.setFuel(mainDetail.getFuel().add(disperseFuel));
            utilize.setBasic(mainDetail.getBase().add(disperseBase));
            utilize.setRawMaterial(mainDetail.getMaterial().add(disperseMaterial));
            utilize.setAllTotal(mainTotal.add(disAll));   //总主体利用
            utilize.setOther(mainDetail.getOther());   //调入量
            proStrawUtilize= calculationStrawUtilize2(utilize.getAllTotal(), null, returnResource,utilize.getYieldExport(),mainDetail.getOther());
        }

        //秸秆利用量
        utilize.setStrawUsage(proStrawUtilize);
        //综合利用率
        BigDecimal comprehensiveUtilizeRatio = calculationComprehensiveUtilizeRatio(proStrawUtilize, utilize.getOther(), utilize.getYieldExport(), collectResource);
        utilize.setComprehensiveRate(comprehensiveUtilizeRatio);


        //综合利用能力指数
        utilize.setComprehensiveIndex(   calculationComprehensiveIndex(proStrawUtilize,utilize.getYieldExport(),collectResource));
        //产业化利用能力指数
        utilize.setIndustrializationIndex(calculationinIndustrializationIndex(mainTotal,collectResource));

        return utilize;
    }


    /**
     * 组装产生量与利用量数据,未计算利用率等信息
     * @param psd
     * @param dud
     * @param sud
     * @return StrawProduceUtilizeResVo
     */
    public static StrawProduceUtilizeResVo assemblyProduceUtilize(ProStillDetail psd, DisperseUtilizeDetail dud, StrawUtilizeDetail sud) {
        StrawProduceUtilizeResVo pu = new StrawProduceUtilizeResVo();

        //利用量对象的数据，大多可重用
        StrawUtilizeResVo utilize = assemblyUtilize(psd, dud, sud);

        //五料化
        pu.setFertilising(utilize.getDisperseFertilising().add(utilize.getMainFertilising()));
        pu.setForage(utilize.getDisperseForage().add(utilize.getMainForage()));
        pu.setFuel(utilize.getDisperseFuel().add(utilize.getMainFuel()));
        pu.setBase(utilize.getDisperseBase().add(utilize.getMainBase()));
        pu.setMaterial(utilize.getDisperseMaterial().add(utilize.getMainMaterial()));

        pu.setMainTotal(utilize.getMainTotal());
        pu.setDisperseTotal(utilize.getDisperseTotal());
        pu.setTheoryResource(utilize.getTheoryResource());
        pu.setCollectResource(utilize.getCollectResource());
        pu.setProStillField(utilize.getProStillField());
        pu.setProStrawUtilize(utilize.getProStrawUtilize());
        pu.setYieldAllExport(utilize.getYieldAllExport());
        pu.setMainTotalOther(utilize.getMainTotalOther());

        return pu;
    }

    /**
     * 市场主体料+农夫分散料
     * @param main
     * @param disperse
     * @return
     */
    public static BigDecimal mainAddDisperse(  BigDecimal main,BigDecimal disperse  ){
        if (main==null)  main=BigDecimal.ZERO;

        if (disperse==null) disperse=BigDecimal.ZERO;

        return main.add(disperse);

    }


}
