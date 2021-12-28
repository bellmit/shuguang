package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.CollectFlow;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.vo.CollectFlowWithTotalVo;
import com.sofn.ducss.vo.StrawProduceResVo;
import com.sofn.ducss.vo.UpdateStatusVo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public interface CollectFlowService extends IService<CollectFlow> {

    PageUtils<CollectFlow> getCollectFlowByPage(Integer pageNo, Integer pageSize, List<String> years, String areaId);

    CollectFlowWithTotalVo getWaitForExamineDataForCity(String year, String status, String cityId);

    CollectFlowWithTotalVo getWaitForExamineDataForProvince(String year, String status, String provinceId);

    CollectFlowWithTotalVo getWaitForExamineDataForMinistry(String year, String status, String regioncode);

    String updateStatusForCity(UpdateStatusVo queryVo, String userRegionId, String level, String userId, String userName);

    public List<StrawUtilizeSum> selectDucStrawUtilizeSum(String areaId, String year);

    /**
     * 查询全国任何一个县下有无  上报 ，已读，通过的数据
     *
     * @param parms
     * @return
     */
    Integer selectPass(HashMap<String, Object> parms);


    /**
     * 秸秆产生量
     *
     * @param areaId 区域ID
     * @param year   年度
     * @return List<StrawProduceResVo>
     */
    List<StrawProduceResVo> findStrawProduceData(String areaId, String year) throws JsonProcessingException, IllegalAccessException, InvocationTargetException;

    /**
     * 秸秆利用量
     *
     * @param areaId 区域ID
     * @param year   年度
     * @return List<StrawProduceResVo>
     */
    List<StrawUtilizeSum> findStrawUtilzeData(String areaId, String year) throws JsonProcessingException;

    List<CollectFlow> listByAreaIdsAndYears(List<String> areaIds, List<String> years);
}
