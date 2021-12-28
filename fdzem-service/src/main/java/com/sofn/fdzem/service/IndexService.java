package com.sofn.fdzem.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.Index;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/19 9:13
 * Description:
 * Version: V1.0
 */
public interface IndexService {


    /**
     * 获取指标管理列表
     *
     * @param pageNum
     * @param indexType
     * @param indexName
     * @param startTime
     * @param endTime
     * @return
     */
    PageUtils<Index> listPage(Integer pageNum, Integer pageSize, String indexType, String indexName, String startTime, String endTime);

    /**
     * 保存指标管理
     *
     * @param index
     */
    void saveIndex(Index index);

    /**
     * 根据id查询指标管理
     *
     * @param id
     * @return
     */
    Index getById(Long id);

    /**
     * 修改指标管理
     *
     * @param index
     */
    void updateIndex(Index index);

    /**
     * 修改指标管理状态
     *
     * @param id
     * @param state
     */
    void updateIndexStatus(Long id, Integer state);

    /**
     * 获得一级指标的id和name
     * @return
     */
    List<Index> getTopIndex();
}
