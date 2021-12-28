package com.sofn.fyem.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.ReleaseEvaluateIndicator;
import com.sofn.fyem.vo.SecondEvaluateIndicatorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放评价指标表Mapper
 * @Author: mcc
 */
@Mapper
public interface ReleaseEvaluateIndicatorMapper extends BaseMapper<ReleaseEvaluateIndicator> {

    /**
     * 获取流放评价指标信息List
     * @param params params
     * @return List<ReleaseEvaluateIndicator>
     */
    List<ReleaseEvaluateIndicator> getReleaseEvaluateIndicatorList(Map<String,Object> params);

    /**
     * 获取流放评价指标信息
     * @param id id
     * @return ReleaseEvaluateIndicator
     */
    ReleaseEvaluateIndicator getReleaseEvaluateIndicatorById(String id);

    /**
     * 修改流放评价指标状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 获取一级指标所属二级指标及相关结构
     * @param firstIds firstIds
     * @return List
     */
    List <SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorType(@Param(value="firstIds") List<String> firstIds);

    /**
     * 获取一级指标所属二级指标及相关分数
     * @param firstIds firstIds
     * @param  belongYear belongYear
     * @param basicReleaseId basicReleaseId
     * @return List
     */
    List <SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorVo(@Param(value="firstIds") List<String> firstIds,
                                                                  String belongYear,String basicReleaseId);

    /**
     * 获取一级指标所属二级指标及相关历史分数
     * @param firstIds firstIds
     * @param  belongYear belongYear
     * @param basicReleaseId basicReleaseId
     * @return List
     */
    List <SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorHistory(@Param(value="firstIds") List<String> firstIds,
                                                                  String belongYear,String basicReleaseId);


    /**
     * 删除一级指标下的所有二级指标
     * @param params params
     */
    void removeByParentId(Map<String,Object> params);
}