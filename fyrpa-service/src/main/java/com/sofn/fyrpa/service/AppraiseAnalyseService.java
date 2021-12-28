package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.model.AppraiseAnalyse;

import java.util.List;

public interface AppraiseAnalyseService extends IService<AppraiseAnalyse> {

    /**
     * 评价得分
     * @param appraiseAnalyseList
     * @return
     */
    Result add(List<AppraiseAnalyse> appraiseAnalyseList);

    /**
     * 查询评价效果分析页面
     * @param pageNo
     * @param pageSize
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @return
     */
    Result selectResourceAnalyseList(Integer pageNo, Integer pageSize,String name, String submitTime,
                                    Double startTotalScore, Double endTotalScore, String basinOrSeaArea);


    /**
     * 按批准时间排序
     * @param pageNo
     * @param pageSize
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @param sort
     * @return
     */
    Result selectListByTimeSort(Integer pageNo, Integer pageSize,String name, String submitTime,
                                     Double startTotalScore, Double endTotalScore, String basinOrSeaArea,String sort);


    /**
     * 按评分效果排序
     * @param pageNo
     * @param pageSize
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @param sort
     * @return
     */
    Result selectListByScoreSort(Integer pageNo, Integer pageSize,String name, String submitTime,
                                Double startTotalScore, Double endTotalScore, String basinOrSeaArea,String sort);


    /**
     * 查询评价页面指标集合
     * @return
     */
    Result selectAnalyseList();


    /**
     * 评分详情页查看
     * @return
     */
    Result selectAppraiseAnalyseDetails(String resourceId);
}
