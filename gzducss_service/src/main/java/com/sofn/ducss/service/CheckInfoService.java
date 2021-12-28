package com.sofn.ducss.service;

import com.sofn.ducss.model.CheckInfo;
import com.sofn.ducss.vo.checkcolor.CheckStrawLevelingVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawProduceVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawUtilzeVo;

import java.util.List;

public interface CheckInfoService {

    /**
     * 获取审核信息，包含比较阈值
     *
     * @param year   年份
     * @param areaId 区划
     * @return List<CheckInfo>
     */
    List<CheckInfo> getCheckInfo(String year, String areaId, String status);

    /**
     * 计算秸秆产生量颜色
     *
     * @param year   年份
     * @param areaId 区域
     * @return List<CheckStrawProduceVo>
     */
    List<CheckStrawProduceVo> getStrawProduceCheckInfo(String year, String areaId) throws Exception;

    /**
     * 计算秸秆利用量颜色标识
     *
     * @param year   年度
     * @param areaId 区域
     * @return List<CheckStrawUtilzeVo>
     * @throws Exception Exception
     */
    List<CheckStrawUtilzeVo> getUtilzeCheckInfo(String year, String areaId) throws Exception;

    /**
     * 计算还田离田量颜色标识
     *
     * @param year   年度
     * @param areaId 区域
     * @return List<CheckStrawLevelingVo>
     * @throws Exception Exception
     */
    List<CheckStrawLevelingVo> getLevelingVo(String year, String areaId) throws Exception;
}
