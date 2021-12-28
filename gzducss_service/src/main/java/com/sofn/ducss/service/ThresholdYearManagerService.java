package com.sofn.ducss.service;

import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.ThresholdYearManagerVo;

import java.util.List;

public interface ThresholdYearManagerService {

    /**
     * 新增阈值年度管理
     * @param thresholdYearManagerVo    阈值年度信息
     */
    void insert(ThresholdYearManagerVo thresholdYearManagerVo);

    /**
     * 更新
     * @param thresholdYearManagerVo ThresholdYearManagerVo
     */
    void update(ThresholdYearManagerVo thresholdYearManagerVo);

    /**
     * 删除
     * @param id  待删除ID
     */
    void delete(String id);

    /**
     *  按条件分页查询
     * @param year   年度
     * @param pageNo   从哪条记录开始
     * @param pageSize  每页显示多少条
     * @return   PageUtils<ThresholdYearManager>
     */
    PageUtils<ThresholdYearManagerVo> getList(String year,Integer pageNo, Integer pageSize);

    /**
     * 获取数据库有的年度
     * @return  List<String>
     */
    List<String> getHaveYear();

}
