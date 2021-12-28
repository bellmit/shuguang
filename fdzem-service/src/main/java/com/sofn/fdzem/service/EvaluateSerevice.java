package com.sofn.fdzem.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.Index;
import com.sofn.fdzem.vo.EvaluateVo;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/19 16:11
 * Description:
 * Version: V1.0
 */
public interface EvaluateSerevice {
    /**
     * 获取评价选项
     *
     * @return
     */
    List<Index> getIndices();

    /**
     * 获取评价管理列表
     *
     * @param pageNum
     * @param pageSize
     * @param organizationName
     * @param submitYear
     * @param lowestScore
     * @param highestScore
     * @return
     */
    PageUtils<EvaluateVo> listPage(Integer pageNum, Integer pageSize, String organizationName, String submitYear, Double lowestScore, Double highestScore,
                                   String field, String isAcs);

    /**
     * 保存评分
     *
     * @param id
     * @param score
     */
    void insert(String id, String score, String date);

    /**
     * 根据id获取评分数据
     *
     * @param id
     * @return
     */
    String getById(String id, String date);
}
