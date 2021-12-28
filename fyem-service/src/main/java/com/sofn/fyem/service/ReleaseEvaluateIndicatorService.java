package com.sofn.fyem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.ReleaseEvaluateIndicator;
import com.sofn.fyem.vo.SecondEvaluateIndicatorVo;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface ReleaseEvaluateIndicatorService extends IService<ReleaseEvaluateIndicator> {


    /**
     * 获取满足条件的指标评价数据(分页)
     * @param params params
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @return PageUtils
     */
    PageUtils<ReleaseEvaluateIndicator> getReleaseEvaluateIndicatorListByPage(Map<String,Object> params, int pageNo, int pageSize);


    /**
     * 获取满足条件的指标评价数据(不分页)
     * @param params params
     * @return List
     */
    List<ReleaseEvaluateIndicator> getReleaseEvaluateIndicatorListByQuery(Map<String,Object> params);


    /**
     * 获取指定的指标评价信息
     * @param id id
     * @return ReleaseEvaluateIndicator
     */
    ReleaseEvaluateIndicator getReleaseEvaluateIndicatorById(String id);


    /**
     * 修改指标评价信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);


    /**
     * 新增指标评价
     * @param releaseEvaluateIndicator releaseEvaluateIndicator
     */
    void addReleaseEvaluateIndicator(ReleaseEvaluateIndicator releaseEvaluateIndicator);


    /**
     * 修改指标评价
     * @param releaseEvaluateIndicator releaseEvaluateIndicator
     */
    void updateReleaseEvaluateIndicator(ReleaseEvaluateIndicator releaseEvaluateIndicator);;


    /**
     * 删除指标评选
     * @param id id
     */
    void removeReleaseEvaluateIndicator(String id);

    /**
     * 获取一级指标所属二级指标及相关结构
     * @param ids ids
     * @return List
     */
    List <SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorType(List<String> firstIds);

    /**
     * 获取一级指标所属二级指标及相关分数
     * @param ids ids
     * @return List
     */
    List <SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorVo(List<String> firstIds,String BelongYear,String basicReleaseId);

    /**
     * 获取一级指标所属二级指标及相关历史分数
     * @param ids ids
     * @return List
     */
    List <SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorHistory(List<String> firstIds,String BelongYear,String basicReleaseId);

}
