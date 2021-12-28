package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.DistributionMap;
import com.sofn.agzirdd.vo.DistributionMapVo;
import com.sofn.common.map.MapComponentImpl;
import com.sofn.common.map.MapViewData;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DistributionMapService extends IService<DistributionMap>, MapComponentImpl{
    //查询一年以内的外来物种入侵分布信息
    List<DistributionMapVo> selectByOneYearDuring();
    //地图信息相关接口
    MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String,String> conditions);

    /**
     * 根据关联调查ID，删除数据
     * @param id
     * @return
     */
    boolean removeBySpecInveId(String id);
}
