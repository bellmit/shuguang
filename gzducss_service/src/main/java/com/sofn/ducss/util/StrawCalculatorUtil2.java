package com.sofn.ducss.util;

import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.vo.StrawProduceUtilizeResVo;
import com.sofn.ducss.vo.StrawUtilizeResVo3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StrawCalculatorUtil2 {


    /**
     * 计算总秸秆利用量
     * 总秸秆利用量 = 主体利用量 + 分散利用量 + 直接还田量+调出量-调入量
     * @param mainUtilize   主体利用量
     * @param disperseUtilize   分散利用量
     * @param returnField   直接还田量
     * @param yieldAllExport   调出量
     * @param other   调入量
     * @return
     */
    public static BigDecimal calculationStrawUtilize(BigDecimal mainUtilize,BigDecimal disperseUtilize,BigDecimal returnField,BigDecimal yieldAllExport ,BigDecimal other ){
        //防空
        if(mainUtilize==null) mainUtilize = BigDecimal.ZERO;
        if(disperseUtilize == null) disperseUtilize = BigDecimal.ZERO;
        if(returnField ==null ) returnField = BigDecimal.ZERO;
        if(yieldAllExport ==null ) yieldAllExport = BigDecimal.ZERO;
        if(other ==null ) other = BigDecimal.ZERO;

        return mainUtilize.add(disperseUtilize).add(returnField).add(yieldAllExport).subtract(other);
    }



    /**
     * 计算综合利用率
     * =秸秆利用量/可收集量*100%
     * 秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)
     * @param proStrawUtilize
     * @param collectResource
     * @return
     */
    public static BigDecimal calculationComprehensiveRote(BigDecimal proStrawUtilize,BigDecimal collectResource){
        if(proStrawUtilize==null) proStrawUtilize = BigDecimal.ZERO;
        if(collectResource==null||collectResource.compareTo(new BigDecimal(0))==0) {
            return BigDecimal.ZERO;
        }else {
            return proStrawUtilize.divide(collectResource,10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        }

    }

    /**
     * 计算综合利用率
     * =秸秆利用量/可收集量*100%
     * 秸秆利用量：秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)。
     * @param mainTotal 市场主体利用量
     * @param disperseTotal 农户分散利用量
     * @param proStillField 直接还田量
     * @param yieldAllExport 调出量
     * @param mainTotalOther 调入量
     * @param collectResource 可收集资源量
     * @return
     */
    public static BigDecimal calculationComprehensiveRote(  BigDecimal mainTotal,BigDecimal disperseTotal,
                                                                BigDecimal proStillField,
                                                            BigDecimal yieldAllExport, BigDecimal mainTotalOther,
                                                            BigDecimal collectResource)

    {

        if(collectResource==null||collectResource.compareTo(new BigDecimal(0))==0) {
            return BigDecimal.ZERO;
        }else {
            //防空
            if(mainTotal==null) mainTotal = BigDecimal.ZERO;
            if(disperseTotal==null) disperseTotal = BigDecimal.ZERO;
            if(proStillField==null) proStillField = BigDecimal.ZERO;
            if(yieldAllExport==null) yieldAllExport = BigDecimal.ZERO;
            if(mainTotalOther==null) mainTotalOther = BigDecimal.ZERO;
            if(collectResource==null) collectResource = BigDecimal.ZERO;

            //秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)
            BigDecimal comprehensive = mainTotal.add(disperseTotal).add(proStillField).add(yieldAllExport)
                    .subtract(mainTotalOther).divide(collectResource, 10, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

            return comprehensive;
        }


    }

    //

    /** 综合利用能力指数：市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100
     *
     * @param mainTotal 市场主体规模化利用量
     * @param disperseTotal 农户分散利用量
     * @param proStillField 直接还田量
     * @param collectResource 可收集量
     * @return
     */
    public static BigDecimal calculationComprehensiveindex(  BigDecimal mainTotal,BigDecimal disperseTotal,
                                                            BigDecimal proStillField,
                                                            BigDecimal collectResource){


        if(collectResource==null||collectResource.compareTo(new BigDecimal(0))==0) {
            return BigDecimal.ZERO;
        }else {
            //防空
            if(mainTotal==null) mainTotal = BigDecimal.ZERO;
            if(disperseTotal==null) disperseTotal = BigDecimal.ZERO;
            if(proStillField==null) proStillField = BigDecimal.ZERO;

            //市场主体规模化利用量+农户分散利用量+直接还田量/可收集量

            BigDecimal comprehensiveindex = mainTotal.add(disperseTotal).add(proStillField)
                   .divide(collectResource, 10, BigDecimal.ROUND_HALF_UP);

            return comprehensiveindex;
        }

    }



    /**
     * 计算产业化利用能力指数
     * 产业化利用能力指数=本县的市场主体秸秆利用总量/本县秸秆可收集量
     * @param mainTotal   总主体利用量
     * @param collectResource   可收集资源量
     * @return
     */
    public static BigDecimal calculationinIndustrializationIndex(BigDecimal mainTotal,BigDecimal collectResource){
        //防空
        if(mainTotal==null) mainTotal=BigDecimal.ZERO;
        if(collectResource==null) collectResource = BigDecimal.ZERO;

        if(collectResource.compareTo(BigDecimal.ZERO)==1)   //可收集资源量大于0
            return mainTotal.divide(collectResource, 10, RoundingMode.HALF_UP);
        else
            return BigDecimal.ZERO;
    }

    /**
     *
     * 产生量（吨）=粮食产量（吨）*草谷比
     *
     * @param grainYield 粮食产量
     * @param grassValleyRatio 草谷比
     * @return
     */
    public static BigDecimal cProud( BigDecimal grainYield,BigDecimal grassValleyRatio ){

        if (grainYield==null) grainYield=BigDecimal.ZERO;
        if (grassValleyRatio==null) grassValleyRatio=BigDecimal.ZERO;
        return grainYield.multiply(grassValleyRatio);
    }

    /**
     * 计算可收集量：可收集量（吨）=产生量（吨）*收集系数
     * @param produce 产生量
     * @param collectionRatio 收集系数
     * @return
     */
    public static BigDecimal cCollectResource( BigDecimal produce,BigDecimal collectionRatio ){

        if (produce==null) produce=BigDecimal.ZERO;
        if (collectionRatio==null) collectionRatio=BigDecimal.ZERO;
        return produce.multiply(collectionRatio);
    }


    /**
     * 计算五料化总利用量
     *  五料化总利用量 = 肥料化量 + 饲料化量 + 燃料化量 + 基料化量 + 原料化量
     * @param fertilising   肥料化量=(市场主体肥料化+直接还田量)
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
     * 计算还田比例
     * 还田比例 = 还田面积/播种面积*100
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
     * 利用量情况汇总：肥料化利用量单独处理=(市场主体肥料化+直接还田量)
     * @param fertilising
     * @param proStillField
     * @return
     */
    public static BigDecimal cmainFertilising(BigDecimal fertilising ,BigDecimal proStillField ){
        if (fertilising==null) fertilising=BigDecimal.ZERO;
        if (proStillField==null) proStillField=BigDecimal.ZERO;
        return fertilising.add(proStillField);
    }


    /**
     * 组装利用量汇总数据，并返回结果 ok
     * @param psd                 产生量数据
     * @param disperseDetail    分散利用数据
     * @param mainDetail    主体利用数据
     * @return  利用量汇总数据
     */

    public static StrawUtilizeResVo3 assemblyUtilize(ProStillDetail psd, DisperseUtilizeDetail disperseDetail, StrawUtilizeDetail mainDetail) {
        StrawUtilizeResVo3 utilize = new StrawUtilizeResVo3();
        utilize.setStrawType(psd.getStrawType());
        utilize.setStrawName(psd.getStrawName());
        //调出量
        utilize.setYieldExport(psd.getExportYield());

        //产生量
        BigDecimal theoryResource = cProud(psd.getGrainYield(), psd.getGrassValleyRatio());

        //可收集资源量=产生量*收集系数
        BigDecimal collectResource= cCollectResource(theoryResource, psd.getCollectionRatio());
        utilize.setCollectResource(collectResource);

        //直接还田量
        BigDecimal returnResource = calculationReturnResource(collectResource, psd.getReturnRatio());
        utilize.setReturnResource(returnResource);

        //肥料额外加直接还田量
        BigDecimal disperseFertilising=BigDecimal.ZERO;
        BigDecimal disperseForage=BigDecimal.ZERO;
        BigDecimal disperseFuel=BigDecimal.ZERO;
        BigDecimal disperseBase=BigDecimal.ZERO;
        BigDecimal disperseMaterial=BigDecimal.ZERO;
        //分散之和
        BigDecimal disAll=BigDecimal.ZERO;

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

        //秸秆利用量
        BigDecimal proStrawUtilize=new BigDecimal(0);
        if(mainDetail!=null){
            //主体五料化利用
            //肥料额外加直接还田量
            utilize.setFertilizer(mainDetail.getFertilising().add(disperseFertilising).add(returnResource));
            utilize.setFeed(mainDetail.getForage().add(disperseForage));
            utilize.setFuel(mainDetail.getFuel().add(disperseFuel));
            utilize.setBasic(mainDetail.getBase().add(disperseBase));
            utilize.setRawMaterial(mainDetail.getMaterial().add(disperseMaterial));
            //此处allTotal=市场+分散+直接还田
            utilize.setAllTotal(mainTotal.add(disAll).add(returnResource));
            //调入量
            utilize.setOther(mainDetail.getOther());
            proStrawUtilize = calculationStrawUtilize(mainTotal, disAll, returnResource,psd.getExportYield(),mainDetail.getOther());
        }else{
            //比如市场主体没有填写
            //主体五料化利用
            //肥料额外加直接还田量
            utilize.setFertilizer(disperseFertilising.add(returnResource));
            utilize.setFeed(disperseForage);
            utilize.setFuel(disperseFuel);
            utilize.setBasic(disperseBase);
            utilize.setRawMaterial(disperseMaterial);
            //此处allTotal=市场+分散+直接还田
            utilize.setAllTotal(disAll.add(returnResource));
            //调入量
            utilize.setOther(BigDecimal.ZERO);
            proStrawUtilize = calculationStrawUtilize(mainTotal, disAll, returnResource,psd.getExportYield(),null);
        }

        //秸秆利用量
        utilize.setStrawUsage(proStrawUtilize);
        //综合利用率
        BigDecimal comprehensiveUtilizeRatio = calculationComprehensiveRote(proStrawUtilize,collectResource);
        utilize.setComprehensiveRate(comprehensiveUtilizeRatio);

        //综合利用能力指数
        utilize.setComprehensiveIndex(   calculationComprehensiveindex(mainTotal,disAll,returnResource,collectResource));
        //产业化利用能力指数
        utilize.setIndustrializationIndex(calculationinIndustrializationIndex(mainTotal,collectResource));


        return utilize;
    }







    /**
     * 计算分散利用量
     *  分散利用量 = （分散五料化秸秆产量 * 可收集资源量） / 秸秆产生量
     *  先乘再除，结果保留两10位小数
     * @param disperse  分散五料化秸秆产量：通过sql查询出来的。
     * @param collectResource  可收集资源量
     * @param produce   秸秆产生量
     *  分散利用量= (n1+n2+n3)/(y1+y2+y3)*可收集资源量
     * (n1+n2+n3)= 分散五料化秸秆产量
     *(y1+y2+y3)=秸秆产生量
     *
     *
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
     * 计算直接还田量=区域秸秆可收集量×(区域农作物秸秆还田面积/区域农作物播种面积)×100%
     * 或 =区域秸秆可收集量*还田比例
     * @param collectResource    区域秸秆可收集量
     * @param returnRatio  还田比例
     * @return
     */
    public static BigDecimal calculationReturnResource(BigDecimal collectResource,BigDecimal returnRatio){
        //防空
        if(collectResource==null) collectResource = BigDecimal.ZERO;
        if(returnRatio ==null) returnRatio = BigDecimal.ZERO;

        return collectResource.multiply(returnRatio).divide(new BigDecimal(100));
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
        StrawUtilizeResVo3 utilize = assemblyUtilize(psd, dud, sud);
        //五料化
        pu.setFertilising(utilize.getFertilizer());
        pu.setForage(utilize.getFeed());
        pu.setFuel(utilize.getFuel());
        pu.setBase(utilize.getBasic());
        pu.setMaterial(utilize.getRawMaterial());

        pu.setMainTotal(utilize.getMainTotal());
        //这两个字段不使用
//        pu.setDisperseTotal(utilize.());
        //产生量=粮食产量（吨）*草谷比(草古比用那个省的呢)
       pu.setTheoryResource(psd.getGrainYield().multiply(psd.getGrassValleyRatio()));
        pu.setCollectResource(utilize.getCollectResource());
        pu.setProStillField(utilize.getReturnResource());
        pu.setProStrawUtilize(utilize.getStrawUsage());
        pu.setYieldAllExport(utilize.getYieldExport());
        pu.setMainTotalOther(utilize.getOther());
        pu.setSum(utilize.getAllTotal());

//        //五料化
//        pu.setFertilising(utilize.getDisperseFertilising().add(utilize.getMainFertilising()));
//        pu.setForage(utilize.getDisperseForage().add(utilize.getMainForage()));
//        pu.setFuel(utilize.getDisperseFuel().add(utilize.getMainFuel()));
//        pu.setBase(utilize.getDisperseBase().add(utilize.getMainBase()));
//        pu.setMaterial(utilize.getDisperseMaterial().add(utilize.getMainMaterial()));
//
//        pu.setMainTotal(utilize.getMainTotal());
//        pu.setDisperseTotal(utilize.getDisperseTotal());
//        pu.setTheoryResource(utilize.getTheoryResource());
//        pu.setCollectResource(utilize.getCollectResource());
//        pu.setProStillField(utilize.getProStillField());
//        pu.setProStrawUtilize(utilize.getProStrawUtilize());
//        pu.setYieldAllExport(utilize.getYieldAllExport());
//        pu.setMainTotalOther(utilize.getMainTotalOther());
        return pu;
    }





}
