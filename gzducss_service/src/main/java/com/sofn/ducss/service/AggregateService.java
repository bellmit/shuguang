package com.sofn.ducss.service;

import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.*;

import java.util.List;

public interface AggregateService {

    /**
     * 获取产生量汇总数据
     *
     * @param vo
     * @return
     */
    List<StrawProduceResVo> getStrawProduceData(AggregateQueryVo vo);

    /**
     * 获取利用量汇总数据
     *
     * @param vo
     * @return
     */
    List<StrawUtilizeResVo> getStrawUtilzeData(AggregateQueryVo vo);

    /**
     * 获取利用量汇总数据2
     *
     * @param vo
     * @return
     */
    List<StrawUtilizeResVo3> getStrawUtilzeData2(AggregateQueryVo vo);


    /**
     * 获取产生量与利用量汇总数据
     *
     * @param vo
     * @return
     */
    List<StrawProduceUtilizeResVo> findStrawProduceAndUtilzeData(AggregateQueryVo vo);

    /**
     * 获取产生量与利用量汇总数据2
     *
     * @param vo
     * @return
     */
    List<StrawProduceUtilizeResVo2> findStrawProduceAndUtilzeData2(AggregateQueryVo vo);

    /**
     * 获取市场化主体利用量汇总数据
     *
     * @param queryVo
     * @return
     */
    List<MainUtilizeResVo> findMainUtilizeData(AggregateMainUtilizeQueryVo queryVo);

    /**
     * 获取市场化主体利用量汇总数据
     * 分页
     *
     * @param vo
     * @return
     */
    PageUtils<MainUtilizeResVo> findMainUtilizeDataPage(AggregateMainUtilizeQueryVo vo);

    /**
     * 获得单个市场主体利用量信息
     *
     * @param mainId
     * @return
     */
    StrawUtilizeVo findMainUtilizeOneData(String mainId);

    /**
     * 获取已发布的区域利用汇总
     *
     * @param queryVo
     * @return
     */
    List<StrawProduceUtilizeResVo> findAreaUtilizeData(AggregateQueryVo queryVo);

    /**
     * 获取产生量汇总数据2
     *
     * @param vo
     * @return
     */

    List<StrawProduceResVo2> getStrawProduceData2(AggregateQueryVo vo);

    /**
     * 获取还田离田汇总数据
     *
     * @param queryVo
     * @return
     */
    List<ReturnLeaveSumVo> findReturnLeaveSumData(AggregateQueryVo queryVo);

    /**
     * 获取收集量和产生量，县级秸秆综合利用量
     *
     * @param queryVo
     * @return
     */
    StrawBigDataVo bigDataIndexSum(AggregateQueryVo queryVo);

    /**
     * 秸秆综合利用情况汇总
     *
     * @param queryVo
     * @return
     */
    List<StrawUsageVo> findStrawUsage(AggregateQueryVo queryVo);

}
