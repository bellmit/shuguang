package com.sofn.ducss.service;

import com.sofn.ducss.model.ThresholdValueManager;
import com.sofn.ducss.vo.ThresholdValueManagerVo;

import java.util.List;
import java.util.Map;

public interface ThresholdValueManagerService {

    /**
     * 获取年度表格
     * @param year  年份
     * @return    List<Map<String,String>>
     */
    List<Map<String,String>> getYearTableList(String year);


    /**
     * 编辑值
     * @param thresholdValueManagerVos  所有阈值
     */
    void editValue(List<ThresholdValueManagerVo>  thresholdValueManagerVos);

    /**
     * 查询具体的阈值信息
     * @param year  年份
     * @param tableType  表格类型
     * @return   ThresholdValueManagerVo
     */
    List<ThresholdValueManagerVo> getInfo(String year, String tableType );

    /**
     * 具体的阈值信息
     * @param year   年度
     * @return  List<ThresholdValueManagerVo>
     */
    List<ThresholdValueManager> getInfo(String year);

    /**
     * 根据年份删除阈值信息
     * @param year   年份
     */
    void deleteByYear(String year);

    /**
     * 批量保存
     * @param thresholdValueManagerVos  阈值列表
     */
    void batchSave(List<ThresholdValueManager> thresholdValueManagerVos);

}
